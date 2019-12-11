package pack;

public class Fcmpge extends Compare
{
	private int jump;

	public Fcmpge(int j)
	{
		jump = j;
	}
	
	public String print() 
	{	
		return "fcmpge " + jump;
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
		double top = (double) VM.operandStack.pop().getDataValue();
		double bottom = (double) VM.operandStack.pop().getDataValue();
		if(bottom >= top)
			VM.programCounter = jump;
		else
			VM.programCounter++;
	}
}
