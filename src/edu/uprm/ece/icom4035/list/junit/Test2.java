package edu.uprm.ece.icom4035.list.junit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.uprm.ece.icom4035.list.SortedCircularDoublyLinkedList;

public class Test2 {

	private SortedCircularDoublyLinkedList<String> L;

	@Before
	public void setUp() throws Exception {
		L = new SortedCircularDoublyLinkedList<String>();
		L.add("Zen");
		L.add("Jose");
		L.add("Don");
		L.add("Cal");
		L.add("Al");


	}

	@Test
	public void testOne() {
		assertTrue(L.first().equals("Al"));
		assertTrue(L.last().equals("Zen"));

	}
	
	@Test
	public void testTwo() {
		L.add("Zoe");
		assertTrue(L.last().equals("Zoe"));
		assertTrue(L.get(L.size() -2).equals("Zen"));

	}
	@Test
	public void testThree() {
	
		L.add("Zoe");
		assertTrue(L.last().equals("Zoe"));
		L.remove("Zoe");
		assertTrue(L.firstIndex("Zoe") < 0);
		L.add("Zoe");
		assertTrue(L.size() == 6);
		assertTrue(L.firstIndex("Zoe") >= 0);

	}
	@Test
	public void testFour() {
	
		L.add("Zoe");
		L.add("Zoe");
		L.add("Zoe");

		assertTrue(L.last().equals("Zoe"));
		L.remove("Zoe");
		assertTrue(L.last().equals("Zoe"));
		L.add("Zoe");
		assertTrue(L.firstIndex("Zoe") == 5);


	}
	@Test
	public void testFive() {
	
		L.add("Zoe");
		L.add("Zoe");
		L.add("Zoe");

		assertTrue(L.last().equals("Zoe"));
		L.remove("Zoe");
		assertTrue(L.last().equals("Zoe"));
		L.add("Zoe");
		assertTrue(L.firstIndex("Zoe") == 5);
		L.removeAll("Zoe");
		assertTrue(L.firstIndex("Zoe") < 0);


	}

}
