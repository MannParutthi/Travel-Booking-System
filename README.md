# Team Codeblooded - Travel Booking System


## <u> Requirements </u>

This application's frontend is built using Angular and the backend is built using Spring boot.

For building and running the application you need:

- [JDK 1.7 or 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)
- [NodeJS](https://nodejs.org/en/download/)
- [Angular CLI](https://cli.angular.io/)
- [Git](https://git-scm.com)
- [IntelliJ](https://www.jetbrains.com/idea) (Optional, but recommended)


<hr/>


## <u> Clone the Repository </u>

[Link to GitHub Repository](https://github.com/MannParutthi/Travel-Booking-System)

To clone this project, run the following command in your terminal:

```
git clone https://github.com/MannParutthi/Travel-Booking-System
```

<hr/>


## <u> Running the Server (Backend: API's) </u> 

## Import the Project in IntelliJ

To import the project in IntelliJ, follow these steps:

1. Open IntelliJ IDEA.
2. Click on `File` in the top menu and select `Open`.
3. Navigate to the directory where you cloned the repository and select the folder.
4. Click `OK`.

IntelliJ will import the project and download any required dependencies.

## Run the Application

To run the application, follow these steps:

1. In the project panel, navigate to `src/main/java/com/codeblooded/travelbookingsystem/TravelbookingsystemApplication.java`.
2. Right-click on the file and select `Run TravelbookingsystemApplication.main()`.
3. Wait for the application to start. You should see a message in the console saying `Tomcat started on port(s): 8081`.


## Test the API's using Swagger (Testing during development)

Swagger / OpenAPI, is a specification and toolset used to describe and interact with RESTful web services.

With Swagger, we can use a web-based UI that allows interactation with the API endpoints and test its functionality. This UI is automatically generated based on the OpenAPI specification and provides a user-friendly interface for sending requests, inspecting responses, and debugging issues.

To access Swagger, follow these steps:

1. Open your web browser and navigate to [`http://localhost:8081/swagger-ui.html`](http://localhost:8081/swagger-ui.html).
2. You should see the Swagger UI, which provides an interactive documentation of the API developed.
3. You can explore the available endpoints and their parameters, and test them out using the Swagger UI.

<hr/>


## <u> Running the Client (Frontend) </u>

Open a terminal instance inside the folder where you cloned the repository and run the following commands:

```
cd Travel-Booking-System-Client/
```
Now run the following commands:
```
npm install
```

This installs the required dependencies for the frontend.

Now run:
```
npm run start
```

Provided you have installed all the necessary dependencies, you will be able to access the application locally at [`http://localhost:4200`](http://localhost:4200).
