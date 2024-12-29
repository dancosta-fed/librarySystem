import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class LibrarySystem {
    public static void main(String[] args) throws ParseException {
        Library library = new Library();

        Scanner scanner = new Scanner(System.in);
        String menu = String.format(
                "%nWhat would you like to do?%n" +
                        "1 - List books%n" +
                        "2 - List authors%n" +
                        "3 - List clients%n" +
                        "4 - Other options%n" +
                        "0 - Exit%n%n" +
                        "Enter your choice: "
        );

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date danCostaDateOfBirth = sdf.parse("1988-02-04");

        Author authorDan = new Author("Dan Costa", danCostaDateOfBirth);
        Book book = new Book("My Book", authorDan);
        library.addBook(book);

        System.out.println("Welcome to the Library System!");

        while (true) {
            System.out.println(menu);
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> System.out.println(library.listBooks());
                case 2 -> System.out.println(library.listAuthors());
                case 3 -> System.out.println(library.listClients());
                case 4 -> otherOptions(scanner, library);
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
                            "0 - Go back to the main menu%n%n" +
                            "Enter your choice:"
            );

            System.out.println(optionsMenu);
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addABookOption(library, scanner);
                case 2 -> System.out.println("Add author functionality not implemented yet.");
                case 3 -> System.out.println("Add client functionality not implemented yet.");
                case 4 -> System.out.println("Loan a book functionality not implemented yet.");
                case 5 -> System.out.println("Return a book functionality not implemented yet.");
                case 0 -> showOptionsMenu = false;
                default -> System.out.println("Invalid choice. Please try again.%n");
            }
        }
    }

    static void addABookOption(Library library, Scanner scanner) {
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
        System.out.println("Enter the author's name:");
        String authorName = scanner.nextLine();

        System.out.println("Enter the author's date of birth (yyyy-MM-dd):");
        String dateOfBirth = scanner.nextLine();

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dob = sdf.parse(dateOfBirth);

            Author newAuthor = new Author(authorName, dob);
            library.addAuthor(newAuthor);
            System.out.println("Author added successfully!");
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please try again.");
        }
    }

}
