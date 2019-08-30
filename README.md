# Reviews API project
Reviews API project for Udacity java nanodegree course

# Requirements
* Java 11
* Maven

# How to run
It is required to have MySQL installed on the system, you can change the connection settings in `application.properties` inside resources


```shell script
# Compile the application
$ mvn clean package

# Run the application
# Make sure the database is running before running the application
$ java -jar target/reviews-0.0.1-SNAPSHOT.jar
```

# Test

```shell script
$ mvn test
```
