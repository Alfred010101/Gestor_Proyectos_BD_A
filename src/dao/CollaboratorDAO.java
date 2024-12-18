package dao;

import controller.ProjectController;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import model.Collaborator;
import model.Staff;
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

                colaboratosList.add(new Collaborator(pkId, proyecto, colaborador));

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

    public static List<String> getSusProyectos(int id_empleado)
    {
        List<String> projects = new ArrayList<>();
        String query = "SELECT nombre "
                + "FROM Proyectos "
                + "WHERE pk_id IN ("
                + "    SELECT fk_proyecto "
                + "    FROM Colaboradores "
                + "    WHERE fk_colaborador = ?"
                + ")"
                + "ORDER BY nombre ASC;";

        try (Connection connection = ConnectionBD.getConnection(); PreparedStatement statement = connection.prepareStatement(query);)
        {
            statement.setInt(1, id_empleado);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next())
            {
                projects.add(resultSet.getString("nombre"));
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

    public static List<Staff> getColaboradores(int id_empleado, String proyecto)
    {
        List<Staff> colaboradores = new ArrayList<>();
        String query = "SELECT p.* "
                + "FROM Personal p "
                + "JOIN Colaboradores c ON p.pk_id = c.fk_colaborador "
                + "JOIN Proyectos pr ON c.fk_proyecto = pr.pk_id "
                + "WHERE pr.nombre = ? AND pr.fk_lider = ?";

        try (Connection connection = ConnectionBD.getConnection(); PreparedStatement statement = connection.prepareStatement(query);)
        {
            statement.setString(1, proyecto);
            statement.setInt(2, id_empleado);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next())
            {
                int pkId = resultSet.getInt("pk_id");
                int rol = resultSet.getInt("fk_rol");
                int departamento = resultSet.getInt("fk_departamento");
                String nombre = resultSet.getString("nombre");
                String apPaterno = resultSet.getString("ap_paterno");
                String apMaterno = resultSet.getString("ap_materno");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                String direccion = resultSet.getString("direccion");
                String numeroTel = resultSet.getString("telefono");

                colaboradores.add(new Staff(pkId, rol, departamento, nombre, apPaterno, apMaterno, password, email, direccion, numeroTel));
            }
        } catch (SQLException ex)
        {
            System.out.println("SQLException : " + ex);
        } catch (Exception e)
        {
            System.out.println("Exception : " + e);
        }
        return colaboradores;
    }
}
