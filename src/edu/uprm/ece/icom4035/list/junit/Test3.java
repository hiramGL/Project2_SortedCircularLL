package edu.uprm.ece.icom4035.list.junit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.uprm.ece.icom4035.list.SortedCircularDoublyLinkedList;

public class Test3 {

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
		L.clear();
		assertTrue(L.size() == 0);
	}

	@Test
	public void testTwo() {
		L.clear();
		assertTrue(L.size() == 0);
		L.add("Joe");
		assertTrue(L.size() == 1);
	}

	@Test
	public void testThree() {
		L.clear();
		assertTrue(L.size() == 0);
		L.add("Joe");
		assertTrue(L.size() == 1);
		L.remove(0);
		assertTrue(L.size() == 0);
	}

	@Test
	public void testFour() {
		L.clear();
		assertTrue(L.size() == 0);
		L.add("Joe");
		assertTrue(L.size() == 1);
		L.add("Joe");
		assertTrue(L.firstIndex("Joe") == 0);
		assertTrue(L.lastIndex("Joe") == 1);
	}

	@Test
	public void testFive() {

		assertTrue(L.firstIndex("Pol") < 0);
		assertTrue(L.lastIndex("Pol") < 0);
	}

}
