/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_EXAM;

import DTO_EXAM.CauHoiDTO;

/**
 *
 * @author ASUS
 */
public class ContainerDAO {

    public static GiangVienDAO daoGV = new GiangVienDAO();
    public static NhanVienDAO daoNV = new NhanVienDAO();
    public static SinhVienDAO daoSV = new SinhVienDAO();
    public static UserDAO daoUser = new UserDAO();
    public static KhoaDAO daoKhoa = new KhoaDAO();
    public static LopDAO daoLop = new LopDAO();
    public static ChiTietQuyenDAO daoQCV = new ChiTietQuyenDAO();
    public static ChucNangDAO daoChucNang = new ChucNangDAO();
    public static BaseDAO daoBase = new BaseDAO();
    public static MonSinhVienDAO daoMSV = new MonSinhVienDAO();
    public static MonDAO daoMon = new MonDAO();
    public static KyThiDAO daoKyThi = new KyThiDAO();
    public static DeThiDAO daoDeThi = new DeThiDAO();
    public static CauHoiDAO daoCauHoi = new CauHoiDAO();
    public static DeCauHoiDAO daoDeCauHoi = new DeCauHoiDAO();
    public static BaiThiDAO daoBaiThi = new BaiThiDAO();
    public static ChiTietQuyenDAO daoCTQ = new ChiTietQuyenDAO();
    public static GiangVienMonDAO daoGVM = new GiangVienMonDAO();

}
