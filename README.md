# library-management
A library management system with departments and using Spring-boot and node.js


# JAVA PROJECT::

# Introduction
A Library Management System designed to see the books available in a college library. It allows librarians/students to add/delete and view books from the college library hassle free. The backend is designed as a Monolithic Architecture with various nuances as discussed below.

# Technologies and Dependencies Used
- Maven used as a dependency management tool.
- Java version: 17 and Spring Boot version: 3.1.9 are used to build hassle free web applications and writing REST APIs.
- Mongo Repositories (Hibernate) Used to reduce the time of writing hardcoded mongo db queries and instead allows to write much more readable and scalable code.
- Mongo DB database is used as a Java persistence store.
- Project Lombok Reduces the time of writing java boiler plate code.
- JUnit 4 and Mockito are used for writing test case scenarios.
- JPA Auditing for storing Auditable data.

# Using Library Management System
CLI-->

  git clone https://github.com/vidhishah171/library-management.git
  cd library-management-system
  mvn package 
  java -jar target/library-management-system-0.0.1-SNAPSHOT.jar
  Intellij/Eclipse-->

1. Let maven resolve dependencies.
2. Run SpringBootApplication.

# Backend Design
Actors/Entities are inspired by the real world entities that can use the applications.
1. **Book** having attributes:
- Unique primary key id, title, author, publicationYear, isbn (unique key index), genre, department, availabilty.
2. **Department** used mainly for storing departments in the library:
unique primary key id, name(unique key index), description.


# Functionalities Exposed
1. Department Controller Class:
The REST APIs exposed are
- CRUD APIs for the add, delete, update, view, list departments. The register user API http://localhost:8080/library/department/add creates a department entity with name and description as requests body. 
- Another API which is exposed is to generate JWT Token for the registerd user. That is http://localhost:8080/library/department/list lists all the departments present in the library.
After creating department, we can search by name and also delete the department. And then we can create book in the department.

2. Books Controller Class:
- For the books entity we have REST APIs for Basic CRUD operations such as addBook, updateBook, getBook, deleteBookById, removeBook, findBookByTitle, findBookByAuthor, listAllBooks, listAvailableBooks using the respected parameters.
- Also, user can fetch Book by adding title, author and isbn number in the Path Variable.


# API Endpoints

Few Examples: Each example API preceeded by "http://localhost:8080/library"

/books/add --> To add Book.

/books/get/:id --> To get Book by id.

/books/list --> To fetch all books from the library

/books/update/:id --> To update book.

/books/delete/:id --> To delete book.

/books/:isbn --> To remove book by isbn.

/books/find/byTitle/:title --> To find Book by title ignore case.

/books/find/byAuthor/:author --> To find Book by author ignore case.

/books/find/list/available --> To find all the available Books.


/department/add --> To add Department.

/department/get/:id --> To get Department by id.

/department/list --> To fetch all Departments from the library

/department/update/:id --> To update Department.

/department/delete/:id --> To delete Department.

/department/:name --> To remove Department by name.

/department/find/byName/:name --> To find Department by name ignore case.

/menu --> It will return all the APIs/operations we can do with this application.


# Exception Handling

For the Library Management System Custom Books and Department Exceptions are created and all the Exceptions and Http response codes are handled in the GlobalExceptionHandler class such as:

- Internal Server Exceptions
- Authentication Exception
- NoHandlerFoundException, MethodNotAllowed Exception
- MethodArgumentNotValidException etc.



# NODE PROJECT::

# Introduction
A Library Management System with LeaderBoards designed to see the Popular Books and Departments Daily, Monthly and Weekly.
Rankings are based on the downloads of the books.

# Technologies and Dependencies Used
- Node version: 16.14.0 and Express.js are used to build hassle free web applications and writing REST APIs.
- Mongo Repositories Used to reduce the time of writing hardcoded mongo db queries and instead allows to write much more readable and scalable code.
- Mongo DB database and Mongoose are used for Database queries.

# Using Library Management System
CLI-->

  git clone https://github.com/vidhishah171/library-management.git
  cd library-management-system
  extra node-services.zip
  cd node-services
  npm install
  npm start
  Intellij/VS-Code-->

1. Let maven resolve dependencies.
2. Run SpringBootApplication.

# Backend Design
Actors/Entities are inspired by the real world entities that can use the applications.
1. **Book** having attributes:
- Unique primary key id, title, author, publicationYear, isbn (unique key index), genre, department, availabilty.
2. **Department** used mainly for storing departments in the library:
unique primary key id, name(unique key index), description.
3. **BookDownload** used mainly for storing books downloads in the library:
unique primary key id, bookId, bookTitle, departmentId, departmentName.
4. **LeaderBoard** used mainly for storing LeaderBoards in the library:
unique primary key id, type, popularity and entities and lastWeekWinner.


# Functionalities Exposed
1. Download Controller Class:
The REST APIs exposed are
- CRUD APIs for the downloading books. The register user API http://localhost:3000/download/:id downloads a book and adds entry for the download in book downloads collection. 

2. Book Controller Class:
- Another API which is exposed is to fetch all the books. That is http://localhost:3000/books lists all the books present in the library.

3. LeaderBoard Controller Class:
- For fetching the leaderBoard by the popularity: {Daily, Weekly, Monthly, '' (Only for Department)} and type: {Book/Department}. That is http://localhost:3000/leaderBoard?type={type}&popularity={popularity}.
- It will return the rankings based on that, also return the lastWeekWinner for Department type.

# API Endpoints

Few Examples: Each example API preceeded by "http://localhost:3000"

/books/ --> To get Book.

/download/:id --> To download Book by id.

/leaderBoard?type={type}&popularity={popularity} --> To fetch the popular books and departments by type and popularity.

# Cron Jobs

RankingService: updateRankings: It will update the rankings Daily, Weekly, Monthly and Hourly for the Books and Departments.

removeBookService: updateLeastDownloadedBooks: It will fetch current and past week's downloaded data and compare if the same book is in the least downloaded list, it will remove that book.
- This cron will run every sunday 12 am.


# Author and Developed by
**Vidhi Shah**

