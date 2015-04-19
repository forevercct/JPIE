package uitox.eric.ch5;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

public class SimpleTree<E extends Comparable<E>>{
	private E value;
	private SimpleTree<E> left;
	private SimpleTree<E> right;

	private static final Logger logger = LoggerFactory
			.getLogger(SimpleTree.class);

	public static void main(String[] args) {
		final SimpleTree<Integer> root = new SimpleTree<Integer>(7, null, null);
		root.insert(3);
		root.insert(9);
		root.insert(10);

		root.inOrderTraversal();
		System.out.println();

		int toFind = 10;
		logger.info("the value {} exists in this tree? {}", toFind,
				root.search(toFind));

		logger.info("the value of the right node is {}", root.getRight()
				.getValue());
		
		// unit test
		assertTrue(root.search(toFind));
		assertEquals(Integer.valueOf(10), root.getRight().getRight().getValue());
	}

	public SimpleTree(E value, SimpleTree<E> left, SimpleTree<E> right) {
		this.value = value;
		this.left = left;
		this.right = right;
	}

	public void inOrderTraversal() {
		inOrderTraversal(this);
	}

	private void inOrderTraversal(SimpleTree<E> root) {
		if (root == null)
			return;
		inOrderTraversal(root.left);
		System.out.print(root.value + " ");
		inOrderTraversal(root.right);
	}

	// inserting values into a binary tree
	public void insert(final E toInsert) {
		if (toInsert.compareTo(value) < 0) {
			if (left == null) {
				left = new SimpleTree<E>(toInsert, null, null);
			} else {
				left.insert(toInsert);
			}
		} else {
			if (right == null) {
				right = new SimpleTree<E>(toInsert, null, null);
			} else {
				right.insert(toInsert);
			}
		}
	}

	// finding values in a binary tree
	public boolean search(final E toFind) {
		if (toFind.equals(value)) {
			return true;
		}

		if (toFind.compareTo(value) < 0 && left != null) {
			return left.search(toFind);
		}

		return right != null && right.search(toFind);
	}

	public SimpleTree<E> getRight() {
		return this.right;
	}

	public E getValue() {
		return this.value;
	}

}
