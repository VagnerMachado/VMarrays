package pack;

public class Iaload extends Instruction 
{

	private int val;
	
	public Iaload(int x)
	{
		val = x;
	}
	@Override
	public String print() 
	{
		return "iaload " + val;
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
			
		IntegerArrayOperand data = (IntegerArrayOperand)VM.operandStack.pop();
		VM.operandStack.push(new IntegerOperand((int)data.get(params)));
		VM.programCounter++;
	}

}
