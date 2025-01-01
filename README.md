# librarySystem

***

This is a simple library system app. Created following the instructions
of a Rocket Seat challenge.

*Created by*: Daniel de Oliveira Costa

***
# Library Loan System Challenge

## About the Challenge

In this challenge, you will develop a basic system to manage book loans in a library. The focus will be on listing available books and implementing a loan feature.

The application should include a user interaction loop, where the user is prompted to view available books.

- **If YES**: The console will display the available books. The user will then select a book by its ID, provide their name, and receive a confirmation message that the book has been successfully loaned.
- **If NO**: The system will terminate, displaying a message indicating the application has ended.

---

## System Features

### Book Management

- **Add a new book**: Include title and author (as this is an introductory challenge, you can predefine books directly within the class, as demonstrated in the lessons).
- **List available books**: Display only the books currently available for loan.
- **Loan a book**: Allow a user to select an available book and register the loan.

---

## Project Structure

The project will be divided into simple classes to maintain organization. Below are the main classes and their responsibilities:

### `Book`

- **id**: Unique identifier for the book
- **title**: Title of the book
- **author**: Author of the book (an object of type `Author`)
- **available**: Indicates whether the book is available for loan
- **registrationDate**: The date the book was added to the library
- **updateDate**: The date the book was last updated

### `Author`

- **id**: Unique identifier for the author
- **name**: Name of the author
- **birthDate**: Author's date of birth

### `Library`

- **books**: A list of books in the library
- **authors**: A list of authors in the library
- **loans**: A list of loans made through the library

---

## Business Rules

### Book Management

- Only books marked as available can be loaned.
- Users must provide their name when loaning a book.

### Book Loans

- When a loan is processed, the selected book should be marked as unavailable and cannot be loaned again during the program's execution.

---

## Going Further

Here are some additional features you could implement:

- **Client Registration**: Create a `Client` class to represent library users, with attributes such as `id`, `name`, `birthDate`, and `email`.
    - Implement a feature to list all registered clients.
    - Add the ability to associate loans with clients, allowing you to view which books a specific client has borrowed and when.

- **Extended Menu Options**:
    - Add a menu at the start of the system, allowing users to register a new book by providing all required details.
    - Once added, the book will be marked as available for loans.

By implementing these additional features, you can expand the functionality of the library system and improve its usability.

