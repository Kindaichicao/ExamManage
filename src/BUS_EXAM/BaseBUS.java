/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS_EXAM;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author Admin
 */
public abstract class BaseBUS {

    /**
     * Hàm để lấy một đối tượng
     *
     * @param maObject: Mã đối tượng cần lấy
     * @return Đối tượng có mã như yêu cầu, null nếu không tìm thấy
     */
    public abstract Vector getThongTin(int trang);

    public abstract int coutThongTin();

    public abstract Object getObject(String maObject);

    /**
     * Hàm lấy danh sách đối tượng
     *
     * @return ArrayList chứa các đối tượng có trong bảng, có trạng thái bằng 1. Trả về null nếu không lấy được danh sách
     */
    public abstract ArrayList getList();

    /**
     * Hàm thêm đối tượng
     *
     * @param obj: Đối tượng cần thêm.
     * @return true nếu thêm thành công và ngược lại.
     */
    public abstract boolean them(Object obj);

    /**
     * Hàm sửa đối tượng
     *
     * @param obj : Đối tượng truyền vào, thay thế thông tin đối tượng cũ.
     * @param maObject: Mã đối tượng cần sửa.
     * @return true nếu thực hiện thành công và ngược lại.
     */
    public abstract boolean sua(Object obj, String maObject);

    /**
     * Hàm xóa đối tượng
     *
     * @param maObject: Mã của đối tượng cần xóa (xóa chỉ set Trạng Thái của đối tượng về 0).
     * @return true nếu xóa thành công và ngược lại.
     */
    public abstract boolean xoa(String maObject);

    /**
     * Hàm import dữ liệu bằng file excel
     *
     * @param fileName: Tên file, phải có đuôi là ".xlsx" hoặc ".xls" File import phải có cấu trúc như mẫu.
     * @return true nếu import thành công và ngược lại.
     */
    public abstract boolean importExcel(String fileName);

    /**
     * Hàm export dữ liệu ra file excel
     *
     * @param fileName: Tên file, phải có đuôi là ".xlsx" hoặc ".xls"
     * @return true nếu export thành công và ngược lại.
     */
    public abstract boolean exportExcel(String fileName);

    /**
     * Hàm kiểm tra xem đối tượng đã tồn tại hay chưa
     *
     * @param maObject Mã của đối tượng cần kiểm tra
     * @return true nếu đã tồn tại và ngược lại. Chỉ kiểm tra trong những đối tượng có trạng thái bằng 1.
     */
    public abstract boolean isExisted(String maObject);

    /**
     * Hàm kiểm tra xem file có phải là file excel hay không
     *
     * @param fileName: Tên file cần kiểm tra
     * @return true nếu là file excel và ngược lại
     */
    public boolean isExcelFile(String fileName) {
        if (fileName.endsWith("xlsx") || fileName.endsWith("xls")) {
            return true;
        }
        return false;
    }

}
