package com.example.Skill_Test_SD_Muhammad_Hashaam_Shahid.Service;

import com.example.Skill_Test_SD_Muhammad_Hashaam_Shahid.Model.Credentials;
import com.example.Skill_Test_SD_Muhammad_Hashaam_Shahid.Model.UserPrincipal;
import com.example.Skill_Test_SD_Muhammad_Hashaam_Shahid.Repository.CredentialsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private CredentialsRepo credentialsRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Credentials user = credentialsRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new UserPrincipal(user);
    }
}
