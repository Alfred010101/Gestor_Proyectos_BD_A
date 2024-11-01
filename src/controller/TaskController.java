
package controller;

import dao.TaskDAO;
import java.util.List;
import java.util.Set;
import model.Task;

/**
 *
 * @author Alfred
 */

public class TaskController
{
    public static List<Task> getMisTareas(int id_empleado)
    {
        //Aqui deben ir validaciones
        return TaskDAO.getTareas(id_empleado);
    }
    
    public static  List<Task> getSusTareasFiltradas(int id_empleado, Set<String> proyectos, Set<String> estados,  String ordenarPor, String forma)
    {
        return TaskDAO.getSusTareasFiltradas(id_empleado, proyectos, estados,ordenarPor, forma);
    }
}
