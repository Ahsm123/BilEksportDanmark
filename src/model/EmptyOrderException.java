package model;

public class EmptyOrderException extends Exception {
	private static final long serialVersionUID = 2L;

	public EmptyOrderException(String message, Throwable e) {
		super(message, e);
	}
}
