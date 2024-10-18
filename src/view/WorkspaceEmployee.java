package view;

import controller.TaskController;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.JViewport;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import model.Staff;
import model.Task;
import utils.Var;

/**
 *
 * @author Alfred
 */
public class WorkspaceEmployee extends JFrame
{

    private JPanel space;
    private JPanel panelNorth;
    private JPanel panelWest;
    private JPanel panelCenter;
    private Staff employee;

    private JToggleButton btnMostrarMenu;

    private JButton btnTareas;
    private JButton btnProyectos;
    private JButton btnColaboraciones;
    private JButton btnNuevoProyecto;

    private CardLayout cardWork;
    private JPanel cardPanelTareas;
    private JPanel cardPanelProyectos;
    private JPanel cardPanelColaboraciones;
    private JPanel cardPanelNvProyecto;

    public WorkspaceEmployee(Staff employee)
    {
        this.employee = employee;
        setTitle("Workspace");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        space = new JPanel(new BorderLayout());
        initComponents();
        add(space);
    }

    private void initComponents()
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e)
        {
        }
        initPanelWest();
        initPanelNorth();
        initPanelCenter();
        space.add(panelNorth, BorderLayout.NORTH);
        space.add(panelWest, BorderLayout.WEST);
        space.add(panelCenter, BorderLayout.CENTER);
    }

    /**
     * Gestiona la interfaz en el panel de la parte superior Revision : OK
     */
    private void initPanelNorth()
    {
//        panelNorth = GenerateComponents.crearPanelDegradado(new BorderLayout(), "#0277BD", "#0277BD");
        panelNorth = new JPanel(new BorderLayout());
        panelNorth.setBackground(Color.decode("#0277BD"));
        panelNorth.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        /**
         * Panel que contiene el avatar y el nombre del personal
         */
        JPanel panelPerfil = GenerateComponents.crearPanelTransparente(new FlowLayout(FlowLayout.LEFT));
        btnMostrarMenu = GenerateComponents.crearBotonONOFF(panelWest, "Ocultar Menú", "Mostrar Menú", "dep_lis.png", "dep_lis_Res.png");
        //btnMostrarMenu = GenerateComponents.crearBotonConIcono("Mostrar menú", "avatar_final.png","#0277BD", "#01579B");
        JButton btnPerfil = GenerateComponents.crearBotonConIcono("Mi Perfil", "avatar_final.png", "#0277BD", "#01579B");
        JLabel lblUsuario = new JLabel(employee.getName());
        lblUsuario.setFont(new Font("Arial", Font.BOLD, 14));
        lblUsuario.setForeground(Color.WHITE);

//        intiActionButtonNorth();
        panelPerfil.add(btnMostrarMenu);
        panelPerfil.add(btnPerfil);
        panelPerfil.add(lblUsuario);

        /**
         * Panel que contiene las opciones del "menu" puerior izquierdo
         */
        JPanel panelIconos = GenerateComponents.crearPanelTransparente(null);
        panelIconos.setLayout(new BoxLayout(panelIconos, BoxLayout.X_AXIS));

        JButton btnAyuda = GenerateComponents.crearBotonConIcono("Ayuda", "avatar_final.png", "#0277BD", "#556080");
        JButton btnNotificaciones = GenerateComponents.crearBotonConIcono("Notificaciones", "avatar_final.png", "#0277BD", "#556080");
        JButton btnSalir = GenerateComponents.crearBotonConIcono("Cerrar Sesión", "avatar_final.png", "#0277BD", "#556080");
        panelIconos.add(btnAyuda);
        panelIconos.add(btnNotificaciones);
        panelIconos.add(btnSalir);

        panelNorth.add(panelPerfil, BorderLayout.WEST);
        panelNorth.add(panelIconos, BorderLayout.EAST);
    }

//     private void intiActionButtonNorth()
//     {
//         btnMostrarMenu.addActionListener((e) ->
//         {
//            panelWest.setVisible(!panelWest.isVisible());
//         });
//     }
    /**
     * Gestiona la interfaz en el panel de la izquieda Revision : EN PROGRESO
     */
    private void initPanelWest()
    {
        panelWest = new JPanel();
        panelWest.setLayout(new BoxLayout(panelWest, BoxLayout.Y_AXIS));
        panelWest.setBackground(Color.decode("#FFFFFF"));

        // Creación de los botones con iconos para el menú
        btnTareas = GenerateComponents.crearBotonConIcono("Tareas", "avatar_final.png");
        btnProyectos = GenerateComponents.crearBotonConIcono("Proyectos", "avatar_final.png");
        btnColaboraciones = GenerateComponents.crearBotonConIcono("Colaboraciones", "avatar_final.png");
        btnNuevoProyecto = GenerateComponents.crearBotonConIcono("Nuevo Proyecto", "avatar_final.png");

        intiActionButtonWest();

        // Añadir botones al menú lateral
        panelWest.add(btnTareas);
        panelWest.add(btnProyectos);
        panelWest.add(btnColaboraciones);
        panelWest.add(btnNuevoProyecto);
    }

    private void intiActionButtonWest()
    {
        btnTareas.addActionListener((e) ->
        {
            cardWork.show(panelCenter, "Card Tareas");
        });

        btnProyectos.addActionListener((e) ->
        {
            cardWork.show(panelCenter, "Card Proyectos");
        });

        btnColaboraciones.addActionListener((e) ->
        {
            cardWork.show(panelCenter, "Card Colaboraciones");
        });

        btnNuevoProyecto.addActionListener((e) ->
        {
            cardWork.show(panelCenter, "Card Nuevo");
        });
    }

    /**
     * Gestiona la interfaz en el panel central Revision : EN ESPERA
     */
    private void initPanelCenter()
    {
        panelCenter = new JPanel();
        panelCenter.setBackground(Color.decode("#D7DBDD"));
        cardWork = new CardLayout();
        panelCenter.setLayout(cardWork);

        initCardPanelTareas();
        initCardPanelProyectos();
        initCardPanelColaboraciones();
        initCardPanelNvProyecto();

        panelCenter.add(cardPanelTareas, "Card Tareas");
        panelCenter.add(cardPanelProyectos, "Card Proyectos");
        panelCenter.add(cardPanelColaboraciones, "Card Colaboraciones");
        panelCenter.add(cardPanelNvProyecto, "Card Nuevo");
    }

    private void initCardPanelTareas()
    {
        cardPanelTareas = new JPanel();
        cardPanelTareas.setLayout(new BoxLayout(cardPanelTareas, BoxLayout.Y_AXIS));

        JPanel panelHerraminetas = new JPanel();
        panelHerraminetas.setBackground(Color.decode("#7f8c8d"));

        JButton btnNvTarea = GenerateComponents.crearBotonHerramineta("Nueva Tarea", "agregar-tarea_Res.png");
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
        panelHerraminetas.add(btnNvTarea);
        panelHerraminetas.add(checkboxPanel);
        panelHerraminetas.add(panelOrdenar);

        JPanel panelBtnHoy = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBtnHoy.setBackground(Color.decode("#ccd1d1"));
        JPanel panelTabHoy = new JPanel();
        panelTabHoy.setLayout(new BoxLayout(panelTabHoy, BoxLayout.Y_AXIS));
        panelTabHoy.add(initTableHoy());
        JToggleButton btnHoy = GenerateComponents.crearBotonONOFF(panelTabHoy, "Ocultar Tareas de Hoy", "Mostrar Tareas de Hoy", "dep_lis.png", "dep_lis_Res.png");

        panelBtnHoy.add(btnHoy);

        JPanel panelBtnSemana = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBtnSemana.setBackground(Color.decode("#ccd1d1"));
        JPanel panelTabSemana = new JPanel();
//        panelTabSemana.setLayout(new BoxLayout(panelTabSemana, BoxLayout.Y_AXIS));
        panelTabSemana.setLayout(new BoxLayout(panelTabSemana, BoxLayout.Y_AXIS));
        panelTabSemana.add(initTableHoy());
        JToggleButton btnSemana = GenerateComponents.crearBotonONOFF(panelTabSemana, "Ocultar Tareas de la Semana", "Mostrar Tareas de la Semana", "dep_lis.png", "dep_lis_Res.png");

        panelBtnSemana.add(btnSemana);

        JPanel panelBtnMes = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBtnMes.setBackground(Color.decode("#ccd1d1"));
        JPanel panelTabMes = new JPanel();
        panelTabMes.setLayout(new BoxLayout(panelTabMes, BoxLayout.Y_AXIS));
        panelTabMes.add(initTableHoy());
        JToggleButton btnMes = GenerateComponents.crearBotonONOFF(panelTabMes, "Ocultar Tareas del Mes", "Mostrar Tareas del Mes", "dep_lis.png", "dep_lis_Res.png");

        panelBtnMes.add(btnMes);

        cardPanelTareas.add(panelHerraminetas);
        cardPanelTareas.add(panelBtnHoy);
        cardPanelTareas.add(panelTabHoy);
        cardPanelTareas.add(panelBtnSemana);
        cardPanelTareas.add(panelTabSemana);
        cardPanelTareas.add(panelBtnMes);
        cardPanelTareas.add(panelTabMes);
    }

    private JScrollPane initTableHoy()
    {
        List<Task> tareas = TaskController.getMisTareas(employee.getId());
        Object[][] data = new Object[tareas.size()][5];
        for (int i = 0; i < tareas.size(); i++)
        {
//            data[i][0] = tareas.get(i).getResponsible();
            data[i][0] = tareas.get(i).getState();
            data[i][1] = tareas.get(i).getStartDate();
            data[i][2] = tareas.get(i).getEndDate();
            data[i][3] = tareas.get(i).getExpectedDate();
            data[i][4] = null;
        }
        String[] columnNames =
        {
            "Estado", "Fecha de Inicio", "Fecha de Termino", "Fecha Marcada", "Acciones"
        };
//        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames)
        {
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return column == 4; // Solo la columna de botones es editable
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
        table.getColumn("Acciones").setCellRenderer(new ButtonRenderer());

        // Editor para botones
        table.getColumn("Acciones").setCellEditor(new ButtonEditor(new JCheckBox()));
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
        table.getColumnModel().getColumn(4).setPreferredWidth(300);
        //table.getColumnModel().getColumn(4).setPreferredWidth(120);

        Dimension tableSize = table.getPreferredSize();
        scrollPane.setPreferredSize(new Dimension(tableSize.width, table.getRowHeight() * table.getRowCount()));
        return scrollPane;
    }

    private void initCardPanelProyectos()
    {
        cardPanelProyectos = new JPanel();
        cardPanelProyectos.add(new JLabel("Tarjeta 2"));
        cardPanelProyectos.setBackground(Color.GREEN);
    }

    private void initCardPanelColaboraciones()
    {
        cardPanelColaboraciones = new JPanel();
        cardPanelColaboraciones.add(new JLabel("Tarjeta 3"));
        cardPanelColaboraciones.setBackground(Color.YELLOW);
    }

    private void initCardPanelNvProyecto()
    {
        cardPanelNvProyecto = new JPanel();
        cardPanelNvProyecto.add(new JLabel("Tarjeta 4"));
        cardPanelNvProyecto.setBackground(Color.RED);
    }
}
