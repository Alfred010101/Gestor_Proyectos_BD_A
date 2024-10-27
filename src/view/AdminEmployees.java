
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Alfred
 */

public class AdminEmployees extends CardJPanel
{
    private final int idEmployee;
    private JTable tabla;
    private JScrollPane contenedorTabla;
    boolean[] seleccionados;
                
    public AdminEmployees(int idEmployee)
    {
        this.idEmployee = idEmployee;
        seleccionados = new boolean[9];
        for (int i = 0; i < 9; i++)
        {
            seleccionados[i] = true;
        }
        initComponets();
    }
    
    private void initComponets()
    {
        initPanelNorth();
        initPanelCenter();
    }
    
    private void initPanelNorth()
    {
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBorder(new EmptyBorder(0, 0, 10, 0));
        
        JPanel panelInicio = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelInicio.setBackground(Color.LIGHT_GRAY);
        JButton btnAgregar = GenerateComponents.crearBotonHerramineta("", "nuevo_Res.png");
        JButton btnVer = GenerateComponents.crearBotonHerramineta("", "expediente_Res.png");
        JButton btnModificar = GenerateComponents.crearBotonHerramineta("", "boton-editar_Res.png");
        JButton btnEliminar = GenerateComponents.crearBotonHerramineta("", "borrar_Res.png");
        panelInicio.add(btnAgregar);
        panelInicio.add(btnVer);
        panelInicio.add(btnModificar);
        panelInicio.add(btnEliminar);
        
        JPanel panelFiltrar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnCampos = GenerateComponents.crearBotonHerramineta("Ver Campos", "filtrar_Res.png");
        JButton btnRoles = GenerateComponents.crearBotonHerramineta("Roles", "filtrar_Res.png");
        JButton btnDepartamentos = GenerateComponents.crearBotonHerramineta("Departamentos", "filtrar_Res.png");
        String[] opcionesOrdenar =
        {
            "Default", "Mayor Prioridad", "Menor Prioridad", "Mayor Tiempo", "Mayor tiempo"
        };
        String[] formasOrdenar =
        {
            "Default", "A-Z", "Z-A", "Acendente", "Decendente"
        };

        // Crear el JComboBox con las opciones
        JComboBox<String> cbxOrdenar = new JComboBox<>(opcionesOrdenar);
        JComboBox<String> cbxFormasOrdenar = new JComboBox<>(formasOrdenar);
        panelFiltrar.add(btnCampos);
        panelFiltrar.add(btnRoles);
        panelFiltrar.add(btnDepartamentos);
        
        /***/
        btnCampos.addActionListener((e) ->
        {
            JPopupMenu popupCampos = SeleccionarCampos.lista(contenedorTabla, seleccionados);
            popupCampos.show(btnCampos, 0, btnCampos.getHeight());
        });
        
        btnRoles.addActionListener((e) ->
        {
            JPopupMenu popupRoles = SeleccionarCampos.lista(contenedorTabla, seleccionados);
            popupRoles.show(btnRoles, 0, btnRoles.getHeight());
        });
        
        btnDepartamentos.addActionListener((e) ->
        {
            JPopupMenu popupDepartamentos = SeleccionarCampos.lista(contenedorTabla, seleccionados);
            popupDepartamentos.show(btnDepartamentos, 0, btnDepartamentos.getHeight());
        });
        /***/
        
        JPanel panelOrdenar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelOrdenar.add(new JLabel("Ordenar por campo"));
        panelOrdenar.add(cbxOrdenar);
        panelOrdenar.add(new JLabel("Forma"));
        panelOrdenar.add(cbxFormasOrdenar);
        
        JPanel panelBuscar = new JPanel(new BorderLayout());
        
        tabbedPane.addTab("Inicio", panelInicio);
//        tabbedPane.addTab("Buscar", panelBuscar);
        tabbedPane.addTab("Filtrar", panelFiltrar);        
        tabbedPane.addTab("Ordenar", panelOrdenar);
//        panelHerramientas.setBackground(Color.decode("#7f8c8d"));
        
        JPanel panelArriba = new JPanel();
        JPanel panelCentro = new JPanel();
        JPanel panelAbajo = new JPanel();
//        JPanel panelBuscar = new JPanel();
//        JButton btnRoles = GenerateComponents.crearBotonHerramineta("Roles", "agregar-tarea_Res.png");
//        panelBuscar.add(btnRoles);
        panelArriba.add(new JLabel("Id"));
        JTextField filtarId = new JTextField(10);
        panelArriba.add(filtarId);
        panelArriba.add(new JButton("Buscar"));
        
        panelCentro.add(new JLabel("Nombre"));
        JTextField filtarNombre = new JTextField(15);
        panelCentro.add(filtarNombre);
        panelCentro.add(new JLabel("Apellido Paterno"));
        JTextField filtarApPaterno = new JTextField(15);
        panelCentro.add(filtarApPaterno);
        panelCentro.add(new JLabel("Apellido Materno"));
        JTextField filtarApMaterno = new JTextField(15);
        panelCentro.add(filtarApMaterno);
        
        panelAbajo.add(new JButton("Buscar"));
        panelBuscar.add(panelArriba, BorderLayout.NORTH);
        panelBuscar.add(panelCentro, BorderLayout.CENTER);
        panelBuscar.add(panelAbajo, BorderLayout.SOUTH);
        
        panelPricipal.add(tabbedPane, BorderLayout.NORTH);
        
    }
    
    private void initPanelCenter()
    {
        tabla = GenerateTable.getTableEmpleados();
        contenedorTabla = new JScrollPane(tabla);
        contenedorTabla.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        panelPricipal.add(contenedorTabla, BorderLayout.CENTER);
    }

    @Override
    protected void reset()
    {
        
    }
}
