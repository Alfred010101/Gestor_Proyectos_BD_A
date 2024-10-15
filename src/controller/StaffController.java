package controller;

import dao.StaffDAO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.Staff;
import utils.Validations;
import view.Workspace;

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
            int session = StaffDAO.validateCredentials(username, password);
            System.out.println(session);
            switch (session)
            {
                case 0 ->
                {
                    new Workspace(StaffDAO.getStaff(username, password)).setVisible(true);
                    frame.dispose();
                    return true;
                }
                case 1 ->
                {
                    JOptionPane.showMessageDialog(frame, "Verifique sus credenciales y vuelva a intentar.", "Credenciales Invalidas", JOptionPane.WARNING_MESSAGE);
                }
                case 2 ->
                {
                    JOptionPane.showMessageDialog(frame, "NO se pudo establecer conexión con la base de datos.", "Fallo en la conexión", JOptionPane.ERROR_MESSAGE);
                }
                default ->
                    JOptionPane.showMessageDialog(frame, "Algo salio mal.", "Error..", JOptionPane.WARNING_MESSAGE);
                
            }
        }
        return false;
    }
}
