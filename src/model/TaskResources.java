
package model;

/**
 *
 * @author Alfred
 */

public class TaskResources
{
    private final int id;
    private final int resource;
    private final int task;
    private int amount;

    public TaskResources(int id, int resource, int task, int amount)
    {
        this.id = id;
        this.resource = resource;
        this.task = task;
        this.amount = amount;
    }

    /**
     * @return the id
     */
    public int getId()
    {
        return id;
    }

    /**
     * @return the resource
     */
    public int getResource()
    {
        return resource;
    }

    /**
     * @return the task
     */
    public int getTask()
    {
        return task;
    }

    /**
     * @return the amount
     */
    public int getAmount()
    {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(int amount)
    {
        this.amount = amount;
    }
}
