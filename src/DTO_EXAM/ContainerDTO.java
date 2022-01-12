/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO_EXAM;

import DAO_EXAM.BaseDAO;

/**
 *
 * @author Admin
 */
public class ContainerDTO {

    int slAdmin;
    int slBaiThi;
    int slCauHoi;
    int slChucVu;
    int slDeCauHoi;
    int slDeThi;
    int slGiangVien;
    int slPhanCong;
    int slKhoa;
    int slKyThi;
    int slLop;
    int slMon;
    int slNhanVien;
    int slPhanQuyen;
    int slQuyen;
    int slSinhVien;
    BaseDAO base = new BaseDAO();

    public ContainerDTO() {
        //updateNum();
    }

    public void updateNum() {
        slAdmin = base.getNumberOf("admin");
        slBaiThi = base.getNumberOf("baithi");
        slCauHoi = base.getNumberOf("cauhoi");
        slChucVu = base.getNumberOf("quyen");
        slDeCauHoi = base.getNumberOf("de_cauhoi");
        slDeThi = base.getNumberOf("dethi");
        slGiangVien = base.getNumberOf("giangvien");
        slPhanCong = base.getNumberOf("gv_mh");
        slKhoa = base.getNumberOf("khoa");
        slKyThi = base.getNumberOf("kythi");
        slLop = base.getNumberOf("lop");
        slMon = base.getNumberOf("monhoc");
        slNhanVien = base.getNumberOf("nhanvien");
        slPhanQuyen = base.getNumberOf("chitietquyen");
        slQuyen = base.getNumberOf("chucnang");
        slSinhVien = base.getNumberOf("sinhvien");
    }

    public int getNumNum(int i) {
        int d = 0;
        while (i != 0) {
            i /= 10;
            d++;
        }
        return d;
    }

    public String autoID(int id, int i) {
        String auto = String.valueOf(++id);
        while (auto.length() < i) {
            auto = "0" + auto;
        }
        return auto;
    }

    public String idAdmin() {
        updateNum();
        return "AD" + autoID(slAdmin, 3);
    }

    public String idBaiThi() {
        return "";
    }

    public String idCauHoi() {
        updateNum();
        return "CH" + autoID(slCauHoi, 4);
    }

    public String idChucVu() {
        updateNum();
        return "CV" + autoID(slChucVu, 2);
    }

    public String idDeThi() {
        updateNum();
        return "D" + autoID(slDeThi, 3);
    }

    public String idGiangVien() {
        updateNum();
        return "GV" + autoID(slGiangVien, 3);
    }

    public String idPhanCong() {
        updateNum();
        return "PC" + autoID(slPhanCong, 3);
    }

    public String idKhoa() {
        updateNum();
        return "K" + autoID(slKhoa, 3);
    }

    public String idKyThi() {
        updateNum();
        return "KT" + autoID(slKyThi, 2);
    }

    public String idLop() {
        updateNum();
        return "L" + autoID(slLop, 3);
    }

    public String idMon() {
        updateNum();
        return "MH" + autoID(slMon, 3);
    }

    public String idNhanVien() {
        updateNum();
        return "NV" + autoID(slNhanVien, 3);
    }

    public String idPhanQuyen() {
        updateNum();
        return "PQ" + autoID(slPhanQuyen, 3);
    }

    public String idQuyen() {
        updateNum();
        return "Q" + autoID(slQuyen, 2);
    }

    public String idSinhVien() {
        updateNum();
        return "SV" + autoID(slSinhVien, 3);
    }

}
