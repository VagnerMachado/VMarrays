package pack;

public class Fmul extends Instruction
{
	public Fmul()
	{
		
	}
	
	public String print()
	{
		return "fmul";
	}
	
	@Override
	public Object getValue() 
	{
		return "fmul";
	}

	@Override
	public void execute() 
	{
		double top = (double) VM.operandStack.pop().getDataValue();
		double bottom = (double) VM.operandStack.pop().getDataValue();
		VM.operandStack.push(new FloatOperand(bottom * top));
		if(VM.operandStack.size() > VM.maxOperandStack)
			VM.maxOperandStack = VM.operandStack.size();
		VM.programCounter++;
		
	}
	
}
