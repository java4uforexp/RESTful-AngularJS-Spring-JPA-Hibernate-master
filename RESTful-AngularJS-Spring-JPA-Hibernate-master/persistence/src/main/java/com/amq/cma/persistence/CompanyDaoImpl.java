package com.amq.cma.persistence;

import java.awt.color.CMMException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.CriteriaImpl.OrderEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.amq.cma.domain.CompanyEntity;
import com.amq.cma.domain.OwnerEntity;

@Repository
@Profile("dev")
public class CompanyDaoImpl implements CompanyDao {
	private Logger logger = LoggerFactory.getLogger(CompanyDaoImpl.class);
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<CompanyEntity> getAllCompanies() {
		logger.debug("Selecting all companies stored...");

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(CompanyEntity.class);
		criteria.addOrder(Order.asc("name"));

		// Query query = sessionFactory.getCurrentSession().createQuery("SELECT
		// o FROM COMPANY o ORDER BY o.name");//
		// createNamedQuery("Company.findAll",
		// Company.class);
		logger.debug("Querying database after all companies => " + criteria.toString());
		@SuppressWarnings("unchecked")
		List<CompanyEntity> companies = criteria.list();

		logger.debug("List of companies found! Retrieving " + companies.size() + " companie(s).");

		return companies;
	}

	public CompanyEntity getCompany(Long companyId) {
		logger.debug("Selecting company with id " + companyId + " ...");

		CompanyEntity comp = sessionFactory.getCurrentSession().get(CompanyEntity.class, companyId);

		return comp;
	}

	@SuppressWarnings("unchecked")
	public List<OwnerEntity> getOwners(List<OwnerEntity> owners) {
		logger.debug("Selecting owners based on the given list of owners " + owners + "...");

		List<Long> ids = new ArrayList<Long>();
		for (OwnerEntity o : owners) {
			ids.add(o.getOwnerId());
		}

		Query query = sessionFactory.getCurrentSession().getNamedQuery("Owner.findByIds");
		query.setParameter("ids", ids);

		logger.debug("Querying database after owners => " + query.toString());
		owners = query.list();
		logger.debug("List of owners found! Retrieving " + owners.size() + " owners(s).");

		return owners;
	}

	@SuppressWarnings("unchecked")
	public List<OwnerEntity> getAllOwners() {
		logger.debug("Selecting all owners stored...");
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(OwnerEntity.class);
		criteria.addOrder(Order.asc("name"));
		// Query query = sessionFactory.getCurrentSession().createQuery("SELECT
		// o FROM OWNER o ORDER BY o.name");

		// logger.debug("Querying database after all owners => " +
		// query.toString());
		List<OwnerEntity> owners = criteria.list();
		logger.debug("List of owners found! Retrieving " + owners.size() + " owners(s).");

		return owners;
	}

	public CompanyEntity saveCompany(CompanyEntity newCompany) {
		logger.debug("Saving company..." + newCompany);
		if (newCompany == null) {
			return null;
		}

		if (newCompany.getCompanyId() == null) {
			logger.info("Creating new company " + newCompany + "...");

			sessionFactory.getCurrentSession().save(newCompany);
		} else {
			logger.info("Updating company " + newCompany + "...");
			sessionFactory.getCurrentSession().merge(newCompany);
		}

		logger.info("New company " + newCompany.getName() + " saved with id " + newCompany.getCompanyId() + ".");
		return newCompany;
	}

	@Override
	public OwnerEntity saveOwner(OwnerEntity owner) {
		logger.debug("Saving owner " + owner + "...");
		if (owner == null) {
			return null;
		}

		if (owner.getOwnerId() == null) {
			logger.info("Creating owner " + owner + "...");
			sessionFactory.getCurrentSession().save(owner);
		} else {
			logger.info("Updating owner " + owner + "...");
			sessionFactory.getCurrentSession().merge(owner);
		}

		return owner;
	}

	@Override
	public CompanyEntity addOwners(Long companyId, List<OwnerEntity> owners) {
		CompanyEntity company = getCompany(companyId);
		logger.info("Adding Owner " + owners + " to company " + company.getName() + "...");

		company.addOwners(owners);

		return saveCompany(company);
	}

	public OwnerEntity getOwner(Long ownerId) {
		logger.debug("Selecting owner with id " + ownerId + " ...");

		OwnerEntity owner = sessionFactory.getCurrentSession().get(OwnerEntity.class, ownerId);

		return owner;
	}

}
