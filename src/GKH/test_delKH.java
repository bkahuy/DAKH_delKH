/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GKH;




/**
 *
 * @author buikh
 */
public class test_delKH{
    public static void doDelKH(String diachi, String gt){
        
        XLKH kh = new XLKH();
        kh.getcon();
        kh.delKH(diachi, gt);
        System.out.println("xoa thanh cong!");
    }

    public static void main(String[] args) {
        
        doDelKH("thanh hoa", "nu");
    }
    
}
