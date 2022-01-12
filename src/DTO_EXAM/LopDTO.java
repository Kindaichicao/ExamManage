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
public class LopDTO {

    private String maLop;
    private String maKhoa;
    private String maCoVan;
    private String tenLop;
    private int trangThai;

    public LopDTO() {
        NumberDTO.numberObject.updateNum();
        maLop = NumberDTO.numberObject.idLop();
        maKhoa = "";
        maCoVan = "";
        tenLop = "";
        trangThai = 1;
    }

    public LopDTO(String maLop, String maKhoa, String maCoVan, String tenLop, int trangThai) {
        this.maLop = maLop;
        this.maKhoa = maKhoa;
        this.maCoVan = maCoVan;
        this.tenLop = tenLop;
        this.trangThai = trangThai;
    }

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public String getMaKhoa() {
        return maKhoa;
    }

    public void setMaKhoa(String maKhoa) {
        this.maKhoa = maKhoa;
    }

    public String getMaCoVan() {
        return maCoVan;
    }

    public void setMaCoVan(String maCoVan) {
        this.maCoVan = maCoVan;
    }

    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

}
