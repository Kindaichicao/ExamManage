/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO_EXAM;

/**
 *
 * @author Admin
 */
public class CauHoiDTO {

    private String maCauHoi;
    private String maPhanCong;
    private String noiDung;
    private String dapanA;
    private String dapanB;
    private String dapanC;
    private String dapanD;
    private String dapanDung;
    private int doKho;
    private int trangThai;

    public CauHoiDTO() {
        NumberDTO.numberObject.updateNum();
        maCauHoi = NumberDTO.numberObject.idCauHoi();
        maPhanCong = "";
        noiDung = "";
        dapanA = "";
        dapanB = "";
        dapanC = "";
        dapanD = "";
        dapanDung = "";
        doKho = 0;
        trangThai = 1;
    }

    public CauHoiDTO(String maCauHoi, String maPhanCong, String noiDung, String dapanA, String dapanB, String dapanC, String dapacD, String dapanDung, int doKho, int trangThai) {
        this.maCauHoi = maCauHoi;
        this.maPhanCong = maPhanCong;
        this.noiDung = noiDung;
        this.dapanA = dapanA;
        this.dapanB = dapanB;
        this.dapanC = dapanC;
        this.dapanD = dapanD;
        this.dapanDung = dapanDung;
        this.doKho = doKho;
        this.trangThai = trangThai;
    }

    public String getMaCauHoi() {
        return maCauHoi;
    }

    public void setMaCauHoi(String maCauHoi) {
        this.maCauHoi = maCauHoi;
    }

    public String getMaPhanCong() {
        return maPhanCong;
    }

    public void setMaPhanCong(String maPhanCong) {
        this.maPhanCong = maPhanCong;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getDapanA() {
        return dapanA;
    }

    public void setDapanA(String dapanA) {
        this.dapanA = dapanA;
    }

    public String getDapanB() {
        return dapanB;
    }

    public void setDapanB(String dapanB) {
        this.dapanB = dapanB;
    }

    public String getDapanC() {
        return dapanC;
    }

    public void setDapanC(String dapanC) {
        this.dapanC = dapanC;
    }

    public String getDapanD() {
        return dapanD;
    }

    public void setDapanD(String dapanD) {
        this.dapanD = dapanD;
    }

    public String getDapanDung() {
        return dapanDung;
    }

    public void setDapanDung(String dapanDung) {
        this.dapanDung = dapanDung;
    }

    public int getDoKho() {
        return doKho;
    }

    public void setDoKho(int doKho) {
        this.doKho = doKho;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

}
