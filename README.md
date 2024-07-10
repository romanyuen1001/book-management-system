# Book Management System

The Book Management System provides RESTful APIs that allow you to manage a collection of books. This API provides endpoints for creating, retrieving, updating, and deleting books.

## Requirements
1. Java 21
2. Maven 
3. Spring Boot

## Steps to Setup
**1. Download the zip file or clone the repository**
```bash
git clone https://github.com/romanyuen1001/book-management-system.git
```

**2. Run the app using Maven**
```bash
mvn spring-boot:run
```
The app will start running at <http://localhost:8080>

## Explore Rest APIs

The app defines the following CRUD APIs.
  
    GET localhost:8080/books (All books, optional filter by author or published)
    
    POST localhost:8080/books (create books)

    PUT localhost:8080/books/{id} (todo)
    
    DELETE localhost:8080/books/{id} (delete book)

You can test them using Postman or any other rest client.

## Possible improvements / out of scope
It is coming, stay tuned.
