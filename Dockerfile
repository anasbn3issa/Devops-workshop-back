FROM openjdk:8
EXPOSE 8080
ADD Artifact.jar tpAchatProject-1.0.jar
ENTRYPOINT ["java", "-jar", "/tpAchatProject-1.0.jar"]