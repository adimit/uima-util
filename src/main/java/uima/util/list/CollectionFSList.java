package uima.util.list;

import java.util.List;

import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FeatureStructure;

import org.apache.uima.jcas.cas.EmptyFSList;
import org.apache.uima.jcas.cas.FSList;
import org.apache.uima.jcas.cas.NonEmptyFSList;
import org.apache.uima.jcas.cas.TOP;

import org.apache.uima.jcas.JCas;

/**
 * Wrapper class around UIMA's FSList that implements the {@link Collection}
 * interface.
 */
public class CollectionFSList<T extends TOP> {

	/* To avoid casting from FSList to NonEmptyFSList for even the most
	 * trivial operations, we keep {@code head} and {@code tail} as
	 * NonEmptyFSLists, with {@code null} as a sentinel value for EmptyFSList.
	 *
	 * tail points not to the EmtpyFSList at the end of the linked list, but to
	 * the NonEmptyFSList whose tail is the EmptyFSList.
	 */
	private NonEmptyFSList head, tail;
	private final JCas cas;

	/* {@code Size} is eithr a positive integer, or {@code (-1)} indicating
	 * that size computation has not yet happened. {@see #size}*/
	private int size;

	/**
	 * Construct a CollectionFSList from a {@link NonEmptyFSList}.
	 *
	 * @param l 
	 * @throws CASException
	 * @todo Maybe make {@code tail} also lazy. This would mean we'd need an
	 * accessor for {@code tail}. But maybe the whole lazyness thing will end
	 * up costing us more than we'd gain.
	 */
	public CollectionFSList(final NonEmptyFSList l) throws CASException {
		this.cas = l.getCAS().getJCas();
		this.head = l;
		this.tail = null;
		this.size = -1;
	}

	public CollectionFSList(final NonEmptyFSList l, boolean strict) throws CASException {
		this.cas = l.getCAS().getJCas();
		this.head = l;
		if (strict) { whack(); }
		else {
			this.tail = null;
			this.size = -1;
		}
	}

	/* Whacks this CFSList out of its lazy state. {@code size} and {@code tail} will be
	 * defined as a result.*/
	private void whack() {
		NonEmptyFSList p = head;
		int s = 1;
		while (p.getTail() instanceof NonEmptyFSList) {
			s++; p = (NonEmptyFSList)p.getTail();
		}
		this.size = s;
		this.tail = p;
	}

	/**
	 * Add an element to {@underline the end of the list}.
	 *
	 * {@link #append} is an alias for this. {@code O(1)}
	 *
	 * @param t The element to be added.
	 * @return Always {@code true}.
	 */
	public boolean add(T t) {
		final NonEmptyFSList tail_ = new NonEmptyFSList(this.cas);
		tail_.setHead(t);
		tail_.setTail(new EmptyFSList(this.cas));
		if (this.head == null) { // if this is the first add operation
			this.head = this.tail = tail_;
		}
		if (this.tail == null) { whack(); }
		final NonEmptyFSList head_ = new NonEmptyFSList(cas);
		head_.setHead(t);
		if (this.head == null) { // if this is the first add operation
			head_.setTail(new EmptyFSList(cas));
			this.tail = head_;
		} else {
			head_.setTail(this.head);
		}
		this.head = head_;

		return true;
	}

	/**
	 * Alias for {@link #add}.
	 *
	 * @see #add
	 */
	public boolean prepend(T t) {
		return this.add(t);
	}

	/**
	 * Return a pointer to the last element of the given fslist. O(n), obviously.
	 */
	private static NonEmptyFSList lastNonEmptyElement(final NonEmptyFSList l) {
		FSList p = l.getTail();
		while (p instanceof NonEmptyFSList) {
			p = ((NonEmptyFSList)p).getTail();
		}
		return ((NonEmptyFSList)p);
	}

	/**
	 * Retrieve the length of this {@code CollectionFSList}.
	 *
	 * Note that this class stores the list size lazily in case the
	 * CollectionFSList has been constructed from a {@code NonEmptyFSList}.
	 * In this case, the size does not get computed until {@link #size} is
	 * called. This might mean that {@code size} might take longer than
	 * constant time.
	 *
	 * @return The length of this list.
	 */
	public int size() {
		switch (size) {
			case -1: return countFSListSize(head);
			default: return size;
		}
	}

	/**
	 * Recursive method to count a {@link NonEmptyFSList}'s size.
	 *
	 * @param l
	 * @return The size of the given list {@code l}.
	 */
	private static int countFSListSize(final NonEmptyFSList l) {
		FSList p = l.getTail();
		int s = 1;
		while (p instanceof NonEmptyFSList) {
			s++; p = ((NonEmptyFSList)p).getTail();
		}
		return s;
	}

	/**
	 * Construct an empty CollectionFSList from a {@link EmptyFSList}.
	 *
	 * The {@link EmptyFSList} can be provided in order to avoid having to pass
	 * the corresponding JCas explicitly.
	 *
	 * @param l
	 * @throws CASException When the CAS to JCas conversion fails.
	 */
	public CollectionFSList(final EmptyFSList l) throws CASException {
		this.cas = l.getCAS().getJCas();
		this.tail = this.head = null;
	}

	/**
	 * Construct an empty CollectionFSList.
	 *
	 * @param cas The {@link JCas} for this FSList.
	 * @throws CASException
	 */
	public CollectionFSList(final JCas cas) {
		this.cas = cas;
		this.tail = this.head = null;
	}

	public FSList toFSList() {
		if (head == null) { return new EmptyFSList(cas); }
		else return head;
	}
}

