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
public class BaiThiDTO {

    private String maSinhVien;
    private String maDe;
    private String dsCauTraLoi;
    private String dsDungSai;
    private int soCauDung;
    private String diem;
    private int trangThai;

    public BaiThiDTO() {
        this.maSinhVien = "";
        this.maDe = "";
        this.dsCauTraLoi = "";
        this.dsDungSai = "";
        this.soCauDung = 0;
        this.diem = "";
        this.trangThai = 1;
    }

    public BaiThiDTO(String maSinhVien, String maDe, String dsCauTraLoi, String dsDungSai, int soCauDung, String diem, int trangThai) {
        this.maSinhVien = maSinhVien;
        this.maDe = maDe;
        this.dsCauTraLoi = dsCauTraLoi;
        this.dsDungSai = dsDungSai;
        this.soCauDung = soCauDung;
        this.diem = diem;
        this.trangThai = trangThai;
    }

    public void setDsDungSai(String dsDungSai) {
        this.dsDungSai = dsDungSai;
    }

    public void setSoCauDung(int soCauDung) {
        this.soCauDung = soCauDung;
    }

    public void setDiem(String diem) {
        this.diem = diem;
    }

    public String getDsDungSai() {
        return dsDungSai;
    }

    public int getSoCauDung() {
        return soCauDung;
    }

    public String getDiem() {
        return diem;
    }

    public String getMaSinhVien() {
        return maSinhVien;
    }

    public void setMaSinhVien(String maSinhVien) {
        this.maSinhVien = maSinhVien;
    }

    public String getMaDe() {
        return maDe;
    }

    public void setMaDe(String maDe) {
        this.maDe = maDe;
    }

    public String getDsCauTraLoi() {
        return dsCauTraLoi;
    }

    public void setDsCauTraLoi(String dsCauTraLoi) {
        this.dsCauTraLoi = dsCauTraLoi;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "BaiThiDTO{" + "maSinhVien=" + maSinhVien + ", maDe=" + maDe + ", dsCauTraLoi=" + dsCauTraLoi + ", dsDungSai=" + dsDungSai + ", soCauDung=" + soCauDung + ", diem=" + diem + ", trangThai=" + trangThai + '}';
    }

}
