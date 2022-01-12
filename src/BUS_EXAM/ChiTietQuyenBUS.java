/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS_EXAM;

import DAO_EXAM.ChiTietQuyenDAO;
import DAO_EXAM.ContainerDAO;
import DTO_EXAM.ChiTietQuyenDTO;
import DTO_EXAM.ChucNangDTO;
import DTO_EXAM.SinhVienDTO;
import java.awt.Container;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author Admin
 */
public class ChiTietQuyenBUS extends BaseBUS {

    ChiTietQuyenDAO phanquyen = new ChiTietQuyenDAO();

    @Override
    public ChiTietQuyenDTO getObject(String maObject) {
        return phanquyen.getQuyenChucVu(maObject);
    }

    @Override
    public ArrayList<ChiTietQuyenDTO> getList() {
        return phanquyen.getList();
    }

    @Override
    public boolean them(Object obj) {
        return phanquyen.them((ChiTietQuyenDTO) obj);
    }

    @Override
    public boolean sua(Object obj, String maObject) {
        return phanquyen.sua((ChiTietQuyenDTO) obj, maObject);
    }

    @Override
    public boolean xoa(String maObject) {
        return phanquyen.xoa(maObject);
    }

    @Override
    public boolean importExcel(String fileName) {
        return phanquyen.importExcel(fileName);
    }

    @Override
    public boolean exportExcel(String fileName) {
        return phanquyen.exportExcel(fileName);
    }

    @Override
    public boolean isExisted(String maObject) {
        for (Object obj : getList()) {
            ChiTietQuyenDTO pq = (ChiTietQuyenDTO) obj;
            if (pq.getMaChucVu().equals(maObject)) {
                return true;
            }
        }
        return false;
    }
    //Thanh

    public ArrayList<Integer> getListTrangThai(String maQuyen) {
        return ContainerDAO.daoCTQ.getListTrangThai(maQuyen);
    }

    public ArrayList<ChiTietQuyenDTO> getListCT(String maQuyen) {
        return ContainerDAO.daoCTQ.getListCT(maQuyen);
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
