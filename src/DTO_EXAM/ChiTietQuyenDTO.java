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
public class ChiTietQuyenDTO {

    String maPhanQuyen;
    String maChucVu;
    String maQuyen;
    int trangThai;

    public ChiTietQuyenDTO() {
        NumberDTO.numberObject.updateNum();
        maPhanQuyen = NumberDTO.numberObject.idPhanQuyen();
        maChucVu = "";
        maQuyen = "";
        trangThai = 1;
    }

    public ChiTietQuyenDTO(String maPhanQuyen, String maChucVu, String maQuyen, int trangThai) {
        this.maPhanQuyen = maPhanQuyen;
        this.maChucVu = maChucVu;
        this.maQuyen = maQuyen;
        this.trangThai = trangThai;
    }

    public ChiTietQuyenDTO(String maChucVu, String maQuyen) {
        this.maChucVu = maChucVu;
        this.maQuyen = maQuyen;
        this.maPhanQuyen = NumberDTO.numberObject.idPhanQuyen();
    }

    public String getMaPhanQuyen() {
        return maPhanQuyen;
    }

    public void setMaPhanQuyen(String maPhanQuyen) {
        this.maPhanQuyen = maPhanQuyen;
    }

    public String getMaChucVu() {
        return maChucVu;
    }

    public void setMaChucVu(String maChucVu) {
        this.maChucVu = maChucVu;
    }

    public String getMaQuyen() {
        return maQuyen;
    }

    public void setMaQuyen(String maQuyen) {
        this.maQuyen = maQuyen;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "QuyenChucVuDTO{" + "maPhanQuyen=" + maPhanQuyen + ", maChucVu=" + maChucVu + ", maQuyen=" + maQuyen + '}';
    }

}
