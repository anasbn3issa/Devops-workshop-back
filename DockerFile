FROM openjdk:8-jdk-alpine
EXPOSE 8080
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
# ADD target/tpAchatProject-1.0.jar tpAchatProject-1.0.jar
# ENTRYPOINT ["java", "-jar", "/tpAchatProject-1.0.jar"]