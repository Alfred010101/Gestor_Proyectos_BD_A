/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vtn;

/**
 *
 * @author Cassandra
 */
public class VtnNuevoPersonal extends javax.swing.JFrame
{

    /**
     * Creates new form VtnNuevoPersonal
     */
    public VtnNuevoPersonal()
    {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jPanel1 = new javax.swing.JPanel();
        apellidoPat = new javax.swing.JTextField();
        apellidoMat = new javax.swing.JTextField();
        correo = new javax.swing.JTextField();
        contraseña = new javax.swing.JTextField();
        nombre = new javax.swing.JTextField();
        direccion = new javax.swing.JTextField();
        telefono = new javax.swing.JTextField();
        comboBoxRol = new javax.swing.JComboBox<>();
        comboBoxDepto = new javax.swing.JComboBox<>();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(apellidoPat, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 440, 30));
        jPanel1.add(apellidoMat, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, 440, 30));
        jPanel1.add(correo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 460, 440, 30));

        contraseña.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                contraseñaActionPerformed(evt);
            }
        });
        jPanel1.add(contraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 530, 440, 30));
        jPanel1.add(nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 440, 30));

        direccion.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                direccionActionPerformed(evt);
            }
        });
        jPanel1.add(direccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 670, 440, 30));
        jPanel1.add(telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 600, 440, 30));

        comboBoxRol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(comboBoxRol, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 800, 440, 30));

        comboBoxDepto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboBoxDepto.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                comboBoxDeptoActionPerformed(evt);
            }
        });
        jPanel1.add(comboBoxDepto, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 740, 440, 30));

        btnGuardar.setBackground(new java.awt.Color(102, 255, 102));
        btnGuardar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 850, 110, 30));

        btnCancelar.setBackground(new java.awt.Color(255, 51, 51));
        btnCancelar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnCancelar.setText("Cancelar");
        jPanel1.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 850, 110, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/NuevoPersonal.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void contraseñaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_contraseñaActionPerformed
    {//GEN-HEADEREND:event_contraseñaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_contraseñaActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnGuardarActionPerformed
    {//GEN-HEADEREND:event_btnGuardarActionPerformed

    }//GEN-LAST:event_btnGuardarActionPerformed

    private void comboBoxDeptoActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_comboBoxDeptoActionPerformed
    {//GEN-HEADEREND:event_comboBoxDeptoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxDeptoActionPerformed

    private void direccionActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_direccionActionPerformed
    {//GEN-HEADEREND:event_direccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_direccionActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(VtnNuevoPersonal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(VtnNuevoPersonal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(VtnNuevoPersonal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(VtnNuevoPersonal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new VtnNuevoPersonal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField apellidoMat;
    private javax.swing.JTextField apellidoPat;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> comboBoxDepto;
    private javax.swing.JComboBox<String> comboBoxRol;
    private javax.swing.JTextField contraseña;
    private javax.swing.JTextField correo;
    private javax.swing.JTextField direccion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField nombre;
    private javax.swing.JTextField telefono;
    // End of variables declaration//GEN-END:variables
}
