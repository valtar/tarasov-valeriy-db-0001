package server;

import cache.CacheEntry;


public abstract class ServerConnector<K, V> implements CacheListener<K, V> {
	private long lastHeartBeat = 0L;
	private Server<K, V> server;
	
	@Override
	public void cacheChanged(K key, V value) {
		send(new CacheEntry<K, V>(key, value));
	}
	
	private void disconnect(){
		server.removeCacheLisntener(this);
		//...
	}

	public abstract void send(CacheEntry<K, V> entry);

	protected abstract void readHeartBeat();
	
	private abstract class ServerHeartBeatReader<K, V> implements Runnable{}
}
