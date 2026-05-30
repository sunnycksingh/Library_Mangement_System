package Entity;

import java.util.ArrayList;
import java.util.List;

public class Patron {
    
    private String patronId;
    private String name;
    private String email;

    // Borrowing history : just a list of Isbn of book they borrowed

    private List<String> borrowingHistory;

    // Currently borrow books

    private List<String> currentlyBorrowedIsbns;

    public Patron(String patronId, String name, String email) {
        this.patronId = patronId;
        this.name = name;
        this.email = email;
        this.borrowingHistory = new ArrayList<>();
        this.currentlyBorrowedIsbns = new ArrayList<>();

    }

    public String getPatronId() {
        return patronId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<String> getBorrowingHistory() {
        return borrowingHistory;
    }

    public List<String> getCurrentlyBorrowedIsbns() {
        return currentlyBorrowedIsbns;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    // Called the method when patron borrows the book

    public void borrowBooks(String isbn)
    {
        currentlyBorrowedIsbns.add(isbn);
        borrowingHistory.add(isbn);
    }
    
 
    public void returnBooks(String isbn)
    {
        currentlyBorrowedIsbns.remove(isbn);
    }

     @Override
    public String toString() {
        return "[" + patronId + "] " + name + " | Email: " + email
                + " | Currently borrowed: " + currentlyBorrowedIsbns.size() + " book(s)";
    }
    
}
