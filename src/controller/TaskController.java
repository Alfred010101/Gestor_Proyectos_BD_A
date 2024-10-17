
package controller;

import dao.TaskDAO;
import java.util.List;
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
}
