# BAJAJ SQL Challenge - Question 2

## Registration Number: PES1UG22EC176

### Student Information
- **Name:** Nikhil Shiraguppi
- **Email:** nikhilshiraguppi@gmail.com
- **Registration No:** PES1UG22EC176

## Problem Statement

This project solves the **second SQL question** from the BAJAJ challenge:

**Calculate the number of employees who are younger than each employee, grouped by their respective departments.**

For each employee, the solution returns the count of employees in the same department whose age is less than theirs.

### Database Schema
- **DEPARTMENT**: Contains department details (DEPARTMENTID, DEPARTMENTNAME)
- **EMPLOYEE**: Contains employee details (EMPID, FIRSTNAME, LASTNAME, DOB, GENDER, DEPARTMENT)
- **PAYMENTS**: Contains salary payment records (PAYMENTID, EMPID, AMOUNT, PAYMENTTIME)

## Solution

The SQL query implemented:
```sql
SELECT e1.EMP_ID, e1.FIRST_NAME, e1.LAST_NAME, d.DEPARTMENT_NAME,
       COUNT(e2.EMP_ID) AS YOUNGER_EMPLOYEES_COUNT
FROM EMPLOYEE e1
JOIN DEPARTMENT d ON e1.DEPARTMENT = d.DEPARTMENT_ID
LEFT JOIN EMPLOYEE e2 ON e2.DEPARTMENT = e1.DEPARTMENT AND e2.DOB > e1.DOB
GROUP BY e1.EMP_ID, e1.FIRST_NAME, e1.LAST_NAME, d.DEPARTMENT_NAME
ORDER BY e1.EMP_ID DESC;
```

## Technology Stack

- **Java 17**
- **Spring Boot 3.1.5**
- **Maven** (Build tool)
- **org.json** (JSON processing)

## Project Structure

```
BAJAJ/
├── pom.xml
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── yourname/
│                   └── bajajchallenge/
│                       └── ChallengeRunner.java
└── README.md
```

## How to Build

### Prerequisites
- Java 17 or higher
- Maven 3.6+

### Building the JAR file

1. Clone this repository:
```bash
git clone https://github.com/nikhil-gs/BAJAJ.git
cd BAJAJ
```

2. Build the project using Maven:
```bash
mvn clean package
```

3. The JAR file will be created in the `target/` directory:
```
target/bajaj-challenge-PES1UG22EC176.jar
```

## How to Run

Run the JAR file using:
```bash
java -jar target/bajaj-challenge-PES1UG22EC176.jar
```

The application will:
1. Generate a webhook by calling the BAJAJ API
2. Submit the SQL solution to the generated webhook
3. Display the response

## What the Application Does

1. **Registers with BAJAJ API**: Sends name, registration number, and email to get a webhook URL and access token
2. **Submits SQL Solution**: Sends the final SQL query to the webhook with Bearer token authentication
3. **Prints Response**: Displays the API response confirming submission

## Files Description

- **pom.xml**: Maven configuration with dependencies for Spring Boot and JSON processing
- **ChallengeRunner.java**: Main application that implements CommandLineRunner to execute the submission logic
- **README.md**: This file

## Assignment Submission

- **GitHub Repository**: https://github.com/nikhil-gs/BAJAJ
- **JAR Download Link**: [Will be available in Releases]

## Author

Nikhil Shiraguppi (PES1UG22EC176)
