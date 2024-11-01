
package controller;

import dao.ProjectDAO;
import java.util.List;

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
    
    public static String obtenerNombre(int id)
    {
        return ProjectDAO.obtenerCampo(id,"nombre");

    }
    public static String obtenerCampo(String campo, String campoWhere, String valor)
    {
        return ProjectDAO.obtenerCampo(campo, campoWhere, valor);
    }
}
