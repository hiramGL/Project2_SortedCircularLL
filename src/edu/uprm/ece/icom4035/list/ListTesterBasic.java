package edu.uprm.ece.icom4035.list;

import java.util.Iterator;
/**
 * Tester class for testing the SortedCircularDoublyLinkedList class.
 * @author Hiram Garcia Lopez 
 *
 */
public class ListTesterBasic {
	public static void main(String[] args) {
		SortedList<String> list = new SortedCircularDoublyLinkedList<String>();
		list.add("a1");
		list.add("b1");
		list.add("c1");
		list.add("d1");
		list.add("a2");
		list.add("b2");
		list.add("c2");
		list.add("d2");
		printList("Initial list is: ", list);
		
		list.add("a0");
		list.add("b0");
		list.add("c0");
		list.add("d0");
		printList("Testing the add method",list);
		
		list.remove("d2");
		list.remove("c2");
		list.remove("b2");
		list.remove("a2");
		printList("Testing the remove(obj)",list);
		
		list.remove(0);
		list.remove(0);
		printList("Testing the remove(index)",list);
		
		list.add("b1");
		list.add("c1");
		list.add("d1");
		list.removeAll("b1");
		list.removeAll("c1");
		list.removeAll("d1");
		printList("Testing remove(all)",list);
	
		System.out.println("Testing the get(0) element is supposed to be b0:\n" +list.get(0));
		
		System.out.println("Testing the contains(e), list contains c0?\n" + list.contains("c0"));
		printList("List is:",list);
		
		list.add("b0");
		System.out.println("Testing the first index of b0:" + list.firstIndex("b0"));
		System.out.println("Testing the last index of b0:" + list.lastIndex("b0"));
		
		list.clear();
		printList("Testing clear method:\nList after clear is: ",list);
	}
	
	public static void printList(String msg,SortedList<String> l) {
		System.out.println(msg);
		for(String s: l) {
			System.out.println(s);
		}
		System.out.println("size is: " + l.size());
		System.out.println("First is " + l.first());
		System.out.println("Last is "+ l.last());
	}
	public static void printForwardIterator(String msg, SortedList<String> l) {
		System.out.println(msg);
		Iterator<String> fwdi = l.iterator(2);
		while(fwdi.hasNext())
			System.out.println(fwdi.next());
	}
	public static void printReverseList(String msg, SortedList<String> l) {
		System.out.println(msg);
		ReverseIterator<String> iter = l.reverseIterator(1);
		while(iter.hasPrevious()) {
			System.out.println(iter.previous());
		}
	}
	 
}
