
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Department;
import utils.ConnectionBD;

/**
 *
 * @author Alfred
 */

public class DepartmentDAO
{    
    public static List<Department> getDepartments()
    {
        String query = "SELECT * FROM Departamentos";
        List<Department> departmentsList = new ArrayList<>();
        try (Connection connection = ConnectionBD.getConnection(); PreparedStatement statement = connection.prepareStatement(query); ResultSet resultSet = statement.executeQuery())
        {
            while (resultSet.next())
            {
                int id = resultSet.getInt("Id_departamento");
                int manager = resultSet.getInt("Id_jefe");
                String department = resultSet.getString("Departamento");
                int phoneNumber = resultSet.getInt("Telefono_departamento");

                Department departments = new Department(id, manager, department, phoneNumber);
                departmentsList.add(departments);
            }
        } catch (SQLException ex)
        {
            System.out.println(ex);
        } catch (Exception e)
        {
            System.out.println(e);
        }
        return departmentsList;
    }
    
    public static String getDepartmentName(int id)
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
    
    public static String getDepartmentJefe(int id)
    {
//        String query = "SELECT Personal.Id_empleado FROM Departamentos INNER JOIN Personal WHERE Departamento.Id_jefe = Personal.Id_empleado";
//        String query = "SELECT Personal.Nombre FROM Personal WHERE Personal.Id_empleado = (SELECT Departamentos.Id_jefe FROM Departamentos WHERE Departamentos.Id_departamento = ?)";
        String query = "SELECT Personal.Nombre FROM Personal WHERE Personal.Id_empleado = ?";
//        String query = "SELECT Personal.Nombre FROM Personal WHERE Personal.Id_empleado = ?";
        String jefe = "";
        try (Connection connection = ConnectionBD.getConnection(); PreparedStatement statement = connection.prepareStatement(query);)
        {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
            {
                jefe = resultSet.getString("Personal.Nombre");
            }
        } catch (SQLException ex)
        {
            System.out.println(ex);
        } catch (Exception e)
        {
            System.out.println(e);
        }
        return jefe;
    }
}
