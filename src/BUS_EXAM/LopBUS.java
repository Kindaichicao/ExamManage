/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS_EXAM;

import DAO_EXAM.ContainerDAO;
import DAO_EXAM.LopDAO;
import DTO_EXAM.LopDTO;
import DTO_EXAM.MonDTO;
import java.awt.Container;
import java.util.ArrayList;
import java.sql.*;
import java.util.Vector;

/**
 *
 * @author Admin
 */
public class LopBUS extends BaseBUS {

    LopDAO lop = new LopDAO();

    public ArrayList<String> getListLop(String makhoa) {
        return ContainerDAO.daoLop.getListLop(makhoa);
    }

    @Override
    public LopDTO getObject(String maObject) {
        return lop.getLop(maObject);
    }

    @Override
    public ArrayList<LopDTO> getList() {
        return lop.getList();
    }

    @Override
    public boolean them(Object obj) {
        return lop.them((LopDTO) obj);
    }

    @Override
    public boolean sua(Object obj, String maObject) {
        return lop.sua((LopDTO) obj, maObject);
    }

    @Override
    public boolean xoa(String maObject) {
        return false;
    }

    /**
     * Những đối tượng thuộc lớp bị xóa sẽ được chuyển sang lớp mới
     *
     * @param maLop: Mã lớp của lớp cần xóa
     * @param maLopMoi: Mã lớp của lớp cần chuyển qua
     * @return true nếu thành công và ngược lại
     */
    public boolean xoa(String maLop, String maLopMoi) {
        return lop.xoa(maLop, maLopMoi);
    }

    @Override
    public boolean importExcel(String fileName) {
        return lop.importExcel(fileName);
    }

    @Override
    public boolean exportExcel(String fileName) {
        return lop.exportExcel(fileName);
    }

    @Override
    public boolean isExisted(String maObject) {
        for (Object obj : getList()) {
            LopDTO lop = (LopDTO) obj;
            if (lop.getMaLop().equals(maObject)) {
                return true;
            }
        }
        return false;
    }

    //Thanh Them
    public String getTenLop(String maLop) {
        return ContainerDAO.daoLop.getTenLop(maLop);
    }
//Quan

    public Vector getThongTin(int trang) {
        return ContainerDAO.daoLop.getThongTin(trang);
    }

    public int coutThongTin() {
        return ContainerDAO.daoLop.coutThongTin();
    }

    public boolean khoiphuc(String maObject) {
        return lop.khoiphuc(maObject);
    }

    public boolean xoavinhvien(String maObject) {
        return lop.xoavinhvien(maObject);
    }

    public ArrayList<LopDTO> getLopMaKhoa(String maKhoa) {
        ArrayList getLopMaKhoa = new ArrayList();
        for (LopDTO lop : getList()) {
            if (lop.getMaKhoa().equals(maKhoa)) {
                getLopMaKhoa.add(lop);
            }
        }
        return getLopMaKhoa;
    }

}
