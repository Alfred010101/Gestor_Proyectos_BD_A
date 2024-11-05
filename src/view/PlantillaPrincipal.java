package view;

import controller.StaffController;
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
import javax.swing.JOptionPane;
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

    public PlantillaPrincipal(int  id)
    {
        this.employee = StaffController.getEmployee(id);
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
        JButton btnPerfil = GenerateComponents.crearBotonHerramineta("", "cuenta_Res2.png");
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

        JButton btnAyuda = GenerateComponents.crearBotonHerramineta("Ayuda", "soporte_Res2.png");
        JButton btnNotificaciones = GenerateComponents.crearBotonHerramineta("Notificaciones", "notificaciones_Res2.png");
        JButton btnSalir = GenerateComponents.crearBotonHerramineta("Cerrar Sesión", "logout_Res2.png");
        btnSalir.addActionListener((e) ->
        {
            btnSalirActionListener();
        });
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

    public void setBackgroundMenusAdmin(int opc, JButton... botones)
    {
        int menu = 0;
        for (JButton btn : botones)
        {
            if (Var.OPCION_ACT == menu++)
            {
                btn.setBackground(Color.WHITE);
                btn.setForeground(Color.BLACK);
            }
        }
        Var.OPCION_ACT = opc;
        menu = 0;
        for (JButton btn : botones)
        {
            if (Var.OPCION_ACT == menu++)
            {
                btn.setBackground(Color.LIGHT_GRAY);
                btn.setForeground(Color.WHITE);
            }
        }
    }

    private void btnSalirActionListener()
    {
        int confirmar = JOptionPane.showConfirmDialog(this, "Esta seguro de terminar la sesion", "Cerrar Sesión", JOptionPane.YES_NO_CANCEL_OPTION);
        if (confirmar == JOptionPane.YES_OPTION)
        {
            employee = null;
            dispose();
            new Login().setVisible(true);
        }
    }
}
