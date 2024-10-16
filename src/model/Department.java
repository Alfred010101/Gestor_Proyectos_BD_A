
package model;

/**
 *
 * @author Alfred
 */

public class Department
{
    private int id;
    private int manager;
    private int name;
    private int phoneNumber;

    public Department(int id, int manager, int name, int phoneNumber)
    {
        this.id = id;
        this.manager = manager;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    /**
     * @return the id
     */
    public int getId()
    {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * @return the manager
     */
    public int getManager()
    {
        return manager;
    }

    /**
     * @param manager the manager to set
     */
    public void setManager(int manager)
    {
        this.manager = manager;
    }

    /**
     * @return the name
     */
    public int getName()
    {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(int name)
    {
        this.name = name;
    }

    /**
     * @return the phoneNumber
     */
    public int getPhoneNumber()
    {
        return phoneNumber;
    }

    /**
     * @param phoneNumber the phoneNumber to set
     */
    public void setPhoneNumber(int phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }    
}
