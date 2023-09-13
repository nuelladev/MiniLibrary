# MiniLibrary - A Simple Library Management System

MiniLibrary is a lightweight Java library management system that allows you to manage users, books, and borrow/return operations. This README provides information on how to use and set up MiniLibrary in your project.

## Table of Contents

- [Features](#features)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
  - [Installation](#installation)
  - [Configuration](#configuration)
- [Usage](#usage)
  - [API Endpoints](#api-endpoints)
  

## Features

- Manage Users: Add, update, and delete library users.
- Manage Books: Add, update, and delete library books.
- Borrow and Return Books: Allow users to borrow and return books.
- Activity Logging: Log all library activities to a file by dates.checkout the screenshot
![Activity Log Screenshot](https://github.com/nuelladev/MiniLibrary/blob/master/Screenshot%20(185).png)
- Mostly identifies books with ISBN. Reason? because each an author can write two books and 2 books can have the same name. Hence, it only makes sense to identify unique books added to the system with their ISBN number. 

## Prerequisites

Before you start using MiniLibrary, make sure you have the following installed:

- Java Development Kit (JDK) 8 or higher
- Maven (for building and managing dependencies)

## Getting Started

### Installation

Clone the MiniLibrary repository to your local machine:

```bash
git clone https://github.com/your-username/MiniLibrary.git

### Configuration

1. Navigate to the `application.properties` file in the `src/main/resources` directory.

2. Configure the database connection properties according to your setup:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/library_db
   spring.datasource.username=root
   spring.datasource.password=password
## Usage

MiniLibrary is a simple library management system that provides RESTful APIs for managing users, books, borrowed books, and activity logs. You can interact with the library system using various API endpoints.

### API Endpoints

#### Users

- **List All Users**: Retrieve a list of all library users.

  - **Endpoint**: `GET /api/users`

- **Add User**: Add a new user to the library system.

  - **Endpoint**: `POST /api/users`

  - **Example JSON Body**:
    ```json
    {
      "fullName": "John Doe",
      "email": "john.doe@example.com",
      "age": 30
    }
    ```

- **Update User**: Update an existing user's information.

  - **Endpoint**: `PUT /api/users/{userId}`

  - **Example JSON Body**:
    ```json
    {
      "fullName": "Updated Name",
      "email": "updated.email@example.com",
      "age": 35
    }
    ```

- **Delete User**: Delete a user from the library system.

  - **Endpoint**: `DELETE /api/users/{userId}`

#### Books

- **List All Books**: Retrieve a list of all library books.

  - **Endpoint**: `GET /api/books`

- **Add Book**: Add a new book to the library system.

  - **Endpoint**: `POST /api/books`

  - **Example JSON Body**:
    ```json
    {
      "title": "Sample Book",
      "author": "John Author",
      "isbn": "9781234567890",
      "publicationYear": 2022
    }
    ```

- **Update Book**: Update an existing book's information.

  - **Endpoint**: `PUT /api/books/{bookId}`

  - **Example JSON Body**:
    ```json
    {
      "title": "Updated Book Title",
      "author": "Updated Author Name",
      "isbn": "9780987654321",
      "publicationYear": 2023
    }
    ```

- **Delete Book**: Delete a book from the library system.

  - **Endpoint**: `DELETE /api/books/{bookId}`

- **Search Books**: Search for books by title, author, or ISBN.

  - **Endpoint**: `GET /api/books/search?query={searchQuery}`

#### Borrowed Books

- **List Borrowed Books by User**: Retrieve a list of books borrowed by a specific user.

  - **Endpoint**: `GET /api/borrowed-books/user/{userId}`

- **Borrow Book**: Borrow a book for a user.

  - **Endpoint**: `POST /api/borrowed-books/borrow`

  - **Example JSON Body**:
    ```json
    {
      "userId": 1,
      "bookId": 2
    }
    ```

- **Return Book**: Return a borrowed book.

  - **Endpoint**: `POST /api/borrowed-books/return`

  - **Example JSON Body**:
    ```json
    {
      "userId": 1,
      "bookId": 2
    }
    ```

#### Activity Log

- **Retrieve Activity Log**: Retrieve the activity log containing library system events.

  - **Endpoint**: `GET /api/activity-log`

- **Clear Activity Log**: Clear the activity log.

  - **Endpoint**: `DELETE /api/activity-log`




