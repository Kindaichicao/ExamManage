/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_EXAM;

import DTO_EXAM.SinhVienDTO;
import DTO_EXAM.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class SinhVienDAO extends BaseDAO {

    SinhVienDTO sinhvien = new SinhVienDTO();
    UserDAO user = new UserDAO();

    public SinhVienDAO() {
        super();
    }

    public SinhVienDTO getSinhVien(String maSinhVien) {
        return (SinhVienDTO) getDoiTuong(maSinhVien, "sinhvien", sinhvien);
    }

    public ArrayList getList() {
        return getArrayList(sinhvien, "sinhvien");
    }

    public boolean them(SinhVienDTO sinhvien) {
        return themDoiTuong(sinhvien, "sinhvien");
    }

    public boolean sua(SinhVienDTO sinhvien, String maSinhVien) {
        return suaDoiTuong(sinhvien, "sinhvien", maSinhVien);
    }

    public boolean xoa(String maSinhVien) {
        try {
            super.statement.execute("update user set TrangThai = 0 where MaUser = '" + maSinhVien + "'");
            super.statement.execute("update baithi set TrangThai = 0 where MaSV = '" + maSinhVien + "'");
            return !super.statement.execute("update sinhvien set TrangThai = 0 where MaSV = '" + maSinhVien + "'");
        } catch (SQLException ex) {
            Logger.getLogger(SinhVienDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean importExcel(String fileName) {
        return super.importExcel(fileName, sinhvien, "sinhvien");
    }

    public boolean exportExcel(String nameFile) {
        return super.exportExcel(getList(), sinhvien, nameFile);
    }

    //Lấy thông tin sinh viên theo bảng
    public Vector getThongTin(int trang) {
        Vector vtData = new Vector();
        String sql = "SELECT MaSV, Ho, Ten, NgaySinh, GioiTinh, TenLop, TenKhoa FROM sinhvien, lop, khoa WHERE sinhvien.TrangThai=1 AND sinhvien.MaLop=lop.MaLop AND lop.MaKhoa=khoa.MaKhoa LIMIT " + 25 * (trang - 1) + ",25";
        ResultSet rs = super.query(sql);
        try {
            while (rs.next()) {
                Vector vtRow = new Vector();
                vtRow.add(false);
                for (int i = 1; i < 8; i++) {
                    if (i != 4) {
                        vtRow.add(rs.getString(i));
                    } else {
                        vtRow.add(toDate(rs.getString(i)));
                    }
                }
                vtData.add(vtRow);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return vtData;
    }

    public int coutThongTin() {
        int cout = 0;
        String sql = "SELECT COUNT(*) FROM sinhvien WHERE TrangThai=1 ";
        ResultSet rs = super.query(sql);
        try {
            while (rs.next()) {
                cout = Integer.parseInt(rs.getString(1));
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return cout;
    }

    public boolean khoiphuc(String maSinhVien) {
        try {
            super.statement.execute("update user set TrangThai = 1 where MaUser = '" + maSinhVien + "'");
            super.statement.execute("update baithi set TrangThai = 1 where MaSV = '" + maSinhVien + "'");
            return !super.statement.execute("update sinhvien set TrangThai = 1 where MaSV = '" + maSinhVien + "'");
        } catch (SQLException ex) {
            Logger.getLogger(SinhVienDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean xoavinhvien(String maSinhVien) {
        try {
            return !super.statement.execute("DELETE FROM `sinhvien` WHERE MaSV='" + maSinhVien + "'");
        } catch (SQLException ex) {
            Logger.getLogger(SinhVienDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    //Lấy danh sách môn mà sinh viên học trong kỳ thi
    public ArrayList<MonDTO> getListMonKyThi(String idUser, String maKyThi) {
        ArrayList<MonDTO> getListMonKyThi = new ArrayList();
        String sql = "select * from monhoc, baithi, dethi where baithi.MaSV = '" + idUser + "' "
                + "and baithi.MaDe = dethi.MaDe and dethi.MaKyThi = '" + maKyThi + "' and dethi.MaMon = monhoc.MaMon " + "and monhoc.TrangThai = 1";
        ResultSet rs = super.query(sql);
        System.out.println(sql);
        try {
            while (rs.next()) {
                MonDTO mon = new MonDTO(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
                getListMonKyThi.add(mon);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MonSinhVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getListMonKyThi;
    }

    //Lấy dánh sách bài thi của sinh viên có mã idUser và mã kỳ thi là maKyThi
    public ArrayList<BaiThiDTO> getListBaiThiKyThi(String idUser, String maKyThi) {
        ArrayList<BaiThiDTO> getListBaiThiKyThi = new ArrayList();
        String sql = "select * from baithi, dethi where baithi.MaSV = '" + idUser + "' "
                + "and baithi.MaDe = dethi.MaDe and dethi.MaKyThi = '" + maKyThi + "'" + " and baithi.TrangThai = 1";
        ResultSet rs = super.query(sql);
        BaiThiDTO baithi = new BaiThiDTO();
        try {
            while (rs.next()) {
                baithi = new BaiThiDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getInt(7));
                getListBaiThiKyThi.add(baithi);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MonSinhVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getListBaiThiKyThi;
    }

    //Hàm lấy bài thi môn maMon
    public BaiThiDTO getListMonBaiThi(String idUser, String maMon) {
        BaiThiDTO baithi = new BaiThiDTO();
        String sql = "select * from baithi, dethi where baithi.MaSV = '" + idUser + "' and baithi.MaDe = dethi.MaDe and dethi.MaMon = '" + maMon + "' and baithi.TrangThai = 1 ";
        ResultSet rs = super.query(sql);
        try {
            while (rs.next()) {
                baithi = new BaiThiDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getInt(7));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SinhVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return baithi;
    }
}
