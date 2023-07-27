/*
        BAB 7
NAMA    : MUHAMMAD ARIF RIVAI
NPM     : 21312097
KELAS   : IF 21 C

Arf     = Arif
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

public class Arf_Aplikasi_Transaksi_Pembelian extends javax.swing.JFrame {
    Connection c;
    ResultSet r;
    Statement s;
    private Object[][] arfdatapembelian = null;
    private String[] label4 = {"Kode Pembelian", "Tanggal Pembelian", "Kode Supplier", "Kode Barang", "Jumlah Pembelian", "Harga Beli"};
    
    public Arf_Aplikasi_Transaksi_Pembelian() {
        initComponents();
        ArifBukaKoneksi();
        ArifBacaTabelPembelian();
        arf_tkd_beli.setVisible(true);
        arf_tkd_beli.requestFocus();
    }
    
    private void ArifBukaKoneksi() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost/db_toko_pbo_if21c", "root", "");
            System.out.println("Koneksi Sukses");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    private void ArifBacaTabelPembelian() {

        try {
            s = c.createStatement();
            String sql = "Select arfrvi_pembelian.arf_kd_beli, arfrvi_pembelian.arf_tgl_beli, arfrvi_pembelian.arf_kd_sup, arfrvi_pembelian.kd_brg, arfrvi_pembelian.arf_jum_beli, arfrvi_pembelian.arf_hrg_beli From arfrvi_pembelian, arfrvi_supplier, arfrvi_barang Where arfrvi_pembelian.arf_kd_sup=arfrvi_supplier.arf_kd_sup AND arfrvi_pembelian.kd_brg=arfrvi_barang.kd_brg Order By arfrvi_pembelian.arf_kd_beli";
            r = s.executeQuery(sql);
            ResultSetMetaData m = r.getMetaData();
            int kolom = m.getColumnCount();
            int baris = 0;
            while (r.next()) {
                baris = r.getRow();
            }
            arfdatapembelian = new Object[baris][kolom];
            int x = 0;
            r.beforeFirst();
            while (r.next()) {
                arfdatapembelian[x][0] = r.getString("arf_kd_beli");
            	arfdatapembelian[x][1] = r.getString("arf_tgl_beli");
            	arfdatapembelian[x][2] = r.getString("arf_kd_sup");
            	arfdatapembelian[x][3] = r.getString("kd_brg");
            	arfdatapembelian[x][4] = r.getString("arf_jum_beli");
            	arfdatapembelian[x][5] = r.getString("arf_hrg_beli");
                x++;
            }
            arf_tbl_beli.setModel(new DefaultTableModel(arfdatapembelian, label4));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void ArifSetTabel() {
        int row = arf_tbl_beli.getSelectedRow();
        arf_tkd_beli.setText((String) arf_tbl_beli.getValueAt(row, 0));
        arf_ttgl_beli.setText((String) arf_tbl_beli.getValueAt(row, 1));
        arf_tkd_sup.setText((String) arf_tbl_beli.getValueAt(row, 2));
        tkd_brg.setText((String) arf_tbl_beli.getValueAt(row, 3));
        arf_tjum.setText((String) arf_tbl_beli.getValueAt(row, 4));
        arf_thrg_beli.setText((String) arf_tbl_beli.getValueAt(row, 5));
    }
    
    private void ArifBersihField() {
        arf_tkd_beli.setText("");
        arf_ttgl_beli.setText("");
        arf_tkd_sup.setText("");
        tkd_brg.setText("");
        arf_tjum.setText("");
        arf_thrg_beli.setText("");
    }
    
    private void ArifSimpanData(){
        try {
            String sql = "Insert Into arfrvi_pembelian Values('"+arf_tkd_beli.getText()+"','"+arf_ttgl_beli.getText()+"','"+arf_tkd_sup.getText()+"','"+tkd_brg.getText()+"','"+arf_tjum.getText()+"','"+arf_thrg_beli.getText()+"')";
            s.executeUpdate(sql);
            s.close();
            JOptionPane.showMessageDialog(null, "Data berhasil ditambah");
            ArifBersihField();
            ArifBacaTabelPembelian();
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void ArifEditData() {
        try {
            String sql = "Update arfrvi_pembelian Set arfrvi_pembelian.arf_kd_beli='" + arf_tkd_beli.getText() + "', arfrvi_pembelian.arf_tgl_beli='" + arf_ttgl_beli.getText() + "', arfrvi_pembelian.arf_kd_sup='" + arf_tkd_sup.getText() + "', arfrvi_pembelian.kd_brg='" + tkd_brg.getText() + "', arfrvi_pembelian.arf_jum_beli='" + arf_tjum.getText() + "', arfrvi_pembelian.arf_hrg_beli='" + arf_thrg_beli.getText() + "' Where arfrvi_pembelian.arf_kd_beli='" + arf_tkd_beli.getText() + "' ";

            s.executeUpdate(sql);
            s.close();
            JOptionPane.showMessageDialog(null, "Data berhasil diedit");
            ArifBersihField();
            ArifBacaTabelPembelian();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void ArifHapusData() {
        try {
            String sql = "Delete from arfrvi_pembelian Where arfrvi_pembelian.arf_kd_beli='" + arf_tkd_beli.getText() + "' ";
            s.executeUpdate(sql);
            s.close();
            JOptionPane.showMessageDialog(null, "Data berhasil dihapus");

            ArifBersihField();
            ArifBacaTabelPembelian();
        } catch (SQLException e) {
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

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        arf_tbl_beli = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        arf_tkd_beli = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        arf_ttgl_beli = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        arf_tkd_sup = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tkd_brg = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        arf_tjum = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        arf_thrg_beli = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        arf_bt_edit = new javax.swing.JButton();
        arf_bt_hapus = new javax.swing.JButton();
        arf_bt_keluar = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("TABEL DATA PEMBELIAN");

        arf_tbl_beli.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Kode Pembelian", "Tanggal Pembelian", "Kode Supplier", "Kode Barang", "Jumlah Pembelian", "Harga Beli"
            }
        ));
        arf_tbl_beli.setName("arf_tbl_beli"); // NOI18N
        arf_tbl_beli.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                arf_tbl_beliMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(arf_tbl_beli);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setText("INPUT DATA TRANSAKSI PEMBELIAN");

        jLabel3.setText("KODE TRANSAKSI");

        arf_tkd_beli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                arf_tkd_beliKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                arf_tkd_beliKeyReleased(evt);
            }
        });

        jLabel4.setText("TANGGAL TRANSAKSI");

        arf_ttgl_beli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                arf_ttgl_beliKeyPressed(evt);
            }
        });

        jLabel5.setText("KODE SUPPLIER");

        arf_tkd_sup.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                arf_tkd_supKeyPressed(evt);
            }
        });

        jLabel6.setText("KODE BARANG");

        tkd_brg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tkd_brgKeyPressed(evt);
            }
        });

        jLabel7.setText("JUMLAH BARANG");

        arf_tjum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                arf_tjumKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                arf_tjumKeyTyped(evt);
            }
        });

        jLabel8.setText("HARGA BELI");

        arf_thrg_beli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                arf_thrg_beliKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                arf_thrg_beliKeyTyped(evt);
            }
        });

        jButton1.setText("TAMBAH");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        arf_bt_edit.setText("EDIT");
        arf_bt_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                arf_bt_editActionPerformed(evt);
            }
        });

        arf_bt_hapus.setText("HAPUS");
        arf_bt_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                arf_bt_hapusActionPerformed(evt);
            }
        });

        arf_bt_keluar.setText("KELUAR");
        arf_bt_keluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                arf_bt_keluarActionPerformed(evt);
            }
        });

        jLabel9.setText("Rp");

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
                        .addComponent(jLabel1)
                        .addGap(219, 219, 219))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(146, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(arf_thrg_beli, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(arf_tjum, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tkd_brg, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(arf_tkd_sup, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(arf_ttgl_beli, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(100, 100, 100)
                        .addComponent(arf_tkd_beli, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(174, 174, 174))
            .addGroup(layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(jButton1)
                .addGap(92, 92, 92)
                .addComponent(arf_bt_edit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(arf_bt_hapus)
                .addGap(89, 89, 89)
                .addComponent(arf_bt_keluar)
                .addGap(59, 59, 59))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(arf_tkd_beli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(arf_ttgl_beli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(arf_tkd_sup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(tkd_brg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(arf_tjum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(arf_thrg_beli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(arf_bt_edit)
                    .addComponent(arf_bt_hapus)
                    .addComponent(arf_bt_keluar))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ArifSimpanData();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void arf_bt_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_arf_bt_editActionPerformed
        ArifEditData();
    }//GEN-LAST:event_arf_bt_editActionPerformed

    private void arf_bt_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_arf_bt_hapusActionPerformed
        ArifHapusData();
    }//GEN-LAST:event_arf_bt_hapusActionPerformed

    private void arf_bt_keluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_arf_bt_keluarActionPerformed
        int confirmed = JOptionPane.showConfirmDialog(null, "Yakin Ingin Keluar?", "Terima Kasih", JOptionPane.YES_NO_OPTION);
        if (confirmed == JOptionPane.YES_OPTION) {
            dispose();
        } else {
            setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            arf_tkd_beli.requestFocus();
        }
    }//GEN-LAST:event_arf_bt_keluarActionPerformed

    private void arf_tbl_beliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_arf_tbl_beliMouseClicked
        // TODO add your handling code here:
        ArifSetTabel();
    }//GEN-LAST:event_arf_tbl_beliMouseClicked

    private void arf_tkd_beliKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_arf_tkd_beliKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            arf_ttgl_beli.requestFocus();
        }
    }//GEN-LAST:event_arf_tkd_beliKeyPressed

    private void arf_ttgl_beliKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_arf_ttgl_beliKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            arf_tkd_sup.requestFocus();
        }
    }//GEN-LAST:event_arf_ttgl_beliKeyPressed

    private void arf_tkd_supKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_arf_tkd_supKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            tkd_brg.requestFocus();
        }
    }//GEN-LAST:event_arf_tkd_supKeyPressed

    private void tkd_brgKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tkd_brgKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            arf_tjum.requestFocus();
        }
    }//GEN-LAST:event_tkd_brgKeyPressed

    private void arf_tjumKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_arf_tjumKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            arf_thrg_beli.requestFocus();
        }
    }//GEN-LAST:event_arf_tjumKeyPressed

    private void arf_thrg_beliKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_arf_thrg_beliKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            arf_tkd_beli.requestFocus();
        }
    }//GEN-LAST:event_arf_thrg_beliKeyPressed

    private void arf_thrg_beliKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_arf_thrg_beliKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if(!(Character.isDigit(c) || (c == KeyEvent.VK_TAB)||(c == KeyEvent.VK_DELETE))){
            evt.consume();
        }
    }//GEN-LAST:event_arf_thrg_beliKeyTyped

    private void arf_tjumKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_arf_tjumKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if(!(Character.isDigit(c) || (c == KeyEvent.VK_TAB)||(c == KeyEvent.VK_DELETE))){
            evt.consume();
        }
    }//GEN-LAST:event_arf_tjumKeyTyped

    private void arf_tkd_beliKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_arf_tkd_beliKeyReleased
        // TODO add your handling code here:
        try{
            s=c.createStatement();
            r=s.executeQuery("select*from arfrvi_pembelian where arf_kd_beli like '"+arf_tkd_beli.getText()+"%'");
            int baris =0,y=0,x=0;
            ResultSetMetaData rsmd = r.getMetaData();
            y=rsmd.getColumnCount();
            System.out.println("select arf_tgl_beli from arfrvi_pembelian where arf_kd_beli like '"+arf_tkd_beli.getText()+"%'");
            while(r.next()){
                baris = r.getRow();
            }
            arfdatapembelian = new Object [baris] [y];
            r.beforeFirst();
            while(r.next()){
                arfdatapembelian[x][0] = (String)r.getString("arf_kd_beli");
                arfdatapembelian[x][1] = (String)r.getString("arf_tgl_beli");
                arfdatapembelian[x][2] = (String)r.getString("arf_kd_sup");
                arfdatapembelian[x][3] = (String)r.getString("kd_brg");
                arfdatapembelian[x][4] = (String)r.getString("arf_jum_beli");
                arfdatapembelian[x][5] = (String)r.getString("arf_hrg_beli");
                x++;
            }
            arf_tbl_beli.setModel(new DefaultTableModel(arfdatapembelian,label4));
            System.out.println("y");
        }catch (SQLException e){
            System.out.println(e);
        }
    }//GEN-LAST:event_arf_tkd_beliKeyReleased

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
            java.util.logging.Logger.getLogger(Arf_Aplikasi_Transaksi_Pembelian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Arf_Aplikasi_Transaksi_Pembelian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Arf_Aplikasi_Transaksi_Pembelian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Arf_Aplikasi_Transaksi_Pembelian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Arf_Aplikasi_Transaksi_Pembelian().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton arf_bt_edit;
    private javax.swing.JButton arf_bt_hapus;
    private javax.swing.JButton arf_bt_keluar;
    private javax.swing.JTable arf_tbl_beli;
    private javax.swing.JTextField arf_thrg_beli;
    private javax.swing.JTextField arf_tjum;
    private javax.swing.JTextField arf_tkd_beli;
    private javax.swing.JTextField arf_tkd_sup;
    private javax.swing.JTextField arf_ttgl_beli;
    private javax.swing.JButton jButton1;
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
    private javax.swing.JTextField tkd_brg;
    // End of variables declaration//GEN-END:variables
}
