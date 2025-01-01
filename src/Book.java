import java.time.LocalDateTime;
import java.util.UUID;

public class Book {
    private final UUID id;
    private String title;
    private final Author author;
    private final LocalDateTime createdAt;
    private boolean available;
    private LocalDateTime updatedAt;

    public Book(String title, Author author) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.author = author;
        this.available = true;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
