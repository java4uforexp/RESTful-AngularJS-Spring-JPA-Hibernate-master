/**
 * 
 */
package com.amq.cma.model;

import java.util.List;

/**
 * @author Bharath
 *
 */

public class Company {

	private static final long serialVersionUID = -9121332635976602513L;

	private Long companyId;

	private String name;

	private String email;

	private String phoneNumber;

	private String address;

	private String city;

	private String country;

	private List<Owner> owners;

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

	@Override
	public String toString() {
		return "Company [name=" + name + ", email=" + email + ", phoneNumber=" + phoneNumber + ", address=" + address
				+ ", city=" + city + ", country=" + country + ", owners=" + owners + "]";
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

	public List<Owner> getOwners() {
		return owners;
	}

	public void setOwners(List<Owner> owners) {
		this.owners = owners;
	}

}
