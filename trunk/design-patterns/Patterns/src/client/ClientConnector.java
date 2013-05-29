package client;

import cache.CacheEntry;

public abstract class ClientConnector<K,V> {
	private Client<K,V> client;
	
	public ClientConnector(Client<K,V> client){
		this.client = client;
		connect();
		startReader();
	}
	
	protected abstract void startReader();

	protected abstract void connect();
	
	private abstract class ServerReader implements Runnable{

		@Override
		public void run() {
			while(true){
				CacheEntry<K,V> entry = read();
				client.cacheChanged(entry.getKey(),entry.getValue());
			}
			
		}
		
		protected abstract CacheEntry<K, V> read();
	}
	
	private abstract class ServerHeartBeatSender<K, V> implements Runnable{}
}
