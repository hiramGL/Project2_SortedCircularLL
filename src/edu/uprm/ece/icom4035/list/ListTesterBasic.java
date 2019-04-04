package edu.uprm.ece.icom4035.list;

import java.util.Iterator;

public class ListTesterBasic {
	public static void main(String[] args) {
		SortedList<String> list = new SortedCircularDoublyLinkedList<String>();
		
		printList("List is " , list);

	}
	
	public static void printList(String msg,SortedList<String> l) {
		System.out.println(msg);
		for(String s: l) {
			System.out.println(s);
		}
		System.out.println("size is: " + l.size());
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
