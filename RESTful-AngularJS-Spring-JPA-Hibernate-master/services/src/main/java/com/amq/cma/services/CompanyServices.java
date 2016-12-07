package com.amq.cma.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.amq.cma.domain.CompanyEntity;
import com.amq.cma.domain.OwnerEntity;

@Transactional
public interface CompanyServices {
	/** Return a List of all existing companies. Since not clarified what 
	 * is necessary on this list, a lazy loaded list will be returned here containing the Id and Name of the company only. */
	public List<CompanyEntity> getAllCompanies();
	
	/** Return a company that matches the given companyId with all it's properties. */
	public CompanyEntity getCompany(Long companyId);
	
	/** Return a List of all existing owners. */
	public List<OwnerEntity> getAllOwners();
	
	/** Create new company or update an existing one. */
	public CompanyEntity saveCompany(CompanyEntity newCompany);
		
	/** Create or just retrieve the given owner with its current id created or previously stored. */
	public OwnerEntity saveOwner(OwnerEntity owner);
	
	/** Add the new owner to the given company. Since not clarified, 
	 * assuming if the owner does not exists, it will be created, otherwise it will be reused. */
	public CompanyEntity addOwners(Long companyId, List<OwnerEntity> owners);
	
	public OwnerEntity getOwner(Long ownerId);
}
