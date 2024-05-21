package model.exceptions;

public class CopyNotInOrderException extends RuntimeException{
	private static final long serialVersionUID = 2L;

	public CopyNotInOrderException(String message) {
		super(message);
	}
}