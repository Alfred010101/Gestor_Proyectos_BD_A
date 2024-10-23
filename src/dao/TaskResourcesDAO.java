
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.TaskResources;
import utils.ConnectionBD;

/**
 *
 * @author Alfred
 */

public class TaskResourcesDAO
{
    public static List<TaskResources> getTasksResources()
    {
        String query = "SELECT * FROM RecursosTarea";
        List<TaskResources> taskResourcesList = new ArrayList<>();
        try (Connection connection = ConnectionBD.getConnection(); PreparedStatement statement = connection.prepareStatement(query); ResultSet resultSet = statement.executeQuery())
        {
            while (resultSet.next())
            {
                int pkId = resultSet.getInt("pk_id");
                int recurso = resultSet.getInt("fk_recurso");
                int tarea = resultSet.getInt("fk_tarea");
                int cantidad = resultSet.getInt("cantidad");

                taskResourcesList.add(new TaskResources(pkId,recurso, tarea, cantidad));

            }
        } catch (SQLException ex)
        {
            System.out.println("SQLException : " + ex);
        } catch (Exception e)
        {
            System.out.println("Exception : " + e);
        }
        return taskResourcesList;
    }

    public static TaskResources getTaskResources(int id)
    {
        String query = "SELECT * FROM RecursosTarea WHERE pk_id = ?";
        try (Connection connection = ConnectionBD.getConnection(); PreparedStatement statement = connection.prepareStatement(query);)
        {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
            {
                int pkId = resultSet.getInt("pk_id");
                int recurso = resultSet.getInt("fk_recurso");
                int tarea = resultSet.getInt("fk_tarea");
                int cantidad = resultSet.getInt("cantidad");
                
                return new TaskResources(pkId,recurso, tarea, cantidad);

            }
        } catch (SQLException ex)
        {
            System.out.println("SQLException : " + ex);
        } catch (Exception e)
        {
            System.out.println("Exception : " + e);
        }
        return null;
    }
}
