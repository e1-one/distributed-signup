# Distributed Signup System
Deploying a Spring Boot Microservice to Docker. Deploying a Spring Boot Microservice to Docker. 4 docker containers: 2 with microservices 1 with database and 1 with a message queue.
## Concrete technologies/frameworks used in this project:
- Java 8
- Spring Boot 2.1.0
- Spring JDBC
- Apache Kafka
- Docker
### Communication of services:
- *Asynchronous* communication via Apache Kafka
##  Brief guide on how to build and run _Distributed Signup system_
- Install [Docker](https://www.docker.com/products/docker-desktop)
- download this repository
- execute the next command in the project folder:
``` Bash
docker-compose up --build -d
```
#### Functional test 
- Ensure you have deployed _Distributed Signup system_ (see the previous step)
- Than execute next commands:
``` Bash
cd distributed-system-tests
mvnw clean test
```
The test above will make a rest call to a service and then will check DB for an update.
