import java.util.ArrayList;

public class StructureDescription extends ArrayList <Integer>
{
    //feature
    private String description;
    
    //constructor
    public StructureDescription (String description)
    {
        String [ ] descriptionParts = description.split( ",");
        
        for (int dp = 0; dp<descriptionParts.length; dp++ )
        {
            add ( Integer.parseInt (descriptionParts [dp] ));
        }
    }
}

