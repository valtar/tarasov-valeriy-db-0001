package cash;

import java.math.BigInteger;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import cash.interfaces.Computable;

public class MemoizeCache<A, V> implements Computable<A, V> {
	private final Computable<A, V> computer;
	private final ConcurrentMap<A, FutureTask<V>> cache = new ConcurrentHashMap<A, FutureTask<V>>();

	public MemoizeCache(Computable<A, V> computer) {
		super();
		this.computer = computer;
	}

	@Override
	public V compute(final A arg) throws InterruptedException {
		while (true) {
			FutureTask<V> future = cache.get(arg);
			FutureTask<V> tmp = null;

			if (future == null) {
				tmp = new FutureTask<V>(new Callable<V>() {
					@Override
					public V call() throws Exception {
						return computer.compute(arg);
					}
				});

				future = cache.putIfAbsent(arg, tmp);
				if (future == null) {
					future = tmp;
					future.run();
				}
			}

			try {
				return future.get();
			} catch (ExecutionException e) {
				cache.remove(arg);
				throw new RuntimeException();
			} catch (CancellationException e) {
				cache.remove(arg);
			}
		}
	}
}

class ExpensiveComputation implements Computable<String, BigInteger> {

	@Override
	public BigInteger compute(String arg) throws InterruptedException {
		// TODO Auto-generated method stub
		return null;
	}

}
