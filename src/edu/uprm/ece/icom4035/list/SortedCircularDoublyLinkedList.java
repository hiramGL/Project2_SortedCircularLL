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
 * @param <E> I assume that element of type E is a comparable object.
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
		header = createNode(null,null,null);
		header.setNext(header);
		header.setPrev(header);
	}
	//--------------------------------------------------------------------------
	
	@Override
	public boolean add(E obj) {
		if(isEmpty()) {
			addBetween(obj,header,header.getNext());
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
		Node<E> ntr = getNode(obj);
		if(ntr != null) {
			remove(ntr);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean remove(int index) {
		
		if(index < 0 || index >= size())
			throw new IndexOutOfBoundsException("index out of bounds");
		if(isEmpty())
			return false;
		Node<E> ntr = getNode(index);
		remove(ntr);
		return true;
	}

	@Override
	public int removeAll(E obj) {
		int occurrences = 0;
		Node<E> ntr = getNode(obj);
		while(ntr != null) {
			remove(ntr);
			occurrences++;
			ntr = getNode(obj);
		}
		return occurrences;
	}

	@Override
	public E first() {
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
		return getNode(index).getData();
	}

	@Override
	public void clear() {
		ForwardNodeIterator iter = new ForwardNodeIterator();
		while(iter.hasNext()) {
			iter.next();
			iter.remove();
		}
	}

	@Override
	public boolean contains(E e) {
		Node<E> node = getNode(e);
		if(node!= null)
			return true;
		return false;
	}

	@Override
	public int firstIndex(E e) {
		return getFirstIndexOfNode(e);
	}

	@Override
	public int lastIndex(E e) {
		return getLastIndexOfNode(e);
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
		return new ForwardIterator(index);
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
		return new ReverseElementIterator(index);
	}
	//***********************IMPLEMENTED ITERATOR CLASSES********************************
	/**
	 * Forward Iterator of Nodes in the Linked List.
	 * @author Hiram Garcia Lopez
	 *
	 */
	private class ForwardNodeIterator implements Iterator<Node<E>>{
		private Node<E> cursor = header.getNext();
		private Node<E> recent = null;	//recent: node of last reported element.
		private Node<E> first = header;
		private int lindex = 0;
		private int index;
		public ForwardNodeIterator() {
			index =0;
			lindex = size();
		}
		public ForwardNodeIterator(int i) {
			index =i;
			prepareIter();
		}
		@Override
		public boolean hasNext() {
			return cursor != first || lindex < size() ;
		}
		@Override
		public Node<E> next() {
			if(!hasNext())
				throw new NoSuchElementException("no more element");
			recent = cursor;
			cursor = cursor.getNext();
			if(cursor == header && lindex < size())
				cursor = cursor.getNext();
			lindex++;
			return recent;
		}
		@Override
		public void remove() {
			if(recent == null)
				throw new IllegalStateException(" nothing to remove");
			SortedCircularDoublyLinkedList.this.remove(recent.getData());
			recent = null;
		}
		
		private void  prepareIter() {
			int counter = 0;
			while(counter< index){
				cursor = cursor.getNext();
				counter++;
				
			}
			first = cursor;
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
		int index;
		public ForwardIterator() {
			fwdi =  new ForwardNodeIterator();
		}
		public ForwardIterator(int i) {
			fwdi =  new ForwardNodeIterator(i);
		}
		@Override
		public boolean hasNext() {
			return fwdi.hasNext();
		}
		@Override
		public E next() throws NoSuchElementException {
			return fwdi.next().getData();
		}
		
		public void remove() {
			fwdi.remove();
		}
	
	}
	
	/**
	 * Reverse Iterator of Nodes starting from last position in linked list.
	 * @author Hiram Garcia Lopez
	 *
	 */
	private class ReverseNodeIterator implements ReverseIterator<Node<E>>{
		private Node<E> cursor = header.getPrev();
		private Node<E> recent = null;
		private int  index;
		public ReverseNodeIterator() {
			index = 0;
		}
		public ReverseNodeIterator(int i) {
			index = i;
			prepareIter();
		}
		@Override
		public boolean hasPrevious() {
			return cursor != header;
		}
	
		@Override
		public Node<E> previous() {
			if(!hasPrevious())
				throw new NoSuchElementException("No more elements");
			recent = cursor;
			cursor = cursor.getPrev();
			return recent;
		}
		private void prepareIter() {
			int counter = size() - 1;
			while(counter > index) {
				cursor = cursor.getPrev();
				counter--;
		}
		}
	}
	/**
	 * Reverse Iterator of elements. 
	 * It uses the reverse iterator of nodes to access the data of each node.
	 * @author Hiram Garcia Lopez 
	 *
	 */
	private class ReverseElementIterator implements ReverseIterator<E>{
		ReverseNodeIterator riter;
		public ReverseElementIterator() {
			riter = new ReverseNodeIterator();
		}
		public ReverseElementIterator(int index) {
			riter = new ReverseNodeIterator(index);
		}
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
	 * @return new Instance of a node. 
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
	private void addBetween(E e, Node<E> before,Node<E> after) {
		Node<E> newNode = createNode(after,before,e);
		before.setNext(newNode);
		after.setPrev(newNode);
		currentSize++;
	}
	/**
	 * Removes the node given.
	 * @param node node to remove.
	 * @return element that was erased.
	 */
	private void remove(Node<E> node) {
		Node<E> prev = node.getPrev();
		Node<E> after = node.getNext();
		prev.setNext(after);
		after.setPrev(prev);
		currentSize--;
	}
	/**
	 * Get the first index of the node with obj as data.
	 * @param obj 
	 * @return index of obj or -1 if not found.
	 */
	private int getFirstIndexOfNode(E obj) {
		int index = 0;
		Node<E> ntf = header.getNext();
		while(ntf!= header) {
			if(ntf.getData().compareTo(obj) == 0)
				return index;
			ntf = ntf.getNext();
			index++;
		}
		return -1;
	}
	/**
	 * Get the last index of the node with obj as data.
	 * @param obj
	 * @return index of obj or -1 if not found.
	 */
	private int getLastIndexOfNode(E obj) {
		
		int index = size()-1;
		Node<E> ntf = header.getPrev();
		while(ntf != header){
			if(ntf.getData().compareTo(obj)== 0)
				return index;
			index--;
			ntf = ntf.getPrev();
		}
		return -1;
	}
	/**
	 * Returns the first node whose data is obj. 
	 * @param obj
	 * @return first node of obj, or null if not found.
	 */
	private Node<E> getNode(E obj){
		Node<E> ntr = header.getNext();
		while(ntr!= header) {
			if(ntr.getData().compareTo(obj) == 0)
				return ntr;
			ntr = ntr.getNext();
		}
		return null;
	}
	/**
	 * Get the node at the index given.
	 * @param index index of node to get.
	 * @return node.
	 */
	private Node<E> getNode(int index){
		int counter = 0;
		Node<E> ntr = header.getNext();
		while(counter < index) {
			ntr = ntr.getNext();
			counter++;
		}
		return ntr;
	}
}
