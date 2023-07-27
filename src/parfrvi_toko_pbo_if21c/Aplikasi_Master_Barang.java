/*
        BAB 4
NAMA    : MUHAMMAD ARIF RIVAI
NPM     : 21312097
KELAS   : IF 21 B
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

public class Aplikasi_Master_Barang extends javax.swing.JFrame {
    
    public static int statusSearching = 0;
    Connection c;
    ResultSet r;
    Statement s;
    PreparedStatement pst =null;
    private Object[][] databarang = null;
    private String[] label={"Kode Barang","Nama Barang","Diskon","Harga Jual","Jumlah Barang","Rusak","Sisa Barang"};
            
    public Aplikasi_Master_Barang() {
        initComponents();
        BukaKoneksi();
        BacaTabelBarang();
        tkd_brg.setVisible(true);
    }
    private void BukaKoneksi(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost/db_toko_pbo_if21c","root","");
            System.out.println("Koneksi sukses");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
private void BacaTabelBarang() {
        try{
            s=c.createStatement();
            String sql="Select* From arfrvi_barang Order By kd_brg";
            r=s.executeQuery(sql);
            ResultSetMetaData m=r.getMetaData();
            int kolom=m.getColumnCount();
            int baris=0;
            while(r.next()){
              baris=r.getRow();
            }
            databarang=new Object[baris][kolom];
            int x=0;
            r.beforeFirst();
            while(r.next()){
                databarang[x][0]=r.getString("kd_brg");
                databarang[x][1]=r.getString("nm_brg");
                databarang[x][2]=r.getString("diskon");
                databarang[x][3]=r.getString("hrg_jual");
                databarang[x][4]=r.getString("jumlah");
                databarang[x][5]=r.getString("rusak");
                databarang[x][6]=r.getString("sisa");
                x++;
             }
            tbl_brg.setModel(new DefaultTableModel(databarang,label));
        }
            catch(SQLException e){
                JOptionPane.showMessageDialog(null,e);
        }

    }

 private void SimpanData(){
        try{
            String sql = "Insert Into arfrvi_barang Values (" + "\'" + tkd_brg.getText()+ "\'" +","+ "\'" +tnm_brg.getText()+ "\'" +","+tdiskon.getText()+","+thrg_jual.getText()+","+tjumlah.getText()+","+trusak.getText()+","+tsisa.getText()+")";
            s.executeUpdate(sql);
            s.close();
            JOptionPane.showMessageDialog(null, "Data berhasil ditambah");
            BersihField();
            BacaTabelBarang();
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
 private void HapusData(){

 try{

 String sql="Delete from arfrvi_barang Where kd_brg="+"\'"+tkd_brg.getText()+"\'"; 
   
    s.executeUpdate(sql);    
    s.close();
    JOptionPane.showMessageDialog(null,"Data berhasil dihapus ");
    BersihField();
    BacaTabelBarang();
 }
    catch(SQLException e){
      JOptionPane.showMessageDialog(null,e);

 }
 }
     private void BersihField(){ 
        tkd_brg.setText("");
        tnm_brg.setText("");
        thrg_jual.setText("");
        tjumlah.setText("");
        tdiskon.setText("");
        trusak.setText(""); 
        tsisa.setText("");
    }
        private void ExitWindow(){
            int confirmed = JOptionPane.showConfirmDialog
                (null, "Keluar Dari Program?","Exit",JOptionPane.YES_NO_OPTION);
            if (confirmed == JOptionPane.YES_OPTION)
            {
                    dispose();
            }else
                {
                    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                    tkd_brg.requestFocus();
                }
            }
     
        private void EditData(){
    try{
        
        String sql = "Update arfrvi_barang Set kd_brg=" + "\'" + tkd_brg.getText() + "\'" + ", nm_brg=" + "\'" + tnm_brg.getText() + "\'" + ", diskon=" + tdiskon.getText() + ", hrg_jual=" + thrg_jual.getText() + ", jumlah=" + tjumlah.getText() + ", rusak=" + trusak.getText() + ", sisa=" + tsisa.getText() + " Where kd_brg=" + "\'" + tkd_brg.getText() + "\'";
        s.executeUpdate(sql);
        s.close();
        JOptionPane.showMessageDialog(null,"Data berhasil diedit");
        BersihField();
        BacaTabelBarang();
    }catch (SQLException e){ 
     String sql = "Update arfrvi_barang Set kd_brg=" + "\'" + tkd_brg.getText() + "\'" + ", nm_brg=" + "\'" + tnm_brg.getText() + "\'" + ", diskon=" + tdiskon.getText() + ", hrg_jual=" + thrg_jual.getText() + ", jumlah=" + tjumlah.getText() + ", rusak=" + trusak.getText() + ", sisa=" + tsisa.getText() + " Where kd_brg=" + "\'" + tkd_brg.getText() + "\'";
     System.out.println(sql);
     JOptionPane.showMessageDialog(null,e);
 }

   }
        
            private void SetTabel(){
        int row=tbl_brg.getSelectedRow();
        tkd_brg.setText((String)tbl_brg.getValueAt(row,0));
        tnm_brg.setText((String)tbl_brg.getValueAt(row,1)); 
        tdiskon.setText((String)tbl_brg.getValueAt(row,2));
        thrg_jual.setText((String)tbl_brg.getValueAt(row,3));
        tjumlah.setText((String)tbl_brg.getValueAt(row,4)); 
        trusak.setText((String)tbl_brg.getValueAt(row,5));
        tsisa.setText((String)tbl_brg.getValueAt(row,6));
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
        tbl_brg = new javax.swing.JTable();
        iN = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        tkd_brg = new javax.swing.JTextField();
        tnm_brg = new javax.swing.JTextField();
        tdiskon = new javax.swing.JTextField();
        thrg_jual = new javax.swing.JTextField();
        tjumlah = new javax.swing.JTextField();
        trusak = new javax.swing.JTextField();
        tsisa = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        bt_tambah = new javax.swing.JButton();
        bt_edit = new javax.swing.JButton();
        bt_hapus = new javax.swing.JButton();
        bt_keluar = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        lenghtkd_brg = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("APLIKASI MASTER BARANG");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel1.setText("TABEL DATA BARANG");

        tbl_brg.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "KODE BARANG", "NAMA BARANG", "DISKON", "HARGA JUAL", "JUMLAH", "RUSAK", "SISA"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tbl_brg.setName("tbl_brg"); // NOI18N
        tbl_brg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_brgMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_brg);

        iN.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        iN.setText("INPUT DATA BARANG");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel2.setText("Data Barang");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel3.setText("STOK BARANG");

        jLabel4.setText("Kode Barang");

        jLabel5.setText("Nama Barang");

        jLabel6.setText("Diskon");

        jLabel7.setText("Harga Jual");

        jLabel8.setText("Jumlah Barang");

        jLabel9.setText("Rusak");

        jLabel10.setText("Sisa Barang");

        tkd_brg.setName("tkd_brg"); // NOI18N
        tkd_brg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tkd_brgMouseEntered(evt);
            }
        });
        tkd_brg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tkd_brgActionPerformed(evt);
            }
        });
        tkd_brg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tkd_brgKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tkd_brgKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tkd_brgKeyTyped(evt);
            }
        });

        tnm_brg.setText(" ");
        tnm_brg.setName("tnm_brg"); // NOI18N
        tnm_brg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tnm_brgActionPerformed(evt);
            }
        });
        tnm_brg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tnm_brgKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tnm_brgKeyTyped(evt);
            }
        });

        tdiskon.setName("tdiskon"); // NOI18N
        tdiskon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tdiskonActionPerformed(evt);
            }
        });
        tdiskon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tdiskonKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tdiskonKeyTyped(evt);
            }
        });

        thrg_jual.setName("thrg_jual"); // NOI18N
        thrg_jual.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                thrg_jualKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                thrg_jualKeyTyped(evt);
            }
        });

        tjumlah.setName("tjumlah"); // NOI18N
        tjumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tjumlahKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tjumlahKeyTyped(evt);
            }
        });

        trusak.setName("trusak"); // NOI18N
        trusak.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                trusakKeyPressed(evt);
            }
        });

        tsisa.setName("tsisa"); // NOI18N
        tsisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tsisaKeyPressed(evt);
            }
        });

        jLabel11.setText("Rp");

        jLabel12.setText("%");

        bt_tambah.setText("Tambah");
        bt_tambah.setName("bt_tambah"); // NOI18N
        bt_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_tambahActionPerformed(evt);
            }
        });

        bt_edit.setText("Edit");
        bt_edit.setName("bt_edit"); // NOI18N
        bt_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_editActionPerformed(evt);
            }
        });

        bt_hapus.setText("Hapus");
        bt_hapus.setName("bt_hapus"); // NOI18N
        bt_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_hapusActionPerformed(evt);
            }
        });

        bt_keluar.setText("Keluar");
        bt_keluar.setName("bt_keluar"); // NOI18N
        bt_keluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_keluarActionPerformed(evt);
            }
        });

        lenghtkd_brg.setName("lenghtkd_brg"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(105, 105, 105)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(bt_tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(106, 106, 106)
                                        .addComponent(bt_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(81, 81, 81)
                                        .addComponent(bt_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(76, 76, 76)
                                        .addComponent(bt_keluar, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(295, 295, 295)
                                        .addComponent(iN))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(80, 80, 80)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel11)
                                        .addGap(18, 18, 18)
                                        .addComponent(thrg_jual, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(81, 81, 81)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel5)
                                                    .addComponent(jLabel6))
                                                .addGap(53, 53, 53)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel2)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                            .addComponent(tdiskon)
                                                            .addComponent(tnm_brg, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(lenghtkd_brg)
                                                            .addComponent(jLabel12)))))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel4)
                                                .addGap(56, 56, 56)
                                                .addComponent(tkd_brg, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel13)))))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(154, 154, 154)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel9)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel10))
                                        .addGap(33, 33, 33)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(tjumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(trusak, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(tsisa, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel3)
                                        .addGap(45, 45, 45)))))
                        .addGap(0, 50, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(337, 337, 337))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(iN)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(tjumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(trusak, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tsisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(tkd_brg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addComponent(lenghtkd_brg))
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tnm_brg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(tdiskon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(thrg_jual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))))
                .addGap(67, 67, 67)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_keluar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tkd_brgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tkd_brgActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tkd_brgActionPerformed

    private void tdiskonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tdiskonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tdiskonActionPerformed

    private void bt_keluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_keluarActionPerformed
// TODO add your handling code here:
    int confirmed = JOptionPane.showConfirmDialog
        (null, "Yakin Ingin Keluar?", "Terima Kasih", JOptionPane.YES_NO_OPTION);
    if (confirmed == JOptionPane.YES_OPTION) 
    {
                dispose();
    }
    else
    {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        tkd_brg.requestFocus();
        }
    }//GEN-LAST:event_bt_keluarActionPerformed

    private void bt_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_tambahActionPerformed
        // TODO add your handling code here:
        SimpanData();
        if (thrg_jual.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Kolom Tidak Boleh Kosong!!", "Peringatan", JOptionPane.WARNING_MESSAGE);
        } else if (tjumlah.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Kolom Jumlah Tidak Boleh Kosong!!", "Peringatan", JOptionPane.WARNING_MESSAGE);
        } else if (trusak.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Kolom Rusak Tidak Boleh Kosong!!", "Peringatan", JOptionPane.WARNING_MESSAGE);
        } else if (tsisa.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Kolom Sisa Besar Tidak Boleh Kosong!!", "Peringatan", JOptionPane.WARNING_MESSAGE);
        } else if (tdiskon.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Kolom Diskon Kecil Tidak Boleh Kosong!!", "Peringatan", JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Data Berhasil Di Simpan!!");
        }  
    }//GEN-LAST:event_bt_tambahActionPerformed
    public void JumlahKarakter(KeyEvent e) {
        if (tkd_brg.getText().length() == 10) {
            e.consume();
            JOptionPane.showMessageDialog(null, "Maksimal yang dimasukan Hanya 10 Karakter", "Peringatan", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void JumlahKarakterNama(KeyEvent evt) {
        if (tnm_brg.getText().length() == 20) {
            JOptionPane.showMessageDialog(null, "Maksimal yang dimasukan Hanya 20 Karakter", "Peringatan", JOptionPane.WARNING_MESSAGE);
        }
    }
    private void tnm_brgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tnm_brgActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tnm_brgActionPerformed

    private void bt_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_editActionPerformed
// TODO add your handling code here:
        EditData();
    }//GEN-LAST:event_bt_editActionPerformed

    private void bt_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_hapusActionPerformed
        // TODO add your handling code here:
        HapusData();
    }//GEN-LAST:event_bt_hapusActionPerformed

    private void tbl_brgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_brgMouseClicked
        // TODO add your handling code here:
        SetTabel();
    }//GEN-LAST:event_tbl_brgMouseClicked

    private void tkd_brgMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tkd_brgMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tkd_brgMouseEntered

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
    // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing

    private void tjumlahKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tjumlahKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tjumlahKeyTyped

    private void tkd_brgKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tkd_brgKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            tnm_brg.requestFocus();
        }
    }//GEN-LAST:event_tkd_brgKeyPressed

    private void tkd_brgKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tkd_brgKeyReleased
        try{
            s=c.createStatement();
            r=s.executeQuery("select*from arfrvi_barang where kd_brg like '"+tkd_brg.getText()+"%'");
            int baris =0,y=0,x=0;
            ResultSetMetaData rsmd = r.getMetaData();
            y=rsmd.getColumnCount();
            System.out.println("select nm_brg from arfrvi_barang where kd_brg like '"+tkd_brg.getText()+"%'");
            while(r.next()){
                baris = r.getRow();
            }
            databarang = new Object [baris] [y];
            r.beforeFirst();
            while(r.next()){
                databarang[x][0] = (String)r.getString("kd_brg");
                databarang[x][1] = (String)r.getString("nm_brg");
                databarang[x][2] = (String)r.getString("diskon");
                databarang[x][3] = (String)r.getString("hrg_jual");
                databarang[x][4] = (String)r.getString("jumlah");
                databarang[x][5] = (String)r.getString("rusak");
                databarang[x][6] = (String)r.getString("sisa");
                x++;
            }
            tbl_brg.setModel(new DefaultTableModel(databarang,label));
            System.out.println("y");
        }catch (SQLException e){
            System.out.println(e);
        }
        
    }//GEN-LAST:event_tkd_brgKeyReleased

    private void tkd_brgKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tkd_brgKeyTyped
        // TODO add your handling code here:
        JumlahKarakter(evt);
    }//GEN-LAST:event_tkd_brgKeyTyped

    private void tnm_brgKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tnm_brgKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            tdiskon.requestFocus();
        }
    }//GEN-LAST:event_tnm_brgKeyPressed

    private void tnm_brgKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tnm_brgKeyTyped
        // TODO add your handling code here:
        JumlahKarakterNama(evt);
    }//GEN-LAST:event_tnm_brgKeyTyped

    private void tdiskonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tdiskonKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            thrg_jual.requestFocus();
        }
    }//GEN-LAST:event_tdiskonKeyPressed

    private void thrg_jualKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_thrg_jualKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            tjumlah.requestFocus();
        }
    }//GEN-LAST:event_thrg_jualKeyPressed

    private void tjumlahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tjumlahKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            trusak.requestFocus();
        }
    }//GEN-LAST:event_tjumlahKeyPressed

    private void trusakKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_trusakKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            tsisa.requestFocus();
        }
    }//GEN-LAST:event_trusakKeyPressed

    private void tsisaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tsisaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            bt_tambah.requestFocus();
        }
    }//GEN-LAST:event_tsisaKeyPressed

    private void tdiskonKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tdiskonKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if(!(Character.isDigit(c) || (c == KeyEvent.VK_TAB)||(c == KeyEvent.VK_DELETE))){
            evt.consume();
        }
    }//GEN-LAST:event_tdiskonKeyTyped

    private void thrg_jualKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_thrg_jualKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if(!(Character.isDigit(c) || (c == KeyEvent.VK_TAB)||(c == KeyEvent.VK_DELETE))){
            evt.consume();
        }
    }//GEN-LAST:event_thrg_jualKeyTyped

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
            java.util.logging.Logger.getLogger(Aplikasi_Master_Barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Aplikasi_Master_Barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Aplikasi_Master_Barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Aplikasi_Master_Barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Aplikasi_Master_Barang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_edit;
    private javax.swing.JButton bt_hapus;
    private javax.swing.JButton bt_keluar;
    private javax.swing.JButton bt_tambah;
    private javax.swing.JLabel iN;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lenghtkd_brg;
    private javax.swing.JTable tbl_brg;
    private javax.swing.JTextField tdiskon;
    private javax.swing.JTextField thrg_jual;
    private javax.swing.JTextField tjumlah;
    private javax.swing.JTextField tkd_brg;
    private javax.swing.JTextField tnm_brg;
    private javax.swing.JTextField trusak;
    private javax.swing.JTextField tsisa;
    // End of variables declaration//GEN-END:variables

}