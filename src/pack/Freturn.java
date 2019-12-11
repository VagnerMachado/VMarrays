package pack;

public class Freturn extends Instruction
{
	public Freturn()
	{
		
	}
	
	public String print()
	{
		return "freturn";
	}
	
	@Override
	public Object getValue() 
	{
		return null;
	}

	@Override
	public void execute() 
	{
		VM.programCounter = VM.runtimeStack.peek().returnAddress;
		VM.runtimeStack.pop();		
	}
}
