package com.cognixia.jump.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

// @Entity --> defines class that will model our table and Spring will create it using the following
// configurations with the annotations we give
@Entity
@Table(name = "enrollee")
public class Enrollee implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// @Id --> primary key
	// @GeneratedValue --> use to set auto increment for this column
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long enrollee_id;
	
	// @Column --> providing definitions on how to set up our column
	// name --> set the column name used in table
	@Column(name = "name")
	private String name;

	@Column(name = "activation_status")
	private String activationStatus;
	
	@JsonFormat(pattern = "MM-dd-yyyy")
	private String birthday;
	
	@Column(name = "phone_number")
	private String phoneNumber;
	
	@OneToMany(mappedBy = "enrollee")
	@Column(nullable = true)
	@JsonManagedReference
	private List<Dependent> dependent;

	
	public Enrollee() {
		this(-1L, "N/A", "N/A", "N'A", "N/A", new ArrayList<Dependent>());
	}
	
	
	public Enrollee(Long enrollee_id, String name, String activationStatus, String birthday, String phoneNumber,
			List<Dependent> dependent) {
		super();
		this.enrollee_id = enrollee_id;
		this.name = name;
		this.activationStatus = activationStatus;
		this.birthday = birthday;
		this.phoneNumber = phoneNumber;
		this.dependent = dependent;
	}


	public Long getEnrollee_id() {
		return enrollee_id;
	}

	public void setEnrollee_id(Long enrollee_id) {
		this.enrollee_id = enrollee_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getActivationStatus() {
		return activationStatus;
	}

	public void setActivationStatus(String activationStatus) {
		this.activationStatus = activationStatus;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<Dependent> getDependent() {
		return dependent;
	}

	public void setDependent(List<Dependent> dependent) {
		this.dependent = dependent;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public String toString() {
		return "Enrollee [enrollee_id=" + enrollee_id + ", name=" + name + ", activationStatus=" + activationStatus
				+ ", birthday=" + birthday + ", phoneNumber=" + phoneNumber + ", dependent=" + dependent + "]";
	}

	
}
