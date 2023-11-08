package com.example.CandidateSubmission.Service;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.example.CandidateSubmission.Entity.PersonalInformation;

public interface CandidateSubmissionService {

	
	ResponseEntity<List<PersonalInformation>> getListOfCandidates();

	ResponseEntity<PersonalInformation> saveDetails(PersonalInformation information);

	ResponseEntity<String> updateCandidateResume(Long id, MultipartFile file) throws Exception;


    Resource loadFile(String filename) throws Exception;
}
