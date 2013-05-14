package cash;

public interface Cachable<K,V> {

	V get(K key);
	
	void put(K key, V val);
	
}
