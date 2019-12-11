package pack;

public class Idiv extends Instruction
{
	public Idiv()
	{
		
	}
	
	public String print()
	{
		return "idiv";
	}
	
	@Override
	public Object getValue() 
	{
		return "idiv";
	}

	@Override
	public void execute()
	{
		int top = (int) VM.operandStack.pop().getDataValue();
		int bottom = (int) VM.operandStack.pop().getDataValue();
		VM.operandStack.push(new IntegerOperand(bottom / top));
		if(VM.operandStack.size() > VM.maxOperandStack)
			VM.maxOperandStack = VM.operandStack.size();
		VM.programCounter++;
	}
}
