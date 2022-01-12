/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS_EXAM;

import DAO_EXAM.ContainerDAO;
import DAO_EXAM.DeCauHoiDAO;
import DTO_EXAM.DeCauHoiDTO;
import DTO_EXAM.GiangVienDTO;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author Admin
 */
public class DeCauHoiBUS extends BaseBUS {

    DeCauHoiDAO decauhoi = new DeCauHoiDAO();

    @Override
    public Object getObject(String maObject) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public DeCauHoiDTO getObject(String maCH, String maDe) {
        return decauhoi.getDeCauHoi(maCH, maDe);
    }

    @Override
    public ArrayList<DeCauHoiDTO> getList() {
        return decauhoi.getList();
    }

    @Override
    public boolean them(Object obj) {
        return decauhoi.them((DeCauHoiDTO) obj);
    }

    @Override
    public boolean sua(Object obj, String maObject) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean sua(Object obj, String maCH, String maDe) {
        return decauhoi.sua((DeCauHoiDTO) obj, maCH, maDe);
    }

    @Override
    public boolean xoa(String maObject) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean xoa(String maCH, String maDe) {
        return decauhoi.xoa(maCH, maDe);
    }

    @Override
    public boolean importExcel(String fileName) {
        return decauhoi.importExcel(fileName);
    }

    @Override
    public boolean exportExcel(String fileName) {
        return decauhoi.exportExcel(fileName);
    }

    @Override
    public boolean isExisted(String maObject) {
        return false;
    }

    public boolean isExisted(String maCH, String maDe) {
        for (Object obj : getList()) {
            DeCauHoiDTO kq = (DeCauHoiDTO) obj;
            if (kq.getMaCauHoi().equals(maCH) && kq.getMaDe().equals(maDe)) {
                return true;
            }
        }
        return false;
    }

    //Thanh Them
    public ArrayList<DeCauHoiDTO> getDeCauHoiList(String maDe) {
        ArrayList<DeCauHoiDTO> deCauHois = new ArrayList<DeCauHoiDTO>();
        deCauHois = ContainerDAO.daoDeCauHoi.getDeCauHoiList(maDe);
        return deCauHois;
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
