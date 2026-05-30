package Observer;

public interface LibraryObserver 
{
    // This method is called when a book become available

    void onBookReturned(String isbn, String bookTitle);
    
}
