package pack;

/**
 * Frame Class - allows the program to instantiate runtime frames for the function calls. 
 * All the Frames contain a stack of operands, a memory array, a caller frame and the program counter.
 * Each frame is instantiated and placed onto the runTime stack in VM.java and executed through the 
 * method run. This method iterates through the program instruction array and performs actions according
 * to the semantics below:
 * 
    # 	push value onto operand stack
		->	iconst k : push integer constant k onto stack
		->	iload k : push integer at address k in variable area onto stack
		->	fconst x : push floating-point constant x onto stack
		->	fload k : push floating-point value at address k in variable area onto stack
	# 	pop top of operand stack and store
	
		->	istore k : pop top of stack, which is assumed to be an integer, and store it in address k in variable area
		->	fstore k : pop top of stack, which is assumed to be a floating-point number, and store it in address k in variable area
	
	# 	arithmetic
		->	iadd, isub, imul, idiv : pop top two integer values from stack, apply operator to stack[top-1] and stack[top], push result onto stack
		->	fadd, fsub, fmul, fdiv : pop top two floating-point values from stack, apply operator to stack[top-1] and stack[top], push result onto stack
	
	# 	type conversion
		->	intToFloat : convert integer at top of stack to floating-point number
	
	# 	comparison-jump
		->	icmpeq k :   pop top two integer values from stack; 
			if stack[top-1] = stack[top] then goto instruction at address k
		->	icmpne k :   pop top two integer values from stack; 
			if stack[top-1] != stack[top] then goto instruction at address k
		->	icmplt k :   pop top two integer values from stack; 
			if stack[top-1] < stack[top] then goto instruction at address k
		->	icmple k :   pop top two integer values from stack; 
			if stack[top-1] <= stack[top] then goto instruction at address k
		->	icmpgt k :   pop top two integer values from stack; 
			if stack[top-1] > stack[top] then goto instruction at address k
		->	icmpge k :   pop top two integer values from stack; 
			if stack[top-1] >= stack[top] then goto instruction at address k
		->	Likewise for fcmpeq, fcmpne, fcmplt, fcmple, fcmpgt, fcmpge
	
	# 	unconditional jump
		->	goto k : go to instruction at address k
	
	# 	function invocation
		->	invoke k1, k2, k3 : invoke function code that:
		 	starts at label k1, k2 = the # of parameters, k3 = the # of local variables
	
	# 	function return
		->	return : return from void-type function
		->	ireturn : return from function whose return type is integer
		->	freturn : return from function whose return type is floating-point
		
	# 	print
		->	print k : print value at address k in variable area on the screen
		
 *************************************************************************************************************
		For more information about the methods, please check their javadocs
 *************************************************************************************************************

 *************************************************************************************************************
 *                      @author Vagner Machado - QC ID 23651127 - Fall 2019
 *************************************************************************************************************
 */
public class Frame extends VM
{
	protected  Data [] memory;
	protected int returnAddress;
	
	/**
	 * Frame constructor - instantiates a Frame to be placed on the runtime Stack
	 * @param pc - the program counter
	 * @param os - the operand stack
	 * @param mem - the memory needed for the frame local vars and param
	 * @param c - the caller frame
	 */
	public Frame(Data []  mem , int r)
	{
		returnAddress = r;
		memory = mem;
	}

}