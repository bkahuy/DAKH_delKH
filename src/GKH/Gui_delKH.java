/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GKH;
import java.awt.*;
import javax.swing.table.*;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author buikh
 */
public class Gui_delKH extends JFrame implements MouseListener, ActionListener{
    
    private JTextField tfsotk, tfhoten;
    private JRadioButton rbnam, rbnu;
    private JTextField tfdiachi, tfsodu;
    private JButton btxoa;
    private JTable tb;
    private DefaultTableModel dfmodel;
    
    public Gui_delKH(){
        setTitle("xoa khach hang");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        
        buildGui();
        loadData(dfmodel);
    }

    private void buildGui(){
        JPanel pntrai = new JPanel(new GridBagLayout());
        pntrai.setBorder(new  EmptyBorder(10,10,10,10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5,5,5,5);
        //so tk
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel lbsotk = new JLabel("so tai khoan");
        pntrai.add(lbsotk,gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        tfsotk = new JTextField();
        tfsotk.setSize(new Dimension(500,30));
        pntrai.add(tfsotk,gbc);
        
        // ho ten
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel lbhoten = new JLabel("ho ten");
        pntrai.add(lbhoten,gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        tfhoten = new JTextField();
        tfhoten.setSize(new Dimension(500,30));
        pntrai.add(tfhoten,gbc);
        
        //gioi tinh
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel lbgioitinh = new JLabel("gioi tinh");
        pntrai.add(lbgioitinh,gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        JPanel pngioitinh = new JPanel();
        ButtonGroup bggioitinh = new ButtonGroup();
        rbnam = new JRadioButton("nam");
        rbnu = new JRadioButton("nu");
        bggioitinh.add(rbnu);
        bggioitinh.add(rbnam);
        
        pngioitinh.add(rbnam);
        pngioitinh.add(rbnu);
        
        pntrai.add(pngioitinh,gbc);
        
        // dia chi
        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel lbdiachi = new JLabel("dia chi");
        pntrai.add(lbdiachi,gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        tfdiachi = new JTextField();
        tfdiachi.setSize(new Dimension(500,50));
        pntrai.add(tfdiachi,gbc);
        
        //so du
        gbc.gridx = 0;
        gbc.gridy = 4;
        JLabel lbsodu = new JLabel("so du");
        pntrai.add(lbsodu, gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        tfsodu = new JTextField();
        tfsodu.setSize(new Dimension(500,30));
        pntrai.add(tfsodu, gbc);
        
        //button xoa
        gbc.gridx = 0;
        gbc.gridy = 5;
        btxoa = new JButton("xoa khach hang");
        pntrai.add(btxoa,gbc);
        btxoa.addActionListener((e) -> {
            String diachi = tfdiachi.getText().trim();
            String gioitinh = "";
            if (rbnam.isSelected()){
                gioitinh = rbnam.getText();
            }
            else if (rbnu.isSelected()){
                gioitinh = rbnu.getText();
            }
            XLKH kh = new XLKH();
            boolean res = kh.delKH(diachi, gioitinh);
            if (res) {
                loadData(dfmodel);
                JOptionPane.showMessageDialog(null, "xoa thanh cong");
            }
            else {
                JOptionPane.showMessageDialog(null, "xoa that bai");
            }
        });
        pntrai.add(btxoa,gbc);
        
        //table
        JPanel pnphai = new JPanel(new GridLayout(1, 1));
        String[] header = {"so tai khoan", "ho ten", "gioi tinh", "dia chi", "so du"};
        dfmodel = new DefaultTableModel(header, 0);
        tb = new JTable(dfmodel);
        pnphai.add(new JScrollPane(tb));

        // su kien kich chon trong bang
        tb.getSelectionModel().addListSelectionListener((e) -> {
            try {
                int selectRow = tb.getSelectedRow();
                if(selectRow != -1){
                    tfsotk.setText(tb.getValueAt(selectRow, 0).toString());
                    tfhoten.setText(tb.getValueAt(selectRow, 1).toString());
                    if("nam".equals(tb.getValueAt(selectRow, 2).toString())){
                        rbnam.setSelected(true);
                    }else if ("nu".equals(tb.getValueAt(selectRow, 2).toString())){
                        rbnu.setSelected(true);
                    }
                    tfdiachi.setText(tb.getValueAt(selectRow, 3).toString());
                    tfsodu.setText(tb.getValueAt(selectRow, 4).toString());
                }
            } catch (Exception ex) {
            }
        });

        
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(pntrai, BorderLayout.WEST);
        getContentPane().add(pnphai, BorderLayout.CENTER);
    }
    
    private void loadData(DefaultTableModel dfmodel){
        try {
            XLKH xl = new XLKH();
            ResultSet res = xl.getData();
            dfmodel.setRowCount(0);
            if (res != null){
                while (res.next()){
                    Khachhang khach = new Khachhang(res.getString("sotk"), res.getString("hoten"), res.getString("gioitinh"), res.getString("diachi"), res.getInt("sodu"));
                    dfmodel.addRow(new Object[]{
                    khach.getSotk(),
                    khach.getHoten(),
                    khach.getGt(),
                    khach.getDiachi(),
                    khach.getSodu()
                    });
                }
            }
            dfmodel.fireTableDataChanged();
        } catch (Exception e) {
        }
    }
    
    public static void main(String[] args) {
        new Gui_delKH().setVisible(true);
    }
    
    
    
    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
