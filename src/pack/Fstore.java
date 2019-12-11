package pack;

public class Fstore extends Instruction 
{
	int address; //must be between 0 and 999

	public Fstore(int a)
	{
		address = a;
	}
	
	public String print()
	{
		return "fstore " + address;
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
