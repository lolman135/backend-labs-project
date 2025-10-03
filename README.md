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
To run project locally you need docker installed on your pc with docker-compose module and sourcecode.
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
<hr>

### Conclusion
For now that's all. In the future I'll modify this file and project in all. Have a nice day