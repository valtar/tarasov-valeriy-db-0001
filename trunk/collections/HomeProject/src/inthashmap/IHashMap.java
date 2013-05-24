package inthashmap;

public interface IHashMap {
	
	public boolean contains(int key);
	
	public int get(int key) throws NoSuchKeyException;
	
	public boolean remove(int key);

	public int put(int key, int value);
	
}
