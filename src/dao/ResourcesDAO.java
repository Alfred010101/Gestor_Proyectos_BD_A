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
                int pkId = resultSet.getInt("pk_id");
                String name = resultSet.getString("nombre");
                int cantidadTotal = resultSet.getInt("total");
                int cantidadDisponible = resultSet.getInt("disponible");

                resourcesList.add(new Resources(pkId, name, cantidadTotal, cantidadDisponible));

            }
        } catch (SQLException ex)
        {
            System.out.println("SQLException : " + ex);
        } catch (Exception e)
        {
            System.out.println("Exception : " + e);
        }
        return resourcesList;
    }
    
    public static Resources getResource(int id)
    {
        String query = "SELECT * FROM Recursos WHERE pk_id = ?";
        try (Connection connection = ConnectionBD.getConnection(); PreparedStatement statement = connection.prepareStatement(query);)
        {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
            {
                int pkId = resultSet.getInt("pk_id");
                String name = resultSet.getString("nombre");
                int cantidadTotal = resultSet.getInt("total");
                int cantidadDisponible = resultSet.getInt("disponible");

                return new Resources(pkId, name, cantidadDisponible, cantidadTotal);

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
