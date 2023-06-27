# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.1.0/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.1.0/maven-plugin/reference/html/#build-image)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/3.1.0/reference/htmlsingle/#using.devtools)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.1.0/reference/htmlsingle/#web)
* [Spring REST Docs](https://docs.spring.io/spring-restdocs/docs/current/reference/html5/)
* [Spring Configuration Processor](https://docs.spring.io/spring-boot/docs/3.1.0/reference/htmlsingle/#appendix.configuration-metadata.annotation-processor)
* [Jersey](https://docs.spring.io/spring-boot/docs/3.1.0/reference/htmlsingle/#web.servlet.jersey)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)


# ENDPOINTS
## CREATE:
POST localhost:8080/todo/create
{
  "task":"hello world",
  "priority":"high"
}

## READ:
GET localhost:8080/todo/read

## UPDATE:
PATCH localhost:8080/todo/update?id=2
{
  "task":"hello world",
  "priority":"hight"
}

## DELETE:
DELETE localhost:8080/todo/delete/{id}
eg localhost:8080/todo/delete/108

# Table Structure
task_id task priority