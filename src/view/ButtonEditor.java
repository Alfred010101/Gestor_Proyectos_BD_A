/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;

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

class ButtonEditor extends DefaultCellEditor {
    private JButton button;
    private String label;
    private boolean isPushed;

    public ButtonEditor(JCheckBox checkBox) {
        super(checkBox);
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped(); // Dejar de editar
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        label = (value == null) ? "Boton" : value.toString();
        button.setText(label);
        isPushed = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (isPushed) {
            // Realiza la acción del botón aquí
            System.out.println("Boton presionado en la fila: " + label);
        }
        isPushed = false;
        return label;
    }
}