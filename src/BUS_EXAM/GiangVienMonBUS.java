/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS_EXAM;

import DTO_EXAM.GiangVienMonDTO;
import DAO_EXAM.*;
import DTO_EXAM.KhoaDTO;
import java.util.ArrayList;
import java.util.Vector;
import java.sql.*;

/**
 *
 * @author Admin
 */
public class GiangVienMonBUS extends BaseBUS {

    GiangVienMonDAO gvMon = new GiangVienMonDAO();

    public ArrayList<String> getListGiangVien() {
        return ContainerDAO.daoGV.getListGiangVien();
    }

    @Override
    public GiangVienMonDTO getObject(String maObject) {
        return gvMon.getPhanCong(maObject);
    }

    @Override
    public ArrayList<GiangVienMonDTO> getList() {
        return gvMon.getList();
    }

    @Override
    public boolean them(Object obj) {
        return gvMon.them((GiangVienMonDTO) obj);
    }

    @Override
    public boolean sua(Object obj, String maObject) {
        return gvMon.sua((GiangVienMonDTO) obj, maObject);
    }

    @Override
    public boolean xoa(String maObject) {
        return gvMon.xoa(maObject);
    }

    @Override
    public boolean importExcel(String fileName) {
        return gvMon.importExcel(fileName);
    }

    @Override
    public boolean exportExcel(String fileName) {
        return gvMon.exportExcel(fileName);
    }

    @Override
    public boolean isExisted(String maObject) {
        for (Object obj : getList()) {
            GiangVienMonDTO gvm = (GiangVienMonDTO) obj;
            if (gvm.getMaPhanCong().equals(maObject)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<GiangVienMonDTO> getListChungMon(String maMon) {
        return ContainerDAO.daoGVM.getListChungMon(maMon);
    }

    public String getMa(String maGV, String maMon) {
        return gvMon.getMa(maGV, maMon);
    }

    @Override
    public Vector getThongTin(int trang) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int coutThongTin() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
