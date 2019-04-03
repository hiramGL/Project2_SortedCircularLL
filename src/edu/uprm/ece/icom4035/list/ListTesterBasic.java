package edu.uprm.ece.icom4035.list;

import java.util.Iterator;

public class ListTesterBasic {
	public static void main(String[] args) {
		SortedList<String> list = new SortedCircularDoublyLinkedList<String>();
		list.add("a2");
		list.add("b2");
		list.add("a");
		list.add("aa");
		list.add("a1");
		list.add("a3");
		printList("List is " , list);
	
	}
	
	public static void printList(String msg,SortedList<String> l) {
		System.out.println(msg);
		for(String s: l) {
			System.out.println(s);
		}
		System.out.println("size is: " + l.size());
	}
	
	public static void printReverseList(String msg, SortedList<String> l) {
		System.out.println(msg);
		ReverseIterator<String> iter = l.reverseIterator();
		while(iter.hasPrevious()) {
			System.out.println(iter.previous());
		}
	}
	 
}
