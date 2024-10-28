package controller;

import dao.StaffDAO;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.Staff;
import utils.Validations;
import view.WorkspaceAdministrator;
import view.WorkspaceEmployee;

/**
 *
 * @author Alfred
 */
public class StaffController
{

    public static boolean login(JFrame frame, String username, String password)
    {
        if (!Validations.isNull(username) && !Validations.isNull(password))
        {
            int session = StaffDAO.validateCredentials(Integer.parseInt(username), password);
            switch (session)
            {
                case -1 ->
                {
                    JOptionPane.showMessageDialog(frame, "Verifique sus credenciales y vuelva a intentar.", "Credenciales Invalidas", JOptionPane.WARNING_MESSAGE);
                }
                case -2 ->
                {
                    JOptionPane.showMessageDialog(frame, "NO se pudo establecer conexión con la base de datos.", "Fallo en la conexión", JOptionPane.ERROR_MESSAGE);
                }
                case 1 ->
                {
                    new WorkspaceAdministrator(Integer.parseInt(username)).setVisible(true);
                    frame.dispose();
                    return true;
                }
                default ->
                {
                    new WorkspaceEmployee(Integer.parseInt(username)).setVisible(true);
                    frame.dispose();
                    return true;
                }
            }
        }
        return false;
    }
    
    public static List<Staff> getEmployees()
    {
        //Aqui deben ir validaciones
        return StaffDAO.getEmployees();
    }
    public static List<Map<String, Object>> getEmployees(Set<String> campos, Set<String> roles, Set<String> departamentos)
    {
        return StaffDAO.getEmployees(campos, roles, departamentos);
    }

    public static Staff getEmployee(int pkId)
    {
        //Aqui deben ir validaciones
        return StaffDAO.getStaff(pkId);
    }
    
//    public static String getEmployeeName(int id)
//    {
//        return StaffDAO.getStaff(id).getName();
//    }
    public static int getEmployeePass(int id, String pass)
    {
        Staff user = StaffDAO.getStaff(id);
        try
        {
            return user.getPassword().compareTo(pass);
        } catch (NullPointerException e)
        {
        }
        return -1;
    }
    
    public static String getEmployeeRole(int id)
    {
        return StaffDAO.getStaffRol(id);
    }
    
    public static String getEmployeeDepartment(int id)
    {
        return StaffDAO.getStaffDepartment(id);
    }
}
