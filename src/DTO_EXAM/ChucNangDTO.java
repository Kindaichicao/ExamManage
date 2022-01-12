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
public class ChucNangDTO {

    String maQuyen;
    String tenBang;
    String hanhDong;
    int trangThai;

    public ChucNangDTO() {
        maQuyen = NumberDTO.numberObject.idQuyen();
        tenBang = "";
        hanhDong = "";
        trangThai = 1;
    }

    public ChucNangDTO(String maQuyen, String tenBang, String hanhDong, int trangThai) {
        this.maQuyen = maQuyen;
        this.tenBang = tenBang;
        this.hanhDong = hanhDong;
        this.trangThai = trangThai;
    }

    public String getMaQuyen() {
        return maQuyen;
    }

    public void setMaQuyen(String maQuyen) {
        this.maQuyen = maQuyen;
    }

    public String getTenBang() {
        return tenBang;
    }

    public void setTenBang(String tenBang) {
        this.tenBang = tenBang;
    }

    public String getHanhDong() {
        return hanhDong;
    }

    public void setHanhDong(String hanhDong) {
        this.hanhDong = hanhDong;
    }

}
