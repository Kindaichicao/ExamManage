/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_EXAM;

import DTO_EXAM.GiangVienMonDTO;
import DTO_EXAM.KhoaDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;

/**
 *
 * @author Admin
 */
public class GiangVienMonDAO extends BaseDAO {

    GiangVienMonDTO giangvienmon = new GiangVienMonDTO();

    public GiangVienMonDTO getPhanCong(String maPhanCong) {
        return (GiangVienMonDTO) super.getDoiTuong(maPhanCong, "gv_mh", giangvienmon);
    }

    public ArrayList getList() {
        return super.getArrayList(giangvienmon, "gv_mh");
    }

    public boolean them(GiangVienMonDTO giangvienmon) {
        return super.themDoiTuong(giangvienmon, "gv_mh");
    }

    public boolean sua(GiangVienMonDTO giangvienmon, String maPhanCong) {
        return super.suaDoiTuong(giangvienmon, "gv_mh", maPhanCong);
    }

    public boolean xoa(String maPhanCong) {
        try {
            return !super.statement.execute("update gv_mh set TrangThai = 0 where MaPhanCong = '" + maPhanCong + "'");
        } catch (SQLException ex) {
            Logger.getLogger(GiangVienMonDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean importExcel(String fileName) {
        return super.importExcel(fileName, giangvienmon, "gv_mh");
    }

    public boolean exportExcel(String nameFile) {
        return super.exportExcel(getList(), giangvienmon, nameFile);
    }

    public ArrayList<GiangVienMonDTO> getListChungMon(String maMon) {
        ArrayList<GiangVienMonDTO> monChung = new ArrayList<GiangVienMonDTO>();
        String sql = "select * from gv_mh where MaMon = '" + maMon + "'";
        System.out.println(sql);
        ResultSet rs = super.query(sql);
        try {
            while (rs.next()) {
                GiangVienMonDTO mon = new GiangVienMonDTO(rs.getString("MaPhanCong"), rs.getString("MaGV"), rs.getString("MaMon"), rs.getInt("TrangThai"));
                monChung.add(mon);
            }
        } catch (Exception e) {
            System.out.println("DAO_EXAM.GiangVienMonDAO.getListChungMon()");
            return null;
        }
        return monChung;
    }

    public String getMa(String maGV, String maMon) {
        String ma = "";
        String sql = "SELECT MaPhanCong FROM gv_mh WHERE TrangThai=1 AND MaGV='" + maGV + "' AND MaMon = '" + maMon + "'";
        ResultSet rs = super.query(sql);
        try {
            while (rs.next()) {
                ma = rs.getString(1);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return ma;
    }
}
