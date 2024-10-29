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
                int pkId = resultSet.getInt("pk_id");
                int jefe = resultSet.getInt("fk_jefe");
                String departamento = resultSet.getString("nombre");
                String numeroTel = resultSet.getString("telefono");

                departmentsList.add(new Department(pkId, jefe, departamento, numeroTel));

            }
        } catch (SQLException ex)
        {
            System.out.println("SQLException : " + ex);
        } catch (Exception e)
        {
            System.out.println("Exception : " + e);
        }
        return departmentsList;
    }

    public static Department getDepartment(int id)
    {
        String query = "SELECT * FROM Departamentos WHERE pk_id = ?";
        try (Connection connection = ConnectionBD.getConnection(); PreparedStatement statement = connection.prepareStatement(query);)
        {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
            {
                int pkId = resultSet.getInt("pk_id");
                int jefe = resultSet.getInt("fk_jefe");
                String departamento = resultSet.getString("nombre");
                String numeroTel = resultSet.getString("telefono");

                return new Department(pkId, jefe, departamento, numeroTel);

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
    
    public static String getDepartmentGerente(int id)
    {
        String query = "SELECT nombre, ap_paterno, ap_materno "
                + "FROM Personal "
                + "WHERE pk_id =  ?";
        try (Connection connection = ConnectionBD.getConnection(); PreparedStatement statement = connection.prepareStatement(query);)
        {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
            {
                return resultSet.getString("nombre") + " " + resultSet.getString("ap_paterno")  + " " + resultSet.getString("ap_materno");
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
