package view;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import utils.Var;

/**
 *
 * @author Alfred
 */
//class ButtonRenderer extends JPanel implements TableCellRenderer {
//    public ButtonRenderer() {
//        setLayout(new FlowLayout(FlowLayout.LEFT)); // Alinear botones a la izquierda
//        setOpaque(true); // Hacer que el panel sea opaco
//    }
//
//    @Override
//    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
//        // Limpiar el panel
//        removeAll();
//
//        // Crear los botones
//        JButton button1 = new JButton("Ver");
//        JButton button2 = new JButton("Ediar");
//        JButton button3 = new JButton("Eliminar");
//
//        // Agregar los botones al panel
//        add(button1);
//        add(button2);
//        add(button3);
//
//        // Cambiar el color de fondo según la selección
//        if (isSelected) {
//            setBackground(table.getSelectionBackground());
//            setForeground(table.getSelectionForeground());
//        } else {
//            setBackground(Color.WHITE);
//            setForeground(Color.BLACK);
//        }
//
//        // Redibujar el panel
//        revalidate();
//        repaint();
//
//        return this;
//    }
//}
public class ButtonRenderer extends JButton implements TableCellRenderer
{

    public ButtonRenderer(String icon)
    {
//        setText(text);
//        setOpaque(true);
//        JButton botonPersonalizado = GenerateComponents.crearBotonHerramineta("", Var.PATHASSETS + "");
        setIcon(new ImageIcon(Var.PATHASSETS + icon));
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setBackground(Color.WHITE);
        setForeground(Color.BLACK);
//        setBackground(botonPersonalizado.getBackground());
//        setForeground(botonPersonalizado.getForeground());
//        setHorizontalAlignment(botonPersonalizado.getHorizontalAlignment());
//        setFocusPainted(botonPersonalizado.isFocusPainted());
//        setBorderPainted(botonPersonalizado.isBorderPainted());
//        setContentAreaFilled(botonPersonalizado.isContentAreaFilled());
        this.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseEntered(MouseEvent e)
            {
                setContentAreaFilled(true);
                setBackground(Color.decode("#039BE5"));
                setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e)
            {
                setContentAreaFilled(false);
                setBackground(Color.WHITE);
                setForeground(Color.BLACK);
            }
        });
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
        if (isSelected)
        {
            setBackground(table.getSelectionBackground());
            setForeground(table.getSelectionForeground());
        } else
        {
            setBackground(UIManager.getColor("Button.background"));
            setForeground(table.getForeground());
        }
        return this;
    }
}
