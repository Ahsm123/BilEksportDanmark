package model.exceptions;

public class EmptyOrderException extends RuntimeException {
	private static final long serialVersionUID = 2L;

	public EmptyOrderException(String message) {
		super(message);
	}
}