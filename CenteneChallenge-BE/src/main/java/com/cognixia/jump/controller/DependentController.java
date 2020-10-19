package com.cognixia.jump.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.model.Dependent;
import com.cognixia.jump.service.DependentService;

@RequestMapping("/api")
@RestController
public class DependentController {

	@Autowired
	DependentService dependent_Service;
	
	@GetMapping("/dependent")
	public List<Dependent> getAllDependents() {
		
		return dependent_Service.findAll();
	}
	
	@GetMapping("/dependent/{dependent_id}")
	public Dependent getDependent(@PathVariable long dependent_id) {
		
		Optional<Dependent> dependentOpt = dependent_Service.findById(dependent_id);
		
		if(dependentOpt.isPresent()) {
			return dependentOpt.get();
		}
		
		return new Dependent();
	}
	
	@PostMapping("/add/dependent")
	public void addDependent(@RequestBody Dependent newDependent) {
		
		newDependent.setDependent_id(-1L);
		
		Dependent added = dependent_Service.save(newDependent); // save() does an insert or update (depends on id passed)
		
		System.out.println("Added: " + added);
		
	}
	
	@PutMapping("/update/dependent")
	public @ResponseBody String updateEnrollee(@RequestBody Dependent updateDependent) {
		
		// check if dependent exists, then update them
		
		Optional<Dependent> found = dependent_Service.findById(updateDependent.getDependent_id());
		
		if(found.isPresent()) {
			dependent_Service.save(updateDependent);
			return "Saved: " + updateDependent.toString();
		}
		else {
			return "Could not update dependent, the id = " + updateDependent.getDependent_id() + " doesn't exist";
		}
		
	}
	
	@DeleteMapping("/delete/dependent/{id}")
	public ResponseEntity<String> deleteDependent(@PathVariable long dependent_id) {
		
		Optional<Dependent> found = dependent_Service.findById(dependent_id);
		
		if(found.isPresent()) {
			
			dependent_Service.deleteById(dependent_id);
			
			return ResponseEntity.status(200).body("Deleted Dependent with id = " + dependent_id);	
		}
		else {
			return ResponseEntity.status(400)
					.header("Dependent id", dependent_id + "")
					.body("Dependent with id = " + dependent_id + " not found");
		}
			
	}
	
}
