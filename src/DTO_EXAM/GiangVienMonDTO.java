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
public class GiangVienMonDTO {

    private String maPhanCong;
    private String maGiangVien;
    private String maMon;
    private int trangThai;

    public GiangVienMonDTO() {
        NumberDTO.numberObject.updateNum();
        maPhanCong = NumberDTO.numberObject.idPhanCong();
        maGiangVien = "";
        maMon = "";
        trangThai = 1;
    }

    public GiangVienMonDTO(String maPhanCong, String maGiangVien, String maMon, int trangThai) {
        this.maPhanCong = maPhanCong;
        this.maGiangVien = maGiangVien;
        this.maMon = maMon;
        this.trangThai = trangThai;
    }

    public String getMaPhanCong() {
        return maPhanCong;
    }

    public void setMaPhanCong(String maPhanCong) {
        this.maPhanCong = maPhanCong;
    }

    public String getMaGiangVien() {
        return maGiangVien;
    }

    public void setMaGiangVien(String maGiangVien) {
        this.maGiangVien = maGiangVien;
    }

    public String getMaMon() {
        return maMon;
    }

    public void setMaMon(String maMon) {
        this.maMon = maMon;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public int getTrangThai() {
        return trangThai;
    }

}
