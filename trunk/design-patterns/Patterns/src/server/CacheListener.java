package server;

public interface CacheListener<K, V> {
	void cacheChanged(K key, V value);
}
