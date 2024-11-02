/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package GKH;
import java.sql.*;
/**
 *
 * @author buikh
 */
public interface iKhachhang {
    public void getcon();
    public boolean delKH(String diachi, String gt);
    public ResultSet getData();
}
