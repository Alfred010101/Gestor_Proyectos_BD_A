
package model;

/**
 *
 * @author Alfred
 */

public class TaskResource
{
    private final int id;
    private final int resource;
    private final int task;
    private int amount;

    public TaskResource(int id, int resource, int task, int amount)
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

//    /**
//     * @param id the id to set
//     */
//    public void setId(int id)
//    {
//        this.id = id;
//    }

    /**
     * @return the resource
     */
    public int getResource()
    {
        return resource;
    }

//    /**
//     * @param resource the resource to set
//     */
//    public void setResource(int resource)
//    {
//        this.resource = resource;
//    }

    /**
     * @return the task
     */
    public int getTask()
    {
        return task;
    }

//    /**
//     * @param task the task to set
//     */
//    public void setTask(int task)
//    {
//        this.task = task;
//    }

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
