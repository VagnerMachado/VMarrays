package pack;

public class Goto extends Instruction
{
	private int jump;
	
	public Goto(int j)
	{
		jump = j;
	}
	
	public String print()
	{
		return "goto "+ jump;
	}
	
	public void refract()
	{
		jump = VM.jumpMap.get(jump);
	}
	
	@Override
	public Object getValue() 
	{
		return jump;
	}

	@Override
	public void execute() 
	{
		VM.programCounter = jump;
		
	}
}
