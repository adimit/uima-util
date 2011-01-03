package uima.util.list;

import java.util.Collection;
import java.util.Iterator;

import org.apache.uima.cas.CAS;

import org.apache.uima.jcas.cas.EmptyFSList;
import org.apache.uima.jcas.cas.FSList;
import org.apache.uima.jcas.cas.NonEmptyFSList;
import org.apache.uima.jcas.cas.TOP;

import org.apache.uima.jcas.JCas;

public class FSListCollection<T extends TOP> implements Collection<T> {

	private final JCas cas;

	public FSListCollection(JCas cas) {
		this.cas = cas;
		this.head = new EmptyFSList(this.cas);
		this.tail = this.head;
	}

	public FSListCollection(final FSList head) {
		final CAS someCas = head.getCAS();
		if (someCas instanceof JCas) {
			this.cas = (JCas)someCas;
		} else {
			throw new RuntimeException
				("Our CAS isn't a JCas. That's not yet implemented.");
		}
	}

	private FSList head; 
	private FSList tail; 
	private int size = 0;

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
		return size == 0;
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
		//return new UimaListIterator<T>(this.head);
		return null;
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
	 * Prepends an object to the list.
	 *
	 * <code>l.add(x)</code> equates to <code>x:l</code>.
	 *
	 * {@inheritDoc}
	 * @see Collection#add(E)
	 */
	public boolean add(T e) {
		final NonEmptyFSList h = new NonEmptyFSList(cas);
		h.setTail(this.head);
		this.size++;
		return true;
	}

	/**
	 * Appends a single element to the end of the list.
	 *
	 * @param a The element to be appended.
	 */
	public void append(T e) {

	}

	/**
	 * Appends a list of elements of the same base type to the end of the list.
	 *
	 * @param l The list of elements you want to append. Note that the checks
	 * that determine whether this really is a list of the same type of
	 * feature structures as the base list are performed at runtime. This may
	 * lead to runtime errors, so be careful.
	 *
	 * Since FSLists are not (yet?) parametrized over their enclosing type,
	 * there's no other way to implement this (that I see.)
	 */
	public void append(FSList l) {

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
	 * Prepends the given elemets to the FSList.
	 *
	 * {@inheritDoc}
	 * @see Collection#addAll(Collection<E>)
	 */
	public boolean addAll(Collection<? extends T> c) {
		return false;
	}

	/** 
	 * Appends all items in the collection to the underlying list.
	 */
	public void appendAll(Collection<? extends T> c) {

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
	 * @see Object#toString()
	 */
	public String toString() {
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @see Collection#hashCode()
	 */
	public int hashCode() {
		return 1;
	} 

}
