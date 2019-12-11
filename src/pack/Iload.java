package pack;

public class Iload extends Instruction
{
	private int address; //must not be negative and in range 0 to 999
	
	public Iload(int a)
	{
		address = a;
	}
	
	public String print()
	{
		return "iload " + address;
	}
	
	@Override
	public Object getValue() 
	{
		return address;
	}
	
	public int getDataValue()
	{
		return address;
	}

	@Override
	public void execute() 
	{
	 Data x = VM.runtimeStack.peek().memory[address];
	 VM.operandStack.push(new IntegerOperand((int) (x.getDataValue())));
		if(VM.operandStack.size() > VM.maxOperandStack)
			VM.maxOperandStack = VM.operandStack.size();
		VM.programCounter++;
	}
}
