package edu.uprm.ece.icom4035.list;

public class ListTesterBasic {
	public static void main(String[] args) {
		SortedList<String> list = new SortedCircularDoublyLinkedList<String>();
		list.add("apex");
		list.add("black ops 4");
		list.add("cod mw2");
		list.add("destiny 2");
		list.add("warframe");
		list.add("grand theft auto");
		list.add("ghost recon");
		list.add("rainbow six siege");
		list.add("battlefield4");
		list.add("aaron");
		list.add("cable");
		list.add("aaron");
		list.add("cable");
		printList("list is: ",list);
		System.out.println(list.firstIndex("aaron"));
		System.out.println(list.lastIndex("aaron"));
		
	}
	
	public static void printList(String msg,SortedList<String> l) {
		System.out.println(msg);
		for(String s: l) {
			System.out.println(s);
		}
		System.out.println("size is: " + l.size());
	}
	
}
