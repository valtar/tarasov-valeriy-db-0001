package cash;

import org.junit.Before;

public class UnsafeCashTest extends AbstractCashTest {

	@Before
	public void setUp() {
		cash = new UnsafeCache<>();
	}


}
