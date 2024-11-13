package com.example.Skill_Test_SD_Muhammad_Hashaam_Shahid.Service;

import com.example.Skill_Test_SD_Muhammad_Hashaam_Shahid.Model.Credentials;
import com.example.Skill_Test_SD_Muhammad_Hashaam_Shahid.Repository.CredentialsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class CredentialsService {

    @Autowired
    private CredentialsRepo credentialsRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    public void saveCredentials(Credentials credentials) {
        credentialsRepository.save(credentials);
    }

    public String verifyCredentials(Credentials credentials) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword()));
        if (authentication.isAuthenticated()) {
            System.out.println(jwtService.generateToken(credentials.getUsername()));
            return jwtService.generateToken(credentials.getUsername());
        }
        else {
            throw new BadCredentialsException("Bad credentials");
        }
    }

//    public String verifyCredentials(Credentials credentials) {
//        try {
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword())
//            );
//
//            if (authentication.isAuthenticated()) {
//                // Generate JWT token if authentication is successful
//                return jwtService.generateToken(credentials.getUsername());
//            }
//
//        } catch (BadCredentialsException e) {
//            return "Invalid credentials";
//        }
//
//        return "Authentication failed";
//    }
}
