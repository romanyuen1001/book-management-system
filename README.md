# Book Management System 📚

The Book Management System provides RESTful APIs that allow you to manage a collection of books. This API provides endpoints for creating, retrieving, updating, and deleting books.

## Requirements 📂
1. Java 21
2. Maven 
3. Spring Boot

## Steps to Setup 🌐
**1. Download the zip file or clone the repository**
```bash
git clone https://github.com/romanyuen1001/book-management-system.git
```

**2. Run the app using Maven**
```bash
mvn spring-boot:run
```
The app will start running at <http://localhost:8080>.

## Explore Rest APIs 🔗
You may want to visit <http://localhost:8080/swagger-ui/index.html> after running the application.

The app defines the following CRUD APIs.
  
    GET localhost:8080/books (All books, optional filter by author or published)
    
    POST localhost:8080/books (create books)

    PUT localhost:8080/books/{id}/publish (update book published status)
    
    DELETE localhost:8080/books/{id} (delete book)

You can test them using Postman or any other rest client.

#### Visit this [published documentation in Postman](https://documenter.getpostman.com/view/5603649/2sA3e4A9V9) to see the examples with the results of the above APIs.

## Screenshots of APIs call 📷
<img width="1125" alt="image" src="https://github.com/romanyuen1001/book-management-system/assets/57783743/659fb7be-6dc6-4550-8a18-5e6a7c3b3c45">
<img width="1125" alt="image" src="https://github.com/romanyuen1001/book-management-system/assets/57783743/6905c382-0a4b-4488-a3a3-d843fdaa207c">
<img width="1125" alt="image" src="https://github.com/romanyuen1001/book-management-system/assets/57783743/e4cb3cd1-e38a-409e-91c7-34e25ff47a54">
<img width="1125" alt="image" src="https://github.com/romanyuen1001/book-management-system/assets/57783743/76e3e97e-b9e4-4be6-b2b9-06d5cbe7a32d">
<img width="1125" alt="image" src="https://github.com/romanyuen1001/book-management-system/assets/57783743/46a237ad-8b5c-4157-99d3-5b7c1eab01b0">
<img width="1125" alt="image" src="https://github.com/romanyuen1001/book-management-system/assets/57783743/d1e3314e-9355-4d78-9057-b1fd5b5c6d91">
<img width="1125" alt="image" src="https://github.com/romanyuen1001/book-management-system/assets/57783743/7b0020e9-070a-462c-b491-1c19b442265d">



## Unit tests 💨
A few cases are defined in BookServiceTest for testing validate and book service.
<img width="1261" alt="image" src="https://github.com/romanyuen1001/book-management-system/assets/57783743/060cac98-0689-4103-96cc-b6f232d09048">


  
## Possible improvements / out of scope ✨ 
It is coming, stay tuned.

## Thankyou 🤗
Thank you for taking the time to explore my project. I hope you find them informative and useful in learning Java and enhancing your programming skills.

