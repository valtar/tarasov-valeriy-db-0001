package counter;

import org.junit.Before;

public class VolatileCounterTest extends ConcurrentHarness {

	@Override
	@Before
	public void initCounter(){
		counter = new VolatileCounter();
	}


}
