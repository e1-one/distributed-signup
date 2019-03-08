FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY . .
RUN /bin/sh -c "./mvnw clean install -DskipTests"
ENV JAVA_OPTS=""
ENTRYPOINT exec java $JAVA_OPTS -jar target/*.jar