package uitox.eric.ch10;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValueContainer {
	private final String value;
	private static final Logger logger = LoggerFactory
			.getLogger(ValueContainer.class);

	public ValueContainer(final String value) {
		this.value = value;
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		logger.info("Finalizing for {}", toString());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}

		ValueContainer valueContainer = (ValueContainer) obj;
		if (!this.value.equals(valueContainer.value)) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
}
