package complaintsapi.utilities;

import complaintsapi.model.Complaint;

public class ComplaintUtilities {
	
	public boolean validComplaint(Complaint complaint) {
		
		boolean isValid = true;
		
		if(complaint.getTitle() == null || complaint.getTitle().isEmpty()) {
			isValid = false;			
		}
		
		if(complaint.getDescription() == null || complaint.getDescription().isEmpty()) {
			isValid = false;			
		}
		
		if(complaint.getLocale() == null || complaint.getLocale().isEmpty()) {
			isValid = false;			
		}
		
		if(complaint.getCompany() == null || complaint.getCompany().isEmpty()) {
			isValid = false;			
		}
		
		return isValid;
		
	}

}

