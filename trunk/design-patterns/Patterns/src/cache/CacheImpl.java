package cache;

import java.util.*;

public class CacheImpl<K, V> implements Cache<K, V> {

	Map<K, V> cache = new HashMap<>();

	@Override
	public V get(K key) {
		return cache.get(key);
	}

	@Override
	public V put(K key, V value) {
		return cache.put(key,value);
	}


}
