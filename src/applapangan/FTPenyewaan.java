package applapangan;

import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import koneksi.db_connect;

/**
 *
 * @author tobyz
 */
public class FTPenyewaan extends javax.swing.JInternalFrame {

    Boolean isi = true;
    Boolean kurang = true;
    db_connect conn = new db_connect();
    MenuUtama menu = new MenuUtama();
    public String harga;
    double hrg, tot, byr, sisa, jml;

    public FTPenyewaan() {
        initComponents();
        awal();
        pilih();
    }

    void awal() {
        nonaktif();
        bersih();
        tampil();
        jLabel12.setVisible(false);
        lblSisa.setVisible(false);
        btnTambah.setEnabled(true);
        btnSimpan.setEnabled(false);
        btnUbah.setEnabled(false);
        btnTarif.setEnabled(false);
        btnTotal.setEnabled(false);
        cbKID.setEnabled(false);
        btnBatal.setEnabled(false);
    }

    void aktif() {
        rbGuest.setEnabled(true);
        rbPelanggan.setEnabled(true);
        rbTim.setEnabled(true);
        cbKode.setEnabled(true);
        DCTglSewa.setEnabled(true);
        txtBayar.setEnabled(true);
        cbJamSewa.setEnabled(true);
        spLama.setEnabled(true);
    }

    void nonaktif() {
        txtKode.setEnabled(false);
        txtNama.setEnabled(false);
        rbGuest.setEnabled(false);
        rbPelanggan.setEnabled(false);
        rbTim.setEnabled(false);
        cbKode.setEnabled(false);
        DCTglSewa.setEnabled(false);
        txtBayar.setEnabled(false);
        cbJamSewa.setEnabled(false);
        spLama.setEnabled(false);
    }

    void bersih() {
        txtKode.setText("");
        txtNama.setText("");
        txtStatus.setText("");
        cbJamSewa.setSelectedItem("08:00:00");
        cbKode.removeAllItems();
        DCTglSewa.setDate(null);
        spLama.setValue(0);
        txtHarga.setText("");
        txtTotal.setText("");
        txtBayar.setText("");
        lblSisa.setText("");
        txtCari.setText("");
    }

    void tampil() {
        String[] columnNames = {"Kode Booking", "Nama", "Tanggal Sewa", "Jam Sewa", "Status"};
        JTable table = new JTable(getData(), columnNames);
        table.setEnabled(false);
        jScrollPane2.setViewportView(table);
    }

    void pilih() {
        ButtonGroup bgPilih = new ButtonGroup();
        bgPilih.add(rbGuest);
        bgPilih.add(rbPelanggan);
        bgPilih.add(rbTim);
    }

    void otomatis() {
        try {
            String tgl;
            Date ys = new Date();
            SimpleDateFormat s = new SimpleDateFormat("yyMMdd");
            tgl = s.format(ys);

            Statement st = (Statement) conn.getConnection().createStatement();
            String sql = "select right(kd_booking,3)+1 from sewa ORDER BY `right(kd_booking,3)+1` DESC";
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                String kode = rs.getString(1);
                while (kode.length() < 3) {
                    kode = "0" + kode;
                    txtKode.setText("B" + tgl + kode);
                }
            } else {
                txtKode.setText("B" + tgl + "001");
            }
        } catch (Exception e) {
            System.out.println("Koneksi Gagal" + e.toString());
        }
    }

    void simpan() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Statement st = conn.getConnection().createStatement();

            if (rbPelanggan.isSelected()) {
                String sql = "insert into sewa values('" + txtKode.getText() + "','" + txtStatus.getText() + "','" + cbKode.getSelectedItem() + "','"
                        + txtNama.getText() + "','" + dateFormat.format(DCTglSewa.getDate()) + "','" + cbJamSewa.getSelectedItem() + "','" + spLama.getValue() + "','"
                        + txtHarga.getText() + "','" + txtTotal.getText() + "','" + txtBayar.getText() + "','" + lblSisa.getText() + "','" + lblKID.getText() + "','" + null + "','" + lblID.getText() + "')";
                st.executeUpdate(sql);
                JOptionPane.showMessageDialog(this, "Data Berhasil Disimpan", "Informasi", JOptionPane.INFORMATION_MESSAGE);
            } else if (rbTim.isSelected()) {
                String sql = "insert into sewa values('" + txtKode.getText() + "','" + txtStatus.getText() + "','" + cbKode.getSelectedItem() + "','"
                        + txtNama.getText() + "','" + dateFormat.format(DCTglSewa.getDate()) + "','" + cbJamSewa.getSelectedItem() + "','" + spLama.getValue() + "','"
                        + txtHarga.getText() + "','" + txtTotal.getText() + "','" + txtBayar.getText() + "','" + lblSisa.getText() + "','" + null + "','" + lblKID.getText() + "','" + lblID.getText() + "')";
                st.executeUpdate(sql);
                JOptionPane.showMessageDialog(this, "Data Berhasil Disimpan", "Informasi", JOptionPane.INFORMATION_MESSAGE);
            } else {
                String sql = "insert into sewa values('" + txtKode.getText() + "','" + txtStatus.getText() + "','" + cbKode.getSelectedItem() + "','"
                        + txtNama.getText() + "','" + dateFormat.format(DCTglSewa.getDate()) + "','" + cbJamSewa.getSelectedItem() + "','" + spLama.getValue() + "','"
                        + txtHarga.getText() + "','" + txtTotal.getText() + "','" + txtBayar.getText() + "','" + lblSisa.getText() + "','" + null + "','" + null + "','" + lblID.getText() + "')";
                st.executeUpdate(sql);
                JOptionPane.showMessageDialog(this, "Data Berhasil Disimpan", "Informasi", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            System.out.println("Koneksi Gagal" + e.toString());
        }
        awal();
    }

    void update() {
        try {
            Statement st = conn.getConnection().createStatement();
            byr = Double.parseDouble(txtBayar.getText());
            sisa = jml + byr;
            String sql = "update sewa set bayar='" + sisa + "', sisa='" + lblSisa.getText() + "', status='" + txtStatus.getText() + "' where kd_booking='" + txtKode.getText() + "'";
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(this, "Data Berhasil Diupdate", "Informasi", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            System.out.println("Koneksi Gagal" + e.toString());
        }
        awal();
    }

    void combo() {
        try {
            Statement st = conn.getConnection().createStatement();
            String sql = "Select *from lapangan";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String data = rs.getString("kd_lapangan");
                cbKode.addItem(data);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    void Kurang() {
        if (!"0.0".equals(lblSisa.getText())) {
            jml = Double.parseDouble(jLabel12.getText());
            txtTotal.setText(lblSisa.getText());
            jLabel9.setText("Sisa");
            jLabel12.setText("Kurang");
        } else {
        }
    }

    void cari() {
        try {
            Statement st = conn.getConnection().createStatement();
            String a = "Belum Lunas";
            ResultSet rs = st.executeQuery("select *from sewa where kd_booking like'%" + txtCari.getText() + "%' or nama_penyewa like'%" + txtCari.getText() + "%' or tgl_sewa like'%"
                    + txtCari.getText() + "%' or jam_sewa like'%" + txtCari.getText() + "%' or status like'%" + txtCari.getText() + "%' AND status LIKE'" + a + "'");
            if ("Belum Lunas".equals(a)) {
                if (rs.next()) {
                    txtKode.setText(rs.getString("kd_booking"));
                    txtNama.setText(rs.getString("nama_penyewa"));
                    cbKode.addItem(rs.getString("kd_lapangan"));
                    DCTglSewa.setDate(rs.getDate("tgl_sewa"));
                    txtStatus.setText(rs.getString("status"));
                    cbJamSewa.addItem(rs.getString("jam_sewa"));
                    spLama.setValue(rs.getDouble("lama_sewa"));
                    txtHarga.setText(rs.getString("harga"));
                    txtTotal.setText(rs.getString("total_harga"));
                    lblSisa.setText(rs.getString("sisa"));
                    jLabel12.setText(rs.getString("bayar"));
                } else {
                    JOptionPane.showMessageDialog(this, "Data Tidak Ditemukan", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                    txtCari.requestFocus();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Data Tidak Ditemukan", "Informasi", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            System.out.println("Koneksi Gagal" + e.toString());
        }
        btnUbah.setEnabled(true);
        btnTambah.setEnabled(false);
    }

    private Object[][] getData() {
        Object[][] data1 = null;
        try {
            Statement st = conn.getConnection().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM sewa WHERE status='Belum Lunas'");
            rs.last();
            int rowCount = rs.getRow();
            rs.beforeFirst();
            data1 = new Object[rowCount][5];
            int no = -1;
            while (rs.next()) {
                no = no + 1;
                data1[no][0] = rs.getString("kd_booking");
                data1[no][1] = rs.getString("nama_penyewa");
                data1[no][2] = rs.getString("tgl_sewa");
                data1[no][3] = rs.getString("jam_sewa");
                data1[no][4] = rs.getString("status");
            }
        } catch (SQLException e) {
            System.out.println("Koneksi Gagal" + e.toString());
        }
        return data1;
    }

    public void tambah() {
        otomatis();
        combo();
        aktif();
        rbGuest.requestFocus();
        btnSimpan.setEnabled(true);
        btnBatal.setEnabled(true);
        btnTambah.setEnabled(false);
    }

    void Harga() {
        try {
            SimpleDateFormat h = new SimpleDateFormat("EEEE");
            String tglH = h.format(DCTglSewa.getDate());
            if (tglH.equals("Monday") || tglH.equals("Tuesday") || tglH.equals("Wednesday") || tglH.equals("Thrusday") || tglH.equals("Friday") || tglH.equals("Senin") || tglH.equals("Selasa") || tglH.equals("Rabu") || tglH.equals("Kamis") || tglH.equals("Jumat")) {
                Statement st = conn.getConnection().createStatement();
                String sql = "Select *from lapangan where kd_lapangan='" + cbKode.getSelectedItem() + "'";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    harga = rs.getString("harga_weekday");
                    txtHarga.setText(harga);
                }
            } else {
                Statement st = conn.getConnection().createStatement();
                String sql = "Select *from lapangan where kd_lapangan='" + cbKode.getSelectedItem() + "'";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    harga = rs.getString("harga_weekend");
                    txtHarga.setText(harga);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    void Total() {
        hrg = Double.parseDouble(txtHarga.getText());
        jml = (Double) spLama.getValue();
        tot = hrg * jml;
        String total = String.valueOf(tot);
        txtTotal.setText(total);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupMenu1 = new java.awt.PopupMenu();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        jInternalFrame2 = new javax.swing.JInternalFrame();
        jInternalFrame3 = new javax.swing.JInternalFrame();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtKode = new javax.swing.JTextField();
        lblNama = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        DCTglSewa = new com.toedter.calendar.JDateChooser();
        cbJamSewa = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtHarga = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbPenyewaan = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        cbKode = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        btnSimpan = new javax.swing.JButton();
        btnTambah = new javax.swing.JButton();
        btnUbah = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        btnDetail = new javax.swing.JButton();
        spLama = new javax.swing.JSpinner();
        txtStatus = new javax.swing.JTextField();
        txtBayar = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lblSisa = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        rbGuest = new javax.swing.JRadioButton();
        rbPelanggan = new javax.swing.JRadioButton();
        rbTim = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        txtCari = new javax.swing.JTextField();
        btnCari = new javax.swing.JButton();
        lblKID = new javax.swing.JLabel();
        btnTarif = new javax.swing.JButton();
        btnTotal = new javax.swing.JButton();
        lblID = new javax.swing.JLabel();
        cbKID = new javax.swing.JComboBox<>();

        popupMenu1.setLabel("popupMenu1");

        jInternalFrame1.setVisible(true);

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jInternalFrame2.setVisible(true);

        javax.swing.GroupLayout jInternalFrame2Layout = new javax.swing.GroupLayout(jInternalFrame2.getContentPane());
        jInternalFrame2.getContentPane().setLayout(jInternalFrame2Layout);
        jInternalFrame2Layout.setHorizontalGroup(
            jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jInternalFrame2Layout.setVerticalGroup(
            jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jInternalFrame3.setVisible(true);

        javax.swing.GroupLayout jInternalFrame3Layout = new javax.swing.GroupLayout(jInternalFrame3.getContentPane());
        jInternalFrame3.getContentPane().setLayout(jInternalFrame3Layout);
        jInternalFrame3Layout.setHorizontalGroup(
            jInternalFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jInternalFrame3Layout.setVerticalGroup(
            jInternalFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        setClosable(true);
        setIconifiable(true);
        setTitle("Form Transaski Penyewaan");
        setToolTipText("");

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel1.setText("Kode Booking");

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel2.setText("Status");

        txtKode.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtKode.setEnabled(false);

        lblNama.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lblNama.setText("Nama ");

        txtNama.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel4.setText("Tanggal Sewa");

        DCTglSewa.setDateFormatString("yyyy-MM-dd");

        cbJamSewa.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        cbJamSewa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "08:00:00", "09:00:00", "10:00:00", "11:00:00", "12:00:00", "13:00:00", "14:00:00", "15:00:00", "16:00:00", "17:00:00", "18:00:00", "19:00:00", "20:00:00", "21:00:00", "22:00:00", "23:00:00" }));

        jLabel5.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel5.setText("Jam Sewa");

        jLabel6.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel6.setText("Lama Sewa");

        jLabel7.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel7.setText("Jam");

        jLabel8.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel8.setText("Harga");

        txtHarga.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtHarga.setEnabled(false);

        jLabel9.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel9.setText("Total");

        txtTotal.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtTotal.setEnabled(false);

        tbPenyewaan.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tbPenyewaan);

        jLabel10.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel10.setText("Kode Lapangan");

        cbKode.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        cbKode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbKodeActionPerformed(evt);
            }
        });

        btnSimpan.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        btnTambah.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnTambah.setText("Tambah");
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });

        btnUbah.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnUbah.setText("Ubah");
        btnUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUbahActionPerformed(evt);
            }
        });

        btnBatal.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnBatal.setText("Batal");
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });

        btnDetail.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnDetail.setText("Detail");
        btnDetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetailActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(btnTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(btnUbah, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(btnBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDetail, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSimpan, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(btnUbah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTambah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBatal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDetail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        spLama.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        spLama.setModel(new javax.swing.SpinnerNumberModel(0.0d, null, 10.0d, 1.0d));

        txtStatus.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtStatus.setEnabled(false);

        txtBayar.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtBayar.setToolTipText("Teken Enter");
        txtBayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBayarKeyPressed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel11.setText("Bayar");

        jLabel12.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel12.setText("Kurang");

        lblSisa.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lblSisa.setText("Sisa");

        jLabel13.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel13.setText("Pilih Akses Sewa");

        rbGuest.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rbGuest.setText("Guest");
        rbGuest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbGuestActionPerformed(evt);
            }
        });

        rbPelanggan.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rbPelanggan.setText("Pelanggan");
        rbPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbPelangganActionPerformed(evt);
            }
        });

        rbTim.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        rbTim.setText("Tim");
        rbTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbTimActionPerformed(evt);
            }
        });

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

        lblKID.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        lblKID.setForeground(new java.awt.Color(240, 240, 240));
        lblKID.setText("jLabel3");

        btnTarif.setText("Tarif");
        btnTarif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTarifActionPerformed(evt);
            }
        });

        btnTotal.setText("Total");
        btnTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTotalActionPerformed(evt);
            }
        });

        lblID.setForeground(new java.awt.Color(240, 240, 240));
        lblID.setText("ID");

        cbKID.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        cbKID.setToolTipText("Kode/ID");
        cbKID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbKIDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 711, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblID))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(DCTglSewa, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnTarif))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rbGuest)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rbPelanggan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rbTim))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel1)
                            .addComponent(lblNama))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbKode, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtKode, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblKID))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbKID, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(72, 72, 72)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(spLama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnTotal))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(cbJamSewa, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel11)
                            .addComponent(jLabel8)
                            .addComponent(jLabel12))
                        .addGap(51, 51, 51)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSisa))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtKode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblKID))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(rbGuest)
                            .addComponent(rbPelanggan)
                            .addComponent(rbTim))
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNama)
                            .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbKID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(cbKode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(jLabel4)
                                .addGap(14, 14, 14))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(DCTglSewa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(11, 11, 11)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblID)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(cbJamSewa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(spLama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(btnTotal))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtBayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTarif))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(lblSisa))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        if (isi == true) {
            simpan();
        } else {

            update();
            isi = true;
        }
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        otomatis();
        combo();
        aktif();
        rbGuest.requestFocus();
        btnSimpan.setEnabled(true);
        btnBatal.setEnabled(true);
        btnTambah.setEnabled(false);
        btnTarif.setEnabled(true);
        btnTotal.setEnabled(true);
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahActionPerformed
        isi = false;
        kurang = false;
        Kurang();
        nonaktif();
        txtBayar.setEnabled(true);
        btnUbah.setEnabled(false);
        btnTambah.setEnabled(false);
        btnSimpan.setEnabled(true);
        btnBatal.setEnabled(true);
    }//GEN-LAST:event_btnUbahActionPerformed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        awal();
    }//GEN-LAST:event_btnBatalActionPerformed

    private void txtCariKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariKeyTyped
        if (txtCari.getText().length() > 20) {
            JOptionPane.showMessageDialog(null, "Maaf karakter terlalu banyak !!");
            evt.consume();
        }
    }//GEN-LAST:event_txtCariKeyTyped

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
        cari();
    }//GEN-LAST:event_btnCariActionPerformed

    private void rbGuestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbGuestActionPerformed
        if (rbGuest.isSelected()) {
            cbKID.removeAllItems();
            txtNama.setEnabled(true);
            cbKID.setEnabled(false);
            txtNama.setText("");
        }
    }//GEN-LAST:event_rbGuestActionPerformed

    private void rbPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbPelangganActionPerformed
        if (rbPelanggan.isSelected()) {
            cbKID.removeAllItems();
            txtNama.setEnabled(false);
            cbKID.setEnabled(true);
            try {
                Statement st = conn.getConnection().createStatement();
                String sql = "Select *from pelanggan";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    String data = rs.getString("id_pelanggan");
                    cbKID.addItem(data);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }//GEN-LAST:event_rbPelangganActionPerformed

    private void rbTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbTimActionPerformed
        if (rbTim.isSelected()) {
            cbKID.removeAllItems();
            txtNama.setEnabled(false);
            cbKID.setEnabled(true);
            try {
                Statement st = conn.getConnection().createStatement();
                String sql = "Select *from tim";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    String data = rs.getString("kd_tim");
                    cbKID.addItem(data);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }//GEN-LAST:event_rbTimActionPerformed

    private void cbKodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbKodeActionPerformed

    }//GEN-LAST:event_cbKodeActionPerformed

    private void btnTarifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTarifActionPerformed
        Harga();
    }//GEN-LAST:event_btnTarifActionPerformed

    private void btnTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTotalActionPerformed
        Total();
    }//GEN-LAST:event_btnTotalActionPerformed

    private void txtBayarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBayarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (kurang == true) {
                lblSisa.setVisible(true);
                jLabel12.setVisible(true);
                byr = Double.parseDouble(txtBayar.getText());
                sisa = tot - byr;
                String sis = String.valueOf(sisa);
                lblSisa.setText(sis);
                if ("0.0".equals(sis)) {
                    txtStatus.setText("Lunas");
                } else {
                    txtStatus.setText("Belum Lunas");
                }
            } else {
                lblSisa.setVisible(true);
                jLabel12.setVisible(true);
                byr = Double.parseDouble(txtBayar.getText());
                tot = Double.parseDouble(txtTotal.getText());
                sisa = tot - byr;
                String sis = String.valueOf(sisa);
                lblSisa.setText(sis);
                if ("0.0".equals(sis)) {
                    txtStatus.setText("Lunas");
                } else {
                    txtStatus.setText("Belum Lunas");
                }
                kurang = true;
            }
            btnSimpan.requestFocus();
        }
    }//GEN-LAST:event_txtBayarKeyPressed

    private void cbKIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbKIDActionPerformed
        if (rbPelanggan.isSelected()) {
            try {
                Statement st = conn.getConnection().createStatement();
                String sql = "Select *from pelanggan where id_pelanggan='" + cbKID.getSelectedItem() + "'";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    String data = (String) cbKID.getSelectedItem();
                    lblKID.setText(data);
                    txtNama.setText(rs.getString("nama_pelanggan"));
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else if (rbTim.isSelected()) {
            try {
                Statement st = conn.getConnection().createStatement();
                String sql = "Select *from tim where kd_tim='" + cbKID.getSelectedItem() + "'";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    String data = (String) cbKID.getSelectedItem();
                    lblKID.setText(data);
                    txtNama.setText(rs.getString("nama_tim"));
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            cbKID.setEnabled(false);
        }
    }//GEN-LAST:event_cbKIDActionPerformed

    private void btnDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetailActionPerformed
        FTSewaDetail dtSewa = new FTSewaDetail();
        this.getParent().add(dtSewa);
        dtSewa.setVisible(true);
    }//GEN-LAST:event_btnDetailActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser DCTglSewa;
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnDetail;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnTambah;
    private javax.swing.JButton btnTarif;
    private javax.swing.JButton btnTotal;
    private javax.swing.JButton btnUbah;
    private javax.swing.JComboBox<String> cbJamSewa;
    private javax.swing.JComboBox<String> cbKID;
    private javax.swing.JComboBox<String> cbKode;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JInternalFrame jInternalFrame2;
    private javax.swing.JInternalFrame jInternalFrame3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JLabel lblID;
    public javax.swing.JLabel lblKID;
    private javax.swing.JLabel lblNama;
    private javax.swing.JLabel lblSisa;
    private java.awt.PopupMenu popupMenu1;
    public javax.swing.JRadioButton rbGuest;
    public javax.swing.JRadioButton rbPelanggan;
    public javax.swing.JRadioButton rbTim;
    private javax.swing.JSpinner spLama;
    private javax.swing.JTable tbPenyewaan;
    private javax.swing.JTextField txtBayar;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtHarga;
    private javax.swing.JTextField txtKode;
    public javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtStatus;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
