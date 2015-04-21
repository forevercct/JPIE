package uitox.eric.ch8;

public class Person {
	private final String name;
	private final int age;

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;

		if (o == null || getClass() != o.getClass())
			return false;

		Person person = (Person) o;
		if (!this.name.equals(person.name))
			return false;

		if (Integer.compare(this.age, person.age) != 0)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		return name.hashCode() * age;
	}
}
