# Distributed Signup System
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