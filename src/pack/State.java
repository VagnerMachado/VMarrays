package pack;


/************************************************************************************
 *
 * enum State - This enum was separated from the LexVM class to improve design, readability
 * and to better separate functionalities. This enum contains all the states needed for 
 * the DFA in Assignment 1. The states are populated in a specific order that 
 * separates them by different categories: non-final states, final operand states,
 * final operator states and undefined. Those categories can be found in following the 
 * descriptions below.
 * 
 * **********************************************************************************
 *
 *   @author ProfessorYukawa  -  Edited by VagnerMachado - QCID 23651127 - Fall 2019
 * 
 ************************************************************************************
 */

public enum State 
{
	/****   DO NOT CHANGE THE END VALUES OF EACH CATEGORIES AS IT MAY INTERFERE WITH checkFunction() *******/

	// non-final states     ordinal number

	Start,             		// 0
	Period,            		// 1
	E,                		// 2
	EPlusMinus,        		// 3
	Plus,					// 4
	Minus,					// 5

	// final states operands, do not change order as it may interfere with checkFunction()
	Id,          			// 6      		
	SignedInt,				// 7
	UnsignedInt,			// 8
	Float,					// 9
	FloatE,					// 10
	Colon,					// 11
	Comma,					// 12

	// final states operators, do not change order or checkFunction() will not work
	// you can add values in the middle of categories but do not change end points
	iconst,					// 13	
	iload, 					// 14
	istore,  				// 15
	fconst,  				// ...
	fload,
	fstore,
	iadd,
	isub,  
	imul,  
	idiv,
	fadd,
	fsub,
	fmul,
	fdiv,
	intToFloat,
	icmpeq,
	icmpne,
	icmplt,
	icmple,
	icmpgt,
	icmpge,
	fcmpeq,
	fcmpne,
	fcmplt,
	fcmple,
	fcmpgt,
	fcmpge,
	Goto,  				 // upper case due to goto being a reserved word
	invoke,  
	Return,              // upper case due to return being a reserved word
	ireturn,  
	freturn,			// ...
	print,				// 32
	newIntArray,        //addition to project 4 starts here
	newFloatArray,
	aload,
	astore,
	iaload,
	faload,
	iastore,
	fastore,
	areturn,			//addition to project 4 ends here
	// Undefined: Used to indicate invalid undefined transitions
	//  **** MUST BE LAST IN ENUM **** for checkFunction() to work properly
	UNDEF;				// 33           

	/**
	 * checkFunction - validates that an ID is a valid operator
	 * @param str - the string value of current state
	 * @return - a string value representing the state, null if parameter 'str' is an invalid state.name()
	 */
	public static State checkFunction(String str) 
	{
		if (str.equals("goto")) // special case due to 'goto' being reserved
			return State.Goto;
		else if (str.equals("return")) // special case due to 'return' being reserved
			return State.Return;
		for (int i = State.iconst.ordinal(); i < State.UNDEF.ordinal(); i++) 
		{
			if (State.values()[i].name().equals(str))  // compares the State in enum to current state
				return State.values()[i];
		}
		return null;
	}
	/**
	 * isFinal - compares the ordinal value of current state and first invalid state. If
	 * current state >= first final state: returns true; false otherwise.
	 * @param state
	 * @return
	 */
	public static boolean isFinal(State state)
	{
		if(state.equals(State.Id)) //case ID : check for valid  operator names
		{	
			State st = checkFunction(LexVM.tokens); //checks for a valid ID for operator  
			if(st == null) // there is not a matching operator
			{
				LexVM.state = State.UNDEF; // indicate an invalid  transition for ID, i.e (ID) -> (digit) transition i.e: fstore84
				return false;
			}
			else //there is a matching operator
			{
				LexVM.state = st;
				return true;
			}
		} //for all other, compare state ordinal to ID ordinal
		return (((Integer)state.ordinal()).compareTo((Integer)State.Id.ordinal()) >= 0 );  
	}


	/**
	 * Returns the next state of the DFA given the current state and input char;
	 * if the next state is undefined, UNDEF is returned.
	 * @param state - the current state
	 * @param c - the next token read
	 * @return a valid state if transition exists, UNDEF otherwise.
	 */
	public static State nextState(State state, char c)
	{
		switch(state)
		{
		case Start:
			if ( Character.isLetter(c) )
				return State.Id;
			else if ( Character.isDigit(c) )
				return State.UnsignedInt;
			else if ( c == '+' )
				return State.Plus;
			else if ( c == '-' )
				return State.Minus;
			else if ( c == ':' )
				return State.Colon;
			else if (c == ',')
				return State.Comma;
			else if (c == '.')
				return State.Period;
			else
				return State.UNDEF;

		case Id:
			if (Character.isLetter(c)) //checks for a valid operator
			{
				/*State st = checkFunction(LexVM.tokens + c);
				if(st != null)
					return st;*/ //uncommenting this block will allow for parsing of consecutive valid operators
								  // it was commented off to pass input2.txt test for 'fconstt'
								  // uncommenting would parse 'fconst' as valid and 't' as lexical error.
								  // all other provided test input pass with the above uncommented
								  // NOTE: to pass input8.txt to match expected8.txt, this block MUST be uncommented
				return State.Id;
			}				
			else
				return State.UNDEF;

		case UnsignedInt:
			if (Character.isDigit(c))
				return State.UnsignedInt;
			else if ( c == '.' )
				return State.Float;
			else
				return State.UNDEF;

		case Period:
			if (Character.isDigit(c))
				return State.Float;
			else
				return State.UNDEF;

		case Float:
			if ( Character.isDigit(c) )
				return State.Float;
			else if ( c == 'e' || c == 'E' )
				return State.E;
			else
				return State.UNDEF;
		case E:
			if ( Character.isDigit(c) )
				return State.FloatE;
			else if ( c == '+' || c == '-' )
				return State.EPlusMinus;
			else
				return State.UNDEF;
		case EPlusMinus:
			if ( Character.isDigit(c) )
				return State.FloatE;
			else
				return State.UNDEF;
		case FloatE:
			if ( Character.isDigit(c) )
				return State.FloatE;
			else
				return State.UNDEF;
		case Plus:
			if(c == '.')
				return State.Period;
			else if (Character.isDigit(c))
				return State.SignedInt;
			else 
				return State.UNDEF;
		case Minus:
			if(Character.isDigit(c))
				return State.SignedInt;
			else if (c == '.')
				return State.Period;
			else 
				return State.UNDEF;

		case SignedInt:
			if(Character.isDigit(c))
				return State.SignedInt;
			else if (c == '.')
				return State.Float;
			else 
				return State.UNDEF;
		default:
			return State.UNDEF;
		}
	}
}
