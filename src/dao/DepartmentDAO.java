
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.ConnectionBD;

/**
 *
 * @author Alfred
 */

public class DepartmentDAO
{
    public static String getNameDepartment(int id)
    {
        String query = "SELECT Departamento FROM Departamentos WHERE Id_departamento = ?";
        String name = "";
        try (Connection connection = ConnectionBD.getConnection(); PreparedStatement statement = connection.prepareStatement(query);)
        {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
            {
                name = resultSet.getString("Departamento");
            }
        } catch (SQLException ex)
        {
            System.out.println(ex);
        } catch (Exception e)
        {
            System.out.println(e);
        }
        return name;
    }
}
