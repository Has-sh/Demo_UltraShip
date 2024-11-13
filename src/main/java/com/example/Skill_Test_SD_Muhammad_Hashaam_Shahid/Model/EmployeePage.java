package com.example.Skill_Test_SD_Muhammad_Hashaam_Shahid.Model;

import java.util.List;

public class EmployeePage {

    private List<Employee> employees;
    private int totalPages;
    private int currentPage;
    private int totalEmployees;

    public EmployeePage(List<Employee> employees, int totalPages, int currentPage, int totalEmployees) {
        this.employees = employees;
        this.totalPages = totalPages;
        this.currentPage = currentPage;
        this.totalEmployees = totalEmployees;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalEmployees() {
        return totalEmployees;
    }

    public void setTotalEmployees(int totalEmployees) {
        this.totalEmployees = totalEmployees;
    }
}
