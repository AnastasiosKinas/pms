# Post Management System

Post Management System is a Back end Rest Service where it gets Posts for various things like as Food or Travels, the
Post gets a photograph a bigText and also comments and provide them as a REST API in JSON format. You see a lot of new
Post based on real facts, and you can contribute with your opinion if you have one, according to the post.

The app is implemented with Java 11 and Spring Boot 2. It uses Hibernate, Maven, Spring Security and JWT for security
purposes. It also uses MySQL to save the data on the server. The app provides a Rest API to be used for front-end.

Before we run the app run, users create the following directory

```
C://storage
```

in order to save and retrieve the photos of the posts.

The API Documentation can be found at:

```
./API_Documentation.pdf
```

To clone and start the app, just run:

```shell
git clone https://github.com/AnastasiosKinas/pms.git
cd pms
./mvnw spring-boot:run
```

The application can be accessed on:

http://localhost:8081
