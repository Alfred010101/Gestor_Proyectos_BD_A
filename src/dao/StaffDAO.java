package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import model.Staff;
import utils.ConnectionBD;
import utils.Var;

/**
 *
 * @author Alfred
 */
public class StaffDAO
{

    public static int validateCredentials(int id, String password)
    {
        String query = "SELECT fk_rol "
                + "FROM Personal "
                + "WHERE pk_id = ? AND password = ?";

        try (Connection connection = ConnectionBD.getConnection(); PreparedStatement statement = connection.prepareStatement(query);)
        {
            statement.setInt(1, id);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
            {
                return resultSet.getInt("fk_rol");
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

//    public static List<Map<String, Object>> getEmployees(Set<String> campos, Set<String> roles, Set<String> departamentos)
//    {
//        List<String> camposList = new ArrayList<>(campos);
//        String camposSeleccionados = String.join(", ", camposList);
//
//        String query = "SELECT " + camposSeleccionados
//                + " FROM Personal";
//        
//        List<Map<String, Object>> empleados = new ArrayList<>();
//
//        try (Connection connection = ConnectionBD.getConnection(); PreparedStatement statement = connection.prepareStatement(query))
//        {
//            ResultSet resultSet = statement.executeQuery();
//            while (resultSet.next())
//            {
//                Map<String, Object> empleado = new HashMap<>();
//                for (String campo : camposList)
//                {
//                    empleado.put(campo, resultSet.getObject(campo)); // Almacenamos el valor del campo en un mapa
//                }
//                empleados.add(empleado); // Agregamos el mapa a la lista
//            }
//
//        } catch (SQLException ex)
//        {
//            System.out.println("SQLException : " + ex);
//        } catch (Exception e)
//        {
//            System.out.println("Exception : " + e);
//        }
//
//        return empleados;
//    }
    public static List<Map<String, Object>> getEmployees(Set<String> campos, Set<String> roles, Set<String> departamentos)
    {
        List<String> camposList = new ArrayList<>(campos);
        String camposSeleccionados = String.join(", ", camposList);

        // Construir la consulta base
        StringBuilder queryBuilder
                = new StringBuilder("SELECT ").append(camposSeleccionados)
                        .append(" FROM Personal p")
                        .append(" JOIN Roles r ON p.fk_rol = r.pk_id")
                        .append(" JOIN Departamentos d ON p.fk_departamento = d.pk_id");

        String whereClause = "";

        // Agregar filtro de departamentos
        if (!departamentos.isEmpty())
        {
            whereClause += "d.nombre IN (";
            for (String departamento : departamentos)
            {
                whereClause += "'" + departamento + "', ";
            }
            whereClause = whereClause.substring(0, whereClause.length() - 2); // Eliminar la última coma y espacio
            whereClause += ")";
        }

        // Agregar filtro de roles
        if (!roles.isEmpty())
        {
            if (!whereClause.isEmpty())
            {
                whereClause += " AND ";
            }
            whereClause += "r.nombre IN (";
            for (String role : roles)
            {
                whereClause += "'" + role + "', ";
            }
            whereClause = whereClause.substring(0, whereClause.length() - 2); // Eliminar la última coma y espacio
            whereClause += ")";
        }

        // Agregar la cláusula WHERE completa a la consulta
        if (!whereClause.isEmpty())
        {
            queryBuilder.append(" WHERE ").append(whereClause);
        }

        // Agregar cláusula ORDER BY
        if (Var.opcOrdenadoPersonal > -1 && Var.CAMPOS_ORDENAR_PEROSNAL_CONSULTA[Var.opcOrdenadoPersonal] != null && !Var.CAMPOS_ORDENAR_PEROSNAL_CONSULTA[Var.opcOrdenadoPersonal].isEmpty())
        {
            queryBuilder.append(" ORDER BY ").append(Var.CAMPOS_ORDENAR_PEROSNAL_CONSULTA[Var.opcOrdenadoPersonal]).append(" ").append((Var.opcOrdenadoForPersonal) ?  "DESC" : "ASC");
        }

        List<Map<String, Object>> empleados = new ArrayList<>();

        // Ejecutar la consulta
        try (Connection connection = ConnectionBD.getConnection(); PreparedStatement statement = connection.prepareStatement(queryBuilder.toString()))
        {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                Map<String, Object> empleado = new HashMap<>();
                for (String campo : camposList)
                {
                    empleado.put(campo, resultSet.getObject(campo));
                }
                empleados.add(empleado);
            }
        } catch (SQLException ex)
        {
            System.out.println("SQLException : " + ex);
        } catch (Exception e)
        {
            System.out.println("Exception : " + e);
        }

        return empleados;
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
        String query = "SELECT Departamentos.nombre "
                + "FROM Departamentos "
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
                return resultSet.getString("Departamentos.nombre");
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

    public static Set<String> getStaffDepartmets()
    {
        String query = "SELECT DISTINCT d.nombre "
                + "FROM Personal p "
                + "JOIN Departamentos d "
                + "ON p.fk_departamento = d.pk_id";
        Set<String> departamentos = new LinkedHashSet<>();
        try (Connection connection = ConnectionBD.getConnection(); PreparedStatement statement = connection.prepareStatement(query); ResultSet resultSet = statement.executeQuery())
        {
            while (resultSet.next())
            {
                String nombre = resultSet.getString("nombre");
                departamentos.add(nombre);
            }
        } catch (SQLException ex)
        {
            System.out.println("SQLException : " + ex);
        } catch (Exception e)
        {
            System.out.println("Exception : " + e);
        }
        return departamentos;
    }

    public static int getStaffNoDepartmets()
    {
        String query = "SELECT COUNT(DISTINCT d.nombre) "
                + "AS numero_departamentos "
                + "FROM Personal p "
                + "JOIN Departamentos d "
                + "ON p.fk_departamento = d.pk_id";
        int numDepartamentos = 0;
        try (Connection connection = ConnectionBD.getConnection(); PreparedStatement statement = connection.prepareStatement(query); ResultSet resultSet = statement.executeQuery())
        {
            if (resultSet.next())
            {
                numDepartamentos = resultSet.getInt("numero_departamentos");
            }

        } catch (SQLException ex)
        {
            System.out.println("SQLException : " + ex);
        } catch (Exception e)
        {
            System.out.println("Exception : " + e);
        }
        return numDepartamentos;
    }

    public static Set<String> getStaffRoles()
    {
        String query = "SELECT DISTINCT r.nombre "
                + "FROM Personal p "
                + "JOIN Roles r "
                + "ON p.fk_rol = r.pk_id";
        Set<String> roles = new LinkedHashSet<>();
        try (Connection connection = ConnectionBD.getConnection(); PreparedStatement statement = connection.prepareStatement(query); ResultSet resultSet = statement.executeQuery())
        {
            while (resultSet.next())
            {
                String nombre = resultSet.getString("nombre");
                roles.add(nombre);
            }
        } catch (SQLException ex)
        {
            System.out.println("SQLException : " + ex);
        } catch (Exception e)
        {
            System.out.println("Exception : " + e);
        }
        return roles;
    }

    public static int getStaffNoRoles()
    {
        String query = "SELECT COUNT(DISTINCT r.nombre) "
                + "AS numero_roles "
                + "FROM Personal p "
                + "JOIN Roles r "
                + "ON p.fk_rol = r.pk_id";
        int numRoles = 0;
        try (Connection connection = ConnectionBD.getConnection(); PreparedStatement statement = connection.prepareStatement(query); ResultSet resultSet = statement.executeQuery())
        {
            if (resultSet.next())
            {
                numRoles = resultSet.getInt("numero_roles");
            }

        } catch (SQLException ex)
        {
            System.out.println("SQLException : " + ex);
        } catch (Exception e)
        {
            System.out.println("Exception : " + e);
        }
        return numRoles;
    }
}
