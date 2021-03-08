package complaintsapi.error;

public class ComplaintsAPIBadRequestException extends RuntimeException{
	
	public ComplaintsAPIBadRequestException (String message) {
		super(message);
	}

}
