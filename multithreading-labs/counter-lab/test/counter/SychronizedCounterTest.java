package counter;

import org.junit.Before;

public class SychronizedCounterTest extends ConcurrentHarness {


	@Override
	@Before
	public void initCounter() {
		counter = new SychronizedCounter();
	}

}
