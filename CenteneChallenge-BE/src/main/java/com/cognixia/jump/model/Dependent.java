package com.cognixia.jump.model;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

//@Entity --> defines class that will model our table and Spring will create it using the following
//configurations with the annotations we give
@Entity
@Table(name = "dependent")
public class Dependent implements Serializable{

	// @Id --> primary key
	// @GeneratedValue --> use to set auto increment for this column
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long dependent_id;
		
	// @Column --> providing definitions on how to set up our column
	// name --> set the column name used in table
	@Column(name = "name")
	private String name;

	@JsonFormat(pattern = "MM-dd-yyyy")
	private String birthday;
	
	@ManyToOne
	@JoinColumn(name = "dependent_id")
	@JsonBackReference
	private static Enrollee enrollee;

	
	public Dependent() {
		this(-1L, "N/A", "N/A", enrollee);
	}
	
	
	public Dependent(Long dependent_id, String name, String birthday, Enrollee enrollee) {
		super();
		this.dependent_id = dependent_id;
		this.name = name;
		this.birthday = birthday;
		this.enrollee = enrollee;
	}
	

	public Long getDependent_id() {
		return dependent_id;
	}

	public void setDependent_id(Long dependent_id) {
		this.dependent_id = dependent_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public Enrollee getEnrollee() {
		return enrollee;
	}

	public void setEnrollee(Enrollee enrollee) {
		this.enrollee = enrollee;
	}

	@Override
	public String toString() {
		return "Dependent [dependent_id=" + dependent_id + ", name=" + name + ", birthday=" + birthday + ", enrollee="
				+ enrollee + "]";
	}
	
	
}
