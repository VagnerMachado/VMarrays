package pack;

public class Fdiv extends Instruction
{
	public Fdiv()
	{
		
	}
	
	public String print()
	{
		return "fdiv";
	}
	
	
	@Override
	public Object getValue() 
	{
		return "fdiv";
	}

	@Override
	public void execute() 
	{
		double top = (double) VM.operandStack.pop().getDataValue();
		double bottom = (double) VM.operandStack.pop().getDataValue();
		VM.operandStack.push(new FloatOperand(bottom / top));
		if(VM.operandStack.size() > VM.maxOperandStack)
			VM.maxOperandStack = VM.operandStack.size();
		VM.programCounter++;	
	}
}
