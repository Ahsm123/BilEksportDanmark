package model.exceptions;


public class CopyNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 2L;

	public CopyNotFoundException(String message) {
		super(message);
	}
}