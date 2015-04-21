package uitox.eric.ch8;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AuthorTest {
	private List<Author> authors;

	private class Author {
		private final String name;

		private Author(final String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

	@Before
	public void createAuthors() {
		authors = new ArrayList<Author>();

		authors.add(new Author("Stephen Hawking"));
		authors.add(new Author("Edgar Allan Poe"));
		authors.add(new Author("William Shakespeare"));
	}

	@Test
	public void authorListAccess() {
		final Author author = authors.get(2);
		assertEquals("William Shakespeare", author.getName());
	}

	// @Test
	// public void useStrings() {
	// authors.add("J. K. Rowling");
	// final String authorAsString = (String) authors.get(authors.size() - 1);
	// assertEquals("J. K. Rowling", authorAsString);
	// }
}
