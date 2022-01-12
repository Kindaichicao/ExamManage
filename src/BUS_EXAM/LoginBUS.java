/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS_EXAM;

import DAO_EXAM.*;
import static DAO_EXAM.BaseDAO.toDate;
import DTO_EXAM.*;
import DTO_EXAM.ChiTietQuyenDTO;
import GUI_Exam.UserFrame;
import java.awt.Container;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author ASUS
 */
public class LoginBUS {

    private ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
    private ArrayList<String> informations = new ArrayList<String>();
    private ArrayList<String> menu = new ArrayList<String>();
    private ArrayList<String> quyen = new ArrayList<String>();

    public Vector<UserDTO> getUserList() {
        return ContainerDAO.daoUser.getUserList();
    }
    public static NguoiDungDTO isUser;

    public ArrayList<ChiTietQuyenDTO> getQuyenchucVuList(String sqladd) {
        return ContainerDAO.daoQCV.getQuyenChucVuList(sqladd);
    }

    //Hàm này kiểm tra đăng nhập và loại tài khoản
    public ArrayList<ArrayList<String>> isCorrect(String user, String pass) {
        String khoa = "";
        String lop = "";
        ArrayList<Object> params = new ArrayList<>();
        params.add(user);
        params.add(pass);
        UserDTO User = new UserDTO();
        User = ContainerDAO.daoUser.searchUser(params);
        if (User != null) {
            if (User.getTrangThai() == 1) {
                ArrayList<ChiTietQuyenDTO> quyenchucvus = getQuyenchucVuList("MaChucVu = '" + User.getMaChucVu() + "' and TrangThai = 1");
                for (ChiTietQuyenDTO isquyen : quyenchucvus) {
                    menu.add(ContainerDAO.daoChucNang.getHanhDong(isquyen.getMaQuyen()) + " " + ContainerDAO.daoChucNang.getTenBang(isquyen.getMaQuyen()));
                    quyen.add(isquyen.getMaQuyen());
                }
                menu.add("Đổi Mật Khẩu");
                quyen.add("doiMatKhau");

                if (ContainerDAO.daoSV.getSinhVien(User.getMaUser()) != null) {
                    SinhVienBUS busSV = new SinhVienBUS();
                    isUser = busSV.getObject(User.getMaUser());
                    SinhVienDTO tempUser = (SinhVienDTO) isUser;
                    tempUser.setMaKhoa(ContainerDAO.daoLop.getMaKhoa(tempUser.maLop));
                    lop = ContainerDAO.daoLop.getTenLop(tempUser.getMaLop());
                } else if (ContainerDAO.daoGV.getGiangVien(User.getMaUser()) != null) {
                    GiangVienBUS busGV = new GiangVienBUS();
                    isUser = busGV.getObject(User.getMaUser());
                } else if (ContainerDAO.daoNV.getNhanVien(User.getMaUser()) != null) {
                    NhanVienBUS busNV = new NhanVienBUS();
                    isUser = busNV.getObject(User.getMaUser());
                } else {
                    AdminBUS busAM = new AdminBUS();
                    isUser = busAM.getObject(User.getMaUser());
                }
                khoa = ContainerDAO.daoKhoa.getTenKhoa(isUser.getMaKhoa());
                informations.add(isUser.getMaUser());
                informations.add(isUser.getHoDem() + " " + isUser.getTen());
                informations.add(toDate(isUser.getNgaySinh()));
                informations.add(isUser.getGioiTinh());
                informations.add(khoa);
                if (!lop.equals("")) {
                    informations.add(lop);
                }
            } else {
                return data;
            }
        }
        data.add(informations);
        data.add(menu);
        data.add(quyen);
        return data;
    }
}
