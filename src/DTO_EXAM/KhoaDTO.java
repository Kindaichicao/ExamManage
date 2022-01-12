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
public class KhoaDTO {

    private String maKhoa;
    private String tenKhoa;
    private int trangThai;

    public KhoaDTO() {
        maKhoa = NumberDTO.numberObject.idKhoa();
        tenKhoa = "";
        trangThai = 1;
    }

    public KhoaDTO(String maKhoa, String tenKhoa, int trangThai) {
        this.maKhoa = maKhoa;
        this.tenKhoa = tenKhoa;
        this.trangThai = trangThai;
    }

    public String getMaKhoa() {
        return maKhoa;
    }

    public void setMaKhoa(String maKhoa) {
        this.maKhoa = maKhoa;
    }

    public String getTenKhoa() {
        return tenKhoa;
    }

    public void setTenKhoa(String tenKhoa) {
        this.tenKhoa = tenKhoa;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

}
