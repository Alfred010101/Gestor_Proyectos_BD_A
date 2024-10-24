
package utils;

/**
 *
 * @author Alfred
 */
public class Validations
{
    public static boolean isNull(String string)
    {
        return string == null;
    }    
    
    public static boolean validaEntero(String var)
    {
        try
        {
            Integer.parseInt(var);
        } catch (Exception e)
        {
            return false;
        }
        return true;
        
    }
}
