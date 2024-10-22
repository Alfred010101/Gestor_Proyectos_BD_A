
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Resources;
import utils.ConnectionBD;

/**
 *
 * @author Alfred
 */
public class ResourcesDAO
{
    public static List<Resources> getResources()
    {
        String query = "SELECT * FROM Recursos";
        List<Resources> resourcesList = new ArrayList<>();
        try (Connection connection = ConnectionBD.getConnection(); PreparedStatement statement = connection.prepareStatement(query); ResultSet resultSet = statement.executeQuery())
        {
            while (resultSet.next())
            {
                int id = resultSet.getInt("Id_recurso");
                String name = resultSet.getString("Nombre");
                int cantidadTotal = resultSet.getInt("Cantidad_total");
                int cantidadDisponible = resultSet.getInt("Cantidad_disponible");

                Resources resource = new Resources(id, name, cantidadDisponible, cantidadTotal);
                resourcesList.add(resource);
            }
        } catch (SQLException ex)
        {
            System.out.println(ex);
        } catch (Exception e)
        {
            System.out.println(e);
        }
        return resourcesList;
    }
}
