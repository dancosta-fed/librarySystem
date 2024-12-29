import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Library {
    List<Book> books = new ArrayList<>();
    List<Author> authors = new ArrayList<>();
    List<BookLoan> loans = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public List<Book> listBooks() {
        return books;
    }

    public void addAuthor(Author author) {
        authors.add(author);
    }

    public List<Author> listAuthors() {
        return authors;
    }

    public void updateAuthor(String newAuthorsName, UUID authorId) {
        for (Author author : authors) {
            if (author.getId().equals(authorId)) {
                author.setName(newAuthorsName);
            }
        }
        return;
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
