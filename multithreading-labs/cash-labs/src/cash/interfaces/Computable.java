package cash.interfaces;

public interface Computable<A,V> {

	public V compute(A arg) throws InterruptedException;
	
}
