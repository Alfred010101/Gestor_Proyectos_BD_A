
package controller;

import dao.CollaboratorDAO;
import java.util.Set;

/**
 *
 * @author Alfred
 */
public class CollaboratorController
{
     public static Set<String> getSusProyectos(int id_empleado)
    {
        return CollaboratorDAO.getSusProyectos(id_empleado);
    }
}
