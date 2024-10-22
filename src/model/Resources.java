
package model;

/**
 *
 * @author Alfred
 */

public class Resources
{
    private final int id;
    private String name;
    private int totalAmount;
    private int quantityAvailable;

    public Resources(int id, String name, int totalAmount, int quantityAvailable)
    {
        this.id = id;
        this.name = name;
        this.totalAmount = totalAmount;
        this.quantityAvailable = quantityAvailable;
    }

    /**
     * @return the id
     */
    public int getId()
    {
        return id;
    }

    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return the totalAmount
     */
    public int getTotalAmount()
    {
        return totalAmount;
    }

    /**
     * @param totalAmount the totalAmount to set
     */
    public void setTotalAmount(int totalAmount)
    {
        this.totalAmount = totalAmount;
    }

    /**
     * @return the quantityAvailable
     */
    public int getQuantityAvailable()
    {
        return quantityAvailable;
    }

    /**
     * @param quantityAvailable the quantityAvailable to set
     */
    public void setQuantityAvailable(int quantityAvailable)
    {
        this.quantityAvailable = quantityAvailable;
    }    
}
