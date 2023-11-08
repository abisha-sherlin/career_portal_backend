package com.example.CandidateSubmission.Controllers;

import java.io.*;


import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;


import com.example.CandidateSubmission.Entity.PersonalInformation;
import com.example.CandidateSubmission.Service.CandidateSubmissionService;


@RestController
@RequestMapping("/candidates")
public class UserController {

	@Autowired
	private CandidateSubmissionService candidateSubmissionService;
	@Autowired
	private Helper helper;



	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@PostMapping("/saveCandidate")
	public ResponseEntity<String> saveCandidateDetails(@RequestBody PersonalInformation information) {

		try {
			logger.info("Inside save candidate details");
			if (helper.isNullOrBlank(information.getFirstName()) || helper.isNullOrBlank(information.getLastName())) {
				return ResponseEntity.badRequest().body("Firstname and lastname are required");
			}

			if (helper.isNullOrBlank(information.getEmail())) {
				return ResponseEntity.badRequest().body("Email is required");
			}
			if (helper.isNullOrBlank(information.getState()) || helper.isNullOrBlank(information.getCity()) || helper.isNullOrBlank(information.getPostalCode())) {
				return ResponseEntity.badRequest().body("Field is required");
			}
			if (helper.isNullOrBlank(information.getPhoneNumber())) {
				return ResponseEntity.badRequest().body("Phone number is required");
			}
			if (helper.isNullOrBlank(information.getAddress())) {
				return ResponseEntity.badRequest().body("Address is required");
			}
			if (helper.isNullOrBlank(information.getJobTitle())) {
				return ResponseEntity.badRequest().body("Job Title is required");
			}
			if (helper.isNullOrBlank(information.getCurrentOrganization())) {
				return ResponseEntity.badRequest().body("This field is required");
			}
			if (helper.isNullOrBlank(information.getKeySkills())) {
				return ResponseEntity.badRequest().body("This field is required");
			}
			if (information.getCurrentCtc() == null || information.getExpectedCtc() == null) {
				return ResponseEntity.badRequest().body("This field is required");
			}

			if (information.getYearsOfExperience() == null || information.getYearsOfExperience() <= 0) {
				return ResponseEntity.badRequest().body("Please enter valid format");
			}

			if (information.getCurrentCtc() <= 0.0) {
				return ResponseEntity.badRequest().body("Current CTC must be a positive value");
			}

			if (information.getExpectedCtc() <= 0.0) {
				return ResponseEntity.badRequest().body("Expected CTC must be a positive value");
			}

			if (!helper.isValidURL(information.getWebsite())) {
				return ResponseEntity.badRequest().body("Invalid website URL format");

			}
			if (!helper.isValidPhoneNumber(information.getPhoneNumber())) {
				return ResponseEntity.badRequest().body("Invalid phone number ");

			}
			if (!helper.isValidEmail(information.getEmail())) {
				return ResponseEntity.badRequest().body("Invalid Email format");
			}

			candidateSubmissionService.saveDetails(information);

			return ResponseEntity.ok("Candidate details updated successfully");

		} catch (Exception e) {
			logger.error("An error occurred while saving candidate details: {}", e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while saving candidate details");
		}
	}
	@PostMapping("/upload/{id}")
	public ResponseEntity<String> uploadResumeFile(@PathVariable Long id, @RequestParam("file") MultipartFile file) {

		try {
			logger.info("Inside upload resume file");
			String originalFilename = file.getOriginalFilename();
			if (originalFilename != null && (originalFilename.endsWith(".pdf") || originalFilename.endsWith(".docx"))|| originalFilename.endsWith("doc")) {
				ResponseEntity<String> candidate = candidateSubmissionService.updateCandidateResume(id, file);
				return candidate;
			} else {

				return ResponseEntity.badRequest().body("Invalid file format. Only PDF or DOC files are allowed.");
			}
		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.ok("An error occurred while uploading the file.");
		}

	}


	@GetMapping("/getAllCandidates")
	public ResponseEntity<List<PersonalInformation>> getAllCandidates() {
		try {
			logger.info("Inside get all candidate details");

			ResponseEntity<List<PersonalInformation>> candidates = candidateSubmissionService.getListOfCandidates();
			return candidates;

		} catch (Exception e) {
			logger.error("An error occurred while fetching candidates: " + e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		}

	}


	}

























