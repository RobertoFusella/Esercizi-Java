FROM eclipse-temurin:17
ADD target/event-service-1.0.0-SNAPSHOT.jar event-service.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","event-service.jar"]