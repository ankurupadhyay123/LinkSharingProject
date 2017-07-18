package com.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Integer userId;

	@Column
	private String firstName;

	@Column
	private String lastName;

	@Column(unique = true)
	private String userName;

	@Column(unique = true)
	private String email;

	@Column
	private String password;

	@Column
	private boolean admin;

	@Column
	private boolean active;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdated;

	@Lob
	@Column(name=("profile_pic"), nullable=false, columnDefinition="longblob")
	private byte[] photo;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public byte[] getPhoto() { return photo; }

	public void setPhoto(byte[] photo) { this.photo = photo; }

	public boolean isAdmin() { return admin; }

	public void setAdmin(boolean admin) { this.admin = admin; }

	public boolean isActive() { return active; }

	public void setActive(boolean active) { this.active = active; }

	public Date getDateCreated() { return dateCreated; }

	public void setDateCreated(Date dateCreated) { this.dateCreated = dateCreated; }

	public Date getLastUpdated() { return lastUpdated; }

	public void setLastUpdated(Date lastUpdated) { this.lastUpdated = lastUpdated; }

	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", UserName="
				+ userName + ", email=" + email + ", password=" + password + ", dateCreated="+ dateCreated+", lastUpdated"+ lastUpdated+"]";
	}

}