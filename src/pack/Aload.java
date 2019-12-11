package pack;

public class Aload extends Instruction {

	private int address;
	
	public Aload(int x)
	{
		address = x;
	}
	
	@Override
	public String print() 
	{
		return "aload " + address;
	}

	@Override
	public Object getValue() 
	{
		return address;
	}

	@Override
	public void execute() 
	{
		 Data x = VM.runtimeStack.peek().memory[address];
		 VM.operandStack.push(x);
			if(VM.operandStack.size() > VM.maxOperandStack)
				VM.maxOperandStack = VM.operandStack.size();
			VM.programCounter++;
	}

}
