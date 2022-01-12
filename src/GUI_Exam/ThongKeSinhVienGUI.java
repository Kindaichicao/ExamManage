/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_Exam;

import BUS_EXAM.KyThiBUS;
import BUS_EXAM.SinhVienBUS;
import DTO_EXAM.MonDTO;
import DTO_EXAM.SinhVienDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

/**
 *
 * @author Admin
 */
public class ThongKeSinhVienGUI {

    Vector<Vector<String>> dataSinhVien = new Vector<Vector<String>>();
    Vector<String> headerSinhVien = new Vector<String>();
    int columnCount = headerSinhVien.size();

    DefaultTableModel modelSV = new DefaultTableModel(dataSinhVien, headerSinhVien);
    DefaultTableModel modelSVbegin = new DefaultTableModel(dataSinhVien, headerSinhVien);

    MyTable tableSinhVien = new MyTable();

    DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();

    SinhVienBUS sinhvien = new SinhVienBUS();
    ArrayList<SinhVienDTO> listSinhVien = sinhvien.getList();

    JScrollPane scrSV = new JScrollPane(tableSinhVien);

    String maKyThi = new String();
    String maSinhVien = new String();

    public void setMaKyThi(String maKyThi) {
        this.maKyThi = maKyThi;
    }

    public ThongKeSinhVienGUI(String maKyThi, String maSinhVien) {
        this.maKyThi = maKyThi;
        this.maSinhVien = maSinhVien;
        iniDataTable(maSinhVien);
    }

    public ThongKeSinhVienGUI(String maKyThi) {
        this.maKyThi = maKyThi;
        iniDataTable(maSinhVien);
    }

    /**
     * Tạo dữ liệu table
     *
     */
    public void iniDataTable(String maSinhVien) {
        //Thống kê điểm thi
        headerSinhVien.add("STT");
        headerSinhVien.add("Mã Môn");
        headerSinhVien.add("Tên môn");
        headerSinhVien.add("Số câu đúng");
        headerSinhVien.add("Điểm thi");
        headerSinhVien.add("Xếp loại");

        updateData(maSinhVien);

        tableSinhVien.setModel(modelSV);
        tableSinhVien.revalidate();
    }

    public void updateData(String maSV) {
        SinhVienBUS sinhvien = new SinhVienBUS();
        ArrayList<MonDTO> listMon = sinhvien.getListMonKyThi(maSV, maKyThi);
        for (int i = 0; i < listMon.size(); i++) {
            Vector rowData = new Vector();
            MonDTO mondto = listMon.get(i);
            rowData.add(String.valueOf(i + 1));
            rowData.add(mondto.getMaMon());
            rowData.add(mondto.getTenMon());
            rowData.add(sinhvien.getBaiThiMon(maSV, mondto.getMaMon()).getSoCauDung());
            rowData.add(sinhvien.getBaiThiMon(maSV, mondto.getMaMon()).getDiem());
            rowData.add(sinhvien.getXepLoaiMon(maSV, mondto.getMaMon()));
            dataSinhVien.add(rowData);
        }
        tableSinhVien.setModel(modelSV);
    }

    public JPanel tableSinhVienPanel(int width, int height) {
        JPanel tablePanel = new JPanel();
        tableSinhVien.setModel(modelSV);
        tablePanel.setLayout(new BorderLayout());
        tablePanel.add(scrSV);
        tablePanel.setPreferredSize(new Dimension(width - 150, height - 250));
        return tablePanel;
    }

    public JPanel panelTableSV(int width, int height, JPanel coverPanel, JLabel lb1, JLabel lb2) {
        JPanel panelSinhVien = new JPanel();
        panelSinhVien.setBackground(Color.WHITE);
        panelSinhVien.setPreferredSize(new Dimension(width - 150, 50));
        SinhVienBUS sv = new SinhVienBUS();
        Vector sinhvienVct = sv.getTenSinhVien(maKyThi);
        ArrayList<SinhVienDTO> sinhvienList = sv.getList();
        MyComboBox sinhvienCB = new MyComboBox(sinhvienVct);

        panelSinhVien.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));
        panelSinhVien.add(sinhvienCB);

        JLabel sinhvienName = new JLabel();
        JLabel sinhvienClass = new JLabel();
        JLabel sinhvienDay = new JLabel();
        JLabel sinhvienGT = new JLabel();

        SinhVienDTO sinhvien = sinhvienList.get(sinhvienCB.getSelectedIndex());
        sinhvienName.setText("Họ và tên: " + sinhvien.getHoDem() + " " + sinhvien.getTen());
        sinhvienClass.setText("Mã lớp: " + sinhvien.getMaLop());
        sinhvienDay.setText("Ngày sinh: " + sinhvien.getNgaySinh());
        sinhvienGT.setText("Giới tính: " + sinhvien.getGioiTinh());

        panelSinhVien.add(sinhvienCB);
        panelSinhVien.add(sinhvienName);
        panelSinhVien.add(sinhvienClass);
        panelSinhVien.add(sinhvienDay);
        panelSinhVien.add(sinhvienGT);

        String maSinhVien = sinhvienList.get(sinhvienCB.getSelectedIndex()).getMaUser();

        coverPanel.removeAll();
        coverPanel.add(panelSinhVien);
        coverPanel.repaint();
        coverPanel.revalidate();
        ThongKeSinhVienGUI sinhvienGUI = new ThongKeSinhVienGUI(maKyThi, maSinhVien);
        coverPanel.add(sinhvienGUI.tableSinhVienPanel(width, height));

        sinhvienCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (sinhvienCB.getSelectedIndex() != -1) {
                    SinhVienDTO sinhvien = sinhvienList.get(sinhvienCB.getSelectedIndex());
                    sinhvienName.setText("Họ và tên: " + sinhvien.getHoDem() + " " + sinhvien.getTen());
                    sinhvienClass.setText("Mã lớp: " + sinhvien.getMaLop());
                    sinhvienDay.setText("Ngày sinh: " + sinhvien.getNgaySinh());
                    sinhvienGT.setText("Giới tính: " + sinhvien.getGioiTinh());

                    panelSinhVien.removeAll();
                    panelSinhVien.add(sinhvienCB);
                    panelSinhVien.add(sinhvienName);
                    panelSinhVien.add(sinhvienClass);
                    panelSinhVien.add(sinhvienDay);
                    panelSinhVien.add(sinhvienGT);
                    panelSinhVien.repaint();
                    panelSinhVien.revalidate();

                    String maSinhVien = sinhvienList.get(sinhvienCB.getSelectedIndex()).getMaUser();
                }
            }
        });
        return panelSinhVien;
    }

    public JPanel panelSinhVienChart(JPanel coverPanel, int width, int height) {
        JPanel panelSinhVienCover = new JPanel();
        JPanel panelSinhVien = new JPanel();
        panelSinhVien.setBackground(Color.WHITE);
        panelSinhVien.setPreferredSize(new Dimension(width - 150, 50));
        SinhVienBUS sv = new SinhVienBUS();
        Vector sinhvienVct = sv.getTenSinhVien(maKyThi);
        ArrayList<SinhVienDTO> sinhvienList = sv.getList();
        JComboBox sinhvienCB = new JComboBox(sinhvienVct);

        panelSinhVien.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));
        panelSinhVien.add(sinhvienCB);

        JLabel sinhvienName = new JLabel();
        JLabel sinhvienClass = new JLabel();
        JLabel sinhvienDay = new JLabel();
        JLabel sinhvienGT = new JLabel();
        String maSinhVien = sinhvienList.get(sinhvienCB.getSelectedIndex()).getMaUser();
        coverPanel.removeAll();
        coverPanel.add(panelSinhVien);
        coverPanel.add(chartSinhVienPanel(width, height, maSinhVien));
        coverPanel.repaint();

        sinhvienCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (sinhvienCB.getSelectedIndex() != -1) {
                    SinhVienDTO sinhvien = sinhvienList.get(sinhvienCB.getSelectedIndex());
                    sinhvienName.setText("Họ và tên: " + sinhvien.getHoDem() + " " + sinhvien.getTen());
                    sinhvienClass.setText("Mã lớp: " + sinhvien.getMaLop());
                    sinhvienDay.setText("Ngày sinh: " + sinhvien.getNgaySinh());
                    sinhvienGT.setText("Giới tính: " + sinhvien.getGioiTinh());

                    panelSinhVien.removeAll();
                    panelSinhVien.add(sinhvienCB);
                    panelSinhVien.add(sinhvienName);
                    panelSinhVien.add(sinhvienClass);
                    panelSinhVien.add(sinhvienDay);
                    panelSinhVien.add(sinhvienGT);
                    panelSinhVien.repaint();
                    panelSinhVien.revalidate();

                    String maSinhVien = sinhvien.getMaUser();
                    coverPanel.removeAll();
                    coverPanel.add(panelSinhVien);
                    coverPanel.add(chartSinhVienPanel(width, height, maSinhVien));
                    coverPanel.repaint();
                    coverPanel.revalidate();
                }
            }
        });
        return panelSinhVien;
    }

    //Lấy dữ liệu cho chart:
    public CategoryDataset setDataChart(String maSinhVien) {

        DefaultCategoryDataset chartdata = new DefaultCategoryDataset();
        SinhVienBUS sinhvien = new SinhVienBUS();
        ArrayList<MonDTO> monList = sinhvien.getListMonKyThi(maSinhVien, maKyThi);
        for (MonDTO mon : monList) {
            System.out.println(sinhvien.getBaiThiMon(maSinhVien, mon.getMaMon()).getMaSinhVien());
            Double diem = Double.parseDouble(sinhvien.getBaiThiMon(maSinhVien, mon.getMaMon()).getDiem());
            chartdata.addValue(diem, "Điểm", mon.getTenMon());
        }
        return chartdata;
    }

    public JPanel chartSinhVienPanel(int width, int height, String maSV) {
        //Khai báo Panel chứa biểu đồ
        JPanel chartKTPanel = new JPanel();

        KyThiBUS kythi = new KyThiBUS();

        //Tạo chart
        JFreeChart chart = ChartFactory.createBarChart("Biểu đồ kết quả kỳ thi của sinh viên", "Môn", "Điểm", setDataChart(maSV), PlotOrientation.VERTICAL, false, false, false);

        //Hiện dữ liệu khi hover:
        chart.setElementHinting(true);

        //Tạo chartPanel:
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(600, 300));

        chartKTPanel.add(chartPanel);
        chartKTPanel.setPreferredSize(new Dimension(600, 300));
        return chartKTPanel;
    }
}
