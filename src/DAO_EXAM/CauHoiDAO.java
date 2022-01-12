/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_EXAM;

import DTO_EXAM.*;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author AQ
 */
public class CauHoiDAO extends BaseDAO {

    public CauHoiDAO() {
        super();
    }

    public ArrayList<String> getListA() {
        ArrayList<String> getListA = new ArrayList();
        String sql = "SELECT A FROM cauhoi WHERE TrangThai=1";
        ResultSet rs = super.query(sql);
        try {
            while (rs.next()) {
                getListA.add(rs.getString(1));
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return getListA;
    }

    public ArrayList<String> getListB() {
        ArrayList<String> getListB = new ArrayList();
        String sql = "SELECT B FROM cauhoi WHERE TrangThai=1";
        ResultSet rs = super.query(sql);
        try {
            while (rs.next()) {
                getListB.add(rs.getString(1));
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return getListB;
    }

    public ArrayList<String> getListC() {
        ArrayList<String> getListC = new ArrayList();
        String sql = "SELECT C FROM cauhoi WHERE TrangThai=1";
        ResultSet rs = super.query(sql);
        try {
            while (rs.next()) {
                getListC.add(rs.getString(1));
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return getListC;
    }

    public ArrayList<String> getListD() {
        ArrayList<String> getListD = new ArrayList();
        String sql = "SELECT D FROM cauhoi WHERE TrangThai=1";
        ResultSet rs = super.query(sql);
        try {
            while (rs.next()) {
                getListD.add(rs.getString(1));
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return getListD;
    }

    public ArrayList<String> getListND() {
        ArrayList<String> getListND = new ArrayList();
        String sql = "SELECT NoiDung FROM cauhoi WHERE TrangThai=1";
        ResultSet rs = super.query(sql);
        try {
            while (rs.next()) {
                getListND.add(rs.getString(1));
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return getListND;
    }

    /**
     * Phần của Thắng :v
     *
     */
    CauHoiDTO cauhoi = new CauHoiDTO();

    public CauHoiDTO getCauHoi(String maCH) {
        return (CauHoiDTO) super.getDoiTuong(maCH, "cauhoi", cauhoi);
    }

    public ArrayList getList() {
        return super.getArrayList(cauhoi, "cauhoi");
    }

    public boolean them(CauHoiDTO cauhoi) {
        return super.themDoiTuong(cauhoi, "cauhoi");
    }

    public boolean sua(CauHoiDTO cauhoi, String maCH) {
        return super.suaDoiTuong(cauhoi, "cauhoi", maCH);
    }

    public boolean xoa(String maCH) {
        try {
            super.statement.execute("update de_cauhoi set TrangThai = 0 where MaCH = '" + maCH + "'");
            return !super.statement.execute("update cauhoi set TrangThai = 0 where MaCH = '" + maCH + "'");
        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean importExcel(String fileName) {
        return super.importExcel(fileName, cauhoi, "cauhoi");
    }

    public boolean exportExcel(String nameFile) {
        return super.exportExcel(getList(), cauhoi, nameFile);
    }

    public ArrayList<CauHoiDTO> getListCauHoiMon(ArrayList<GiangVienMonDTO> gVMs) {
        ArrayList<CauHoiDTO> cauHois = new ArrayList<>();
        String params = "";
        params += "MaPhanCong = '" + gVMs.get(0).getMaPhanCong() + "'";
        gVMs.remove(0);
        for (GiangVienMonDTO gvm : gVMs) {
            params += " or MaPhanCong = '" + gvm.getMaPhanCong() + "'";
        }
        String sql = "select * from cauhoi where " + params;
        ResultSet rs = super.query(sql);
        try {
            while (rs.next()) {
                CauHoiDTO ch = new CauHoiDTO(rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6),
                        rs.getString(7), rs.getString(8), rs.getInt(9), rs.getInt(10));
                cauHois.add(ch);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return cauHois;
    }

    public ArrayList<CauHoiDTO> getListCauHoiMonDoKho(ArrayList<GiangVienMonDTO> gVMs, int dokho) {
        ArrayList<CauHoiDTO> cauHois = null;
        String params = "";
        params += "(MaPhanCong = '" + gVMs.get(0).getMaPhanCong() + "'";
        for (int i = 1; i < gVMs.size(); i++) {
            params += " or MaPhanCong = '" + gVMs.get(i).getMaPhanCong() + "'";
        }

        String sql = "select * from cauhoi where " + params + ")and DoKho = " + dokho;
        System.out.println(sql);
        ResultSet rs = super.query(sql);
        try {
            while (rs.next()) {
                CauHoiDTO ch = new CauHoiDTO(rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6),
                        rs.getString(7), rs.getString(8), rs.getInt(9), rs.getInt(10));
                cauHois.add(ch);
            }
        } catch (Exception e) {
//            System.out.println(e);
            return null;
        }
        return cauHois;
    }

    //Quân
    public boolean khoiphuc(String maCH) {
        try {
            super.statement.execute("update de_cauhoi set TrangThai = 1 where MaCH = '" + maCH + "'");
            return !super.statement.execute("update cauhoi set TrangThai = 1 where MaCH = '" + maCH + "'");
        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean xoavinhvien(String maCH) {
        try {
            return !super.statement.execute("DELETE FROM `cauhoi` WHERE MaCH='" + maCH + "'");
        } catch (SQLException ex) {
            return false;
        }
    }

    public Vector getThongTin(String[] maMon, int trang) {
        Vector vtData = new Vector();
        String sq;
        if (maMon.length > 0) {
            sq = "monhoc.MaMon = '" + maMon[0] + "' ";
            for (int i = 1; i < maMon.length; i++) {
                sq = sq + "OR monhoc.MaMon = '" + maMon[i] + "' ";
            }
        } else {
            return vtData;
        }
        String sql = "SELECT MaCH, NoiDung, A, B, C, D, DapAn, DoKho , TenMon FROM cauhoi, gv_mh, monhoc WHERE cauhoi.TrangThai=1 AND gv_mh.TrangThai=1 AND monhoc.TrangThai=1 AND cauhoi.MaPhanCong=gv_mh.MaPhanCong AND gv_mh.MaMon = monhoc.MaMon AND ( " + sq + ") ORDER BY MaCH LIMIT " + 25 * (trang - 1) + ",25";

        ResultSet rs = super.query(sql);
        try {
            while (rs.next()) {
                Vector vtRow = new Vector();
                vtRow.add(false);
                for (int i = 1; i < 10; i++) {
                    vtRow.add(rs.getString(i));
                }
                vtData.add(vtRow);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return vtData;
    }

    public Vector getThongTin(int trang) {
        Vector vtData = new Vector();
        String sql = "SELECT MaCH, NoiDung, A, B, C, D, DapAn, DoKho , TenMon FROM cauhoi, gv_mh, monhoc WHERE cauhoi.TrangThai=1 AND gv_mh.TrangThai=1 AND monhoc.TrangThai=1 AND cauhoi.MaPhanCong=gv_mh.MaPhanCong AND gv_mh.MaMon = monhoc.MaMon ORDER BY MaCH LIMIT " + 25 * (trang - 1) + ",25";
        ResultSet rs = super.query(sql);
        try {
            while (rs.next()) {
                Vector vtRow = new Vector();
                vtRow.add(false);
                for (int i = 1; i < 10; i++) {
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
        String sql = "SELECT COUNT(*) FROM cauhoi WHERE TrangThai=1 ";
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

    public int coutThongTin(String[] maMon) {
        int cout = 0;
        String sq;
        if (maMon.length > 0) {
            sq = "monhoc.MaMon = '" + maMon[0] + "' ";
            for (int i = 1; i < maMon.length; i++) {
                sq = sq + "OR monhoc.MaMon = '" + maMon[i] + "' ";
            }
        } else {
            return cout;
        }
        String sql = "SELECT COUNT(*) FROM cauhoi, gv_mh, monhoc WHERE cauhoi.TrangThai=1 AND cauhoi.MaPhanCong=gv_mh.MaPhanCong AND gv_mh.MaMon = monhoc.MaMon AND ( " + sq + ") ";
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
