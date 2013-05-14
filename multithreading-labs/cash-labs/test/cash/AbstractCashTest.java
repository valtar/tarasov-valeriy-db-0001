package cash;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractCashTest {

	private int CYCLE = 100000;
	protected AtomicInteger hit = new AtomicInteger(0);
	protected AtomicInteger fail = new AtomicInteger(0);

	protected Cachable<Integer, Integer> cash;
	private ExecutorService executor;

	public class CashRun implements Runnable {
		Random rand = new Random();
		Integer generatedKey;
		Integer value;

		@Override
		public void run() {
			generatedKey = rand.nextInt(3);

			value = cash.get(generatedKey);

			if (value == null) {
				cash.put(generatedKey, generatedKey);
			} else {
				if (!generatedKey.equals(value)) {
					fail.incrementAndGet();
					cash.put(generatedKey, generatedKey);
				}
				hit.incrementAndGet();
			}

		}
	}
	
	
	public AbstractCashTest() {
		super();
	}

	@Test
	public void test() {
		for (int i = 0; i < CYCLE; i++) {
			executor.execute(new CashRun());
		}
	}

	@After
	public void tearDown() throws InterruptedException {
		executor.shutdown();
		boolean terminated = executor.awaitTermination(5, TimeUnit.SECONDS);
		System.out.println("hits: " + hit);
		System.out.println("fails: " + fail);
		Assert.assertTrue(terminated);
		Assert.assertEquals(0, fail.get());
	}

	@Before
	public abstract void setUp();
	
	@Before
	public void initExecutor(){
		executor = Executors.newFixedThreadPool(1000);
	}

}