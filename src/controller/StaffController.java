package controller;

import dao.StaffDAO;
import java.util.List;
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
                case 0 ->
                {
                    //new Workspace(StaffDAO.getStaff(username, password)).setVisible(true);
                    //default, nunca debe entrar aqui, reg > 0
                }
                case -1 ->
                {
                    JOptionPane.showMessageDialog(frame, "Verifique sus credenciales y vuelva a intentar.", "Credenciales Invalidas", JOptionPane.WARNING_MESSAGE);
                }
                case -2 ->
                {
                    JOptionPane.showMessageDialog(frame, "NO se pudo establecer conexión con la base de datos.", "Fallo en la conexión", JOptionPane.ERROR_MESSAGE);
                }
                default ->
                {
                    Staff employeeStaff = StaffDAO.getStaff(session);
                    if (employeeStaff.getRole() == 1)
                    {
                        new WorkspaceAdministrator(employeeStaff).setVisible(true);
                    }else
                    {
                        new WorkspaceEmployee(employeeStaff).setVisible(true);
                    }
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

    public static Staff getEmployee(int pkId)
    {
        //Aqui deben ir validaciones
        return StaffDAO.getStaff(pkId);
    }
    
    public static String getEmployeeName(int id)
    {
        return StaffDAO.getStaff(id).getName();
    }
}
