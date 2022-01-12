/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_EXAM;

import DTO_EXAM.ChiTietQuyenDTO;
import DTO_EXAM.ChucNangDTO;
import java.util.ArrayList;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class ChiTietQuyenDAO extends BaseDAO {

    ChiTietQuyenDTO quyenchucvu = new ChiTietQuyenDTO();

    public ChiTietQuyenDAO() {
        super();
    }

    public ArrayList<ChiTietQuyenDTO> getQuyenChucVuList() {
        ArrayList<ChiTietQuyenDTO> quyens = new ArrayList<ChiTietQuyenDTO>();
        String sql = "select * from `chitietquyen` where TrangThai = 1";
        ResultSet rs = super.query(sql);
        try {
            while (rs.next()) {
                ChiTietQuyenDTO quyen = new ChiTietQuyenDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4));
                quyens.add(quyen);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return quyens;
    }

    public ArrayList<ChiTietQuyenDTO> getQuyenChucVuList(String sqladd) {
        ArrayList<ChiTietQuyenDTO> quyens = new ArrayList<ChiTietQuyenDTO>();
        String sql = "select * from `chitietquyen` where " + sqladd;
        ResultSet rs = super.query(sql);
        try {
            while (rs.next()) {
                ChiTietQuyenDTO quyen = new ChiTietQuyenDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4));
                quyens.add(quyen);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return quyens;
    }

    public ChiTietQuyenDTO getQuyenChucVu(String maPhanQuyen) {
        return (ChiTietQuyenDTO) super.getDoiTuong(maPhanQuyen, "chitietquyen", quyenchucvu);
    }

    public ArrayList getList() {
        return super.getArrayList(quyenchucvu, "chitietquyen");
    }

    public boolean them(ChiTietQuyenDTO quyenchucvu) {
        return super.themDoiTuong(quyenchucvu, "chitietquyen");
    }

    public boolean sua(ChiTietQuyenDTO quyenchucvu, String maPhanQuyen) {
        return super.suaDoiTuong(quyenchucvu, "chitietquyen", maPhanQuyen);
    }

    public boolean xoa(String maPhanQuyen) {
        try {
            return !super.statement.execute("update chitietquyen set TrangThai = 0 where MaPhanQuyen = '" + maPhanQuyen + "'");
        } catch (SQLException ex) {
            Logger.getLogger(ChucNangDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean importExcel(String fileName) {
        return super.importExcel(fileName, quyenchucvu, "chitietquyen");
    }

    public boolean exportExcel(String nameFile) {
        return super.exportExcel(getList(), quyenchucvu, nameFile);
    }
    //Thanh Them

    public ArrayList<Integer> getListTrangThai(String maQuyen) {
        ArrayList<Integer> tts = new ArrayList<>();
        String sql = "SELECT TrangThai FROM `chitietquyen` WHERE MaChucVu = '" + maQuyen + "'";
        ResultSet rs = query(sql);
        try {
            while (rs.next()) {
                tts.add(rs.getInt("TrangThai"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return tts;
    }

    public ArrayList<ChiTietQuyenDTO> getListCT(String maQuyen) {
        ArrayList<ChiTietQuyenDTO> tts = new ArrayList<>();
        String sql = "SELECT * FROM `chitietquyen` WHERE MaChucVu = '" + maQuyen + "'";
        System.out.println(sql);
        ResultSet rs = query(sql);
        try {
            while (rs.next()) {
                ChiTietQuyenDTO ct = new ChiTietQuyenDTO(rs.getString("MaPhanQuyen"), rs.getString("MaChucVu"),
                        rs.getString("MaQuyen"), rs.getInt("TrangThai"));
                tts.add(ct);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return tts;
    }
}
