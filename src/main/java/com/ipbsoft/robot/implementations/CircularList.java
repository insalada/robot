package com.ipbsoft.robot.implementations;

/**
 * @author insalada
 * 
 * A very simple circular list implementation to get the previous and next element 
 * <p>
 * <i>It makes uses of List interface through composition (safer than inheritance for this purpose)</i>
 * 
 */
import java.util.List;

public class CircularList<E> {
	private List<E> list;

	public CircularList(List<E> list) {
		this.list = list;
	}

	/**
	 * Returns the previous element in circular. 
	 * If we reach the beginning of the list, it takes the last one.
	 * <p>In case the element does not exist, returns the last ocurrence
	 * @param element
	 * @return the previous element
	 */
	public E previous(E element) {
		int previousIndex = list.indexOf(element) - 1;
		if (previousIndex < 0)
			return list.get(list.size() - 1);
		else
			return list.get(previousIndex);
	}
	
	/**
	 * Returns the next element in circular. 
	 * If we reach the beginning of the list, it takes the last one.
	 * <p>In case the element does not exist, return the first one
	 * @param element
	 * @return
	 */
	public E next(E element) {
		int nextIndex = list.indexOf(element) + 1;
		if (nextIndex <= 0 || nextIndex >= list.size())
			return list.get(0);
		else
			return list.get(nextIndex);
	}
}
