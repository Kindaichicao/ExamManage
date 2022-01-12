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
public class DeThiDTO {

    private String maDe;
    private String maMon;
    private String maKyThi;
    private int thoigianLamBai;
    private String ngayThi;
    private String gioThi;
    private int soLuongCauHoi;
    private int de;
    private int tb;
    private int kho;

    private int trangThai;

    public DeThiDTO() {
        NumberDTO.numberObject.updateNum();
        maDe = NumberDTO.numberObject.idDeThi();
        maMon = "";
        maKyThi = "";
        thoigianLamBai = 0;
        this.ngayThi = "";
        this.gioThi = "";
        this.soLuongCauHoi = 0;
        this.de = 0;
        this.tb = 0;
        this.kho = 0;
        trangThai = 1;
    }

    public int getDe() {
        return de;
    }

    public void setDe(int de) {
        this.de = de;
    }

    public int getTb() {
        return tb;
    }

    public void setTb(int tb) {
        this.tb = tb;
    }

    public int getKho() {
        return kho;
    }

    public void setKho(int kho) {
        this.kho = kho;
    }

    public DeThiDTO(String maMon, String maKyThi, int thoigianLamBai, String ngayThi, String gioThi,
            int soLuongCauHoi, int de, int tb, int kho) {
        this.maDe = NumberDTO.numberObject.idDeThi();
        this.maMon = maMon;
        this.maKyThi = maKyThi;
        this.thoigianLamBai = thoigianLamBai;
        this.ngayThi = ngayThi;
        this.gioThi = gioThi;
        this.soLuongCauHoi = soLuongCauHoi;
        this.de = de;
        this.tb = tb;
        this.kho = kho;
        this.trangThai = 1;
    }

    public DeThiDTO(String maDe, String maMon, String maKyThi, int thoigianLamBai, String ngayThi, String gioThi,
            int soLuongCauHoi, int de, int tb, int kho, int trangThai) {
        this.maDe = maDe;
        this.maMon = maMon;
        this.maKyThi = maKyThi;
        this.thoigianLamBai = thoigianLamBai;
        this.ngayThi = ngayThi;
        this.gioThi = gioThi;
        this.soLuongCauHoi = soLuongCauHoi;
        this.de = de;
        this.tb = tb;
        this.kho = kho;
        this.trangThai = trangThai;
    }

    public String getMaDe() {
        return maDe;
    }

    public void setMaDe(String maDe) {
        this.maDe = maDe;
    }

    public String getMaMon() {
        return maMon;
    }

    public void setMaMon(String maMon) {
        this.maMon = maMon;
    }

    public String getMaKyThi() {
        return maKyThi;
    }

    public void setMaKyThi(String maKyThi) {
        this.maKyThi = maKyThi;
    }

    public int getThoigianLamBai() {
        return thoigianLamBai;
    }

    public void setThoigianLamBai(int thoigianLamBai) {
        this.thoigianLamBai = thoigianLamBai;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public String getNgayThi() {
        return ngayThi;
    }

    public void setNgayThi(String ngayThi) {
        this.ngayThi = ngayThi;
    }

    public String getGioThi() {
        return gioThi;
    }

    public void setGioThi(String gioThi) {
        this.gioThi = gioThi;
    }

    public int getSoLuongCauHoi() {
        return soLuongCauHoi;
    }

    public void setSoLuongCauHoi(int soLuongCauHoi) {
        this.soLuongCauHoi = soLuongCauHoi;
    }

}
