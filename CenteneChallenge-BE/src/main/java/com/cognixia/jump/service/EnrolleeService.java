package com.cognixia.jump.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.model.Enrollee;

//mark/label this as a repo
@Repository
public interface EnrolleeService extends JpaRepository<Enrollee, Long>{

	// one of the methods listed in jpa, retrieve all the records/entities from a table
	List<Enrollee> findAll();
}
