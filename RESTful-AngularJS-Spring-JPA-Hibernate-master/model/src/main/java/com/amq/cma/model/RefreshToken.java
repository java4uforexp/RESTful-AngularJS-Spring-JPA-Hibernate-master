/**
 * 
 */
package com.amq.cma.model;

/**
 * @author Bharath
 *
 */
public class RefreshToken {
	
	String value;
	Long expiration;
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Long getExpiration() {
		return expiration;
	}
	public void setExpiration(Long expiration) {
		this.expiration = expiration;
	}
	

}
