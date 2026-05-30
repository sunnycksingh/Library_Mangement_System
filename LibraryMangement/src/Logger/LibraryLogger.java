package Logger;

public class LibraryLogger 
{

    private static LibraryLogger instance;

    private LibraryLogger() {};

    public static LibraryLogger getInstance()
    {
        if(instance == null)
            
            instance = new LibraryLogger();
        
        return instance;
    }

    public void info(String message)
    {
        System.out.println("[INFO] " + message);
    }

    public void error(String message)
    {
        System.out.println("[ERROR] "+ message);
    }

    public void warn(String message)
    {
        System.out.println("[WARN] "+message);
    }

    
}
