package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import model.Staff;
import utils.ConnectionBD;

/**
 *
 * @author Alfred
 */
public class StaffDAO
{

    public static int validateCredentials(int id, String password)
    {
        String query = "SELECT pk_id FROM Personal WHERE pk_id = ? AND password = ?";

        try (Connection connection = ConnectionBD.getConnection(); PreparedStatement statement = connection.prepareStatement(query);)
        {
            statement.setInt(1, id);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
            {
                return resultSet.getInt("pk_id");
            }
        } catch (SQLException ex)
        {
            System.out.println(ex);
        } catch (Exception e)
        {
            System.out.println(e);
            return -2;
        }
        return -1;
    }

    public static List<Staff> getEmployees()
    {
        String query = "SELECT * "
                + "FROM Personal";
        List<Staff> staffList = new ArrayList<>();
        try (Connection connection = ConnectionBD.getConnection(); PreparedStatement statement = connection.prepareStatement(query); ResultSet resultSet = statement.executeQuery())
        {
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

                staffList.add(new Staff(pkId, rol, departamento, nombre, apPaterno, apMaterno, password, email, direccion, numeroTel));
            }
        } catch (SQLException ex)
        {
            System.out.println("SQLException : " + ex);
        } catch (Exception e)
        {
            System.out.println("Exception : " + e);
        }
        return staffList;
    }

    public static Staff getStaff(int id)
    {
        String query = "SELECT * "
                + "FROM Personal "
                + "WHERE pk_id = ?";
        try (Connection connection = ConnectionBD.getConnection(); PreparedStatement statement = connection.prepareStatement(query);)
        {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
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
                nombre = (nombre == null) ? "" : nombre;
                return new Staff(pkId, rol, departamento, nombre, apPaterno, apMaterno, password, email, direccion, numeroTel);

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

    public static String getStaffRol(int id)
    {
        String query = "SELECT Roles.nombre "
                + "FROM Roles "
                + "WHERE pk_id = "
                + "(SELECT Personal.fk_rol "
                + "FROM Personal "
                + "WHERE pk_id = ?)";
        try (Connection connection = ConnectionBD.getConnection(); PreparedStatement statement = connection.prepareStatement(query);)
        {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
            {
                return resultSet.getString("Roles.nombre");
            }
        } catch (SQLException ex)
        {
            System.out.println("SQLException : " + ex);
        } catch (Exception e)
        {
            System.out.println("Exception : " + e);
        }
        return "";
    }

    public static String getStaffDepartment(int id)
    {
        String query = "SELECT Departamnetos.nombre "
                + "FROM Departamnetos "
                + "WHERE pk_id = "
                + "(SELECT Personal.fk_departamento "
                + "FROM Personal "
                + "WHERE pk_id = ?)";
        try (Connection connection = ConnectionBD.getConnection(); PreparedStatement statement = connection.prepareStatement(query);)
        {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
            {
                return resultSet.getString("Departamnetos.nombre");
            }
        } catch (SQLException ex)
        {
            System.out.println("SQLException : " + ex);
        } catch (Exception e)
        {
            System.out.println("Exception : " + e);
        }
        return "";
    }
}
