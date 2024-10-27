
package view;
import controller.TaskController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import model.Task;
import utils.Var;
import java.util.List;

/**
 * Panel principal 
 * Seleccion de proyeto y Creacion de nuevo proyecto
 * @author Alfred
 */
public class EmployeeProjects
{
    public JPanel panel;
    private JPanel panelSeleccion;
    private final PanelAreaTrabajo areaTrabajo;
    private int id_empleado;
    public EmployeeProjects(int id_empleado)
    {
        this.id_empleado = id_empleado;
        panel = new JPanel();
        areaTrabajo = new PanelAreaTrabajo();
        initComponets();
    }
    
    private void initComponets()
    {
        panel.setLayout(new BorderLayout());
        initPanelSeleccion();
        panel.add(panelSeleccion, BorderLayout.NORTH);
        panel.add(areaTrabajo.panel, BorderLayout.CENTER);
    }
    
    private void initPanelSeleccion()
    {
        panelSeleccion = new JPanel(new BorderLayout());
        JPanel panelContSeleccion = new JPanel(new FlowLayout(FlowLayout.CENTER));
        String[] data = {"Elemento 1", "Elemento 2", "Elemento 3"};
        JComboBox<String> comboBox = new JComboBox<>(data);
        panelContSeleccion.add(new JLabel("Seleccione un Proyecto"));
        panelContSeleccion.add(comboBox);
        JButton btnNuevoProyecto = new JButton("Nuevo Proyecto");
        panelSeleccion.add(panelContSeleccion, BorderLayout.CENTER);
        panelSeleccion.add(btnNuevoProyecto, BorderLayout.EAST);
    }
}

/**
 * Herramientas sobre proyecto
 * _TabbPanel
 * @author Alfred
 */
class PanelAreaTrabajo
{
    public JPanel panel;
    private final TabbPaneProyecto areaTrabajo;
    private JPanel panelHerramientas;
    public PanelAreaTrabajo()
    {
        panel = new JPanel();
        areaTrabajo = new TabbPaneProyecto();
        initComponets();
    }
    
    private void initComponets()
    {
        panel.setLayout(new BorderLayout());
        initPanelHerramientas();
        panel.add(panelHerramientas, BorderLayout.NORTH);
        panel.add(areaTrabajo.tabbedPane, BorderLayout.CENTER);
    }
    private void initPanelHerramientas()
    {
        panelHerramientas = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelHerramientas.add(new JLabel(new ImageIcon(Var.PATHASSETS + "filtrar_Res.png")));
    }
}

/**
 * Gestion de pestañas
 * @author Alfred
 */
class TabbPaneProyecto
{

    public JTabbedPane tabbedPane;
    private final Tareas panelTareas;
    private final Colaboradores panelColaboradores;
    private final Propiedades panelPropiedades;
    
    public TabbPaneProyecto()
    {
        tabbedPane = new JTabbedPane();
        panelTareas = new Tareas();
        panelColaboradores = new Colaboradores();
        panelPropiedades = new Propiedades();
        initComponets();
       
    }
    
    private void initComponets()
    {
        tabbedPane.addTab("Tareas", panelTareas.panel);
        tabbedPane.addTab("Colaboradores", panelColaboradores.panel);
        tabbedPane.addTab("Propiedades", panelPropiedades.panel);
    }
}

/**
 * Pestaña tareas
 * @author Alfred
 */
class Tareas
{
    public JPanel panel;
    
    public Tareas()
    {
        panel = new JPanel();
        initComponets();
    }
    private void initComponets()
    {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(initTableHoy());
    }
    
    private JScrollPane initTableHoy()
    {
        List<Task> tareas = TaskController.getMisTareas(2);
        Object[][] data = new Object[tareas.size()][7];
        for (int i = 0; i < tareas.size(); i++)
        {
//            data[i][0] = tareas.get(i).getResponsible();
            data[i][0] = tareas.get(i).getState();
            data[i][1] = tareas.get(i).getStartDate();
            data[i][2] = tareas.get(i).getEndDate();
            data[i][3] = tareas.get(i).getExpectedDate();
            data[i][4] = null;
            data[i][5] = null;
            data[i][6] = null;
        }
        String[] columnNames =
        {
            "Estado", "Fecha de Inicio", "Fecha de Termino", "Fecha Marcada", "Ver", "Editar", "Eliminar"
        };
//        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames)
        {
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return column >= 4; // Solo la columna de botones es editable
            }
        };

        // Crear la JTable con el modelo de datos personalizado
        JTable table = new JTable(tableModel);

//        // Estilizar la cabecera de la tabla
        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(51, 153, 255));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));
//        header.setPreferredSize(new Dimension(100, 40)); // Altura de la cabecera

        // Estilizar las filas
        table.setRowHeight(30); // Altura de las filas
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        // Estilizar la selección de filas
        table.setSelectionForeground(Color.WHITE);
//        table.setFillsViewportHeight(true);       

        // Crear un renderizador personalizado para la tabla
        TableCellRenderer cellRenderer = new TableCellRenderer();
        for (int i = 0; i < table.getColumnCount(); i++)
        {
            table.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }

        // Agregar un detector de mouse para el efecto hover
        table.addMouseMotionListener(new MouseAdapter()
        {
            @Override
            public void mouseMoved(MouseEvent e)
            {
                int row = table.rowAtPoint(e.getPoint());
                cellRenderer.setHoverRow(row);  // Establecer la fila que tiene hover
                table.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e)
            {
                cellRenderer.setHoverRow(-1); // Elimina hover
                table.repaint();
            }
        });

        // Renderizador para botones
//        table.getColumn("Ver").setCellRenderer(new ButtonRenderer("expediente_Res.png"));
//        table.getColumn("Ver").setCellEditor(new ButtonEditor(new JCheckBox(), "expediente_Res.png"));
//        table.getColumn("Editar").setCellRenderer(new ButtonRenderer("boton-editar_Res.png"));
//        table.getColumn("Editar").setCellEditor(new ButtonEditor(new JCheckBox(), "boton-editar_Res.png"));
//        table.getColumn("Eliminar").setCellRenderer(new ButtonRenderer("borrar_Res.png"));
//        table.getColumn("Eliminar").setCellEditor(new ButtonEditor(new JCheckBox(), "borrar_Res.png"));
//        
        // Agregar la tabla a un JScrollPane para hacerla desplazable
        JScrollPane scrollPane = new JScrollPane(table);
//        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setBackground(Color.WHITE);
//        JViewport viewport = scrollPane.getViewport();
//        viewport.setBackground(Color.RED);

//        // Establecer el comportamiento de desplazamiento horizontal
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//        
//        // Permitir que la tabla no ajuste automáticamente el ancho
//        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        // Ajustar el tamaño de las columnas manualmente
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(250);
        table.getColumnModel().getColumn(2).setPreferredWidth(250);
        table.getColumnModel().getColumn(3).setPreferredWidth(250);
        table.getColumnModel().getColumn(4).setPreferredWidth(100);
        table.getColumnModel().getColumn(5).setPreferredWidth(100);
        table.getColumnModel().getColumn(6).setPreferredWidth(100);
        //table.getColumnModel().getColumn(4).setPreferredWidth(120);

        Dimension tableSize = table.getPreferredSize();
        scrollPane.setPreferredSize(new Dimension(tableSize.width, table.getRowHeight() * table.getRowCount()));
        return scrollPane;
    }
    
}

/**
 * Pestaña Colaboradores
 * @author Alfred
 */
class Colaboradores
{
    public JPanel panel;

    public Colaboradores()
    {
        panel = new JPanel();
        initComponets();
    }
    private void initComponets()
    {
        panel.add(new JLabel("Contenido de la Pestaña 2"));
    }
    
}

/**
 * Pestaña Propiedades
 * @author Alfred
 */
class Propiedades
{
    public JPanel panel;

    public Propiedades()
    {
        panel = new JPanel();
        initComponets();
    }
    private void initComponets()
    {
        panel.add(new JLabel("Contenido de la Pestaña 2"));
    }
    
}
