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
			Node<E> newnode = createNode(header,header,obj);
			header.setNext(newnode);
			header.setPrev(newnode);
			currentSize++;
			return true;
		}
		return add( obj, header.getNext());
	}
	//-----------------Private method for adding an object using recursion-----------------------------
	private boolean add(E object, Node<E> node) {
		if(node == header) {
			Node<E> newnode = createNode(header,header.getPrev(),object);
			header.getPrev().setNext(newnode);
			header.setPrev(newnode);
			currentSize++;
			return true;
		}
		if(object.compareTo(node.getData()) < 0) {
			Node<E> nta = createNode(node,node.getPrev(),object);
			node.getPrev().setNext(nta);
			node.setPrev(nta);
			currentSize++;
			return true;
		}
		
		return add(object,node.getNext());
		//------------------------------------------------------------------------------------------------
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
		// TODO Not implemented.
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
		// TODO Not implemented.
		return null;
	}
	
	@Override
	public Iterator<E> iterator() {
		// TODO Not Implemented.
		return new ForwardIterator();
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
	//-----------------Implemented Iterators Classes---------------------------
	private class ForwardNodeIterator implements Iterator<Node<E>>{
		Node<E> current = header.getNext();
		Node<E> oldcurr;
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

	//-------------------------------------------------------------------------
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
