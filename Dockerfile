FROM maven:3.8.6-openjdk-18-slim AS build
WORKDIR /RelCrud
COPY . .
RUN mvn clean install
CMD mvn spring-boot:run