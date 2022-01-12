/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS_EXAM;

import java.util.ArrayList;
import DAO_EXAM.*;
import DTO_EXAM.KhoaDTO;
import DTO_EXAM.KyThiDTO;
import java.util.Vector;

/**
 *
 * @author Admin
 */
public class KhoaBUS extends BaseBUS {

    KhoaDAO khoa = new KhoaDAO();

    public ArrayList<String> getListKhoa() {
        return ContainerDAO.daoKhoa.getListKhoa();
    }

    public Vector getThongTin(int trang) {
        return ContainerDAO.daoKhoa.getThongTin(trang);
    }

    public int coutThongTin() {
        return ContainerDAO.daoKhoa.coutThongTin();
    }

    @Override
    public KhoaDTO getObject(String maObject) {
        return khoa.getKhoa(maObject);
    }

    @Override
    public ArrayList<KhoaDTO> getList() {
        return khoa.getList();
    }

    @Override
    public boolean them(Object obj) {
        return khoa.them((KhoaDTO) obj);
    }

    @Override
    public boolean sua(Object obj, String maObject) {
        return khoa.sua((KhoaDTO) obj, maObject);
    }

    @Override
    public boolean xoa(String maObject) {
        return khoa.xoa(maObject);
    }

    public boolean khoiphuc(String maObject) {
        return khoa.khoiphuc(maObject);
    }

    public boolean xoavinhvien(String maObject) {
        return khoa.xoavinhvien(maObject);
    }

    @Override
    public boolean importExcel(String fileName) {
        return khoa.importExcel(fileName);
    }

    @Override
    public boolean exportExcel(String fileName) {
        return khoa.exportExcel(fileName);
    }

    @Override
    public boolean isExisted(String maObject) {
        for (Object obj : getList()) {
            KhoaDTO khoa = (KhoaDTO) obj;
            if (khoa.getMaKhoa().equals(maObject)) {
                return true;
            }
        }
        return false;
    }

    //Thanh Them
    public String getTenKhoa(String maKhoa) {
        return ContainerDAO.daoKhoa.getTenKhoa(maKhoa);
    }
}
