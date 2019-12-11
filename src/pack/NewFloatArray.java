package pack;

public class NewFloatArray extends Instruction
{
	private int val;

	public NewFloatArray(int x)
	{
		val = x;
	}

	@Override
	public String print() 
	{
		return "newFloatArray " + val;
	}

	@Override
	public Object getValue()
	{
		return val;
	}

	@Override
	public void execute() 
	{
		int [] listSize;
		double [] data;
		int dataLength = 1;
		int param;
		listSize = new int [val];
		for(int i = val - 1; i >= 0; i--)
		{
			param = (int)VM.operandStack.pop().getDataValue();
			if(param < 0)
			{
				System.out.println("\nError: Attempting to construct NewFloatArray with negative index size " + param + " while executing "
						+ VM.instructionArray[VM.programCounter].print());
				System.exit(-1);
			}
			listSize[i] = param;
			dataLength *= param;
		}
		data = new double[dataLength];		
		VM.operandStack.push(new FloatArrayOperand(listSize, data));
		if(VM.operandStack.size() > VM.maxOperandStack)
			VM.maxOperandStack = VM.operandStack.size();
		VM.programCounter++;
	}

}
