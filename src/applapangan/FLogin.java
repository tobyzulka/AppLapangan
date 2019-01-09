package applapangan;

import java.sql.*;
import javax.swing.JOptionPane;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import koneksi.db_connect;

/**
 *
 * @author tobyz
 */
public class FLogin extends javax.swing.JFrame {

    private Dimension screensize;
    db_connect conn = new db_connect();
    public String id;
    
    public FLogin() {
        initComponents();
        this.setResizable(false);
        screensize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screensize.width / 2) - (getSize().width / 2), (screensize.height / 2) - (getSize().height / 2));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        btnMasuk = new javax.swing.JButton();
        chkTampilPass = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        cbHak = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Aplikasi Penyewaan [Beta] - Login");
        setBackground(new java.awt.Color(204, 204, 204));
        setMaximumSize(new java.awt.Dimension(405, 296));

        jLabel1.setBackground(new java.awt.Color(102, 102, 102));
        jLabel1.setFont(new java.awt.Font("Century Schoolbook", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("App Sewa Lapangan ");
        jLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel2.setText("Hak Akses");

        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel3.setText("Username");

        txtUsername.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtUsername.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUsernameKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUsernameKeyTyped(evt);
            }
        });

        txtPassword.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPasswordKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPasswordKeyTyped(evt);
            }
        });

        btnMasuk.setBackground(new java.awt.Color(255, 255, 255));
        btnMasuk.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnMasuk.setText("Masuk");
        btnMasuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMasukActionPerformed(evt);
            }
        });
        btnMasuk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnMasukKeyPressed(evt);
            }
        });

        chkTampilPass.setText("Tampil Password");
        chkTampilPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTampilPassActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel4.setText("Password");

        cbHak.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        cbHak.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pemilik", "Operator" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkTampilPass)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtPassword)
                            .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(cbHak, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbHak))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtUsername, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkTampilPass)
                .addGap(18, 18, 18)
                .addComponent(btnMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtUsernameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsernameKeyTyped
        if (txtUsername.getText().length() > 16) {
            JOptionPane.showMessageDialog(null, "Maaf karakter terlalu banyak !!");
            evt.consume();
        }
    }//GEN-LAST:event_txtUsernameKeyTyped

    private void txtPasswordKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswordKeyTyped
        if (txtPassword.getText().length() > 16) {
            JOptionPane.showMessageDialog(null, "Maaf karakter terlalu banyak !!");
            evt.consume();
        }
    }//GEN-LAST:event_txtPasswordKeyTyped

    private void chkTampilPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTampilPassActionPerformed
        if (chkTampilPass.isSelected()) {
            txtPassword.setEchoChar((char) 0);
        } else {
            txtPassword.setEchoChar('*');
        }
    }//GEN-LAST:event_chkTampilPassActionPerformed

    private void btnMasukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMasukActionPerformed
        MenuUtama menu = new MenuUtama();
        FMPelanggan pl = new FMPelanggan();
        try {
            Statement st = (Statement) conn.getConnection().createStatement();
            ResultSet rs = st.executeQuery("select * from user where" + " username ='" + txtUsername.getText().trim() + "' and password ='" + txtPassword.getText().trim() + "'");
            if (rs.next()) {
                String hak = rs.getString("hak_akses");
                if (cbHak.getSelectedItem().equals(hak) && cbHak.getSelectedItem().equals("Operator")) {
                    menu.lblID.setText(rs.getString("id_user"));
                    String nama = rs.getString("nama_user");
                    id = rs.getString("id_user");
                    JOptionPane.showMessageDialog(this, "Selamat datang, " + nama + "", "Welcome ", JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                    menu.MiUser.setVisible(false);
                    menu.lblID.setText(id);
                    menu.lblUser.setText(nama);
                    menu.setLocationRelativeTo(null);
                    menu.setVisible(true);
                } else if (cbHak.getSelectedItem().equals(hak) && cbHak.getSelectedItem().equals("Pemilik")) {
                    menu.lblID.setText(rs.getString("id_user"));
                    String nama = rs.getString("nama_user");
                    id = rs.getString("id_user");
                    JOptionPane.showMessageDialog(this, "Selamat datang, " + nama + "", "Welcome ", JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                    menu.lblID.setText(id);
                    menu.lblUser.setText(nama);
                    menu.setLocationRelativeTo(null);
                    menu.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "User tidak ditemukan", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "User tidak ditemukan", "Informasi", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (SQLException e) {
            System.out.println("Koneksi Gagal" + e.toString());
        }
        /*try {
            Statement statement = (Statement) conn.getConnection().createStatement();
            ResultSet result;
            MenuUtama menu = new MenuUtama();
            if (cbHak.getSelectedItem().equals("Pemilik")) {
                result = statement.executeQuery("select * from pemilik where " + "username='" + txtUsername.getText() + "', password='" +  "'");
                menu.lblID.setText(result.getString("id_pemilik"));
            } else {
                result = statement.executeQuery("select * from operator where " + "username='" + txtUsername.getText() + "', password='" + "'");
                menu.lblID.setText(result.getString("id_operator"));
                menu.MiOperator.setVisible(false);
                menu.MiPemilik.setVisible(false);
            }

            if (result.next()) {
                if (txtPassword.getText().equals(result.getString("password"))) {
                    menu.show();
                    menu.lblUser.setText(txtUsername.getText());
                    this.dispose();

                } else {
                    JOptionPane.showMessageDialog(rootPane, "Password salah");
                    txtPassword.setText("");
                    txtUsername.requestFocus();
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "User tidak ditemukan");
                txtUsername.setText("");
                txtPassword.setText("");
                txtUsername.requestFocus();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "gagal");
        }*/
    }//GEN-LAST:event_btnMasukActionPerformed

    private void txtUsernameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsernameKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnMasuk.doClick();
        }
    }//GEN-LAST:event_txtUsernameKeyPressed

    private void txtPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswordKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnMasuk.doClick();
        }
    }//GEN-LAST:event_txtPasswordKeyPressed

    private void btnMasukKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnMasukKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnMasuk.doClick();
        }
    }//GEN-LAST:event_btnMasukKeyPressed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMasuk;
    private javax.swing.JComboBox<String> cbHak;
    private javax.swing.JCheckBox chkTampilPass;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
