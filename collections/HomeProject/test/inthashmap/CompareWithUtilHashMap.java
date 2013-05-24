package inthashmap;

import org.junit.Assert;
import org.junit.Test;

public class CompareWithUtilHashMap {

	@Test
	public void myMapshouldUseLessMemoryThanUtilHashMap(){
		IHashMap map = new HashMap();
		Runtime runtime = Runtime.getRuntime();
		java.util.HashMap<Integer, Integer> utilMap = new java.util.HashMap<>();
		
		long before = 0;
	
		long myMapAfter = 0;
		long utilMapAfter = 0;
		int SIZE = 1_000_000;
		
		runtime.gc();
		before = runtime.totalMemory() - runtime.freeMemory();
		for (int i = 0; i < SIZE; i++) {
			map.put(i, i);
		}
		myMapAfter = (runtime.totalMemory() - runtime.freeMemory()) - before;
		System.out.println("my :" + myMapAfter/SIZE);
		
		runtime.gc();
		before = runtime.totalMemory() - runtime.freeMemory();
		for (int i = 0; i < SIZE; i++) {
			utilMap.put(i, i);
		}
		utilMapAfter = (runtime.totalMemory() - runtime.freeMemory()) - before;
		System.out.println("ut :" + utilMapAfter/SIZE);
		
		Assert.assertTrue(myMapAfter < utilMapAfter);
	}
}
