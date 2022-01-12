/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_Exam;

import BUS_EXAM.KyThiBUS;
import BUS_EXAM.PDFBUS;
import BUS_EXAM.SinhVienBUS;
import DTO_EXAM.KyThiDTO;
import DTO_EXAM.SinhVienDTO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
/**
 * Thống kê phải đi kèm đánh giá - Thống kê theo kỳ thi: sinh viên - điểm trung bình học phần - xếp loại - Thống kê theo sinh viên: sinh viên - môn - điểm môn - điểm trung bình học phần - xếp loại - Thống kê theo môn thi: sinh viên - điểm - xếp loại
 *
 * @author Admin
 */
public class ThongKeGUI {

    JFrame fr = new JFrame();
    EventThongKeGUI event = new EventThongKeGUI();

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int width = (int) ((int) screenSize.getWidth() * 0.7);
    int height = (int) ((int) screenSize.getHeight() * 0.7);

    //ComboBox danh mục
    String[] danhmucStr = {"Thống kê kết quả kỳ thi", "Thống kê điểm sinh viên"};
    MyComboBox danhmucCb = new MyComboBox(danhmucStr);

    //ComboBox kỳ thi
    KyThiBUS kythiBUS = new KyThiBUS();
    Vector<String> listKyThi = kythiBUS.getListTenKyThi();
    JComboBox<String> kythiCb = new JComboBox<String>(listKyThi);
    String selectedKyThi = (String) kythiCb.getSelectedItem();

    //Label chức năng:
    Vector<JLabel> listLabel = new Vector();
    Vector<String> listLabelName = new Vector();
    Vector<String> listIconName = new Vector();
    Vector<String> listIconAdd = new Vector();

    //Lấy mã kì thi
    String maKyThi = kythiBUS.getAllList().get(danhmucCb.getSelectedIndex()).getMaKyThi();

    //Panel center:
    JPanel centerPanel = new JPanel();

    public ThongKeGUI() {
        eventDanhMucCB();
        iniList();
        eventCb();
        kythiCb.setBorder(BorderFactory.createLineBorder(Color.decode("#ACE1AF"), 3));
        kythiCb.setBackground(Color.WHITE);
        kythiCb.setPreferredSize(new Dimension(220, 30));
        event.eventHover(listLabel);
        fr.setSize(width, height);
        fr.setBackground(Color.WHITE);
        fr.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                fr.setVisible(false);
            }
        });
        fr.setLocationRelativeTo(null);
        fr.add(thongkePanel());
        fr.setVisible(true);
    }

    public void iniList() {
        listLabelName.add("Xem thống kê");
        listLabelName.add("Xem biểu đồ");
        listLabelName.add("Xuất Excel");
        listLabelName.add("Xuất báo cáo");
        listLabelName.add("In báo cáo");

        listIconName.add("icon_sta");
        listIconName.add("icon_chart");
        listIconName.add("icon_excel");
        listIconName.add("icon_pdf");
        listIconName.add("icon_print");

        listIconAdd.add("src\\img\\icons8-graph-report-64.png");
        listIconAdd.add("src\\Img\\icon-chart.png");
        listIconAdd.add("src\\Img\\icons8-microsoft-excel-2019-48.png");
        listIconAdd.add("src\\Img\\icons8-report-card-50.png");
        listIconAdd.add("src\\Img\\icons8-print-50.png");

        for (int i = 0; i < listLabelName.size(); i++) {
            ImageIcon iconTemp = new ImageIcon(listIconAdd.get(i), listIconName.get(i));
            JLabel lbTemp = new JLabel(listLabelName.get(i), iconTemp, JLabel.CENTER);
            lbTemp.setBackground(Color.WHITE);
            listLabel.add(lbTemp);
        }
    }

    public void setLabel(JLabel lb) {
        lb.setOpaque(true);
        lb.setBackground(new Color(135, 206, 250));
        MyBorder bd = new MyBorder(lb.getBackground(), 1, 5, 0);
        lb.setBorder(bd);
        lb.setPreferredSize(new Dimension(130, 40));
    }

    public void setComboBox() {
        danhmucCb.setPreferredSize(new Dimension(170, 40));
        kythiCb.setPreferredSize(new Dimension(170, 40));
    }

    public JPanel thongkePanel() {
        JPanel thongkePanel = new JPanel();
        thongkePanel.setBackground(Color.white);
        thongkePanel.setLayout(new BorderLayout());
        thongkePanel.add(northPanel(), BorderLayout.NORTH);
        centerPanel();
        thongkePanel.add(centerPanel, BorderLayout.CENTER);
        return thongkePanel;
    }

    public JPanel northPanel() {
        JPanel northPanel = new JPanel();

        northPanel.setLayout(new GridLayout(2, 1));
        northPanel.setPreferredSize(new Dimension(width, height / 5));

        northPanel.add(filterPanel());
        northPanel.add(chucnangPanel());

        northPanel.setBackground(Color.white);
        return northPanel;
    }

    public JPanel filterPanel() {
        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new FlowLayout(1, 30, 20));
        filterPanel.setPreferredSize(new Dimension((int) ((int) width * 0.6), (int) ((int) height * 0.1)));
        filterPanel.setBackground(Color.WHITE);
        JLabel lbChon = new JLabel("Chọn mục: ");
        JLabel lbKyThi = new JLabel("Chọn kỳ thi: ");

        filterPanel.add(lbChon);
        filterPanel.add(danhmucCb);
        filterPanel.add(lbKyThi);
        filterPanel.add(kythiCb);

        return filterPanel;
    }

    public JPanel chucnangPanel() {
        JPanel chucnangPanel = new JPanel();
        chucnangPanel.setBackground(Color.white);
        chucnangPanel.setLayout(new FlowLayout(1, 30, 10));
        chucnangPanel.setPreferredSize(new Dimension(width, height / 7));
        for (int i = 0; i < listLabel.size(); i++) {
            setLabel(listLabel.get(i));
            chucnangPanel.add(listLabel.get(i));
        }
        return chucnangPanel;
    }

    public void centerPanel() {
        if (danhmucCb.getSelectedIndex() == 0) {
            ThongKeKyThiGUI kythi = new ThongKeKyThiGUI(maKyThi);
            centerPanel.add(kythi.tableKyThiPanelBegin(width, height));
            event.eventPanel(listLabel.get(1), centerPanel, kythi.chartKyThiPanel(width, height), danhmucCb);
            event.eventPanel(listLabel.get(0), centerPanel, kythi.tableKyThiPanel(width, height), danhmucCb);
            event.eventExportExcelKyThi(listLabel.get(2), centerPanel);
            event.eventExportReportKyThi(listLabel.get(3), kythi.maKyThi);
            eventPrintPDF(listLabel.get(4));
        } else if (danhmucCb.getSelectedIndex() == 1) {
            centerPanel.removeAll();
            centerPanel.setBackground(Color.white);

            //Thiết kế giao diện chung
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

            centerPanel.removeAll();
            centerPanel.add(panelSinhVien);
            ThongKeSinhVienGUI sinhvienGUI = new ThongKeSinhVienGUI(maKyThi, maSinhVien);
            centerPanel.add(sinhvienGUI.tableSinhVienPanel(width, height));
            centerPanel.repaint();
            centerPanel.revalidate();

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

                        if (listLabel.get(0).getBackground().equals(new Color(30, 144, 255))) {
                            String maSinhVien = sinhvienList.get(sinhvienCB.getSelectedIndex()).getMaUser();
                            centerPanel.removeAll();
                            centerPanel.add(panelSinhVien);
                            ThongKeSinhVienGUI sinhvienGUI = new ThongKeSinhVienGUI(maKyThi, maSinhVien);
                            centerPanel.add(sinhvienGUI.tableSinhVienPanel(width, height));
                            centerPanel.repaint();
                            centerPanel.revalidate();
                        } else {
                            String maSinhVien = sinhvienList.get(sinhvienCB.getSelectedIndex()).getMaUser();
                            centerPanel.removeAll();
                            centerPanel.add(panelSinhVien);
                            ThongKeSinhVienGUI sinhvienGUI = new ThongKeSinhVienGUI(maKyThi, maSinhVien);
                            centerPanel.add(sinhvienGUI.chartSinhVienPanel(width, height, maSinhVien));
                            centerPanel.repaint();
                            centerPanel.revalidate();
                        }

                    }
                }
            });
            listLabel.get(0).addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    String maSinhVien = sinhvienList.get(sinhvienCB.getSelectedIndex()).getMaUser();
                    centerPanel.removeAll();
                    centerPanel.add(panelSinhVien);
                    ThongKeSinhVienGUI sinhvienGUI = new ThongKeSinhVienGUI(maKyThi, maSinhVien);
                    centerPanel.add(sinhvienGUI.tableSinhVienPanel(width, height));
                    centerPanel.repaint();
                    centerPanel.revalidate();
                }
            });

            listLabel.get(1).addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    String maSinhVien = sinhvienList.get(sinhvienCB.getSelectedIndex()).getMaUser();
                    centerPanel.removeAll();
                    centerPanel.add(panelSinhVien);
                    ThongKeSinhVienGUI sinhvienGUI = new ThongKeSinhVienGUI(maKyThi, maSinhVien);
                    centerPanel.add(sinhvienGUI.chartSinhVienPanel(width, height, maSinhVien));
                    centerPanel.repaint();
                    centerPanel.revalidate();
                }
            });
            centerPanel.repaint();
            centerPanel.revalidate();
        }
    }

    public void eventDanhMucCB() {
        centerPanel.setBackground(Color.WHITE);
        danhmucCb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                centerPanel.removeAll();
                centerPanel();
                centerPanel.repaint();
                centerPanel.revalidate();
            }
        });
    }

    public void eventCb() {
        kythiCb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (kythiCb.getSelectedIndex() != -1) {
                    maKyThi = kythiBUS.getAllList().get(kythiCb.getSelectedIndex()).getMaKyThi();
                    for (JLabel temp : listLabel) {
                        if (!temp.equals(listLabel.get(0))) {
                            temp.setBackground(new Color(135, 206, 250));
                        }
                    }
                    centerPanel.removeAll();
                    centerPanel();
                    centerPanel.repaint();
                    centerPanel.revalidate();
                    listLabel.get(0).setBackground(new Color(30, 144, 255));
                }
            }

        });
    }

    public void eventPrintPDF(JLabel lb) {
        lb.addMouseListener(new MouseAdapter() {
            public void mouseClicked() {
                PDFBUS pdf = new PDFBUS();
                System.out.println(pdf.printPDFKyThi());

            }
        });
    }
}
