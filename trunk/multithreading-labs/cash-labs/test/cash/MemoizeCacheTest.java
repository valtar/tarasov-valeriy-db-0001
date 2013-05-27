package cash;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;

import cash.interfaces.Computable;

public class MemoizeCacheTest {

	Computable<Integer, Integer> computer;
	MemoizeCache<Integer, Integer> memoizeCache;
	Integer count = 0;

	@Before
	public void setUp() throws InterruptedException {
		computer = (Computable<Integer, Integer>) mock(Computable.class);
		memoizeCache = new MemoizeCache<>(computer);
	}

	@Test
	public void compute_shouldComputeEveryTime_whenUniqueArgsComputed()
			throws InterruptedException {
		int iterations = 10;
		for (Integer i = 0; i < iterations; i++) {
			memoizeCache.compute(i);
		}

		verify(computer, times(iterations)).compute(anyInt());
	}
	
	@Test
	public void compute_shouldNotCompute_whenSameArgs() throws InterruptedException{
		int iterations = 10;
		for(Integer i = 0; i < iterations; i++ ){
			memoizeCache.compute(0);
			memoizeCache.compute(1);
		}
		
		verify(computer, times(2)).compute(anyInt());
	}
	
	@Test
	public void compute_shouldRecompute_whenExpired() throws InterruptedException{
		memoizeCache.timeExpired = 0;
		memoizeCache.compute(0);
		memoizeCache.compute(0);
		
		verify(computer, times(2)).compute(anyInt());
	}

}
