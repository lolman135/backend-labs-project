# Backend labs project
### Group: IO-31
### Student: Kyrylo Kusovskyi
<hr>
 
### Description
Hi, my name is Kyrylo, i'm a student at NTUU KPI, FICE, F7 "Computer Engineering" specialization.
This project was created for my subject "Backend". It uses Kotlin as main language and default Java stack:
Spring Framework (Spring Boot, Spring Data and Spring Security) and Hibernate ORM and Gradle as build system (Everything includes data and security
will be added soon).

### Instructions

#### • With Docker
To run project locally you need docker installed on your pc with docker-compose module and sourcecode.
You can install it [here](https://docs.docker.com/engine/install/).
<br> Go to root dir with ```compose.yaml``` file and run command:
```bash
docker-compose up
```
This command runs docker that builds app from ```Dockerfile``` and runs it on port 8080.
If you run project second and more time, and want to rebuild project because of modification sourcecode, 
run it with ```--build``` flag like this:
```bash
docker-compose up --build
```
In this way docker will rebuild container that includes application even you've built it earlier.

#### • Without Docker (Required Gradle installed):
Because of using  microservice architecture I don't recommend to run it without docker compose.
But if you want:
 - First, if you don't have, you should install Gradle: building system for java project, and JRE 21.
   Install JRE (or JDK, if you want to modify code) [here](https://www.oracle.com/ua/java/technologies/downloads/).
   Then install [Gradle](https://docs.gradle.org/current/userguide/installation.html#gs:installation).
 
 - After installation navigate to project root directory, and run each service one by one. Navigate to
   ```HeathcheckService```, ```UserService```, ```ExcpenseService``` and```ApiGatewayService```, and run command inside 
   each directory
    ```bash
    ./gradlew bootRun
    ```

#### • Check work
Project starts on localhost on port 8080, so all requests should be sent on 
```http://localhost:8080```
To check that project working send get request on ```http://localhost:8080/healthcheck```

Also, project is already deployed on Render hosting. Check workability here:<br>
https://backend-labs-project.onrender.com/healthcheck
<hr>

### Conclusion
For now that's all. In the future I'll modify this file and project in all. Have a nice day