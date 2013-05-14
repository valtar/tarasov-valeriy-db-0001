package counter;

public class SychronizedCounter implements Counterable {

	public int var = 0;
	
	@Override
	public synchronized void increment() {
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
