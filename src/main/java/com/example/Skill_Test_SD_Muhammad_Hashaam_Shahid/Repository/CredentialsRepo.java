package com.example.Skill_Test_SD_Muhammad_Hashaam_Shahid.Repository;

import com.example.Skill_Test_SD_Muhammad_Hashaam_Shahid.Model.Credentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CredentialsRepo extends JpaRepository<Credentials, Integer> {
    Credentials findByUsername(String username);
}
