package com.amq.cma.persistence;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.amq.cma.domain.CompanyEntity;
import com.amq.cma.domain.OwnerEntity;


public interface CompanyDao {
	/** Return a list with all companies available in the app's repository. */
	public List<CompanyEntity> getAllCompanies();
	
	/** Search the repository and return a company based on the given companyId. */
	public CompanyEntity getCompany(Long companyId);
	
	/** Store the given company into this app's repository. */
	public CompanyEntity saveCompany(CompanyEntity newCompany);

	/** Add all given owners in an existing company based on the given companyId. */
	public CompanyEntity addOwners(Long companyId, List<OwnerEntity> owners);
	
	/** Retrieve a list of all owners stored in the underline repository. */
	public List<OwnerEntity> getAllOwners();
	
	/** Fetch a list of owners from the underline repository based on the given owners list. 
	 * This service is mainly used within this persistence module to fetch previous stored owners.*/
	public List<OwnerEntity> getOwners(List<OwnerEntity> owners);
	
	/** Save the given owner on the app's repository. */
	public OwnerEntity saveOwner(OwnerEntity owner); 
	
	public OwnerEntity getOwner(Long ownerId);
}
