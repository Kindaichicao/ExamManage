/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_EXAM;

import DTO_EXAM.KhoaDTO;
import DTO_EXAM.KyThiDTO;
import DTO_EXAM.MonDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public final class KhoaDAO extends BaseDAO {

    KhoaDTO khoa = new KhoaDTO();

    public KhoaDAO() {
        super();
    }

    public String getTenKhoa(String makhoa) {
        String sql = "select TenKhoa from khoa where MaKhoa = '" + makhoa + "'";
        ResultSet rs = super.query(sql);

        try {
            if (rs.next()) {
                return rs.getNString("TenKhoa");
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return "";
    }

    public String getMaKhoa(String maLop) {
        String sql = "select MaKhoa from lop where MaLop = '" + maLop + "'";
        ResultSet rs = super.query(sql);

        try {
            if (rs.next()) {
                return rs.getNString("MaKhoa");
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return "";
    }

    public KhoaDTO getKhoa(String maKhoa) {
        return (KhoaDTO) super.getDoiTuong(maKhoa, "khoa", khoa);
    }

    public ArrayList getList() {
        return super.getArrayList(khoa, "khoa");
    }

    public boolean them(KhoaDTO khoa) {
        return super.themDoiTuong(khoa, "khoa");
    }

    public boolean sua(KhoaDTO khoa, String maKhoa) {
        return super.suaDoiTuong(khoa, "khoa", maKhoa);
    }

    public boolean importExcel(String fileName) {
        return super.importExcel(fileName, khoa, "khoa");
    }

    public boolean exportExcel(String nameFile) {
        return super.exportExcel(getList(), khoa, nameFile);
    }

    //Quân
    public ArrayList<String> getListKhoa() {
        ArrayList<String> getListKhoa = new ArrayList();
        String sql = "SELECT TenKhoa FROM khoa WHERE TrangThai=1 AND MaKhoa != 'K000'";
        ResultSet rs = super.query(sql);
        try {
            while (rs.next()) {
                getListKhoa.add(rs.getString(1));
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return getListKhoa;
    }

    public boolean xoa(String maKhoa) {
        try {
            super.statement.execute("update lop set MaKhoa = 'K000' where MaKhoa = '" + maKhoa + "'");
            super.statement.execute("update nhanvien set MaKhoa = 'K000' where MaKhoa = '" + maKhoa + "'");
            super.statement.execute("update giangvien set MaKhoa = 'K000' where MaKhoa = '" + maKhoa + "'");
            return !super.statement.execute("update khoa set TrangThai = 0 where MaKhoa = '" + maKhoa + "'");
        } catch (SQLException ex) {
            Logger.getLogger(KhoaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean khoiphuc(String maKhoa) {
        try {
            super.statement.execute("update lop set MaKhoa = '" + maKhoa + "' where MaKhoa = 'K000'");
            super.statement.execute("update nhanvien set MaKhoa ='" + maKhoa + "' where MaKhoa = 'K000'");
            super.statement.execute("update giangvien set MaKhoa = '" + maKhoa + "' where MaKhoa = 'K000'");
            return !super.statement.execute("update khoa set TrangThai = 1 where MaKhoa = '" + maKhoa + "'");
        } catch (SQLException ex) {
            Logger.getLogger(KhoaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean xoavinhvien(String maKhoa) {
        try {
            return !super.statement.execute("DELETE FROM `khoa` WHERE MaKhoa='" + maKhoa + "'");
        } catch (SQLException ex) {
            Logger.getLogger(KhoaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    //Lấy thông tin sinh viên theo bảng
    public Vector getThongTin(int trang) {
        Vector vtData = new Vector();
        String sql = "SELECT MaKhoa, TenKhoa FROM khoa WHERE TrangThai=1 LIMIT " + 25 * (trang - 1) + ",25";
        ResultSet rs = super.query(sql);
        try {
            while (rs.next()) {
                Vector vtRow = new Vector();
                vtRow.add(false);
                for (int i = 1; i < 3; i++) {
                    vtRow.add(rs.getString(i));
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
        String sql = "SELECT COUNT(*) FROM khoa WHERE TrangThai=1 ";
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
}
