package Entity.Repository;

import java.util.ArrayList;
import java.util.List;

import Entity.Book;

public class BookRepository 
{

    private List<Book> books;

    public BookRepository()
    {
        this.books = new ArrayList<>();
    }

    // Add a book

    public void addBook(Book book)
    {
        books.add(book);
    }

    // Remove a book by ISBN

    public boolean removeBook(String isbn)
    {
        Book found = findByIsbn(isbn);
         if(found != null)
         {
            books.remove(found);
         }

         return false;
        
    }

    // Find by ISBN (extact match)

    public Book findByIsbn(String isbn)
    {
        for(Book book : books)
        {
            if(book.getIsbn().equalsIgnoreCase(isbn))
            {
                return book;
            }
        }

        return null;  // Not foud
    }

    // Find by title partial match and also case-insensitive

    public List<Book> findByTitle(String title)
    {
        List<Book> result = new ArrayList<>();  // The result will get we will store isnide empty arraylist

        for(Book book : books)
        {
            if(book.getTitle().toLowerCase().contains(title.toLowerCase()))
            {
                result.add(book);
            }
        }

        return result;
    }


        // Find by title partial match and also case-insensitive

        public List<Book> findByAuthor(String author)
        {
            List<Book> result = new ArrayList<>();

            for(Book book : books)
            {
                if(book.getAuthor().toLowerCase().contains(author.toLowerCase()))
                {
                    result.add(book);
                }
            }

            return result;
        }


        // Get All books

        public List<Book> getAllBooks()
        {
            return books;
        }
}
