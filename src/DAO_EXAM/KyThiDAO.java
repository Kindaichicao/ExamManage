/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_EXAM;

import DTO_EXAM.KyThiDTO;
import DTO_EXAM.LopDTO;
import DTO_EXAM.MonDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class KyThiDAO extends BaseDAO {

    KyThiDTO kythi = new KyThiDTO();

    public ArrayList<String> getListKyThi() {
        ArrayList<String> getListKyThi = new ArrayList();
        String sql = "SELECT TenKyThi FROM kythi";
        ResultSet rs = super.query(sql);
        try {
            while (rs.next()) {
                getListKyThi.add(rs.getString(1));
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return getListKyThi;
    }

    //Lấy thông tin sinh viên theo bảng
    public Vector getThongTin(int trang) {
        Vector vtData = new Vector();
        String sql = "SELECT MaKyThi, TenKyThi, NgayBatDau, NgayKetThuc FROM kythi WHERE TrangThai=1 LIMIT " + 25 * (trang - 1) + ",25";
        ResultSet rs = super.query(sql);
        try {
            while (rs.next()) {
                Vector vtRow = new Vector();
                vtRow.add(false);
                for (int i = 1; i < 5; i++) {
                    if (i != 3 && i != 4) {
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
        String sql = "SELECT COUNT(*) FROM kythi WHERE TrangThai=1 ";
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

    public KyThiDTO getKyThi(String maKyThi) {
        return (KyThiDTO) super.getDoiTuong(maKyThi, "kythi", kythi);
    }

    public ArrayList getList() {
        return super.getArrayList(kythi, "kythi");
    }

    public boolean them(KyThiDTO kythi) {
        return super.themDoiTuong(kythi, "kythi");
    }

    public boolean sua(KyThiDTO kythi, String maKyThi) {
        return super.suaDoiTuong(kythi, "kythi", maKyThi);
    }

    /* Khóa
    public boolean xoa()
     */
    public boolean importExcel(String fileName) {
        return super.importExcel(fileName, kythi, "kythi");
    }

    public boolean exportExcel(String nameFile) {
        return super.exportExcel(getList(), kythi, nameFile);
    }

    // Phần Thanh Thêm
    public KyThiDTO getKyThiHienTai() {
        String sql = "select * from kythi where TrangThai = 1";
        KyThiDTO kyThi = new KyThiDTO();
        ResultSet rs = super.query(sql);
        try {
            if (rs.next()) {
                kyThi = new KyThiDTO(rs.getString("MaKyThi"), rs.getString("TenKyThi"),
                        rs.getString("NgayBatDau"), rs.getString("NgayKetThuc"), rs.getInt("TrangThai"));
            }
        } catch (Exception e) {
            System.err.println(e);
        }

        return kyThi;
    }

    public String getTenKyThi(String maKyThi) {
        String sql = "select TenKyThi from kythi where MaKyThi = '" + maKyThi + "'";
        ResultSet rs = super.query(sql);
        try {
            if (rs.next()) {
                return rs.getString("TenKyThi");
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
        return null;
    }

    //Kêt Thúc Phần Thanh Thêm
    //quan
    public boolean xoavinhvien(String maKT) {
        try {
            return !super.statement.execute("DELETE FROM `kythi` WHERE MaKyThi='" + maKT + "'");
        } catch (SQLException ex) {
            Logger.getLogger(SinhVienDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public ArrayList<KyThiDTO> getAllList() {
        ArrayList getList = new ArrayList();
        String sql = "select * from kythi where 1";
        ResultSet rs = super.query(sql);
        try {
            while (rs.next()) {
                KyThiDTO kythi = new KyThiDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
                getList.add(kythi);
            }
        } catch (SQLException ex) {
            Logger.getLogger(KyThiDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return getList;
    }

}
