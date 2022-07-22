# Learning project

## Spring Boot JPA CRUD Rest Api

Mysql needed properties are set in application.properties file
* spring.datasource.username & spring.datasource.password properties are the same as your database installation.
* spring.jpa.hibernate.ddl-auto is used for database initialization. We set the value to update value so that a table will be created in the database automatically corresponding to defined data model.


Extension of JPARepository lets us use:
save(), findOne(), findById()... without implementing them

## Creation of Dockerized MySQL + Spring boot:

### In project:

Inside application.properties put needed datasource variables:

server.port=8080
spring.datasource.url=jdbc:mysql://mysql:3306/companydb?useSSL=false
spring.datasource.username=sa
spring.datasource.password=1234
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5InnoDBDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

Prepare simple Dockerfile:

(as in project)

### In docker

a) Docker build image of project

b) Create network that will incorporate project + mysql
* docker network create springmysql-net

c) Run MySQL container in network:
* docker run --name mysqldb --network springmysql-net
 -e MYSQL_ROOT_PASSWORD=X -e MYSQL_DATABASE=DB -e MYSQL_USER=user 
 -e MYSQL_PASSWORD=1234 -d mysql:5.7

* Container name is mysqldb. Note that we have given this in the connection URL in the application.properties file.
* Network is springmysql-net.
* -e stands for environment variables. These values also have mentioned in the application.properties file.

d) Run spring boot container in the same network
* docker run --network springmysql-net --name backend-container -p8080:8080 -d backend