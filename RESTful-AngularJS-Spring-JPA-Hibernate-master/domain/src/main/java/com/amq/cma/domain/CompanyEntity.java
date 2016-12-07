package com.amq.cma.domain;

import java.io.Serializable;
import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "COMPANY")
@NamedQuery(name = "Company.findAll", query = "SELECT c.companyId, c.name FROM CompanyEntity c ORDER BY c.name")
@NamedEntityGraph(name = "Company.with.owners", includeAllAttributes = true)
public class CompanyEntity implements Serializable {
	@Transient
	private static final long serialVersionUID = -9121332635976602513L;

	@Id
	@Column(name = "company_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQUENCE1")
	@SequenceGenerator(name="SEQUENCE1", sequenceName="SEQUENCE1", allocationSize=1)
	private Long companyId;

	@Column( length = 100, nullable = false, unique = true)
	private String name;

	@Column(length = 100, nullable = true, unique = false)
	private String email;

	@Column(length = 20, nullable = true, unique = false)
	private String phoneNumber;

	@Column(length = 200, nullable = true, unique = false)
	private String address;

	@Column(length = 100, nullable = true, unique = false)
	private String city;

	@Column(length = 100, nullable = true, unique = false)
	private String country;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinTable(name = "comp_owner", joinColumns = @JoinColumn(name = "company_id"), inverseJoinColumns = @JoinColumn(name = "owner_id"))
	private List<OwnerEntity> owners;

	public CompanyEntity() {
		owners = new ArrayList<OwnerEntity>();
	}

	public CompanyEntity(Long companyId, String name) {
		this();
		this.companyId = companyId;
		this.name = name;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public List<OwnerEntity> getOwners() {
		return owners;
	}

	public void setOwners(List<OwnerEntity> owners) {
		this.owners = owners;
	}

	public boolean addOwner(OwnerEntity owner) {
		return owners.add(owner);
	}

	public boolean addOwners(List<OwnerEntity> owners) {
		return this.owners.addAll(owners);
	}

	public boolean removeOwner(OwnerEntity owner) {
		return owners.remove(owner);
	}

	public int ownersNumber() {
		return owners.size();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((companyId == null) ? 0 : companyId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}

		CompanyEntity other = (CompanyEntity) obj;
		if (companyId == null) {
			if (other.companyId != null) {
				return false;
			}
		} else if (!companyId.equals(other.companyId)) {
			return false;
		}

		return true;
	}

	@Override
	public String toString() {
		return companyId + "-" + name;
	}

}
