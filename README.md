# Getting Started

* First Pull the application from the repo: https://github.com/babsgenius2001/PolishNotationComputation.git

* Compile the whole application - SinchTechnicalAssignment by running:

```
mvn clean package
```
This will generate all the jars for modules

### Problems 1 & 2

The solutions for problems 1 and 2 can be found in the module/directory called <b>Coding-Assignments</b>

* The sum of pairs solution for Problem 1 could be found within the Problem1 class within the Coding-Assignments module. It will be run from the terminal
* The Polish Notation solution for Problem 2 could be found within the Problem2 class within the Coding-Assignments module. It will also be run from the   terminal.

### API

This contains the API implementation of the Polish Notation solution for Problem2. These guides illustrate how to test it:

After changing directory into the Code-API module, run:

```
java -jar target/code-api-1.0.SNAPSHOT.jar
```

You can also run this in the command line while being in the Code-API directory:

```
mvn spring-boot:run
```

Thereafter, test with the following URL (POST):

```
http://localhost:8080/api/evaluate
```
Kindly bear in mind while testing via Postman App that the requestbody will be a Text!
