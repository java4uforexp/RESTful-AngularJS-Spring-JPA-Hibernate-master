package com.amq.cma.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.amq.cma.domain.CompanyEntity;
import com.amq.cma.domain.OwnerEntity;

@RunWith(SpringJUnit4ClassRunner.class)
//@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration("classpath:/services-context.xml")
@ActiveProfiles("test")
public class CompanyServicesTest {
	private final Logger logger = LoggerFactory.getLogger(CompanyServicesTest.class);
	
	@Mock 
	private CompanyServices companyServices;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
	} 
	
	@Test
	public void getAllCompaniesTest(){
		logger.debug("testing CompanyServicesImpl.getAllCompanies()...");
		OwnerEntity owner = new OwnerEntity();
		owner.setOwnerId(54L);
		owner.setName("Skywalker Kenobi");
		
		CompanyEntity comp = new CompanyEntity();
		comp.setCompanyId(Long.valueOf(333));
		comp.setName("Acme Inc.");
		comp.setAddress("685 Market St #7th floor");
		comp.setCity("San Francisco");
		comp.setCountry("USA");
		comp.addOwner(owner);
		
		CompanyEntity comp1 = new CompanyEntity();
		comp1.setCompanyId(Long.valueOf(444));
		comp1.setName("VanHack Inc.");
		comp1.setAddress("900 West Georgia St #5th floor");
		comp1.setCity("Vancouver");
		comp1.setCountry("Canada");
		comp1.addOwner(owner);
		
		List<CompanyEntity> comps = new ArrayList<CompanyEntity>();
		comps.add(comp);
		comps.add(comp1);
		
		Mockito.when(companyServices.getAllCompanies()).thenReturn(comps);
		
		List<CompanyEntity> result = companyServices.getAllCompanies();
		
		assertEquals(comps.size(), result.size());
	}
	
	@Test
	public void getCompanyTest(){
		logger.debug("testing CompanyServicesImpl.getCompany(companyId)...");
		
		OwnerEntity owner = new OwnerEntity();
		owner.setOwnerId(54L);
		owner.setName("Skywalker Kenobi");
		
		CompanyEntity comp = new CompanyEntity();
		comp.setCompanyId(Long.valueOf(333));
		comp.setName("Acme Inc.");
		comp.setAddress("685 Market St #7th floor");
		comp.setCity("San Francisco");
		comp.setCountry("USA");
		comp.addOwner(owner);
		
		Mockito.when(companyServices.getCompany(333L)).thenReturn(comp);
		
		CompanyEntity comp1 = companyServices.getCompany(333L);
		assertEquals(Long.valueOf(333L), comp1.getCompanyId());
		assertEquals("Acme Inc.", comp1.getName());
	}
	
	@Test
	public void getAllOwnersTest(){
		logger.debug("testing CompanyServicesImpl.getAllOwners()...");
		
		OwnerEntity owner = new OwnerEntity();
		owner.setOwnerId(54L);
		owner.setName("Skywalker Kenobi");
		OwnerEntity owner1 = new OwnerEntity();
		owner1.setOwnerId(55L);
		owner1.setName("Trying Skywalker");
		
		List<OwnerEntity> owners = new ArrayList<OwnerEntity>();
		owners.add(owner);
		owners.add(owner1);
		
		Mockito.when(companyServices.getAllOwners()).thenReturn(owners);
		
		List<OwnerEntity> result = companyServices.getAllOwners();
		
		assertEquals(owners.size(), result.size());
	}

	@Test
	public void saveCompanyTest(){
		logger.debug("testing CompanyServicesImpl.saveCompany()...");
		OwnerEntity owner = new OwnerEntity();
		owner.setName("Skywalker Kenobi");
		List<OwnerEntity> owners = new ArrayList<OwnerEntity>();
		owners.add(owner);
		
		CompanyEntity comp = new CompanyEntity();
		comp.setName("New Acme Inc.");
		comp.setAddress("685 California St #777th floor");
		comp.setCity("San Mateo");
		comp.setCountry("USA");
		comp.addOwners(owners);
		
		OwnerEntity owner1 = new OwnerEntity();
		owner1.setOwnerId(78L);
		owner1.setName("Skywalker Kenobi");
		List<OwnerEntity> owners1 = new ArrayList<OwnerEntity>();
		owners1.add(owner1);
		
		CompanyEntity comp1 = new CompanyEntity();
		comp1.setCompanyId(444L);
		comp1.setName("New Acme Inc.");
		comp1.setAddress("685 California St #777th floor");
		comp1.setCity("San Mateo");
		comp1.setCountry("USA");
		comp1.addOwners(owners1);

		Mockito.when(companyServices.saveCompany(comp)).thenReturn(comp1);
		
		CompanyEntity comp2 = companyServices.saveCompany(comp);
		assertEquals(Long.valueOf(444L), comp2.getCompanyId());
		assertEquals(Long.valueOf(78L), comp2.getOwners().get(0).getOwnerId());
	}
	
	@Test
	public void saveCompanyTestNull(){
		logger.debug("testing CompanyServicesImpl.saveCompany() null...");
		
		Mockito.when(companyServices.saveCompany(null)).thenReturn(null);		
		CompanyEntity comp = companyServices.saveCompany(null);
		
		assertNull(comp);
	}
	
	@Test
	public void addOwnersTest(){
		logger.debug("testing CompanyServicesImpl.addOwners(companyId, owners) create...");
		OwnerEntity owner = new OwnerEntity();
		owner.setName("Skywalker Vader");
		OwnerEntity owner1 = new OwnerEntity();
		owner1.setName("Vader Skywalker");
		
		List<OwnerEntity> owners = new ArrayList<OwnerEntity>();
		owners.add(owner);
		owners.add(owner1);
		
		OwnerEntity owner2 = new OwnerEntity();
		owner2.setOwnerId(22L);
		owner2.setName("Skywalker Kenobi");
		
		CompanyEntity comp = new CompanyEntity();
		comp.setCompanyId(444L);
		comp.setName("Acme Inc.");
		comp.setAddress("685 Market St #7th floor");
		comp.setCity("San Francisco");
		comp.setCountry("USA");
		comp.addOwner(owner2);
		
		List<OwnerEntity> owners1 = new ArrayList<OwnerEntity>();
		owners1.add(owner);
		owners1.add(owner1);
		owners1.add(owner2);
		
		CompanyEntity comp1 = new CompanyEntity();
		comp1.setCompanyId(444L);
		comp1.setName("Acme Inc.");
		comp1.setAddress("685 Market St #7th floor");
		comp1.setCity("San Francisco");
		comp1.setCountry("USA");
		comp1.addOwners(owners1);
		
		Mockito.when(companyServices.addOwners(444L, owners)).thenReturn(comp1);
		
		CompanyEntity comp2 = companyServices.addOwners(444L, owners);
		assertEquals(3, comp2.getOwners().size());
	}
	
	@Test
	public void addOwnersTestNull(){
		logger.debug("testing CompanyServicesImpl.addOwners(companyId, owners) null...");
		OwnerEntity owner = new OwnerEntity();
		owner.setName("Skywalker Vader");
		OwnerEntity owner1 = new OwnerEntity();
		owner1.setName("Vader Skywalker");
		
		List<OwnerEntity> owners = new ArrayList<OwnerEntity>();
		owners.add(owner);
		owners.add(owner1);
		
		Mockito.when(companyServices.addOwners(null, owners)).thenReturn(null);		
		CompanyEntity comp = companyServices.addOwners(null, owners);
		assertNull(comp);
	}
	
	@Test
	public void saveOwnerTest(){
		logger.debug("testing CompanyServicesImpl.saveOwner() ...");
		OwnerEntity owner = new OwnerEntity();
		owner.setOwnerId(54L);
		owner.setName("Skywalker Kenobi");
		
		Mockito.when(companyServices.saveOwner(owner)).thenReturn(owner);		
		owner = companyServices.saveOwner(owner);		
		assertNotNull(owner.getOwnerId());
	}
	
	@Test
	public void saveOwnerTestNull(){
		Mockito.when(companyServices.saveOwner(null)).thenReturn(null);		
		OwnerEntity owner = companyServices.saveOwner(null);		
		assertNull(owner);
	}
}
