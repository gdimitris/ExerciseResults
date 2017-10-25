import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class LibraryTest {

    private Library library;

    @Before
    public void setUp() throws Exception {
        library = new Library();
    }

    @Test
    public void itBeginsWithNoAuthors() {
        assertEquals(Arrays.asList(), library.authors());
    }

    @Test
    public void itCanRetrieveOneAuthor() {
        addBookToLibrary("irrelevant", "Stephen King", 0);

        assertEquals(Arrays.asList("Stephen King"), library.authors());
    }

    @Test
    public void itCanRetrieveMultipleAuthors() {
        addBookToLibrary("irrelevant", "Stephen King", 0);
        addBookToLibrary("irrelevant", "J.K. Rowling", 0);

        assertEquals(Arrays.asList("Stephen King", "J.K. Rowling"), library.authors());
    }

    @Test
    public void itReturnsTheTitles() {
        addBookToLibrary("Harry Potter", "irrelevant", 0);
        addBookToLibrary("IT", "irrelevant", 0);

        assertEquals(Arrays.asList("Harry Potter", "IT"), library.titles());
    }

    @Test
    public void itDoesntIncludeTheSameAuthorsTwice() {
        addBookToLibrary("irrelevant", "Stephen King", 0);
        addBookToLibrary("irrelevant", "Stephen King", 0);

        assertEquals(Arrays.asList("Stephen King"), library.authors());
    }

    @Test
    public void itReturnsAllBookTitlesAfterAYear() {
        addBookToLibrary("Harry Potter", "J.K. Rowling", 1997);
        addBookToLibrary("Harry Potter 2", "J.K. Rowling", 1999);
        addBookToLibrary("Harry Potter 3", "J.K. Rowling", 2001);

        assertEquals(Arrays.asList("Harry Potter 2", "Harry Potter 3"), library.titlesAfter(1998));
    }

    @Test
    public void itReturnsTheAuthorsAndTheirBookCount() {
        addBookToLibrary("Harry Potter", "J.K. Rowling", 0);
        addBookToLibrary("Harry Potter 2", "J.K. Rowling", 0);
        addBookToLibrary("Harry Potter 3", "J.K. Rowling", 0);
        addBookToLibrary("IT", "Stephen King", 0);

        Map<String, Long> bookCount = library.bookCount();

        assertEquals(3L, bookCount.get("J.K. Rowling"), 0.001);
        assertEquals(1L, bookCount.get("Stephen King"), 0.001);
    }

    private void addBookToLibrary(String title, String authorName, int year) {
        Author author = new Author(authorName);
        Book book = new Book(author, title, year);
        library.add(book);
    }
}