/*
        BAB 5
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

public class Arf_Aplikasi_Master_Supplier extends javax.swing.JFrame {
    public static int statusSearching = 0;
    Connection c;
    ResultSet r;
    Statement s;
    private Object [][] arfdatasupplier=null;
    private String[] label1={"Kode Supplier","Nama Perusahaan","Nama Penanggung Jawab","No Telpon","Email","Alamat"};
    
    public Arf_Aplikasi_Master_Supplier(){
        initComponents();
        ArifBukaKoneksi();
        ArifBacaTabelSupplier();
        arf_tkd_sup.setVisible(true);
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
    private void ArifBacaTabelSupplier(){
        try{
            s=c.createStatement();
            String sql="Select*From arfrvi_supplier Order By arf_kd_sup";
            r=s.executeQuery(sql);
            ResultSetMetaData m=r.getMetaData();
            int kolom=m.getColumnCount();
            int baris=0;
            while(r.next()){
                baris=r.getRow();
            }
            arfdatasupplier=new Object[baris][kolom];
            int x=0;
            r.beforeFirst();
            while(r.next()){
                arfdatasupplier[x][0]=r.getString("arf_kd_sup");
                arfdatasupplier[x][1]=r.getString("arf_nm_per");
                arfdatasupplier[x][2]=r.getString("arf_nm_pen");
                arfdatasupplier[x][3]=r.getString("arf_no_telp");
                arfdatasupplier[x][4]=r.getString("arf_email");
                arfdatasupplier[x][5]=r.getString("arf_alamat");
                x++;
            }
            arf_tbl_sup.setModel(new DefaultTableModel(arfdatasupplier,label1));
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void ArifSetTable(){
        int row=arf_tbl_sup.getSelectedRow();
        arf_tkd_sup.setText((String)arf_tbl_sup.getValueAt(row, 0));
        arf_tnm_per.setText((String)arf_tbl_sup.getValueAt(row, 1));
        arf_tnm_pen.setText((String)arf_tbl_sup.getValueAt(row, 2));
        arf_tno_telp.setText((String)arf_tbl_sup.getValueAt(row, 3));
        arf_temail.setText((String)arf_tbl_sup.getValueAt(row, 4));
        arf_talm.setText((String)arf_tbl_sup.getValueAt(row, 5));
    }
    private void ArifBersihField(){
        arf_tkd_sup.setText("");
        arf_tnm_per.setText("");
        arf_tnm_pen.setText("");
        arf_tno_telp.setText("");
        arf_temail.setText("");
        arf_talm.setText("");
    }
    private void ArifSimpanData(){
        try{
            String sql="Insert Into arfrvi_supplier Values('"+arf_tkd_sup.getText()+"','"+arf_tnm_per.getText()+"',"+ "'"+arf_tnm_pen.getText()+"','"+arf_tno_telp.getText()+"','"+arf_temail.getText()+"','"+arf_talm.getText()+"')";
            s.executeUpdate(sql);
            s.close();
            JOptionPane.showMessageDialog(null,"Data Berhasil Ditambah");
            ArifBersihField();
            ArifBacaTabelSupplier();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    public void ArifJumlahKarakter(KeyEvent e) {
        if (arf_tkd_sup.getText().length() == 10) {
            e.consume();
            JOptionPane.showMessageDialog(null, "Maksimal yang dimasukan Hanya 10 Karakter", "Peringatan", JOptionPane.WARNING_MESSAGE);
        }
    }
    private void ArifEditData(){
        try{
            String sql="Update arfrvi_supplier Set arf_kd_sup='"+arf_tkd_sup.getText()+"',arf_nm_per='"+arf_tnm_per.getText()+"',"
                    + "arf_nm_pen='"+arf_tnm_pen.getText()+"',arf_no_telp='"+arf_tno_telp.getText()+"',arf_email='"+arf_temail.getText()+"',"
                    + "arf_alamat='"+arf_talm.getText()+"'Where arf_kd_sup='"+arf_tkd_sup.getText()+"'";
            s.executeUpdate(sql);
            s.close();
            JOptionPane.showMessageDialog(null, "Data Berhasil Diedit");
            ArifBersihField();
            ArifBacaTabelSupplier();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    private void ArifHapusData(){
        try{
            String sql="Delete from arfrvi_supplier Where arf_kd_sup='"+arf_tkd_sup.getText()+"'";
            s.executeUpdate(sql);
            s.close();
            JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
            ArifBersihField();
            ArifBacaTabelSupplier();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void ArifExitWindow(){
            int confirmed = JOptionPane.showConfirmDialog
                (null, "Keluar Dari Program?","Exit",JOptionPane.YES_NO_OPTION);
            if (confirmed == JOptionPane.YES_OPTION)
            {
                    dispose();
            }else
                {
                    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                    arf_tkd_sup.requestFocus();
                }
            }

    
    
    /**
     * Creates new form Arfrvi_Aplikasi_Master_Supplier
     
    public Arfrvi_Aplikasi_Master_Supplier() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        arf_tbl_sup = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        arf_tkd_sup = new javax.swing.JTextField();
        arf_tnm_per = new javax.swing.JTextField();
        arf_tno_telp = new javax.swing.JTextField();
        arf_tnm_pen = new javax.swing.JTextField();
        arf_temail = new javax.swing.JTextField();
        arf_talm = new javax.swing.JTextField();
        arf_bt_tambah = new javax.swing.JButton();
        arf_bt_edit = new javax.swing.JButton();
        arf_bt_hapus = new javax.swing.JButton();
        arf_bt_keluar = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel1.setText("TABEL DATA BARANG");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 255, 255));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel2.setText("TABEL SUPPLIER");

        arf_tbl_sup.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Kode Supplier", "Nama Perusahaan", "Nama Penanggung Jawab", "No Telpon", "Email", "Alamat"
            }
        ));
        arf_tbl_sup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                arf_tbl_supMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(arf_tbl_sup);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel3.setText("INPUT DATA SUPPLIER");

        jLabel4.setText("Kode Supplier");

        jLabel5.setText("Nama Perusahaan");

        jLabel6.setText("Nama Penanggung Jawab");

        jLabel7.setText("No Telpon");

        jLabel8.setText("Email");

        jLabel9.setText("Alamat");

        arf_tkd_sup.setName("arf_tkd_sup"); // NOI18N
        arf_tkd_sup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                arf_tkd_supActionPerformed(evt);
            }
        });
        arf_tkd_sup.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                arf_tkd_supKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                arf_tkd_supKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                arf_tkd_supKeyTyped(evt);
            }
        });

        arf_tnm_per.setName("arf_tnm_per"); // NOI18N
        arf_tnm_per.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                arf_tnm_perActionPerformed(evt);
            }
        });
        arf_tnm_per.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                arf_tnm_perKeyPressed(evt);
            }
        });

        arf_tno_telp.setName("arf_tno_telp"); // NOI18N
        arf_tno_telp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                arf_tno_telpKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                arf_tno_telpKeyTyped(evt);
            }
        });

        arf_tnm_pen.setName("arf_tnm_pen"); // NOI18N
        arf_tnm_pen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                arf_tnm_penActionPerformed(evt);
            }
        });
        arf_tnm_pen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                arf_tnm_penKeyPressed(evt);
            }
        });

        arf_temail.setName("arf_temail"); // NOI18N
        arf_temail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                arf_temailKeyPressed(evt);
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

        arf_bt_keluar.setText("Keluar");
        arf_bt_keluar.setName("arf_bt_keluar"); // NOI18N
        arf_bt_keluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                arf_bt_keluarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel7)
                                        .addComponent(jLabel8)
                                        .addComponent(jLabel9))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(arf_bt_tambah)
                                        .addGap(76, 76, 76)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(7, 7, 7)
                                        .addComponent(arf_bt_edit)
                                        .addGap(86, 86, 86)
                                        .addComponent(arf_bt_hapus)
                                        .addGap(109, 109, 109)
                                        .addComponent(arf_bt_keluar))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(25, 25, 25)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(arf_tnm_pen, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(arf_tkd_sup, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
                                                .addComponent(arf_tnm_per)
                                                .addComponent(arf_tno_telp)
                                                .addComponent(arf_temail))))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(arf_talm, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25)))
                        .addGap(0, 263, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(331, 331, 331))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(386, 386, 386))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 19, Short.MAX_VALUE)
                        .addGap(38, 38, 38)
                        .addComponent(arf_tkd_sup, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(arf_tnm_per, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(arf_tnm_pen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(arf_tno_telp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(arf_temail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(arf_talm, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(arf_bt_tambah)
                    .addComponent(arf_bt_edit)
                    .addComponent(arf_bt_hapus)
                    .addComponent(arf_bt_keluar))
                .addGap(55, 55, 55))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void arf_tnm_penActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_arf_tnm_penActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_arf_tnm_penActionPerformed

    private void arf_tkd_supActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_arf_tkd_supActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_arf_tkd_supActionPerformed

    private void arf_tnm_perActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_arf_tnm_perActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_arf_tnm_perActionPerformed

    private void arf_bt_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_arf_bt_tambahActionPerformed
        // TODO add your handling code here:
        ArifSimpanData();    
        if (arf_tnm_per.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Kolom Tidak Boleh Kosong!!", "Peringatan", JOptionPane.WARNING_MESSAGE);
        } else if (arf_tnm_pen.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Kolom Jumlah Tidak Boleh Kosong!!", "Peringatan", JOptionPane.WARNING_MESSAGE);
        } else if (arf_tno_telp.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Kolom Rusak Tidak Boleh Kosong!!", "Peringatan", JOptionPane.WARNING_MESSAGE);
        } else if (arf_talm.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Kolom Sisa Besar Tidak Boleh Kosong!!", "Peringatan", JOptionPane.WARNING_MESSAGE);
        } else if (arf_temail.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Kolom Diskon Kecil Tidak Boleh Kosong!!", "Peringatan", JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Data Berhasil Di Simpan!!");
        }  
    }//GEN-LAST:event_arf_bt_tambahActionPerformed
    public void JumlahKarakter(KeyEvent e) {
        if (arf_tkd_sup.getText().length() == 10) {
            e.consume();
            JOptionPane.showMessageDialog(null, "Maksimal yang dimasukan Hanya 10 Karakter", "Peringatan", JOptionPane.WARNING_MESSAGE);
        }
    }
    private void arf_bt_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_arf_bt_editActionPerformed
        // TODO add your handling code here:
        ArifEditData();
    }//GEN-LAST:event_arf_bt_editActionPerformed

    private void arf_bt_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_arf_bt_hapusActionPerformed
        // TODO add your handling code here:
        ArifHapusData();
    }//GEN-LAST:event_arf_bt_hapusActionPerformed

    private void arf_bt_keluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_arf_bt_keluarActionPerformed
        // TODO add your handling code here:
        ArifExitWindow();
    }//GEN-LAST:event_arf_bt_keluarActionPerformed

    private void arf_tbl_supMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_arf_tbl_supMouseClicked
        // TODO add your handling code here:
        ArifSetTable();
    }//GEN-LAST:event_arf_tbl_supMouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing

    private void arf_tkd_supKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_arf_tkd_supKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            arf_tnm_per.requestFocus();
        }
    }//GEN-LAST:event_arf_tkd_supKeyPressed

    private void arf_tnm_perKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_arf_tnm_perKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            arf_tnm_pen.requestFocus();
        }
    }//GEN-LAST:event_arf_tnm_perKeyPressed

    private void arf_tnm_penKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_arf_tnm_penKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            arf_tno_telp.requestFocus();
        }
    }//GEN-LAST:event_arf_tnm_penKeyPressed

    private void arf_tno_telpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_arf_tno_telpKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            arf_temail.requestFocus();
        }
    }//GEN-LAST:event_arf_tno_telpKeyPressed

    private void arf_temailKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_arf_temailKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            arf_talm.requestFocus();
        }
    }//GEN-LAST:event_arf_temailKeyPressed

    private void arf_tkd_supKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_arf_tkd_supKeyTyped
        // TODO add your handling code here:
        ArifJumlahKarakter(evt);
    }//GEN-LAST:event_arf_tkd_supKeyTyped

    private void arf_tkd_supKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_arf_tkd_supKeyReleased
        // TODO add your handling code here:
        try{
            s=c.createStatement();
            r=s.executeQuery("select*from arfrvi_supplier where arf_kd_sup like'"+arf_tkd_sup.getText()+"%'");
            int baris =0,y=0,x=0;
            ResultSetMetaData rsmd = r.getMetaData();
            y=rsmd.getColumnCount();
            System.out.println("select arf_nm_per from arfrvi_supplier where arf_kd_sup like '"+arf_tkd_sup.getText()+"%'");
            while(r.next()){
                baris = r.getRow();
            }
            arfdatasupplier = new Object [baris] [y];
            r.beforeFirst();
            while(r.next()){
                arfdatasupplier[x][0] = (String)r.getString("arf_kd_sup");
                arfdatasupplier[x][1] = (String)r.getString("arf_nm_per");
                arfdatasupplier[x][2] = (String)r.getString("arf_nm_pen");
                arfdatasupplier[x][3] = (String)r.getString("arf_no_telp");
                arfdatasupplier[x][4] = (String)r.getString("arf_email");
                arfdatasupplier[x][5] = (String)r.getString("arf_alamat");
                x++;
            }
            arf_tbl_sup.setModel(new DefaultTableModel(arfdatasupplier,label1));
            System.out.println("y");
        }catch (SQLException e){
            System.out.println(e);
        }
    }//GEN-LAST:event_arf_tkd_supKeyReleased

    private void arf_tno_telpKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_arf_tno_telpKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if(!(Character.isDigit(c) || (c == KeyEvent.VK_TAB)||(c == KeyEvent.VK_DELETE))){
            evt.consume();
        }
    }//GEN-LAST:event_arf_tno_telpKeyTyped

    private void arf_talmKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_arf_talmKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            arf_tkd_sup.requestFocus();
        }
    }//GEN-LAST:event_arf_talmKeyPressed

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
                new Arf_Aplikasi_Master_Supplier().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton arf_bt_edit;
    private javax.swing.JButton arf_bt_hapus;
    private javax.swing.JButton arf_bt_keluar;
    private javax.swing.JButton arf_bt_tambah;
    private javax.swing.JTextField arf_talm;
    private javax.swing.JTable arf_tbl_sup;
    private javax.swing.JTextField arf_temail;
    private javax.swing.JTextField arf_tkd_sup;
    private javax.swing.JTextField arf_tnm_pen;
    private javax.swing.JTextField arf_tnm_per;
    private javax.swing.JTextField arf_tno_telp;
    private javax.swing.JLabel jLabel1;
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
