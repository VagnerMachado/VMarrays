package pack;

public class Iadd extends Instruction
{
	public Iadd()
	{
		
	}
	
	public String print()
	{
		return "iadd";
	}
	
	@Override
	public Object getValue() 
	{
		return "iadd";
	}

	@Override
	public void execute()
	{
		int top = (int)VM.operandStack.pop().getDataValue();
		int bottom = (int) VM.operandStack.pop().getDataValue();
		VM.operandStack.push(new IntegerOperand(bottom + top));
		if(VM.operandStack.size() > VM.maxOperandStack)
			VM.maxOperandStack = VM.operandStack.size();
		VM.programCounter++;
	}
}
