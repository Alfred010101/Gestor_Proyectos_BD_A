package view;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import model.Staff;

/**
 *
 * @author Alfred
 */
public class WorkspaceAdministrator extends JFrame
{
    private Staff employee;

    public WorkspaceAdministrator(Staff employee)
    {
        this.employee = employee;
        setTitle("Workspace");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
//        space = new JPanel(new BorderLayout());
        initComponents();
//        add(space);
    }

    private void initComponents()
    {
       
    }
    
}
