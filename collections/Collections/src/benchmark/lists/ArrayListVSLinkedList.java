package benchmark.lists;

import java.util.*;

public class ArrayListVSLinkedList {

	public static void main(String[] args) {
		ArrayList<String> arrayList = new ArrayList<>();
		LinkedList<String> linkedList = new LinkedList<>();
		
		long arrayTime = 0;
		long linkedTime = 0;
		int ITERATIONS = 10_000;
		String s = "STRING";

		//simple
		for (int i = 0; i < ITERATIONS; i++) {
			arrayTime = System.currentTimeMillis();
			arrayList.add(0, s);
			arrayTime = System.currentTimeMillis() - arrayTime;
			
			linkedTime = System.currentTimeMillis();
			linkedList.add(0,s);
			linkedTime = System.currentTimeMillis() - linkedTime;
			
			if(linkedTime < arrayTime){
				System.out.println("simlple: " + i);
				break;
			}
		}

		//warm
		arrayTime = 0;
		linkedTime = 0;
		arrayList.removeAll(arrayList);
		linkedList.removeAll(linkedList);
		
		for (int i = 0; i < ITERATIONS; i++) {
			//1-10
			arrayTime = System.currentTimeMillis();
			arrayList.add(0, s);//1
			arrayList.add(0, s);//2
			arrayList.add(0, s);//3
			arrayList.add(0, s);//4
			arrayList.add(0, s);//5
			arrayList.add(0, s);//6
			arrayList.add(0, s);//7
			arrayList.add(0, s);//8
			arrayList.add(0, s);//9
			arrayList.add(0, s);//10
			arrayTime = System.currentTimeMillis() - arrayTime;
			
			//1-10
			linkedTime = System.currentTimeMillis();
			linkedList.add(0,s);//1
			linkedList.add(0,s);//2
			linkedList.add(0,s);//3
			linkedList.add(0,s);//4
			linkedList.add(0,s);//5
			linkedList.add(0,s);//6
			linkedList.add(0,s);//7
			linkedList.add(0,s);//8
			linkedList.add(0,s);//9
			linkedList.add(0,s);//10
			linkedTime = System.currentTimeMillis() - linkedTime;
			if(linkedTime < arrayTime){
				
			}
			
		}
		
		arrayTime = 0;
		linkedTime = 0;
		arrayList.removeAll(arrayList);
		linkedList.removeAll(linkedList);
		
		arrayList = new ArrayList<>(10_000);
		linkedList = new LinkedList<>(arrayList);
		//go
		for (long i = 0; i < ITERATIONS; i++) {
			//1-10
			arrayTime = System.currentTimeMillis();
			arrayList.add(0, s);//1
			arrayList.add(0, s);//2
			arrayList.add(0, s);//3
			arrayList.add(0, s);//4
			arrayList.add(0, s);//5
			arrayList.add(0, s);//6
			arrayList.add(0, s);//7
			arrayList.add(0, s);//8
			arrayList.add(0, s);//9
			arrayList.add(0, s);//10
			arrayTime = System.currentTimeMillis() - arrayTime;
			
			//1-10
			linkedTime = System.currentTimeMillis();
			linkedList.add(0,s);//1
			linkedList.add(0,s);//2
			linkedList.add(0,s);//3
			linkedList.add(0,s);//4
			linkedList.add(0,s);//5
			linkedList.add(0,s);//6
			linkedList.add(0,s);//7
			linkedList.add(0,s);//8
			linkedList.add(0,s);//9
			linkedList.add(0,s);//10
			linkedTime = System.currentTimeMillis() - linkedTime;
			if(linkedTime < arrayTime){
				System.out.println("result iteration: " + i*10);
				break;
			}
			
		}
		
	}
	
}
