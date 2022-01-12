/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS_EXAM;

import DAO_EXAM.ContainerDAO;
import DAO_EXAM.KyThiDAO;
import DTO_EXAM.KyThiDTO;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author ASUS
 */
public class KyThiBUS extends BaseBUS {
    //Quân

    public ArrayList<String> getListKyThi() {
        return ContainerDAO.daoKyThi.getListKyThi();
    }

    public Vector getThongTin(int trang) {
        return ContainerDAO.daoKyThi.getThongTin(trang);
    }

    public int coutThongTin() {
        return ContainerDAO.daoKyThi.coutThongTin();
    }

    //Thanh Thêm
    public KyThiDTO getKyThi(String maKyThi) {
        return ContainerDAO.daoKyThi.getKyThi(maKyThi);
    }

    public KyThiDTO getKyThiHienTai() {
        return ContainerDAO.daoKyThi.getKyThiHienTai();
    }
    //Kết Thúc

    KyThiDAO kythi = new KyThiDAO();

    @Override
    public KyThiDTO getObject(String maObject) {
        return kythi.getKyThi(maObject);
    }

    @Override
    public ArrayList<KyThiDTO> getList() {
        return kythi.getList();
    }

    @Override
    public boolean them(Object obj) {
        return kythi.them((KyThiDTO) obj);
    }

    @Override
    public boolean sua(Object obj, String maObject) {
        return kythi.sua((KyThiDTO) obj, maObject);
    }

    @Override
    public boolean xoa(String maObject) {
        return false;
    }

    @Override
    public boolean importExcel(String fileName) {
        return kythi.importExcel(fileName);
    }

    @Override
    public boolean exportExcel(String fileName) {
        return kythi.exportExcel(fileName);
    }

    @Override
    public boolean isExisted(String maObject) {
        for (Object obj : getList()) {
            KyThiDTO kt = (KyThiDTO) obj;
            if (kt.getMaKyThi().equals(maObject)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<KyThiDTO> getAllList() {
        return kythi.getAllList();
    }

    public Vector<String> getListTenKyThi() {
        Vector getTenKyThi = new Vector();
        for (KyThiDTO kythi : getAllList()) {
            getTenKyThi.add(kythi.getTenKyThi());
        }
        return getTenKyThi;
    }

    //Thanh Them
    public String getTenKyThi(String maKyThi) {
        return ContainerDAO.daoKyThi.getTenKyThi(maKyThi);
    }

    public boolean xoavinhvien(String maObject) {
        return kythi.xoavinhvien(maObject);
    }
    //Quan
}
