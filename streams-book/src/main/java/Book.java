public class Book {
    private Author author;
    private int publicationYear;
    private String title;

    public Book(Author author, String title, int publicationYear) {
        this.author = author;
        this.title = title;
        this.publicationYear = publicationYear;
    }

    public Author author() {
        return this.author;
    }

    public String title() {
        return this.title;
    }

    public int year() {
        return this.publicationYear;
    }
}
