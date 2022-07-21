# Learning project

## Spring Boot JPA CRUD Rest Api

Mysql needed properties are set in application.properties file
* spring.datasource.username & spring.datasource.password properties are the same as your database installation.
* spring.jpa.hibernate.ddl-auto is used for database initialization. We set the value to update value so that a table will be created in the database automatically corresponding to defined data model.


Extension of JPARepository lets us use:
save(), findOne(), findById()... without implementing them

