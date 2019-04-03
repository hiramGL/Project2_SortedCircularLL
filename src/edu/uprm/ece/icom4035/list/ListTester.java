package edu.uprm.ece.icom4035.list;

public class ListTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SortedList<String> theList = new SortedCircularDoublyLinkedList<String>();
		System.out.println("Empty List: " + theList.isEmpty());
		theList.add("Bob");
		theList.add("Ron");
		printList(theList);
		theList.add("Jil");
		System.out.println("Element 0 is Bob: " + theList.get(0).equals("Bob"));
		System.out.println("Element 1 is Jil: " + theList.get(1).equals("Jil"));
		theList.add("Amy");
		System.out.println("First element is Amy: " + theList.first().equals("Amy"));
		theList.add("Ned");
		System.out.println("Last element is Ned: " + theList.last().equals("Ned"));  // must be false
		System.out.println("Last element is Ron: " + theList.last().equals("Ron"));
		theList.add("Cal");
		printReverseList(theList);
		System.out.println("Remove element at position 1: " + theList.remove(1));
		printReverseList(theList);
		System.out.println("Remove last elements: " + theList.remove("Ron"));
		printList(theList);
		System.out.println();
		printReverseList(theList);

	}

	private static void printList(SortedList<String> theList) {
		for (String s: theList){
			System.out.println(s);
		}
	}

	private static void printReverseList(SortedList<String> theList) {
		for (ReverseIterator<String> iter = theList.reverseIterator(); iter.hasPrevious(); ){
			System.out.println(iter.previous());
		}
	}

}
