# Image Generation

An application to generate an image in which each color occurs only once.

## Description

In the applicattion, an image, of user specified size, is generated having only unique colors. The user is asked to enter the dimensions -width and height -of the image where width* height should not exceed 256x128=32768(as set for this challenge)

## Getting Started

### Prerequisites

* JDK 1.8
* Maven 3
* Postman
* IDE

### Technologies used
The tech stack includes:
* Spring Boot
* REST API
* Mockito framework for testing


### Installing

* Clone the project from the github repository to a local folder 
* Import the project to the IDE

### Executing program

* Build the project using ```mvn clean install ```
* Building can be done either from terminal in the IDE or using command prompt
* Run the application using ```mvn spring-boot:run``` or directly from the IDE "Run" after adding the required maven configurations

### Verifying the results

* The application will be available at ```localhost:{port}```
* From postman, this can be verified by hitting this endpoint and verifying the output file generated
* This curl can be used for testing 
```curl --location --request GET 'localhost:8080/api/image?width={}&height={}''```

### Test Cases

* Test cases are included for all the layers of the application
* Run those from the IDE to check the functionality of the methods

## Authors

*Ann George - annrosevarghese@gmail.com

## Version History

* 0.1
    * Initial Release
