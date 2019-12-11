package pack;

public class Istore extends Instruction
{
	private int address; //must be in valid range 0 - 999


	public Istore(int a)
	{
		address = a;
	}
	
	public String print()
	{
		return "istore " + address;
	}
	
	@Override
	public Object getValue() 
	{
		return address;
	}

	@Override
	public void execute() 
	{
		VM.runtimeStack.peek().memory[address] = VM.operandStack.pop();
		VM.programCounter++;
		
	}
}