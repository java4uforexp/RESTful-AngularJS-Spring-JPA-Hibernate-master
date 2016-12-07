package com.amq.cma.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.amq.cma.domain.CompanyEntity;
import com.amq.cma.domain.OwnerEntity;
import com.amq.cma.services.CompanyServices;

@RestController
@RequestMapping("/services")
@Profile("dev")
public class CmaFrontController {
	private final Logger logger = LoggerFactory.getLogger(CmaFrontController.class);
	@Autowired
	private CompanyServices companyServices;

	public void setCompanyServices(CompanyServices companyServices) {
		this.companyServices = companyServices;
	}

	@RequestMapping(value = "/companies", method = RequestMethod.GET)
	public ResponseEntity<List<CompanyEntity>> listCompanies() {
		logger.debug("Listing companies...567");

		List<CompanyEntity> comps = companyServices.getAllCompanies();

		return new ResponseEntity<List<CompanyEntity>>(comps, HttpStatus.OK);
	}

	@RequestMapping(value = "/companies/{companyId}", method = RequestMethod.GET)
	public ResponseEntity<CompanyEntity> getCompany(@PathVariable long companyId) {
		logger.debug("Looking for company " + companyId + "...");

		CompanyEntity comp = companyServices.getCompany(companyId);
		return new ResponseEntity<CompanyEntity>(comp, HttpStatus.OK);
	}

	@RequestMapping(value = "/companies/save", method = RequestMethod.POST)
	public ResponseEntity<CompanyEntity> saveCompany(@RequestBody CompanyEntity company) {
		if (company == null) {
			return new ResponseEntity<CompanyEntity>(company, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		logger.debug("Saving company" + company + "...");

		company = companyServices.saveCompany(company);

		return new ResponseEntity<CompanyEntity>(company, HttpStatus.OK);
	}

	@RequestMapping(value = "/owners", method = RequestMethod.GET)
	public ResponseEntity<List<OwnerEntity>> listOwners() {
		logger.debug("Listing owners...");

		List<OwnerEntity> owners = companyServices.getAllOwners();

		return new ResponseEntity<List<OwnerEntity>>(owners, HttpStatus.OK);
	}

	@RequestMapping(value = "/owners/save", method = RequestMethod.POST)
	public ResponseEntity<OwnerEntity> saveOwner(@RequestBody OwnerEntity owner) {
		if (owner == null) {
			return new ResponseEntity<OwnerEntity>(owner, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		logger.debug("Saving owner " + owner + "...");

		owner = companyServices.saveOwner(owner);
		return new ResponseEntity<OwnerEntity>(owner, HttpStatus.OK);
	}

	@RequestMapping(value = "/owners", method = RequestMethod.GET)
	public ResponseEntity<OwnerEntity> getOwner(@PathVariable long ownerId) {
		logger.debug("Looking for owner " + ownerId + "...");

		OwnerEntity owner = companyServices.getOwner(ownerId);
		return new ResponseEntity<OwnerEntity>(owner, HttpStatus.OK);
	}
}
