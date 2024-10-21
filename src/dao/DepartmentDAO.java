
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
}
