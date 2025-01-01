import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class LibrarySystem {
    public static void main(String[] args) throws ParseException {
        Library library = new Library();

        Scanner scanner = new Scanner(System.in);
        String menu = String.format(
                "%nWhat would you like to do?%n" +
                        "1 - List books%n" +
                        "2 - List authors%n" +
                        "3 - List clients%n" +
                        "4 - List available books%n" +
                        "5 - List loaned books%n" +
                        "6 - Get book by author%n" +
                        "7 - Other options%n" +
                        "0 - Exit%n%n" +
                        "Enter your choice: "
        );

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date danCostaDateOfBirth = sdf.parse("1989-02-04");

        Author authorDan = new Author("Dan Costa", danCostaDateOfBirth);
        library.addAuthor(authorDan);

        Book book = new Book("My Rocket Seat Java Book", authorDan);
        library.addBook(book);

        Book book2 = new Book("Fly With Java Book", authorDan);
        library.addBook(book2);

        Client clientDan = new Client("Dan Costa", "dan@rocket.com", danCostaDateOfBirth);
        library.addClient(clientDan);

        System.out.println("Welcome to the Library System!");

        while (true) {
            System.out.println(menu);
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> System.out.println(library.listBooks());
                case 2 -> System.out.println(library.listAuthors());
                case 3 -> System.out.println(library.listClients());
                case 4 -> System.out.println(library.listAvailableBooks());
                case 5 -> System.out.println(library.listBookLoans());
                case 6 -> getBookByAuthor(library, scanner);
                case 7 -> otherOptions(scanner, library);
                case 0 -> {
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.%n");
            }
        }
    }

    static void otherOptions(Scanner scanner, Library library) {
        boolean showOptionsMenu = true;
        while (showOptionsMenu) {
            String optionsMenu = String.format(
                    "%n" +
                            "Choose an option:%n" +
                            "1 - Add book%n" +
                            "2 - Add author%n" +
                            "3 - Add client%n" +
                            "4 - Loan a book%n" +
                            "5 - Return a book%n" +
                            "6 - Edit author's name%n" +
                            "7 - Edit book's title%n" +
                            "0 - Go back to the main menu%n%n" +
                            "Enter your choice:"
            );

            System.out.println(optionsMenu);
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addABook(library, scanner);
                case 2 -> addAuthor(library, scanner);
                case 3 -> addClient(library, scanner);
                case 4 -> bookLoan(library, scanner);
                case 5 -> returnBook(library, scanner);
                case 6 -> editAuthor(library, scanner);
                case 7 -> editBook(library, scanner);
                case 0 -> showOptionsMenu = false;
                default -> System.out.println("Invalid choice. Please try again.%n");
            }
        }
    }

    static void addABook(Library library, Scanner scanner) {
        List<Author> authorsList = library.listAuthors();
        List<Book> booksList = library.listBooks();

        System.out.println("What's the book's name?");
        String booksTitle = scanner.nextLine();

        for (Book book : booksList) {
            if (book.getTitle().equals(booksTitle)) {
                System.out.println("Book title already exists. Would you like to list books? Y/N");
                String answer = scanner.nextLine();
                if (answer.equalsIgnoreCase("Y")) {
                    System.out.println(library.listBooks());
                }
                return;
            }
        }

        System.out.println("Who's the book's author?");
        String typedBooksAuthor = scanner.nextLine();

        Author booksAuthor = null;
        authorsList = library.listAuthors();
        for (Author author : authorsList) {
            if (author.getName().equals(typedBooksAuthor)) {
                booksAuthor = author;
                break;
            }
        }

        if (booksAuthor == null) {
            System.out.println("Author not found. Please add the author before adding the book.");
            addAuthor(library, scanner);

            for (Author author : authorsList) {
                if (author.getName().equals(typedBooksAuthor)) {
                    booksAuthor = author;
                    break;
                }
            }
        }

        Book newBook = new Book(booksTitle, booksAuthor);
        library.addBook(newBook);
        System.out.println("Book added successfully!");
    }

    static void addAuthor(Library library, Scanner scanner) {
        List<Author> authorsList = library.listAuthors();
        System.out.println("Enter the author's name:");
        String authorName = scanner.nextLine();

        System.out.println("Enter the author's date of birth (yyyy-MM-dd):");
        String dateOfBirth = scanner.nextLine();

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dob = sdf.parse(dateOfBirth);

            for (Author author : authorsList) {
                if (author.getName().equals(authorName) && author.getDateOfBirth().equals(dob)) {
                    System.out.println("This author has been added already! Try again.");
                    return;
                }
            }

            Author newAuthor = new Author(authorName, dob);
            library.addAuthor(newAuthor);
            System.out.println("Author added successfully!");
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please try again.");
        }
    }

    static void addClient(Library library, Scanner scanner) {
        List<Client> clientsList = library.listClients();
        System.out.println("\nEnter the client's name:");
        String clientName = scanner.nextLine();

        System.out.println("Enter the client's date of birth (yyyy-MM-dd):");
        String dateOfBirth = scanner.nextLine();

        System.out.println("Enter the clients's email address:");
        String emailAddress = scanner.nextLine();

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dob = sdf.parse(dateOfBirth);

            for (Client client : clientsList) {
                if (client.getName().equals(clientName) && client.getDateOfBirth().equals(dob)) {
                    System.out.println("This client has been added already! Try again.");
                    return;
                }

                if (client.getEmail().equals(emailAddress)) {
                    System.out.println("This email has already been used. Try a different one.");
                }
            }

            Client newClient = new Client(clientName, emailAddress, dob);
            library.addClient(newClient);
            System.out.println("Client added successfully!");
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please try again.");
        }
    }

    static void bookLoan(Library library, Scanner scanner) {
        List<Book> bookList = library.listBooks();
        List<Client> clientList = library.listClients();
        System.out.println("Here are the available books:");

        int bookCounter = 0;
        for (Book book : bookList) {
            if (book.isAvailable())
                System.out.println(++bookCounter + "- " + book.getTitle() + " by " + book.getAuthor().getName());
        }

        Client rentingClient = null;
        System.out.println("\nWhat's the client's name?");
        String clientsName = scanner.nextLine().toLowerCase();

        for (Client client : clientList) {
            if (client.getName().toLowerCase().equals(clientsName)) {
                rentingClient = client;
                break;
            }
        }

        if (rentingClient == null) {
            System.out.println("Client not found! Add new client below:");
            addClient(library, scanner);
            return;
        }

        System.out.println("\nWhat book would " + clientsName + " like to loan?");
        String booksName = scanner.nextLine().toLowerCase();
        boolean bookFound = false;

        for (Book book : bookList) {
            if (book.getTitle().toLowerCase().equals(booksName)) {
                library.getABookLoan(book, rentingClient);
                System.out.println(book.getTitle() + " has been loaned." + " to " + clientsName);
                bookFound = true;
                break;
            }
        }

        if (!bookFound) {
            System.out.println("Book not found in the library.");
        }
    }

    static void returnBook(Library library, Scanner scanner) {
        List<BookLoan> bookLoan = library.listBookLoans();

        System.out.println("What book would you like to return?");
        String booksName = scanner.nextLine().toLowerCase();
        boolean bookFound = false;
        for (BookLoan book : bookLoan) {
            if (book.getBook().getTitle().toLowerCase().equals(booksName)) {
                if (book.getBook().isAvailable()) {
                    System.out.println(book.getBook().getTitle() + " was already returned.");
                    return;
                }

                library.returnABook(book);
                System.out.println(book.getBook().getTitle() + " is returned and now available to be loaned again.");
                bookFound = true;
                break;
            }
        }

        if (!bookFound) {
            System.out.println("Book not found in the library.");
        }
    }

    static void getBookByAuthor(Library library, Scanner scanner) {
        List<Author> authorsList = library.listAuthors();
        System.out.println("What author are you interested in?");
        String booksAuthor = scanner.nextLine();

        for (Author author : authorsList) {
            if (!author.getName().equals(booksAuthor)) {
                System.out.println("Author not found!");
                return;
            }
            System.out.println("These are the books written by " + author.getName());
            System.out.println(library.getBooksByAuthor(author));
            break;
        }
    }

    static void editAuthor(Library library, Scanner scanner) {
        List<Author> authorsList = library.listAuthors();
        System.out.println("Enter the author's name:");
        String authorName = scanner.nextLine();

        System.out.println("Enter the author's date of birth (yyyy-MM-dd):");
        String dateOfBirth = scanner.nextLine();

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dob = sdf.parse(dateOfBirth);

            for (Author author : authorsList) {
                if (!author.getName().equals(authorName) || !author.getDateOfBirth().equals(dob)) {
                    System.out.println("Author not found!");
                    return;
                }

                System.out.println("Would you like to edit this author's name? Y/N");
                String response = scanner.nextLine();

                if (response.equalsIgnoreCase("n")) {
                    System.out.println("Ok...");
                    return;
                }

                if (response.equalsIgnoreCase("y")) {
                    System.out.println("What's the updated name?");
                    String newName = scanner.nextLine();

                    author.setName(newName);
                    System.out.println("Author updated successfully!");
                    return;
                }

                System.out.println("That's not an available response.");
            }

        } catch (ParseException e) {
            System.out.println("Invalid date format. Please try again.");
        }
    }

    static void editBook(Library library, Scanner scanner) {
        List<Book> booksList = library.listBooks();
        System.out.println("Here are all the books:");

        int bookCounter = 0;
        for (Book book : booksList) {
            System.out.println(++bookCounter + "- " + book.getTitle() + " by " + book.getAuthor().getName());
        }

        System.out.println("Enter the book's name:");
        String bookName = scanner.nextLine();

        for (Book book : booksList) {
            if (book.getTitle().equals(bookName)) {
                System.out.println("Would you like to edit this book's title? Y/N");
                String response = scanner.nextLine();

                if (response.equalsIgnoreCase("n")) {
                    System.out.println("Ok...");
                    return;
                }

                if (response.equalsIgnoreCase("y")) {
                    System.out.println("What's the updated title?");
                    String newName = scanner.nextLine();

                    book.setTitle(newName);
                    System.out.println("Book updated successfully!");
                    return;
                }

                System.out.println("That's not an available response.");
                return;
            }
        }

    }
}
