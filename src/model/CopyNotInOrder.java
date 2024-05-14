package model;

public class CopyNotInOrder extends RuntimeException{
	private static final long serialVersionUID = 2L;

	public CopyNotInOrder(String message) {
		super(message);
	}
}