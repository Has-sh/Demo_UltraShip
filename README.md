# Employee Management Demo

This project is a demo for an **Employee Management System** built with **Spring Boot** and **GraphQL**. It showcases how to handle employee creation, secure credentials management, and provide authentication using JWT (JSON Web Tokens).

## Features:
- **Employee Creation**: Create employee profiles with details like name, age, class, subjects, and attendance.
- **Authentication**: User authentication using username and password with JWT token generation.
- **Credential Management**: Secure password storage using **bcrypt** hashing for employee login.
- **GraphQL**: The application uses GraphQL for querying and mutating data.
- **JWT Authentication**: Secure access to protected resources via JWT tokens.
  
## Exposed Endpoints: Here are the instructions on how to set up, run, and test the application, as well as the features and API details.

## Dependencies
Before you can run the project, make sure you have the following installed:

- **Java 23 or higher**
- **Maven** (for building and managing dependencies)
- **Spring Boot** (for backend development)
- **GraphQL** (for API communication)
- **H2 Database** (in-memory database for development)
  
## Installation and Setup

1. **Clone the repository**:
    ```bash
    git clone https://github.com/Has-sh/Demo_UltraShip.git
    ```

2. **Navigate to the project directory**:
    ```bash
    cd Skill-Test-SD-Muhammad-Hashaam-Shahid
    ```

3. **Build the project**:
    ```bash
    mvn clean install
    ```

4. **Run the application**:
    ```bash
    mvn spring-boot:run
    ```

   The application will start on `http://localhost:8080`.

5. **Access the H2 Database Console**:
   - Go to `http://localhost:8080/h2-console`
   - Default JDBC URL: `jdbc:h2:~/test`
   - Username: `sa`
   - Password: `password`

## API Endpoints

### Role-Based Access Control (RBAC)

This project enforces access control based on user roles, restricting specific actions and queries according to the user’s assigned role. Below is a summary of the operations accessible to each role.

---

#### Roles and Access Levels

1. **Admin** (`ADMIN` Role):
   - **Full Access**: Admins have unrestricted access to all mutations, queries, and management operations, including employee creation, updates, and other administrative actions.
   - **Example Operations Available to Admin**:
     - `createEmployeeAndCredentials`
     - `updateEmployee`
     - `getEmployee`
     - `getAllEmployees`

2. **Employee** (`EMPLOYEE` Role):
   - **Restricted Access**: Employees can view their own data but are restricted from accessing or modifying other employees' information.
   - **Accessible Operations**:
     - `getEmployee` (limited to their own data based on `id` validation)
     - Specific queries based on their `employeeId` matching the authenticated user.

3. **Unauthenticated Access**:
   - **Limited to Specific Operations**: Only certain mutations or endpoints are accessible without authentication.
   - **Public Operations**:
     - `createEmployeeAndCredentials`: Allows creating a new employee and credentials without requiring existing authentication.
     - `login`: Allows a user to login and get the JWT token for further access to authenication based endpoints
     - **Note**: Other mutations and queries are restricted for unauthenticated users.


### GraphQL Mutations

#### 1. **Create Employee and Credentials (No Authentication Required)**
- **Mutation Name**: `createEmployeeAndCredentials`
- **Arguments**:
  - `name`: String (Employee name)
  - `age`: Integer (Employee age)
  - `employeeClass`: String (Employee class)
  - `subjects`: String (Employee subjects optional)
  - `attendance`: String (Employee attendance optional)
  - `role`: Role (Employee role)
  - `username`: String (Employee username)
  - `password`: String (Employee password)
- **Response**:
  - Returns the created employee's credentials (username and encoded password).
  
Example:
```graphql
mutation {
  createEmployeeAndCredentials(
    name: "Admin",
    age: 18,
    employeeClass: "A",
    subjects: "Computer Science",
    attendance: "Present",
    role: ADMIN,
    username: "username",
    password: "password"
  ) {
    username
    password
  }
}
```

#### 2. **Login (No Authentication Required)**
- **Mutation Name**: `login`
- **Arguments**:
  - `username`: String (Employee username)
  - `password`: String (Employee password)
- **Response**:
  - Returns a JWT token if the credentials are valid.
  - JWT token can be passed in the headers of the further requests for authorization
  
Example:
```graphql
mutation {
  login(username: "username", password: "password")
}
```


#### 3. **Update Employee based on ID(Authentication Required)**
- **Mutation Name**: `updateEmployee`
- **Arguments**: (
  - `id`: Integer (The unique ID of the employee to be updated)
  - `name`: String (New name for the employee, optional)
  - `age`: Integer (New age for the employee, optional)
  - `employeeClass`: String (Updated employee class, optional)
  - `subjects`: String (Updated subjects for the employee, optional)
  - `attendance`: String (Updated attendance status, optional)
- **Response**:
  - Returns the updated `Employee` object with all updated fields.

Example:
```graphql
mutation {
  updateEmployee(
    id: 1,
    name: "Admin",
    age: 28,
    employeeClass: "A",
    subjects: "History, English",
    attendance: "Absent"
  ) {
    id
    name
    age
    employeeClass
    subjects
    attendance
  }
}
```

### GraphQL Queuries

#### **Get Employee (Authentication Required and logged in employees can only access there own details)**
- **Query Name**: `getEmployee`
- **Arguments**:
  - `id`: Integer (The unique ID of the employee to retrieve)
- **Response**:
  - Returns the `Employee` object, including details like `id`, `name`, `age`, `employeeClass`, `subjects`, `attendance`, and `role`.

**Example**:
```graphql
query {
  getEmployee(id: 1) {
    id
    name
    age
    employeeClass
    subjects
    attendance
    role
  }
}
```
#### **Get Employees by Filter (Admin Authentication Required)**
- **Query Name**: `getFilteredEmployees`
- **Arguments**:
  - `page`: Integer (The page number to retrieve, must be greater than 0)
  - `size`: Integer (The number of employees per page, must be greater than 0)
  - `sort`: String (The field by which to sort employees, should match a field in the Employee)
  - `name`: String (optional)
  - `age`: Integer (optional)
  - `employeeClass`: String (optional)
  - `subjects`: String (optional)
  - `attendance`: String (optional)
- **Response**:
  - Returns a list of `Employee` objects, with each entry including details such as `name`, `age`, `employeeClass`, `subjects`, `attendance`, and `role`.

**Example**
```
query {
  getFilteredEmployees(
    role: EMPLOYEE,
    page: 1,
    size: 5,
    sort: "name"
  ) {
    employees {
      name
    }
  }
}
```
#### **Get All Employees (Admin Authentication Required)**
- **Query Name**: `getAllEmployees`
- **Description**: Allows an admin to retrieve a list of all employees in the system.
- **Arguments**:
- `page`: Integer (The page number to retrieve, must be greater than 0)
- `size`: Integer (The number of employees per page, must be greater than 0)
- `sort`: String (The field by which to sort employees, should match a field in the Employee object, such as id, name, etc.)
- **Response**:
  - Returns a list of `Employee` objects, with each entry including details such as `id`, `name`, `age`, `employeeClass`, `subjects`, `attendance`, and `role`.

**Example**:
```graphql
query {
  getAllEmployees(page: 1, size: 1, sort: "id") {
    employees {
      id
      name
      age
      employeeClass
      subjects
      attendance
      role
    }
    totalPages
    totalEmployees
  }
}
```

## Testing

To test the functionality of the Employee Management System, you can use the following test cases:

### Test Case 1: Create Employee and Credentials
- **Objective**: Verify that an employee can be created successfully.
- **Steps**:
  1. Send a `createEmployeeAndCredentials` mutation request with valid data.
  2. Verify that the employee is created successfully in the database.
  3. Check the response to confirm the employee's username is returned.

### Test Case 2: Login with Correct Credentials
- **Objective**: Verify that login works with valid credentials.
- **Steps**:
  1. Send a `login` mutation with correct credentials.
  2. Verify that a JWT token is returned in the response.

### Test Case 3: Login with Incorrect Password
- **Objective**: Verify that login fails with incorrect credentials.
- **Steps**:
  1. Send a `login` mutation with incorrect credentials.
  2. Verify that the response contains an authentication error message.

### Test Case 4: Unauthorized Access to Protected Endpoint
- **Objective**: Verify that unauthorized access is denied.
- **Steps**:
  1. Send a request to a protected endpoint without a valid JWT token.
  2. Ensure that the response returns an error indicating unauthorized access.

### Test Case 5: JWT Token Expiration
- **Objective**: Verify that an expired JWT token is rejected.
- **Steps**:
  1. Wait for the JWT token to expire.
  2. Send a request with the expired token.
  3. Ensure that the response contains an expiration error.

## Additional Information

- **Password Storage**: Passwords are stored securely in the database using **BCrypt** hashing.
- **JWT Token**: The JWT token is used for authentication, and it is returned after a successful login.
  
## Conclusion

This Employee Management System allows administrators and users to manage employee data securely with authentication and role-based access control. It leverages **Spring Boot** for backend development and **GraphQL** for flexible querying. The application uses **BCrypt** for secure password hashing and **JWT** for secure authentication.

For further improvements:
  - Normalize the database (creating separate table for subjects and attendence)
  - Creating refresh token
  - Security improvement against csrf and xss attacks
  - Some edge case handling
    
## Technologies Used:
- **Spring Boot**: Backend framework for building the application.
- **GraphQL**: API query language for handling data.
- **JWT**: For handling authentication and securing the API.
- **BCrypt**: For securely hashing and storing passwords.
- **H2 Database**: Lightweight in-memory database for storing employee data.


