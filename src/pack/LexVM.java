package pack;

/************************************************************************************
 * LexVM - This class provides the driver for a lexical analyzer for tokens defined
 * in the following grammar:
 * 
 * <digit> 		  -> 0 | 1 | ... | 9
 * <unsigned int> -> { <digit> }+
 * <signed int>	  -> (+|-) { <digit> }+
 * <float>		  -> [+|-] ( { <digit> }+ "." { <digit> } | "." { <digit> }+ )
 * <floatE>		  -> <float> (e|E) [+|-] { <digit> }+
 * <instruction>  -> "iconst" | "iload" | "istore" | "fconst" | "fload" | "fstore" |
 *                  "iadd" | "isub" | "imul" | "idiv" | "fadd" | "fsub" | "fmul" | "fdiv" |
 *                  "intToFloat" | "icmpeq" | "icmpne" | "icmplt" | "icmple" | "icmpgt" |
 *                  "icmpge" |  "fcmpeq" | "fcmpne" | "fcmplt" | "fcmple" | "fcmpgt" | "fcmpge" | 
 *                  "goto" | "invoke" | "return" | "ireturn" | "freturn" | "print" |
 *                  "newIntArray" <unsigned int> | "newFloatArray"  <unsigned int> |
       				"aload"  <unsigned int> | "astore"  <unsigned int> |
       				"iaload" <unsigned int> | "iastore" <unsigned int> |
       				"faload" <unsigned int> | "fastore" <unsigned int> | "areturn"
 * <colon> -> ":"
 * <comma> -> ","
 * 
 *  The lexical analyzer is implemented through a DFA that will accept the above tokens.
 *	The DFA states are represented by the Enum type State, located in file State.java
 *
 *  *** The DFA has the following states represented by enum-type literals ***
 *  
 *  A) Accepted states 
 *		STATE			ACCEPTS
 *		Colon			:
 *		Comma			,
 *		Id				Letters: a-z, A-Z
 *		SignedInt		Signed Integers
 *		UnsignedInt		Unsigned Integers
 *		Float			Float numbers without exponentiation
 *		FloatE			Float numbers with exponentiation
 *	
 *	A.1) If accept state is Id, then it is checked for following operator
 *		 states, also represented as enum-type literals:
 *		STATE			OPERATION
 *		iconst  		push integer constant
 *		iload  			push integer value from memory
 *		istore          pop value to memory
 *		fconst          push float constant
 *		fload  		    push float value from memory
 *		fstore          pop float value to memory
 *      iadd            addition of integers
 *      isub  			subtraction of integers
 *      imul  			multiplication of integers
 *      idiv  			division of integers
 *      fadd            addition of floats
 *      fsub  			subtraction of floats
 *      fmul            multiplication of floats
 *      fdiv            division of floats
 *      intToFloat      parse integer to float
 *      icmpeq          integer comparison for ==
 *      icmpne  		integer comparison for !=
 *      icmplt          integer comparison for <
 *      icmple  		integer comparison for <=
 *      icmpgt 			integer comparison for >
 *      icmpge   		integer comparison for >=
 *      fcmpeq  		float comparison for ==
 *      fcmpne  		float comparison for !=
 *      fcmplt  		float comparison for <
 *      fcmple  		float comparison for <=
 *      fcmpgt  		float comparison for >
 *      fcmpge  		float comparison for >=
 *      goto  			unconditional jump
 *      invoke  		method call
 *      return  	    void return
 *      ireturn         integer return
 *      freturn         float return
 *      print           print value
 *      newIntArray     instantiates a new array of integers
 *      newFloatArray   instantiates a new array of float
 *      aload           push the reference to an array object stored in vars[k] onto operand stack
 *      astore			pop the reference to an array object from operand stack and store it in vars[k]
 *      iaload
 *      faload
 *      iastore
 *      fastore
 *	
 *  B) Non-final states:
 *  	STATE			TOKEN
 *  	Start      		the empty string
		Period     		a float that starts with period
		E          		float ending with E or e
		EPlusMinus 		float ending with + or - in exponentiation part
		Plus			Signed float or integer starting with +
 *  	Minus			Signed float or integer starting with -
 *  
 *  C) The DFA has a undefined state denoted as State.UNDEF which signals that
 *     the current token generated an undefined transition.
 *		
 *
 *	Javadocs for the methods in LexVM.java and other classes can be found 
 *  on top of the class/enum declaration and over their method signatures
 *  
 *  ***************************** PLEASE READ ***************************************
 *  
 *  IMPORTANT: In order to allow for extra functionality when parsing, please
 *  read the comment on line 160 of State.java. Commenting out the mentioned block
 *  will allow for parsing of consecutive valid operators not separated by spaces.
 *  i.e iconstimulreturninvoke would be parsed as iconst imul return invoke. 
 *  Commenting out the block in State.java would, however, fail to pass the test
 *  for input file #2 provided because it has 'fconstt' as input. Commenting off
 *  the said block would parse this as Fconst for 'fconst' and invalid token for the last 't'.
 *  Since this does not match the expected output for input 2, the block was 
 *  commented off by default. To pass the "ultimate test" provided in file 
 *  input8.txt, please comment off the block on line 160 of State.java and compare it
 *  to expected8.txt file. 
 *  
 *      Parse safely :)          
 *      -Vagner
 *  
 ***********************************************************************************
 *
 *               @author  VagnerMachado - QCID 23651127 - Fall 2019
 * 
 ************************************************************************************
 */
public abstract class LexVM 
{
	public static String tokens; //holds current concatenated tokens
	public static State state;   //holds current state of DFA

	/**
	 * lexycalAnalysis - Assumes the input and output Stream are initialized
	 * Checks the input file for lexycal problems and prints when one is fould.
	 * Otherwise prints the token and category it belongs to.
	 */
	public static void lexycalAnalysis()
	{		
		int i;

		while ( Stream.intToken != -1 ) // while is not end-of-stream
		{
			i = driver();    // extract the next token
			if (i == 1)	     //valid category found                 		
				Stream.displayln( tokens.trim() + "\t  : " + state.toString().substring(0,1).toUpperCase()
						+ state.toString().substring(1));
			else if (i == 0) //invalid token, lexical error
				Stream.displayln( tokens.trim() + "\t  : Lexical Error, invalid token");
		} 
		//close files in stream
		Stream.close();
	}

	/**
	 * This is the driver of the FA. 
	 * If a valid token is found, assigns it to "tokens" and returns 1.
	 * @return - If an invalid token is found:
	 * 		a) If current state is Id with invalid operator: assigns it to "tokens" and returns 0.
	 * 		b) If current state is Id with valid operator: return 0.
	 * @return - If state is Final, return 1
	 * @return - If end-of-stream is reached without finding any non-whitespace character, returns -1.
	 */
	private static int driver()
	{

		State nextSt; 	// the next state of the FA

		tokens = ""; 	//concatenated tokens
		state = State.Start;

		if (Character.isWhitespace((char) Stream.intToken) )
			Stream.intToken = Stream.getChar();       // get the next non-whitespace character
		if ( Stream.intToken == -1 )				  // end-of-stream is reached
			return -1;

		while ( Stream.intToken != -1 ) // do the body if "intToken" is not end-of-stream
		{
			Stream.charToken = (char) Stream.intToken;// parse int to char
			nextSt = State.nextState(state, Stream.charToken ); //get next state based on char
			//	System.out.println("next state: " + nextSt.toString());
			if ( nextSt == State.UNDEF ) 			  // The current DFA operation will halt.
			{
				if (State.isFinal(state)) 			  // valid token extracted
					return 1; 
				else if(state.equals(State.UNDEF))    //invalid function name found in State checkState() i.e floAT90
					return 0;                      	  //just returning allows for 90 to be parsed as unsigned int

				else  		   	 // "charToken" is a unexpected character
				{
					tokens = tokens + Stream.charToken;
					Stream.intToken = Stream.getNextChar();
					return 0;	 // invalid token found
				}
			}
			else 				 // The DFA will go on.
			{
				state = nextSt;
				tokens = tokens + Stream.charToken;
				Stream.intToken = Stream.getNextChar();
			}
		}

		if (State.isFinal(state))  // end-of-stream is reached while a token is being extracted
			return 1; 			   // valid token extracted
		else
			return 0;              // invalid token found

	} // end driver

	/**
	 * getToken - Extracts the next token using the driver of the FA.
	 * If an invalid token is found, issue an error message.
	 * Used at the VM.java class to parse tokens
	 */
	public static String getToken()
	{
		int i = driver();
		//	System.out.println("This is i: " + i + " and state is " + state.toString());
		if (i == 0) //error
		{
			Stream.displayln(tokens + "\t: Lexical Error, invalid token");
			System.out.println(tokens + "\t: Lexical Error, invalid token");
			Stream.close();
			return null;
		}
		else if (i == 1) // valid
			return tokens;
		else
			return null; // -1 for end of file
	}
	
} 
