# Payment Service

Service for handling all payment related operations

## APIs

| Functionality | REST Endpoint | Parameter | Response |
| --- | --- | --- | --- |
| Make Payment | **POST** `/makePayment` | JSON String | JSON String |
| Accept Response from Payment Gateway | **POST** `/paymentResponse` |     | Redirect Response |

## Configuration

Edit the properties of **Application.yml** file

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
  port: port in which the customer service is to run (Eg: 8093)

# Application properties
spring:
  application:
    name: name of the application (Eg: PAYMENT-SERVICE)
```

## Local Deployment
