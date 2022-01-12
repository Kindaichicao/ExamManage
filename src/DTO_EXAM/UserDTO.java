/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO_EXAM;

/**
 *
 * @author ASUS
 */
public class UserDTO {

    public String maUser;
    public String matKhau;
    public String maChucVu;
    public int trangThai;

    public UserDTO() {
        this.maUser = "";
        this.matKhau = "123456";
        this.maChucVu = "";
        this.trangThai = 1;
    }

    public UserDTO(String maUser, String matKhau, String maChucVu, int trangThai) {
        this.maUser = maUser;
        this.matKhau = matKhau;
        this.maChucVu = maChucVu;
        this.trangThai = trangThai;
    }

    public String getMaUser() {
        return maUser;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public String getMaChucVu() {
        return maChucVu;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setMaUser(String maUser) {
        this.maUser = maUser;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public void setMaChucVu(String maChucVu) {
        this.maChucVu = maChucVu;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "UserDTO{" + "maUser=" + maUser + ", matKhau=" + matKhau + ", maChucVu=" + maChucVu + ", trangThai=" + trangThai + '}';
    }

}
