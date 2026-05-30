package Entity.Repository;

import java.util.ArrayList;
import java.util.List;

import Entity.Patron;

public class PatronRepository 
{

    private List<Patron> patrons;

    // Constructor

    public PatronRepository()
    {
        this.patrons = new ArrayList<>();
    }


    // Add a patron

    public void addPatron(Patron patron)
    {
        patrons.add(patron);
    }

    
  // FindById patrons

  public Patron findById(String patronId)
  {
    for(Patron patron : patrons)
    {
        if(patron.getPatronId().equalsIgnoreCase(patronId))
        {
            return patron;
        }
    }

    return null;
  }

  // Get All Patrons

  public List<Patron> getAllPatrons()
  {
    return patrons;
  }   
}
