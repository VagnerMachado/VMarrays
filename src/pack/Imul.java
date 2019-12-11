package pack;

public class Imul extends Instruction
{
	public Imul()
	{
		
	}
	
	public String print()
	{
		return "imul";
	}
	
	@Override
	public Object getValue() 
	{
		return "imul";
	}

	@Override
	public void execute() 
	{
		int top = (int) VM.operandStack.pop().getDataValue();
		int bottom = (int) VM.operandStack.pop().getDataValue();
		VM.operandStack.push(new IntegerOperand(bottom * top));
		if(VM.operandStack.size() > VM.maxOperandStack)
			VM.maxOperandStack = VM.operandStack.size();
		VM.programCounter++;		
	}
}
