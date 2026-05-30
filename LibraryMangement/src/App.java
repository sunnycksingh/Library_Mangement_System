import java.util.Scanner;

import Service.LibraryFacade;

public class App {

    private static LibraryFacade facade = new LibraryFacade();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args)
     {
      
        System.out.println("===================================");
        System.out.println("   Welcome to Library System      ");
        System.out.println("===================================");

        boolean running = true;

        while(running)
        {
            printMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":  addBook();           break;
                case "2":  removeBook();        break;
                case "3":  updateBook();        break;
                case "4":  searchBooks();       break;
                case "5":  listAllBooks();      break;
                case "6":  addPatron();         break;
                case "7":  updatePatron();      break;
                case "8":  showHistory();       break;
                case "9":  listAllPatrons();    break;
                case "10": checkoutBook();      break;
                case "11": returnBook();        break;
                case "12": registerObserver();  break;
                case "0":  running = false;     break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }


    private static void printMenu()
    {
        System.out.println("\n--- MENU ---");
        System.out.println("-- Books --");
        System.out.println("  1. Add Book");
        System.out.println("  2. Remove Book");
        System.out.println("  3. Update Book");
        System.out.println("  4. Search Books");
        System.out.println("  5. List All Books");
        System.out.println("-- Patrons --");
        System.out.println("  6. Add Patron");
        System.out.println("  7. Update Patron");
        System.out.println("  8. Show Patron Borrowing History");
        System.out.println("  9. List All Patrons");
        System.out.println("-- Lending --");
        System.out.println("  10. Checkout Book");
        System.out.println("  11. Return Book");
        System.out.println("  12. Register for Notification (Observer demo)");
        System.out.println("  0. Exit");
        System.out.print("Enter choice: ");
    }

     private static void addBook() {
        System.out.print("Title: ");      String title = scanner.nextLine();
        System.out.print("Author: ");     String author = scanner.nextLine();
        System.out.print("ISBN: ");       String isbn = scanner.nextLine();
        System.out.print("Pub Year: ");   int year = Integer.parseInt(scanner.nextLine());
        facade.addBooks(title, author, isbn, year);
    }

     private static void removeBook() {
        System.out.print("ISBN to remove: ");
        facade.removeBooks(scanner.nextLine());
    }

     private static void updateBook() {
        System.out.print("ISBN to update: ");   String isbn = scanner.nextLine();
        System.out.print("New Title: ");        String title = scanner.nextLine();
        System.out.print("New Author: ");       String author = scanner.nextLine();
        System.out.print("New Pub Year: ");     int year = Integer.parseInt(scanner.nextLine());
        facade.updateBooks(isbn, title, author, year);
    }

     private static void searchBooks() {
        System.out.println("Search by: 1=Title  2=Author  3=ISBN");
        System.out.print("Choice: ");
        String opt = scanner.nextLine();
        System.out.print("Enter search term: ");
        String term = scanner.nextLine();
        if (opt.equals("1"))      facade.searchByTitle(term);
        else if (opt.equals("2")) facade.searchByAuthor(term);
        else if (opt.equals("3")) facade.searchByIsbn(term);
        else System.out.println("Invalid option.");
    }

    private static void listAllBooks() {
        facade.listAllBooks();
    }

      private static void addPatron() {
        System.out.print("Patron ID: ");  String id = scanner.nextLine();
        System.out.print("Name: ");       String name = scanner.nextLine();
        System.out.print("Email: ");      String email = scanner.nextLine();
        facade.addPatron(id, name, email);
    }

     private static void updatePatron() {
        System.out.print("Patron ID: ");   String id = scanner.nextLine();
        System.out.print("New Name: ");    String name = scanner.nextLine();
        System.out.print("New Email: ");   String email = scanner.nextLine();
        facade.updatePatron(id, name, email);
    }

     private static void showHistory() {
        System.out.print("Patron ID: ");
        facade.showPatronHistory(scanner.nextLine());
    }

     private static void listAllPatrons() {
        facade.listAllPatrons();
    }

     private static void checkoutBook() {
        System.out.print("Patron ID: ");   String pid = scanner.nextLine();
        System.out.print("Book ISBN: ");   String isbn = scanner.nextLine();
        facade.checkoutBook(pid, isbn);
    }

    private static void returnBook() {
        System.out.print("Patron ID: ");   String pid = scanner.nextLine();
        System.out.print("Book ISBN: ");   String isbn = scanner.nextLine();
        facade.returnBook(pid, isbn);
    }

    private static void registerObserver() {
        System.out.print("Enter patron name to register for notifications: ");
        facade.registerObserver(scanner.nextLine());
    }

}
