package com.example.Skill_Test_SD_Muhammad_Hashaam_Shahid.Repository;

import com.example.Skill_Test_SD_Muhammad_Hashaam_Shahid.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
    List<Employee> findAll();
    Employee findById(int id);
}
