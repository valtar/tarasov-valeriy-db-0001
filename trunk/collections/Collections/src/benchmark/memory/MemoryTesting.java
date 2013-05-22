package benchmark.memory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;


public class MemoryTesting {
	private static final Runtime runtime = Runtime.getRuntime();
	
	public static void main(String[] args) {
		Random rand = new Random();
		int i = 0;
		long before = runtime.freeMemory();
		
		//LinkedList
		runtime.gc();
		before = runtime.totalMemory() - runtime.freeMemory();
		List<Integer> list = new LinkedList<Integer>();
		int size = 1_000_000;
		for (; i < size; ++i) {
			list.add(rand.nextInt());
		}

		System.out.println(LinkedList.class.getSimpleName() + ": " + ( - before +
				(runtime.totalMemory() - runtime.freeMemory())));
		

		//ArrayList
		runtime.gc();
		before = runtime.totalMemory() - runtime.freeMemory();
		list = new ArrayList<>();
		for (i = 0; i < size; ++i) {
			list.add(rand.nextInt());
		}
		System.out.println(ArrayList.class.getSimpleName() + ":  " + ( - before +
				runtime.totalMemory() - runtime.freeMemory()));
		
		//Array
//		runtime.gc();
		before = runtime.totalMemory() - runtime.freeMemory();
		int[] mass = new int[size];
		for (i = 0; i < size; ++i) {
			mass[i] = rand.nextInt();
		}
		
		System.out.println( "Array:      " + ( - before +
				runtime.totalMemory() - runtime.freeMemory()));
		
		//HashMap
		runtime.gc();
		before = runtime.totalMemory() - runtime.freeMemory();
		Map<Integer,Integer> map = new HashMap<>();
		for (i = 0; i < size; ++i) {
			map.put(i, rand.nextInt());
		}
		System.out.println(HashMap.class.getSimpleName() + ":    " + ( - before +
				runtime.totalMemory() - runtime.freeMemory()));
		
		//TreeMap
		runtime.gc();
		before = runtime.totalMemory() - runtime.freeMemory();
		map = new TreeMap<>();
		for (i = 0; i < size; ++i) {
			map.put(i, rand.nextInt());
		}
		System.out.println(TreeMap.class.getSimpleName() + ":    " + (- before +
				runtime.totalMemory() - runtime.freeMemory()));
		
		/*
		 * Console out: 
		 * 
		 * size: 1 000 000
		 * 
		 *  LinkedList: 39758088
		 *	ArrayList:  24460664
	     * 	Array:      4000016
		 *	HashMap:    68994952
		 *	TreeMap:    72964808
		 */
		
	}
}
