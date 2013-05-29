package server;

import java.util.*;

import cache.Cache;
import cache.CacheImpl;

public class Server<K,V> {
	private Cache<K,V> cache = new CacheImpl<>();
	private List<CacheListener<K,V>> listeners = new ArrayList<>();
	
	private void notifyListeners(K key,V value){
		for(CacheListener<K,V> listener : listeners){
			listener.cacheChanged(key, value);
		}
	}
	
	public boolean addCacheLisntener(CacheListener<K,V> cacheListener){
		return listeners.add(cacheListener);
	}
	
	public boolean removeCacheLisntener(CacheListener<K,V> cacheListener){
		return listeners.remove(cacheListener);
	}
	
	public V put(K key, V value){
		V ans = cache.put(key, value);
		notifyListeners(key, value);
		
		return ans;
		
	}
	
}
