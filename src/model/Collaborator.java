
package model;

/**
 *
 * @author Alfred
 */

public class Collaborator
{
    private final int id;
    private final int project;
    private final int collaborator;

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
     * @return the project
     */
    public int getProject()
    {
        return project;
    }

    /**
     * @return the collaborator
     */
    public int getCollaborator()
    {
        return collaborator;
    }
}
