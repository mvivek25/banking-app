## Banking Demo App

### Pre-Requisites

JDK 1.8+ 
Gradle 6+
Docker 19+

### Build the program using gradle
gradle clean build

### Running the program using gradle
1. Navigate to banking-app directory
2. Build the project using below command
   ####gradle clean build
2. Run the below command in terminal
   gradlew bootRun

### Creating a docker image and running the program using docker
1. Creating docker image
   docker build -t <imagename> .
   ####Ex:- docker build -t demobankingapp .
2. Start the app using below command
   docker run --name BankingApplication <imagename>
   ####Ex:- docker run --name BankingApplication demobankingapp

### Swagger Url
http://localhost:8080/mgmt/swagger-ui.html

### Actuator endpoints Url
http://localhost:8080/mgmt/info
http://localhost:8080/mgmt/env
http://localhost:8080/mgmt/health

### Sample urls to check ATMS by identification
http://localhost:8080/v1/atms/LFDCDC11
http://localhost:8080/v1/atms/LFFADC11


