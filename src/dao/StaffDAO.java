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

    public static int validateCredentials(String username, String password)
    {
        String query = "SELECT Id_empleado FROM Personal WHERE Usuario = ? AND Password = ?";

        try (Connection connection = ConnectionBD.getConnection(); PreparedStatement statement = connection.prepareStatement(query);)
        {
            // asigar valor a los parametros de consulta
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            // Procesar resultados
            if (resultSet.next())
            {
                return resultSet.getInt("Id_empleado");
            }
        } catch (SQLException ex)
        {
//            Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e)
        {
//            System.out.println("Null pointer");
            return -2;
        }
        return -1;
    }

    public static Staff getStaff(int id_empleado)
    {
        String query = "SELECT * FROM Personal WHERE Id_empleado = ?";

        try (Connection connection = ConnectionBD.getConnection(); PreparedStatement statement = connection.prepareStatement(query);)
        {
            statement.setInt(1, id_empleado);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
            {
                int id = resultSet.getInt("Id_empleado");
                int role = resultSet.getInt("Id_rol");
                int departament = resultSet.getInt("Id_departamento");
                String name = resultSet.getString("Nombre");
                String user = resultSet.getString("Usuario");
                String pass = resultSet.getString("Password");
                String email = resultSet.getString("E-mail");
                String address = resultSet.getString("Direccion");
                int phoneNumber = resultSet.getInt("Telefono");

                return new Staff(id, role, departament, name, user, pass, email, address, phoneNumber);

            }
        } catch (SQLException ex)
        {
            System.out.println(ex);
        } catch (Exception e)
        {
            System.out.println(e);
        }
        return null;
    }

    public static List<Staff> getEmployees()
    {
        String query = "SELECT * FROM Personal";
        List<Staff> staffList = new ArrayList<>();
        try (Connection connection = ConnectionBD.getConnection(); PreparedStatement statement = connection.prepareStatement(query); ResultSet resultSet = statement.executeQuery())
        {
            while (resultSet.next())
            {
                int id = resultSet.getInt("Id_empleado");
                int role = resultSet.getInt("Id_rol");
                int department = resultSet.getInt("Id_departamento");
                String name = resultSet.getString("Nombre");
                String user = resultSet.getString("Usuario");
                String pass = resultSet.getString("Password");
                String email = resultSet.getString("E-mail");
                String address = resultSet.getString("Direccion");
                int phoneNumber = resultSet.getInt("Telefono");

                Staff staff = new Staff(id, role, department, name, user, pass, email, address, phoneNumber);
                staffList.add(staff);
            }
        } catch (SQLException ex)
        {
            System.out.println(ex);
        } catch (Exception e)
        {
            System.out.println(e);
        }
        return staffList;
    }
    
    public static String getEmployeeRole(int id_rol)
    {
        String query = "SELECT Rol FROM Roles WHERE Id_roll = ?";
        String name = "";
        try (Connection connection = ConnectionBD.getConnection(); PreparedStatement statement = connection.prepareStatement(query))
        {
            statement.setInt(1, id_rol);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
            {
                name = resultSet.getString("Rol");
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
    public static String getEmployeeDepartment(int id_departamento)
    {
        String query = "SELECT Departamento FROM Departamentos WHERE Id_departamento = ?";
        String name = "";
        try (Connection connection = ConnectionBD.getConnection(); PreparedStatement statement = connection.prepareStatement(query); ResultSet resultSet = statement.executeQuery())
        {
            if (resultSet.next())
            {
                name = resultSet.getString("Nombre");
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
    public static String getEmployeeName()
    {
        String query = "SELECT Nombre FROM Personal";
        String name = "";
        try (Connection connection = ConnectionBD.getConnection(); PreparedStatement statement = connection.prepareStatement(query); ResultSet resultSet = statement.executeQuery())
        {
            if (resultSet.next())
            {
                name = resultSet.getString("Nombre");
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
