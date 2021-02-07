FROM openjdk:15-jdk-alpine
ARG JAR_FILE=./target/*.jar
COPY ${JAR_FILE} application.jar
CMD ["java", "-jar", "application.jar"]