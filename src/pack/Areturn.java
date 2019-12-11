package pack;

public class Areturn extends Instruction {

	public Areturn() 
	{
		
	}
	@Override
	public String print() {
		
		return "areturn";
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
