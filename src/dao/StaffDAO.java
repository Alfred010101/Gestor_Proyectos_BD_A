package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
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
        }catch(Exception e)
        {
//            System.out.println("Null pointer");
            return -2;
        }
        return -1;
    }
    
//    public static Staff getStaff(String username, String password)
//    {
//        String query = "SELECT * FROM Personal WHERE Usuario = ? AND Contraseña = ?";
//
//        try (Connection connection = ConnectionBD.getConnection(); PreparedStatement statement = connection.prepareStatement(query);)
//        {
//            // asigar valor a los parametros de consulta
//            statement.setString(1, username);
//            statement.setString(2, password);            
//
//            ResultSet resultSet = statement.executeQuery();
//
//            // Procesar resultados
//            if (resultSet.next())
//            {
//                int id = resultSet.getInt("Id_empleado");
//                int role = resultSet.getInt("Id_rol");
//                int departament = resultSet.getInt("Id_departamento");
//                String name = resultSet.getString("Nombre");
//                String user = resultSet.getString("Usuario");
//                String pass = resultSet.getString("Password");
//                String email = resultSet.getString("E-mail");
//                String address = resultSet.getString("Direccion");
//                int phoneNumber = resultSet.getInt("Telefono");
//                
//                return new Staff(id, role, departament, name, user, pass, email, address, phoneNumber);
//                            
//            }
//        } catch (SQLException ex)
//        {
////            Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }catch(Exception e)
//        {
////            System.out.println("Null pointer");
//        }
//        return null;
//    }
    
    public static Staff getStaff(int id_empleado)
    {
        String query = "SELECT * FROM Personal WHERE Id_empleado = ?";

        try (Connection connection = ConnectionBD.getConnection(); PreparedStatement statement = connection.prepareStatement(query);)
        {
            // asigar valor a los parametros de consulta
            statement.setString(1, String.valueOf(id_empleado));
            
            ResultSet resultSet = statement.executeQuery();

            // Procesar resultados
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
//            Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
        }catch(Exception e)
        {
//            System.out.println("Null pointer");
        }
        return null;
    }
}
