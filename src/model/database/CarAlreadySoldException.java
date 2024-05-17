package model.database;

public class CarAlreadySoldException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public CarAlreadySoldException(String message) {
		super(message);
	}
}
