/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO_EXAM;

import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class MonSinhVienDTO {

    private String maSV;
    private String maMon;
    private int trangThai;

    public MonSinhVienDTO() {
        this.maSV = "";
        this.maMon = "";
        this.trangThai = 1;
    }

    public MonSinhVienDTO(String maSV, String maMon, int trangThai) {
        this.maSV = maSV;
        this.maMon = maMon;
        this.trangThai = trangThai;
    }

    public String getMaSV() {
        return maSV;
    }

    public String getMaMon() {
        return maMon;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public void setMaMon(String maMon) {
        this.maMon = maMon;
    }

    @Override
    public String toString() {
        return "MonSinhVienDTO{" + "maSV=" + maSV + ", maMon=" + maMon + ", trangThai=" + trangThai + '}';
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public int getTrangThai() {
        return trangThai;
    }

}
