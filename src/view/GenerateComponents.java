package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import utils.Var;

/**
 *
 * @author Alfred
 */
public class GenerateComponents
{

    public static JPanel crearPanelDegradado(LayoutManager layout, String color1, String color2)
    {
        JPanel p = new JPanel(layout)
        {
            @Override
            protected void paintChildren(Graphics g)
            {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//                GradientPaint gA = new GradientPaint(0, 0, Color.decode("#3a7bd5"), getWidth(), 0, Color.decode("#00d2ff"));
                GradientPaint gA = new GradientPaint(0, 0, Color.decode(color1), getWidth(), getHeight(), Color.decode(color2));
                g2.setPaint(gA);
//                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 5);
                g2.fillRect(0, 0, getWidth(), getHeight());
                super.paintChildren(g);
            }
        };
        return p;
    }

    public static JPanel crearPanelTransparente(LayoutManager layout)
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
        p.setOpaque(false);
        return p;
    }

    public static JButton crearBotonConIcono(String texto, String sources)
    {
        JButton boton = new JButton("    " + texto);
        boton.setBackground(Color.WHITE);
        boton.setForeground(Color.BLACK);
        boton.setHorizontalAlignment(SwingConstants.LEFT); // Alinear texto e icono a la izquierda
        boton.setPreferredSize(new Dimension(170, 50)); // Tamaño del botón

        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setContentAreaFilled(false);
        boton.setIcon(new ImageIcon(Var.PATHASSETS + sources));

//        // Hacer que el botón ocupe todo el ancho del panel lateral
        boton.setMaximumSize(new Dimension(Integer.MAX_VALUE, boton.getMinimumSize().height));

        boton.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseEntered(MouseEvent e)
            {
                boton.setContentAreaFilled(true);
                boton.setBackground(Color.decode("#039BE5"));
                boton.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e)
            {
                boton.setContentAreaFilled(false);
                boton.setBackground(Color.WHITE);
                boton.setForeground(Color.BLACK);
            }
        });
        return boton;
    }

    public static JButton crearBotonConIcono(String txt, String sources, String color1, String color2)
    {
        JButton boton = new JButton()
        {
            @Override
            protected void paintChildren(Graphics g)
            {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(getBackground());
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
                super.paintComponent(g);
            }
        };
        boton.setBorderPainted(false);
        boton.setFocusPainted(false);
        boton.setContentAreaFilled(false);
        boton.setIcon(new ImageIcon(Var.PATHASSETS + sources));
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

    public static JToggleButton crearBotonONOFF(JPanel component, String txtON, String txtOFF, String sourcesON, String sourcesOFF)
    {
        JToggleButton boton = new JToggleButton("    " + txtON);
        boton.setSelected(true);
        boton.setBackground(Color.WHITE);
        boton.setForeground(Color.BLACK);
        boton.setFont(new Font("Arial", Font.PLAIN, 12));
        
//        boton.setHorizontalAlignment(SwingConstants.LEFT); // Alinear texto e icono a la izquierda
//        boton.setPreferredSize(new Dimension(170, 50)); // Tamaño del botón

        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setContentAreaFilled(false);
        boton.setIcon(new ImageIcon(Var.PATHASSETS + sourcesON));

//        // Hacer que el botón ocupe todo el ancho del panel lateral
        boton.setMaximumSize(new Dimension(Integer.MAX_VALUE, boton.getMinimumSize().height));

        boton.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseEntered(MouseEvent e)
            {
                boton.setContentAreaFilled(true);
                boton.setBackground(Color.decode("#039BE5"));
                boton.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e)
            {
                boton.setContentAreaFilled(false);
                boton.setBackground(Color.WHITE);
                boton.setForeground(Color.BLACK);
            }
        });

        boton.addActionListener((ActionEvent e) ->
        {
            if (boton.isSelected())
            {
                boton.setText(txtON);
                boton.setIcon(new ImageIcon(Var.PATHASSETS + sourcesON));
            } else
            {
                boton.setText(txtOFF);
                boton.setIcon(new ImageIcon(Var.PATHASSETS + sourcesOFF));
            }
            component.setVisible(!component.isVisible());
        });
        return boton;
    }
    
    public static JButton crearBotonHerramineta(String texto, String sources)
    {
        JButton boton = new JButton(texto);
        boton.setBackground(Color.WHITE);
        boton.setForeground(Color.BLACK);
        boton.setHorizontalAlignment(SwingConstants.LEFT); // Alinear texto e icono a la izquierda
//        boton.setPreferredSize(new Dimension(170, 50)); // Tamaño del botón

        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setContentAreaFilled(false);
        boton.setIcon(new ImageIcon(Var.PATHASSETS + sources));

//        // Hacer que el botón ocupe todo el ancho del panel lateral
//        boton.setMaximumSize(new Dimension(Integer.MAX_VALUE, boton.getMinimumSize().height));

        boton.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseEntered(MouseEvent e)
            {
                boton.setContentAreaFilled(true);
                boton.setBackground(Color.decode("#039BE5"));
                boton.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e)
            {
                boton.setContentAreaFilled(false);
                boton.setBackground(Color.WHITE);
                boton.setForeground(Color.BLACK);
            }
        });
        return boton;
    }
}
