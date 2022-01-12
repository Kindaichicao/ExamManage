/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO_EXAM;

import DAO_EXAM.ContainerDAO;

/**
 *
 * @author Admin
 */
public class SinhVienDTO extends NguoiDungDTO {

    public String maLop;

    public SinhVienDTO() {
        super();
        NumberDTO.numberObject.updateNum();
        super.setMaUser(NumberDTO.numberObject.idSinhVien());
        maLop = "";
    }

    public SinhVienDTO(String maUser, String maLop, String maKhoa, String hoDem, String ten, String ngaySinh, String gioiTinh, int trangThai) {
        super(maUser, maKhoa, hoDem, ten, ngaySinh, gioiTinh, trangThai);
        this.maLop = maLop;
    }

    public SinhVienDTO(String maLop, String maKhoa, String hoDem, String ten, String ngaySinh, String gioiTinh, int trangThai) {
        super(maKhoa, hoDem, ten, ngaySinh, gioiTinh, trangThai);
        NumberDTO.numberObject.updateNum();
        super.setMaUser(NumberDTO.numberObject.idSinhVien());
        this.maLop = maLop;
    }

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public String toString() {
        return "SinhVienDTO{" + "maLop=" + maLop + '}' + super.toString();
    }
}
