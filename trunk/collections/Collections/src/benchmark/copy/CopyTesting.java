package benchmark.copy;

import java.util.Random;

public class CopyTesting {
	
	class TimeEntry{
		long systemTime = 0;
		long iteratorTime = 0;
		public TimeEntry(long systemTime, long iteratorTime) {
			super();
			this.systemTime = systemTime;
			this.iteratorTime = iteratorTime;
		}
		
	}
	
	
	private Random rand = new Random();

	public static void main(String[] args) {
		CopyTesting test = new CopyTesting();
		
		for(int i = 0; i< 10_000; i++)
			test.copyArrayTest(1_000,false);
		test.copyArrayTest(1_000,true);
		
		for(int i = 0; i< 10_000; i++)
			test.copyArrayTest(10_000,false);
		test.copyArrayTest(100_000,true);

		for(int i = 0; i< 10; i++)
			test.copyArrayTest(10_000_000,false);
		test.copyArrayTest(10_000_000,true);
		
		test.copyArrayTest(100_000_000,true);
		
		
		

	}

	protected void copyArrayTest(int SIZE, boolean out) {
		int[] toCopy = new int[SIZE];
		for (int i = 0; i < toCopy.length; i++) {
			toCopy[i] = rand.nextInt(); 
		}
		int[] copy = new int[SIZE];

		long systemTime = 0;
		long iteratorTime = 0;
		
		systemTime = System.nanoTime();
		System.arraycopy(toCopy, 0, copy, 0, SIZE);
		systemTime = System.nanoTime() - systemTime;
		
		iteratorTime = System.nanoTime();
		for (int i = 0; i < toCopy.length; i++) {
			copy[i] = toCopy[i];
		}
		iteratorTime = System.nanoTime() - iteratorTime;
		if(out){
			System.out.println("size: " + SIZE);
			System.out.println("System.arraycopy time: " + systemTime);
			System.out.println("Own copy time: " + iteratorTime);
			System.out.println("system arraycopy better in " + iteratorTime/(systemTime*1.f) + " times");
			System.out.println();
		}
	}

}
