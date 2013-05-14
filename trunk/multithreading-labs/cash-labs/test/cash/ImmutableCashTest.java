package cash;

import org.junit.Before;

public class ImmutableCashTest extends AbstractCashTest {

	@Before
	public void setUp() {
		cash = new ImmutableCache<>();
	}

}
