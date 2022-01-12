/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_EXAM;

import DTO_EXAM.MonSinhVienDTO;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author ASUS
 */
public class MonSinhVienDAO extends BaseDAO {

    //Phần Thanh Thêm
    public ArrayList<MonSinhVienDTO> getMonSinhVienList(String idUser) {
        ArrayList<MonSinhVienDTO> mons = new ArrayList<MonSinhVienDTO>();
        String sql = "SELECT * FROM sinhvien_mon where MaSV = '" + idUser + "'";
        ResultSet rs = super.query(sql);
        try {
            while (rs.next()) {
                MonSinhVienDTO msv = new MonSinhVienDTO(rs.getString("MaSV"), rs.getString("MaMon"), rs.getInt("TrangThai"));
                if (msv.getTrangThai() == 1) {
                    mons.add(msv);
                }
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return mons;
    }
    //Kết Thúc Phần Thanh Thêm
}
