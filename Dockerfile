FROM openjdk:11
ARG JAR_FILE=target/*.jar
EXPOSE 8080
COPY ${JAR_FILE} fampay-assignment.jar
ENTRYPOINT ["java","-jar","/fampay-assignment.jar"]