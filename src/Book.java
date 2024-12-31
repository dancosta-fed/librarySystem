import java.time.LocalDateTime;
import java.util.UUID;

public class Book {
    private final UUID id;
    private final String title;
    private final Author author;
    private final LocalDateTime createdAt;
    private boolean available;
    private LocalDateTime updatedAt;
    private UUID loanedBy;

    public Book(String title, Author author) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.author = author;
        this.available = true;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.loanedBy = null;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
        this.updatedAt = LocalDateTime.now();
    }

    public void setLoanToClient(UUID clientId) {
        this.loanedBy = clientId;
    }

    @Override
    public String toString() {
        return "Book Details:\n" +
                "id= " + id + '\n' +
                "title= " + title + '\n' +
                "author= " + author.getName() + '\n' +
                "available= " + available + '\n' +
                "created at= " + createdAt + '\n' +
                "updated at= " + updatedAt;
    }
}
