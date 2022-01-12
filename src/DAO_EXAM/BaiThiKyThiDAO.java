/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_EXAM;

import DTO_EXAM.BaiThiDTO;

/**
 *
 * @author ASUS
 */
public class BaiThiKyThiDAO extends BaseDAO {

    public void add(BaiThiDTO baithi, String maKyThi) {
        String sql = "INSERT INTO `baithi_kythi`"
                + "(`MaSV`, `MaDe`, `DSCauTraLoi`, `DanhSachDungSai`, `SoCauDung`, "
                + "`MaKyThi`, `Diem`, `TrangThai`) VALUES ('" + baithi.getMaSinhVien() + "','" + baithi.getMaDe() + "','"
                + baithi.getDsCauTraLoi() + "','" + baithi.getDsDungSai() + "'," + baithi.getSoCauDung() + ",'"
                + maKyThi + "','" + baithi.getDiem() + "'," + baithi.getTrangThai();
        super.query(sql);
    }
}
