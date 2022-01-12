/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS_EXAM;

import DAO_EXAM.BaiThiKyThiDAO;
import DTO_EXAM.BaiThiDTO;

/**
 *
 * @author ASUS
 */
public class BaiThiKyThiBUS {

    public boolean add(BaiThiDTO baithi, String maKyThi) {
        BaiThiKyThiDAO daoBTKT = new BaiThiKyThiDAO();
        daoBTKT.add(baithi, maKyThi);
        return true;
    }
}
