import java.time.LocalDateTime;
import java.util.UUID;

public class BookLoan {
    private final UUID id;
    private final Client client;
    private final Book book;
    private final LocalDateTime loanDate;
    private LocalDateTime returnDate;

    public BookLoan(Client client, Book book) {
        this.id = UUID.randomUUID();
        this.client = client;
        this.book = book;
        this.loanDate = LocalDateTime.now();
        this.returnDate = null;
    }

    public UUID getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public Book getBook() {
        return book;
    }

    public LocalDateTime getLoanDate() {
        return loanDate;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "Book Loan Details:\n" +
                "Loan ID: " + id + "\n" +
                "Client: " + client.getName() + "\n" +
                "Book Title: " + book.getTitle() + "\n" +
                "Loan Date: " + loanDate + "\n" +
                "Return Date: " + (returnDate != null ? returnDate : "Not yet returned");
    }

}
