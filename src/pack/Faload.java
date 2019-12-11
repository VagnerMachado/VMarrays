package pack;

public class Faload extends Instruction 
{
	private int val;
	
	public Faload(int x)
	{
		val = x;
	}

	@Override
	public String print() 
	{
		return "faload " + val;
	}

	@Override
	public Object getValue() 
	{
		return val;
	}

	@Override
	public void execute() 
	{
		int [] params = new int [val];
		for (int i = val - 1; i >= 0; i--)
			params[i] = (int) VM.operandStack.pop().getDataValue();
			
		FloatArrayOperand data = (FloatArrayOperand)VM.operandStack.pop();
		VM.operandStack.push(new FloatOperand((double)data.get(params)));
		VM.programCounter++;
	}

}
