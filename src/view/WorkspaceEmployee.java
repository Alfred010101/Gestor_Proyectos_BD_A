package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import model.Staff;

/**
 *
 * @author Alfred
 */
public class WorkspaceEmployee extends JFrame
{

    private JPanel space;
    private JPanel panelNorth;
    private JPanel panelWest;
    private Staff employee;

    public WorkspaceEmployee(Staff employee)
    {
        this.employee = employee;
        setTitle("Menú Superior con Iconos");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        space = new JPanel(new BorderLayout());

        //setLayout(new BorderLayout());
        initComponents();
        add(space);
    }

    private void initComponents()
    {
        initPanelNorth();
        initPanelWest();
        space.add(panelNorth, BorderLayout.NORTH);
        space.add(panelWest, BorderLayout.WEST);
    }

    private void initPanelNorth()
    {
        panelNorth = new JPanel(new BorderLayout())
        {
            @Override
            protected void paintChildren(Graphics g)
            {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//                GradientPaint gA = new GradientPaint(0, 0, Color.decode("#3a7bd5"), getWidth(), 0, Color.decode("#00d2ff"));
                GradientPaint gA = new GradientPaint(0, 0, Color.decode("#42A5F5"), getWidth(), getHeight(), Color.decode("#90CAF9"));
                g2.setPaint(gA);
                //g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 5);
                g2.fillRect(0, 0, getWidth(), getHeight());
                super.paintChildren(g);
            }
        };
        panelNorth.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        JPanel panelPerfil = crearPanelTransparente(new FlowLayout(FlowLayout.LEFT));
//        panelPerfil.setBackground(Color.LIGHT_GRAY);
        panelPerfil.setOpaque(false);
        JButton iconoPerfil = crearBotonConIcono("Mi Perfil", "src/assets/avatar_final.png", "#42A5F5", "#556080"); // Icono del perfil
        JLabel nombreUsuario = new JLabel(employee.getName());
        nombreUsuario.setFont(new Font("Arial", Font.BOLD, 14));
        nombreUsuario.setForeground(Color.WHITE);
        panelPerfil.add(iconoPerfil);
        panelPerfil.add(nombreUsuario);

//        JPanel panelIconos = crearPanelTransparente(new FlowLayout(FlowLayout.RIGHT));
        JPanel panelIconos = crearPanelTransparente(null);
        panelIconos.setLayout(new BoxLayout(panelIconos, BoxLayout.X_AXIS));
        panelIconos.setOpaque(false);
//        panelIconos.setBackground(Color.LIGHT_GRAY);

        // Icono de notificaciones
        JButton botonNotificaciones = crearBotonConIcono("Ayuda", "src/assets/avatar_final.png", "#90CAF9", "#556080");
        botonNotificaciones.setBorderPainted(false);
        botonNotificaciones.setFocusPainted(false);
        botonNotificaciones.setContentAreaFilled(false);

        // Icono de ajustes
        JButton botonAjustes = crearBotonConIcono("Notificaciones", "src/assets/avatar_final.png", "#90CAF9", "#556080");
        botonAjustes.setBorderPainted(false);
        botonAjustes.setFocusPainted(false);
        botonAjustes.setContentAreaFilled(false);

        // Icono de ayuda
        JButton botonAyuda = crearBotonConIcono("Cerrar Sesión", "src/assets/avatar_final.png", "#90CAF9", "#556080");
        botonAyuda.setBorderPainted(false);
        botonAyuda.setFocusPainted(false);
        botonAyuda.setContentAreaFilled(false);

        // Añadir los iconos al panel derecho
        panelIconos.add(botonNotificaciones);
        panelIconos.add(botonAjustes);
        panelIconos.add(botonAyuda);
        panelNorth.add(panelPerfil, BorderLayout.WEST);
        panelNorth.add(panelIconos, BorderLayout.EAST);
//        panelNorth.setBackground(Color.red);
    }

    private JButton crearBotonConIcono(String txt, String rutaIcono, String color1, String color2)
    {
        JButton boton = new JButton()
        {
            @Override
            protected void paintChildren(Graphics g)
            {
                // Dibuja un fondo redondeado
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(getBackground());
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
//                int diameter = Math.min(getWidth(), getHeight());
//                int x = (getWidth() - diameter) / 2; // Centrar el círculo en el botón
//                int y = (getHeight() - diameter) / 2;
//
//                // Dibuja el círculo
//                g2d.setColor(getBackground());
//                g2d.fillOval(x, y, diameter, diameter);

                super.paintComponent(g);
            }
        };
//        boton.setForeground(Color.WHITE);
        boton.setBorderPainted(false);
        boton.setFocusPainted(false);
        boton.setContentAreaFilled(false);
        boton.setIcon(new ImageIcon(rutaIcono));
        boton.setBackground(Color.decode(color1));
        boton.setToolTipText(txt);
//        boton.setOpaque(false);

        boton.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseEntered(MouseEvent e)
            {
//                boton.setContentAreaFilled(true);
                boton.setBackground(Color.decode(color2));
            }

            @Override
            public void mouseExited(MouseEvent e)
            {
//                boton.setContentAreaFilled(false);
                boton.setBackground(Color.decode(color1));
            }
        });
        return boton;
    }

    private JPanel crearPanelTransparente(LayoutManager layout)
    {
        JPanel p = new JPanel(layout)
        {
            @Override
            protected void paintChildren(Graphics g)
            {
                g.setColor(new Color(255, 255, 255, 0));
                g.fillRect(0, 0, getWidth(), getHeight());
                super.paintChildren(g);
            }
        };
        return p;
    }

    private void initPanelWest()
    {
        panelWest = new JPanel();
        panelWest.setLayout(new BoxLayout(panelWest, BoxLayout.Y_AXIS));
        panelWest.setBackground(Color.DARK_GRAY);

        // Creación de los botones con iconos para el menú
        JButton btnInicio = crearBotonConIcono("Inicio", "src/assets/avatar_final.png", "Inicio");
        JButton btnAcercaDe = crearBotonConIcono("Acerca de", "src/assets/avatar_final.png", "Acerca de");
        JButton btnServicios = crearBotonConIcono("Servicios", "src/assets/avatar_final.png", "Servicios");
        JButton btnContacto = crearBotonConIcono("Contacto", "src/assets/avatar_final.png", "Contacto");

        // Añadir botones al menú lateral
        panelWest.add(btnInicio);
        panelWest.add(btnAcercaDe);
        panelWest.add(btnServicios);
        panelWest.add(btnContacto);

        // Añadir el panel del menú a la ventana
        
    }
    private JButton crearBotonConIcono(String texto, String rutaIcono, String card)
    {
        JButton boton = new JButton(texto);
        boton.setFocusPainted(false);
        boton.setForeground(Color.WHITE);
        boton.setBackground(Color.GRAY);
        // Quitar el borde
        boton.setBorderPainted(false);

// Quitar el relieve cuando el botón está enfocado
        boton.setFocusPainted(false);

// Hacer que el botón no sea opaco (transparente al fondo)
        boton.setContentAreaFilled(false);
        boton.setIcon(new ImageIcon(rutaIcono));
        boton.setHorizontalAlignment(SwingConstants.LEFT); // Alinear texto e icono a la izquierda
        boton.setPreferredSize(new Dimension(200, 50)); // Tamaño del botón

        // Hacer que el botón ocupe todo el ancho del panel lateral
        boton.setMaximumSize(new Dimension(Integer.MAX_VALUE, boton.getMinimumSize().height));

        // Añadir efecto de hover
        boton.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseEntered(MouseEvent e)
            {
                boton.setContentAreaFilled(true);
                boton.setBackground(Color.LIGHT_GRAY);
            }

            @Override
            public void mouseExited(MouseEvent e)
            {
                boton.setContentAreaFilled(false);
                boton.setBackground(Color.GRAY);
            }
        });

        // Añadir acción para cambiar el panel visible
//        boton.addActionListener(e -> cardLayout.show(((JPanel) boton.getParent().getParent()), card));

        return boton;
    }
}
