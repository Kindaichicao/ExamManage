/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS_EXAM;

import DAO_EXAM.ContainerDAO;
import DTO_EXAM.*;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class MonSinhVienBUS {

    //Phần Thanh Thêm
    public ArrayList<MonDTO> getMonList(String idUser) {
        ArrayList<MonDTO> mons = new ArrayList<MonDTO>();
        ArrayList<MonSinhVienDTO> msvs = new ArrayList<MonSinhVienDTO>();
        MonDTO mon;
        msvs = ContainerDAO.daoMSV.getMonSinhVienList(idUser);
        for (MonSinhVienDTO msv : msvs) {
            mon = ContainerDAO.daoMon.getMon(msv.getMaMon());
            if (mon.getTrangThai() == 1) {
                mons.add(mon);
            }
        }
        return mons;
    }

    public ArrayList<MonDTO> getToanBoMonList(String idUser) {
        ArrayList<MonDTO> mons = new ArrayList<MonDTO>();
        ArrayList<MonSinhVienDTO> msvs = new ArrayList<MonSinhVienDTO>();
        MonDTO mon;
        msvs = ContainerDAO.daoMSV.getMonSinhVienList(idUser);
        for (MonSinhVienDTO msv : msvs) {
            mon = ContainerDAO.daoMon.getMon(msv.getMaMon());
            mons.add(mon);
        }
        return mons;
    }
    //Kết thúc Phần Thanh Thêm
}
