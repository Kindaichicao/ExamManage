/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_EXAM;

import DTO_EXAM.DeThiDTO;
import DTO_EXAM.GiangVienDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class DeThiDAO extends BaseDAO {

    DeThiDTO dethi = new DeThiDTO();

    public DeThiDTO getDeThi(String maDe) {
        return (DeThiDTO) super.getDoiTuong(maDe, "dethi", dethi);
    }

    public ArrayList getList() {
        return super.getArrayList(dethi, "dethi");
    }

    public boolean them(DeThiDTO dethi) {
        return super.themDoiTuong(dethi, "dethi");
    }

    public boolean sua(DeThiDTO dethi, String maDe) {
        return super.suaDoiTuong(dethi, "dethi", maDe);
    }

    public boolean xoa(String maDe) {
        try {
            super.statement.execute("update de_cauhoi set TrangThai = 0 where MaDe = '" + maDe + "'");
            super.statement.execute("update baithi set TrangThai = 0 where MaDe = '" + maDe + "'");
            return !super.statement.execute("update dethi set TrangThai = 0  where MaDe = '" + maDe + "'");
        } catch (SQLException ex) {
            Logger.getLogger(GiangVienDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean importExcel(String fileName) {
        return super.importExcel(fileName, dethi, "dethi");
    }

    public boolean exportExcel(String nameFile) {
        return super.exportExcel(getList(), dethi, nameFile);
    }

    //Phần Thanh thêm
    public DeThiDTO getDeMaMonKyThi(String maMon, String maKyThi) {
        DeThiDTO deThi = null;
        String sql = "SELECT * FROM `dethi` WHERE MaMon = '" + maMon + "'";
        ResultSet rs = query(sql);
        try {
            while (rs.next()) {
                if (rs.getString("MaKyThi").equals(maKyThi)) {
                    deThi = new DeThiDTO(rs.getString("MaDe"), rs.getString("MaMon"), rs.getString("MaKyThi"),
                            rs.getInt("ThoiGianLamBai"), rs.getString("NgayThi"), rs.getString("GioThi"), rs.getInt("SoLuongCauHoi"),
                            rs.getInt("De"), rs.getInt("TrungBinh"), rs.getInt("Kho"), rs.getInt("TrangThai"));
                }
            }
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }
        return deThi;
    }

    public String getMaMon(String maDe) {
        String sql = "Select MaMon From dethi where MaDe = '" + maDe + "'";
        ResultSet rs = super.query(sql);
        try {
            if (rs.next()) {
                return rs.getString("MaMon");
            }
        } catch (Exception e) {
        }
        return null;
    }

    //Kết Thúc phần Thanh Thêm
    //Quaan
    public boolean khoiphuc(String maDe) {
        try {
            super.statement.execute("update de_cauhoi set TrangThai = 1 where MaDe = '" + maDe + "'");
            super.statement.execute("update baithi set TrangThai = 1 where MaDe = '" + maDe + "'");
            return !super.statement.execute("update dethi set TrangThai = 1  where MaDe = '" + maDe + "'");
        } catch (SQLException ex) {
            Logger.getLogger(GiangVienDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean xoavinhvien(String maDe) {
        try {
            return !super.statement.execute("DELETE FROM `dethi` WHERE MaDe='" + maDe + "'");
        } catch (SQLException ex) {
            Logger.getLogger(GiangVienDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public Vector getThongTin(int trang) {
        Vector vtData = new Vector();
        String sql = "SELECT MaDe, TenMon, TenKyThi, ThoiGianLamBai, NgayThi, GioThi, SoLuongCauHoi FROM dethi, monhoc, kythi WHERE dethi.TrangThai=1 AND dethi.MaMon=monhoc.MaMon AND dethi.MaKyThi=kythi.MaKyThi LIMIT " + 25 * (trang - 1) + ",25";
        ResultSet rs = super.query(sql);
        try {
            while (rs.next()) {
                Vector vtRow = new Vector();
                vtRow.add(false);
                for (int i = 1; i < 8; i++) {
                    if (i != 5) {
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
        String sql = "SELECT COUNT(*) FROM dethi WHERE TrangThai=1 ";
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
