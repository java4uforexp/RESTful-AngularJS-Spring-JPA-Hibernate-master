package com.amq.cma.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.amq.cma.domain.CompanyEntity;
import com.amq.cma.domain.OwnerEntity;
import com.amq.cma.persistence.CompanyDao;

@Service("companyServices")
@Profile("dev")
public class CompanyServicesImpl implements CompanyServices {
	private final Logger logger = LoggerFactory.getLogger(CompanyServicesImpl.class);
	private CompanyDao companyDao;
	
	@Autowired
	public void setCompanyDao(CompanyDao companyDao){
		this.companyDao = companyDao;
	}
	
	@Override
	public List<CompanyEntity> getAllCompanies() {
		logger.debug("Selecting all companies stored...");
		
		return companyDao.getAllCompanies();
	}
	
	@Override
	public CompanyEntity getCompany(Long companyId) {
		if(companyId == null){ return null; }
		logger.debug("Selecting company with id "+ companyId +" ...");
		
		return companyDao.getCompany(companyId);
	}
	
	@Override
	public List<OwnerEntity> getAllOwners(){
		logger.debug("Selecting all owners stored...");
		
		return companyDao.getAllOwners();
	}
	
	@Override
	public CompanyEntity saveCompany(CompanyEntity newCompany) {
		if(newCompany == null){ return null; }
		logger.debug("Saving company..."+ newCompany);
		
		return companyDao.saveCompany(newCompany);
	}
	
	@Override
	public OwnerEntity saveOwner(OwnerEntity owner){
		if(owner == null){ return owner; }		
		logger.info("Saving owner "+ owner +"...");
		
		return companyDao.saveOwner(owner);
	}
	
	@Override
	public CompanyEntity addOwners(Long companyId, List<OwnerEntity> owners) {
		if(companyId == null){ 
			logger.warn("Company id was null. Returning null."); 
			return null; 
		}
		logger.info("Adding Owner(s) "+ owners + " to company "+ companyId +"...");
		
		return companyDao.addOwners(companyId, owners);
	}

	public OwnerEntity getOwner(Long ownerId) {
		if(ownerId == null){ return null; }
		logger.debug("Selecting Owner with id "+ ownerId +" ...");
		
		return companyDao.getOwner(ownerId);
	}
}
