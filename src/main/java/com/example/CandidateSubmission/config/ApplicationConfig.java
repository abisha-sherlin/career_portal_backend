package com.example.CandidateSubmission.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

	@Value("${file.upload-dir}")
	private String uploadDir;

	public String getUploadDir() {
		return uploadDir;
	}


}

