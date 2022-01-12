/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_EXAM;

import DTO_EXAM.ChucNangDTO;
import java.util.ArrayList;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class ChucNangDAO extends BaseDAO {

    ChucNangDTO quyen = new ChucNangDTO();

    public ChucNangDAO() {
        super();
    }

    public String getTenBang(String sqladd) {
        String tenbang = new String();
        String sql = "select TenBang from `chucnang` where MaQuyen = '" + sqladd + "'";
        ResultSet rs = super.query(sql);
        try {
            while (rs.next()) {
                tenbang = rs.getString(1);
            }
        } catch (Exception e) {
            System.err.println(e);
        }

        return tenbang;
    }

    public String getHanhDong(String sqladd) {
        String hanhdong = null;
        String sql = "select HanhDong from `chucnang` where MaQuyen = '" + sqladd + "'";
        ResultSet rs = super.query(sql);
        try {
            while (rs.next()) {
                hanhdong = rs.getString(1);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return hanhdong;
    }

    public ChucNangDTO getQuyen(String maQuyen) {
        return (ChucNangDTO) getDoiTuong(maQuyen, "chucnang", quyen);
    }

    public ArrayList getList() {
        return super.getArrayList(quyen, "chucnang");
    }

    public boolean them(ChucNangDTO quyen) {
        return super.themDoiTuong(quyen, "chucnang");
    }

    public boolean sua(ChucNangDTO quyen, String maQuyen) {
        return super.suaDoiTuong(quyen, "chucnang", maQuyen);
    }

    public boolean xoa(String maQuyen) {
        try {
            statement.execute("update chitietquen set TrangThai = 0 where MaQuyen = '" + maQuyen + "'");
            return !super.statement.execute("update chucnang set TrangThai = 0 where MaQuyen = '" + maQuyen + "'");
        } catch (SQLException ex) {
            Logger.getLogger(ChucNangDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean importExcel(String fileName) {
        return super.importExcel(fileName, quyen, "chucnang");
    }

    public boolean exportExcel(String nameFile) {
        return super.exportExcel(getList(), quyen, nameFile);
    }
}
