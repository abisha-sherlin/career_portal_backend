package com.example.CandidateSubmission.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.CandidateSubmission.Entity.PersonalInformation;

public interface CandidateSubmissionRepository extends JpaRepository<PersonalInformation,Long> {
}
