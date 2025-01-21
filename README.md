
# Automation_Java_Jenkins_Docker_Intellij

This project demonstrates the integration of Java-based automation testing with Jenkins and Docker, utilizing IntelliJ IDEA as the development environment.

## Prerequisites

- **Java Development Kit (JDK)**: Ensure that JDK is installed and properly configured.
- **IntelliJ IDEA**: Recommended for development and project management.
- **Maven**: For project build and dependency management.
- **Jenkins**: For continuous integration and continuous deployment (CI/CD) pipelines.
- **Docker**: To containerize the application for consistent environments across different stages.

## Setup Instructions

1. **Clone the Repository**:

   ```bash
   git clone https://github.com/chopraji94/Automation_Java_Jenkins_Docker_Intellij.git
   ```

2. **Import the Project into IntelliJ IDEA**:

   - Open IntelliJ IDEA.
   - Navigate to `File` > `Open` and select the cloned repository folder.
   - IntelliJ will automatically detect and import the Maven project.

3. **Build the Project with Maven**:

   - Open the terminal in IntelliJ IDEA.
   - Run the following command to compile the project and resolve dependencies:

     ```bash
     mvn clean install
     ```

4. **Configure Jenkins**:

   - Install Jenkins on your local machine or use an existing Jenkins server.
   - Install necessary plugins, such as the Maven Integration plugin.
   - Create a new Jenkins job and configure it to pull the code from this repository.
   - Set up build triggers, build steps, and post-build actions as required.

5. **Set Up Docker**:

   - Install Docker on your system.
   - Use this command from directory where docker file is peresent to up image and container to run testcases through docker
     
      ```bash
     docker compose up
     ```
   - Intially code is commented to seto platform to LINUX as docker by default gives linux platform to run code. Just uncomment the code in Baseclass to run testcases through dcoker.

## Running Tests

- To execute tests locally, use the following Maven command:

  ```bash
  mvn test
  ```

- Jenkins can be configured to run tests automatically upon each build.

## Contributing

Contributions are welcome! Please fork the repository and submit a pull request for any enhancements or bug fixes.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Contact

For any questions or inquiries, please contact [chopraji94](https://github.com/chopraji94).
Mail me at -: chopraji94@gamil.com
