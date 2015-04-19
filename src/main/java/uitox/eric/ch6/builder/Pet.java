package uitox.eric.ch6.builder;

import java.util.Date;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Pet {
	private final Animal animal;
	private final String petName;
	private final String ownerName;
	private final String address;
	private final String telephone;
	private final Date dateOfBirth; // optional
	private final String emailAddress; // optional

	private static final Logger logger = LoggerFactory.getLogger(Pet.class);

	private Pet(final Animal animal, final String petName, String ownerName,
			String address, String telephone, Date dateOfBirth,
			String emailAddress) {
		this.animal = animal;
		this.petName = petName;
		this.ownerName = ownerName;
		this.address = address;
		this.telephone = telephone;
		this.dateOfBirth = dateOfBirth;
		this.emailAddress = emailAddress;
	}

	public static void main(String[] args) {
		legalBuild();
//		illegalBuild();
	}

	public static class Builder {
		private Animal animal;
		private String petName;
		private String ownerName;
		private String address;
		private String telephone;
		private Date dateOfBirth; // optional
		private String emailAddress; // optional

		public Builder withAnimal(final Animal animal) {
			this.animal = animal;
			return this;
		}

		public Builder withPetName(final String petName) {
			this.petName = petName;
			return this;
		}

		public Builder withOwnerName(final String ownerName) {
			this.ownerName = ownerName;
			return this;
		}

		public Builder withAddress(final String address) {
			this.address = address;
			return this;
		}

		public Builder withTelephone(final String telephone) {
			this.telephone = telephone;
			return this;
		}

		public Builder withDateOfBirth(final Date dateOfBirth) {
			this.dateOfBirth = dateOfBirth;
			return this;
		}

		public Builder withEmailAddress(final String emailAddress) {
			this.emailAddress = emailAddress;
			return this;
		}

		public Pet build() {
			if (petName == null || ownerName == null || address == null
					|| telephone == null) {
				throw new IllegalStateException("Cannot create Pet");
			}

			return new Pet(animal, petName, ownerName, address, telephone,
					dateOfBirth, emailAddress);
		}

	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("kind: " + this.animal);
		sb.append(" ,petName: " + this.petName);
		sb.append(" ,ownerName: " + this.ownerName);
		sb.append(" ,address: " + this.address);
		sb.append(" ,telephone: " + this.telephone);

		return sb.toString();
	}

	@Test
	public static void legalBuild() {
		final Pet.Builder builder = new Pet.Builder();
		final Pet pet = builder.withAnimal(new Animal.CAT().build()).withPetName("Squidge")
				.withOwnerName("Simon Smith").withAddress("123 High Street")
				.withTelephone("07777777770")
				.withEmailAddress("simon@email.com").build();

		logger.info(pet.toString());
	}

	@Test(expected = IllegalStateException.class)
	public static void illegalBuild() {
		final Pet.Builder builder = new Pet.Builder();
		final Pet pet = builder.withPetName("Squidge")
				.withOwnerName("Simon Smith").build();

		// logger.info(pet.toString());
	}

}
