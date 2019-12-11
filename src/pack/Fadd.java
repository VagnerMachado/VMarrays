package pack;

public class Fadd extends Instruction
{
	public Fadd()
	{
	}

	public String print()
	{	
		return "fadd";
	}

	@Override
	public Object getValue() 
	{
		return null;
	}

	@Override
	public void execute() {
		double top = (double) VM.operandStack.pop().getDataValue();
		double bottom = (double) VM.operandStack.pop().getDataValue();
		VM.operandStack.push(new FloatOperand(bottom + top));
		if(VM.operandStack.size() > VM.maxOperandStack)
			VM.maxOperandStack = VM.operandStack.size();
		VM.programCounter++;
	}

}
