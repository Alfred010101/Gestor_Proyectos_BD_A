package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import model.Staff;
import utils.Var;

/**
 * Esta clase proporciona el diseño de una plantilla de la interfaz principal.
 * Cubrindo lo que es la parte superior (Ocultar Menú, Perfil Usuario, Ayuda,
 * Notificaciones, Cerrar Sesión). Tambien se encarga del menú lateral.
 *
 * @author Alfred
 */
public abstract class PlantillaPrincipal extends JFrame
{

    /**
     * Objetos contnidos en la ventana
     */
    private JPanel panelPrincipal;

    /**
     * Objetos contnidos en el panelNorte
     */
    private JPanel panelNorth;

    /**
     * Objetos contenidos en el panelWest
     */
    protected JPanel panelWest;

    /**
     * Objetos contenidos en el panelCenter
     */
    protected JPanel panelCenter;
    protected CardLayout cardWork;

    /**
     * Objetos requeridos para contriur la plantilla
     */
    protected Staff employee;

    public PlantillaPrincipal(Staff employee)
    {
        this.employee = employee;
        try
        {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e)
        {
        }
        setTitle("Workspace");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        initComponets();
        add(panelPrincipal);
    }

    private void initComponets()
    {
        panelPrincipal = new JPanel(new BorderLayout());

        initPanelWest();
        initPanelNorth();
        initPanelCenter();
        panelPrincipal.add(panelNorth, BorderLayout.NORTH);
        panelPrincipal.add(panelWest, BorderLayout.WEST);
        panelPrincipal.add(panelCenter, BorderLayout.CENTER);
    }

    /**
     * @return the panelPrincipal
     */
    public JPanel getPanelPrincipal()
    {
        return panelPrincipal;
    }

    /**
     * @param panelPrincipal the panelPrincipal to set
     */
    public void setPanelPrincipal(JPanel panelPrincipal)
    {
        this.panelPrincipal = panelPrincipal;
    }

    private void initPanelNorth()
    {
        panelNorth = new JPanel(new BorderLayout());
        panelNorth.setBackground(Color.decode("#0277BD"));
        panelNorth.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        panelNorth.setLayout(new BorderLayout());

        /**
         * Panel que contiene el avatar y el nombre del personal
         */
        JPanel panelPerfil = GenerateComponents.crearPanelTransparente(new FlowLayout(FlowLayout.LEFT));
        JToggleButton btnMostrarMenu = GenerateComponents.crearBotonONOFF(panelWest, "Ocultar Menú", "Mostrar Menú", "dep_lis.png", "dep_lis_Res.png");
        JButton btnPerfil = GenerateButton.crearBotonBarra("Mi Perfil", "avatar_final.png", Var.COLORES_BOTONES_NORTH);
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

        JButton btnAyuda = GenerateButton.crearBotonBarra("Ayuda", "ayudar_Res.png", Var.COLORES_BOTONES_NORTH);
        JButton btnNotificaciones = GenerateButton.crearBotonBarra("Notificaciones", "boton-de-notificaciones_Res.png", Var.COLORES_BOTONES_NORTH);
        JButton btnSalir = GenerateButton.crearBotonBarra("Cerrar Sesión", "cerrar-sesion_Res.png", Var.COLORES_BOTONES_NORTH);
        panelIconos.add(btnAyuda);
        panelIconos.add(btnNotificaciones);
        panelIconos.add(btnSalir);

        panelNorth.add(panelPerfil, BorderLayout.WEST);
        panelNorth.add(panelIconos, BorderLayout.EAST);
    }

    private void initPanelWest()
    {
        panelWest = new JPanel();
        panelWest.setLayout(new BoxLayout(panelWest, BoxLayout.Y_AXIS));
        panelWest.setBackground(Color.decode("#FFFFFF"));

    }

    private void initPanelCenter()
    {
        panelCenter = new JPanel();
        panelCenter.setBackground(Color.decode("#D7DBDD"));
        cardWork = new CardLayout();
        panelCenter.setLayout(cardWork);
    }
    
    protected abstract void initMenu();
    
    protected abstract void initWorckspace();
    
//    private void initMenu2()
//    {
////        Var.OPCION_ACTUAL = Var.MENU_ADMIN.DEPARTAMENTOS;
//        JButton btnPersonal;
//        JButton btnDepartamentos;
//        JButton btnRecursos;
//
//        if (employee.getRole() == 1)
//        {
//            btnPersonal = GenerateButton.crearBotonConIcono("Personal", "jefe-de-equipo_Res.png", Var.MENU_ADMIN.PERSONAL);
//
//            btnDepartamentos = GenerateButton.crearBotonConIcono("Departamentos", "departamento-de-la-compania_Res.png", Var.MENU_ADMIN.DEPARTAMENTOS);
//
//            btnRecursos = GenerateButton.crearBotonConIcono("Recursos", "en-stock_Res.png", Var.MENU_ADMIN.RECURSOS);
//
//        } else
//        {
//            btnPersonal = GenerateButton.crearBotonConIcono("Tareas", "tareas_Res.png", Var.MENU_ADMIN.PERSONAL);
//
//            btnDepartamentos = GenerateButton.crearBotonConIcono("Proyectos", "gestion-de-proyectos_Res.png", Var.MENU_ADMIN.DEPARTAMENTOS);
//
//            btnRecursos = GenerateButton.crearBotonConIcono("Colaboraciones", "colaborar_Res.png", Var.MENU_ADMIN.RECURSOS);
//
//        }
//
//        btnPersonal.addActionListener((e) ->
//        {
//            if (Var.OPCION_ACTUAL != Var.MENU_ADMIN.PERSONAL)
//            {
//                setBackgroundMenusAdmin(Var.MENU_ADMIN.PERSONAL, btnPersonal, btnDepartamentos, btnRecursos);
//            }
//        });
//
//        btnDepartamentos.addActionListener((e) ->
//        {
//            if (Var.OPCION_ACTUAL != Var.MENU_ADMIN.DEPARTAMENTOS)
//            {
//                setBackgroundMenusAdmin(Var.MENU_ADMIN.DEPARTAMENTOS, btnPersonal, btnDepartamentos, btnRecursos);
//            }
//        });
//
//        btnRecursos.addActionListener((e) ->
//        {
//            if (Var.OPCION_ACTUAL != Var.MENU_ADMIN.RECURSOS)
//            {
//                setBackgroundMenusAdmin(Var.MENU_ADMIN.RECURSOS, btnPersonal, btnDepartamentos, btnRecursos);
//            }
//        });
//
//        panelWest.add(btnPersonal);
//        panelWest.add(btnDepartamentos);
//        panelWest.add(btnRecursos);
//    }

    public void setBackgroundMenusAdmin(int opc, JButton... botones)
    {
        int menu = 0;
        for(JButton btn : botones)
        {
            if (Var.OPCION_ACT == menu++)
            {
                btn.setBackground(Color.WHITE);
                btn.setForeground(Color.BLACK);
            }
        }
        Var.OPCION_ACT = opc;
        menu = 0;
        for(JButton btn : botones)
        {
            if (Var.OPCION_ACT == menu++)
            {
                btn.setBackground(Color.LIGHT_GRAY);
                btn.setForeground(Color.WHITE);
            }
        }
    }
}
