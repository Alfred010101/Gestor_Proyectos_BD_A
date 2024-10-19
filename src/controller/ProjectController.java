
package controller;

import dao.ProjectDAO;
import java.util.List;
import model.Project;

/**
 *
 * @author Alfred
 */
public class ProjectController
{
    public static List<Object[]> getMisProyectos(int id_empleado)
    {
        //Aqui deben ir validaciones
        return ProjectDAO.getProyects(id_empleado);
    }
}
