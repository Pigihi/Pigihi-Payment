# Payment Service

Service for handling all payment related operations

## APIs

| Functionality | REST Endpoint | Parameter | Body | Response |
| --- | --- | --- | --- | --- |
| Make Payment | **POST** `/makePayment` |     | JSON String | JSON String |
| Accept Response from Payment Gateway | **POST** `/paymentResponse` |     |     | Redirect Response |

## Configuration

Edit the properties of **application.yml** file

```yaml
# Eureka properties
eureka:
  client:
    fetch-registry: true
    register-with-eureka: true
    service-url:
      defaultZone: address of the eureka server (Eg: http://localhost:8761/eureka)
  instance:
    hostname: specify the hostname here (Eg: localhost)

# Server properties
server:
  port: port in which the payment service is to be run (Eg: 8093)

# Application properties
spring:
  application:
    name: name of the application (Eg: PAYMENT-SERVICE)
```

## Local Deployment

Service Registry should be started for successful execution of all queries.

In application.yml file, change the properties

| Property | Value | Example |
| --- | --- | --- |
| eureka_hostname | hostname of eureka server | service-registry |

### Using Docker

Create docker bridge network: `docker network create -d bridge pigihi-network`

docker-compose can be used to run the application and the corresponding mongodb instance

1. Go to project folder
2. Open terminal and run `docker-compose up`
3. The application can be accessed at localhost:8093 (port 8093 is set in docker-compose)

To run only the application

1.  Go to project folder
2.  Open terminal and run `docker build .`
3.  Run `docker run -p 8093:8093 docker_image_name`
4.  The application can be accessed at localhost:8093

### Using Gradle

MongoDB should be run seperately and the configurations should be updated in application.yml

1.  Go to project folder
2.  Open terminal and run `./gradlew build`
3.  Run `./gradlew bootRun`

* * *
