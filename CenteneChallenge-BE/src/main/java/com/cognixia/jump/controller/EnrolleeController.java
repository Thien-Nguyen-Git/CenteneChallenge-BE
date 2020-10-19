package com.cognixia.jump.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.model.Enrollee;
import com.cognixia.jump.service.EnrolleeService;

@RequestMapping("/api")
@RestController
public class EnrolleeController {

	@Autowired
	EnrolleeService enrollee_Service;
	
	@GetMapping("/enrollees")
	public List<Enrollee> getAllEnrollees() {
		
		return enrollee_Service.findAll();
	}
	
	@GetMapping("/enrollee/{enrollee_id}")
	public Enrollee getEnrollee(@PathVariable long enrollee_id) {
		
		Optional<Enrollee> enrolleeOpt = enrollee_Service.findById(enrollee_id);
		
		if(enrolleeOpt.isPresent()) {
			return enrolleeOpt.get();
		}
		
		return new Enrollee();
	}
	
	@PostMapping("/add/enrollee")
	public void addEnrollee(@RequestBody Enrollee newEnrollee) {
		
		newEnrollee.setEnrollee_id(-1L);
		
		Enrollee added = enrollee_Service.save(newEnrollee); // save() does an insert or update (depends on id passed)
		
		System.out.println("Added: " + added);
		
	}
	
	@PutMapping("/update/enrollee")
	public @ResponseBody String updateEnrollee(@RequestBody Enrollee updateEnrollee) {
		
		// check if enrollee exists, then update them
		
		Optional<Enrollee> found = enrollee_Service.findById(updateEnrollee.getEnrollee_id());
		
		if(found.isPresent()) {
			enrollee_Service.save(updateEnrollee);
			return "Saved: " + updateEnrollee.toString();
		}
		else {
			return "Could not update enrollee, the id = " + updateEnrollee.getEnrollee_id() + " doesn't exist";
		}
		
	}
	
	@DeleteMapping("/delete/enrollee/{id}")
	public ResponseEntity<String> deleteEnrollee(@PathVariable long enrollee_id) {
		
		Optional<Enrollee> found = enrollee_Service.findById(enrollee_id);
		
		if(found.isPresent()) {
			
			enrollee_Service.deleteById(enrollee_id);
			
			return ResponseEntity.status(200).body("Deleted Enrollee with id = " + enrollee_id);	
		}
		else {
			return ResponseEntity.status(400)
					.header("Enrollee id", enrollee_id + "")
					.body("Enrollee with id = " + enrollee_id + " not found");
		}
			
	}
		
}
