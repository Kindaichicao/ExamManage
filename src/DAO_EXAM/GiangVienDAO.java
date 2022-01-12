/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_EXAM;

import DTO_EXAM.*;
import java.util.ArrayList;
import java.sql.*;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class GiangVienDAO extends BaseDAO {

    GiangVienDTO giangvien = new GiangVienDTO();

    public GiangVienDAO() {
        super();
    }
    //Danh sách chứa họ tên Giảng viên

    public ArrayList<String> getListGiangVien() {
        ArrayList<String> getListGiangVien = new ArrayList();
        String sql = "SELECT Ho, Ten FROM giangvien WHERE TrangThai=1";
        ResultSet rs = super.query(sql);
        try {
            while (rs.next()) {
                getListGiangVien.add(rs.getString(1) + " " + rs.getString(2));
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return getListGiangVien;
    }

    //Lấy thông tin giảng viên theo bảng
    public Vector getThongTin(int trang) {
        Vector vtData = new Vector();
        String sql = "SELECT MaGV, Ho, Ten, NgaySinh, GioiTinh, TenKhoa FROM giangvien, khoa WHERE giangvien.TrangThai=1 AND giangvien.MaKhoa=khoa.MaKhoa LIMIT " + 25 * (trang - 1) + ",25";
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
        String sql = "SELECT COUNT(*) FROM giangvien WHERE TrangThai=1 ";
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

    public GiangVienDTO getGiangVien(String maGiangVien) {
        return (GiangVienDTO) super.getDoiTuong(maGiangVien, "giangvien", giangvien);
    }

    public ArrayList<MonDTO> getMonQuanLy(String maGiangVien) {
        ArrayList getMonQuanLy = new ArrayList();
        String sql = "select * from monhoc, gv_mh where monhoc.MaMon = gv_mh.MaMon and gv_mh.MaGV= '" + maGiangVien + "'";
        ResultSet rs = super.query(sql);
        try {
            while (rs.next()) {
                MonDTO mon = new MonDTO(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
                getMonQuanLy.add(mon);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GiangVienDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return getMonQuanLy;
    }

    public ArrayList getList() {
        return super.getArrayList(giangvien, "giangvien");
    }

    public boolean them(GiangVienDTO giangvien) {
        return super.themDoiTuong(giangvien, "giangvien");
    }

    public boolean sua(GiangVienDTO giangvien, String maGiangVien) {
        return super.suaDoiTuong(giangvien, "giangvien", maGiangVien);
    }

    public boolean xoa(String maGiangVien) {
        try {
            super.statement.execute("update user set TrangThai = 0 where MaUser = '" + maGiangVien + "'");
            super.statement.execute("update gv_mh set TrangThai = 0 where MaGV = '" + maGiangVien + "'");
            return !super.statement.execute("update giangvien set TrangThai = 0  where MaGV = '" + maGiangVien + "'");
        } catch (SQLException ex) {
            Logger.getLogger(GiangVienDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean importExcel(String fileName) {
        return super.importExcel(fileName, giangvien, "giangvien");
    }

    public boolean exportExcel(String nameFile) {
        return super.exportExcel(getList(), giangvien, nameFile);
    }

    //Quan
    public boolean khoiphuc(String maGiangVien) {
        try {
            super.statement.execute("update user set TrangThai = 1 where MaUser = '" + maGiangVien + "'");
            super.statement.execute("update gv_mh set TrangThai = 1 where MaGV = '" + maGiangVien + "'");
            return !super.statement.execute("update giangvien set TrangThai = 1  where MaGV = '" + maGiangVien + "'");
        } catch (SQLException ex) {
            Logger.getLogger(GiangVienDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean xoavinhvien(String maGiangVien) {
        try {
            return !super.statement.execute("DELETE FROM `giangvien` WHERE MaGV='" + maGiangVien + "'");
        } catch (SQLException ex) {
            Logger.getLogger(SinhVienDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

}
