package com.example.CandidateSubmission.Controllers;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.regex.Pattern;

@Component
public class Helper {

   
    private static final String EMAIL_PATTERN =
            "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z]{2,4})+$";
    
    private static final String URL_PATTERN =
            "^(http|https)://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?$";

    
    private static final String PHONE_NUMBER_PATTERN =
    		 "(\\+91)?[6789]\\d{9}";
    
    public boolean isValidEmail(String email) {
        return Pattern.matches(EMAIL_PATTERN, email);
    }

    public boolean isValidURL(String url) {
        return Pattern.matches(URL_PATTERN, url);
    }

    public boolean isValidPhoneNumber(String phoneNumber) {
        return Pattern.matches(PHONE_NUMBER_PATTERN, phoneNumber);
    }
    
    public boolean isNullOrBlank(String input) {
        return input == null || input.trim().isEmpty();
    }

}
