package pack;

public class Ireturn extends Instruction
{
	public Ireturn()
	{
		
	}
	
	public String print()
	{
		return "ireturn";
	}
	
	@Override
	public Object getValue() 
	{
		return "ireturn";
	}

	@Override
	public void execute()
	{
		//operandStack.push(valueFromCalee);
		//if(operandStack.size() > maxOperandStack)
			//maxOperandStack = operandStack.size();
		VM.programCounter = VM.runtimeStack.peek().returnAddress;
		VM.runtimeStack.pop();	
	}
}
