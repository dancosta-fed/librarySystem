import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LibrarySystem {
    public static void main(String[] args) throws ParseException {
        Library library = new Library();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date danCostaDateOfBirth = sdf.parse("1988-02-04");

        Author authorDan = new Author("Dan Costa", danCostaDateOfBirth);
        Book book = new Book("My Book", authorDan);
        library.addBook(book);

        System.out.println(authorDan);
    }
}
