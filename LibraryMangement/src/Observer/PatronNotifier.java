package Observer;

import Logger.LibraryLogger;

public class PatronNotifier implements LibraryObserver 
{
    private String patronName;
    private LibraryLogger logger = LibraryLogger.getInstance(); // Singleton logger

    public PatronNotifier(String patronName)
    {
        this.patronName = patronName;
    }

    @Override
    public void onBookReturned(String isbn, String bookTitle) {
      // In a real system this would send a email/SMS
      // Here we just print to console

      logger.info("Notification -> Hey "+ patronName + "! This book \"" + bookTitle + "\" (ISBN: " + isbn + ") is now available.");
    }
    
}
