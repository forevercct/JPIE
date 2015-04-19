package uitox.eric.ch7;

import static org.junit.Assert.*;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LinkedList<T> {
	private T element;
	private LinkedList<T> next;

	private static final Logger logger = LoggerFactory
			.getLogger(LinkedList.class);

	public static void main(String[] args) {

//		final LinkedList<String> three = new LinkedList<String>("3", null);
//		final LinkedList<String> two = new LinkedList<String>("2", three);
//		final LinkedList<String> one = new LinkedList<String>("1", two);
//
//		final LinkedList<String> reversed = LinkedList.rverse(one);
//		logger.info("The result of checked functions: {}",
//				reversed.getNext().getElement());

	}

	public LinkedList(T element, LinkedList<T> next) {
		this.element = element;
		this.next = next;
	}

	public T getElement() {
		return element;
	}

	public LinkedList<T> getNext() {
		return next;
	}

	public static <T> LinkedList<T> rverse(final LinkedList<T> original) {
		if (original == null) {
			throw new NullPointerException("Cannot reverse a null list");
		}

		if (original.getNext() == null) {
			return original;
		}

		final LinkedList<T> next = original.next;
		original.next = null;

		final LinkedList<T> otherReversed = rverse(next);

		next.next = original;

		return otherReversed;
	}
	
	@Test
	public void reverseLinkedList() {
		final LinkedList<String> three = new LinkedList<String>("3", null);
		final LinkedList<String> two = new LinkedList<String>("2", three);
		final LinkedList<String> one = new LinkedList<String>("1", two);
		final LinkedList<String> reversed = LinkedList.rverse(one);
		
		assertEquals("3", reversed.getElement());
		assertEquals("2", reversed.getNext().getElement());
		assertEquals("1", reversed.getNext().getNext().getElement());
	}
}
