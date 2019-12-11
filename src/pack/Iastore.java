package pack;

public class Iastore extends Instruction 
{
	private int val;

	public Iastore(int x)
	{
		val = x;
	}
	
	@Override
	public String print() 
	{
		return "iastore " + val;
	}

	@Override
	public Object getValue() 
	{
		return val;
	}

	@Override
	public void execute() 
	{
		int save = (int) VM.operandStack.pop().getDataValue();
		int [] params = new int [val];
		for (int i = val - 1; i >= 0; i--)	
			params[i] = (int) VM.operandStack.pop().getDataValue();

		IntegerArrayOperand data = (IntegerArrayOperand)VM.operandStack.pop();
		data.set(save, params);	
		VM.programCounter++;
	}

}
