# Distributed Signup System
Deploying a Spring Boot Microservice to Docker. 4 docker conteiners: 2 with microservices 1 with data base and 1 with message queue.
## Concrete technologies/frameworks:
- Java 8
- Spring Boot 2.1.0
- Spring JDBC
- Apache Kafka
- Docker
### Communication of services:
* *Asynchronous* communication via Apache Kafka
##  Brief guide how to build Distributed Signup system
- docker-compose up --build -d
#### Functional test 
- _Must have running Distributed Signup system_ (see previous step)
- cd distributed-system-tests
- mvnw clean test
