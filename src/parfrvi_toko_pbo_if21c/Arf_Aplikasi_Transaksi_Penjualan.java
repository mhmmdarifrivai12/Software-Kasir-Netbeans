/*
        BAB 8
NAMA    : Muhammad Arif Rivai
NPM     : 21312097
KELAS   : IF 21 C

Arf     = Arif
Arv  = Arfrvi
 */
package parfrvi_toko_pbo_if21c;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.*;
import java.io.*;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class Arf_Aplikasi_Transaksi_Penjualan extends javax.swing.JFrame {

    Connection c;
    ResultSet r;
    Statement s;
    private Object[][] datajual = null;
    private String[] label5 = {"Kode Barang", "Nama Barang", "Harga Jual", "Jumlah", "Total Harga", "Kode Pegawai", "Nama Pegawai"};

    /**
     * Creates new form *
     */
    public Arf_Aplikasi_Transaksi_Penjualan() {

        initComponents();
        BukaKoneksi();
//        BacaTabelPenjualan();
        arf_tkd_trans.setVisible(true);
        BacaTabelPenjualan();
        isiotomatisnmbrg();
        arf_tkd_trans.requestFocus();
    }

    private void BukaKoneksi() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            c=DriverManager.getConnection("jdbc:mysql://localhost/db_toko_pbo_if21c","root","");
            System.out.println("Koneksi Sukses");
        }catch(Exception e){
            System.out.println(e);
        }
    }

    private void BacaTabelPenjualan() {
        try {
            s = c.createStatement();
            String sql = "SELECT arfrvi_penjualan.arf_kd_brg, arfrvi_barang.nm_brg, arfrvi_barang.hrg_jual, arfrvi_penjualan.arf_jml, arfrvi_penjualan.arf_tot_hrg, arfrvi_penjualan.arf_kd_peg, arfrvi_pegawai.arf_nm_peg FROM arfrvi_penjualan, arfrvi_barang, arfrvi_pegawai WHERE arfrvi_penjualan.arf_kd_brg = arfrvi_barang.kd_brg AND arfrvi_pegawai.arf_kd_peg = arfrvi_penjualan.arf_kd_peg order by arfrvi_barang.kd_brg";

            r = s.executeQuery(sql);
            ResultSetMetaData m = r.getMetaData();
            int kolom = m.getColumnCount();
            int baris = 0;
            while (r.next()) {
                baris = r.getRow();
            }
            datajual = new Object[baris][kolom];
            int x = 0;
            r.beforeFirst();
            while (r.next()) {
                datajual[x][0] = r.getString("arf_kd_brg");
                datajual[x][1] = r.getString("nm_brg");
                datajual[x][2] = r.getString("hrg_jual");
                datajual[x][3] = r.getString("arf_jml");
                datajual[x][4] = r.getString("arf_tot_hrg");
                datajual[x][5] = r.getString("arf_kd_peg");
                datajual[x][6] = r.getString("arf_nm_peg");
                x++;
            }
            arf_tbl_jual.setModel(new DefaultTableModel(datajual, label5));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void SetTabel() {
        int row=arf_tbl_jual.getSelectedRow();
        arf_tkd_brg.setText((String) arf_tbl_jual.getValueAt(row, 0));
        arf_tnm_brg.setText((String) arf_tbl_jual.getValueAt(row, 1));
        arf_thrg_jual.setText((String) arf_tbl_jual.getValueAt(row, 2));
        arf_tjml.setText((String) arf_tbl_jual.getValueAt(row, 3));
        arf_thrg_tot.setText((String) arf_tbl_jual.getValueAt(row, 4));
        arf_tkd_peg.setText((String) arf_tbl_jual.getValueAt(row, 5));
        arf_tnm_peg.setText((String) arf_tbl_jual.getValueAt(row, 6));
        arf_itot.setText("");
        arf_tbyr.setText("");
        arf_kembalian.setText("");
    }

    private void BersihField() {
        arf_tkd_trans.setText("");
        arf_ttgl.setText("");
        arf_tkd_brg.setText("");
        arf_tnm_brg.setText("");
        arf_thrg_jual.setText("");
        arf_tjml.setText("");
        arf_thrg_tot.setText("");
        arf_tkd_peg.setText("");
        arf_tnm_peg.setText("");
    }

    private void SimpanData() {
        try {
            String sql = "insert into arfrvi_penjualan values('"+arf_tkd_trans.getText()+"','"+arf_ttgl.getText()+"','"+arf_tkd_brg.getText()+"','"+arf_tjml.getText()+"','"+arf_tkd_peg.getText()+"','"+arf_thrg_tot.getText()+"')";
            s.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan");
            BacaTabelPenjualan();
            BersihField();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void TambahData() {
        try {
            String sql = "insert into arfrvi_penjualan values('"+arf_tkd_trans.getText()+"','"+arf_ttgl.getText()+"','"+arf_tkd_brg.getText()+"','"+arf_tjml.getText()+"','"+arf_tkd_peg.getText()+"')";
            s.executeUpdate(sql);
            s.close();
            JOptionPane.showMessageDialog(null, "Data berhasil ditambah");
            BersihField();
            BacaTabelPenjualan();
            }
            catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
        }
    }

    private void HapusData() {
        try {
            String sql = "Delete from arfrvi_penjualan Where kd_brg='" + arf_tkd_brg.getText() + "' ";
            s.executeUpdate(sql);
            s.close();
            JOptionPane.showMessageDialog(null, "Data berhasil dihapus ");
            BersihField();
            BacaTabelPenjualan();
            }
            catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
    }
 

    private void total() {
        int y = 0;
        int totrec=arf_tbl_jual.getRowCount();
        for (int z = 0; z < totrec; z++) {
            y = y + Integer.parseInt(arf_tbl_jual.getValueAt(2, 4).toString());
            arf_itot.setText(String.valueOf(y));
        }
    }
    
    private void isiotomatisnmbrg(){
        try {
            s=c.createStatement();
            r=s.executeQuery("select nm_brg from arfrvi_barang where kd_brg='"+arf_tkd_brg.getText()+"'");
            while(r.next()){
                arf_tnm_brg.setText((String)r.getString("nm_brg"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void ArfJumlahKarakter(KeyEvent e) {
        if (arf_tkd_trans.getText().length() == 10) {
            e.consume();
            JOptionPane.showMessageDialog(null, "Maksimal yang dimasukan Hanya 10 Karakter", "Peringatan", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void ArfJumlahKarakterKd_Brg(KeyEvent e) {
        if (arf_tkd_brg.getText().length() == 10) {
            e.consume();
            JOptionPane.showMessageDialog(null, "Maksimal yang dimasukan Hanya 10 Karakter", "Peringatan", JOptionPane.WARNING_MESSAGE);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.'
     */
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        arf_bt_total = new javax.swing.JButton();
        arf_bt_tot = new javax.swing.JButton();
        arf_bt_tambah = new javax.swing.JButton();
        arf_bt_kembali = new javax.swing.JButton();
        arf_bt_hapus = new javax.swing.JButton();
        arf_bt_simpan = new javax.swing.JButton();
        arf_bt_cetak = new javax.swing.JButton();
        arf_bt_keluar = new javax.swing.JButton();
        arf_tkd_trans = new javax.swing.JTextField();
        arf_tkd_brg = new javax.swing.JTextField();
        arf_tnm_brg = new javax.swing.JTextField();
        arf_tkd_peg = new javax.swing.JTextField();
        arf_tbyr = new javax.swing.JTextField();
        arf_ttgl = new javax.swing.JTextField();
        arf_thrg_jual = new javax.swing.JTextField();
        arf_tjml = new javax.swing.JTextField();
        arf_tnm_peg = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        arf_tbl_jual = new javax.swing.JTable();
        arf_thrg_tot = new javax.swing.JTextField();
        arf_itot = new javax.swing.JLabel();
        arf_kembalian = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("TRANSAKSI PENJUALAN");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Kode Transaksi");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Tanggal Transaksi");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Kode Barang");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Harga Jual");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Nama Barang");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("Jumlah Beli");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setText("Kode Pegawai");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("Nama Pegawai");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setText("Pembayaran");

        arf_bt_total.setLabel("Total");
        arf_bt_total.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                arf_bt_totalActionPerformed(evt);
            }
        });

        arf_bt_tot.setText("Total Bayar");
        arf_bt_tot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                arf_bt_totActionPerformed(evt);
            }
        });

        arf_bt_tambah.setText("Tambah");
        arf_bt_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                arf_bt_tambahActionPerformed(evt);
            }
        });

        arf_bt_kembali.setText("Kembali");
        arf_bt_kembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                arf_bt_kembaliActionPerformed(evt);
            }
        });

        arf_bt_hapus.setText("Hapus");
        arf_bt_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                arf_bt_hapusActionPerformed(evt);
            }
        });

        arf_bt_simpan.setText("Simpan");

        arf_bt_cetak.setText("Cetak");
        arf_bt_cetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                arf_bt_cetakActionPerformed(evt);
            }
        });

        arf_bt_keluar.setText("Keluar");
        arf_bt_keluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                arf_bt_keluarActionPerformed(evt);
            }
        });

        arf_tkd_trans.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                arf_tkd_transActionPerformed(evt);
            }
        });
        arf_tkd_trans.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                arf_tkd_transKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                arf_tkd_transKeyTyped(evt);
            }
        });

        arf_tkd_brg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                arf_tkd_brgKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                arf_tkd_brgKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                arf_tkd_brgKeyTyped(evt);
            }
        });

        arf_tnm_brg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                arf_tnm_brgKeyPressed(evt);
            }
        });

        arf_tkd_peg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                arf_tkd_pegKeyReleased(evt);
            }
        });

        arf_ttgl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                arf_ttglKeyPressed(evt);
            }
        });

        arf_thrg_jual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                arf_thrg_jualActionPerformed(evt);
            }
        });
        arf_thrg_jual.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                arf_thrg_jualKeyPressed(evt);
            }
        });

        arf_tjml.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                arf_tjmlKeyPressed(evt);
            }
        });

        arf_tbl_jual.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Kode Barang", "Nama Barang", "Harga Jual", "Jumlah", "Total Harga", "Kode Pegawai", "Nama Pegawai"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        arf_tbl_jual.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                arf_tbl_jualMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(arf_tbl_jual);

        arf_thrg_tot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                arf_thrg_totActionPerformed(evt);
            }
        });

        arf_itot.setText("                                             ");

        arf_kembalian.setText("                                    ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(282, 282, 282)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(arf_bt_kembali)
                                    .addComponent(jLabel10))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(arf_tbyr, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(arf_kembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(arf_bt_keluar, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(arf_bt_tot)
                                .addGap(18, 18, 18)
                                .addComponent(arf_itot, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(arf_bt_cetak)
                                .addGap(18, 18, 18)
                                .addComponent(arf_bt_hapus)
                                .addGap(18, 18, 18)
                                .addComponent(arf_bt_simpan)
                                .addGap(18, 18, 18)
                                .addComponent(arf_bt_tambah))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel8)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel6)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(43, 43, 43)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(arf_tkd_trans, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(arf_tkd_brg, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(arf_tnm_brg, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(33, 33, 33)
                                        .addComponent(arf_tkd_peg, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel7))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(arf_tjml)
                                            .addComponent(arf_ttgl)
                                            .addComponent(arf_thrg_jual, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel9)
                                            .addComponent(arf_bt_total, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(46, 46, 46)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(arf_thrg_tot, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(arf_tnm_peg, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 760, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(50, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(arf_tkd_trans, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(arf_tkd_brg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(arf_tnm_brg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(51, 51, 51)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(arf_tkd_peg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(arf_ttgl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(arf_thrg_jual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(arf_tjml, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(arf_bt_total)
                            .addComponent(arf_thrg_tot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(arf_tnm_peg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(arf_bt_tambah)
                    .addComponent(arf_bt_tot)
                    .addComponent(arf_itot)
                    .addComponent(arf_bt_simpan)
                    .addComponent(arf_bt_hapus)
                    .addComponent(arf_bt_cetak))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(arf_tbyr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(arf_kembalian)
                    .addComponent(arf_bt_kembali)
                    .addComponent(arf_bt_keluar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void arf_bt_totalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_arf_bt_totalActionPerformed
        if(arf_tjml.getText().isEmpty() && arf_thrg_jual.getText().isEmpty()){
            arf_thrg_tot.setText("0");
        }else{
            int a = Integer.valueOf(arf_tjml.getText()) * Integer.valueOf(arf_thrg_jual.getText());
        arf_thrg_tot.setText(String.valueOf(a));
        }
    }//GEN-LAST:event_arf_bt_totalActionPerformed

    private void arf_thrg_jualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_arf_thrg_jualActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_arf_thrg_jualActionPerformed

    private void arf_tkd_transActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_arf_tkd_transActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_arf_tkd_transActionPerformed

    private void arf_thrg_totActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_arf_thrg_totActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_arf_thrg_totActionPerformed

    private void arf_bt_kembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_arf_bt_kembaliActionPerformed
        if(arf_tbyr.getText().isEmpty()){
            arf_kembalian.setText("");
        }else{
            arf_kembalian.setText(Float.toString(Float.parseFloat(arf_tbyr.getText()) - Float.parseFloat(arf_itot.getText())));
        }
    }//GEN-LAST:event_arf_bt_kembaliActionPerformed

    private void arf_bt_totActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_arf_bt_totActionPerformed
        arf_itot.setText(arf_thrg_tot.getText());
    }//GEN-LAST:event_arf_bt_totActionPerformed

    private void arf_tkd_pegKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_arf_tkd_pegKeyReleased
        if(arf_tkd_peg.getText().isEmpty()){
            arf_tnm_peg.setText("");
        }else{
            try {
                s=c.createStatement();
                r=s.executeQuery("select arf_nm_peg from arfrvi_pegawai where arf_kd_peg='"+arf_tkd_peg.getText()+"'");
                r.beforeFirst();
                while(r.next()){
                    arf_tnm_peg.setText((String)r.getString("arf_nm_peg"));
                    System.out.println((String)r.getString("arf_nm_peg"));
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_arf_tkd_pegKeyReleased

    private void arf_tkd_brgKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_arf_tkd_brgKeyReleased
        if(arf_tkd_brg.getText().isEmpty()){
            arf_tnm_brg.setText("");
        }else{
            try {
                s=c.createStatement();
                r=s.executeQuery("select nm_brg from arfrvi_barang where kd_brg='"+arf_tkd_brg.getText()+"'");
                r.beforeFirst();
                while(r.next()){
                    arf_tnm_brg.setText((String)r.getString("nm_brg"));
                    System.out.println((String)r.getString("nm_brg"));
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_arf_tkd_brgKeyReleased

    private void arf_tbl_jualMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_arf_tbl_jualMouseClicked
        SetTabel();
    }//GEN-LAST:event_arf_tbl_jualMouseClicked

    private void arf_bt_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_arf_bt_tambahActionPerformed
        SimpanData();
        
    }//GEN-LAST:event_arf_bt_tambahActionPerformed

    private void arf_bt_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_arf_bt_hapusActionPerformed
HapusData();
    }//GEN-LAST:event_arf_bt_hapusActionPerformed

    private void arf_bt_keluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_arf_bt_keluarActionPerformed
        int a = JOptionPane.showConfirmDialog(rootPane, "Apakah anda yakin ingin keluar?", "Confirmation", JOptionPane.YES_OPTION);
        if(a == JOptionPane.YES_OPTION){
            dispose();
        }else{
        
        }
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        arf_tkd_trans.requestFocus();
    }//GEN-LAST:event_arf_bt_keluarActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        int a = JOptionPane.showConfirmDialog(rootPane, "Apakah anda yakin ingin keluar?", "Confirmation", JOptionPane.YES_OPTION);
        if(a == JOptionPane.YES_OPTION){
            dispose();
        }else{
        
        }
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        arf_tkd_trans.requestFocus();
    }//GEN-LAST:event_formWindowClosing

    private void arf_tkd_transKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_arf_tkd_transKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            arf_ttgl.requestFocus();
        }
    }//GEN-LAST:event_arf_tkd_transKeyPressed

    private void arf_tkd_transKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_arf_tkd_transKeyTyped
        // TODO add your handling code here:
        ArfJumlahKarakter(evt);
    }//GEN-LAST:event_arf_tkd_transKeyTyped

    private void arf_ttglKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_arf_ttglKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            arf_tkd_brg.requestFocus();
        }
    }//GEN-LAST:event_arf_ttglKeyPressed

    private void arf_tkd_brgKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_arf_tkd_brgKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            arf_tnm_brg.requestFocus();
        }
    }//GEN-LAST:event_arf_tkd_brgKeyPressed

    private void arf_tkd_brgKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_arf_tkd_brgKeyTyped
        // TODO add your handling code here:
        ArfJumlahKarakterKd_Brg(evt);
    }//GEN-LAST:event_arf_tkd_brgKeyTyped

    private void arf_tnm_brgKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_arf_tnm_brgKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            arf_thrg_jual.requestFocus();
        }
    }//GEN-LAST:event_arf_tnm_brgKeyPressed

    private void arf_thrg_jualKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_arf_thrg_jualKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            arf_tjml.requestFocus();
        }
    }//GEN-LAST:event_arf_thrg_jualKeyPressed

    private void arf_tjmlKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_arf_tjmlKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            arf_tkd_peg.requestFocus();
        }
    }//GEN-LAST:event_arf_tjmlKeyPressed

    private void arf_bt_cetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_arf_bt_cetakActionPerformed
        // TODO add your handling code here:
        String sql = "Select * from arfrvi_penjualan";
    try{
    
        JasperPrint jp = JasperFillManager.fillReport(getClass().getResourceAsStream("arfrvi_laporanpenjualan.jasper"),null, c);
        JasperViewer.viewReport (jp, false);
    } catch(Exception e){
        JOptionPane.showMessageDialog(null, e);
    }
    }//GEN-LAST:event_arf_bt_cetakActionPerformed

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
            java.util.logging.Logger.getLogger(Arf_Aplikasi_Master_Supplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Arf_Aplikasi_Master_Supplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Arf_Aplikasi_Master_Supplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Arf_Aplikasi_Master_Supplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Arf_Aplikasi_Transaksi_Penjualan().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton arf_bt_cetak;
    private javax.swing.JButton arf_bt_hapus;
    private javax.swing.JButton arf_bt_keluar;
    private javax.swing.JButton arf_bt_kembali;
    private javax.swing.JButton arf_bt_simpan;
    private javax.swing.JButton arf_bt_tambah;
    private javax.swing.JButton arf_bt_tot;
    private javax.swing.JButton arf_bt_total;
    private javax.swing.JLabel arf_itot;
    private javax.swing.JLabel arf_kembalian;
    private javax.swing.JTable arf_tbl_jual;
    private javax.swing.JTextField arf_tbyr;
    private javax.swing.JTextField arf_thrg_jual;
    private javax.swing.JTextField arf_thrg_tot;
    private javax.swing.JTextField arf_tjml;
    private javax.swing.JTextField arf_tkd_brg;
    private javax.swing.JTextField arf_tkd_peg;
    private javax.swing.JTextField arf_tkd_trans;
    private javax.swing.JTextField arf_tnm_brg;
    private javax.swing.JTextField arf_tnm_peg;
    private javax.swing.JTextField arf_ttgl;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
