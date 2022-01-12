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
public class NguoiDungDTO {

    private String maUser;
    private String maKhoa;
    private String hoDem;
    private String ten;
    private String ngaySinh;
    private String gioiTinh;
    private int trangThai;

    public NguoiDungDTO() {
        this.gioiTinh = "";
        this.hoDem = "";
        this.ten = "";
        this.ngaySinh = "2001-01-01";
        this.maKhoa = "";
        this.trangThai = 1;
    }

    public NguoiDungDTO(String maUser, String maKhoa, String hoDem, String ten, String ngaySinh, String gioiTinh, int trangThai) {
        this.maUser = maUser;
        this.maKhoa = maKhoa;
        this.hoDem = hoDem;
        this.ten = ten;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.trangThai = trangThai;
    }

    public NguoiDungDTO(String hoDem, String ten, String ngaySinh, String gioiTinh, int trangThai, String maKhoa) {
        this.maKhoa = maKhoa;
        this.hoDem = hoDem;
        this.ten = ten;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.trangThai = trangThai;
    }

    public NguoiDungDTO(String maUser, String hoDem, String ten, String ngaySinh, String gioiTinh, int trangThai) {
        this.maUser = maUser;
        this.hoDem = hoDem;
        this.ten = ten;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.trangThai = trangThai;
    }

    public String getMaKhoa() {
        return maKhoa;
    }

    public String getMaUser() {
        return maUser;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public String getHoDem() {
        return hoDem;
    }

    public String getTen() {
        return ten;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setMaUser(String maUser) {
        this.maUser = maUser;
    }

    public void setMaKhoa(String maKhoa) {
        this.maKhoa = maKhoa;
    }

    public void setHoDem(String hoDem) {
        this.hoDem = hoDem;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String toString() {
        return "NguoiDungDTO{" + "maUser=" + maUser + ", maKhoa=" + maKhoa + ", hoDem=" + hoDem + ", ten=" + ten + ", "
                + "ngaySinh=" + ngaySinh + ", gioiTinh=" + gioiTinh + ", trangThai=" + trangThai + '}';
    }
}
