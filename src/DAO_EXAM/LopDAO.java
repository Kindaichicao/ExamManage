/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_EXAM;

import DTO_EXAM.LopDTO;
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
public class LopDAO extends BaseDAO {

    LopDTO lop = new LopDTO();

    public LopDAO() {
        super();
    }

    public ArrayList<String> getListLop(String khoa) {
        ArrayList<String> getListLop = new ArrayList();
        String sql = "SELECT TenLop FROM lop, khoa WHERE khoa.MaKhoa=lop.MaKhoa AND lop.TrangThai='1' AND TenKhoa='" + khoa + "'";

        ResultSet rs = super.query(sql);
        try {
            while (rs.next()) {
                getListLop.add(rs.getString(1));
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return getListLop;
    }

    public String getMaKhoa(String malop) {
        String sql = "select MaKhoa from lop where MaLop = '" + malop + "'";
        ResultSet rs = super.query(sql);

        try {
            if (rs.next()) {
                return rs.getNString(1);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return "";
    }

    public String getTenLop(String malop) {
        String sql = "select TenLop from lop where MaLop = '" + malop + "'";
        ResultSet rs = super.query(sql);

        try {
            if (rs.next()) {
                return rs.getNString(1);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return "";
    }

    public LopDTO getLop(String maLop) {
        return (LopDTO) super.getDoiTuong(maLop, "lop", lop);
    }

    public ArrayList getList() {
        return super.getArrayList(lop, "lop");
    }

    public boolean them(LopDTO lop) {
        return super.themDoiTuong(lop, "lop");
    }

    public boolean sua(LopDTO lop, String maLop) {
        return super.suaDoiTuong(lop, "lop", maLop);
    }

    public boolean xoa(String maLop, String maLopMoi) {
        try {
            super.statement.execute("update sinhvien set MaLop = '" + maLopMoi + "' where MaLop = '" + maLop + "'");
            return !super.statement.execute("update  lop set TrangThai = 0 where MaLop = '" + maLop + "'");
        } catch (SQLException ex) {
            Logger.getLogger(ChucNangDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean importExcel(String fileName) {
        return super.importExcel(fileName, lop, "lop");
    }

    public boolean exportExcel(String nameFile) {
        return super.exportExcel(getList(), lop, nameFile);
    }

    //Quân thêm
    public Vector getThongTin(int trang) {
        Vector vtData = new Vector();
        String sql = "SELECT MaLop, TenLop, TenKhoa, Ho, Ten FROM giangvien, lop, khoa WHERE lop.TrangThai=1 AND lop.MaCoVan=giangvien.MaGV AND lop.MaKhoa=khoa.MaKhoa LIMIT " + 25 * (trang - 1) + ",25";
        ResultSet rs = super.query(sql);
        try {
            while (rs.next()) {
                Vector vtRow = new Vector();
                vtRow.add(false);
                for (int i = 1; i < 4; i++) {
                    vtRow.add(rs.getString(i));
                }
                vtRow.add(rs.getString(4) + " " + rs.getString(5));
                vtData.add(vtRow);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return vtData;
    }

    public int coutThongTin() {
        int cout = 0;
        String sql = "SELECT COUNT(*) FROM lop WHERE TrangThai=1 ";
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

    public boolean khoiphuc(String maLop) {
        try {
            super.statement.execute("update sinhvien set MaLop = '" + maLop + "' where MaLop = 'L000'");
            return !super.statement.execute("update  lop set TrangThai = 1 where MaLop = '" + maLop + "'");
        } catch (SQLException ex) {
            Logger.getLogger(QuyenDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean xoavinhvien(String maLop) {
        try {
            return !super.statement.execute("DELETE FROM `lop` WHERE MaLop='" + maLop + "'");
        } catch (SQLException ex) {
            Logger.getLogger(LopDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
