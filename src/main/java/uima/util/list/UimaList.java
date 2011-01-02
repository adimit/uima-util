package uima.util.list;

import java.util.Collection;
import java.util.Iterator;

public class UimaList<T> implements Collection<T> {

	private T head;

	/**
	 * {@inheritDoc}
	 * @see Collection#size()
	 */
	public int size() {
		return 0;
	}

	/**
	 * {@inheritDoc}
	 * @see Collection#isEmpty()
	 */
	public boolean isEmpty() {
		return true;
	}

	/**
	 * {@inheritDoc}
	 * @see Collection#contains(Object)
	 */
	public boolean contains(Object o) {
		return false;
	}

	/**
	 * {@inheritDoc}
	 * @see Iterable#iterator()
	 */
	public Iterator<T> iterator() {
		return new UimaListIterator<T>(this.head);
	}

	/**
	 * {@inheritDoc}
	 * @see Collection#toArray()
	 */
	public Object[] toArray() {
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @see Collection#toArray(T[])
	 */
	public <E> E[] toArray(E[] a) {
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @see Collection#add(E)
	 */
	public boolean add(T e) {
		return false;
	}

	/**
	 * {@inheritDoc}
	 * @see Collection#remove(Object)
	 */
	public boolean remove(Object o) {
		return false;
	}

	/**
	 * {@inheritDoc}
	 * @see Collection#containsAll(Collection<?>)
	 */
	public boolean containsAll(Collection<?> c) {
		return false;
	}

	/**
	 * {@inheritDoc}
	 * @see Collection#addAll(Collection<E>)
	 */
	public boolean addAll(Collection<? extends T> c) {
		return false;
	}

	/**
	 * {@inheritDoc}
	 * @see Collection#removeAll(Collection<?>)
	 */
	public boolean removeAll(Collection<?> c) {
		return false;
	}

	/**
	 * {@inheritDoc}
	 * @see Collection#retainAll(Collection<?>)
	 */
	public boolean retainAll(Collection<?> c) {
		return false;
	}

	/**
	 * {@inheritDoc}
	 * @see Collection#clear()
	 */
	public void clear() {
		return;
	}

	/**
	 * {@inheritDoc}
	 * @see Collection#equals(Object)
	 */
	public boolean equals(Object o) {
		return false;
	}

	/**
	 * {@inheritDoc}
	 * @see Collection#hashCode()
	 */
	public int hashCode() {
		return 1;
	} 

}
