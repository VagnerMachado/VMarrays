package pack;

public class Fload extends Instruction 
{
	int address; //must be between 0 and 999

	public Fload(int a)
	{
		address = a;
	}
	
	public String print()
	{
		return "fload " + address;
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
	public void execute() {
		 Data x = VM.runtimeStack.peek().memory[address];
		 VM.operandStack.push(new FloatOperand((double) (x.getDataValue())));
			if(VM.operandStack.size() > VM.maxOperandStack)
				VM.maxOperandStack = VM.operandStack.size();
			VM.programCounter++;
		
	}
}
