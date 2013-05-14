package counter;

public class VolatileCounter implements Counterable {
	private volatile int var = 0;
	
	@Override
	public void increment() {
		var++;
	}

	@Override
	public int getVar() {
		return var;
	}

	@Override
	public void setVar(int var) {
		this.var = var;
	}

	
}
