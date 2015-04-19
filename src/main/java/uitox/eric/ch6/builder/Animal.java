package uitox.eric.ch6.builder;

public class Animal {
	private final String kind;

	private Animal(final String kind) {
		this.kind = kind;
	}

	public static class CAT {
		private final String kind = "cat";

		public Animal build() {
			return new Animal(kind);
		}
	}

	public String toString() {
		return "The animal is " + this.kind;
	}
}
