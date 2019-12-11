package pack;

public class Invoke extends Instruction
{
	private int a, b, c;

	public Invoke(int i, int j, int k)
	{
		a = i;
		b = j;
		c = k;
	}

	public String print()
	{
		return "invoke " + a + ", " + b + ", " + c;
	}

	public void refract()
	{
		a = VM.jumpMap.get(a);
	}
	
	@Override
	public Object getValue() 
	{
		return new Integer [] {a,b,c};
	}

	@Override
	public void execute() 
	{
		Data [] paramAndMemory = new Data[b + c]; 
		int param = b;	
		//System.out.println("params " + param + " - stack size: " + VM.operandStack.size());
		while(param > 0)
		{
			paramAndMemory[--param] = VM.operandStack.pop();
		}
		
		VM.runtimeStack.push(new Frame(paramAndMemory, VM.programCounter + 1));
		if(VM.runtimeStack.size() > VM.maxRuntimeStack)
			VM.maxRuntimeStack = VM.runtimeStack.size();
		VM.programCounter = a;
	}

}
