package counter;

public class UnsafeCounter implements Counterable {
	private int var = 0;
	
	public void increment() {
		var++;
	}

	public int getVar() {
		return var;
	}

	public void setVar(int var) {
		this.var = var;
	}
	
	
	
}
