
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
}
