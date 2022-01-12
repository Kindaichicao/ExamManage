/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS_EXAM;

import DAO_EXAM.ContainerDAO;
import DAO_EXAM.SinhVienDAO;
import DTO_EXAM.*;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author Admin
 */
public class SinhVienBUS extends BaseBUS {

    SinhVienDAO sinhvien = new SinhVienDAO();

    @Override
    public SinhVienDTO getObject(String maSinhVien) {
        return sinhvien.getSinhVien(maSinhVien);
    }

    @Override
    public ArrayList<SinhVienDTO> getList() {
        return sinhvien.getList();
    }

    @Override
    public boolean them(Object obj) {
        return sinhvien.them((SinhVienDTO) obj);
    }

    @Override
    public boolean sua(Object obj, String maObject) {
        return sinhvien.sua((SinhVienDTO) obj, maObject);
    }

    @Override
    public boolean xoa(String maObject) {
        return sinhvien.xoa(maObject);
    }

    @Override
    public boolean importExcel(String fileName) {
        return sinhvien.importExcel(fileName);
    }

    @Override
    public boolean exportExcel(String fileName) {
        return sinhvien.exportExcel(fileName);
    }

    @Override
    public boolean isExisted(String maObject) {
        for (Object obj : getList()) {
            SinhVienDTO sv = (SinhVienDTO) obj;
            if (sv.getMaUser().equals(maObject)) {
                return true;
            }
        }
        return false;
    }

    public Vector getThongTin(int trang) {
        return ContainerDAO.daoSV.getThongTin(trang);
    }

    public int coutThongTin() {
        return ContainerDAO.daoSV.coutThongTin();
    }

    public boolean khoiphuc(String maObject) {
        return sinhvien.khoiphuc(maObject);
    }

    public boolean xoavinhvien(String maObject) {
        return sinhvien.xoavinhvien(maObject);
    }

    public ArrayList<MonDTO> getListMonKyThi(String maSV, String maKyThi) {
        return sinhvien.getListMonKyThi(maSV, maKyThi);
    }

    public ArrayList<BaiThiDTO> getListBaiThiKyThi(String maSV, String maKyThi) {
        return sinhvien.getListBaiThiKyThi(maSV, maKyThi);
    }

    public BaiThiDTO getBaiThiMon(String maSV, String maMon) {
        return sinhvien.getListMonBaiThi(maSV, maMon);
    }

    public double getDiemTrungBinh(String maSV, String maKyThi) {
        double diem = 0;
        double soluongMon = 0;
        double diemTrungBinh;
        for (BaiThiDTO baithi : getListBaiThiKyThi(maSV, maKyThi)) {
            diem += Double.parseDouble(baithi.getDiem());
            soluongMon++;
        }
        diemTrungBinh = diem / soluongMon;
        return (double) Math.round(diemTrungBinh * 100) / 100;
    }

    public String getXepLoai(String maSV, String maKyThi) {
        double dtb = getDiemTrungBinh(maSV, maKyThi);
        if (dtb >= 8.5 && dtb <= 10) {
            return "Giỏi";
        } else if (dtb >= 7 && dtb < 8.4) {
            return "Khá";
        } else if (dtb >= 5.5 && dtb < 7) {
            return "Trung bình";
        } else if (dtb >= 4 && dtb < 5.5) {
            return "Yếu";
        } else {
            return "Kém";
        }
    }

    public String getXepLoaiMon(String maSV, String maMon) {
        BaiThiDTO baithi = getBaiThiMon(maSV, maMon);
        double diem = Double.parseDouble(baithi.getDiem());
        if (diem >= 8.5 && diem <= 10) {
            return "Giỏi";
        } else if (diem >= 7 && diem < 8.4) {
            return "Khá";
        } else if (diem >= 5.5 && diem < 7) {
            return "Trung bình";
        } else if (diem >= 4 && diem < 5.5) {
            return "Yếu";
        } else {
            return "Kém";
        }
    }

    public Vector<String> getTenSinhVien(String maKyThi) {
        Vector get = new Vector();
        for (SinhVienDTO sinhvien : getList()) {
            if (getListBaiThiKyThi(sinhvien.getMaUser(), maKyThi).size() > 0) {
                get.add(sinhvien.getMaUser() + " - " + sinhvien.getHoDem() + " " + sinhvien.getTen());
            }
        }
        return get;
    }

}
