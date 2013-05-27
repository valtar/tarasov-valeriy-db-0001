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
	public long timeExpired = 100_000;
	private final Computable<A, V> computer;
	private final ConcurrentMap<A, FutureData<V>> cache = new ConcurrentHashMap<>();

	
	private class FutureData<V> {
		FutureTask<V> future;
		private long timeComputed;
		long timeExpired;

		public FutureData(FutureTask<V> future, long timeExpired) {
			super();
			this.future = future;
			this.timeExpired = timeExpired;
		}

		public boolean isExpired() {
			return System.currentTimeMillis() - getTimeComputed() >= timeExpired;
		}

		public long getTimeComputed() {
			return timeComputed;
		}

		public void setTimeComputed() {
			this.timeComputed = System.currentTimeMillis();
		}
	}
	
	public MemoizeCache(Computable<A, V> computer) {
		super();
		this.computer = computer;
	}

	@Override
	public V compute(final A arg) throws InterruptedException {
		while (true) {
			FutureData<V> data = cache.get(arg);

			FutureTask<V> future = null;

			if (data != null && data.isExpired()) {
				cache.remove(arg);
				continue;
			}

			if (data != null) {
				future = data.future;
			} else {

				future = new FutureTask<V>(new Callable<V>() {
					@Override
					public V call() throws Exception {
						return computer.compute(arg);
					}
				});

				data = new FutureData<>(future, timeExpired);

				FutureData<V> tmp = cache.putIfAbsent(arg, data);

				if (tmp == null) {
					future.run();
					data.setTimeComputed();
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
		return new BigInteger(arg);
	}

}
