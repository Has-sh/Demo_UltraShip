package com.example.Skill_Test_SD_Muhammad_Hashaam_Shahid.Controller;

import com.example.Skill_Test_SD_Muhammad_Hashaam_Shahid.Model.Credentials;
import com.example.Skill_Test_SD_Muhammad_Hashaam_Shahid.Model.Employee;
import com.example.Skill_Test_SD_Muhammad_Hashaam_Shahid.Model.Role;
import com.example.Skill_Test_SD_Muhammad_Hashaam_Shahid.Service.CredentialsService;
import com.example.Skill_Test_SD_Muhammad_Hashaam_Shahid.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;

import java.security.SecureRandom;


@Controller
public class CredentialsController {

    @Autowired
    private CredentialsService credentialsService;

    @Autowired
    private EmployeeService employeeService;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2B, 12, new SecureRandom());

    @PreAuthorize("permitAll()")
    @MutationMapping
    public Credentials createEmployeeAndCredentials(
            @Argument String name,
            @Argument int age,
            @Argument String employeeClass,
            @Argument String subjects,
            @Argument String attendance,
            @Argument Role role,
            @Argument String username,
            @Argument String password) {

        Employee employee = new Employee();
        employee.setName(name);
        employee.setAge(age);
        employee.setEmployeeClass(employeeClass);
        employee.setSubjects(subjects);
        employee.setAttendance(attendance);
        employee.setRole(role);

        employeeService.saveEmployee(employee);

        String encodedPassword = encoder.encode(password);

        Credentials credentials = new Credentials();
        credentials.setUsername(username);
        credentials.setPassword(encodedPassword);
        credentials.setEmployee(employee);

        credentialsService.saveCredentials(credentials);

        return credentials;
    }

    @PreAuthorize("permitAll()")
    @MutationMapping
    public String login(@Argument String username, @Argument String password) {

        Credentials credentials = new Credentials();
        credentials.setUsername(username);
        credentials.setPassword(password);

        return credentialsService.verifyCredentials(credentials);
    }
}