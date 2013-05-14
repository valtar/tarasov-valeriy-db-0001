package cash;

import org.junit.Before;

public class SychronizedCashTest extends AbstractCashTest {

	@Before
	public void setUp() {
		cash = new SychronizedCache<>();
	}
	
}
