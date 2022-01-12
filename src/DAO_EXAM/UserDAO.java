/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_EXAM;

import DTO_EXAM.*;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.util.Vector;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
//Class này chứa các hàm cơ bản như đọc thêm sửa xóa ở lớp DAO
public class UserDAO extends BaseDAO {

    public UserDAO() {
        super();
    }

//Hàm này đọc vào Vector vct danh sách được lấy từ bảng User;
    public Vector<UserDTO> getUserList() {
        String sql = "select * from `user` where 1";
        Vector<UserDTO> vct = new Vector();
        ResultSet result = super.query(sql);
        try {
            while (result.next()) {
                UserDTO User = new UserDTO(result.getString(1), result.getString(2), result.getString(3), result.getInt(4));
                vct.add(User);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Buồn quá");
        }
        return vct;
    }

    public Vector<UserDTO> getUserList(String sqladd) {
        String sql = "select * from `user` where " + sqladd;
        Vector<UserDTO> vct = new Vector();
        ResultSet result = super.query(sql);
        try {
            while (result.next()) {
                UserDTO User = new UserDTO(result.getString(1), result.getString(2), result.getString(3), result.getInt(4));
                vct.add(User);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return vct;
    }

    public UserDTO searchUser(ArrayList<Object> params) {
        String sql = "select * from user where MaUser = ? and MatKhau = ?";
        ResultSet result = super.query(sql, params);
        UserDTO user = null;
        try {
            if (result.next()) {
                user = new UserDTO(result.getString(1), result.getString(2), result.getString(3), result.getInt(4));
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return user;
    }

    public String getPassword(String idUser) {
        String sql = "select MatKhau from user where MaUser = '" + idUser + "'";
        String pass = "";
        ResultSet rs = super.query(sql);
        try {
            if (rs.next()) {
                pass = rs.getString(1);
            }

        } catch (Exception e) {
            System.err.println(e);
        }
        return pass;
    }

    public void setPassword(String idUser, String pass) {
        String sql = "UPDATE `user` SET `MatKhau`= '" + pass + "'  WHERE  MaUser = '" + idUser + "'";
        super.update(sql);
    }

    public boolean xoaUser(String maUser) {
        try {
            return !super.statement.execute("update user set TrangThai = 0 where MaUser  '= " + maUser + "'");
        } catch (SQLException ex) {
            return false;
        }
    }
    UserDTO us = new UserDTO();

    public UserDTO getUser(String maUser) {
        return (UserDTO) super.getDoiTuong(maUser, "user", us);
    }

    public ArrayList getList() {
        return super.getArrayList(us, "user");
    }

    public boolean sua(UserDTO us, String maUser) {
        return super.suaDoiTuong(us, "user", maUser);
    }

    public boolean importExcel(String fileName) {
        return super.importExcel(fileName, us, "user");
    }

    public boolean exportExcel(String fileName) {
        return super.exportExcel(getList(), us, fileName);
    }

    //Q
    public Vector getThongTin(int trang) {
        Vector vtData = new Vector();
        String sql = "SELECT MaUser, MatKhau, TenChucVu FROM user, quyen WHERE user.TrangThai=1 AND user.MaChucVu=quyen.MaChucVu LIMIT " + 25 * (trang - 1) + ",25";
        ResultSet rs = super.query(sql);
        try {
            while (rs.next()) {
                Vector vtRow = new Vector();
                vtRow.add(false);
                for (int i = 1; i < 4; i++) {
                    vtRow.add(rs.getString(i));
                }
                vtData.add(vtRow);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return vtData;
    }

    public int coutThongTin() {
        int cout = 0;
        String sql = "SELECT COUNT(*) FROM user WHERE TrangThai=1 ";
        ResultSet rs = super.query(sql);
        try {
            while (rs.next()) {
                cout = Integer.parseInt(rs.getString(1));
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return cout;
    }

    public boolean xoa(String maUser) {
        try {
            super.statement.execute("update sinhvien set TrangThai = 0 where MaSV = '" + maUser + "'");
            super.statement.execute("update nhanvien set TrangThai = 0 where MaNV = '" + maUser + "'");
            super.statement.execute("update giangvien set TrangThai = 0 where MaGV = '" + maUser + "'");
            super.statement.execute("update admin set TrangThai = 0 where MaAd = '" + maUser + "'");
            return !super.statement.execute("update user set TrangThai = 0 where MaUser = '" + maUser + "'");
        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean khoiphuc(String maUser) {
        try {
            super.statement.execute("update sinhvien set TrangThai = 1 where MaSV = '" + maUser + "'");
            super.statement.execute("update nhanvien set TrangThai = 1 where MaNV = '" + maUser + "'");
            super.statement.execute("update giangvien set TrangThai = 1 where MaGV = '" + maUser + "'");
            super.statement.execute("update admin set TrangThai =  1 where MaAd = '" + maUser + "'");
            return !super.statement.execute("update user set TrangThai = 1 where MaUser = '" + maUser + "'");
        } catch (SQLException ex) {
            return false;
        }
    }
}
