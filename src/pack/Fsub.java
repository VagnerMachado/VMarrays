package pack;

public class Fsub extends Instruction
{
	public Fsub()
	{
		
	}
	
	public String print()
	{
		return "fsub";
	}
	
	@Override
	public Object getValue() 
	{
		return "fsub";
	}

	@Override
	public void execute() 
	{
		double top = (double) VM.operandStack.pop().getDataValue();
		double bottom = (double) VM.operandStack.pop().getDataValue();
		VM.operandStack.push(new FloatOperand(bottom - top));
		if(VM.operandStack.size() > VM.maxOperandStack)
			VM.maxOperandStack = VM.operandStack.size();
		VM.programCounter++;
	}
}
