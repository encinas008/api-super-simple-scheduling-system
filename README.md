# Spring Boot "API" for super-simple-scheduling-system project
This is a api for simple scheduling system where you can register students, classes and each student associate to many classes.

## File structure
````bash
  src
     ├── main
     │   ├── java
     │   │   └── com
     │   │       └── scheduling
     │   │           └── system
     │   │               ├── ApiSuperSimpleSchedulingSystemApplication.java
     │   │               ├── DAL
     │   │               │   ├── models
     │   │               │   │   ├── Class.java
     │   │               │   │   └── Student.java
     │   │               │   └── repositories
     │   │               │       ├── ClassRepository.java
     │   │               │       └── StudentRepository.java
     │   │               ├── Domain
     │   │               │   ├── dtos
     │   │               │   │   ├── ClassDto.java
     │   │               │   │   └── StudentDto.java
     │   │               │   ├── parsers
     │   │               │   │   ├── ClassParser.java
     │   │               │   │   ├── IParser.java
     │   │               │   │   └── StudentParser.java
     │   │               │   └── utils
     │   │               │       └── Convert.java
     │   │               ├── Rest
     │   │               │   ├── controllers
     │   │               │   │   ├── ClassController.java
     │   │               │   │   └── StudentController.java
     │   │               │   └── handlerExceptionHandler
     │   │               │       ├── HandlerExceptionController.java
     │   │               │       └── ResponseException.java
     │   │               └── Service
     │   │                   ├── ClassService.java
     │   │                   └── StudentService.java
     │   └── resources
     │       └── application.properties
     └── test
         └── java
             └── com
                 └── scheduling
                     └── system
                         └── ApiSuperSimpleSchedulingSystemApplicationTests.java
 
 ````

## How to Run 

* Clone this repository 
* Make sure you are using JDK 1.8 and Gradle 4.X
* You can build the project by running on console into the project/folder api-super-simple-scheduling-system```$ gradle clean bootJar```
* Once successfully built, you can run the services.
```
    $ cd build/libs/

    $ java -jar api-super-simple-scheduling-system-0.0.1-SNAPSHOT.jar
````

## Endpoints on POSTMAN
you can find the next file on the project api-s4.postman_collection.json import this file on POSTMAN there are all endpoints availables on API