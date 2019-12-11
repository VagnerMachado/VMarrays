package pack;

public class Astore extends Instruction {

	private int address;
	
	public Astore(int x)
	{
		address = x;
	}
	
	@Override
	public String print() 
	{
		return "astore " + address;
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
