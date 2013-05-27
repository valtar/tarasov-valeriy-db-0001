
package cash;

import cash.interfaces.Cachable;

public class ImmutableCache<K,V> implements Cachable<K, V>{
	private volatile CashData data;
	
	private class CashData{
		final private K key;
		final private V value;

		public K getKey() {
			return key;
		}
		
		public V getValue() {
			return value;
		}
		
		
		public CashData(K key, V value) {
			super();
			this.key = key;
			this.value = value;
		}
		
	}
	
	
	@Override
	public V get(K anotherKey) {
		CashData data = this.data;
		
		if(data == null){
			return null;
		}
		
		if(!data.getKey().equals(anotherKey)){
			return null;
		}
		
		return data.getValue();
	}

	@Override
	public void put(K key, V value) {
		data = new CashData(key,value);
	}
	
}

