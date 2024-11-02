package dao;

import controller.ProjectController;
import controller.StaffController;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
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
                String proyecto = ProjectController.obtenerNombre(resultSet.getInt("fk_proyecto"));
                String responsable = StaffController.obtenerNombre(resultSet.getInt("fk_responsable"));
                String titulo = resultSet.getString("titulo");
                String descripcion = resultSet.getString("descripcion");
                String estado = switch (resultSet.getInt("estado"))
                {
                    case 1 ->
                        "En Progreso";
                    case 2 ->
                        "Detenida";
                    case 3 ->
                        "En Revicion";
                    case 4 ->
                        "Completada";
                    case 5 ->
                        "Atrasada";
                    default ->
                        "Pendiente";
                };
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
                String proyecto = ProjectController.obtenerNombre(resultSet.getInt("fk_proyecto"));
                String responsable = StaffController.obtenerNombre(resultSet.getInt("fk_responsable"));
                String titulo = resultSet.getString("titulo");
                String descripcion = resultSet.getString("descripcion");
                String estado = switch (resultSet.getInt("estado"))
                {
                    case 1 ->
                        "En Progreso";
                    case 2 ->
                        "Detenida";
                    case 3 ->
                        "En Revicion";
                    case 4 ->
                        "Completada";
                    case 5 ->
                        "Atrasada";
                    default ->
                        "Pendiente";
                };
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
                String proyecto = ProjectController.obtenerNombre(resultSet.getInt("fk_proyecto"));
                String responsable = StaffController.obtenerNombre(resultSet.getInt("fk_responsable"));
                String titulo = resultSet.getString("titulo");
                String descripcion = resultSet.getString("descripcion");
                String estado = switch (resultSet.getInt("estado"))
                {
                    case 1 ->
                        "En Progreso";
                    case 2 ->
                        "Detenida";
                    case 3 ->
                        "En Revicion";
                    case 4 ->
                        "Completada";
                    case 5 ->
                        "Atrasada";
                    default ->
                        "Pendiente";
                };
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
    
    public static List<Task> getTareas(int id_empleado, String nombreProyecto)
    {
        List<Task> tareas = new ArrayList();
        String query = "SELECT * "
                + "FROM Tareas "
                + "WHERE fk_proyecto = (SELECT pk_id FROM Proyectos WHERE nombre = ? AND fk_lider = ?)";

        try (Connection connection = ConnectionBD.getConnection(); PreparedStatement statement = connection.prepareStatement(query);)
        {
            statement.setString(1, nombreProyecto);
            statement.setInt(2, id_empleado);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next())
            {
                int pkId = resultSet.getInt("pk_id");
                String proyecto = ProjectController.obtenerNombre(resultSet.getInt("fk_proyecto"));
                String responsable = StaffController.obtenerNombre(resultSet.getInt("fk_responsable"));
                String titulo = resultSet.getString("titulo");
                String descripcion = resultSet.getString("descripcion");
                String estado = switch (resultSet.getInt("estado"))
                {
                    case 1 ->
                        "En Progreso";
                    case 2 ->
                        "Detenida";
                    case 3 ->
                        "En Revicion";
                    case 4 ->
                        "Completada";
                    case 5 ->
                        "Atrasada";
                    default ->
                        "Pendiente";
                };
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

    public static  List<Task> getSusTareasFiltradas(int id_empleado, Set<String> proyectos, Set<String> estados, String ordenarPor, String forma)
    {
        // Construir la consulta base
        StringBuilder queryBuilder
                = new StringBuilder("SELECT * "
                        + "FROM Tareas t");

        String whereClause = "";

        if (!proyectos.isEmpty())
        {
            whereClause += " AND t.fk_proyecto IN (";
            for (String proyecto : proyectos)
            {
                whereClause += proyecto + ", ";
            }
            whereClause = whereClause.substring(0, whereClause.length() - 2); // Eliminar la última coma y espacio
            whereClause += ")";
        }
        
        if (!estados.isEmpty())
        {
            whereClause += " AND t.estado IN (";
            for (String estado : estados)
            {
                whereClause += estado + ", ";
            }
            whereClause = whereClause.substring(0, whereClause.length() - 2); // Eliminar la última coma y espacio
            whereClause += ")";
        }

        // Agregar la cláusula WHERE completa a la consulta
        if (!whereClause.isEmpty())
        {
            queryBuilder.append(" WHERE fk_responsable = ").append(id_empleado).append(whereClause);
        }else
        {
            queryBuilder.append(" WHERE fk_responsable = ").append(id_empleado);
        }
        
        queryBuilder.append(" ORDER BY ").append(ordenarPor).append(" ").append(forma);
        List<Task> tareas = new ArrayList();
        try (Connection connection = ConnectionBD.getConnection(); PreparedStatement statement = connection.prepareStatement(queryBuilder.toString());)
        {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next())
            {
                int pkId = resultSet.getInt("pk_id");
                String proyecto = ProjectController.obtenerNombre(resultSet.getInt("fk_proyecto"));
                String responsable = StaffController.obtenerNombre(resultSet.getInt("fk_responsable"));
                String titulo = resultSet.getString("titulo");
                String descripcion = resultSet.getString("descripcion");
                String estado = switch (resultSet.getInt("estado"))
                {
                    case 1 ->
                        "En Progreso";
                    case 2 ->
                        "Detenida";
                    case 3 ->
                        "En Revicion";
                    case 4 ->
                        "Completada";
                    case 5 ->
                        "Atrasada";
                    default ->
                        "Pendiente";
                };
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
