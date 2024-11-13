package com.example.Skill_Test_SD_Muhammad_Hashaam_Shahid.Controller;

import com.example.Skill_Test_SD_Muhammad_Hashaam_Shahid.Model.Employee;
import com.example.Skill_Test_SD_Muhammad_Hashaam_Shahid.Model.EmployeePage;
import com.example.Skill_Test_SD_Muhammad_Hashaam_Shahid.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PreAuthorize("hasRole('ADMIN')")
    @QueryMapping
    public EmployeePage getAllEmployees(@Argument Integer page, @Argument Integer size, @Argument String sort) {
        try {
            return employeeService.getEmployees(page, size, sort);
        }
        catch (IllegalArgumentException e) {
            e.printStackTrace();
            return null;
        }
    }

    @PreAuthorize("hasRole('ADMIN') or (authentication.principal.employeeId == #id and hasRole('EMPLOYEE'))")
    @QueryMapping
    public Employee getEmployee(@Argument Integer id){
        return employeeService.getEmployee(id);
    }

    @PreAuthorize("hasRole('ADMIN') or (authentication.principal.employeeId == #id and hasRole('EMPLOYEE'))")
    @MutationMapping
    public Employee updateEmployee(
            @Argument Integer id,
            @Argument String name,
            @Argument Integer age,
            @Argument String employeeClass,
            @Argument String subjects,
            @Argument String attendance){

        return employeeService.updateEmployee(id, name, age, employeeClass, subjects, attendance);
    }
}
