import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Library {
    List<Book> books = new ArrayList<>();
    List<Author> authors = new ArrayList<>();
    List<BookLoan> loans = new ArrayList<>();
    List<Client> clients = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public List<Book> listBooks() {
        return books;
    }

    public void addAuthor(Author author) {
        authors.add(author);
    }

    public void addClient(Client client) {
        clients.add(client);
    }

    public List<Author> listAuthors() {
        return authors;
    }

    public List<Client> listClients() {
        return clients;
    }

    public void getABookLoan(Book book, Client client){
        if (!book.isAvailable()) {
            System.out.println("Book is not available at this time.");
            return;
        }

        BookLoan loan = new BookLoan(client, book);
        loans.add(loan);
        book.setAvailable(false);
    }

    public void returnABook(BookLoan loanedBook) {
        for(BookLoan loan : loans) {
            if(loan.getId().equals(loanedBook.getId()) && loan.getReturnDate() == null) {
                loan.setReturnDate(LocalDateTime.now());
            }
            break;
        }
    }

    public List<Book> listAvailableBooks() {
        List<Book> availableBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.isAvailable()) {
                availableBooks.add(book);
            }
        }
        return availableBooks;
    }

    public List<Book> getBooksByAuthor(Author author) {
        List<Book> booksByAuthor = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().equals(author)) {
                booksByAuthor.add(book);
            }
        }
        return booksByAuthor;
    }

    public List<BookLoan> listBookLoans() {
        List<BookLoan> activeLoans = new ArrayList<>();
        for (BookLoan loan : loans) {
            if (loan.getReturnDate() == null) {
                activeLoans.add(loan);
            }
        }
        return activeLoans;
    }

}
