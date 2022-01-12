/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS_EXAM;

import DAO_EXAM.QuyenDAO;
import DTO_EXAM.QuyenDTO;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author Admin
 */
public class QuyenBUS extends BaseBUS {

    QuyenDAO chucvu = new QuyenDAO();

    @Override
    public QuyenDTO getObject(String maObject) {
        return chucvu.getChucVu(maObject);
    }

    @Override
    public ArrayList<QuyenDTO> getList() {
        return chucvu.getList();
    }

    @Override
    public boolean them(Object obj) {
        return chucvu.them((QuyenDTO) obj);
    }

    @Override
    public boolean sua(Object obj, String maObject) {
        return chucvu.sua((QuyenDTO) obj, maObject);
    }

    @Override
    public boolean xoa(String maObject) {
        return chucvu.xoa(maObject);
    }

    @Override
    public boolean importExcel(String fileName) {
        return chucvu.importExcel(fileName);
    }

    @Override
    public boolean exportExcel(String fileName) {
        return chucvu.exportExcel(fileName);
    }

    @Override
    public boolean isExisted(String maObject) {
        for (Object obj : getList()) {
            QuyenDTO cv = (QuyenDTO) obj;
            if (cv.getMaChucVu().equals(maObject)) {
                return true;
            }
        }
        return false;
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
