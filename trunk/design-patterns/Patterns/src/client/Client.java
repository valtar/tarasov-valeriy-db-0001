package client;

import server.CacheListener;
import cache.Cache;
import cache.CacheImpl;

public class Client<K,V> implements CacheListener<K, V> {
	private Cache<K,V> cache = new CacheImpl<>();

	@Override
	public void cacheChanged(K key, V value){
		synchronized(cache){
			cache.put(key, value);
		}
	}
	
	public V get(K key){
		synchronized(cache){
			return cache.get(key);
		}
	}

}
