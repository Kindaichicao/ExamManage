/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS_EXAM;

import DAO_EXAM.ChucNangDAO;
import DTO_EXAM.ChiTietQuyenDTO;
import DTO_EXAM.ChucNangDTO;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author Admin
 */
public class ChucNangBUS extends BaseBUS {

    ChucNangDAO quyen = new ChucNangDAO();

    @Override
    public ChucNangDTO getObject(String maObject) {
        return quyen.getQuyen(maObject);
    }

    @Override
    public ArrayList<ChucNangDTO> getList() {
        return quyen.getList();
    }

    @Override
    public boolean them(Object obj) {
        return quyen.them((ChucNangDTO) obj);
    }

    @Override
    public boolean sua(Object obj, String maObject) {
        return quyen.sua((ChucNangDTO) obj, maObject);
    }

    @Override
    public boolean xoa(String maObject) {
        return quyen.xoa(maObject);
    }

    @Override
    public boolean importExcel(String fileName) {
        return quyen.importExcel(fileName);
    }

    @Override
    public boolean exportExcel(String fileName) {
        return quyen.exportExcel(fileName);
    }

    @Override
    public boolean isExisted(String maObject) {
        for (Object obj : getList()) {
            ChucNangDTO quyen = (ChucNangDTO) obj;
            if (quyen.getMaQuyen().equals(maObject)) {
                return true;
            }
        }
        return false;
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
