package Service;

import java.util.ArrayList;
import java.util.List;

import Entity.Book;
import Entity.Patron;
import Entity.Repository.BookRepository;
import Entity.Repository.PatronRepository;
import Logger.LibraryLogger;
import Observer.LibraryObserver;

public class LendingService 
{
    private BookRepository   bookRepository;
    private PatronRepository patronRepository;
    private LibraryLogger    logger;

    // DESIGN PATTERN : OBSERVER - we hold a list of observers to notify

    private List<LibraryObserver> observers;


      // Creating Constructor

    public LendingService(BookRepository bookRepository, PatronRepository patronRepository) {
        this.bookRepository = bookRepository;
        this.patronRepository = patronRepository;
        this.logger = LibraryLogger.getInstance();
        this.observers = new ArrayList<>();
    }

    // Register an observer (e.g , a patron waiting for a book)

    public void addObserver(LibraryObserver observer)
    {
        observers.add(observer);
    }


    // Checkout : Patron borrows a book

    public void checkoutBooks(String patronId, String isbn)
    {
        // If patron not found 

        Patron patron = patronRepository.findById(patronId);
        
        if(patron == null)
        {
            logger.error("Patron not found : "+ patronId);
            return;
        }

        // User enter the book id and if not found that 

        Book book = bookRepository.findByIsbn(isbn);

        if(book == null)
        {
            logger.error("Book not found : "+isbn);
            return;
        }

        // If book is not abvailable which we try to buy

        if(!book.isAvailable())
        {
            logger.warn("Book \"" + book.getTitle() + "\" is already borrowed.");
            return ;
        }

        // If everything is correct we found patron,book and book is available

        book.setAvailable(false);
        patron.borrowBooks(isbn);
        logger.info("Checkout: \"" + book.getTitle() + "\" → Patron: " + patron.getName());

    }

    // Return : Patron returns a book

    public void returnBooks(String patronId, String isbn)
    {
        Patron patron = patronRepository.findById(patronId);

        if(patron == null)
        {
            logger.error("Patron not found: " + patronId);
            return;
        }

        Book book = bookRepository.findByIsbn(isbn);

        if(book == null)
        {
           logger.error("Book not found: " + isbn);
            return; 
        }

        if(book.isAvailable())
        {
            logger.warn("Book \"" + book.getTitle() + "\" is not currently borrowed.");
            return; 
        }

        // Mark Book as availbale and update patron record;

        book.setAvailable(true);
        patron.returnBooks(isbn);
        logger.info("Returned: \"" + book.getTitle() + "\" ← Patron: " + patron.getName());

        // OBSERVER : Notify all registered observers that this book is now available

        for(LibraryObserver observer : observers)
        {
            observer.onBookReturned(isbn, book.getTitle());
        }

    }
  
    
    
    
}
