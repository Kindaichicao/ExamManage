/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_EXAM;

import BUS_EXAM.BaseBUS;
import DTO_EXAM.*;
import GUI_Exam.LoiKetNoiFrame;
import GUI_Exam.MainFrame;
import java.sql.*;
import java.io.*;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author ASUS
 */
public class BaseDAO {

    private Connection connection;
    protected Statement statement;
    private ResultSet resultSet;
    private String errorMessage;
    private String activeSql;
    private PreparedStatement activeStatement;

    public BaseDAO() {
        errorMessage = "no error";
        connection = null;
        statement = null;
        resultSet = null;
        activeSql = null;
        connectionDB();
    }

    public Connection getConnection() {
        return connection;
    }

    //Kết nối database
    public void connectionDB() {
        try {
            connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/qlttn", "root", "");
            if (connection != null) {
                statement = (Statement) connection.createStatement();
            } else {
                MainFrame.isKN.show();
            }
        } catch (Exception e) {
            MainFrame.isKN.show();
        }
    }

    public ResultSet query(String sql) {

        if (sql == null || sql.isEmpty()) {
            System.err.println("[WARNING] SQL string is null or empty in "
                    + "query()");
            return resultSet;
        }

        try {
            // check connection before executing SQL
            if (connection == null || connection.isClosed()) {
                System.err.println("[WARNING] Connection is NOT established. "
                        + "Make connection to DB before calling query().");
                return resultSet;
            }

            // execute sql
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            System.err.println("[SQL ERROR] " + e.getSQLState() + ": "
                    + e.getMessage());

        } catch (Exception e) {
            System.err.println("[ERROR]: " + e.getMessage());
        }

        return resultSet;
    }

    //////////////////////////////////////////////////////////////////////////
    // Execute dynamic SQL query statement. It returns ResultSet object if
    // successful, otherwise returns null.
    //////////////////////////////////////////////////////////////////////////
    public ResultSet query(String sql, ArrayList<Object> params) {
        // init return value
        resultSet = null;
        try {
            // create new prepared statement only if sql was changed
            if (!sql.equals(activeSql)) {
                activeStatement = connection.prepareStatement(sql);
                activeSql = sql;
            }

            // set all parameter values of prepared statement
            if (params != null) {
                setParametersForPreparedStatement(params);

            } else {
                System.err.println("[WARNING] SQL string is null or empty in "
                        + "query()");
                return resultSet;
            }

            // check connection before executing SQL
            if (connection == null || connection.isClosed()) {
                System.err.println("[WARNING] Connection is NOT established. "
                        + "Make connection to DB before calling query().");
                return resultSet;
            }

            // execute the prepared statement
            resultSet = activeStatement.executeQuery();

        } catch (SQLException e) {
            System.err.println("[SQL ERROR] " + e.getSQLState() + ": "
                    + e.getMessage());

        } catch (Exception e) {
            System.err.println("[ERROR]: " + e.getMessage());
        }

        return resultSet;
    }

    //////////////////////////////////////////////////////////////////////////
    // Execute static SQL update statement. It returns 0 or # of rows are
    // changed if successful, otherwise returns -1.
    //////////////////////////////////////////////////////////////////////////
    public int update(String sql) {
        // init return value
        int result = -1;

        // validate input
        if (sql == null || sql.isEmpty()) {
            System.err.println("[WARNING] SQL string is null or empty in "
                    + "query()");
            return result;
        }

        try {
            // check connection before executing SQL
            if (connection == null || connection.isClosed()) {
                System.err.println("[WARNING] Connection is NOT established. "
                        + "Make connection to DB before calling query().");
                return result;
            }

            // execute sql
            result = statement.executeUpdate(sql);

        } catch (SQLException e) {
            System.err.println("[SQL ERROR] " + e.getSQLState() + ": "
                    + e.getMessage());

        } catch (Exception e) {
            System.err.println("[ERROR]: " + e.getMessage());
        }

        return result;
    }

    //////////////////////////////////////////////////////////////////////////
    // Execute dynamic SQL update statement. It returns 0 or # of rows are
    // changed if successful, otherwise returns -1.
    //////////////////////////////////////////////////////////////////////////
    public int update(String sql, ArrayList<Object> params) {
        // init return value
        int result = -1;

        try {
            // create new prepared statement only if sql was changed
            if (!sql.equals(activeSql)) {
                activeStatement = connection.prepareStatement(sql);
                activeSql = sql;
            }

            // set all parameter values of prepared statement
            if (params != null) {
                setParametersForPreparedStatement(params);

            } else {
                System.err.println("[WARNING] SQL string is null or empty in "
                        + "query()");
                return result;
            }

            // check connection before executing SQL
            if (connection == null || connection.isClosed()) {
                System.err.println("[WARNING] Connection is NOT established. "
                        + "Make connection to DB before calling query().");
                return result;
            }

            // execute the prepared statement
            result = activeStatement.executeUpdate();

        } catch (SQLException e) {
            System.err.println("[SQL ERROR] " + e.getSQLState() + ": "
                    + e.getMessage());

        } catch (Exception e) {
            System.err.println("[ERROR]: " + e.getMessage());
        }

        return result;
    }

    //////////////////////////////////////////////////////////////////////////
    // Set the params of the prepared statement.
    // It will cast each param to original data type before calling setXXX()
    //////////////////////////////////////////////////////////////////////////
    private void setParametersForPreparedStatement(ArrayList<Object> params) {
        errorMessage = "";
        Object param = null;
        try {
            for (int i = 0; i < params.size(); ++i) {
                param = params.get(i);
                if (param instanceof Integer) {
                    activeStatement.setInt(i + 1, (int) param);
                } else if (param instanceof Double) {
                    activeStatement.setDouble(i + 1, (double) param);
                } else if (param instanceof String) {
                    activeStatement.setString(i + 1, (String) param);
                } else if (param instanceof Long) {
                    activeStatement.setLong(i + 1, (long) param);
                } else if (param instanceof Date) {
                    activeStatement.setDate(i + 1, (Date) param);
                } else if (param instanceof Time) {
                    activeStatement.setTime(i + 1, (Time) param);
                } else if (param instanceof Blob) {
                    activeStatement.setBlob(i + 1, (Blob) param);
                }
            }
        } catch (SQLException e) {
            errorMessage = e.getSQLState() + ": " + e.getMessage();
            System.err.println(errorMessage);
        } catch (Exception e) {
            errorMessage = e.getMessage();
            System.err.println(errorMessage);
        }
    }

    // Hàm lấy số lượng đối tương, tham số là tên bảng
    public int getNumberOf(String nameTable) {
        ResultSet rs = query("select count(*) as number from `" + nameTable + "` where 1");
        try {
            rs.next();
            return rs.getInt("number");
        } catch (Exception e) {
            System.err.print(e);
            return -1;
        }
    }

    //Hàm lấy thuộc tính của Object, tham số là đối tượng cần lấy field
    public ArrayList<Field> getField(Object obj) {
        ArrayList arrGetField = new ArrayList();
        for (Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            arrGetField.add(field);
        }
        if (obj instanceof NguoiDungDTO) {
            for (Field field : obj.getClass().getSuperclass().getDeclaredFields()) {
                field.setAccessible(true);
                arrGetField.add(field);
            }
            if (obj instanceof SinhVienDTO) {
                arrGetField.remove(2);
                arrGetField.add(0, arrGetField.get(1));
                arrGetField.remove(2);
            }
            if (obj instanceof AdminDTO) {
                arrGetField.remove(1);
            }
        }
        return arrGetField;

    }

    //Hàm lấy giá trị string của một đối tượng, tham số là đối tượng cần lấy giá trị
    public ArrayList getFieldValue(Object obj) {
        ArrayList arrGetField = new ArrayList();
        for (Field field : getField(obj)) {
            try {
                arrGetField.add(String.valueOf(field.get(obj)));
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(BaseDAO.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            } catch (IllegalAccessException ex) {
                Logger.getLogger(BaseDAO.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }
        return arrGetField;
    }

    //Hàm lấy tên các thuộc tính, tham số là đối tượng cần lấy tên field
    public ArrayList getFieldName(Object obj) {
        ArrayList arrGetField = new ArrayList();
        for (Field field : getField(obj)) {
            arrGetField.add(field.getName());
        }
        return arrGetField;
    }

    //Hàm lấy tên cột của bảng, tham số là tên bảng sql
    public ArrayList getColumnName(String tableName) {
        ArrayList getColumnName = new ArrayList();
        Statement st = null;
        try {
            st = connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(BaseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        ResultSet rs = null;
        try {
            rs = st.executeQuery("Select * from `" + tableName + "` " + "where 1");
        } catch (SQLException ex) {
            Logger.getLogger(BaseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        ResultSetMetaData rsm = null;
        try {
            rsm = (ResultSetMetaData) rs.getMetaData();
        } catch (SQLException ex) {
            Logger.getLogger(BaseDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        try {
            for (int i = 1; i <= rsm.getColumnCount(); i++) {
                getColumnName.add(rsm.getColumnName(i));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        try {
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(BaseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getColumnName;
    }

    //Hàm tạo user
    public UserDTO createUser(NguoiDungDTO nguoidung) {
        UserDTO user = new UserDTO();
        user.setMaUser(nguoidung.getMaUser());
        user.setMatKhau("123456");
        user.setTrangThai(1);
        String chucvu = "";
        if (nguoidung instanceof SinhVienDTO) {
            chucvu = "CV04";
        } else if (nguoidung instanceof NhanVienDTO) {
            chucvu = "CV03";
        } else if (nguoidung instanceof GiangVienDTO) {
            chucvu = "CV02";
        } else {
            chucvu = "CV01";
        }
        user.setMaChucVu(chucvu);
        return user;
    }

    //Hàm thêm theo đối tượng, tham số là đối tượng cần thêm và tên bảng
    public boolean themDoiTuong(Object obj, String tableName) {
        String sql = "INSERT INTO " + tableName + " (";
        for (int i = 0; i < getColumnName(tableName).size() - 1; i++) {
            sql += getColumnName(tableName).get(i) + ",";
        }
        sql += getColumnName(tableName).get(getColumnName(tableName).size() - 1) + ") VALUES (";
        int i = 0;
        for (i = 0; i < getFieldValue(obj).size() - 1; i++) {
            sql += "'" + getFieldValue(obj).get(i) + "', ";
        }
        sql += "'" + getFieldValue(obj).get(i) + "' )";
        if (obj instanceof NguoiDungDTO) {
            themDoiTuong(createUser((NguoiDungDTO) obj), "user");
        }
        try {
            return !statement.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(BaseDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    //Hàm sửa đối tượng, tham số là đối tượng chứa thông tin mới, tên bảng và mã đối tượng cần sửa
    public boolean suaDoiTuong(Object obj, String tableName, String maObject) {
        String sql = "UPDATE `" + tableName + "` SET `";
        int i;
        for (i = 1; i < getColumnName(tableName).size() - 1; i++) {
            try {
                sql += getColumnName(tableName).get(i) + "`= '" + getFieldValue(obj).get(i) + "', `";
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(BaseDAO.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
        sql += getColumnName(tableName).get(i) + "`= " + getFieldValue(obj).get(i) + " WHERE `" + getColumnName(tableName).get(0) + "`= " + "'" + maObject + "'";
        try {
            return !statement.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(BaseDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public Object taoDoiTuong(Object obj) {
        for (Constructor<?> constructor : obj.getClass().getDeclaredConstructors()) {
            if (constructor.getParameterTypes().length == 0) try {
                obj = constructor.newInstance();
            } catch (InstantiationException ex) {
                Logger.getLogger(BaseDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(BaseDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(BaseDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(BaseDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return obj;
    }

    public Object getDoiTuong(String maDoiTuong, String tableName, Object obj) {
        obj = taoDoiTuong(obj);
        try {
            ResultSet rs = query("Select * from `" + tableName + "` where " + getColumnName(tableName).get(0) + "= '" + maDoiTuong + "'");
            if (rs.next()) {
                for (int i = 0; i < getField(obj).size(); i++) {
                    try {
                        getField(obj).get(i).set(obj, rs.getString(i + 1));
                    } catch (IllegalArgumentException ex) {
                        try {
                            getField(obj).get(i).set(obj, rs.getInt(i + 1));
                        } catch (IllegalArgumentException ex1) {
                            Logger.getLogger(BaseDAO.class.getName()).log(Level.SEVERE, null, ex1);
                            return null;
                        } catch (IllegalAccessException ex1) {
                            Logger.getLogger(BaseDAO.class.getName()).log(Level.SEVERE, null, ex1);
                            return null;
                        }
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(BaseDAO.class.getName()).log(Level.SEVERE, null, ex);
                        return null;
                    }
                }
                rs.close();
                return obj;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    //Hàm đọc danh sách đối tượng, tham số là tên bảng và một đối tượng thuộc kiểu đối tượng muốn đọc
    public ArrayList getArrayList(Object obj, String tableName) {
        ArrayList getArrayList = new ArrayList<Object>();
        String sql = "select * from `" + tableName + "` where TrangThai = 1";
        ResultSet rs = query(sql);
        try {
            while (rs.next()) {
                try {
                    Object temp = taoDoiTuong(obj);
                    for (int i = 0; i < getField(obj).size(); i++) {
                        try {
                            getField(obj).get(i).set(temp, rs.getString(i + 1));
                        } catch (IllegalArgumentException ex) {
                            try {
                                getField(obj).get(i).set(temp, rs.getInt(i + 1));
                            } catch (IllegalArgumentException ex1) {
                                Logger.getLogger(BaseDAO.class.getName()).log(Level.SEVERE, null, ex1);
                                return null;
                            } catch (IllegalAccessException ex1) {
                                Logger.getLogger(BaseDAO.class.getName()).log(Level.SEVERE, null, ex1);
                                return null;
                            }
                        } catch (IllegalAccessException ex) {
                            System.err.println("Class can't acesssed");
                            Logger.getLogger(BaseDAO.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    getArrayList.add(temp);
                } catch (Exception e) {
                    System.err.println("Error in line 433");
                    System.err.print(e);
                    return null;
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error in result");
            Logger.getLogger(BaseDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return getArrayList;
    }

    //Tạo CellStyle cho xuất excel
    public CellStyle getCellStyleTitleCol(Workbook wb) {
        //Tạo font
        Font font = wb.createFont();
        font.setBold(true);

        //Tạo cellstyle
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setFont(font);
        return cellStyle;
    }

    //Hàm đọc file Excel, tham số là tên file, một đối tượng thuộc loại đối
    //tượng cần đọc và tên bảng cần thêm
    public boolean importExcel(String fileName, Object obj, String tableName) {

        //Đọc file
        InputStream ipStream;
        try {
            ipStream = new FileInputStream(new File(fileName));
        } catch (FileNotFoundException ex) {
            System.out.println("File not exist");
            Logger.getLogger(BaseDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        Workbook wb = null;
        try {
            if (fileName.endsWith("xlsx")) {
                wb = new XSSFWorkbook(ipStream);
            } else if (fileName.endsWith("xls")) {
                wb = new HSSFWorkbook(ipStream);
            } else {
                throw new IllegalArgumentException("The specified file is not Excel file");
            }
        } catch (IOException ex) {
            Logger.getLogger(BaseDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error in line 532");
            return false;
        }
        Sheet sheet = wb.getSheetAt(0);
        Iterator<Row> iterator = sheet.iterator();

        //Đọc từng hàng
        Row nextRow = iterator.next();
        while (iterator.hasNext()) {
            nextRow = iterator.next();

            //Đọc từng cell
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            int i = 1;
            Object temp = new Object();
            try {
                temp = taoDoiTuong(obj);
            } catch (IllegalArgumentException ex) {
                System.err.println("Error in line 555");
                Logger.getLogger(BaseDAO.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                //Lấy giá trị gán vào field của Object:
                try {
                    getField(temp).get(i).set(temp, cell.getStringCellValue());
                    i++;
                } catch (IllegalArgumentException ex) {
                    try {
                        getField(obj).get(i).set(temp, Integer.parseInt(cell.getStringCellValue()));
                        i++;
                    } catch (IllegalArgumentException ex1) {
                        Logger.getLogger(BaseDAO.class.getName()).log(Level.SEVERE, null, ex1);
                        System.err.println("Error in line 579");
                        return false;
                    } catch (IllegalAccessException ex1) {
                        Logger.getLogger(BaseDAO.class.getName()).log(Level.SEVERE, null, ex1);
                        System.err.println("Error in line 583");
                        return false;
                    }
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(BaseBUS.class.getName()).log(Level.SEVERE, null, ex);
                    System.err.println("Error in line 588");
                    return false;
                }
            }
            themDoiTuong(temp, tableName);
        }
        try {
            wb.close();
        } catch (IOException ex) {
            Logger.getLogger(BaseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    //Hàm xuất danh sách ra excel, tham số là danh sách cần in, đối tượng, tên file và tiêu đề file
    public boolean exportExcel(ArrayList listUser, Object obj, String nameFile) {
        //Khai báo:
        Workbook workBook = new XSSFWorkbook();
        String nameSheet = WorkbookUtil.createSafeSheetName(nameFile);
        Sheet sheet = workBook.createSheet(nameSheet);
        ArrayList arr = getFieldName(obj);
        ArrayList arrInfo = getFieldValue(obj);
        Cell[] cell = new Cell[arr.size()];
        int rowNum = 0;
        Row row = sheet.createRow(rowNum++);
        Cell titleCell = row.createCell(0);
        titleCell.setCellStyle(getCellStyleTitleCol(workBook));

        //In tiêu đề cột
        for (int i = 0; i < arr.size(); i++) {
            cell[i] = row.createCell(i);
            cell[i].setCellValue(String.valueOf(arr.get(i)));
            cell[i].setCellStyle(getCellStyleTitleCol(workBook));
        }

        //In thông tin của bảng
        for (Object element : listUser) {
            row = sheet.createRow(rowNum++);
            for (int i = 0; i < arr.size(); i++) {
                cell[i] = row.createCell(i);
                cell[i].setCellValue(String.valueOf(getFieldValue(element).get(i)));
                sheet.autoSizeColumn(i);
            }
        }

        //Xuất file:
        try {
            FileOutputStream outputStream = new FileOutputStream(nameFile);
            workBook.write(outputStream);
            workBook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //Quaan
    public static String toDate(String date) {
        String d = "";
        String[] time = date.split("-");
        d = time[2] + "-" + time[1] + "-" + time[0];
        return d;
    }
}
