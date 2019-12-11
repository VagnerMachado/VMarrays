package pack;

public class Fastore extends Instruction 
{
	private int val;

	public Fastore(int x) 
	{
		val = x;
	}

	@Override
	public String print() 
	{
		return "fastore " + val;
	}

	@Override
	public Object getValue() 
	{
		return val;
	}

	@Override
	public void execute() 
	{
		double save = (double) VM.operandStack.pop().getDataValue();
		int [] params = new int [val];
		for (int i = val - 1; i >= 0; i--)
			params[i] = (int) VM.operandStack.pop().getDataValue();
		FloatArrayOperand data = (FloatArrayOperand)VM.operandStack.pop();
		data.set(save, params);
		VM.programCounter++;
	}

}
