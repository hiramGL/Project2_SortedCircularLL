package edu.uprm.ece.icom4035.list;

import java.util.Iterator;

public interface SortedList<E extends Comparable<E>> extends Iterable<E>{
	// valid position in the list
	// [0, size() - 1]
	/**Adds a new element to the list in the right order The method traverses the list, 
	 * looking for the right position for the object.
	 */
	public boolean add(E obj);
	
	/**Returns the number of elements in the list.*/
	public int size();
	
	/** Remove the first occurrence of the object from the list. Returns true if erased,
	 * or false otherwise.
	 */
	public boolean remove(E obj);
	
	/**Removes the element at position index. Returns true if the element is erased,
	 * or an IndexOutOfBoundsException if index is illegal.
	 */
	public boolean remove(int index);
	
	/**Removes all copies of the object, and returns the number of copies erased.*/
	public int removeAll(E obj);
	
	/** Returns the first(smallest) element in the list, or null if the list is empty.*/
	public E first();
	
	/**Returns the last(largest) number in the list, or null if the list is empty.*/
	public E last();
	
	/**Returns the element at position index, or an IndexOutOfBoundsException if index is illegal.*/
	public E get(int index);
	
	/** Removes all elements in the list.*/
	public void clear();
	
	/**Returns true if the element e is in the list or false otherwise.*/
	public boolean contains(E e);
	
	/**Returns true if the list is empty, false otherwise.*/
	public boolean isEmpty();
	
	/**Returns a forward iterator from position index, or an IndexOutOfBoundsException if index is illegal.*/
	public Iterator<E> iterator(int index);
	
	/**Returns the index(position) of the first position of element e in the list 
	 * or -1 if the element is not present.
	 */
	public int firstIndex(E e);
	
	/**Returns the index(position) of the last position of element e in the list
	 * or -1 if the element is not present
	 */
	public int lastIndex(E e);
	
	/**Returns a reverse iterator,starting from the last element in the list.*/
	public ReverseIterator<E> reverseIterator();
	
	/**Returns a reverse iterator, starting from position index in the list,
	 *  or an IndexOutOfBoundsException if index is illegal.
	 */
	public ReverseIterator<E> reverseIterator(int index);
	
}
