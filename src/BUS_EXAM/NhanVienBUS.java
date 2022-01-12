/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS_EXAM;

import DAO_EXAM.ContainerDAO;
import DAO_EXAM.NhanVienDAO;
import DTO_EXAM.NhanVienDTO;
import DTO_EXAM.ChucNangDTO;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author Admin
 */
public class NhanVienBUS extends BaseBUS {

    NhanVienDAO nhanvien = new NhanVienDAO();

    @Override
    public NhanVienDTO getObject(String maObject) {
        return nhanvien.getNhanVien(maObject);
    }

    @Override
    public ArrayList<NhanVienDTO> getList() {
        return nhanvien.getList();
    }

    @Override
    public boolean them(Object obj) {
        return nhanvien.them((NhanVienDTO) obj);
    }

    @Override
    public boolean sua(Object obj, String maObject) {
        return nhanvien.sua((NhanVienDTO) obj, maObject);
    }

    @Override
    public boolean xoa(String maObject) {
        return nhanvien.xoa(maObject);
    }

    @Override
    public boolean importExcel(String fileName) {
        return nhanvien.importExcel(fileName);
    }

    @Override
    public boolean exportExcel(String fileName) {
        return nhanvien.exportExcel(fileName);
    }

    @Override
    public boolean isExisted(String maObject) {
        for (Object obj : getList()) {
            NhanVienDTO nv = (NhanVienDTO) obj;
            if (nv.getMaUser().equals(maObject)) {
                return true;
            }
        }
        return false;
    }
//Q

    public Vector getThongTin(int trang) {
        return ContainerDAO.daoNV.getThongTin(trang);
    }

    public int coutThongTin() {
        return ContainerDAO.daoNV.coutThongTin();
    }

    public boolean khoiphuc(String maObject) {
        return nhanvien.khoiphuc(maObject);
    }

    public boolean xoavinhvien(String maObject) {
        return nhanvien.xoavinhvien(maObject);
    }
}
