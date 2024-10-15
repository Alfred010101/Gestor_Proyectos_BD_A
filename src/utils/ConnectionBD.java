package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Alfred
 */
public class ConnectionBD
{
    // Ruta de la base de datos Access
    // Ajusta la ruta...
    private static final String PATH = "C:/Users/Alfred/Documents/NetBeansProjects/Gestor_Proyectos_BD_A/src/BD/Gestion1.accdb";
    private static final String URL = "jdbc:ucanaccess://" + PATH;

    public static Connection getConnection()
    {
        Connection connection = null;
        try
        {
            connection = DriverManager.getConnection(URL);
//            System.out.println("Conexión a la base de datos establecida con éxito.");
            return connection;
        } catch (SQLException e)
        {
        }
        return connection;
    }
}
