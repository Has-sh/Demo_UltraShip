package com.example.Skill_Test_SD_Muhammad_Hashaam_Shahid.Repository;

import com.example.Skill_Test_SD_Muhammad_Hashaam_Shahid.Model.Employee;
import com.example.Skill_Test_SD_Muhammad_Hashaam_Shahid.Model.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
    List<Employee> findAll();

    Employee findById(int id);

    @Query("SELECT e FROM Employee e WHERE " +
            "(:name IS NULL OR e.name = :name) AND " +
            "(:age IS NULL OR e.age = :age) AND " +
            "(:employeeClass IS NULL OR e.employeeClass = :employeeClass) AND " +
            "(:subject IS NULL OR e.subjects = :subject) AND " +
            "(:attendance IS NULL OR e.attendance = :attendance) AND " +
            "(:role IS NULL OR e.role = :role)")
    Page<Employee> findByFilters(
            @Param("name") String name,
            @Param("age") Integer age,
            @Param("employeeClass") String employeeClass,
            @Param("subject") String subject,
            @Param("attendance") String attendance,
            @Param("role") Role role,
            Pageable pageable
    );
}