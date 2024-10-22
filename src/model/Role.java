
package model;

/**
 *
 * @author Alfred
 */

public class Role
{
    private final int id;
    private String name;

    public Role(int id, String name)
    {
        this.id = id;
        this.name = name;
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
}
