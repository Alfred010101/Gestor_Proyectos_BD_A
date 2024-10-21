
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
public class RoleDAO
{
    public static String getRole(int id)
    {
        String query = "SELECT Rol FROM Roles WHERE Id_rol = ?";
        String role = "";
        try (Connection connection = ConnectionBD.getConnection(); PreparedStatement statement = connection.prepareStatement(query);)
        {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
            {
                role = resultSet.getString("Rol");
            }
        } catch (SQLException ex)
        {
            System.out.println(ex);
        } catch (Exception e)
        {
            System.out.println(e);
        }
        return role;
    }
}
