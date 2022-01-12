/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_EXAM;

import DTO_EXAM.MonDTO;
import DTO_EXAM.NhanVienDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class MonDAO extends BaseDAO {

    MonDTO mon = new MonDTO();

    public ArrayList<String> getListMon() {
        ArrayList<String> getListMon = new ArrayList();
        String sql = "SELECT TenMon FROM monhoc WHERE TrangThai=1";
        ResultSet rs = super.query(sql);
        try {
            while (rs.next()) {
                getListMon.add(rs.getString(1));
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return getListMon;
    }

    //Lấy thông tin sinh viên theo bảng
    public Vector getThongTin(int trang) {
        Vector vtData = new Vector();
        String sql = "SELECT MaMon, TenMon, SoTinChi FROM monhoc WHERE TrangThai=1 LIMIT " + 25 * (trang - 1) + ",25";
        ResultSet rs = super.query(sql);
        try {
            while (rs.next()) {
                Vector vtRow = new Vector();
                vtRow.add(false);
                for (int i = 1; i < 4; i++) {
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
        String sql = "SELECT COUNT(*) FROM monhoc WHERE TrangThai=1 ";
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

    public MonDTO getMon(String maMon) {
        return (MonDTO) super.getDoiTuong(maMon, "monhoc", mon);
    }

    public ArrayList getList() {
        return super.getArrayList(mon, "monhoc");
    }

    public boolean them(MonDTO mon) {
        return super.themDoiTuong(mon, "monhoc");
    }

    public boolean sua(MonDTO mon, String maMon) {
        return super.suaDoiTuong(mon, "monhoc", maMon);
    }

    public boolean xoa(String maMon) {
        try {
            super.statement.execute("update gv_mh set TrangThai = 0 where MaMon = '" + maMon + "'");
            return !super.statement.execute("update  monhoc set TrangThai = 0 where MaMon = '" + maMon + "'");
        } catch (SQLException ex) {
            Logger.getLogger(ChucNangDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean importExcel(String fileName) {
        return super.importExcel(fileName, mon, "monhoc");
    }

    public boolean exportExcel(String nameFile) {
        return super.exportExcel(getList(), mon, nameFile);
    }

    //Thanh Thêm
    public String getTenMon(String maMon) {
        String sql = "select TenMon from monhoc where MaMon = '" + maMon + "'";
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

    //Kết thúc
    //Q
    public boolean khoiphuc(String maMon) {
        try {
            super.statement.execute("update gv_mh set TrangThai = 1 where MaMon = '" + maMon + "'");
            return !super.statement.execute("update  monhoc set TrangThai = 1 where MaMon = '" + maMon + "'");
        } catch (SQLException ex) {
            Logger.getLogger(QuyenDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean xoavinhvien(String maMon) {
        try {
            return !super.statement.execute("DELETE FROM `monhoc` WHERE MaMon='" + maMon + "'");
        } catch (SQLException ex) {
            Logger.getLogger(QuyenDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
