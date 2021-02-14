
package talen;

import java.util.ResourceBundle;


public class Taal
{
    private static ResourceBundle r;

    public static ResourceBundle getR()
    {
        return r;
    }

    public static void setR(ResourceBundle r)
    {
        Taal.r = r;
    }
    
    public static String getString(String naam)        
    {
    return r.getString(naam);
    
    }
}
