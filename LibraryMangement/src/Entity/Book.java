package Entity;

public class Book 
{
    private String  title;
    private String  author;
    private String  isbn;
    private int     publicationYear;
    private boolean isAvailable;


    public Book(String title, String author, String isbn, int publicationYear) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.isAvailable = true;
    }


    public String getTitle() {
        return title;
    }


    public String getAuthor() {
        return author;
    }


    public String getIsbn() {
        return isbn;
    }


    public int getPublicationYear() {
        return publicationYear;
    }


    public boolean isAvailable() {
        return isAvailable;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public void setAuthor(String author) {
        this.author = author;
    }


    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }


    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    
    
 @Override
    public String toString() {
        return "[" + isbn + "] \"" + title + "\" by " + author
                + " (" + publicationYear + ")"
                + (isAvailable ? " - AVAILABLE" : " - BORROWED");
    }
    
    
}
