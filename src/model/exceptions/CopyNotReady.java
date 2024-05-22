package model.exceptions;

public class CopyNotReady extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public CopyNotReady(String message) {
		super(message);
	}
}
