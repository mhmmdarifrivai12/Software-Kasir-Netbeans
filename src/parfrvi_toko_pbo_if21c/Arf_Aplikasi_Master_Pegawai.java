/* 
        BAB 6
NAMA    : MUHAMMAD ARIF RIVAI
NPM     : 21312097
KELAS   : IF 21 C

Arf     = ARIF
Arfrvi  = Arif Rivai
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

public class Arf_Aplikasi_Master_Pegawai extends javax.swing.JFrame {

    Connection c;
    ResultSet r;
    Statement s;
    private Object [][] arfdatapegawai=null;
    private String[] label={"Kode Pegawai","Nama Pegawai","Tempat Lahir","Tanggal Lahir","Pendidikan","Jabatan","No Telp","Alamat"};
    
    public Arf_Aplikasi_Master_Pegawai() {
        initComponents();
        ArifBukaKoneksi();
        ArifBacaTabelPegawai();
        arf_tkd_peg.setVisible(true);
        
    }
    
    private void ArifBukaKoneksi(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            c=DriverManager.getConnection("jdbc:mysql://localhost/db_toko_pbo_if21c","root","");
            System.out.println("Koneksi Sukses");
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    private void ArifBacaTabelPegawai(){
        try{
            s=c.createStatement();
            String sql="Select*From arfrvi_pegawai";
            r=s.executeQuery(sql);
            ResultSetMetaData m=r.getMetaData();
            int kolom=m.getColumnCount();
            int baris=0;
            while(r.next()){ 
                baris=r.getRow();
            }
            arfdatapegawai=new Object[baris][kolom];
            int x=0;
            r.beforeFirst();
            while(r.next()){
                arfdatapegawai[x][0]=r.getString("arf_kd_peg");
                arfdatapegawai[x][1]=r.getString("arf_nm_peg");
                arfdatapegawai[x][2]=r.getString("arf_temp_lhr");
                arfdatapegawai[x][3]=r.getString("arf_tgl_lahir");
                arfdatapegawai[x][4]=r.getString("arf_pend");
                arfdatapegawai[x][5]=r.getString("arf_jab");
                arfdatapegawai[x][6]=r.getString("arf_no_telp");
                arfdatapegawai[x][7]=r.getString("arf_alamat");
                x++;
            }
            arf_tbl_peg.setModel(new DefaultTableModel(arfdatapegawai,label));
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void ArifSetTable(){
        int row=arf_tbl_peg.getSelectedRow();
        arf_tkd_peg.setText((String)arf_tbl_peg.getValueAt(row, 0));
        arf_tnm_peg.setText((String)arf_tbl_peg.getValueAt(row, 1));
        arf_ttemp.setText((String)arf_tbl_peg.getValueAt(row, 2));
        arf_ttgl.setText((String)arf_tbl_peg.getValueAt(row, 3));
        arf_cb_pen.setSelectedItem((String)arf_tbl_peg.getValueAt(row, 4));
        arf_cb_jab.setSelectedItem((String)arf_tbl_peg.getValueAt(row, 5));
        arf_ttelp.setText((String)arf_tbl_peg.getValueAt(row, 6));
        arf_talm.setText((String)arf_tbl_peg.getValueAt(row, 7));
    }
    
    private void ArifBersihField(){
        arf_tkd_peg.setText("");
        arf_tnm_peg.setText("");
        arf_ttemp.setText("");
        arf_ttgl.setText("");
        arf_cb_pen.setSelectedIndex(0);
        arf_cb_jab.setSelectedIndex(0);
        arf_ttelp.setText("");
        arf_talm.setText("");
    }
    
    private void ArifSimpanData(){
        try{
            String sql="Insert Into arfrvi_pegawai Values('"+arf_tkd_peg.getText()+"','"+arf_tnm_peg.getText()+"',"+ "'"+arf_ttemp.getText()+"','"+arf_ttgl.getText()+"','"+arf_cb_pen.getSelectedItem()+"','"+arf_cb_jab.getSelectedItem()+"','"+arf_ttelp.getText()+"','"+arf_talm.getText()+"')";
            s.executeUpdate(sql);
            s.close();
            JOptionPane.showMessageDialog(null,"Data Berhasil Ditambah");
            ArifBersihField();
            ArifBacaTabelPegawai();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    
    private void ArifEditData(){
        try{
            String sql="Update arfrvi_pegawai Set arf_kd_peg='"+arf_tkd_peg.getText()+"',arf_nm_peg='"+arf_tnm_peg.getText()+"',"
                    + "arf_temp_lhr='"+arf_ttemp.getText()+"',arf_tgl_lahir='"+arf_ttgl.getText()+"',arf_pend='"+arf_cb_pen.getSelectedItem()+"',"
                    + "arf_jab='"+arf_cb_jab.getSelectedItem()+"',arf_no_telp='"+arf_ttelp.getText()+"',arf_alamat='"+arf_talm.getText()+"'Where arf_kd_peg='"+arf_tkd_peg.getText()+"'";
            s.executeUpdate(sql);
            s.close();
            JOptionPane.showMessageDialog(null, "Data Berhasil Diedit");
            ArifBersihField();
            ArifBacaTabelPegawai();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    
    private void ArifHapusData(){
        try{
            String sql="Delete from arfrvi_pegawai Where arf_kd_peg='"+arf_tkd_peg.getText()+"'";
            s.executeUpdate(sql);
            s.close();
            JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
            ArifBersihField();
            ArifBacaTabelPegawai();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        arf_tbl_peg = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        arf_tkd_peg = new javax.swing.JTextField();
        arf_tnm_peg = new javax.swing.JTextField();
        arf_ttemp = new javax.swing.JTextField();
        arf_ttgl = new javax.swing.JTextField();
        arf_cb_pen = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        arf_cb_jab = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        arf_ttelp = new javax.swing.JTextField();
        arf_talm = new javax.swing.JTextField();
        arf_bt_tambah = new javax.swing.JButton();
        arf_bt_keluar = new javax.swing.JButton();
        arf_bt_edit = new javax.swing.JButton();
        arf_bt_hapus = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel2.setText("Arfrvi Table Pegawai");

        arf_tbl_peg.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Kode Pegawai", "Nama Pegawai", "Tempat Lahir", "Tanggal Lahir", "Pendidikan", "Jabatan", "No Telp", "Alamat"
            }
        ));
        arf_tbl_peg.setName("arf_tbl_peg"); // NOI18N
        arf_tbl_peg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                arf_tbl_pegMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(arf_tbl_peg);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel3.setText("Arfrvi Input Pegawai");

        jLabel1.setText("Kode Pegawai");

        jLabel4.setText("Nama Pegawai");

        jLabel5.setText("Tempat Lahir");

        jLabel6.setText("Tanggal Lahir");

        arf_tkd_peg.setName("arf_tkd_peg"); // NOI18N
        arf_tkd_peg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                arf_tkd_pegActionPerformed(evt);
            }
        });
        arf_tkd_peg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                arf_tkd_pegKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                arf_tkd_pegKeyTyped(evt);
            }
        });

        arf_tnm_peg.setName("arf_tnm_peg"); // NOI18N
        arf_tnm_peg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                arf_tnm_pegActionPerformed(evt);
            }
        });
        arf_tnm_peg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                arf_tnm_pegKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                arf_tnm_pegKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                arf_tnm_pegKeyTyped(evt);
            }
        });

        arf_ttemp.setName("arf_ttemp"); // NOI18N
        arf_ttemp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                arf_ttempKeyPressed(evt);
            }
        });

        arf_ttgl.setName("arf_ttgl"); // NOI18N
        arf_ttgl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                arf_ttglKeyPressed(evt);
            }
        });

        arf_cb_pen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- SD", "- SMP", "- SMA", "" }));
        arf_cb_pen.setName("arf_cb_pen"); // NOI18N
        arf_cb_pen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                arf_cb_penActionPerformed(evt);
            }
        });

        jLabel7.setText("Jabatan");

        jLabel8.setText("Pendidikan");

        arf_cb_jab.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Pegawai", "- Kasir", "- Satpam" }));
        arf_cb_jab.setName("arf_cb_jab"); // NOI18N
        arf_cb_jab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                arf_cb_jabActionPerformed(evt);
            }
        });

        jLabel9.setText("No Telpon");

        jLabel10.setText("Alamat");

        arf_ttelp.setName("arf_ttelp"); // NOI18N
        arf_ttelp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                arf_ttelpKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                arf_ttelpKeyTyped(evt);
            }
        });

        arf_talm.setName("arf_talm"); // NOI18N
        arf_talm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                arf_talmKeyPressed(evt);
            }
        });

        arf_bt_tambah.setText("Tambah");
        arf_bt_tambah.setName("arf_bt_tambah"); // NOI18N
        arf_bt_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                arf_bt_tambahActionPerformed(evt);
            }
        });

        arf_bt_keluar.setText("Keluar");
        arf_bt_keluar.setName("arf_bt_keluar"); // NOI18N
        arf_bt_keluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                arf_bt_keluarActionPerformed(evt);
            }
        });

        arf_bt_edit.setText("Edit");
        arf_bt_edit.setName("arf_bt_edit"); // NOI18N
        arf_bt_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                arf_bt_editActionPerformed(evt);
            }
        });

        arf_bt_hapus.setText("Hapus");
        arf_bt_hapus.setName("arf_bt_hapus"); // NOI18N
        arf_bt_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                arf_bt_hapusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(352, 352, 352))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(355, 355, 355))))))
            .addGroup(layout.createSequentialGroup()
                .addGap(122, 122, 122)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(68, 68, 68)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(arf_tkd_peg)
                            .addComponent(arf_tnm_peg)
                            .addComponent(arf_ttemp, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                            .addComponent(arf_ttgl))
                        .addGap(101, 101, 101)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addGap(66, 66, 66)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(arf_cb_jab, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(arf_talm, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(arf_ttelp)
                            .addComponent(arf_cb_pen, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(82, 82, 82))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(arf_bt_tambah)
                        .addGap(115, 115, 115)
                        .addComponent(arf_bt_edit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 187, Short.MAX_VALUE)
                        .addComponent(arf_bt_hapus)
                        .addGap(95, 95, 95)
                        .addComponent(arf_bt_keluar)
                        .addGap(153, 153, 153))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(arf_tkd_peg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(arf_cb_pen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(arf_tnm_peg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(arf_cb_jab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(arf_ttemp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(arf_ttelp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(arf_ttgl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(arf_talm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(arf_bt_tambah)
                    .addComponent(arf_bt_keluar)
                    .addComponent(arf_bt_edit)
                    .addComponent(arf_bt_hapus))
                .addGap(24, 24, 24))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void arf_tkd_pegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_arf_tkd_pegActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_arf_tkd_pegActionPerformed

    private void arf_tnm_pegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_arf_tnm_pegActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_arf_tnm_pegActionPerformed

    private void arf_cb_penActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_arf_cb_penActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_arf_cb_penActionPerformed

    private void arf_cb_jabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_arf_cb_jabActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_arf_cb_jabActionPerformed

    private void arf_bt_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_arf_bt_editActionPerformed
        // TODO add your handling code here:
        ArifEditData();
    }//GEN-LAST:event_arf_bt_editActionPerformed

    private void arf_tbl_pegMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_arf_tbl_pegMouseClicked
        // TODO add your handling code here:
        ArifSetTable();
    }//GEN-LAST:event_arf_tbl_pegMouseClicked

    private void arf_bt_keluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_arf_bt_keluarActionPerformed
        // TODO add your handling code here:
        int confirmed = JOptionPane.showConfirmDialog(null, "Yakin Ingin Keluar?", "Terima Kasih", JOptionPane.YES_NO_OPTION);
        if (confirmed == JOptionPane.YES_OPTION) {
            dispose();
        } else {
            setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            arf_tkd_peg.requestFocus();
        }
    }//GEN-LAST:event_arf_bt_keluarActionPerformed

    private void arf_bt_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_arf_bt_tambahActionPerformed
        // TODO add your handling code here:
        ArifSimpanData();
    }//GEN-LAST:event_arf_bt_tambahActionPerformed
    public void JumlahKarakter(KeyEvent e) {
        if (arf_tkd_peg.getText().length() == 10) {
            e.consume();
            JOptionPane.showMessageDialog(null, "Maksimal yang dimasukan Hanya 10 Karakter", "Peringatan", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void JumlahKarakterNama(KeyEvent evt) {
        if (arf_tnm_peg.getText().length() == 20) {
            JOptionPane.showMessageDialog(null, "Maksimal yang dimasukan Hanya 20 Karakter", "Peringatan", JOptionPane.WARNING_MESSAGE);
        }
    }
    private void arf_bt_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_arf_bt_hapusActionPerformed
        // TODO add your handling code here:
        ArifHapusData();
    }//GEN-LAST:event_arf_bt_hapusActionPerformed

    private void arf_tkd_pegKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_arf_tkd_pegKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            arf_tnm_peg.requestFocus();
        }
    }//GEN-LAST:event_arf_tkd_pegKeyPressed

    private void arf_ttempKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_arf_ttempKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            arf_ttgl.requestFocus();
        }
    }//GEN-LAST:event_arf_ttempKeyPressed

    private void arf_ttglKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_arf_ttglKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            arf_ttelp.requestFocus();
        }
    }//GEN-LAST:event_arf_ttglKeyPressed

    private void arf_ttelpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_arf_ttelpKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            arf_talm.requestFocus();
        }
    }//GEN-LAST:event_arf_ttelpKeyPressed

    private void arf_talmKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_arf_talmKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            arf_tkd_peg.requestFocus();
        }
    }//GEN-LAST:event_arf_talmKeyPressed

    private void arf_tnm_pegKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_arf_tnm_pegKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_arf_tnm_pegKeyReleased

    private void arf_tnm_pegKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_arf_tnm_pegKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            arf_ttemp.requestFocus();
        }
    }//GEN-LAST:event_arf_tnm_pegKeyPressed

    private void arf_ttelpKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_arf_ttelpKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if(!(Character.isDigit(c) || (c == KeyEvent.VK_TAB)||(c == KeyEvent.VK_DELETE))){
            evt.consume();
        }
    }//GEN-LAST:event_arf_ttelpKeyTyped

    private void arf_tkd_pegKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_arf_tkd_pegKeyTyped
        // TODO add your handling code here:
        JumlahKarakter(evt);
    }//GEN-LAST:event_arf_tkd_pegKeyTyped

    private void arf_tnm_pegKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_arf_tnm_pegKeyTyped
        // TODO add your handling code here:
        JumlahKarakterNama(evt);
    }//GEN-LAST:event_arf_tnm_pegKeyTyped

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(Arf_Aplikasi_Master_Pegawai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Arf_Aplikasi_Master_Pegawai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Arf_Aplikasi_Master_Pegawai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Arf_Aplikasi_Master_Pegawai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Arf_Aplikasi_Master_Pegawai().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton arf_bt_edit;
    private javax.swing.JButton arf_bt_hapus;
    private javax.swing.JButton arf_bt_keluar;
    private javax.swing.JButton arf_bt_tambah;
    private javax.swing.JComboBox<String> arf_cb_jab;
    private javax.swing.JComboBox<String> arf_cb_pen;
    private javax.swing.JTextField arf_talm;
    private javax.swing.JTable arf_tbl_peg;
    private javax.swing.JTextField arf_tkd_peg;
    private javax.swing.JTextField arf_tnm_peg;
    private javax.swing.JTextField arf_ttelp;
    private javax.swing.JTextField arf_ttemp;
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
