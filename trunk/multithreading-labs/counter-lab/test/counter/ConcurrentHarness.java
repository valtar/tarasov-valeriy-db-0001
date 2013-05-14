package counter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class ConcurrentHarness {
	public ExecutorService executor;
	public Counterable counter;
	public static final int CYCLES = 1000000;
	
	public ConcurrentHarness() {
		super();
	}

	
	@Before
	public abstract void initCounter();

	@Test
	public void test() throws InterruptedException {
		sumbitTask(CYCLES);
	}
	
	@Before
	public void initExecutor() {
		executor = Executors.newFixedThreadPool(16);
	}
	
	protected void sumbitTask(int CYCLES) throws InterruptedException {
		for (int i = 0; i < CYCLES; i++) {
			executor.submit(newTask());
		}
		waitToAllTasks();
	}

	@After
	public void waitToAllTasks() throws InterruptedException {
		executor.shutdown();
		boolean terminated = executor.awaitTermination(5, TimeUnit.SECONDS);
		Assert.assertTrue(terminated);
		checkResults();
	}
	
	void checkResults() {
		Assert.assertEquals(CYCLES, counter.getVar());
	}

	
	
	protected Runnable newTask() {
		return new Runnable() {
			@Override
			public void run() {
				counter.increment();
			}
		};
	}
}