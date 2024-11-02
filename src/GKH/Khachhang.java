/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GKH;

/**
 *
 * @author buikh
 */
public class Khachhang {
    private String sotk, hoten, gt, diachi;
    private int sodu;

    public String getSotk() {
        return sotk;
    }

    public String getHoten() {
        return hoten;
    }

    public String getGt() {
        return gt;
    }

    public String getDiachi() {
        return diachi;
    }

    public int getSodu() {
        return sodu;
    }

    public void setSotk(String sotk) {
        this.sotk = sotk;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public void setGt(String gt) {
        this.gt = gt;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public void setSodu(int sodu) {
        this.sodu = sodu;
    }

    public Khachhang() {
        this.sotk = "";
        this.hoten = "";
        this.gt = "";
        this.diachi = "";
        this.sodu = 0;
    }

    public Khachhang(String sotk, String hoten, String gt, String diachi, int sodu) {
        this.sotk = sotk;
        this.hoten = hoten;
        this.gt = gt;
        this.diachi = diachi;
        this.sodu = sodu;
    }
    
    
}
