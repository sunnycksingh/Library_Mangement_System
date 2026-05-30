package Service;

import java.util.List;

import Entity.Book;
import Entity.Patron;
import Entity.Repository.BookRepository;
import Entity.Repository.PatronRepository;
import Logger.LibraryLogger;
import Observer.PatronNotifier;

public class LibraryFacade 
{
    private BookRepository   bookRepository;
    private PatronRepository patronRepository;
    private LendingService   lendingService;
    private LibraryLogger    logger;

 
    public LibraryFacade()
    {
        this.bookRepository    = new BookRepository();
        this.patronRepository  = new PatronRepository();
        this.lendingService    = new LendingService(bookRepository, patronRepository);
        this.logger            = LibraryLogger.getInstance();  
    }

    // --- Book Mangement
    
    public void addBooks(String title, String author, String isbn, int year)
    {
        if(bookRepository.findByIsbn(isbn) != null)
        {
             logger.warn("Book with ISBN " + isbn + " already exists.");
             return;
        }
        bookRepository.addBook(new Book(title, author, isbn, year));
        logger.info("Book added: \"" + title + "\"");
    }


    // Remove books 

    public void removeBooks(String isbn)
    {
        boolean removed = bookRepository.removeBook(isbn);

        if(removed)
        {
            logger.info("Book removed: ISBN " + isbn); 
        }
        else
        {
             logger.error("Book not found for removal: ISBN " + isbn);
        }
    }

    // Update Books

    public void updateBooks(String isbn, String newTitle, String newAuthor, int newYear)
    {
        Book book = bookRepository.findByIsbn(isbn);

        if(book == null)
        {
             logger.error("Book not found: ISBN " + isbn);
            return;
        }

        book.setTitle(newTitle);
        book.setAuthor(newAuthor);
        book.setPublicationYear(newYear);
        logger.info("Book updated: ISBN " + isbn);
    }

    // Search By Title

    public void searchByTitle(String title)
    {
        List<Book> results = bookRepository.findByTitle(title);
        printBookList(results, "author: " + title);
    }

    // Search By Author

    public void searchByAuthor(String author)
    {
        List<Book> result = bookRepository.findByAuthor(author);
        printBookList(result, "author : "+ author);
    }

    // Search By Isbn

    public void searchByIsbn(String isbn)
    {
        Book book = bookRepository.findByIsbn(isbn);

        if(book == null)
        {
            System.out.println("No book found with ISBN : "+isbn);
        }
        else
        {
            System.out.println(book);
        }
    }

    // List of All Books

    public void listAllBooks()
    {
        printBookList(bookRepository.getAllBooks(), "all books");
    }



    // - Patron Mangement ----------------

    //  Add Patron 

    public void addPatron(String id, String name, String email)
    {
        if(patronRepository.findById(id) != null)
        {
            logger.warn("Patron with ID " + id + " already exists.");
            return; 
        }

        patronRepository.addPatron(new Patron(id,name,email));
        logger.info("Patron added: " + name);
    }


    // Update Patron 

    public void updatePatron(String id, String newName, String newEmail)
    {
        Patron patron = patronRepository.findById(id);

        if(patron == null)
        {
            logger.error("Patron not found: " + id);
            return; 
        }
        patron.setName(newName);
        patron.setEmail(newEmail);
        logger.info("Patron updated: " + id);
    }

    // Show Patron History

    public void showPatronHistory(String patronId)
    {
        Patron patron = patronRepository.findById(patronId);

        if(patron == null)
        {
            logger.error("Patron not found: " + patronId);
            return;
        }

        System.out.println("Borrowing histroy for "+ patron.getName() + " : ");
        if(patron.getBorrowingHistory().isEmpty())
        {
            System.out.println("No History yest.");
        }
        else
        {
            for(String isbn : patron.getBorrowingHistory())
            {
                System.out.println(" ISBN " + isbn);
            }
        }
    }

    // List of All Patrons

    public void listAllPatrons()
    {
        List<Patron> patrons = patronRepository.getAllPatrons();
        if(patrons.isEmpty())
        {
             System.out.println("No patrons registered.");
            return;
        }

        for(Patron p : patrons)
        {
           System.out.println(p);  
        }
    }

    // --- Lending -----------------------------

    // Check out Books

    public void checkoutBook(String patronId, String isbn)
    {
        lendingService.checkoutBooks(patronId, isbn);
    }

    // Return books

    public void returnBook(String patronId, String isbn)
    {
        lendingService.returnBooks(patronId, isbn);
    }
    

     // Register a patron as an observer (for notifications on return)
    public void registerObserver(String patronName) {
        lendingService.addObserver(new PatronNotifier(patronName));
    }
     

    // - Helpers ----------------------

    public void printBookList(List<Book> books, String context)
    {
        if(books.isEmpty())
        {
            System.out.println("No books found for "+context + " . ");
            return;
        }

        System.out.println("Results for " + context + " . ");
        for(Book book : books)
        {
            System.out.println("  "+book);
        }
    }
   
  
}
