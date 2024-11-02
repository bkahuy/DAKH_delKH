/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GKH;
import java.sql.*;
/**
 *
 * @author buikh
 */
public class XLKH implements iKhachhang {
    private static Connection cn;

    @Override
    public void getcon() {
        if(cn ==null){
            try {
                cn = DriverManager.getConnection("jdbc:sqlserver://BKAHUYYYYY;database=DLKH;user=sa;password=1;trustServerCertificate=true;");
                System.out.println("pass connect");
            } catch (SQLException e) {
                System.out.println("failed connect " + e.getMessage());
            }
        }
    }

    @Override
    public boolean delKH(String diachi, String gt) {
        getcon();
        try {
            PreparedStatement pst = cn.prepareStatement("delete from tbKhachhang where diachi = N'" + diachi + "' and gioitinh = N'" + gt + "'");
            int res = pst.executeUpdate();
            return res > 0;
        } catch (SQLException e) {
            System.out.println("khong xoa duoc" + e.getMessage());
            return false;
        }
    }

    @Override
    public ResultSet getData() {
        getcon();
        try {
            Statement st = cn.createStatement();
            return st.executeQuery("select * from tbKhachhang");
            
        } catch (SQLException e) {
            System.out.println("load data failed");
            return null;
        }
    }
    
}
