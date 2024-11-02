package model;

import java.util.Date;

/**
 *
 * @author Alfred
 */

public class Task
{
    private final int id;
    private final String project;
    private final String responsible;
    private String state;
    private String titulo;
    private String description;
    private Date startDate;
    private Date endDate;
    private Date expectedDate;

    public Task(int id, String project, String responsible, String state, String titulo, String description, Date startDate, Date endDate, Date expectedDate)
    {
        this.id = id;
        this.project = project;
        this.responsible = responsible;
        this.state = state;
        this.titulo = titulo;
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
     * @return the project
     */
    public String getProject()
    {
        return project;
    }

    /**
     * @return the responsible
     */
    public String getResponsible()
    {
        return responsible;
    }

    /**
     * @return the state
     */
    public String getState()
    {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state)
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

    /**
     * @return the titulo
     */
    public String getTitulo()
    {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo)
    {
        this.titulo = titulo;
    }
    
    
}
