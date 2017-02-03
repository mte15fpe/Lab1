package set;

import java.util.NoSuchElementException;
import java.util.Iterator;

public class MaxSet<E extends Comparable<E>> extends ArraySet<E> {
	private E maxElement;

	/**
	 * Constructs a new empty set.
	 */
	public MaxSet() {
		super();
	}

	/**
	 * Returns the currently largest element in this set. pre: the set is not
	 * empty post: the set is unchanged
	 * 
	 * @return the currently largest element in this set
	 * @throws NoSuchElementException
	 *             if this set is empty
	 */
	public E getMax() {
		if (maxElement != null) {
			return maxElement;
		} else
			throw new NoSuchElementException();
	}

	/**
	 * Adds the specified element to this set, if it is not already present.
	 * post: x is added to the set if it is not already present
	 * 
	 * @param x
	 *            the element to be added
	 * @return true if the specified element was added
	 */
	public boolean add(E x) {
		if (maxElement == null) {
			maxElement = x;
		} else if (x.compareTo(maxElement) > 0) {
			maxElement = x;
		}
		return super.add(x);
	}

	/**
	 * Removes the specified element from this set if it is present. post: x is
	 * removed if it was present
	 * 
	 * @param x
	 *            the element to remove - if present
	 * @return true if the set contained the specified element
	 */
	public boolean remove(Object x) {
	
		if (maxElement == null) {
			return false;
		} else if (maxElement.equals(x)) {
			maxElement = null;
			return super.remove(x);
		} else {
			
			super.remove(x); // Kolla om du faktiskt lyckades ta bort elementet
			if (isEmpty()) { // Listan är tom?
				maxElement = null;			// Nollställ maxelement
			} else {  					// Det finns fler element
				Iterator<E> itr2 = super.iterator();	// Hitta största elementet.
				if (itr2.hasNext() == true) {
					E temp = itr2.next();
					maxElement = temp;
					System.out.println("Current max " + maxElement);
					while (itr2.hasNext()) {
						temp = itr2.next();
						if (maxElement.compareTo(temp) < 0) {
							maxElement = temp;
						}
					}
				}
			}
		}
		return true;
	}

	/**
	 * Adds all of the elements in the specified set, for which it is possible,
	 * to this set. post: all elements, for which it is possible, in the
	 * specified set are added to this set.
	 * 
	 * @return true if this set changed as a result of the call
	 */
	public boolean addAll(SimpleSet<? extends E> c) {
		if (c.isEmpty())
			return false;
		boolean hasChanged = false;
		Iterator<? extends E> itr = c.iterator();
		while (itr.hasNext()) {
			hasChanged = this.add(itr.next());
		}
		return hasChanged;
	}

}