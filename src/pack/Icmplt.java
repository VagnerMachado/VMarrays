package pack;

public class Icmplt extends Compare
{
	private int jump;

	public Icmplt(int j)
	{
		jump = j;
	}
	
	public String print()
	{
		return "icmplt " + jump;
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
		int top = (int) VM.operandStack.pop().getDataValue();
		int bottom = (int) VM.operandStack.pop().getDataValue();
		if(bottom < top)
			VM.programCounter = jump;
		else
			VM.programCounter++;
	}

}
