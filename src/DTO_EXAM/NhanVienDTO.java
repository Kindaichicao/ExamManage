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
public class NhanVienDTO extends NguoiDungDTO {

    public NhanVienDTO() {

        super();
        NumberDTO.numberObject.updateNum();
        super.setMaUser(NumberDTO.numberObject.idNhanVien());
    }

    public NhanVienDTO(String maUser, String maKhoa, String hoDem, String ten, String ngaySinh, String gioiTinh, int trangThai) {
        super(maUser, maKhoa, hoDem, ten, ngaySinh, gioiTinh, trangThai);
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
