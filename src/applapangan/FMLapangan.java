package applapangan;

import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import koneksi.db_connect;

/**
 *
 * @author tobyz
 */
public class FMLapangan extends javax.swing.JInternalFrame {

    Boolean isi = true;
    db_connect conn = new db_connect();

    public FMLapangan() {
        initComponents();
        awal();
    }
    
    void awal() {
        nonaktif();
        bersih();
        tampil();
        btnTambah.setEnabled(true);
        btnSimpan.setEnabled(false);
        btnUbah.setEnabled(false);
        btnHapus.setEnabled(false);
        btnBatal.setEnabled(false);
    }

    void aktif() {
        txtKode.setEnabled(true);
        txtNama.setEnabled(true);
        txtHargaWd.setEnabled(true);
        txtHargaWe.setEnabled(true);
    }

    void nonaktif() {
        txtKode.setEnabled(false);
        txtNama.setEnabled(false);
        txtHargaWd.setEnabled(false);
        txtHargaWe.setEnabled(false);
    }

    void bersih() {
        txtKode.setText("");
        txtNama.setText("");
        txtHargaWd.setText("");
        txtHargaWe.setText("");
        txtCari.setText("");
    }

    void tampil() {
        String[] columnNames = {"Kode", "Nama", "Harga Weekday", "Harga Weekend"};
        JTable table = new JTable(getData(), columnNames);
        table.setEnabled(false);
        jScrollPane2.setViewportView(table);
    }

    void otomatis() {
        try {
            Statement st = (Statement) conn.getConnection().createStatement();
            String sql = "select right(kd_lapangan,3)+1 from lapangan ORDER BY `right(kd_lapangan,3)+1` DESC";
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()){
                rs.last();
                String kode = rs.getString(1);
                while (kode.length() < 3) {
                    kode = "0" + kode;
                    txtKode.setText("LP" + kode);
                }
            } else {
                txtKode.setText("LP001");
            }
        } catch (Exception e) {
            System.out.println("Koneksi Gagal" + e.toString());
        }
    }
    
    void simpan() {
        try {
            Statement st = conn.getConnection().createStatement();
            String sql = "insert into lapangan values('" + txtKode.getText() + "','" + txtNama.getText() + "','" + txtHargaWd.getText() + "','" + txtHargaWe.getText() + "','"+ this.lblID.getText() + "')";
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(this, "Data Berhasil Disimpan", "Informasi", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            System.out.println("Koneksi Gagal" + e.toString());
        }
        awal();
    }

    void update() {
        try {
            Statement st = conn.getConnection().createStatement();
            String sql = "update lapangan set nama_lapangan='" + txtNama.getText() + "', harga_weekday='" + txtHargaWd.getText() + "', harga_weekend='" + txtHargaWe.getText() + "' where kd_lapangan='" + txtKode.getText() + "'";
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(this, "Data Berhasil Diupdate", "Informasi", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            System.out.println("Koneksi Gagal" + e.toString());
        }
        awal();
    }

    void delete() {
        try {
            Statement st = conn.getConnection().createStatement();
            String sql = "delete from lapangan where kd_lapangan='" + txtKode.getText() + "'";
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(this, "Data Berhasil Dihapus", "Informasi", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            System.out.println("Koneksi Gagal" + e.toString());
        }
        awal();
    }

    void cari() {
        try {
            Statement st = conn.getConnection().createStatement();
            ResultSet rs = st.executeQuery("select *from lapangan where kd_lapangan like'%" + txtCari.getText() + "%' or nama_lapangan like'%" + txtCari.getText() + "%'");
            if (rs.next()) {
                txtKode.setText(rs.getString("kd_lapangan"));
                txtNama.setText(rs.getString("nama_lapangan"));
                txtHargaWd.setText(rs.getString("harga_weekday"));
                txtHargaWe.setText(rs.getString("harga_weekend"));
            } else {
                JOptionPane.showMessageDialog(this, "Data Tidak Ditemukan", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                txtCari.requestFocus();
            }
        } catch (SQLException e) {
            System.out.println("Koneksi Gagal" + e.toString());
        }
        btnUbah.setEnabled(true);
        btnHapus.setEnabled(true);
        btnTambah.setEnabled(false);
    }

    private Object[][] getData() {
        Object[][] data1 = null;
        try {
            Statement st = conn.getConnection().createStatement();
            ResultSet rs = st.executeQuery("select *from lapangan");
            rs.last();
            int rowCount = rs.getRow();
            rs.beforeFirst();
            data1 = new Object[rowCount][4];
            int no = -1;
            while (rs.next()) {
                no = no + 1;
                data1[no][0] = rs.getString("kd_lapangan");
                data1[no][1] = rs.getString("nama_lapangan");
                data1[no][2] = rs.getString("harga_weekday");
                data1[no][3] = rs.getString("harga_weekend");
            }
        } catch (SQLException e) {
            System.out.println("Koneksi Gagal" + e.toString());
        }
        return data1;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        lblID = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtCari = new javax.swing.JTextField();
        btnCari = new javax.swing.JButton();
        txtKode = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtHargaWe = new javax.swing.JTextField();
        txtNama = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        btnBatal = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnSimpan = new javax.swing.JButton();
        btnUbah = new javax.swing.JButton();
        btnTambah = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbTim = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        txtHargaWd = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setTitle("Form Master Lapangan");

        jLabel5.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel5.setText("Harga Weekend");

        lblID.setForeground(new java.awt.Color(240, 240, 240));
        lblID.setText("ID");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pencarian", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 0, 12))); // NOI18N

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(txtCari)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCari, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCari, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8))
        );

        txtKode.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtKode.setEnabled(false);
        txtKode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtKodeKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel4.setText("Harga Weekday");

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel2.setText("Kode Tim");

        txtHargaWe.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtHargaWe.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtHargaWeKeyTyped(evt);
            }
        });

        txtNama.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtNama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNamaKeyTyped(evt);
            }
        });

        btnBatal.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnBatal.setText("Batal");
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });

        btnHapus.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        btnSimpan.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        btnUbah.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnUbah.setText("Ubah");
        btnUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUbahActionPerformed(evt);
            }
        });

        btnTambah.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnTambah.setText("Tambah");
        btnTambah.setPreferredSize(new java.awt.Dimension(71, 20));
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnUbah, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(btnBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBatal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnUbah, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSimpan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTambah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnHapus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        tbTim.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tbTim);

        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel3.setText("Nama Tim");

        txtHargaWd.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtHargaWd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtHargaWdKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addGap(56, 56, 56)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtKode, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(21, 21, 21)
                                .addComponent(lblID))
                            .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtHargaWe, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtHargaWd, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtKode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblID))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtHargaWd, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtHargaWe, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void txtKodeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKodeKeyTyped
        if (txtKode.getText().length() > 12) {
            JOptionPane.showMessageDialog(null, "Maaf karakter terlalu banyak !!");
            evt.consume();
        }
    }//GEN-LAST:event_txtKodeKeyTyped

    private void txtHargaWeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHargaWeKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
            evt.consume();
        }
        if (txtHargaWe.getText().length() > 20) {
            JOptionPane.showMessageDialog(null, "Maaf karakter terlalu banyak !!");
            evt.consume();
        }
    }//GEN-LAST:event_txtHargaWeKeyTyped

    private void txtNamaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNamaKeyTyped
        if (txtNama.getText().length() > 30) {
            JOptionPane.showMessageDialog(null, "Maaf karakter terlalu banyak !!");
            evt.consume();
        }
    }//GEN-LAST:event_txtNamaKeyTyped

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        awal();
    }//GEN-LAST:event_btnBatalActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        JOptionPane.showMessageDialog(this, "Yakin Mau Dihapus?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if ((JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            delete();
            awal();
        }
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        if (isi == true) {
            simpan();
        } else {
            update();
            isi = true;
        }
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahActionPerformed
        isi = false;
        aktif();
        txtKode.setEnabled(false);
        btnUbah.setEnabled(false);
        btnTambah.setEnabled(false);
        btnSimpan.setEnabled(true);
        btnBatal.setEnabled(true);
    }//GEN-LAST:event_btnUbahActionPerformed

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        otomatis();
        aktif();
        txtKode.setEnabled(false);
        txtNama.requestFocus();
        btnSimpan.setEnabled(true);
        btnBatal.setEnabled(true);
        btnTambah.setEnabled(false);
    }//GEN-LAST:event_btnTambahActionPerformed

    private void txtHargaWdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHargaWdKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
            evt.consume();
        }
        if (txtHargaWd.getText().length() > 20) {
            JOptionPane.showMessageDialog(null, "Maaf karakter terlalu banyak !!");
            evt.consume();
        }
    }//GEN-LAST:event_txtHargaWdKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnTambah;
    private javax.swing.JButton btnUbah;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JLabel lblID;
    private javax.swing.JTable tbTim;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtHargaWd;
    private javax.swing.JTextField txtHargaWe;
    private javax.swing.JTextField txtKode;
    private javax.swing.JTextField txtNama;
    // End of variables declaration//GEN-END:variables
}
