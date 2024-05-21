package model.exceptions;

public class CopyAlreadyInOrderException extends RuntimeException{
	private static final long serialVersionUID = 2L;

	public CopyAlreadyInOrderException(String message) {
		super(message);
	}
}