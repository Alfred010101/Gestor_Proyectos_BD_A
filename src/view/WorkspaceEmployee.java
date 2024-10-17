package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import model.Staff;
import test.Lib;

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
        initPanelNorth();
        initPanelWest();
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

        JButton btnPerfil = GenerateComponents.crearBotonConIcono("Mi Perfil", "avatar_final.png", "#0277BD", "#01579B");
        JLabel lblUsuario = new JLabel(employee.getName());
        lblUsuario.setFont(new Font("Arial", Font.BOLD, 14));
        lblUsuario.setForeground(Color.WHITE);
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

    /**
     * Gestiona la interfaz en el panel de la izquieda Revision : EN PROGRESO
     */
    private void initPanelWest()
    {
        panelWest = new JPanel();
        panelWest.setLayout(new BoxLayout(panelWest, BoxLayout.Y_AXIS));
        panelWest.setBackground(Color.WHITE);

        // Creación de los botones con iconos para el menú
        btnTareas = GenerateComponents.crearBotonConIcono("Tareas", "avatar_final.png");
        btnProyectos = GenerateComponents.crearBotonConIcono("Proyectos", "avatar_final.png");
        btnColaboraciones = GenerateComponents.crearBotonConIcono("Colaboraciones", "avatar_final.png");
        btnNuevoProyecto = GenerateComponents.crearBotonConIcono("Nuevo Proyecto", "avatar_final.png");

        intiActionButton();

        // Añadir botones al menú lateral
        panelWest.add(btnTareas);
        panelWest.add(btnProyectos);
        panelWest.add(btnColaboraciones);
        panelWest.add(btnNuevoProyecto);
    }

    private void intiActionButton()
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
        cardPanelTareas.add(new JLabel("Tarjeta 1"));
        cardPanelTareas.setBackground(Color.CYAN);
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
