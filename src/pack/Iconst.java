package pack;

public class Iconst extends Instruction
{
	private int constant; //must check for plus or minus
	
	public Iconst(int c)
	{
		constant = c;
	}
	
	public String print()
	{
		return "iconst " + constant;
	}

	@Override
	public Object getValue()
	{
		return constant;
	}
	
	public int getDataValue()
	{
		return constant;
	}

	@Override
	public void execute() 
	{
		VM.operandStack.push(new IntegerOperand(constant));
		if(VM.operandStack.size() > VM.maxOperandStack)
			VM.maxOperandStack = VM.operandStack.size();
		VM.programCounter++;
	}
	
}
