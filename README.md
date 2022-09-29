### Az-BookStore-Service

#### Requirements:
* REST API for an online bookstore, where the user can perform the following operations:
  - CRUD operations on Books.
  - Checkout operation for single or multiple books which will return the total payable amount. 
* Book object should have the following attributes:
  - Name
  - Description
  - Author
  - Type/Classification
  - Price
  - ISBN
* Checkout method will take list of books as parameters plus optional promotion code and return total price after discount (if applicable).
  Promotion/Discounts is variant according to book type/classification, ex: fiction books may have 10% discount while comic books have 0% discount.
#### Deliverables:
* Unit tests for all operations
* Ability to run the application locally
* Ability to run the application on a docker container
* Please upload the source code into github

#### Technologies:
- Java8
- Spring Boot
- Spring Data JPA
- H2 DB
- Spring Doc open API (Swagger-3)
- Gradle project
- JUnit 5
- JaCoCo plugin
- Docker
- Sonar plugin

#### How to run
##### Run as a Docker
###### Build Docker image
* Run below command:
```
./gradlew bootBuildImage --imageName=az-bookstore-service
```
###### Run Docker image
```
docker run -p 8080:8080 az-bookstore-service
```
###### Run without Docker
```
./gradlew clean build bootRun
```
###### Code coverage report
* Make sure SonarQube server up and running or execute below command
```
docker run -d --name sonarqube -e SONAR_ES_BOOTSTRAP_CHECKS_DISABLE=true -p 9000:9000 sonarqube:6.7.5
```
* Please validate [SonarQube server up](http://localhost:9000/)
* Default username/password admin/admin
* Please run below command to check SonarQube report
```
./gradlew clean build sonarqube
```

#### How to use:
* For all the endpoints `API_ACCESS_KEY = $2a$12$W/lAX/yFLzh7DDxGIPm9vup4E.uB0h1l.B1aJjQzwwkdoM/FTO2vK` is required in the header.
* `BookController` have all the required endpoint.
* Ideally we should have dynamics BookTypes and Promotions functionality but WRT time I limit the scope. So I have created predefined promotions for some BookTypes
  - Promotion-1: `promotionCode = ABCD1234; bookType = CRIME; discount = 10%;`
  - Promotion-1: `promotionCode = ABCD1234; bookType = FICTION; discount = 5%;`
* Run application and then you can use [Swagger url](http://localhost:8080/swagger-ui/index.html)
* In case you need to check DB [H2 Console](http://localhost:8080/h2-console)


#### Plus points:
* I have used `HeaderInterceptor` to make API secure.
* Proper Exception handling and giving `ErrorMessage` object in the response.
* Code Coverage `100%`
* Vulnerability free and Clean code. Please have a look on `Sonar Qube` reports 
* TDD reporting with the help of `JaCoCo`.
* `SonarQube` plugin also configured for Code quality, coverage , vulnerabilities and many more.
* `Docker` Image, so we can easily build, ship and deploy.
* `Swagger-UI`, Spring doc open API configured so that we can use swagger-UI for testing purpose.

#### Useful screenshot:
![Swagger-UI](/doc/Swagger-UI.png)
![Sonar Report](/doc/sonarqube1.png)
![Sonar Report2](/doc/sonarqube2.png)
![JaCoCo Report](/doc/JaCoCoReport.png)