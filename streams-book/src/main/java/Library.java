import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.toList;

public class Library {
    private List<Book> books = new ArrayList<>();

    public void add(Book book) {
        books.add(book);
    }

    public List<String> authors() {
        return books.stream()
                .map(Book::author)
                .map(Author::name)
                .distinct()
                .collect(toList());
    }

    public List<String> titles() {
        return books.stream()
                .map(Book::title)
                .collect(toList());
    }

    public Map<String,Long> bookCount() {
        return books.stream()
                .map(Book::author)
                .collect(Collectors.groupingBy(Author::name, counting()));
    }

    public List<String> titlesAfter(int year) {
        return books.stream()
                .filter(book -> book.year() > year)
                .map(Book::title)
                .collect(toList());
    }
}
