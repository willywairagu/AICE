import java.util.ArrayList;

public class Architecture extends ArrayList <Integer>
{
    //features
    private String description;
    
    
    //constructor
    // "2,2,1"
    public Architecture ( String description )
    {
        String descriptionList [ ] = description.split ( ",");
        
        for ( int dLI = 0; dLI < descriptionList.length; dLI ++ )
            add ( Integer.parseInt ( descriptionList [ dLI ] ) );
    }
}