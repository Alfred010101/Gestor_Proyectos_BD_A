
package controller;

import dao.CollaboratorDAO;
import java.util.List;
import model.Staff;

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
    
    public static List<Staff> getColaboradores(int id_empleado, String proyecto)
    {
        return CollaboratorDAO.getColaboradores(id_empleado, proyecto);
    }
}
