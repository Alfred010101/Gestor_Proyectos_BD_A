
package model;

import java.util.Date;

/**
 *
 * @author Alfred
 */

public class Project
{
    private int id;
    private int responsible;
    private String name;
    private int state;
    private String description; 
    private Date startDate;
    private Date endDate;

    public Project(int id, int responsible, String name, int state, String description, Date startDate, Date endDate)
    {
        this.id = id;
        this.responsible = responsible;
        this.name = name;
        this.state = state;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
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
     * @return the responsible
     */
    public int getResponsible()
    {
        return responsible;
    }

    /**
     * @param responsible the responsible to set
     */
    public void setResponsible(int responsible)
    {
        this.responsible = responsible;
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
     * @return the state
     */
    public int getState()
    {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(int state)
    {
        this.state = state;
    }

    /**
     * @return the description
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * @return the startDate
     */
    public Date getStartDate()
    {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate)
    {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate()
    {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate)
    {
        this.endDate = endDate;
    }
}
