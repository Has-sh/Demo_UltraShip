package com.example.Skill_Test_SD_Muhammad_Hashaam_Shahid.Service;

import com.example.Skill_Test_SD_Muhammad_Hashaam_Shahid.Model.Employee;
import com.example.Skill_Test_SD_Muhammad_Hashaam_Shahid.Model.EmployeePage;
import com.example.Skill_Test_SD_Muhammad_Hashaam_Shahid.Model.Role;
import com.example.Skill_Test_SD_Muhammad_Hashaam_Shahid.Repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepository;


    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public EmployeePage getEmployees(int page, int size, String sort) {
        page = page - 1;
        Page<Employee> employeePage = employeeRepository.findAll(PageRequest.of(page, size, Sort.by(sort)));
        return new EmployeePage(
                employeePage.getContent(),
                employeePage.getTotalPages(),
                employeePage.getNumber(),
                (int) employeePage.getTotalElements()
        );
    }

    public Employee getEmployee(int id) {
        return employeeRepository.findById(id);
    }

    public Employee updateEmployee(Integer id,
                                   String name,
                                   Integer age,
                                   String employeeClass,
                                   String subjects,
                                   String attendance) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();

            if (name != null) employee.setName(name);
            if (age >= 0) employee.setAge(age);
            if (employeeClass != null) employee.setEmployeeClass(employeeClass);
            if (subjects != null) employee.setSubjects(subjects);
            if (attendance != null) employee.setAttendance(attendance);

            return employeeRepository.save(employee);
        } else {
            return null;
        }
    }


    public EmployeePage findByFilters(
            String name,
            Integer age,
            String employeeClass,
            String subject,
            String attendance,
            Role role,
            int page,
            int size,
            String sort
    ) {
        page = page - 1;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));

        Page<Employee> employeePage = employeeRepository.findByFilters(
                name, age, employeeClass, subject, attendance, role, pageable
        );

        return new EmployeePage(
                employeePage.getContent(),
                employeePage.getTotalPages(),
                employeePage.getNumber(),
                (int) employeePage.getTotalElements());
    }

}
