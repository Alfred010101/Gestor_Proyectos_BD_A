
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Project;
import utils.ConnectionBD;

/**
 *
 * @author Alfred
 */
public class ProjectDAO
{
    public static List<Project> getProyects()
    {
        String query = "SELECT * FROM Proyectos";
        List<Project> projects = new ArrayList();        
        try (Connection connection = ConnectionBD.getConnection(); PreparedStatement statement = connection.prepareStatement(query);ResultSet resultSet = statement.executeQuery())
        {
            while (resultSet.next())
            {
                int pkId = resultSet.getInt("pk_id");
                int lider = resultSet.getInt("fk_lider");
                String nombre = resultSet.getString("nombre");
                int estado = resultSet.getInt("estado");
                Date fechaInicio = resultSet.getDate("fecha_inicio");
                Date fechaTermino = resultSet.getDate("fecha_termino");
                String descripcion = resultSet.getString("descripcion");
                projects.add(new Project(pkId, lider, nombre, estado, descripcion, fechaInicio, fechaTermino));
            }
        } catch (SQLException ex)
        {
            System.out.println("SQLException : " + ex);
        } catch (Exception e)
        {
            System.out.println("Exception : " + e);
        }
        return projects;
    }
    
    public static Project getProyect(int id)
    {
        String query = "SELECT * FROM Proyectos WHERE pk_id = ?";    
        try (Connection connection = ConnectionBD.getConnection(); PreparedStatement statement = connection.prepareStatement(query);)
        {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
            {
                int pkId = resultSet.getInt("pk_id");
                int lider = resultSet.getInt("fk_lider");
                String nombre = resultSet.getString("nombre");
                int estado = resultSet.getInt("estado");
                Date fechaInicio = resultSet.getDate("fecha_inicio");
                Date fechaTermino = resultSet.getDate("fecha_termino");
                String descripcion = resultSet.getString("descripcion");
                return new Project(pkId, lider, nombre, estado, descripcion, fechaInicio, fechaTermino);
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
    
    public static List<Object[]> getProyects(int id_empleado)
    {
        List<Object[]> projects = new ArrayList();
        String query = "SELECT nombre FROM Proyectos WHERE Id_responsable = ?";

        try (Connection connection = ConnectionBD.getConnection(); PreparedStatement statement = connection.prepareStatement(query);)
        {
            statement.setInt(1, id_empleado);
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next())
            {
                String description = resultSet.getString("nombre");
                projects.add(new Object[]{description});
            }
        } catch (SQLException ex)
        {
            System.out.println("SQLException : " + ex);
        } catch (Exception e)
        {
            System.out.println("Exception : " + e);
        }
        return projects;
    }
    
    public static String obtenerCampo(int id, String campo)
    {
        String resultado= "";
        String query = "SELECT " + campo +" FROM Proyectos WHERE pk_id= ?";

         try (Connection connection = ConnectionBD.getConnection(); PreparedStatement statement = connection.prepareStatement(query);)
        {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
            {
                resultado = resultSet.getString(campo);               
            }
        } catch (SQLException ex)
        {
            System.out.println("SQLException : " + ex);
        } catch (Exception e)
        {
            System.out.println("Exception : " + e);
        }
        return resultado;
    }
}
