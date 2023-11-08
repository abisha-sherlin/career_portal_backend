package com.example.CandidateSubmission.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.Optional;

import com.example.CandidateSubmission.config.ApplicationConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.CandidateSubmission.Entity.PersonalInformation;
import com.example.CandidateSubmission.Repository.CandidateSubmissionRepository;
import org.springframework.web.util.UriUtils;


@Service
public class CandidateSubmissionServiceImpl implements CandidateSubmissionService {


	@Autowired
	private CandidateSubmissionRepository candidateSubmissionRepository;
	@Autowired
	private ApplicationConfig applicationConfig;



	private static final Logger logger = LoggerFactory.getLogger(CandidateSubmissionServiceImpl.class);


	@Override
	public ResponseEntity<List<PersonalInformation>> getListOfCandidates() {
		try {
			return new ResponseEntity<>(candidateSubmissionRepository.findAll(), HttpStatus.OK);

		} catch (Exception e) {
			logger.info("Something went wrong..");

			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@Override
	public ResponseEntity<PersonalInformation> saveDetails(PersonalInformation information) {

		try {
			PersonalInformation savedInformation = PersonalInformation.builder()
					.firstName(information.getFirstName())
					.lastName(information.getLastName())
					.website(information.getWebsite())
					.dob(information.getDob())
					.email(information.getEmail())
					.phoneNumber(information.getPhoneNumber())
					.homePhone(information.getHomePhone())
					.cellPhone(information.getCellPhone())
					.workPhone(information.getWorkPhone())
					.address(information.getAddress())
					.city(information.getCity())
					.state(information.getState())
					.postalCode(information.getPostalCode())
					.bestTimeToCall(information.getBestTimeToCall())
					.jobTitle(information.getJobTitle())
					.yearsOfExperience(information.getYearsOfExperience())
					.currentCtc(information.getCurrentCtc())
					.expectedCtc(information.getExpectedCtc())
					.employmentType(information.getEmploymentType())
					.currentOrganization(information.getCurrentOrganization())
					.screeningStatus(information.getScreeningStatus())
					.currentStatusUpdateDate(information.getCurrentStatusUpdateDate())
					.followUpDate(information.getFollowUpDate())
					.source(information.getSource())
					.keySkills(information.getKeySkills())
					.comments(information.getComments())
					.submissionType(information.getSubmissionType())
					.build();

			return new ResponseEntity<PersonalInformation>(candidateSubmissionRepository.save(savedInformation), HttpStatus.CREATED);
		} catch (Exception e) {
			logger.info("Error while saving candidate details");
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	public  ResponseEntity<String> updateCandidateResume(Long id, MultipartFile file) throws Exception {

		Optional<PersonalInformation> optionalCandidate = candidateSubmissionRepository.findById(id);
		if (!optionalCandidate.isPresent()) {
			throw new Exception("Candidate with ID " + id + " not found.");
		}
		PersonalInformation candidate = optionalCandidate.get();

		try {
			String uniqueIdentifier = UUID.randomUUID().toString();
			String originalFilename = file.getOriginalFilename();
			String fileExtension = "";
			if (originalFilename != null && originalFilename.contains(".")) {
				fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
			}
			String newFilename = uniqueIdentifier + fileExtension;
			String fileName = applicationConfig.getUploadDir() + newFilename;
			Files.write(Paths.get(fileName), file.getBytes());

			candidate.setFileName(newFilename);

			// Save the updated candidate object to the database
			candidateSubmissionRepository.save(candidate);
			String successMessage = "file uploaded successfully";


			return ResponseEntity.ok(successMessage);


		} catch (IOException e) {
			logger.info("Error while uploading file");

			throw new Exception("An error occurred while processing the file.", e);
		}
	}

	@Override
	public Resource loadFile(String filename) throws FileNotFoundException {
		try {
			String sanitizedFilename = UriUtils.encodePath(filename, "UTF-8");
			File file = new File(applicationConfig.getUploadDir(), sanitizedFilename);

			if (file.exists()) {
				return new FileSystemResource(file);
			} else {
				throw new FileNotFoundException("File not found or other error occurred.");
			}

		} catch (Exception e) {
			logger.error("Error while loading file: {}", e.getMessage(), e);
			throw new FileNotFoundException("File not found.");
		}
	}
	}



