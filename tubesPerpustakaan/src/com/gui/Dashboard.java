package com.gui;

import java.util.List;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.GroupLayout.*;
import javax.swing.LayoutStyle.*;
import javax.swing.table.*;

import java.sql.SQLException;
import com.mysql.cj.jdbc.exceptions.CommunicationsException;
import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.toedter.calendar.*;
import com.dao.*;
import com.entities.*;

public class Dashboard extends MyFrame{

    private JButton bSimpan, bTambah, bPerpanjang, bKembali, bHapus;
    private JLabel  lVideo, lfJumlah, lMonograf, lPenyalinan, lPeminjaman, 
                    lJudulPage, lfJudul, lfKreator, lfTahun, lfISBN, lfDurasi, lfPublik,
                    lfIDKoleksi, lfNoMember, lfTglPinjam, lfTglSalin;
    private JPanel jPanel1, jPanel2;
    private JScrollPane jScrollPane1;
    private JTable tMonograf, tVideo, tPenyalinan, tPeminjaman;
    private JTextField tfJudul, tfKreator, tfTahun, tfISBN, tfJumlah, tfDurasi, tfIdKoleksi, tfNoMember;
    private JComboBox<String> cbPublik;
    private JDateChooser dcTglPinjam, dcTglSalin;
    private MonografDAO mDAO = new MonografDAO();
    private VideoDAO vDAO = new VideoDAO();
    private PeminjamanDAO pjDAO = new PeminjamanDAO();
    private PenyalinanDAO pyDAO = new PenyalinanDAO();
    private MemberDAO memDAO = new MemberDAO();
    
    Dashboard() throws SQLException{
        super();
        pageMonograf();
        pack();
        setLocationRelativeTo(null);
    }

    public JPanel navbar(){
        jPanel2 = new JPanel();
        jPanel2.setBackground(new Color(3, 32, 57));
        jPanel2.setMinimumSize(new Dimension(900, 60));

        lVideo = new JLabel();
        lVideo.setFont(new Font("Montserrat", 0, 14)); // NOI18N
        lVideo.setForeground(new Color(252, 253, 255));
        lVideo.setText("Koleksi Video");
        lVideo.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent evt) {
                lVideoMouseClicked(evt);
            }
            private void lVideoMouseClicked(MouseEvent evt) {
                try {
                    remove(jPanel1);
                    pageVideo();
                    lMonograf.setFont(new Font("Montserrat", 0, 14));
                    lVideo.setFont(new Font("Montserrat", 1, 14));
                    lPeminjaman.setFont(new Font("Montserrat", 0, 14));
                    lPenyalinan.setFont(new Font("Montserrat", 0, 14));
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        });
        
        lMonograf = new JLabel();
        lMonograf.setFont(new Font("Montserrat", 1, 14)); // NOI18N
        lMonograf.setForeground(new Color(252, 253, 255));
        lMonograf.setText("Koleksi Monograf");
        lMonograf.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent evt) {
                lMonografMouseClicked(evt);
            }
            private void lMonografMouseClicked(MouseEvent evt) {
                try {
                    remove(jPanel1);
                    pageMonograf();
                    lMonograf.setFont(new Font("Montserrat", 1, 14));
                    lVideo.setFont(new Font("Montserrat", 0, 14));
                    lPeminjaman.setFont(new Font("Montserrat", 0, 14));
                    lPenyalinan.setFont(new Font("Montserrat", 0, 14));
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        });

        lPenyalinan = new JLabel();
        lPenyalinan.setFont(new Font("Montserrat", 0, 14)); // NOI18N
        lPenyalinan.setForeground(new Color(252, 253, 255));
        lPenyalinan.setText("Penyalinan");
        lPenyalinan.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent evt) {
                lPenyalinanMouseClicked(evt);
            }
            private void lPenyalinanMouseClicked(MouseEvent evt) {
                try {
                    remove(jPanel1);
                    pagePenyalinan();
                    lMonograf.setFont(new Font("Montserrat", 0, 14));
                    lVideo.setFont(new Font("Montserrat", 0, 14));
                    lPeminjaman.setFont(new Font("Montserrat", 0, 14));
                    lPenyalinan.setFont(new Font("Montserrat", 1, 14));
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        });

        lPeminjaman = new JLabel();
        lPeminjaman.setFont(new Font("Montserrat", 0, 14)); // NOI18N
        lPeminjaman.setForeground(new Color(252, 253, 255));
        lPeminjaman.setText("Peminjaman");
        lPeminjaman.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent evt) {
                lPeminjamanMouseClicked(evt);
            }
            private void lPeminjamanMouseClicked(MouseEvent evt) {
                try {
                    remove(jPanel1);
                    pagePeminjaman();
                    lMonograf.setFont(new Font("Montserrat", 0, 14));
                    lVideo.setFont(new Font("Montserrat", 0, 14));
                    lPeminjaman.setFont(new Font("Montserrat", 1, 14));
                    lPenyalinan.setFont(new Font("Montserrat", 0, 14));
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        });

        GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(lMonograf)
                .addGap(18, 18, 18)
                .addComponent(lPeminjaman)
                .addGap(77, 77, 77)
                .addComponent(lVideo)
                .addGap(18, 18, 18)
                .addComponent(lPenyalinan)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(Alignment.LEADING)
            .addGroup(Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(lVideo)
                    .addComponent(lMonograf)
                    .addComponent(lPenyalinan)
                    .addComponent(lPeminjaman))
                .addGap(20, 20, 20))
        );

        return jPanel2;
    }

    public void pageMonograf() throws SQLException{
        jPanel2 = navbar();

        jPanel1 = new JPanel();
        jPanel1.setBackground(new Color(252, 253, 255));
        jPanel1.setMinimumSize(new Dimension(900, 500));

        lJudulPage = new JLabel();
        lJudulPage.setFont(new Font("Montserrat", 1, 24)); // NOI18N
        lJudulPage.setForeground(new Color(3, 23, 57));
        lJudulPage.setText("Koleksi Monograf");

        tfJudul = new JTextField();
        tfJudul.setBorder(BorderFactory.createCompoundBorder(
            tfJudul.getBorder(),
            BorderFactory.createEmptyBorder(3, 3, 3, 3)
        ));

        tfKreator = new JTextField();
        tfKreator.setBorder(BorderFactory.createCompoundBorder(
            tfKreator.getBorder(),
            BorderFactory.createEmptyBorder(3, 3, 3, 3)
        ));

        tfTahun = new JTextField();
        tfTahun.setBorder(BorderFactory.createCompoundBorder(
            tfTahun.getBorder(),
            BorderFactory.createEmptyBorder(3, 3, 3, 3)
        ));

        tfJumlah = new JTextField();
        tfJumlah.setBorder(BorderFactory.createCompoundBorder(
            tfJumlah.getBorder(),
            BorderFactory.createEmptyBorder(3, 3, 3, 3)
        ));

        tfISBN = new JTextField();
        tfISBN.setBorder(BorderFactory.createCompoundBorder(
            tfISBN.getBorder(),
            BorderFactory.createEmptyBorder(3, 3, 3, 3)
        ));

        lfJudul = new JLabel();
        lfJudul.setFont(new Font("Montserrat", 1, 12)); // NOI18N
        lfJudul.setHorizontalAlignment(SwingConstants.RIGHT);
        lfJudul.setText("Judul");

        lfKreator = new JLabel();
        lfKreator.setFont(new Font("Montserrat", 1, 12)); // NOI18N
        lfKreator.setHorizontalAlignment(SwingConstants.RIGHT);
        lfKreator.setText("Kreator");

        lfTahun = new JLabel();
        lfTahun.setFont(new Font("Montserrat", 1, 12)); // NOI18N
        lfTahun.setHorizontalAlignment(SwingConstants.RIGHT);
        lfTahun.setText("Tahun");

        lfISBN = new JLabel();
        lfISBN.setFont(new Font("Montserrat", 1, 12)); // NOI18N
        lfISBN.setHorizontalAlignment(SwingConstants.RIGHT);
        lfISBN.setText("ISBN");

        lfJumlah = new JLabel();
        lfJumlah.setFont(new Font("Montserrat", 1, 12)); // NOI18N
        lfJumlah.setHorizontalAlignment(SwingConstants.RIGHT);
        lfJumlah.setText("Jumlah");

        tMonograf = new JTable();
        tMonograf.setFont(new Font("Arial", 0, 13)); // NOI18N
        tMonograf.setBackground(new Color(252, 253, 255));
        tMonograf.setGridColor(new Color(211, 211, 211));
        tMonograf.setRowHeight(20);
        tMonograf.getTableHeader().setOpaque(false);
        tMonograf.getTableHeader().setBackground(new Color(245, 245, 245));
        tMonograf.getTableHeader().setFont(new Font("Arial", 1, 14));

        tMonograf.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tMonograf.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent evt) {
                tMonografMouseClicked(evt);
            }
            private void tMonografMouseClicked(MouseEvent evt) {
                tfJudul.setText(tMonograf.getValueAt(tMonograf.getSelectedRow(), 1).toString());
                tfKreator.setText(tMonograf.getValueAt(tMonograf.getSelectedRow(), 2).toString());
                tfISBN.setText(tMonograf.getValueAt(tMonograf.getSelectedRow(), 4).toString());
                tfTahun.setText(tMonograf.getValueAt(tMonograf.getSelectedRow(), 3).toString());
                tfJumlah.setText(tMonograf.getValueAt(tMonograf.getSelectedRow(), 5).toString());
            }
        });
        tMonograf.setModel(new DefaultTableModel(null, new String[]{"ID", "Judul", "Kreator", "Tahun", "ISBN", "Jumlah"}){
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false};
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }});

        jScrollPane1 = new JScrollPane();
        jScrollPane1.setViewportView(tMonograf);
        if (tMonograf.getColumnModel().getColumnCount() > 0) {
            tMonograf.getColumnModel().getColumn(0).setPreferredWidth(80);
            tMonograf.getColumnModel().getColumn(1).setPreferredWidth(250);
            tMonograf.getColumnModel().getColumn(2).setPreferredWidth(170);
            tMonograf.getColumnModel().getColumn(3).setPreferredWidth(80);
            tMonograf.getColumnModel().getColumn(4).setPreferredWidth(130);
            tMonograf.getColumnModel().getColumn(5).setPreferredWidth(80);
        }

        readMonograf();

        bSimpan = new JButton();
        bSimpan.setBackground(new Color(8, 118, 144));
        bSimpan.setFont(new Font("Montserrat", 1, 12)); // NOI18N
        bSimpan.setForeground(new Color(252, 253, 255));
        bSimpan.setText("Simpan");
        bSimpan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    if(!(tfJudul.getText().isEmpty()||tfKreator.getText().isEmpty()||tfTahun.getText().isEmpty()||tfISBN.getText().isEmpty()||tfJumlah.getText().isEmpty())){
                        bSimpanActionPerformed(evt);
                    } else{
                        JOptionPane.showMessageDialog(null, "Gagal memperbarui data. Lengkapi semua kolom input.");
                    }
                } catch (CommunicationsException e) {
                    JOptionPane.showMessageDialog(null, "Tidak terkoneksi dengan database. Gagal memperbarui data.");
                } catch (MysqlDataTruncation e1){
                    JOptionPane.showMessageDialog(null, "Gagal memperbarui data.\nInput Tahun tidak valid. Input angka antara 0-99 atau 1901-2155");
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                } catch (NumberFormatException e){
                    JOptionPane.showMessageDialog(null, "Gagal memperbarui data. Inputkan data pada kolom Tahun dan Jumlah dengan angka."); 
                }
                tfJudul.setText("");
                tfKreator.setText("");
                tfISBN.setText("");
                tfTahun.setText("");
                tfJumlah.setText("");
            }
            private void bSimpanActionPerformed(ActionEvent evt) throws SQLException {
                int row = tMonograf.getSelectedRow();
                int mID = 0;
                if(row!=-1){
                    mID = Integer.valueOf(tMonograf.getValueAt(row, 0).toString());
                }
                String mJudul = tfJudul.getText();
                String mKreator = tfKreator.getText();
                String mISBN = tfISBN.getText();
                int mTahun = Integer.valueOf(tfTahun.getText());
                int mJumlah = Integer.valueOf(tfJumlah.getText()); 
                int affectedRow = mDAO.update(new Monograf(mISBN, mJumlah, mID, mJudul, mKreator, mTahun));
                if(affectedRow!=0){
                    JOptionPane.showMessageDialog(null, "Koleksi monograf berhasil diperbarui.");
                }
                readMonograf();
            }
        });

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
                    .addComponent(lJudulPage)
                    .addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING)
                        .addGroup(Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING)
                                .addComponent(lfJudul)
                                .addComponent(lfKreator)
                                .addComponent(lfISBN))
                            .addPreferredGap(ComponentPlacement.UNRELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING, false)
                                .addComponent(tfJudul, GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                                .addComponent(tfKreator)
                                .addComponent(tfISBN))
                            .addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(34, 34, 34)
                                    .addComponent(lfTahun)
                                    .addPreferredGap(ComponentPlacement.UNRELATED)
                                    .addComponent(tfTahun, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE))
                                .addGroup(Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addGap(28, 28, 28)
                                    .addComponent(lfJumlah)
                                    .addPreferredGap(ComponentPlacement.UNRELATED)
                                    .addComponent(tfJumlah, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)))
                            .addGap(63, 63, 63)
                            .addComponent(bSimpan))
                        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 843, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(31, Short.MAX_VALUE))
            .addComponent(jPanel2, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(lJudulPage)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(Alignment.CENTER)
                    .addComponent(lfJudul)
                    .addComponent(tfJudul, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lfTahun)
                        .addComponent(tfTahun, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(bSimpan)))
                .addPreferredGap(ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(lfKreator)
                    .addComponent(tfKreator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lfJumlah)
                    .addComponent(tfJumlah, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(tfISBN, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lfISBN))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    public void readMonograf() throws SQLException{
        DefaultTableModel tb = (DefaultTableModel) tMonograf.getModel();
        tb.setRowCount(0);
        List<Monograf> list = mDAO.getAll();
        while(!list.isEmpty()){
            Object[] data = {
                list.get(0).getId(),
                list.get(0).getJudul(),
                list.get(0).getKreator(),
                list.get(0).getTahun(),
                list.get(0).getIsbn(),
                list.get(0).getJumlah(),
            };
            tb.addRow(data);
            list.remove(0);
        }
    }

    public void pageVideo() throws SQLException{
        jPanel2 = navbar();

        jPanel1 = new JPanel();
        jPanel1.setBackground(new Color(252, 253, 255));
        jPanel1.setMinimumSize(new Dimension(900, 500));
        
        lJudulPage = new JLabel();
        lJudulPage.setFont(new Font("Montserrat", 1, 24)); // NOI18N
        lJudulPage.setForeground(new Color(3, 23, 57));
        lJudulPage.setText("Koleksi Video");
        
        tfJudul = new JTextField();
        tfJudul.setBorder(BorderFactory.createCompoundBorder(
            tfJudul.getBorder(),
            BorderFactory.createEmptyBorder(3, 3, 3, 3)
        ));

        tfKreator = new JTextField();
        tfKreator.setBorder(BorderFactory.createCompoundBorder(
            tfKreator.getBorder(),
            BorderFactory.createEmptyBorder(3, 3, 3, 3)
        ));

        tfTahun = new JTextField();
        tfTahun.setBorder(BorderFactory.createCompoundBorder(
            tfTahun.getBorder(),
            BorderFactory.createEmptyBorder(3, 3, 3, 3)
        ));

        tfDurasi = new JTextField();
        tfDurasi.setBorder(BorderFactory.createCompoundBorder(
            tfDurasi.getBorder(),
            BorderFactory.createEmptyBorder(3, 3, 3, 3)
        ));


        lfJudul = new JLabel();
        lfJudul.setFont(new Font("Montserrat", 1, 12)); // NOI18N
        lfJudul.setHorizontalAlignment(SwingConstants.RIGHT);
        lfJudul.setText("Judul");

        lfKreator = new JLabel();
        lfKreator.setFont(new Font("Montserrat", 1, 12)); // NOI18N
        lfKreator.setHorizontalAlignment(SwingConstants.RIGHT);
        lfKreator.setText("Kreator");

        lfTahun = new JLabel();
        lfTahun.setFont(new Font("Montserrat", 1, 12)); // NOI18N
        lfTahun.setHorizontalAlignment(SwingConstants.RIGHT);
        lfTahun.setText("Tahun");

        lfDurasi = new JLabel();
        lfDurasi.setFont(new Font("Montserrat", 1, 12)); // NOI18N
        lfDurasi.setHorizontalAlignment(SwingConstants.RIGHT);
        lfDurasi.setText("Durasi");

        lfPublik = new JLabel();
        lfPublik.setFont(new Font("Montserrat", 1, 12)); // NOI18N
        lfPublik.setHorizontalAlignment(SwingConstants.RIGHT);
        lfPublik.setText("Publik");

        cbPublik = new JComboBox<>();
        cbPublik.setModel(new DefaultComboBoxModel<>(new String[] { "Ya", "Tidak" }));
        cbPublik.setBackground(Color.WHITE);
        cbPublik.setBorder(BorderFactory.createCompoundBorder(
            cbPublik.getBorder(),
            BorderFactory.createEmptyBorder(0, 3, 0, 3)
        ));

        tVideo = new JTable();
        tVideo.setFont(new Font("Arial", 0, 13)); // NOI18N
        tVideo.setBackground(new Color(252, 253, 255));
        tVideo.setGridColor(new Color(211, 211, 211));
        tVideo.setRowHeight(20);
        tVideo.getTableHeader().setOpaque(false);
        tVideo.getTableHeader().setBackground(new Color(245, 245, 245));
        tVideo.getTableHeader().setFont(new Font("Arial", 1, 14));

        tVideo.setModel(new DefaultTableModel(null,
            new String [] {
                "ID", "Judul", "Kreator", "Tahun", "Durasi (Menit)", "Publik"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tVideo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tVideo.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                tVideoMouseClicked(evt);
            }
            private void tVideoMouseClicked(MouseEvent evt) {
                int row = tVideo.getSelectedRow();
                tfJudul.setText(tVideo.getValueAt(row, 1).toString());
                tfKreator.setText(tVideo.getValueAt(row, 2).toString());
                tfTahun.setText(tVideo.getValueAt(row, 3).toString());
                tfDurasi.setText(tVideo.getValueAt(row, 4).toString());
                if(tVideo.getValueAt(row, 5).toString().equals("Ya")){
                    cbPublik.setSelectedItem("Ya");
                } else{
                    cbPublik.setSelectedItem("Tidak");
                }
            }
        });
        
        jScrollPane1 = new JScrollPane();
        jScrollPane1.setViewportView(tVideo);
        if (tVideo.getColumnModel().getColumnCount() > 0) {
            tVideo.getColumnModel().getColumn(0).setPreferredWidth(80);
            tVideo.getColumnModel().getColumn(1).setPreferredWidth(250);
            tVideo.getColumnModel().getColumn(2).setPreferredWidth(170);
            tVideo.getColumnModel().getColumn(3).setPreferredWidth(80);
            tVideo.getColumnModel().getColumn(4).setPreferredWidth(130);
            tVideo.getColumnModel().getColumn(5).setPreferredWidth(80);
        }

        readVideo();

        bSimpan = new JButton();
        bSimpan.setBackground(new Color(8, 118, 144));
        bSimpan.setFont(new Font("Montserrat", 1, 12)); // NOI18N
        bSimpan.setForeground(new Color(252, 253, 255));
        bSimpan.setText("Simpan");
        bSimpan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    if(!(tfJudul.getText().isEmpty()||tfKreator.getText().isEmpty()||tfTahun.getText().isEmpty()||tfDurasi.getText().isEmpty())){
                        bSimpanActionPerformed(evt);
                    } else{
                        JOptionPane.showMessageDialog(null, "Gagal memperbarui data. Lengkapi semua kolom input.");
                    }
                } catch (CommunicationsException e){
                    JOptionPane.showMessageDialog(null, "Tidak terkoneksi dengan database. Gagal memperbarui data.");
                } catch (MysqlDataTruncation e1){
                    JOptionPane.showMessageDialog(null, "Gagal memperbarui data.\nInput Tahun tidak valid. Input angka antara 0-99 atau 1901-2155");
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                } catch (NumberFormatException e){
                    JOptionPane.showMessageDialog(null, "Gagal memperbarui data. Inputkan data pada kolom Tahun dan Durasi dengan angka."); 
                }
                tfJudul.setText("");
                tfKreator.setText("");
                tfDurasi.setText("");
                tfTahun.setText("");
                cbPublik.setSelectedItem("Ya");
            }
            private void bSimpanActionPerformed(ActionEvent evt) throws SQLException {
                int row = tVideo.getSelectedRow();
                int vID = 0;
                if(row!=-1){
                    vID = Integer.valueOf(tVideo.getValueAt(row, 0).toString());
                }
                String vJudul = tfJudul.getText();
                String vKreator = tfKreator.getText();
                int vTahun = Integer.valueOf(tfTahun.getText());
                int vDurasi = Integer.valueOf(tfDurasi.getText());
                String vPublik = cbPublik.getSelectedItem().toString();
                Boolean isPublik;
                if(vPublik.equals("Ya")){
                    isPublik = true;
                } else{
                    isPublik = false;
                }
                int affectedRow = vDAO.update(new Video(vDurasi, isPublik, vID, vJudul, vKreator, vTahun));
                if(affectedRow!=0){
                    JOptionPane.showMessageDialog(null, "Koleksi video berhasil diperbarui.");
                }
                readVideo();
            }
        });

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
                    .addComponent(lJudulPage)
                    .addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING)
                        .addGroup(Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING)
                                .addComponent(lfJudul)
                                .addComponent(lfKreator)
                                .addComponent(lfDurasi))
                            .addPreferredGap(ComponentPlacement.UNRELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING, false)
                                .addComponent(tfJudul, GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                                .addComponent(tfKreator)
                                .addComponent(tfDurasi, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
                                .addGroup(Alignment.CENTER, jPanel1Layout.createSequentialGroup()
                                    .addGap(28, 28, 28)
                                    .addComponent(lfPublik)
                                    .addPreferredGap(ComponentPlacement.UNRELATED)
                                    .addComponent(cbPublik, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addGap(68, 68, 68))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(34, 34, 34)
                                    .addComponent(lfTahun)
                                    .addPreferredGap(ComponentPlacement.UNRELATED)
                                    .addComponent(tfTahun, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
                                    .addGap(63, 63, 63)))
                            .addComponent(bSimpan))
                        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 843, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(31, Short.MAX_VALUE))
            .addComponent(jPanel2, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(lJudulPage)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(Alignment.CENTER)
                    .addComponent(lfJudul)
                    .addComponent(tfJudul, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lfTahun)
                        .addComponent(tfTahun, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(bSimpan)))
                .addPreferredGap(ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(lfKreator)
                    .addComponent(tfKreator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lfPublik)
                    .addComponent(cbPublik, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(tfDurasi, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lfDurasi))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    public void readVideo() throws SQLException{
        DefaultTableModel tb = (DefaultTableModel) tVideo.getModel();
        tb.setRowCount(0);
        List<Video> list = vDAO.getAll();
        while(!list.isEmpty()){
            String isPublik;
            if(list.get(0).isPublik()){
                isPublik = "Ya";
            }else{
                isPublik = "Tidak";
            }
            Object[] data = {
                list.get(0).getId(),
                list.get(0).getJudul(),
                list.get(0).getKreator(),
                list.get(0).getTahun(),
                list.get(0).getDurasi(),
                isPublik
            };
            tb.addRow(data);
            list.remove(0);
        }
    }

    public void pagePeminjaman() throws SQLException{
        jPanel1 = new JPanel();
        jPanel1.setBackground(new Color(252, 253, 255));
        jPanel1.setMinimumSize(new Dimension(900, 500));

        jPanel2 = navbar();

        lJudulPage = new JLabel();
        lJudulPage.setFont(new Font("Montserrat", 1, 24)); // NOI18N
        lJudulPage.setForeground(new Color(3, 23, 57));
        lJudulPage.setText("Peminjaman");

        tPeminjaman = new JTable();
        tPeminjaman.setFont(new Font("Arial", 0, 13)); // NOI18N
        tPeminjaman.setBackground(new Color(252, 253, 255));
        tPeminjaman.setGridColor(new Color(211, 211, 211));
        tPeminjaman.setRowHeight(20);
        tPeminjaman.getTableHeader().setOpaque(false);
        tPeminjaman.getTableHeader().setBackground(new Color(245, 245, 245));
        tPeminjaman.getTableHeader().setFont(new Font("Arial", 1, 14));

        tPeminjaman.setModel(new DefaultTableModel(
            null,
            new String [] {
                "NO", "ID Koleksi", "No Member", "Tanggal Pinjam", "Tenggat", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });

        tPeminjaman.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tPeminjaman.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                tPeminjamanMouseClicked(evt);
            }
            private void tPeminjamanMouseClicked(MouseEvent evt) {
                bPerpanjang.setEnabled(!tPeminjaman.getValueAt(tPeminjaman.getSelectedRow(), 5).toString().equals("Kembali"));
                bKembali.setEnabled(!tPeminjaman.getValueAt(tPeminjaman.getSelectedRow(), 5).toString().equals("Kembali"));
                bHapus.setEnabled(!(tPeminjaman.getSelectedRow()==-1));
            }
        });

        jScrollPane1 = new JScrollPane();
        jScrollPane1.setViewportView(tPeminjaman);
        if (tPeminjaman.getColumnModel().getColumnCount() > 0) {
            tPeminjaman.getColumnModel().getColumn(0).setPreferredWidth(80);
            tPeminjaman.getColumnModel().getColumn(1).setPreferredWidth(100);
            tPeminjaman.getColumnModel().getColumn(2).setPreferredWidth(100);
            tPeminjaman.getColumnModel().getColumn(3).setPreferredWidth(140);
            tPeminjaman.getColumnModel().getColumn(4).setPreferredWidth(140);
            tPeminjaman.getColumnModel().getColumn(5).setPreferredWidth(120);
        }

        readPeminjaman();

        tfIdKoleksi = new JTextField();
        tfIdKoleksi.setBorder(BorderFactory.createCompoundBorder(
            tfIdKoleksi.getBorder(),
            BorderFactory.createEmptyBorder(3, 3, 3, 3)
        ));

        tfNoMember = new JTextField();
        tfNoMember.setBorder(BorderFactory.createCompoundBorder(
            tfNoMember.getBorder(),
            BorderFactory.createEmptyBorder(3, 3, 3, 3)
        ));

        dcTglPinjam = new JDateChooser();
        dcTglPinjam.setDateFormatString("dd MMM yyyy");

        lfIDKoleksi = new JLabel();
        lfIDKoleksi.setFont(new Font("Montserrat", 1, 12)); // NOI18N
        lfIDKoleksi.setHorizontalAlignment(SwingConstants.RIGHT);
        lfIDKoleksi.setText("ID Koleksi");

        lfNoMember = new JLabel();
        lfNoMember.setFont(new Font("Montserrat", 1, 12)); // NOI18N
        lfNoMember.setHorizontalAlignment(SwingConstants.RIGHT);
        lfNoMember.setText("No Member");

        lfTglPinjam = new JLabel();
        lfTglPinjam.setFont(new Font("Montserrat", 1, 12)); // NOI18N
        lfTglPinjam.setHorizontalAlignment(SwingConstants.RIGHT);
        lfTglPinjam.setText("Tgl Pinjam");

        bTambah = new JButton();
        bTambah.setBackground(new Color(8, 118, 144));
        bTambah.setFont(new Font("Montserrat", 1, 12)); // NOI18N
        bTambah.setForeground(new Color(252, 253, 255));
        bTambah.setText("Tambah");
        bTambah.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    if(!(tfIdKoleksi.getText().isEmpty()||tfNoMember.getText().isEmpty())){
                        if(dcTglPinjam.getDate()!=null){
                            bTambahActionPerformed(evt);
                        } else{
                            JOptionPane.showMessageDialog(null, "Gagal memperbarui data. Inputkan tanggal sesuai format.");
                        }
                    } else{
                        JOptionPane.showMessageDialog(null, "Gagal memperbarui data. Lengkapi semua kolom input.");
                    }
                } catch (CommunicationsException e){
                    JOptionPane.showMessageDialog(null, "Tidak terkoneksi dengan database. Gagal memperbarui data.");
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                } catch (NumberFormatException e){
                    JOptionPane.showMessageDialog(null, "Gagal memperbarui data. Inputkan data pada kolom ID Koleksi dan No Member dengan angka."); 
                }
                tfIdKoleksi.setText("");
                tfNoMember.setText("");
                dcTglPinjam.setDate(null);
            }
            private void bTambahActionPerformed(ActionEvent evt) throws SQLException {
                try {
                    int id_koleksi = Integer.valueOf(tfIdKoleksi.getText());
                    int no_member = Integer.valueOf(tfNoMember.getText());
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    String tgl_pinjam = df.format(dcTglPinjam.getDate());
                    if(mDAO.isAda(id_koleksi)){
                        if(mDAO.isValid()){
                            if (memDAO.isAda(no_member)) {
                                if(memDAO.isValid()){
                                    int affectedRow = pjDAO.insert(new Peminjaman(0, new Monograf(null, 0, id_koleksi, null, null, 0), (new Member(no_member, true, null)), tgl_pinjam, null, null));
                                    if(affectedRow!=0){
                                        JOptionPane.showMessageDialog(null, "Peminjaman berhasil ditambahkan.");
                                    }
                                } else{
                                    JOptionPane.showMessageDialog(null, "Member dengan No Member "+no_member+" tidak aktif. Tidak dapat melakukan peminjaman.");
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Member dengan No Member "+no_member+" tidak ditemukan.");
                            }
                        } else{
                            JOptionPane.showMessageDialog(null, "Monograf dengan ID Koleksi " +id_koleksi+ " berjumlah 0. Tidak dapat dipinjam.");
                        }
                    } else{
                        JOptionPane.showMessageDialog(null, "Monograf dengan ID Koleksi "+id_koleksi+" tidak ditemukan.");
                    }
                    readPeminjaman();
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Gagal menambahkan data. Inputkan data pada kolom ID Koleksi dan No Member dengan angka.");
                }
            }
        });

        bPerpanjang = new JButton();
        bPerpanjang.setBackground(new Color(8, 118, 144));
        bPerpanjang.setFont(new Font("Montserrat", 1, 12)); // NOI18N
        bPerpanjang.setForeground(new Color(252, 253, 255));
        bPerpanjang.setText("Perpanjang");
        bPerpanjang.setEnabled(false);
        bPerpanjang.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    bPerpanjangActionPerformed(evt);
                } catch (CommunicationsException e){
                    JOptionPane.showMessageDialog(null, "Tidak terkoneksi dengan database. Gagal memperbarui data.");
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                } catch (ParseException e){
                    JOptionPane.showMessageDialog(null, e);
                }
            }
            private void bPerpanjangActionPerformed(ActionEvent evt) throws SQLException, ParseException{
                int no = Integer.valueOf(tPeminjaman.getValueAt(tPeminjaman.getSelectedRow(), 0).toString());
                int id_koleksi = Integer.valueOf(tPeminjaman.getValueAt(tPeminjaman.getSelectedRow(), 1).toString());
                int no_member = Integer.valueOf(tPeminjaman.getValueAt(tPeminjaman.getSelectedRow(), 2).toString());
                String tgl_pinjam = tPeminjaman.getValueAt(tPeminjaman.getSelectedRow(), 3).toString();
                String tgl_tenggat = tPeminjaman.getValueAt(tPeminjaman.getSelectedRow(), 4).toString();
                String status = tPeminjaman.getValueAt(tPeminjaman.getSelectedRow(), 5).toString();
                int affectedRow = pjDAO.updatePerpanjang(new Peminjaman(no, (new Monograf(null, 0, id_koleksi, null, null, 0)), (new Member(no_member, true, null)), tgl_pinjam, tgl_tenggat, status));
                if(affectedRow!=0){
                    JOptionPane.showMessageDialog(null, "Peminjaman berhasil diperpanjang.");
                }
                readPeminjaman();               
            }
        });

        bKembali = new JButton();
        bKembali.setBackground(new Color(8, 118, 144));
        bKembali.setFont(new Font("Montserrat", 1, 12)); // NOI18N
        bKembali.setForeground(new Color(252, 253, 255));
        bKembali.setText("Kembali");
        bKembali.setEnabled(false);
        bKembali.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    bKembaliActionPerformed(evt);
                } catch (CommunicationsException e){
                    JOptionPane.showMessageDialog(null, "Tidak terkoneksi dengan database. Gagal memperbarui data.");
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
            private void bKembaliActionPerformed(ActionEvent evt) throws SQLException {
                int no = Integer.valueOf(tPeminjaman.getValueAt(tPeminjaman.getSelectedRow(), 0).toString());
                int id_koleksi = Integer.valueOf(tPeminjaman.getValueAt(tPeminjaman.getSelectedRow(), 1).toString());
                int no_member = Integer.valueOf(tPeminjaman.getValueAt(tPeminjaman.getSelectedRow(), 2).toString());
                String tgl_pinjam = tPeminjaman.getValueAt(tPeminjaman.getSelectedRow(), 3).toString();
                String tgl_tenggat = tPeminjaman.getValueAt(tPeminjaman.getSelectedRow(), 4).toString();
                String status = tPeminjaman.getValueAt(tPeminjaman.getSelectedRow(), 5).toString();
                int affectedRow = pjDAO.updateStatus(new Peminjaman(no, (new Monograf(null, 0, id_koleksi, null, null, 0)), (new Member(no_member, false, null)), tgl_pinjam, tgl_tenggat, status));
                if(affectedRow!=0){
                    JOptionPane.showMessageDialog(null, "Monograf berhasil dikembalikan.");
                }
                readPeminjaman();
            }
        });

        bHapus = new JButton();
        bHapus.setBackground(new Color(251, 153, 28));
        bHapus.setFont(new Font("Montserrat", 1, 12)); // NOI18N
        bHapus.setForeground(new Color(3, 32, 57));
        bHapus.setText("Hapus");
        bHapus.setEnabled(false);
        bHapus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    bHapusActionPerformed(evt);
                } catch (CommunicationsException e){
                    JOptionPane.showMessageDialog(null, "Tidak terkoneksi dengan database. Gagal memperbarui data.");
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
            private void bHapusActionPerformed(ActionEvent evt) throws SQLException {
                int chosen = JOptionPane.showConfirmDialog(null, "Yakin ingin menghapus data?", "Konfirmasi Penghapusan Data", JOptionPane.YES_NO_OPTION);
                if(chosen==0){
                    int no = Integer.valueOf(tPeminjaman.getValueAt(tPeminjaman.getSelectedRow(), 0).toString());
                    int affectedRow = pjDAO.delete(no);
                    if(affectedRow!=0){
                        JOptionPane.showMessageDialog(null, "Data berhasil dihapus.");
                        readPeminjaman();
                    }
                }
            }
        });

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(Alignment.LEADING)
            .addComponent(jPanel2, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
                    .addComponent(lJudulPage)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING)
                            .addComponent(lfIDKoleksi)
                            .addComponent(lfNoMember)
                            .addComponent(lfTglPinjam))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING, false)
                            .addComponent(tfIdKoleksi, GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                            .addComponent(tfNoMember)
                            .addComponent(dcTglPinjam, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(33, 33, 33)
                        .addComponent(bTambah))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 669, GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
                            .addComponent(bPerpanjang)
                            .addComponent(bKembali)
                            .addComponent(bHapus))))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(lJudulPage)
                .addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(bPerpanjang)
                        .addGap(18, 18, 18)
                        .addComponent(bKembali)
                        .addGap(18, 18, 18)
                        .addComponent(bHapus)))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(Alignment.CENTER)
                    .addComponent(lfIDKoleksi)
                    .addComponent(tfIdKoleksi, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(bTambah))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(lfNoMember)
                    .addComponent(tfNoMember, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(Alignment.CENTER)
                    .addComponent(dcTglPinjam, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lfTglPinjam))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    public void readPeminjaman() throws SQLException{
        DefaultTableModel tb = (DefaultTableModel) tPeminjaman.getModel();
        tb.setRowCount(0);
        List<Peminjaman> list = pjDAO.getAll();
        while(!list.isEmpty()){
            Object[] data = {
                list.get(0).getNo(),
                list.get(0).getMonograf().getId(),
                list.get(0).getMember().getNoMember(),
                list.get(0).getTglPinjam(),
                list.get(0).getTglTenggat(),
                list.get(0).getStatus()
            };
            tb.addRow(data);
            list.remove(0);
        }
    }

    public void pagePenyalinan() throws SQLException{
        jPanel1 = new JPanel();
        jPanel1.setBackground(new Color(252, 253, 255));
        jPanel1.setMinimumSize(new Dimension(900, 500));

        jPanel2 = navbar();

        lJudulPage = new JLabel();
        lJudulPage.setFont(new Font("Montserrat", 1, 24)); // NOI18N
        lJudulPage.setForeground(new Color(3, 23, 57));
        lJudulPage.setText("Penyalinan");

        tPenyalinan = new JTable();
        tPenyalinan.setFont(new Font("Arial", 0, 13)); // NOI18N
        tPenyalinan.setBackground(new Color(252, 253, 255));
        tPenyalinan.setGridColor(new Color(211, 211, 211));
        tPenyalinan.setRowHeight(20);
        tPenyalinan.getTableHeader().setOpaque(false);
        tPenyalinan.getTableHeader().setBackground(new Color(245, 245, 245));
        tPenyalinan.getTableHeader().setFont(new Font("Arial", 1, 14));
        tPenyalinan.setModel(new DefaultTableModel(
            null,
            new String [] {
                "NO", "ID Koleksi", "No Member", "Tanggal Penyalinan"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });

        tPenyalinan.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tPenyalinan.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                tPenyalinanMouseClicked(evt);
            }
            private void tPenyalinanMouseClicked(MouseEvent evt) {
                bHapus.setEnabled(!(tPenyalinan.getSelectedRow()==-1));
            }
        });

        jScrollPane1 = new JScrollPane();
        jScrollPane1.setViewportView(tPenyalinan);
        if (tPenyalinan.getColumnModel().getColumnCount() > 0) {
            tPenyalinan.getColumnModel().getColumn(0).setPreferredWidth(80);
            tPenyalinan.getColumnModel().getColumn(1).setPreferredWidth(100);
            tPenyalinan.getColumnModel().getColumn(2).setPreferredWidth(100);
            tPenyalinan.getColumnModel().getColumn(3).setPreferredWidth(140);
        }

        readPenyalinan();        

        lfIDKoleksi = new JLabel();
        lfIDKoleksi.setFont(new Font("Montserrat", 1, 12)); // NOI18N
        lfIDKoleksi.setHorizontalAlignment(SwingConstants.RIGHT);
        lfIDKoleksi.setText("ID Koleksi");

        lfNoMember = new JLabel();
        lfNoMember.setFont(new Font("Montserrat", 1, 12)); // NOI18N
        lfNoMember.setHorizontalAlignment(SwingConstants.RIGHT);
        lfNoMember.setText("No Member");

        tfIdKoleksi = new JTextField();
        tfIdKoleksi.setBorder(BorderFactory.createCompoundBorder(
            tfIdKoleksi.getBorder(),
            BorderFactory.createEmptyBorder(3, 3, 3, 3)
        ));

        tfNoMember = new JTextField();
        tfNoMember.setBorder(BorderFactory.createCompoundBorder(
            tfNoMember.getBorder(),
            BorderFactory.createEmptyBorder(3, 3, 3, 3)
        ));

        dcTglSalin = new JDateChooser();
        dcTglSalin.setDateFormatString("dd MMM yyyy");

        lfTglSalin = new JLabel();
        lfTglSalin.setFont(new Font("Montserrat", 1, 12)); // NOI18N
        lfTglSalin.setHorizontalAlignment(SwingConstants.RIGHT);
        lfTglSalin.setText("Tgl Penyalinan");

        bTambah = new JButton();
        bTambah.setBackground(new Color(8, 118, 144));
        bTambah.setFont(new Font("Montserrat", 1, 12)); // NOI18N
        bTambah.setForeground(new Color(252, 253, 255));
        bTambah.setText("Tambah");
        bTambah.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    if(!(tfIdKoleksi.getText().isEmpty()||tfNoMember.getText().isEmpty())){
                        if(dcTglSalin.getDate()!=null){
                            bTambahActionPerformed(evt);
                        } else{
                            JOptionPane.showMessageDialog(null, "Gagal memperbarui data. Inputkan tanggal sesuai format.");
                        }
                    } else{
                        JOptionPane.showMessageDialog(null, "Gagal memperbarui data. Lengkapi semua kolom input.");
                    }
                } catch (CommunicationsException e){
                    JOptionPane.showMessageDialog(null, "Tidak terkoneksi dengan database. Gagal memperbarui data.");
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                } catch (NumberFormatException e){
                    JOptionPane.showMessageDialog(null, "Gagal memperbarui data. Inputkan data pada kolom ID Koleksi dan No Member dengan angka."); 
                }
                tfIdKoleksi.setText("");
                tfNoMember.setText("");
                dcTglSalin.setDate(null);
            }
            private void bTambahActionPerformed(ActionEvent evt) throws SQLException {
                int id_koleksi = Integer.valueOf(tfIdKoleksi.getText());
                int no_member = Integer.valueOf(tfNoMember.getText());
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String tgl_penyalinan = df.format(dcTglSalin.getDate());
                if(vDAO.isAda(id_koleksi)){
                    if(vDAO.isValid()){
                        if(memDAO.isAda(no_member)){
                            if(memDAO.isValid()){
                                int affectedRow = pyDAO.insert(new Penyalinan(0, (new Video(0, false, id_koleksi, null, null, 0)), (new Member(no_member, false, null)), tgl_penyalinan));
                                if(affectedRow!=0){
                                    JOptionPane.showMessageDialog(null, "Data penyalinan berhasil ditambahkan.");
                                }
                            } else{
                                JOptionPane.showMessageDialog(null, "Member dengan No Member "+no_member+" tidak aktif. Tidak dapat melakukan penyalinan.");
                            }
                        } else{
                            JOptionPane.showMessageDialog(null, "Member dengan No Member "+no_member+" tidak ditemukan.");
                        }
                    } else{
                        JOptionPane.showMessageDialog(null, "Video dengan ID Koleksi "+id_koleksi+" bersifat privat. Tidak dapat disalin.");
                    }
                } else{
                    JOptionPane.showMessageDialog(null, "Video dengan ID Koleksi "+id_koleksi+" tidak ditemukan.");
                }
                readPenyalinan();
            }
        });

        bHapus = new JButton();
        bHapus.setBackground(new Color(251, 153, 28));
        bHapus.setFont(new Font("Montserrat", 1, 12)); // NOI18N
        bHapus.setForeground(new Color(3, 32, 57));
        bHapus.setText("Hapus");
        bHapus.setEnabled(false);
        bHapus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    bHapusActionPerformed(evt);
                } catch (CommunicationsException e){
                    JOptionPane.showMessageDialog(null, "Tidak terkoneksi dengan database. Gagal memperbarui data.");
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
            private void bHapusActionPerformed(ActionEvent evt) throws SQLException {
                int chosen = JOptionPane.showConfirmDialog(null, "Yakin ingin menghapus data?", "Konfirmasi Penghapusan Data", JOptionPane.YES_NO_OPTION);
                if(chosen==0){
                    int no = Integer.valueOf(tPenyalinan.getValueAt(tPenyalinan.getSelectedRow(), 0).toString());
                    int affectedRow = pyDAO.delete(no);
                    if(affectedRow!=0){
                        JOptionPane.showMessageDialog(null, "Data berhasil dihapus.");
                    }
                    readPenyalinan();
                }
            }
        });

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(Alignment.LEADING)
            .addComponent(jPanel2, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
                    .addComponent(lJudulPage)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 502, GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
                            .addComponent(bHapus)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING)
                                    .addComponent(lfIDKoleksi)
                                    .addComponent(lfNoMember)
                                    .addComponent(lfTglSalin))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING, false)
                                    .addComponent(tfIdKoleksi)
                                    .addComponent(tfNoMember)
                                    .addComponent(dcTglSalin, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bTambah))))))
                .addContainerGap(58, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(lJudulPage)
                .addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addGroup(jPanel1Layout.createParallelGroup(Alignment.CENTER)
                            .addComponent(lfIDKoleksi)
                            .addComponent(tfIdKoleksi, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
                            .addComponent(lfNoMember)
                            .addComponent(tfNoMember, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(Alignment.CENTER)
                            .addComponent(dcTglSalin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(lfTglSalin))
                        .addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(bTambah))
                            .addGroup(Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(bHapus))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 327, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    public void readPenyalinan() throws SQLException{
        DefaultTableModel tb = (DefaultTableModel) tPenyalinan.getModel();
        tb.setRowCount(0);
        List<Penyalinan> list = pyDAO.getAll();
        while(!list.isEmpty()){
            Object[] data = {
                list.get(0).getNo(),
                list.get(0).getVideo().getId(),
                list.get(0).getMember().getNoMember(),
                list.get(0).getTglPenyalinan()
            };
            tb.addRow(data);
            list.remove(0);
        }
    }

}