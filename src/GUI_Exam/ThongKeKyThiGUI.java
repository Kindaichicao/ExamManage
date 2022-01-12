/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_Exam;

import BUS_EXAM.KyThiBUS;
import BUS_EXAM.SinhVienBUS;
import DTO_EXAM.SinhVienDTO;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

/**
 *
 * @author Admin
 */
public class ThongKeKyThiGUI {

    Vector<Vector<String>> dataKyThi = new Vector<Vector<String>>();
    Vector<String> headerKyThi = new Vector<String>();
    int columnCount = headerKyThi.size();

    DefaultTableModel model = new DefaultTableModel(dataKyThi, headerKyThi);
    DefaultTableModel modelbegin = new DefaultTableModel(dataKyThi, headerKyThi);

    MyTable tableKyThi = new MyTable();

    DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();

    SinhVienBUS sinhvien = new SinhVienBUS();
    ArrayList<SinhVienDTO> listSinhVien = sinhvien.getList();

    JScrollPane scrKyThi = new JScrollPane(tableKyThi);

    String maKyThi = new String();

    public void setMaKyThi(String maKyThi) {
        this.maKyThi = maKyThi;
    }

    public ThongKeKyThiGUI() {
        iniDataTable();
    }

    public ThongKeKyThiGUI(String maKyThi) {
        this.maKyThi = maKyThi;
        iniDataTable();
    }

    /**
     * Tạo dữ liệu table
     *
     */
    public void iniDataTable() {
        //Thống kê kỳ thi
        headerKyThi.add("STT");
        headerKyThi.add("Mã SV");
        headerKyThi.add("Họ tên sinh viên");
        headerKyThi.add("Ngày sinh");
        headerKyThi.add("Giới tính");
        headerKyThi.add("ĐTB kỳ thi");
        headerKyThi.add("Xếp loại");

        System.out.println(maKyThi + " Testt");
        //Data kỳ thi
        SinhVienBUS sinhvien = new SinhVienBUS();
        ArrayList<SinhVienDTO> listSinhVien = sinhvien.getList();
        for (int i = 0; i < listSinhVien.size(); i++) {
            Vector rowData = new Vector();
            SinhVienDTO sinhviendto = listSinhVien.get(i);
            if (sinhvien.getListBaiThiKyThi(sinhviendto.getMaUser(), maKyThi).size() > 0) {
                rowData.add(String.valueOf(i + 1));
                rowData.add(sinhviendto.getMaUser());
                rowData.add(sinhviendto.getHoDem() + " " + sinhviendto.getTen());
                rowData.add(sinhviendto.getNgaySinh());
                rowData.add(sinhviendto.getGioiTinh());
                rowData.add(sinhvien.getDiemTrungBinh(sinhviendto.getMaUser(), maKyThi));
                rowData.add(sinhvien.getXepLoai(sinhviendto.getMaUser(), maKyThi));
                dataKyThi.add(rowData);
            }
        }
        tableKyThi.setModel(model);
        tableKyThi.revalidate();
    }

    /**
     * Tạo Panel chứa bảng
     *
     * @param width
     * @param height
     * @return
     */
    public JPanel tableKyThiPanel(int width, int height) {
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.add(scrKyThi);
        tablePanel.setPreferredSize(new Dimension(width - 150, height - 200));
        return tablePanel;
    }

    public JPanel tableKyThiPanelBegin(int width, int height) {
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        MyTable tb = new MyTable(modelbegin);
        JScrollPane scrKyThi = new JScrollPane(tb);
        tablePanel.add(scrKyThi);
        tablePanel.setPreferredSize(new Dimension(width - 150, height - 200));
        return tablePanel;
    }

    /**
     * Tạo biểu đồ:
     *
     * @param width
     * @param height
     * @return
     */
    public JPanel chartKyThiPanel(int width, int height) {
        //Khai báo Panel chứa biểu đồ
        JPanel chartKTPanel = new JPanel();

        KyThiBUS kythi = new KyThiBUS();

        //Tạo chart
        JFreeChart chart = ChartFactory.createPieChart3D("Tỉ lệ xếp loại kết quả " + kythi.getObject(maKyThi).getTenKyThi(), setDataChart(maKyThi), true, true, true);
        PiePlot3D plot = (PiePlot3D) chart.getPlot();

        //Set cấu hình cho nhãn
        PieSectionLabelGenerator labelGenerator = new StandardPieSectionLabelGenerator("{0}: ({2})", new DecimalFormat("2"), new DecimalFormat("0.00%"));
        plot.setLabelGenerator(labelGenerator);

        //Set độ trong suốt
        plot.setForegroundAlpha((float) 0.6);
        plot.setBackgroundPaint(new Color(255, 255, 255));

        chart.getTitle().setPaint(new Color(0, 0, 255));
        chart.getTitle().setPadding(5, 5, 5, 5);

        //Set màu cho từng phần:
        plot.setSectionPaint("Xếp loại kém", new Color(120, 0, 120));

        //Hiện dữ liệu khi hover:
        chart.setElementHinting(true);

        //Tạo chartPanel:
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(600, 400));

        //Set chức năng lăn chuột
        chartPanel.setMouseWheelEnabled(true);

        chartKTPanel.add(chartPanel);
        chartKTPanel.setPreferredSize(new Dimension(600, 400));
        return chartKTPanel;
    }

    //Lấy dữ liệu cho chart:
    public PieDataset setDataChart(String maKyThi) {

        DefaultPieDataset chartdata = new DefaultPieDataset();
        double loaiA = 0;
        double loaiB = 0;
        double loaiC = 0;
        double loaiD = 0;
        double loaiF = 0;

        for (int i = 0; i < listSinhVien.size(); i++) {
            String xeploai = sinhvien.getXepLoai(listSinhVien.get(i).getMaUser(), maKyThi);
            if (xeploai.equals("Giỏi")) {
                loaiA++;
            } else if (xeploai.equals("Khá")) {
                loaiB++;
            } else if (xeploai.equals("Trung bình")) {
                loaiC++;
            } else if (xeploai.equals("Yếu")) {
                loaiD++;
            } else {
                loaiF++;
            }
        }

        double tileA = (double) Math.round(loaiA / (loaiA + loaiB + loaiC + loaiD + loaiF) * 100) / 100;
        double tileB = (double) Math.round(loaiB / (loaiA + loaiB + loaiC + loaiD + loaiF) * 100) / 100;
        double tileC = (double) Math.round(loaiC / (loaiA + loaiB + loaiC + loaiD + loaiF) * 100) / 100;
        double tileD = (double) Math.round(loaiD / (loaiA + loaiB + loaiC + loaiD + loaiF) * 100) / 100;
        double tileF = (double) Math.round(loaiF / (loaiA + loaiB + loaiC + loaiD + loaiF) * 100) / 100;

        chartdata.setValue("Xếp loại giỏi", tileA);
        chartdata.setValue("Xếp loại khá", tileB);
        chartdata.setValue("Xếp loại trung bình", tileC);
        chartdata.setValue("Xếp loại yếu", tileD);
        chartdata.setValue("Xếp loại kém", tileF);
        return chartdata;
    }

    //Tạo CellStyle cho xuất excels
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

    public boolean exportExcelThongKe(String fileName) {
        //Khai báo:
        Workbook workBook = new XSSFWorkbook();
        String nameSheet = WorkbookUtil.createSafeSheetName(fileName);
        Sheet sheet = workBook.createSheet(nameSheet);

        Cell[] cell = new Cell[headerKyThi.size()];
        int rowNum = 0;
        Row row = sheet.createRow(rowNum++);
        Cell titleCell = row.createCell(0);

        //In tiêu đề cột
        for (int i = 0; i < cell.length; i++) {
            cell[i] = row.createCell(i);
            cell[i].setCellValue(String.valueOf(headerKyThi.get(i)));
            cell[i].setCellStyle(getCellStyleTitleCol(workBook));
        }

        //In thông tin của bảng
        for (Vector element : dataKyThi) {
            row = sheet.createRow(rowNum++);
            for (int i = 0; i < headerKyThi.size(); i++) {
                cell[i] = row.createCell(i);
                cell[i].setCellValue(String.valueOf(element.get(i)));
                sheet.autoSizeColumn(i);
            }
        }

        //Xuất file:
        try {
            FileOutputStream outputStream = new FileOutputStream(fileName);
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
}
