package inthashmap;

public class HashMap implements IHashMap {

	private static final float LOAD_FACTOR = 0.75f;

	private static final int INITIAL_BACKETS_CAPACITY = 1_000_000;

	private static final int INITIAL_BACKET_CAPACITY = 2;

	private int[][][] backets;

	 private int nElements;

	enum EntryOrder {
		KEY, VALUE;
	}

	int hash(int key) {
		int h = key;

		h ^= (h >>> 20) ^ (h >>> 12);
		h = h ^ (h >>> 7) ^ (h >>> 4);
		return h % backets.length;
	}

	private int[] newEntry(int key, int value) {
		return new int[] { key, value };
	}

	public HashMap() {
		backets = new int[INITIAL_BACKETS_CAPACITY][INITIAL_BACKET_CAPACITY][];
		nElements = 0;
	}

	@Override
	public boolean contains(int key) {
		boolean contians = true;
		try {
			get(key);
		} catch (NoSuchKeyException e) {
			contians = false;
		}
		return contians;
	}

	@Override
	public int get(int key) throws NoSuchKeyException {
		int hash = hash(key);
		int[][] list = backets[hash];

		for (int[] listEntry : list) {
			if (listEntry == null){
				throw new NoSuchKeyException();
			}

			if (listEntry[0] == key) {
				return listEntry[1];
			}
		}

		throw new NoSuchKeyException();
	}

	@Override
	public boolean remove(int key) {
		int hash = hash(key);

		int[][] list = backets[hash];

		for (int i = 0; i < list.length; i++) {
		if (list[i] != null && list[i][0] == key) {
				backets[hash][i] = null;
				nElements--;
				return true;
			}
		}

		return false;
	}

	@Override
	public int put(int key, int value) {
		
		resize();
		
		int hash = hash(key);
		int[] entry = newEntry(key, value);

		int[][] list = backets[hash];

		for (int i = 0; i < list.length; i++) {
			if (list[i] == null || list[i][0] == key) {
				if(list[i] == null) nElements++;
				list[i] = entry;
				return value;
			}
		}

		int[][] newList = new int[(int) (list.length * 2)][];
		System.arraycopy(list, 0, newList, 0, list.length);
		newList[list.length] = entry;
		nElements++;
		
		backets[hash] = newList;
		
		for(int i = list.length+1; i < newList.length; i++){
			backets[hash][i] = null;
		}

		return value;
	}

	private void resize(){
		if(nElements*LOAD_FACTOR < backets.length)
			return;
		int[][][] oldBackets = backets;
		backets = new int[(int)(backets.length*1.1) + 1][INITIAL_BACKET_CAPACITY][];
		
		nElements = 0;
		for (int[][] backet : oldBackets) {
			for (int[] entry : backet) {
				if(entry != null){
					put(entry[0],entry[1]);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		IHashMap map = new HashMap();
		Runtime runtime = Runtime.getRuntime();
		java.util.HashMap<Integer, Integer> utilMap = new java.util.HashMap<>();
		
		long before = 0;
		long after = 0;
		int SIZE = 1_000_000;
		
		runtime.gc();
		before = runtime.totalMemory() - runtime.freeMemory();
		for (int i = 0; i < SIZE; i++) {
			map.put(i, i);
		}
		after = (runtime.totalMemory() - runtime.freeMemory()) - before;
		System.out.println("my :" + after/SIZE);
		
		runtime.gc();
		before = runtime.totalMemory() - runtime.freeMemory();
		for (int i = 0; i < SIZE; i++) {
			utilMap.put(i, i);
		}
		after = (runtime.totalMemory() - runtime.freeMemory()) - before;
		System.out.println("ut :" + after/SIZE);
		
		
	}

}
