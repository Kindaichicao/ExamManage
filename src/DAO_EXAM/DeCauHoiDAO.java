
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_EXAM;

import DTO_EXAM.DeCauHoiDTO;
import DTO_EXAM.GiangVienDTO;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class DeCauHoiDAO extends BaseDAO {

    DeCauHoiDTO decauhoi = new DeCauHoiDTO();

    public DeCauHoiDTO getDeCauHoi(String maCH, String maDe) {
        String sql = "select * from de_cauhoi where MaCH = '" + maCH + "' MaDe = '" + maDe + "'";
        ResultSet rs = super.query(sql);
        DeCauHoiDTO decauhoi = new DeCauHoiDTO();
        try {
            if (rs.next()) {
                decauhoi = new DeCauHoiDTO(rs.getString(1), rs.getString(2), rs.getInt(3));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DeCauHoiDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return decauhoi;
    }

    public ArrayList getDeCauHoiList(String maDe) {
        ArrayList<DeCauHoiDTO> dchs = new ArrayList<DeCauHoiDTO>();
        String sql = "select * from de_cauhoi where MaDe ='" + maDe + "'";
        ResultSet rs = super.query(sql);
        try {
            while (rs.next()) {
                DeCauHoiDTO decauhoi = new DeCauHoiDTO();
                decauhoi = new DeCauHoiDTO(rs.getString(1), rs.getString(2), rs.getInt(3));
                dchs.add(decauhoi);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DeCauHoiDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dchs;
    }

    public ArrayList getList() {
        return super.getArrayList(decauhoi, "de_cauhoi");
    }

    public boolean them(DeCauHoiDTO decauhoi) {
        return super.themDoiTuong(decauhoi, "de_cauhoi");
    }

    public boolean sua(DeCauHoiDTO decauhoi, String maCH, String maDe) {
        try {
            return !super.statement.execute("update de_cauhoi set maCH = '" + decauhoi.getMaCauHoi() + "', maDe = '" + decauhoi.getMaDe() + "' where MaCH = '" + maCH + "' and MaDe= '" + maDe + "'");
        } catch (SQLException ex) {
            Logger.getLogger(DeCauHoiDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean xoa(String maCH, String maDe) {
        try {
            return super.statement.execute("update de_cauhoi set TrangThai = 0  where MaCH = '" + maCH + "' and MaDe = '" + maDe + "'");
        } catch (SQLException ex) {
            Logger.getLogger(GiangVienDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean importExcel(String fileName) {
        return super.importExcel(fileName, decauhoi, "de_cauhoi");
    }

    public boolean exportExcel(String nameFile) {
        return super.exportExcel(getList(), decauhoi, nameFile);
    }

}
