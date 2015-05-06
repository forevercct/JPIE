package uitox.eric.ch11;

import java.io.Serializable;

public class Message implements Serializable {

	private static final long serialVersionUID = 1L;
	private final String message;

	public Message(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
