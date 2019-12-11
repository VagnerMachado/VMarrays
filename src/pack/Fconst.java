package pack;

public class Fconst extends Instruction
{
	private double constant; //must work for float and float E

	public Fconst(double d)
	{
		constant = d;
	}
	
	public String print() 
	{	
		return "fconst " + constant ;
	}
	
	@Override
	public Object getValue() 
	{
		return constant;
	}

	public double getDataValue() {
		
		return constant;
	}

	@Override
	public void execute() 
	{
		VM.operandStack.push(new FloatOperand(constant));
		if(VM.operandStack.size() > VM.maxOperandStack)
			VM.maxOperandStack = VM.operandStack.size();
		VM.programCounter++;
	}

}
