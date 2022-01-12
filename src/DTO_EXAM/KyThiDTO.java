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
public class KyThiDTO {

    private String maKyThi;
    private String tenKyThi;
    private String ngayBatDau;
    private String ngayKetThuc;
    private int trangThai;

    public KyThiDTO() {
        maKyThi = NumberDTO.numberObject.idKyThi();
        tenKyThi = "";
        ngayBatDau = "";
        ngayKetThuc = "";
        trangThai = 1;
    }

    public KyThiDTO(String maKyThi, String tenKyThi, String ngayBatDau, String ngayKetThuc, int trangThai) {
        this.maKyThi = maKyThi;
        this.tenKyThi = tenKyThi;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.trangThai = trangThai;
    }

    public String getMaKyThi() {
        return maKyThi;
    }

    public void setMaKyThi(String maKyThi) {
        this.maKyThi = maKyThi;
    }

    public String getTenKyThi() {
        return tenKyThi;
    }

    public void setTenKyThi(String tenKyThi) {
        this.tenKyThi = tenKyThi;
    }

    public String getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(String ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public String getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(String ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

}
