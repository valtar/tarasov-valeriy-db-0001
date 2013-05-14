package counter;

import org.junit.Before;

public class UnsafeCounterTest extends ConcurrentHarness {

	@Override
	@Before
	public void initCounter(){
		counter = new UnsafeCounter();
	}

}
