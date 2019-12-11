package pack;

public class Isub extends Instruction
{
	public Isub()
	{
		
	}
	
	public String print()
	{
		return "isub";
	}
	
	@Override
	public Object getValue() 
	{
		return "isub";
	}

	@Override
	public void execute() 
	{
		int top = (int) VM.operandStack.pop().getDataValue();
		int bottom = (int) VM.operandStack.pop().getDataValue();
		VM.operandStack.push(new IntegerOperand(bottom - top));
		if(VM.operandStack.size() > VM.maxOperandStack)
			VM.maxOperandStack = VM.operandStack.size();
		VM.programCounter++;	
	}
}
