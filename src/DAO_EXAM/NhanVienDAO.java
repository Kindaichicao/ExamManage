/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_EXAM;

import DTO_EXAM.NhanVienDTO;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author ASUS
 */
public class NhanVienDAO extends BaseDAO {

    public NhanVienDAO() {
        super();
    }

    NhanVienDTO nhanvien = new NhanVienDTO();

    public NhanVienDTO getNhanVien(String maNhanVien) {
        return (NhanVienDTO) super.getDoiTuong(maNhanVien, "nhanvien", nhanvien);
    }

    public ArrayList getList() {
        return super.getArrayList(nhanvien, "nhanvien");
    }

    public boolean them(NhanVienDTO nhanvien) {
        return super.themDoiTuong(nhanvien, "nhanvien");
    }

    public boolean sua(NhanVienDTO nhanvien, String maNhanVien) {
        return super.suaDoiTuong(nhanvien, "nhanvien", maNhanVien);
    }

    public boolean xoa(String maNhanVien) {
        try {
            super.statement.execute("update user set TrangThai = 0 where MaUser = '" + maNhanVien + "'");
            return !super.statement.execute("update  nhanvien set TrangThai = 0 where MaNhanVien = '" + maNhanVien + "'");
        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean importExcel(String fileName) {
        return super.importExcel(fileName, nhanvien, "nhanvien");
    }

    public boolean exportExcel(String nameFile) {
        return super.exportExcel(getList(), nhanvien, nameFile);
    }

    public Vector getThongTin(int trang) {
        Vector vtData = new Vector();
        String sql = "SELECT MaNV, Ho, Ten, NgaySinh, GioiTinh, TenKhoa FROM nhanvien, khoa WHERE nhanvien.TrangThai=1 AND nhanvien.MaKhoa=khoa.MaKhoa LIMIT " + 25 * (trang - 1) + ",25";
        ResultSet rs = super.query(sql);
        try {
            while (rs.next()) {
                Vector vtRow = new Vector();
                vtRow.add(false);
                for (int i = 1; i < 7; i++) {
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
        String sql = "SELECT COUNT(*) FROM nhanvien WHERE TrangThai=1 ";
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

    public boolean khoiphuc(String maNhanVien) {
        try {
            super.statement.execute("update user set TrangThai = 1 where MaNV = '" + maNhanVien + "'");
            return !super.statement.execute("update nhanvien set TrangThai = 1  where MaNV = '" + maNhanVien + "'");
        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean xoavinhvien(String maNhanVien) {
        try {
            return !super.statement.execute("DELETE FROM `nhanvien` WHERE MaNV='" + maNhanVien + "'");
        } catch (SQLException ex) {
            return false;
        }
    }

}
