package com.example.Skill_Test_SD_Muhammad_Hashaam_Shahid.Model;
import jakarta.persistence.*;
import org.springframework.lang.NonNull;

@Entity
@Table(name = "employee")
public class Employee {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        @NonNull
        @Column(unique = true)
        private String name;
        @NonNull
        private int age;
        @NonNull
        private String employeeClass;
        private String subjects;
        private String attendance;

        @Enumerated(EnumType.STRING)
        private Role role;

        @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
        private Credentials credentials;

        public Employee() {}

        public Employee(String name, int age, String employeeClass, String subjects, String attendance, Role role) {
                this.name = name;
                this.age = age;
                this.employeeClass = employeeClass;
                this.subjects = subjects;
                this.attendance = attendance;
                this.role = role;
        }

        public int getId() { return id; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public int getAge() { return age; }
        public void setAge(int age) { this.age = age; }

        public String getEmployeeClass() { return employeeClass; }
        public void setEmployeeClass(String employeeClass) { this.employeeClass = employeeClass; }

        public String getSubjects() { return subjects; }
        public void setSubjects(String subjects) { this.subjects = subjects; }

        public String getAttendance() { return attendance; }
        public void setAttendance(String attendance) { this.attendance = attendance; }

        public Role getRole() { return role; }
        public void setRole(Role role) { this.role = role; }

        public Credentials getCredentials() { return credentials; }
        public void setCredentials(Credentials credentials) { this.credentials = credentials; }
}