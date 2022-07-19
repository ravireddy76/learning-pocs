### Introduction
This is spring boot  poc is microservice application to demonstrate how to use spring boot with kong api gateway.

### Getting Started
To setup, build and run poc application in LOCAL environment.
1.	Clone or download from github (https://github.com/ravireddy76/docker-compose)
2.	Open Project (sb-kong-poc) in IntelliJ/Eclipse
3.  For lombok plugin configuration please follow steps https://www.baeldung.com/lombok-ide
3.	Build ``` mvn clean install ```
4.	Run the application by right click  ClusterApplication class
      OR using gradle command  ``` mvn bootRun ```

### Endpoint Information
Swagger Documentation : http://localhost:8080/swagger-ui/
- Create:
```
```

### Jar file execution option 2 run application
- Open command line prompt
- Go to (cd to) project folder
- Go to target directory
- execute command  ``` java -jar sb-kong-poc.jar ```
- http://localhost:8085/swagger-ui/

### Docker Option 2 run application
- To build docker image from docker file
  ``` docker build -t sb-kong-poc .```
- To run created docker image  ``` docker run --name sb-kong-poc --network=kong-db-client_kong-net -p 8085:8085 -t sb-kong-poc ```
- http://localhost:8085/swagger-ui/

### Contribute
SWAT Engineering Team (BCBS)

### License
@CopyRight ( C ) All rights reserved to BCBS. It's Illegal to reproduce this code.
[Ravi Reddy](https://www.linkedin.com/in/ravireddy55447/)
