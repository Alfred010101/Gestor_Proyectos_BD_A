package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Task;
import utils.ConnectionBD;

/**
 *
 * @author Alfred
 */
public class TaskDAO
{

    public static List<Task> getTasks()
    {
        String query = "SELECT * FROM Tareas";
        List<Task> tasks = new ArrayList();
        try (Connection connection = ConnectionBD.getConnection(); PreparedStatement statement = connection.prepareStatement(query); ResultSet resultSet = statement.executeQuery())
        {
            while (resultSet.next())
            {
                int pkId = resultSet.getInt("pk_id");
                int proyecto = resultSet.getInt("fk_proyecto");
                int responsable = resultSet.getInt("fk_responsable");
                String descripcion = resultSet.getString("descripcion");
                int estado = resultSet.getInt("estado");
                Date fechaInicio = resultSet.getDate("fecha_inicio");
                Date fechaTermino = resultSet.getDate("fecha_termino");
                Date fechaprogramadaTermino = resultSet.getDate("fecha_programada_termino");

                tasks.add(new Task(pkId, proyecto, responsable, estado, descripcion, fechaInicio, fechaTermino, fechaprogramadaTermino));

            }
        } catch (SQLException ex)
        {
            System.out.println("SQLException : " + ex);
        } catch (Exception e)
        {
            System.out.println("Exception : " + e);
        }
        return tasks;
    }

    public static Task getTask(int id)
    {
        String query = "SELECT * FROM Tareas WHERE pk_id = ?";
        try (Connection connection = ConnectionBD.getConnection(); PreparedStatement statement = connection.prepareStatement(query);)
        {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
            {
                int pkId = resultSet.getInt("pk_id");
                int proyecto = resultSet.getInt("fk_proyecto");
                int responsable = resultSet.getInt("fk_responsable");
                String descripcion = resultSet.getString("descripcion");
                int estado = resultSet.getInt("estado");
                Date fechaInicio = resultSet.getDate("fecha_inicio");
                Date fechaTermino = resultSet.getDate("fecha_termino");
                Date fechaprogramadaTermino = resultSet.getDate("fecha_programada_termino");

                return new Task(pkId, proyecto, responsable, estado, descripcion, fechaInicio, fechaTermino, fechaprogramadaTermino);

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

    public static List<Task> getTareas(int id_empleado)
    {
        List<Task> tareas = new ArrayList();
        String query = "SELECT * FROM Tareas WHERE Id_responsable = ?";

        try (Connection connection = ConnectionBD.getConnection(); PreparedStatement statement = connection.prepareStatement(query);)
        {
            statement.setInt(1, id_empleado);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next())
            {
                int id = resultSet.getInt("Id_tarea");
//                int project = resultSet.getInt("Id_proyecto");
                int responsible = resultSet.getInt("Id_responsable");
                int state = resultSet.getInt("Estado");
                String description = resultSet.getString("Descripcion");
                Date startDate = resultSet.getDate("Fecha_inicio");
                Date endDate = resultSet.getDate("Fecha_termino");
                Date expectedDate = resultSet.getDate("Fecha_esperada");
                //El 3 no debe ir hay
                tareas.add(new Task(id, 3, responsible, state, description, startDate, endDate, expectedDate));
            }
        } catch (SQLException ex)
        {
            System.out.println("SQLException : " + ex);
        } catch (Exception e)
        {
            System.out.println("Exception : " + e);
        }
        return tareas;
    }
}
