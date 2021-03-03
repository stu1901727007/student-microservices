FROM openjdk:8-jdk-alpine
#FROM openjdk:13-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
#ARG JAR_FILE=gateway-service/target/*.jar
ARG JAR_FILE
#ARG SERVER_PORT
#ENV SERVER_PORT=$SERVER_PORT
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]