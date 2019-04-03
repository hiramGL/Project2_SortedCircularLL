package edu.uprm.ece.icom4035.list;

import java.util.Iterator;

public class ListTesterBasic {
	public static void main(String[] args) {
		SortedList<String> list = new SortedCircularDoublyLinkedList<String>();
		list.add("a0");
		list.add("b1");
		list.add("c2");
		list.add("d3");
		list.add("e4");
		list.add("f5");
		list.add("g6");
		
		
	}
	
	public static void printList(String msg,SortedList<String> l) {
		System.out.println(msg);
		for(String s: l) {
			System.out.println(s);
		}
		System.out.println("size is: " + l.size());
	}
	
}
