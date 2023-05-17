# Team Codeblooded - Travel Booking System


## Requirements

This application's frontend is built using Angular and the backend is built using Spring boot.

For building and running the application you need:

- [JDK 1.7 or 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)
- [NodeJS](https://nodejs.org/en/download/)
- [Angular CLI](https://cli.angular.io/)
- [Git](https://git-scm.com)
- [IntelliJ](https://www.jetbrains.com/idea) (Optional, but recommended)


<hr/>


## Clone the Repository

[Link to GitHub Repository](https://github.com/MannParutthi/Travel-Booking-System)

To clone this project, run the following command in your terminal:

```
git clone https://github.com/MannParutthi/Travel-Booking-System
```

<hr/>


## Running the Server (Backend: API's)

### Import the Project in IntelliJ

To import the project in IntelliJ, follow these steps:

1. Open IntelliJ IDEA.
2. Click on `File` in the top menu and select `Open`.
3. Navigate to the directory where you cloned the repository and select the server folder 'Travel-Booking-System-Server/'.
4. Click `OK`.

IntelliJ will import the project and download any required dependencies.

### Run the Application

To run the application, follow these steps:

1. Open the project in IntelliJ.
2. In the project panel, navigate to `src/main/java/com/codeblooded/travelbookingsystem/TravelbookingsystemApplication.java`.
3. Right-click on the file and select `Run TravelbookingsystemApplication.main()`.
4. Wait for the application to start. You should see a message in the console saying `Tomcat started on port(s): 8081`.


### Call and Test the API's using Swagger

To access Swagger, follow these steps:

1. Open your web browser and navigate to [`http://localhost:8081/swagger-ui.html`](http://localhost:8081/swagger-ui.html).
2. You should see the Swagger UI, which provides an interactive documentation of the API developed.
3. You can explore the available endpoints and their parameters, and test them out using the Swagger UI.

<hr/>


## Running the Client (Frontend)

Navigate to the directory 'Travel-Booking-System-Client/' and run the following commands:

```
npm install
npm run start
```

This installs the required dependencies for the frontend, and if everthing works correctly, you can access the application locally at [`http://localhost:4200`](http://localhost:4200).
