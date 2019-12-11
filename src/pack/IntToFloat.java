package pack;

public class IntToFloat extends Instruction
{
	public IntToFloat()
	{
		
	}
	
	public String print()
	{
		return "intToFloat";
	}
	
	@Override
	public Object getValue() 
	{
		return "intToFloat";
	}

	@Override
	public void execute() 
	{
		VM.operandStack.push(new FloatOperand((double)((int)VM.operandStack.pop().getDataValue()) + 0.0));
		if(VM.operandStack.size() > VM.maxOperandStack)
			VM.maxOperandStack = VM.operandStack.size();
		VM.programCounter++;
	}
}
