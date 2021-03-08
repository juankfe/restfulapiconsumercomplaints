package complaintsapi.repository;


import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import complaintsapi.model.Complaint;

@Repository
@Transactional
public interface ComplaintsRepository extends CrudRepository<Complaint, Long>, 
PagingAndSortingRepository<Complaint, Long>{
	
	Page<Complaint> findAll(Pageable pageable);
	
	@Query("SELECT c FROM Complaint c WHERE c.locale=?1")
	Page<Complaint> findComplaintsByLocale(String locale, Pageable pageable);
	
	@Query("SELECT c FROM Complaint c WHERE c.company=?1")
	Page<Complaint> findComplaintsByCompany(String company, Pageable pageable);
	
	@Query("SELECT c FROM Complaint c WHERE c.company=?1 AND c.locale=?2")
	Page<Complaint> findComplaintsByCompanyByLocale(String company, String Locale, Pageable pageable);
	
	@Query("SELECT COUNT(c.complaintId) FROM Complaint c WHERE c.locale=?1")
	int countComplaintsByLocale(String locale);
	
	@Query("SELECT COUNT(c.complaintId) FROM Complaint c WHERE c.company=?1")
	int countComplaintsByCompany(String company);
	
	@Query("SELECT COUNT(c.complaintId) FROM Complaint c WHERE c.company=?1 AND c.locale=?2")
	int countComplaintsByCompanyByLocale(String company, String Locale);
	
}
