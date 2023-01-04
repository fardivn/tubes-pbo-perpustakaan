package com.gui;

import javax.swing.*;
import javax.swing.GroupLayout.*;
import javax.swing.LayoutStyle.*;

import java.awt.*;
import java.awt.event.*;

import java.sql.SQLException;
import com.mysql.cj.jdbc.exceptions.CommunicationsException;

import com.dao.PegawaiDAO;

public class Login extends MyFrame implements ActionListener{
    private JLabel lJudulPage, lID, lPass;
    private JTextField tfID;
    private JPasswordField pfPass;
    private JButton bLogin;
    private Font fontLabel = new Font("Montserrat", Font.BOLD, 16);
    private Color warnaBiruDongker = new Color(3, 32,57);

    public Login(){
        super();

        lJudulPage = new JLabel();
        lJudulPage.setText("<html><div style:'text-align: center;'>Sistem Informasi<br>Koleksi Perpustakaan</div></html>");
        lJudulPage.setFont(new Font("Montserrat", Font.BOLD, 36));
        lJudulPage.setForeground(warnaBiruDongker);

        lID = new JLabel();
        lID.setText("ID");
        lID.setFont(fontLabel);
        lID.setForeground(warnaBiruDongker);

        tfID = new JTextField(20);
        tfID.setFont(new Font("Arial", 0, 12));
        tfID.setBorder(BorderFactory.createCompoundBorder(
            tfID.getBorder(),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        tfID.setForeground(warnaBiruDongker);

        lPass = new JLabel();
        lPass.setText("Password");
        lPass.setFont(fontLabel);
        lPass.setForeground(warnaBiruDongker);
        
        pfPass = new JPasswordField(20);
        pfPass.setFont(new Font("Arial", 0, 12));
        pfPass.setBorder(BorderFactory.createCompoundBorder(
            pfPass.getBorder(),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        pfPass.setForeground(warnaBiruDongker);

        bLogin = new JButton("LOGIN");
        bLogin.setBounds(100, 100, 60, 20);
        bLogin.setFont(fontLabel);
        bLogin.setBackground(warnaBiruDongker);
        bLogin.setForeground(Color.WHITE);
        bLogin.setBorderPainted(false);
        bLogin.addActionListener(this);
        bLogin.setBorder(BorderFactory.createCompoundBorder(
            bLogin.getBorder(),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(280, 280, 280)
                        .addGroup(layout.createParallelGroup(Alignment.TRAILING)
                            .addComponent(lID)
                            .addComponent(lPass))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(Alignment.CENTER)
                            .addComponent(tfID, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)
                            .addComponent(pfPass, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(280, 280, 280)
                        .addGroup(layout.createParallelGroup(Alignment.LEADING)
                            .addComponent(bLogin, Alignment.CENTER)
                            .addComponent(lJudulPage, Alignment.CENTER))))
                .addContainerGap(280, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(120, Short.MAX_VALUE)
                .addComponent(lJudulPage)
                .addGap(70, 70, 70)
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                    .addComponent(tfID, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lID, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(Alignment.TRAILING)
                    .addComponent(lPass)
                    .addComponent(pfPass, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addComponent(bLogin)
                .addGap(80, 80, 80))
        );
        pack();
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        PegawaiDAO pDAO = new PegawaiDAO();
        try {
            int id = Integer.valueOf(tfID.getText());
            String pass = String.valueOf(pfPass.getPassword());
            if(pDAO.isAda(id)){
                boolean isValid = pDAO.login(id, pass);
                if(isValid){
                    this.dispose();
                    Dashboard d = new Dashboard();
                    d.setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(null, "Login gagal. Kombinasi ID dan password Anda salah.");
                }
            } else{
                JOptionPane.showMessageDialog(null, "Login gagal. ID tidak ditemukan.");
            }
        } catch (NumberFormatException e1) {
            JOptionPane.showMessageDialog(null, "Login gagal. ID tidak valid.");
        } catch(CommunicationsException e1){
            JOptionPane.showMessageDialog(null, "Tidak terkoneksi dengan database. Tidak dapat melakukan login.");
        } catch (SQLException e1) {
            JOptionPane.showMessageDialog(null, e1);
        }
        tfID.setText("");
        pfPass.setText("");
    }

}