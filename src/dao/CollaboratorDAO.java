
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Collaborator;
import utils.ConnectionBD;

/**
 *
 * @author Alfred
 */

public class CollaboratorDAO
{
    public static List<Collaborator> getColaborators()
    {
        String query = "SELECT * FROM Colaboradores";
        List<Collaborator> colaboratosList = new ArrayList<>();
        try (Connection connection = ConnectionBD.getConnection(); PreparedStatement statement = connection.prepareStatement(query); ResultSet resultSet = statement.executeQuery())
        {
            while (resultSet.next())
            {
                int pkId = resultSet.getInt("pk_id");
                int proyecto = resultSet.getInt("fk_proyecto");
                int colaborador = resultSet.getInt("fk_colaborador");

                colaboratosList.add(new Collaborator(pkId,proyecto, colaborador));

            }
        } catch (SQLException ex)
        {
            System.out.println("SQLException : " + ex);
        } catch (Exception e)
        {
            System.out.println("Exception : " + e);
        }
        return colaboratosList;
    }

    public static Collaborator getColaborator(int id)
    {
        String query = "SELECT * FROM Colaboradores WHERE pk_id = ?";
        try (Connection connection = ConnectionBD.getConnection(); PreparedStatement statement = connection.prepareStatement(query);)
        {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
            {
                int pkId = resultSet.getInt("pk_id");
                int proyecto = resultSet.getInt("fk_proyecto");
                int colaborador = resultSet.getInt("fk_colaborador");


                return new Collaborator(pkId, proyecto, colaborador);

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
