/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_EXAM;

import DTO_EXAM.AdminDTO;
import DTO_EXAM.GiangVienDTO;
import DTO_EXAM.MonDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class AdminDAO extends BaseDAO {

    AdminDTO admin = new AdminDTO();

    public AdminDTO getAdmin(String maAdmin) {
        return (AdminDTO) super.getDoiTuong(maAdmin, "admin", admin);
    }

    public ArrayList getList() {
        return super.getArrayList(admin, "admin");
    }

    public boolean them(AdminDTO admin) {
        return super.themDoiTuong(admin, "admin");
    }

    public boolean sua(AdminDTO admin, String maAdmin) {
        return super.suaDoiTuong(admin, "admin", maAdmin);
    }

    public boolean xoa(String maAdmin) {
        try {
            super.statement.execute("update user set TrangThai = 0 where MaUser = '" + maAdmin + "'");
            return !super.statement.execute("update admin set TrangThai = 0 where MaAdmin = '" + maAdmin + "'");
        } catch (SQLException ex) {
            Logger.getLogger(GiangVienDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean importExcel(String fileName) {
        return super.importExcel(fileName, admin, "admin");
    }

    public boolean exportExcel(String nameFile) {
        return super.exportExcel(getList(), admin, nameFile);
    }

}
