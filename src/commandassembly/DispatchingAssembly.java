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
		
		B b2 = decomposeB(b);
		
		A a = (A)assembly.assemble(b2);
		
		return composeA(a,b);
		
	}
	
	public B disassemble(A a) {
		
		int dispatcherIndex = dispatchA(a);
		Assembly assembly = dispatchingList[dispatcherIndex];
		
		A a2 = decomposeA(a);
		
		B b = (B) assembly.disassemble(a2);
		
		return composeB(b,a);
		
	}
	
	public abstract int dispatchA(A a);
	public abstract int dispatchB(B b);
	
	public A decomposeA (A a) {return a;}
	public B decomposeB (B b) {return b;}
	
	public A composeA (A a, B b) {return a;}
	public B composeB (B b, A a) {return b;}
	
	
	
}
