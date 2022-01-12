/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS_EXAM;

import java.util.ArrayList;
import DAO_EXAM.*;
import DTO_EXAM.*;
import java.util.Vector;

/**
 *
 * @author Admin
 */
public class AdminBUS extends BaseBUS {

    AdminDAO admin = new AdminDAO();

    @Override
    public AdminDTO getObject(String maObject) {
        return admin.getAdmin(maObject);
    }

    @Override
    public ArrayList<AdminDTO> getList() {
        return admin.getList();
    }

    @Override
    public boolean them(Object obj) {
        return admin.them((AdminDTO) obj);
    }

    @Override
    public boolean sua(Object obj, String maObject) {
        return admin.sua((AdminDTO) obj, maObject);
    }

    @Override
    public boolean xoa(String maObject) {
        return admin.xoa(maObject);
    }

    @Override
    public boolean importExcel(String fileName) {
        return admin.importExcel(fileName);
    }

    @Override
    public boolean exportExcel(String fileName) {
        return admin.exportExcel(fileName);
    }

    @Override
    public boolean isExisted(String maObject) {
        for (Object obj : getList()) {
            AdminDTO ad = (AdminDTO) obj;
            if (ad.getMaUser().equals(maObject)) {
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
