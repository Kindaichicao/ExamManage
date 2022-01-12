/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO_EXAM;

/**
 *
 * @author Admin
 */
public class QuyenDTO {

    private String maChucVu;
    private String tenChucVu;
    private int trangThai;

    public QuyenDTO() {
        NumberDTO.numberObject.updateNum();
        maChucVu = NumberDTO.numberObject.idChucVu();
        tenChucVu = "";
        trangThai = 1;
    }

    public QuyenDTO(String maChucVu, String tenChucVu, int trangThai) {
        this.maChucVu = maChucVu;
        this.tenChucVu = tenChucVu;
        this.trangThai = trangThai;
    }

    public QuyenDTO(String text, String text0) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getMaChucVu() {
        return maChucVu;
    }

    public void setMaChucVu(String maChucVu) {
        this.maChucVu = maChucVu;
    }

    public String getTenChucVu() {
        return tenChucVu;
    }

    public void setTenChucVu(String tenChucVu) {
        this.tenChucVu = tenChucVu;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public int getTrangThai() {
        return trangThai;
    }

    @Override
    public String toString() {
        return maChucVu + " " + tenChucVu;
    }

}
