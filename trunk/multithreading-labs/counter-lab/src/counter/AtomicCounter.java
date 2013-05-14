package counter;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter implements Counterable{
	AtomicInteger var = new AtomicInteger(0);
	@Override
	public void increment() {
		var.incrementAndGet();
	}

	@Override
	public int getVar() {
		return var.get();
	}

	@Override
	public void setVar(int var) {
		this.var.set(var);
	}

}
