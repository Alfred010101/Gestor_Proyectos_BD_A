
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import utils.Var;

/**
 *
 * @author Alfred
 */

public class GenerateButton
{
    public static JButton crearBotonBarra(String txt, String sources, String[] colores)
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
        boton.setBackground(Color.decode(colores[1]));
        boton.setToolTipText(txt);
        
        boton.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseEntered(MouseEvent e)
            {
                boton.setBackground(Color.decode(colores[0]));
            }

            @Override
            public void mouseExited(MouseEvent e)
            {
                boton.setBackground(Color.decode(colores[1]));
            }

            @Override
            public void mousePressed(MouseEvent e)
            {
                boton.setBackground(Color.decode(colores[2]));
            }

            @Override
            public void mouseReleased(MouseEvent e)
            {
               boton.setBackground(Color.decode(colores[0]));
            }
        });     
        return boton;
    }
    
    public static JButton crearBotonConIcono(String texto, String sources, int opc)
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

        boton.setMaximumSize(new Dimension(Integer.MAX_VALUE, boton.getMinimumSize().height));

        boton.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseEntered(MouseEvent e)
            {
                if (opc != Var.OPCION_ACT)
                {
                    boton.setContentAreaFilled(true);
                    boton.setBackground(Color.decode("#039BE5"));
                    boton.setForeground(Color.WHITE);
                }
            }

            @Override
            public void mouseExited(MouseEvent e)
            {
                if (opc != Var.OPCION_ACT)
                {
                    boton.setContentAreaFilled(false);
                    boton.setBackground(Color.WHITE);
                    boton.setForeground(Color.BLACK);
                }
            }
        });
        return boton;
    }
}
