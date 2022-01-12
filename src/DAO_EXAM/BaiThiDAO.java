/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_EXAM;

import DTO_EXAM.BaiThiDTO;
import DTO_EXAM.CauHoiDTO;
import DTO_EXAM.DeCauHoiDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author Admin
 */
public class BaiThiDAO extends BaseDAO {

    BaiThiDTO baithi = new BaiThiDTO();

    public Vector getThongTin(int trang) {
        Vector vtData = new Vector();
        String sql = "SELECT MaSV, MaDe, DSCauTraLoi, DanhSachDungSai, SoCauDung, Diem FROM baithi WHERE TrangThai=1 LIMIT " + 25 * (trang - 1) + ",25";
        ResultSet rs = super.query(sql);
        try {
            while (rs.next()) {
                Vector vtRow = new Vector();
                vtRow.add(false);
                for (int i = 1; i < 7; i++) {
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
        String sql = "SELECT COUNT(*) FROM baithi WHERE TrangThai=1 ";
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

    public BaiThiDTO getbaithi(String maSV, String maDe) {
        String sql = "select * from baithi where MaSV= '" + maSV + "' and MaDe = '" + maDe + "'";
        ResultSet rs = super.query(sql);
        BaiThiDTO baithi = new BaiThiDTO();
        try {
            if (rs.next()) {
                baithi = new BaiThiDTO(rs.getString("MaSV"), rs.getString("MaDe"), rs.getString("DSCauTraLoi"),
                        rs.getString("DanhSachDungSai"), rs.getInt("SoCauDung"), rs.getString("Diem"), rs.getInt("TrangThai"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DeCauHoiDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return baithi;
    }

    public ArrayList getList() {
        return super.getArrayList(baithi, "baithi");
    }

    public boolean them(BaiThiDTO baithi) {
        return super.themDoiTuong(baithi, "baithi");
    }

    public boolean sua(BaiThiDTO baithi, String maSV, String maDe) {
        try {
            return super.statement.execute("update baithi set MaSV = '" + baithi.getMaSinhVien() + "' MaDe = '" + baithi.getMaDe() + "' DSCauTraLoi = '" + baithi.getDsCauTraLoi() + " TrangThai = '" + baithi.getTrangThai() + " where MaSV = " + maSV + " and MaDe = " + maDe);
        } catch (SQLException ex) {
            Logger.getLogger(DeCauHoiDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean xoa(String maSV, String maDe) {
        try {
            return !super.statement.execute("update dethi set TrangThai = 0  where MaSV = '" + maSV + "' and MaDe = '" + maDe + "'");
        } catch (SQLException ex) {
            Logger.getLogger(GiangVienDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean importExcel(String fileName) {
        return super.importExcel(fileName, baithi, "baithi");
    }

    public boolean exportExcel(String nameFile) {
        return super.exportExcel(getList(), baithi, nameFile);
    }

    //Thanh Thêm
    public ArrayList<BaiThiDTO> getBaiThiList(String maSV) {
        String sql = "SELECT * FROM `baithi` WHERE MaSV = '" + maSV + "'";
        ResultSet rs = super.query(sql);
        ArrayList<BaiThiDTO> baiThis = new ArrayList<BaiThiDTO>();
        try {
            while (rs.next()) {
                BaiThiDTO baiThi = new BaiThiDTO(rs.getString("MaSV"), rs.getString("MaDe"), rs.getString("DSCauTraLoi"),
                        rs.getString("DanhSachDungSai"), rs.getInt("SoCauDung"), rs.getString("Diem"), rs.getInt("TrangThai"));
                baiThis.add(baiThi);
            }
        } catch (Exception e) {
            System.out.println("DAO_EXAM.BaiThiDAO.getBaiThiList() " + e);
            return null;
        }
        return baiThis;
    }
}
