/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS_EXAM;

import DAO_EXAM.ContainerDAO;
import DAO_EXAM.KhoaDAO;
import DAO_EXAM.MonDAO;
import DTO_EXAM.*;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author Admin
 */
public class MonBUS extends BaseBUS {

    MonDAO mon = new MonDAO();

    public ArrayList<String> getListMon() {
        return ContainerDAO.daoMon.getListMon();
    }

    public Vector getThongTin(int trang) {
        return ContainerDAO.daoMon.getThongTin(trang);
    }

    public int coutThongTin() {
        return ContainerDAO.daoMon.coutThongTin();
    }

    @Override
    public MonDTO getObject(String maObject) {
        return mon.getMon(maObject);
    }

    @Override
    public ArrayList<MonDTO> getList() {
        return mon.getList();
    }

    @Override
    public boolean them(Object obj) {
        return mon.them((MonDTO) obj);
    }

    @Override
    public boolean sua(Object obj, String maObject) {
        return mon.sua((MonDTO) obj, maObject);
    }

    @Override
    public boolean xoa(String maObject) {
        return mon.xoa(maObject);
    }

    @Override
    public boolean importExcel(String fileName) {
        return mon.importExcel(fileName);
    }

    @Override
    public boolean exportExcel(String fileName) {
        return mon.exportExcel(fileName);
    }

    @Override
    public boolean isExisted(String maObject) {
        for (Object obj : getList()) {
            MonDTO mon = (MonDTO) obj;
            if (mon.getMaMon().equals(maObject)) {
                return true;
            }
        }
        return false;
    }

    public String getTenMon(String maMon) {
        return ContainerDAO.daoMon.getTenMon(maMon);
    }
//Q

    public boolean khoiphuc(String maObject) {
        return mon.khoiphuc(maObject);
    }

    public boolean xoavinhvien(String maObject) {
        return mon.xoavinhvien(maObject);
    }

}
