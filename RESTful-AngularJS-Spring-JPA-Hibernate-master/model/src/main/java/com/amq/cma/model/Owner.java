/**
 * 
 */
package com.amq.cma.model;



/**
 * @author Bharath
 *
 */
public class Owner {

	
	private static final long serialVersionUID = -8607277250011280663L;

	
	private Long ownerId;
	
	
	private String name;


	public Long getOwnerId() {
		return ownerId;
	}


	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return "Owner [name=" + name + "]";
	}
	
	
}
