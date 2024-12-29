import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

public class Author {
    private final UUID id;
    private String name;
    private final Date dateOfBirth;
    private final LocalDateTime createdAt;

    public Author(String name, Date dateOfBirth) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.createdAt = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Author Details:\n" +
                "id: " + id + "\n" +
                "Name: " + name + "\n" +
                "Date of Birth: " + dateOfBirth + "\n" +
                "Created At: " + createdAt;
    }
}
