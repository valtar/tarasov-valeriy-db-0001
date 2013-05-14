package cash;

public class SychronizedCache<K,V> implements Cachable<K, V> {
		private K key;
		private V value;
		
		@Override
		public synchronized V get(K anotherKey) {
			if(this.key == null){
				return null;
			}
			
			if(!this.key.equals(anotherKey)){
				return null;
			}
			
			return value;
		}

		@Override
		public synchronized void put(K key, V value) {
			this.key = key;
			this.value = value;
		}
		 
		public boolean equalsKeyValue(){
			return key.equals(value);
		}



}
