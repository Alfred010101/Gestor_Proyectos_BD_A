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
                String titulo = resultSet.getString("titulo");
                String descripcion = resultSet.getString("descripcion");
                int estado = resultSet.getInt("estado");
                Date fechaInicio = resultSet.getDate("fecha_inicio");
                Date fechaTermino = resultSet.getDate("fecha_termino");
                Date fechaprogramadaTermino = resultSet.getDate("fecha_programada_termino");

                tasks.add(new Task(pkId, proyecto, responsable, estado, titulo, descripcion, fechaInicio, fechaTermino, fechaprogramadaTermino));

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
                String titulo = resultSet.getString("titulo");
                String descripcion = resultSet.getString("descripcion");
                int estado = resultSet.getInt("estado");
                Date fechaInicio = resultSet.getDate("fecha_inicio");
                Date fechaTermino = resultSet.getDate("fecha_termino");
                Date fechaprogramadaTermino = resultSet.getDate("fecha_programada_termino");

                return new Task(pkId, proyecto, responsable, estado, titulo, descripcion, fechaInicio, fechaTermino, fechaprogramadaTermino);

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
        String query = "SELECT * FROM Tareas WHERE fk_responsable = ?";

        try (Connection connection = ConnectionBD.getConnection(); PreparedStatement statement = connection.prepareStatement(query);)
        {
            statement.setInt(1, id_empleado);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next())
            {
                int pkId = resultSet.getInt("pk_id");
                int proyecto = resultSet.getInt("fk_proyecto");
                int responsable = resultSet.getInt("fk_responsable");
                String titulo = resultSet.getString("titulo");
                String descripcion = resultSet.getString("descripcion");
                int estado = resultSet.getInt("estado");
                Date fechaInicio = resultSet.getDate("fecha_inicio");
                Date fechaTermino = resultSet.getDate("fecha_termino");
                Date fechaprogramadaTermino = resultSet.getDate("fecha_programada_termino");
                tareas.add(new Task(pkId, proyecto, responsable, estado, titulo, descripcion, fechaInicio, fechaTermino, fechaprogramadaTermino));
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
