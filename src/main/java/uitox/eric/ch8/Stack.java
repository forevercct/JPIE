package uitox.eric.ch8;

import java.util.LinkedList;
import java.util.List;

public class Stack<E> {
	private final List<E> values;

	public Stack() {
		values = new LinkedList<E>();
	}

	public void push(final E element) {
		values.add(0, element);
	}

	public E pop() {
		if (values.size() == 0)
			return null;

		return values.remove(0);
	}
	
}
