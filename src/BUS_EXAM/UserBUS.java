/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS_EXAM;

import java.util.ArrayList;
import DTO_EXAM.*;
import DAO_EXAM.*;
import java.util.Vector;

/**
 *
 * @author Admin
 */
public class UserBUS extends BaseBUS {

    UserDAO user = new UserDAO();

    @Override
    public UserDTO getObject(String maObject) {
        return user.getUser(maObject);
    }

    @Override
    public ArrayList<UserDTO> getList() {
        return user.getList();
    }

    @Override
    public boolean them(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean sua(Object obj, String maObject) {
        return user.sua((UserDTO) obj, maObject);
    }

    @Override
    public boolean importExcel(String fileName) {
        return user.importExcel(fileName);
    }

    @Override
    public boolean exportExcel(String fileName) {
        return user.exportExcel(fileName);
    }

    @Override
    public boolean isExisted(String maObject) {
        for (UserDTO user : getList()) {
            if (user.getMaUser().equals(maObject)) {
                return true;
            }
        }
        return false;
    }

    //Q
    public Vector getThongTin(int trang) {
        return ContainerDAO.daoUser.getThongTin(trang);
    }

    public int coutThongTin() {
        return ContainerDAO.daoUser.coutThongTin();
    }

    @Override
    public boolean xoa(String maObject) {
        return user.xoa(maObject);
    }

    public boolean khoiphuc(String maObject) {
        return user.khoiphuc(maObject);
    }

    public void setPassword(String idUser, String pass) {
        user.setPassword(idUser, pass);
    }
}
