
package controller;

import dao.CollaboratorDAO;
import java.util.List;

/**
 *
 * @author Alfred
 */
public class CollaboratorController
{
     public static List<String> getSusProyectos(int id_empleado)
    {
        return CollaboratorDAO.getSusProyectos(id_empleado);
    }
}
