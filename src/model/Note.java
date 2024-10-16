package model;

import java.util.Date;

/**
 *
 * @author Alfred
 */

public class Note
{

    private final int id;
    private int employee;
    private String description;
    private Date date;

    public Note(int id, int employee, String description, Date date)
    {
        this.id = id;
        this.employee = employee;
        this.description = description;
        this.date = date;
    }

    /**
     * @return the id
     */
    public int getId()
    {
        return id;
    }

//    /**
//     * @param id the id to set
//     */
//    public void setId(int id)
//    {
//        this.id = id;
//    }

    /**
     * @return the employee
     */
    public int getEmployee()
    {
        return employee;
    }

    /**
     * @param employee the employee to set
     */
    public void setEmployee(int employee)
    {
        this.employee = employee;
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
     * @return the date
     */
    public Date getDate()
    {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date)
    {
        this.date = date;
    }
}
