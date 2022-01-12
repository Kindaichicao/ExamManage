/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS_EXAM;

import DAO_EXAM.ContainerDAO;
import DAO_EXAM.GiangVienDAO;
import DTO_EXAM.GiangVienDTO;
import DTO_EXAM.GiangVienMonDTO;
import DTO_EXAM.MonDTO;
import java.awt.Component;
import java.io.File;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 *
 * @author Admin
 */
public class GiangVienBUS extends BaseBUS {

    GiangVienDAO giangvien = new GiangVienDAO();

    public ArrayList<String> getListGiangVien() {
        return ContainerDAO.daoGV.getListGiangVien();
    }

    public Vector getThongTin(int trang) {
        return ContainerDAO.daoGV.getThongTin(trang);
    }

    public int coutThongTin() {
        return ContainerDAO.daoGV.coutThongTin();
    }

    @Override
    public GiangVienDTO getObject(String maObject) {
        return giangvien.getGiangVien(maObject);
    }

    @Override
    public ArrayList<GiangVienDTO> getList() {
        return giangvien.getList();
    }

    @Override
    public boolean them(Object obj) {
        return giangvien.them((GiangVienDTO) obj);
    }

    @Override
    public boolean sua(Object obj, String maObject) {
        return giangvien.sua((GiangVienDTO) obj, maObject);
    }

    @Override
    public boolean xoa(String maObject) {
        return giangvien.xoa(maObject);
    }

    @Override
    public boolean importExcel(String fileName) {
        return giangvien.importExcel(fileName);
    }

    @Override
    public boolean exportExcel(String fileName) {
        return giangvien.exportExcel(fileName);
    }

    @Override
    public boolean isExisted(String maObject) {
        for (Object obj : getList()) {
            GiangVienDTO gv = (GiangVienDTO) obj;
            if (gv.getMaUser().equals(maObject)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<MonDTO> getMonQuanLy(String maGiangVien) {
        return giangvien.getMonQuanLy(maGiangVien);
    }

    public boolean khoiphuc(String maObject) {
        return giangvien.khoiphuc(maObject);
    }

    public boolean xoavinhvien(String maObject) {
        return giangvien.xoavinhvien(maObject);
    }
}
