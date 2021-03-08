package complaintsapi.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import complaintsapi.error.ComplaintsAPIBadRequestException;
import complaintsapi.error.ComplaintsAPIException;
import complaintsapi.error.ComplaintsAPINotFoundException;
import complaintsapi.model.Complaint;
import complaintsapi.repository.ComplaintsRepository;
import complaintsapi.utilities.ComplaintUtilities;

@RestController
public class ComplaintsController {
	
	@Autowired
	private ComplaintsRepository complaintsRepository;	
	
	//Endpoint to add complaints to the database
	@PostMapping(value = "/complaints", produces = "application/json")
	public ResponseEntity<Complaint> addComplaint(@RequestBody Complaint complaint){
		
		ComplaintUtilities complaintUtilities = new ComplaintUtilities();
		
		if(complaintUtilities.validComplaint(complaint) == true) {
			
			Complaint savedComplaint;
			
			try {
				//saves complaint to the db and returns status CREATED.
				savedComplaint = complaintsRepository.save(complaint);
				return new ResponseEntity<Complaint>(savedComplaint, HttpStatus.CREATED);
			}
			catch(Exception ex) {
				//throws custom exception in case an error occurs (eg. failure to communicate with db)
				throw new ComplaintsAPIException("Operation failed.");
			}
			
		}
		else
		{
			//throws custom exception in case data is invalid (eg. missing one of the fields)
			throw new ComplaintsAPIBadRequestException("Invalid data. Title, "
					+ "description, locale and company must be informed.");			
		}
	}
	
	// Endpoint to update a complaint in the database
	@PutMapping(value="/complaints", produces="application/json")
	public ResponseEntity<Complaint> updateComplaint(@RequestBody Complaint complaint){
	
		try {	
			
			//searches to find if complaint exists
			if(complaintsRepository.findById(complaint.getComplaintId()).isEmpty() == true) {				
				throw new ComplaintsAPINotFoundException("Complaint not found.");
			}			
		}
		catch(ComplaintsAPINotFoundException ex) {
			//throws custom exception in case complaint does not exist.
			throw ex;
		}
		catch(Exception ex) {
			//throws custom exception in case another error occurs (eg. failure to communicate with db)
			throw new ComplaintsAPIException("Operation failed.");
		}		
		
		
		ComplaintUtilities complaintUtilities = new ComplaintUtilities();
		
		//checks if it is a valid complaint (all required fields available)
		if(complaintUtilities.validComplaint(complaint) == true) {
			
			Complaint updatedComplaint;
			
			try {
				//updates the complaint in the db
				updatedComplaint = complaintsRepository.save(complaint);		
				return new ResponseEntity<Complaint>(updatedComplaint, HttpStatus.OK);
			}
			catch(Exception ex) {
				//throws custom exception in case an error occurs (eg. failure to communicate with db)
				throw new ComplaintsAPIException("Operation failed.");
			}			
			
		}
		else
		{
			//throws custom exception in case data is invalid (eg. missing one of the fields)
			throw new ComplaintsAPIBadRequestException("Invalid data. Title, "
					+ "description, locale and company must be informed.");			
		}
		
	}
	
	// Endpoint to list complaints (paged)
	@GetMapping(value = "/complaints", produces = "application/json")
	public ResponseEntity<Page<Complaint>> listComplaints(
			@RequestParam(value = "pageNumber") int pageNumber,
			@RequestParam(value = "pageSize") int pageSize){
		
		Page<Complaint> complaintsPage;
		
		Pageable pg = PageRequest.of(pageNumber, pageSize);		
		
		try {
			complaintsPage = complaintsRepository.findAll(pg);			
		}
		catch(Exception ex) {
			//throws custom exception in case an error occurs (eg. failure to communicate with db)
			throw new ComplaintsAPIException("Operation failed.");
		}		
		
		if(complaintsPage.isEmpty()==true) {
			throw new ComplaintsAPINotFoundException("No complaints found.");
		}
		
		return new ResponseEntity<Page<Complaint>>(complaintsPage, HttpStatus.OK);
	}
	
	
	// Endpoint to list complaints by locale (paged)
	@GetMapping(value = "complaintsbylocale", produces="application/json")
	public ResponseEntity<Page<Complaint>> listComplaintsByLocale(
			@RequestParam(value = "locale") String locale,
			@RequestParam(value = "pageNumber") int pageNumber,
			@RequestParam(value = "pageSize") int pageSize){
		
		Page<Complaint> localeComplaints;
		
		Pageable pg = PageRequest.of(pageNumber, pageSize);
				
		try {
			localeComplaints = complaintsRepository.findComplaintsByLocale(locale, pg);
		}
		catch(Exception ex) {
			//throws custom exception in case an error occurs (eg. failure to communicate with db)
			throw new ComplaintsAPIException("Operation failed.");
		}	
		
		if(localeComplaints.isEmpty()==true) {
			throw new ComplaintsAPINotFoundException("No complaints found.");
		}
		
		return new ResponseEntity<Page<Complaint>>(localeComplaints, HttpStatus.OK);
	}
	
	
	// Endpoint to list complaints by company (paged)
	@GetMapping(value = "complaintsbycompany", produces="application/json")
	public ResponseEntity<Page<Complaint>> listComplaintsByCompany(
			@RequestParam(value = "company") String company,
			@RequestParam(value = "pageNumber") int pageNumber,
			@RequestParam(value = "pageSize") int pageSize){
		
		Page<Complaint> companyComplaints;
		
		Pageable pg = PageRequest.of(pageNumber, pageSize);
				
		try {
			companyComplaints = complaintsRepository.findComplaintsByCompany(company, pg);
		}
		catch(Exception ex) {
			//throws custom exception in case an error occurs (eg. failure to communicate with db)
			throw new ComplaintsAPIException("Operation failed.");
		}
		
		if(companyComplaints.isEmpty()==true) {
			throw new ComplaintsAPINotFoundException("No complaints found.");
		}
		
		return new ResponseEntity<Page<Complaint>>(companyComplaints, HttpStatus.OK);
	}
	
	// Endpoint to list complaints by company and locale (paged)
	@GetMapping(value = "complaintsbycompanybylocale", produces="application/json")
	public ResponseEntity<Page<Complaint>> listComplaintsByCompanyByLocale(
			@RequestParam(value = "company") String company,
			@RequestParam(value = "locale") String locale,
			@RequestParam(value = "pageNumber") int pageNumber,
			@RequestParam(value = "pageSize") int pageSize){
		
		Page<Complaint> companyLocaleComplaints;
		
		Pageable pg = PageRequest.of(pageNumber, pageSize);
				
		try {
			companyLocaleComplaints = complaintsRepository.findComplaintsByCompanyByLocale(company, locale, pg);
		}
		catch(Exception ex) {
			//throws custom exception in case an error occurs (eg. failure to communicate with db)
			throw new ComplaintsAPIException("Operation failed.");
		}	
		
		if(companyLocaleComplaints.isEmpty()==true) {
			throw new ComplaintsAPINotFoundException("No complaints found.");
		}
		
		return new ResponseEntity<Page<Complaint>>(companyLocaleComplaints, HttpStatus.OK);
	}
	
	
	// Endpoint that returns the number of complaints by locale
	@GetMapping(value = "countcomplaintsbylocale", produces="application/json")
	public ResponseEntity<String> countComplaintsByLocale(
			@RequestParam(value = "locale") String locale){
		
		int numberOfComplaints;
				
		try {
			numberOfComplaints = complaintsRepository.countComplaintsByLocale(locale);
		}
		catch(Exception ex) {
			//throws custom exception in case an error occurs (eg. failure to communicate with db)
			throw new ComplaintsAPIException("Operation failed.");
		}		
		
		return new ResponseEntity<String>("Locale " + locale + " has " + numberOfComplaints + " complaints", HttpStatus.OK);
	}
	
	// Endpoint that returns the number of complaints by company
	@GetMapping(value = "countcomplaintsbycompany", produces="application/json")
	public ResponseEntity<String> countComplaintsByCompany(
			@RequestParam(value = "company") String company){
		
		int numberOfComplaints;
				
		try {
			numberOfComplaints = complaintsRepository.countComplaintsByCompany(company);
		}
		catch(Exception ex) {
			//throws custom exception in case an error occurs (eg. failure to communicate with db)
			throw new ComplaintsAPIException("Operation failed.");
		}		
		
		return new ResponseEntity<String>("Company " + company + " has " + numberOfComplaints + " complaints", HttpStatus.OK);
	}
	
	// Endpoint that returns the number of complaints by company and locale
	@GetMapping(value = "countcomplaintsbycompanybylocale", produces="application/json")
	public ResponseEntity<String> countComplaintsByCompanyByLocale(
			@RequestParam(value = "company") String company,
			@RequestParam(value = "locale") String locale){
		
		int numberOfComplaints;
				
		try {
			numberOfComplaints = complaintsRepository.countComplaintsByCompanyByLocale(company, locale);
		}
		catch(Exception ex) {
			//throws custom exception in case an error occurs (eg. failure to communicate with db)
			throw new ComplaintsAPIException("Operation failed.");
		}		
		
		return new ResponseEntity<String>("Company " + company + " has " + numberOfComplaints + 
				" complaints in locale " + locale, HttpStatus.OK);
	}
	
	// Endpoint to delete a complaint
	@DeleteMapping(value = "/complaints", produces="application/json")
	public ResponseEntity<String> deleteComplaint(
			@RequestParam(value = "complaintId") Long complaintId){
		
		try {
			
			Optional<Complaint> complaintToDelete = complaintsRepository.findById(complaintId);
			
			if(complaintToDelete.isEmpty() == true) {
				throw new ComplaintsAPINotFoundException("Complaint not found.");
			}
			
		}
		catch(ComplaintsAPINotFoundException ex) {
			//throws custom exception in case complaint does not exist.
			throw ex;			
		}
		catch(Exception ex) {
			//throws custom exception in case another error occurs (eg. failure to communicate with db)
			throw new ComplaintsAPIException("Operation failed.");
		}
		
		try {
			complaintsRepository.deleteById(complaintId);
		}
		catch(Exception ex) {
			throw new ComplaintsAPIException("Operation failed.");
		}
		
		return new ResponseEntity<String>("ComplaintId " + complaintId + " deleted successfully", HttpStatus.OK);
				
	}

}
