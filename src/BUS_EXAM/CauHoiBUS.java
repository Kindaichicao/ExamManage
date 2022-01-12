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
import java.sql.*;

/**
 *
 * @author AQ
 */
public class CauHoiBUS extends BaseBUS {

    public Vector getThongTin(int trang) {
        return ContainerDAO.daoCauHoi.getThongTin(trang);
    }

    public Vector getThongTin(String[] maMon, int trang) {
        return ContainerDAO.daoCauHoi.getThongTin(maMon, trang);
    }

    public int coutThongTin() {
        return ContainerDAO.daoCauHoi.coutThongTin();
    }

    public int coutThongTin(String[] maMon) {
        return ContainerDAO.daoCauHoi.coutThongTin(maMon);
    }

    public ArrayList<String> getListA() {
        return ContainerDAO.daoCauHoi.getListA();
    }

    public ArrayList<String> getListB() {
        return ContainerDAO.daoCauHoi.getListB();
    }

    public ArrayList<String> getListC() {
        return ContainerDAO.daoCauHoi.getListC();
    }

    public ArrayList<String> getListD() {
        return ContainerDAO.daoCauHoi.getListD();
    }

    public ArrayList<String> getListND() {
        return ContainerDAO.daoCauHoi.getListND();
    }

    CauHoiDAO cauhoi = new CauHoiDAO();

    @Override
    public CauHoiDTO getObject(String maObject) {
        return cauhoi.getCauHoi(maObject);
    }

    @Override
    public ArrayList<CauHoiDTO> getList() {
        return cauhoi.getList();
    }

    @Override
    public boolean them(Object obj) {
        return cauhoi.them((CauHoiDTO) obj);
    }

    @Override
    public boolean sua(Object obj, String maObject) {
        return cauhoi.sua((CauHoiDTO) obj, maObject);
    }

    @Override
    public boolean xoa(String maObject) {
        return cauhoi.xoa(maObject);
    }

    @Override
    public boolean importExcel(String fileName) {
        return cauhoi.importExcel(fileName);
    }

    @Override
    public boolean exportExcel(String fileName) {
        return cauhoi.exportExcel(fileName);
    }

    @Override
    public boolean isExisted(String maObject) {
        for (Object obj : getList()) {
            CauHoiDTO ch = (CauHoiDTO) obj;
            if (ch.getMaCauHoi().equals(maObject)) {
                return true;
            }
        }
        return false;
    }

    //Phần Thanh thêm
    public ArrayList<CauHoiDTO> getCauHoiList(String maDe) {
        ArrayList<CauHoiDTO> cauHois = new ArrayList<CauHoiDTO>();
        DeCauHoiBUS busDeCauHoi = new DeCauHoiBUS();
        ArrayList<DeCauHoiDTO> deCauHois = busDeCauHoi.getDeCauHoiList(maDe);
        for (DeCauHoiDTO dch : deCauHois) {
            CauHoiDTO cauhoi = ContainerDAO.daoCauHoi.getCauHoi(dch.getMaCauHoi());
            cauHois.add(cauhoi);
        }
        return cauHois;
    }

    public ArrayList<CauHoiDTO> getListCauHoiMon(ArrayList<GiangVienMonDTO> gVMs) {
        return ContainerDAO.daoCauHoi.getListCauHoiMon(gVMs);
    }

    public ArrayList<CauHoiDTO> getListCauHoiMonDoKho(ArrayList<GiangVienMonDTO> gVMs, int dokho) {
        return ContainerDAO.daoCauHoi.getListCauHoiMonDoKho(gVMs, dokho);
    }

    //Quân
    public boolean khoiphuc(String maObject) {
        return cauhoi.khoiphuc(maObject);
    }

    public boolean xoavinhvien(String maObject) {
        return cauhoi.xoavinhvien(maObject);
    }
}
