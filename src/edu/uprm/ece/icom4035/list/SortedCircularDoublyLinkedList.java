package edu.uprm.ece.icom4035.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * Sorted circular list that use Nodes to linked between its elements. 
 * It will always be sorted and only has reference to its header Node, 
 * which its purpose is to have reference to the last and first element in list.
 * @author Hiram Garcia Lopez
 *
 * @param <E>
 */
public class SortedCircularDoublyLinkedList<E extends Comparable<E>> implements SortedList<E> {
	//--------------------------INNER CLASS NODE--------------------------------
	/**
	 * Node class for the circularDoublyLinkedList in which each node will have a reference
	 * to the next and previous nodes, and will have a data field in which it will store the element of type E
	 * @author Hiram Garcia 
	 *
	 * @param <E> generic type(must implement java interface E).
	 */
	private class Node<E>{
		Node<E> next;
		Node<E> prev;
		E data;
		
		public Node(Node<E> n, Node<E> p, E d) {
			next = n;
			prev = p;
			data = d;
		}
		
		public Node<E> getPrev(){
			return prev;
		}
		
		public Node<E> getNext(){
			return next;
		}
		public E getData() {
			return data;
		}
		public void setNext(Node<E> newnext) {
			next = newnext;
		}
		public void setPrev(Node<E> newprev) {
			prev = newprev;
		}
		
		public void clearNode() {
			next = prev = null;
			data = null;
		}
		
		public String toString() {
			String s= ""+ data;
			return s;
		}
	}//-------------------------END OF INNER CLASS NODE------------------------- 
	
	//-------------------Instance variables and constructors-------------------------------------
	Node<E> header;
	int currentSize = 0;
	public SortedCircularDoublyLinkedList() {
		header = createNode(header,header,null);
	}
	//--------------------------------------------------------------------------
	
	@Override
	public boolean add(E obj) {
		if(isEmpty()) {
			addBetween(obj,header,header);
			return true;
		}
		Node<E> current = header.getNext();
		while( current != header) {
			if(obj.compareTo(current.getData())  < 0) {
				addBetween(obj,current.getPrev(), current);
				return true;
			}
			current = current.getNext();
		}
		addBetween(obj,header.getPrev(),header);
		return true;
	}
	
	@Override
	public int size() {
		return currentSize;
	}

	@Override
	public boolean remove(E obj) {
		ForwardNodeIterator iter = new ForwardNodeIterator();
		Node<E> current;
	
		while(iter.hasNext()) {
			current = iter.next();
			if(current.getData().compareTo(obj)== 0) {
				Node<E> nbc = current.getPrev();
				nbc.setNext(current.getNext());
				current.getNext().setPrev(nbc);
				current.clearNode();
				currentSize--;
				return true;
			}
			
		}
		return false;
	}

	@Override
	public boolean remove(int index) {

		if(index < 0 || index >= size())
			throw new IndexOutOfBoundsException("index out of bounds");
		int in = 0;
		ForwardNodeIterator iter = new ForwardNodeIterator();
		Node<E> current = iter.next();
		while(in < index) {
			current = iter.next();
			in++;
		}
		Node<E> ndc = current.getPrev();
		ndc.setNext(current.getNext());
		current.getNext().setPrev(ndc);
		current.clearNode();
		currentSize--;
		
		return true;
	}

	@Override
	public int removeAll(E obj) {
		ForwardNodeIterator iter = new ForwardNodeIterator();
		Node<E> current;
		int occurences = 0;
		while(iter.hasNext()) {
			current = iter.next();
			if(current.getData().compareTo(obj)== 0) {
				Node<E> nbc = current.getPrev();
				nbc.setNext(current.getNext());
				current.getNext().setPrev(nbc);
				current.clearNode();
				currentSize--;
				occurences++;
			}
			
		}
		
		return occurences;
	}

	@Override
	public E first() {
		// TODO
		if(!isEmpty())
			return header.getNext().getData();
		return null;
	}

	@Override
	public E last() {
		if(!isEmpty())
			return header.getPrev().getData();
		return null;
	}

	@Override
	public E get(int index) {
		if(index < 0 || index >= size())
			throw new IndexOutOfBoundsException("Index is out of bounds");
		Iterator<E> iter = iterator();
		E current = iter.next();
		int ind = 0;
		while(ind < index) {
			current = iter.next();
			ind++;
		}
		return current;
	}

	@Override
	public void clear() {
		// TODO Not implemented.
		ForwardNodeIterator iter = new ForwardNodeIterator();
		while(iter.hasNext()) {
			iter.next().clearNode();
			currentSize--;
		}
		header.setNext(header);
		header.setPrev(header);
	}

	@Override
	public boolean contains(E e) {
		
		Iterator<E> iter = iterator();
		while(iter.hasNext())
			if(iter.next().compareTo(e) == 0)
				return true;
		return false;
	}

	@Override
	public int firstIndex(E e) {
		int index = 0;
		Iterator<E> iter = iterator();
		while(iter.hasNext()) {
			if(iter.next().compareTo(e)==0)
				return index;
			index++;
		}
		return -1;
	}

	@Override
	public int lastIndex(E e) {
		int index = -1;
		int currindex = 0;
		Iterator<E> iter = iterator();
		while(iter.hasNext()) {
			if(iter.next().compareTo(e) == 0)
				index = currindex;
			currindex++;
		}
		return index;
	}
	
	@Override
	public boolean isEmpty() {
		return currentSize ==0;
	}
	//-------------------------ITERATOR METHODS and Classes------------------------------
	@Override
	public Iterator<E> iterator(int index) {
		if(index < 0 || index >= size())
			throw new IndexOutOfBoundsException("index is out of bounds");
		ForwardNodeIterator niter = new ForwardNodeIterator();
		ArrayList<E> arr = new ArrayList<E>();
		while(niter.hasNext())
			arr.add(niter.next().getData());
		return  arr.listIterator(index);
	}
	
	@Override
	public Iterator<E> iterator() {
		return new ForwardIterator();
	}
	
	@Override
	public ReverseIterator<E> reverseIterator() {
		return new ReverseElementIterator();
	}

	@Override
	public ReverseIterator<E> reverseIterator(int index) {
		// TODO Not implemented.
		return null;
	}
	//***********************IMPLEMENTED ITERATOR CLASSES********************************8
	/**
	 * Forward Iterator of Nodes in the Linked List.
	 * @author Hiram Garcia Lopez
	 *
	 */
	private class ForwardNodeIterator implements Iterator<Node<E>>{
		Node<E> current = header.getNext();
		Node<E> oldcurr;	//oldcurr: node to store the current value so the current can be change
		@Override
		public boolean hasNext() {
			
			return current != header;
		}
		@Override
		public Node<E> next() {
			if(!hasNext())
				throw new NoSuchElementException("no more element");
			oldcurr = current;
			current = current.getNext();
			return oldcurr;
		}
		
	}
	/**
	 *  Forward Iterator of element in the linked list. 
	 *  It use the Forward Iterator of Nodes as an object to access the data of each nodes.
	 * @author Hiram Garcia Lopez 
	 *
	 */
	private class ForwardIterator implements Iterator<E>{
		ForwardNodeIterator fwdi = new ForwardNodeIterator();
		@Override
		public boolean hasNext() {
			return fwdi.hasNext();
		}
		@Override
		public E next() throws NoSuchElementException {
			if(!hasNext()) 
				throw new NoSuchElementException("no next element.");
			return fwdi.next().getData();
		}
		
	}
	/**
	 * Reverse Iterator of Nodes starting from last position in linked list.
	 * @author Hiram Garcia Lopez
	 *
	 */
	private class ReverseNodeIterator implements ReverseIterator<Node<E>>{
		Node<E> current = header.getPrev();
		Node<E> oldcurr;
		
		@Override
		public boolean hasPrevious() {
			
			return current != header;
		}
		
		@Override
		public Node<E> previous() {
			if(!hasPrevious())
				throw new NoSuchElementException("No more elements");
			oldcurr = current;
			current = current.getPrev();
			return oldcurr;
		}
		
	}
	/**
	 * Reverse Iterator of elements. 
	 * It uses the reverse iterator of nodes to access the data of each node.
	 * @author Hiram Garcia Lopez 
	 *
	 */
	private class ReverseElementIterator implements ReverseIterator<E>{
		ReverseNodeIterator riter = new ReverseNodeIterator();
		public boolean hasPrevious() {
			return riter.hasPrevious();
		}
		public E previous() {
			return riter.previous().getData();
		}
	}
	
	//******************************************************************************
	//--------------------------USEFUL PRIVATE METHODS-------------------------------------
	/**
	 * Returns a new Instance of a Node<E>.
	 * @param n next node.
	 * @param p previous node.
	 * @param d data. 
	 * @return new Instance.
	 */
	private Node<E> createNode(Node<E> n,Node<E> p, E d){
		return new Node<E>(n,p,d);
	}
	/**
	 * Add a new node between two given.
	 * @param e element to add in the linked list.
	 * @param predecessor node that will be before the new node
	 * @param successor node that will be after the new node.
	 */
	private void addBetween(E e, Node<E> predecessor,Node<E> successor) {
		Node<E> newNode = createNode(successor,predecessor,e);
		predecessor.setNext(newNode);
		successor.setPrev(newNode);
		currentSize++;
	}
}
