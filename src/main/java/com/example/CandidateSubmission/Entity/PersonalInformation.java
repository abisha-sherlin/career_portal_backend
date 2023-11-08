package com.example.CandidateSubmission.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name="personal_information")
public class PersonalInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nameId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String website;

    @Column(nullable = false)
    private Date dob;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false,unique = true)
    private String phoneNumber;

    @Column(nullable = false)
    private String homePhone;

    @Column(nullable = false)
    private String cellPhone;

    @Column(nullable = false)
    private String workPhone;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String postalCode;
    
    @Column(nullable = false)
    private String bestTimeToCall;

    @Column(nullable = false)
    private String jobTitle;

    @Column(nullable = false)
    private Integer yearsOfExperience;

    @Column(nullable = false)
    private Double currentCtc;

    @Column(nullable = false)
    private Double expectedCtc;

    @Column(nullable = false)
    private String employmentType;

    @Column(nullable = false)
    private String currentOrganization;

    @Column(nullable = false)
    private String screeningStatus;

    @Column(nullable = false)
    private Date currentStatusUpdateDate;

    @Column(nullable = false)
    private Date followUpDate;

    @Column(nullable = false)
    private String source;

    @Column(nullable = false)
    private String keySkills;

    @Column(nullable = false)
    private String comments;

    private String fileName;

    @Column(nullable = false)
    private String submissionType;

}
