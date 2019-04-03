package edu.uprm.ece.icom4035.list;

/**
 * This interface specifies the methods for an iterator that traverses a doubly linked list in the reverse direction.
 *
 * @param <E> - the type of data stored in the list.
 */
public interface ReverseIterator<E> {
	
	/**
	 * This methods is used to verify if the list has another element previous to the one we are currently located.
	 * @return true if there is an element previous to the one we are currently located, or false otherwise.
	 */
	public boolean hasPrevious();
	
	/**
	 * This method moves the iterator to the element previous to the one we are currently located and returns it. 
	 * @return the element previous to the one we are currently located.
	 * @throws NoSuchElementException if there is no previous element to the one we are currently located.
	 */
	public E previous();

}
