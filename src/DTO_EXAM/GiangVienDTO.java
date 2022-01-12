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
public class GiangVienDTO extends NguoiDungDTO {

    public GiangVienDTO() {
        super();
        NumberDTO.numberObject.updateNum();
        super.setMaUser(NumberDTO.numberObject.idGiangVien());
    }

    public GiangVienDTO(String maUser, String maKhoa, String hoDem, String ten, String ngaySinh, String gioiTinh, int trangThai) {
        super(maUser, maKhoa, hoDem, ten, ngaySinh, gioiTinh, trangThai);
    }

    @Override
    public String toString() {
        return "GiangVienDTO{" + '}' + super.toString();
    }

}
