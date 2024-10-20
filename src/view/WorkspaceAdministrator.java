package view;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import model.Staff;

/**
 *
 * @author Alfred
 */
public class WorkspaceAdministrator extends JFrame
{

    private Staff employee;
    private PlantillaPrincipal plantilla;

    public WorkspaceAdministrator(Staff employee)
    {
        this.employee = employee;
        setTitle("Workspace");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setBackground(Color.yellow);
        initComponents();
    }

    private void initComponents()
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e)
        {
        }
        plantilla = new PlantillaPrincipal(employee);
        add(plantilla.getPanelPrincipal(), BorderLayout.CENTER);
    }

}
