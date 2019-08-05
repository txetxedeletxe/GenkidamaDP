package types;

public interface Assembly<A,B> {

	public A assemble(B b);
	public B disassemble(A a);
}
