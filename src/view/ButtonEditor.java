/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import utils.Var;

/**
 *
 * @author Alfred
 */
//class ButtonEditor extends DefaultCellEditor {
//    protected JButton button;
//    private String label;
//    private boolean isPushed;
//
//    public ButtonEditor(JCheckBox checkBox) {
//        super(checkBox);
//        button = new JButton();
//        button.setOpaque(true);
//        button.addActionListener(e -> fireEditingStopped());
//    }
//
//    @Override
//    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
//        label = (value == null) ? "" : value.toString();
//        button.setText(label);
//        isPushed = true;
//        return button;
//    }
//
//    @Override
//    public Object getCellEditorValue() {
//        if (isPushed) {
//            // Aquí puedes definir lo que pasa cuando se hace clic en el botón
//            JOptionPane.showMessageDialog(button, "Acción en la fila " + (getCellEditorValue() != null ? getCellEditorValue() : "N/A"));
//        }
//        isPushed = false;
//        return label;
//    }
//
//    @Override
//    public boolean stopCellEditing() {
//        isPushed = false;
//        return super.stopCellEditing();
//    }
//
//    @Override
//    protected void fireEditingStopped() {
//        super.fireEditingStopped();
//    }
//}

//class ButtonEditor extends DefaultCellEditor {
//    private JButton button;
//    private String label;
//    private boolean isPushed;
//
//    public ButtonEditor(JCheckBox checkBox) {
//        super(checkBox);
//        button = new JButton();
//        button.setOpaque(true);
//        button.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                fireEditingStopped(); // Dejar de editar
//            }
//        });
//    }
//
//    @Override
//    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
//        label = (value == null) ? "Boton" : value.toString();
//        button.setText(label);
//        isPushed = true;
//        return button;
//    }
//
//    @Override
//    public Object getCellEditorValue() {
//        if (isPushed) {
//            // Realiza la acción del botón aquí
//            System.out.println("Boton presionado en la fila: " + label);
//        }
//        isPushed = false;
//        return label;
//    }
//}

public class ButtonEditor extends DefaultCellEditor {
    private JButton button;
    private String label;
    private boolean isPushed;

    public ButtonEditor(JCheckBox checkBox, String icon) {
        super(checkBox);
//        button = GenerateComponents.crearBotonHerramineta("", Var.PATHASSETS + icon);
//        button.setOpaque(true);
//        button.setText(text);
        button = new JButton();
        button.setIcon(new ImageIcon(Var.PATHASSETS + icon));
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped(); // Dejar de editar cuando se presiona el botón
            }
        });
        
        button.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseEntered(MouseEvent e)
            {
                button.setContentAreaFilled(true);
                button.setBackground(Color.decode("#039BE5"));
                button.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e)
            {
                button.setContentAreaFilled(false);
                button.setBackground(Color.WHITE);
                button.setForeground(Color.BLACK);
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        label = button.getText();
        isPushed = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (isPushed) {
            System.out.println("Botón '" + label + "' presionado en la fila: " + label);
        }
        isPushed = false;
        return label;
    }
}