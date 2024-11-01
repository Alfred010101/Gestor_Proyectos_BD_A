package view;

import controller.ProjectController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

/**
 * Panel principal Seleccion de proyeto y Creacion de nuevo proyecto
 *
 * @author Alfred
 */
public class EmployeeProjects extends CardJPanel
{

    private final int idEmployee;
    private String proyectoSeleccionado = "";
    private final List<String> susProyectos;

    public EmployeeProjects(int idEmployee)
    {
        this.idEmployee = idEmployee;
        susProyectos = ProjectController.getSusProyectos(idEmployee);
        if (!susProyectos.isEmpty())
        {
            proyectoSeleccionado = susProyectos.get(0);
        }
        initComponets();
    }

    private void initComponets()
    {
        initPanelSeleccion();
        initTablasPest();
    }

    private void initPanelSeleccion()
    {
        JPanel panelSeleccionProyecto = new JPanel(new BorderLayout());
        panelSeleccionProyecto.setBackground(Color.LIGHT_GRAY);
        JPanel panelContenedorNorth = new JPanel();
        panelContenedorNorth.setBackground(Color.decode("#CFD8DC"));
        JComboBox<String> comboBox = new JComboBox<>(susProyectos.toArray(String[]::new));
        comboBox.addActionListener((ActionEvent e) ->
        {
            String seleccion = (String) comboBox.getSelectedItem();
            if (proyectoSeleccionado.compareTo(seleccion) != 0)
            {
                proyectoSeleccionado = seleccion;
                System.out.println("Seleccionado: " + proyectoSeleccionado);
            } else
            {
                System.out.println("Sin cambio");
            }
            
        });
        JButton btnNuevoProyecto = GenerateComponents.crearBotonHerramineta("Nuevo Proyecto", "add_proyecto_Res2.png");
        panelContenedorNorth.add(btnNuevoProyecto);
        panelContenedorNorth.add(new JLabel("Seleccione un Proyecto"));
        panelContenedorNorth.add(comboBox);

        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel panelInicio = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnAgregar = GenerateComponents.crearBotonHerramineta("Agregar", "agregar_Res2.png");
        JButton btnVer = GenerateComponents.crearBotonHerramineta("Detalles", "expediente_Res2.png");
        JButton btnModificar = GenerateComponents.crearBotonHerramineta("Actualizar", "editar_Res2.png");
        JButton btnEliminar = GenerateComponents.crearBotonHerramineta("Eliminar", "borrar_Res2.png");

        panelInicio.add(btnAgregar);
        panelInicio.add(btnVer);
        panelInicio.add(btnModificar);
        panelInicio.add(btnEliminar);

        JPanel panelFiltar = new JPanel();

        JPanel panelInfo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnDetallesProyecto = GenerateComponents.crearBotonHerramineta("Detalles", "expediente_Res2.png");
        JButton btnModificarProyecto = GenerateComponents.crearBotonHerramineta("Actualizar", "editar_Res2.png");
        JButton btnEliminarProyecto = GenerateComponents.crearBotonHerramineta("Eliminar", "borrar_Res2.png");

        panelInfo.add(btnDetallesProyecto);
        panelInfo.add(btnModificarProyecto);
        panelInfo.add(btnEliminarProyecto);

        tabbedPane.addTab("Inicio", panelInicio);
        tabbedPane.addTab("Filtrar", panelFiltar);
        tabbedPane.addTab("Sobre Proyecto", panelInfo);

        panelSeleccionProyecto.add(panelContenedorNorth, BorderLayout.NORTH);
        panelSeleccionProyecto.add(tabbedPane, BorderLayout.CENTER);

        add(panelSeleccionProyecto, BorderLayout.NORTH);
    }

    private void initTablasPest()
    {
        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel panelTareas = new JPanel();
        JPanel panelColaboradores = new JPanel();

        tabbedPane.addTab("Tareas", panelTareas);
        tabbedPane.addTab("Colaboradores", panelColaboradores);
        add(tabbedPane, BorderLayout.CENTER);
    }

    @Override
    protected void reset()
    {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
