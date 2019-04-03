package edu.uprm.ece.icom4035.list;

import java.util.Iterator;
import java.util.NoSuchElementException;



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
		// TODO Not implemented.
		return false;
	}

	@Override
	public int size() {
		// TODO Not Implemented.
		return currentSize;
	}

	@Override
	public boolean remove(E obj) {
		// TODO Not implemented.
		return false;
	}

	@Override
	public boolean remove(int index) {
		// TODO Not implemented.
		return false;
	}

	@Override
	public int removeAll(E obj) {
		// TODO Not implemented.
		return 0;
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
		// TODO Not Implemented.
		return null;
	}

	@Override
	public void clear() {
		// TODO Not implemented.
		
	}

	@Override
	public boolean contains(E e) {
		// TODO Not implemented.
		return false;
	}

	@Override
	public int firstIndex(E e) {
		// TODO Not implemented.
		return 0;
	}

	@Override
	public int lastIndex(E e) {
		// TODO Not implemented.
		return 0;
	}
	
	@Override
	public boolean isEmpty() {
		return currentSize ==0;
	}
	//-------------------------ITERATOR METHODS------------------------------
	@Override
	public Iterator<E> iterator(int index) {
		// TODO Not implemented.
		return null;
	}
	
	@Override
	public Iterator<E> iterator() {
		// TODO Not Implemented.
		return null;
	}
	
	@Override
	public ReverseIterator<E> reverseIterator() {
		// TODO Not Implemented.
		return null;
	}

	@Override
	public ReverseIterator<E> reverseIterator(int index) {
		// TODO Not implemented.
		return null;
	}
	//-----------------------------------------------------------------------
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
	
}
