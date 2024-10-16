package model;

import java.util.Date;

/**
 *
 * @author Alfred
 */

public class Task
{
    private int id;
    private int project;
    private int responsible;
    private int state;
    private String description;
    private Date startDate;
    private Date endDate;
    private Date expectedDate;

    public Task(int id, int project, int responsible, int state, String description, Date startDate, Date endDate, Date expectedDate)
    {
        this.id = id;
        this.project = project;
        this.responsible = responsible;
        this.state = state;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.expectedDate = expectedDate;
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
     * @return the project
     */
    public int getProject()
    {
        return project;
    }

    /**
     * @param project the project to set
     */
    public void setProject(int project)
    {
        this.project = project;
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

    /**
     * @return the expectedDate
     */
    public Date getExpectedDate()
    {
        return expectedDate;
    }

    /**
     * @param expectedDate the expectedDate to set
     */
    public void setExpectedDate(Date expectedDate)
    {
        this.expectedDate = expectedDate;
    }
}
