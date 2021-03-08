package complaintsapi.error;

public class ComplaintsAPINotFoundException extends RuntimeException{
	
	public ComplaintsAPINotFoundException (String message) {
		super(message);
	}

}
