/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_EXAM;

import DTO_EXAM.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class QuyenDAO extends BaseDAO {

    QuyenDTO chucvu = new QuyenDTO();

    public QuyenDTO getChucVu(String maChucVu) {
        return (QuyenDTO) super.getDoiTuong(maChucVu, "quyen", chucvu);
    }

    public ArrayList getList() {
        return super.getArrayList(chucvu, "quyen");
    }

    public boolean them(QuyenDTO chucvu) {
        return super.themDoiTuong(chucvu, "quyen");
    }

    public boolean sua(QuyenDTO chucvu, String maChucVu) {
        return super.suaDoiTuong(chucvu, "quyen", maChucVu);
    }

    public boolean xoa(String maChucVu) {
        try {
            return !super.statement.execute("update quyen set TrangThai = 0 where MaChucVu = '" + maChucVu + "'");
        } catch (SQLException ex) {
            Logger.getLogger(GiangVienDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean importExcel(String fileName) {
        return super.importExcel(fileName, chucvu, "de_cauhoi");
    }

    public boolean exportExcel(String nameFile) {
        return super.exportExcel(getList(), chucvu, nameFile);
    }

}
