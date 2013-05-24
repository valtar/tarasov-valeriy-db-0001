package inthashmap;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class HashMapTest {
	private static final int VALUE = 2;
	private static final int KEY = 1;
	IHashMap map;
	
	@Before
	public void setUp(){
		map = new HashMap();
	}
	
	@Test
	public void contains_shouldRetrunTrue_WhenPreviouslyPutAtSameKey() {
		map.put(KEY, VALUE);
		
		assertTrue(map.contains(KEY));
	}

	@Test
	public void contains_shouldRetrunFalse_WhenPreviouslyPutAtDifferentKey() {
		int key2 = KEY + 1;
		
		map.put(KEY, VALUE);
		
		assertFalse(map.contains(key2));
	}
	
	@Test
	public void put_shouldRewrite_WhenSameKeyPutted() throws NoSuchKeyException{
		int value2 = VALUE + 1;
		
		map.put(KEY, VALUE);
		map.put(KEY, value2);
		
		assertEquals(value2, map.get(KEY));
	}

	@Test
	public void put_shouldContainsKey_WhenMoreThanInitCapacityAdded(){
		for (int i = 0; i < 10000; i++) {
			map.put(i, i);
		}
		
		assertTrue(map.contains(990));
	}

	@Test
	public void revome_shouldReturnTrue_whenRemovePuttedElement(){
		map.put(KEY, VALUE);
		
		assertTrue(map.remove(KEY));
	}

	@Test
	public void revome_shouldReturnFalse_whenRemoveNotPuttedElement(){
		assertFalse(map.remove(KEY));
	}
	
	@Test(expected=NoSuchKeyException.class)
	public void get_shouldThrowExceptionWhenNotPuttedElementGets() throws NoSuchKeyException{
		map.put(KEY + 1, VALUE);
		
		map.get(KEY);
	}

}
