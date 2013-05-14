package counter;

import org.junit.Before;

public class AtomicCounterTest extends ConcurrentHarness{
	
	
	@Before
	public void initCounter(){
		counter = new AtomicCounter();
	}


}
