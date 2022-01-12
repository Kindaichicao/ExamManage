/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS_EXAM;

import DTO_EXAM.DeThiDTO;
import DAO_EXAM.*;
import DTO_EXAM.GiangVienDTO;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author Admin
 */
public class DeThiBUS extends BaseBUS {

    DeThiDAO dethi = new DeThiDAO();

    @Override
    public DeThiDTO getObject(String maObject) {
        return dethi.getDeThi(maObject);
    }

    @Override
    public ArrayList<DeThiDTO> getList() {
        return dethi.getList();
    }

    @Override
    public boolean them(Object obj) {
        return dethi.them((DeThiDTO) obj);
    }

    @Override
    public boolean sua(Object obj, String maObject) {
        return dethi.sua((DeThiDTO) obj, maObject);
    }

    @Override
    public boolean xoa(String maObject) {
        return dethi.xoa(maObject);
    }

    @Override
    public boolean importExcel(String fileName) {
        return dethi.importExcel(fileName);
    }

    @Override
    public boolean exportExcel(String fileName) {
        return dethi.exportExcel(fileName);
    }

    @Override
    public boolean isExisted(String maObject) {
        for (Object obj : getList()) {
            DeThiDTO dt = (DeThiDTO) obj;
            if (dt.getMaDe().equals(maObject)) {
                return true;
            }
        }
        return false;
    }

    //ThanhThem
    public DeThiDTO getDeMaMonKyThi(String maMon, String maKyThi) {
        return ContainerDAO.daoDeThi.getDeMaMonKyThi(maMon, maKyThi);
    }

    public String getMaMon(String maDe) {

        return ContainerDAO.daoDeThi.getMaMon(maDe);
    }

    //Quân
    public Vector getThongTin(int trang) {
        return ContainerDAO.daoDeThi.getThongTin(trang);
    }

    public int coutThongTin() {
        return ContainerDAO.daoDeThi.coutThongTin();
    }

    public boolean khoiphuc(String maObject) {
        return dethi.khoiphuc(maObject);
    }

    public boolean xoavinhvien(String maObject) {
        return dethi.xoavinhvien(maObject);
    }
}
