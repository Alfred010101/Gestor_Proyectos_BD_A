
package model;

/**
 *
 * @author Alfred
 */

public class Collaborator
{
    private int id;
    private int project;
    private int collaborator;

    public Collaborator(int id, int project, int collaborator)
    {
        this.id = id;
        this.project = project;
        this.collaborator = collaborator;
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
     * @return the collaborator
     */
    public int getCollaborator()
    {
        return collaborator;
    }

    /**
     * @param collaborator the collaborator to set
     */
    public void setCollaborator(int collaborator)
    {
        this.collaborator = collaborator;
    }
}
