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
public class DeCauHoiDTO {

    private String maCauHoi;
    private String maDe;
    private int trangThai;

    public DeCauHoiDTO() {
        maCauHoi = "";
        maDe = "";
        trangThai = 0;
    }

    public DeCauHoiDTO(String maCauHoi, String maDe, int trangThai) {
        this.maCauHoi = maCauHoi;
        this.maDe = maDe;
        this.trangThai = trangThai;
    }

    public String getMaCauHoi() {
        return maCauHoi;
    }

    public void setMaCauHoi(String maCauHoi) {
        this.maCauHoi = maCauHoi;
    }

    public String getMaDe() {
        return maDe;
    }

    public void setMaDe(String maDe) {
        this.maDe = maDe;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public int getTrangThai() {
        return trangThai;
    }

}
