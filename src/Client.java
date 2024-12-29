import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

public class Client {

    private final UUID id;
    private final String name;
    private final String email;
    private final Date dateOfBirth;
    private final LocalDateTime createdAt;

    public Client(String name, String email, Date dateOfBirth) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.createdAt = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
       return name;
    }

    public String getEmail() {
        return email;
    }

    public Date getDateOfBirth() {
       return dateOfBirth;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "Author Details:\n" +
                "ID: " + id + "\n" +
                "Name: " + name + "\n" +
                "Date of Birth: " + dateOfBirth + "\n" +
                "Created At: " + createdAt;
    }
}
