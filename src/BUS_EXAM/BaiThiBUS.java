/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS_EXAM;

import DAO_EXAM.*;
import DTO_EXAM.*;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JLabel;

/**
 *
 * @author Admin
 */
public class BaiThiBUS extends BaseBUS {

    BaiThiDAO baithi = new BaiThiDAO();

    @Override
    public Object getObject(String maObject) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public BaiThiDTO getObject(String maSV, String maDe) {
        return baithi.getbaithi(maSV, maDe);
    }

    @Override
    public ArrayList<BaiThiDTO> getList() {
        return baithi.getList();
    }

    @Override
    public boolean them(Object obj) {
        return baithi.them((BaiThiDTO) obj);
    }

    @Override
    public boolean sua(Object obj, String maObject) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean sua(Object obj, String maSV, String maDe) {
        return baithi.sua((BaiThiDTO) obj, maSV, maDe);
    }

    @Override
    public boolean xoa(String maObject) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean xoa(String maSV, String maDe) {
        return baithi.xoa(maSV, maDe);
    }

    @Override
    public boolean importExcel(String fileName) {
        return baithi.importExcel(fileName);
    }

    @Override
    public boolean exportExcel(String fileName) {
        return baithi.exportExcel(fileName);
    }

    @Override
    public boolean isExisted(String maObject) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean isExisted(String maSV, String maDe) {
        for (Object obj : baithi.getList()) {
            BaiThiDTO bt = new BaiThiDTO();
            if (bt.getMaDe().equals(maDe) && bt.getMaSinhVien().equals(maSV)) {
                return true;
            }
        }
        return false;
    }
    //Thanh Thêm

    public ArrayList<BaiThiDTO> getBaiThiList(String maSV) {
        return ContainerDAO.daoBaiThi.getBaiThiList(maSV);
    }
//Quân

    public Vector getThongTin(int trang) {
        return ContainerDAO.daoBaiThi.getThongTin(trang);
    }

    public int coutThongTin() {
        return ContainerDAO.daoBaiThi.coutThongTin();
    }

}
