package commandassembly;

import types.Assembly;

@SuppressWarnings({"rawtypes","unchecked"})
public abstract class DispatchingAssembly<A,B> implements Assembly<A, B>{

	private Assembly[] assemblyMap;
	
	public DispatchingAssembly( Assembly<? extends A,B>[] assemblyMap) {
		this.assemblyMap = assemblyMap;
	}
	
	public A dispatchAssembly(B b, int dispatcherIndex) {
		
		// Check errors
		if (assemblyMap == null)
			throw new NullPointerException();
		
		if (dispatcherIndex < 0 || dispatcherIndex >= assemblyMap.length)
			throw new IndexOutOfBoundsException();
		
		if (assemblyMap[dispatcherIndex] == null)
			throw new NullPointerException();
		
		return (A)assemblyMap[dispatcherIndex].assemble(b);
		
	}
	
	public B dispatchDisAssembly(A a, int dispatcherIndex) {
		
		// Check errors
		if (assemblyMap == null)
			throw new NullPointerException();
		
		if (dispatcherIndex < 0 || dispatcherIndex >= assemblyMap.length)
			throw new IndexOutOfBoundsException();
		
		if (assemblyMap[dispatcherIndex] == null)
			throw new NullPointerException();
		
		return (B)assemblyMap[dispatcherIndex].disassemble(a);
		
	}

	
}
