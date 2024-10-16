# SELENIUM DEMO

## Overview
This project demonstrates the use of Selenium WebDriver to automate the login flow in a web application. It serves as a practical example of how to set up and run automated tests for web interfaces using Java and Selenium.

## Prerequisites
Before you begin, ensure you have the following installed:
- Maven 3.11 or later
- JDK 17
- Chrome browser (latest version)
- ChromeDriver (compatible with your Chrome version)

## Application properties
At resource folder please define which driver (options are : chrome,edge,firefox) which server (options: windows and linux) and location of the driver on your machine.

## Project Structure
- Utilizes Page Object Model (POM) design pattern 
- Maven for dependency management and build automation 
- TestNG for test execution and assertions
- WebDriver manager to manage multiple browser
- Cucumber as BDD Framework for more readable test case

selenium-demo/ 
│ ├── src/ 
│ ├── main/ 
│ │ └── java/ 
│ │ └── (main source files) 
│ │ 
│ └── test/ 
│ └── java/ 
│ └── (test source files) 
│ ├── target/ 
│ └── (compiled classes and test reports) 
│ ├── pom.xml 
├── Dockerfile 
└── README.md


## Setup and Execution 

### Method 1: Using Maven 
1. Clone the repository:
2. Resolve dependencies: mvn dependency:resolve
3. Run Test : mvn test 
**notes** Due to DBS extension, random pop up may appear during succesfull login. You need to click OK manually to close the extension.
4. Check Test result The test reports will be available in the `target` directory of your current location. 

### Method 2: Using Docker (Unfinished) 
If you prefer to run the tests in a containerized environment: 
1. Build the Docker image: docker build -t selenium-demo
2. Run container : docker run --name selenium-test selenium-demo
3. Copy the test report to local directory : docker cp selenium-test:/usr/src/app/target ./target

The test reports will be available in the `target` directory of your current location. 

## Test Reports
After running the tests, you can find the following reports: 
- TestNG HTML report: `target/surefire-reports/index.html` 

## Customization
- To modify the test scenarios, edit the test files in `src/test/java/`. 
- To add or modify dependencies, update the `pom.xml` file.

## Contact
Yohan Sutanto





