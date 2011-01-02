package uima.util.list;

import java.util.Iterator;

public class UimaListIterator<T> implements Iterator<T> {

	public UimaListIterator(final T head) {

	}

	/**
	 * {@inheritDoc}
	 * @see Iterator#hasNext()
	 */
	public boolean hasNext() {
		return false;
	}

	/**
	 * {@inheritDoc}
	 * @see Iterator#next()
	 */
	public T next() {
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @see Iterator#remove()
	 */
	public void remove() {

	}

}
