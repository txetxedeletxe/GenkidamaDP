package commandassembly;

import types.Assembly;

@SuppressWarnings({ "rawtypes", "unchecked" })
public abstract class DispatchingAssembly<A,B> implements Assembly<A,B> {

	private Assembly[] dispatchingList;
	
	public DispatchingAssembly(Assembly[] dispatchingList) {
		this.dispatchingList = dispatchingList;
	}
		
	public A assemble(B b) {
		
		int dispatcherIndex = dispatchB(b);
		Assembly assembly = dispatchingList[dispatcherIndex];
		return (A)assembly.assemble(b);
		
	}
	
	public B disassemble(A a) {
		
		int dispatcherIndex = dispatchA(a);
		Assembly assembly = dispatchingList[dispatcherIndex];
		return (B) assembly.disassemble(a);
	}
	
	public abstract int dispatchA(A a);
	public abstract int dispatchB(B b);
	
}
