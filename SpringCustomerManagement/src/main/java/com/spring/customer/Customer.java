package com.spring.customer;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="customer12")
public class Customer {
	
	@NotNull(message = " ID Can't Be Empty")
	@Id
	private int id;
	@NotEmpty(message = " First Name Can't Be Empty")
	private String firstname;
	@NotEmpty(message = " Last Name Can't Be Empty")
	private String lastname;
	@NotEmpty(message = " Email Can't Be Empty")
	private String email;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	@NotNull(message = " Phone No. Can't Be Empty")
	private long phone;
	
	
	public Customer(@NotEmpty(message = " ID Can't Be Empty") int id,
			@NotEmpty(message = " First Name Can't Be Empty") String firstname,
			@NotEmpty(message = " Last Name Can't Be Empty") String lastname,
			@NotEmpty(message = " Email Can't Be Empty") String email,
			@NotNull(message = " Phone No. Can't Be Empty") long phone) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.phone = phone;
	}
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	
	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email
				+ ", phone=" + phone + "]";
	}
}
