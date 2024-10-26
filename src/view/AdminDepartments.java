
package view;

import controller.DepartmentController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import utils.Var;

/**
 *
 * @author Alfred
 */

public class AdminDepartments extends CardJPanel
{
    private final int idEmployee;
    private JScrollPane tabla;
            
    public AdminDepartments(int idEmployee)
    {
        this.idEmployee = idEmployee;
        initComponets();
    }
    
    private void initComponets()
    {
        initPanelNorth();
        initPanelCenter();
    }
    
    private void initPanelNorth()
    {
//        JPanel panelNorth = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel panelHerramientas = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        panelHerramientas.setBackground(Color.decode("#7f8c8d"));

        JButton btnNvEmpleado = GenerateComponents.crearBotonHerramineta("Agregar Personal", "agregar-tarea_Res.png");
        JPanel checkboxPanel = new JPanel();
//        checkboxPanel.setBackground(Color.decode("#7f8c8d"));
        checkboxPanel.setLayout(new BoxLayout(checkboxPanel, BoxLayout.X_AXIS)); // Organizar verticalmente
        Border border = BorderFactory.createLineBorder(Color.decode("#e5e7e9"), 1); // Color y grosor del borde
        checkboxPanel.setBorder(border);
        // Crear los checkboxes
        JCheckBox checkbox1 = new JCheckBox("Por Hacer");
        JCheckBox checkbox2 = new JCheckBox("Haciendo");
        JCheckBox checkbox3 = new JCheckBox("Revicion");
        JCheckBox checkbox4 = new JCheckBox("Hecho");
        JCheckBox checkbox5 = new JCheckBox("Detenido");
        checkbox1.setBackground(Color.decode("#7f8c8d"));
        checkbox2.setBackground(Color.decode("#7f8c8d"));
        checkbox3.setBackground(Color.decode("#7f8c8d"));
        checkbox4.setBackground(Color.decode("#7f8c8d"));
        checkbox5.setBackground(Color.decode("#7f8c8d"));
        // Agregar los checkboxes al panel
//        JLabel lblTxt = new JLabel(" Filtrar " );
//        lblTxt.setBackground(Color.decode("#7f8c8d"));
        checkboxPanel.add(new JLabel(new ImageIcon(Var.PATHASSETS + "filtrar_Res.png")));
        checkboxPanel.add(checkbox1);
        checkboxPanel.add(checkbox2);
        checkboxPanel.add(checkbox3);
        checkboxPanel.add(checkbox4);
        checkboxPanel.add(checkbox5);

        JPanel panelOrdenar = new JPanel();
        panelOrdenar.setLayout(new BoxLayout(panelOrdenar, BoxLayout.X_AXIS)); // Organizar verticalmente
        Border border2 = BorderFactory.createLineBorder(Color.decode("#e5e7e9"), 1); // Color y grosor del borde
        panelOrdenar.setBorder(border2);
        String[] options =
        {
            "Default", "Mayor Prioridad", "Menor Prioridad", "Mayor Tiempo", "Mayor tiempo"
        };

        // Crear el JComboBox con las opciones
        JComboBox<String> comboBox = new JComboBox<>(options);
        panelOrdenar.add(new JLabel(new ImageIcon(Var.PATHASSETS + "ordenar_Res.png")));
        panelOrdenar.add(comboBox);
        panelHerramientas.add(btnNvEmpleado);
        panelHerramientas.add(checkboxPanel);
        panelHerramientas.add(panelOrdenar);
        
//        panelNorth.add(panelHerramientas);
//        panelNorth.add(nuevoEmpleado, BorderLayout.CENTER);
        panelPricipal.add(panelHerramientas, BorderLayout.NORTH);
        
    }
    
    private void initPanelCenter()
    {
        tabla = GenerateTable.getTable(DepartmentController.getDepartmentData(), Var.DEPARTMENT_COLUMN_NAMES);
        panelPricipal.add(tabla, BorderLayout.CENTER);
    }

    @Override
    protected void reset()
    {
        
    }
}
