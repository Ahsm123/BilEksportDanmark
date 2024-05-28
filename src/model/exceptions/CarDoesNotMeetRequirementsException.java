package model.exceptions;

public class CarDoesNotMeetRequirementsException extends Exception {
	private static final long serialVersionUID = 1L;

	public CarDoesNotMeetRequirementsException(String message) {
		super(message);

	}
}
