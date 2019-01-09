package applapangan;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import koneksi.db_connect;

/**
 *
 * @author tobyz
 */
public class FTSewaDetail extends javax.swing.JInternalFrame {

    db_connect conn = new db_connect();

    public FTSewaDetail() {
        initComponents();
        tampil();
        getData();
        txtCari.requestFocus();
    }

    void tampil() {
        String[] columnNames = {"Kode Booking", "Nama Penyewa", "Kode lapangan", "Tanggal Sewa", "Jam Sewa", "Lama Sewa", "Status"};
        JTable table = new JTable(getData(), columnNames);
        table.setEnabled(false);
        jScrollPane1.setViewportView(table);
    }

    void cari() {
        try {
            Statement st = conn.getConnection().createStatement();
            String a = "Belum Lunas";
            ResultSet rs = st.executeQuery("select *from sewa where kd_booking like'%" + txtCari.getText() + "%' or nama_penyewa like'%" + txtCari.getText() + "%' or tgl_sewa like'%"
                    + txtCari.getText() + "%' or jam_sewa like'%" + txtCari.getText() + "%' or status like'%" + txtCari.getText() + "%'");
            if (rs.next()) {
                txtKID.setText(rs.getString("kd_booking"));
                txtNama.setText(rs.getString("nama_penyewa"));
            } else {
                JOptionPane.showMessageDialog(this, "Data Tidak Ditemukan", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                txtCari.requestFocus();
            }
        } catch (SQLException e) {
            System.out.println("Koneksi Gagal" + e.toString());
        }
    }

    private Object[][] getData() {
        Object[][] data1 = null;
        try {
            Statement st = conn.getConnection().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM sewa");
            rs.last();
            int rowCount = rs.getRow();
            rs.beforeFirst();
            data1 = new Object[rowCount][7];
            int no = -1;
            while (rs.next()) {
                no = no + 1;
                data1[no][0] = rs.getString("kd_booking");
                data1[no][1] = rs.getString("nama_penyewa");
                data1[no][2] = rs.getString("kd_lapangan");
                data1[no][3] = rs.getString("tgl_sewa");
                data1[no][4] = rs.getString("jam_sewa");
                data1[no][5] = rs.getString("lama_sewa");
                data1[no][6] = rs.getString("status");
            }
        } catch (SQLException e) {
            System.out.println("Koneksi Gagal" + e.toString());
        }
        return data1;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        txtCari = new javax.swing.JTextField();
        btnCari = new javax.swing.JButton();
        txtNama = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        lblID = new javax.swing.JLabel();
        txtKID = new javax.swing.JTextField();

        setClosable(true);
        setTitle("Detail Tabel");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pencarian", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 0, 12))); // NOI18N

        txtCari.setToolTipText("");
        txtCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCariKeyTyped(evt);
            }
        });

        btnCari.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnCari.setText("Cari");
        btnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(txtCari)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCari, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCari, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14))
        );

        txtNama.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtNama.setEnabled(false);
        txtNama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNamaKeyTyped(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel3.setText("Nama");

        lblID.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lblID.setText("Kode/ID");

        txtKID.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtKID.setEnabled(false);
        txtKID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtKIDKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(lblID)
                .addGap(18, 18, 18)
                .addComponent(txtKID, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblID)
                    .addComponent(txtKID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCariKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariKeyTyped
        if (txtCari.getText().length() > 20) {
            JOptionPane.showMessageDialog(null, "Maaf karakter terlalu banyak !!");
            evt.consume();
        }
    }//GEN-LAST:event_txtCariKeyTyped

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
        cari();
    }//GEN-LAST:event_btnCariActionPerformed

    private void txtNamaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNamaKeyTyped
        if (txtNama.getText().length() > 30) {
            JOptionPane.showMessageDialog(null, "Maaf karakter terlalu banyak !!");
            evt.consume();
        }
    }//GEN-LAST:event_txtNamaKeyTyped

    private void txtKIDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKIDKeyTyped
        if (txtKID.getText().length() > 12) {
            JOptionPane.showMessageDialog(null, "Maaf karakter terlalu banyak !!");
            evt.consume();
        }
    }//GEN-LAST:event_txtKIDKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCari;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblID;
    private javax.swing.JTextField txtCari;
    public javax.swing.JTextField txtKID;
    public javax.swing.JTextField txtNama;
    // End of variables declaration//GEN-END:variables
}
