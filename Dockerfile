FROM openjdk:18-alpine
ADD target/RelCrud-0.0.1-SNAPSHOT.jar RelCrud-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","RelCrud-0.0.1-SNAPSHOT.jar"]