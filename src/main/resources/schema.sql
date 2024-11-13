CREATE TABLE Employee (
                          id INT PRIMARY KEY AUTO_INCREMENT,
                          name VARCHAR(100) NOT NULL UNIQUE,
                          age INT NOT NULL,
                          employee_class VARCHAR(50),
                          subjects VARCHAR(255),
                          attendance VARCHAR(50),
                          role VARCHAR(20) NOT NULL
);

CREATE TABLE Credentials (
                             id INT PRIMARY KEY AUTO_INCREMENT,
                             username VARCHAR(50) NOT NULL UNIQUE,
                             password VARCHAR(500) NOT NULL,
                             employee_id INT,
                             CONSTRAINT fk_employee_credentials FOREIGN KEY (employee_id) REFERENCES Employee(id) ON DELETE CASCADE
);



