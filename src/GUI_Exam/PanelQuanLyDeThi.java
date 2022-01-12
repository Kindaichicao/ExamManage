/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_Exam;

import BUS_EXAM.BaseBUS;
import BUS_EXAM.CauHoiBUS;
import BUS_EXAM.DeCauHoiBUS;
import BUS_EXAM.DeThiBUS;
import BUS_EXAM.GiangVienMonBUS;
import BUS_EXAM.KhoaBUS;
import BUS_EXAM.KyThiBUS;
import BUS_EXAM.LopBUS;
import BUS_EXAM.MonBUS;
import BUS_EXAM.MonSinhVienBUS;
import BUS_EXAM.SinhVienBUS;
import DTO_EXAM.CauHoiDTO;
import DTO_EXAM.DeCauHoiDTO;
import DTO_EXAM.DeThiDTO;
import DTO_EXAM.GiangVienMonDTO;
import DTO_EXAM.KyThiDTO;
import DTO_EXAM.SinhVienDTO;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ASUS
 */
public class PanelQuanLyDeThi {

    JPanel pnPaint = new JPanel();
    int numPage = 1;
    int check = 0;
    String mahoantac;
    String passhoantac;
    MyTable tba = new MyTable();
    public Vector vtHeader = new Vector();
    public Vector vtData = new Vector();
    DefaultTableModel model = new DefaultTableModel(){
        public Class<?> getColumnClass(int column){
            switch (column) {
		case 0:
                    return Boolean.class;
		default:
                    return String.class;
            }
        }
    };
    FileChooserGUI file = new FileChooserGUI();
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int widthFrame = (int) ((int) screenSize.getWidth() * 0.9);
    int heightFrame = (int) ((int) screenSize.getHeight() * 0.9);
    MyCheckBox[] cbQuyen = new MyCheckBox[11];
    MyComboBox[] cbbQuyen = new MyComboBox[11];
    DeThiDTO dthoantac;

    public PanelQuanLyDeThi(String pnCenter) {
        BaseGUI guiBase = new BaseGUI();
        UserFrame.pCenter.removeAll();
        UserFrame.pCenter.validate();
        UserFrame.pCenter.repaint();
        UserFrame.pCenter.setLayout(new BorderLayout());
        UserFrame.pCenter.setBackground(Color.white);
        String ma = (String) pnCenter;
        JPanel top = new JPanel();
        top.setBackground(Color.white);
        top.setLayout(new FlowLayout(0, 15, 20));
        top.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.8), (int) ((int) heightFrame * 0.2)));
        JPanel btn = new JPanel();
        btn.setLayout(new FlowLayout(1, 20, 15));
        JPanel tb = new JPanel();
        tb.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.75), (int) ((int) heightFrame * 0.68)));
        tb.setBackground(Color.white);
        JScrollPane pane = new JScrollPane();
        pane.setPreferredSize(new Dimension(new Dimension((int) ((int) widthFrame * 0.75), (int) ((int) heightFrame * 0.55))));
        pane.setViewportView(tba);

        String[] bt = {"Xóa", "Thêm", "Sửa", "Hoàn tác", "Điền lại", "Xem"};
        MyButton[] bts = new MyButton[100];
        btn.setBackground(Color.white);
        for (int i = 0; i < bt.length; i++) {
            if (i == 0) {
                bts[i] = new MyButton(bt[i], MyButton.DELETE);
            } else {
                bts[i] = new MyButton(bt[i], MyButton.ADD);
            }
            bts[i].setPreferredSize(new Dimension((int) ((int) widthFrame * 0.08), (int) ((int) heightFrame * 0.04)));
            bts[i].setFont(bts[i].getFont().deriveFont(15.0f));
            btn.add(bts[i]);
        };
        guiBase.isEnabled(bts[3]);

        ImageIcon img = new ImageIcon("src/img/excel.png");
        MyButton btImportExcel = new MyButton("Nhập Excel", 1);
        btImportExcel.setIcon(img);
        btImportExcel.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.08), (int) ((int) heightFrame * 0.04)));

        MyButton btExportExcel = new MyButton("Xuất Excel", 1);
        btExportExcel.setIcon(img);
        btExportExcel.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.08), (int) ((int) heightFrame * 0.04)));

        MyButton btSearch = new MyButton("Tìm Kiếm", MyButton.ADD);
        btSearch.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.09), (int) ((int) heightFrame * 0.04)));
        btSearch.setFont(bts[4].getFont().deriveFont(15.0f));

        MyTextField txtSearch = new MyTextField("Search ...", 1);
        txtSearch.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtSearch.getText().equals("Search ...")) {
                    txtSearch.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtSearch.getText().equals("")) {
                    txtSearch.setText("Search ...");
                }
            }

        });

        MyComboBox cbSearch = new MyComboBox();
        cbSearch.setBorder(BorderFactory.createLineBorder(Color.decode("#33CCFF"), 3));

        BaseBUS infoData;
        int cout = 0;
        String[] info = null;
        JTextField txtPage = new JTextField("1");

        tba.setAutoCreateRowSorter(true);
        btSearch.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                if (txtSearch.getText().equals("Search ...")) {
                    new ErrorFrame("Bạn chưa nhập từ khóa!");
                } else {
                    Vector vtSearch = new Vector();
                    int index = cbSearch.getSelectedIndex();
                    for (int i = 0; i < model.getRowCount(); i++) {
                        if (model.getValueAt(i, index).toString().contains(txtSearch.getText())) {
                            vtSearch.add(model.getDataVector().get(i));
                        }
                    }
                    tba.setModel(new DefaultTableModel(vtSearch, vtHeader));
                }
            }
        });

        JPanel pnSearch = new JPanel();
        pnSearch.setLayout(new FlowLayout(0, 30, 0));
        pnSearch.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.65), (int) ((int) heightFrame * 0.04)));
        pnSearch.setBackground(Color.white);

        pnSearch.add(txtSearch);
        pnSearch.add(cbSearch);
        tb.add(pnSearch);

        tb.add(btSearch);
        tb.add(pane);

        MyPanel pnPage = new MyPanel();
        pnPage.setLayout(new FlowLayout(4, 10, 3));
        pnPage.setPreferredSize(new Dimension(new Dimension((int) ((int) widthFrame * 0.5), (int) ((int) heightFrame * 0.05))));
        pnPage.setBackground(Color.white);

        MyButton btPre = new MyButton("<<", 7);
        btPre.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.03), (int) ((int) heightFrame * 0.04)));
        MyButton btNext = new MyButton(">>", 7);
        btNext.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.03), (int) ((int) heightFrame * 0.04)));
        txtPage.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.03), (int) ((int) heightFrame * 0.04)));
        txtPage.setHorizontalAlignment(JTextField.CENTER);
        pnPage.add(btPre);
        pnPage.add(txtPage);
        pnPage.add(btNext);
        //rieng
        DeThiBUS dt = new DeThiBUS();
        String[] temp = {"", "Mã đề", "Tên môn", "Tên kỳ thi", "Thời gian làm bài", "Ngày Thi", "Giờ Thi", "Số lượng câu hỏi"};
        info = temp;
        MyLabel lbMaDe = new MyLabel("Mã đề :");
        top.add(lbMaDe);
        MyLabel lbMa = new MyLabel("");
        lbMa.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.65), 30));
        top.add(lbMa);

        MyLabel lbMon = new MyLabel(info[2]);
        MonBUS mon = new MonBUS();
        String[] arrMon = new String[mon.getListMon().size()];
        mon.getListMon().toArray(arrMon);
        MyComboBox cbMon = new MyComboBox(arrMon);
        cbMon.addItem("");
        cbMon.setSelectedItem("");
        top.add(lbMon);
        top.add(cbMon);

        MyLabel lbKT = new MyLabel(info[3]);
        lbKT.setPreferredSize(new Dimension(100, 30));
        KyThiBUS kythi = new KyThiBUS();
        String[] arrKT = new String[kythi.getListKyThi().size()];
        kythi.getListKyThi().toArray(arrKT);
        MyComboBox cbKT = new MyComboBox(arrKT);
        cbKT.addItem("");
        cbKT.setSelectedItem("");
        top.add(lbKT);
        top.add(cbKT);

        MyLabel lbSL = new MyLabel(info[7]);
        lbSL.setPreferredSize(new Dimension(150, 30));
        MyTextField txtSL = new MyTextField();
        txtSL.setPreferredSize(new Dimension(120, 30));
        top.add(lbSL);
        top.add(txtSL);

        MyLabel lbTime = new MyLabel(info[4]);
        lbTime.setPreferredSize(new Dimension(150, 30));
        MyTextField txtTime = new MyTextField();
        txtTime.setPreferredSize(new Dimension(120, 30));

        MyLabel lbNgayThi = new MyLabel(info[5]);
        JPanel pnNgayThi = new JPanel();
        pnNgayThi.setBackground(Color.white);
        pnNgayThi.setLayout(new FlowLayout(1, 10, 0));
        pnNgayThi.setPreferredSize(new Dimension(220, 30));

        String[] nam = new String[30];
        nam[0] = "YYYY";
        for (int i = 29; i >= 1; i--) {
            nam[i] = Integer.toString(i + 2000);
        }
        MyComboBox cbNam = new MyComboBox(nam);
        cbNam.setPreferredSize(new Dimension(90, 30));
        cbNam.setFont(new Font("Arial", Font.PLAIN, 12));
        cbNam.setSelectedItem("YYYY");

        String[] thang = new String[13];
        thang[0] = "MM";
        for (int i = 1; i < 13; i++) {
            if (i < 10) {
                thang[i] = "0" + Integer.toString(i);
            } else {
                thang[i] = Integer.toString(i);
            }
        }
        MyComboBox cbThang = new MyComboBox(thang);
        cbThang.setFont(new Font("Arial", Font.PLAIN, 12));
        cbThang.setPreferredSize(new Dimension(50, 30));
        cbThang.setSelectedItem("MM");

        MyComboBox cbNgay = new MyComboBox();
        cbNgay.setFont(new Font("Arial", Font.PLAIN, 12));
        cbNgay.setPreferredSize(new Dimension(50, 30));
        cbNgay.addItem("DD");
        cbNgay.setSelectedItem("DD");
        cbThang.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getSource() == cbThang) {
                    int numDate = 0;
                    if (!cbThang.getSelectedItem().equals("MM") && !cbNam.getSelectedItem().equals("YYYY")) {
                        int x = Integer.parseInt(cbThang.getSelectedItem().toString());
                        if (x == 2) {
                            int y = Integer.parseInt(cbNam.getSelectedItem().toString());
                            if (y % 400 == 0 || (y % 100 != 0 && y % 4 == 0)) {
                                numDate = 29;
                            } else {
                                numDate = 28;
                            }
                        } else if (x == 4 || x == 6 || x == 9 || x == 11) {
                            numDate = 30;
                        } else {
                            numDate = 31;
                        }
                    }
                    String[] ngay = new String[numDate + 1];
                    cbNgay.removeAllItems();
                    cbNgay.addItem("DD");
                    for (int i = 1; i <= numDate; i++) {
                        if (i < 10) {
                            ngay[i] = "0" + Integer.toString(i);
                        } else {
                            ngay[i] = Integer.toString(i);
                        }
                        cbNgay.addItem(ngay[i]);
                    }
                }
            }
        });

        pnNgayThi.add(cbNgay);
        pnNgayThi.add(cbThang);
        pnNgayThi.add(cbNam);

        top.add(lbNgayThi);
        top.add(pnNgayThi);

        MyLabel lbGioThi = new MyLabel(info[6]);
        JPanel pnGioThi = new JPanel();
        pnGioThi.setBackground(Color.white);
        pnGioThi.setLayout(new FlowLayout(0, 10, 0));
        pnGioThi.setPreferredSize(new Dimension(230, 30));

        String[] gio = new String[24];
        for (int i = 0; i < 24; i++) {
            if (i < 10) {
                gio[i] = "0" + Integer.toString(i);
            } else {
                gio[i] = Integer.toString(i);
            }
        }
        MyComboBox cbGio = new MyComboBox(gio);
        cbGio.setPreferredSize(new Dimension(70, 30));
        cbGio.setFont(new Font("Arial", Font.PLAIN, 12));

        String[] phut = new String[60];
        for (int i = 0; i < 60; i++) {
            if (i < 10) {
                phut[i] = "0" + Integer.toString(i);
            } else {
                phut[i] = Integer.toString(i);
            }
        }
        MyComboBox cbPhut = new MyComboBox(phut);
        cbPhut.setFont(new Font("Arial", Font.PLAIN, 12));
        cbPhut.setPreferredSize(new Dimension(70, 30));

        MyLabel haicham = new MyLabel(":");
        haicham.setPreferredSize(new Dimension(10, 30));

        pnGioThi.add(cbGio);
        pnGioThi.add(haicham);
        pnGioThi.add(cbPhut);
        top.add(lbGioThi);
        top.add(pnGioThi);

        top.add(lbTime);
        top.add(txtTime);

        vtHeader.clear();
        vtData.clear();
        for (int i = 0; i < info.length; i++) {
            vtHeader.add(info[i]);
            cbSearch.addItem(info[i]);
        }
        infoData = new DeThiBUS();
        vtData.addAll(infoData.getThongTin(1));
        model.setDataVector(vtData, vtHeader);
        tba.setModel(model);
        cout = infoData.coutThongTin();

        tba.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int i = tba.getSelectedRow();
                if (i != -1) {
                    lbMa.setText(String.valueOf(model.getValueAt(i, 1)));
                    cbMon.setSelectedItem(String.valueOf(model.getValueAt(i, 2)));
                    cbKT.setSelectedItem(String.valueOf(model.getValueAt(i, 3)));
                    txtTime.setText(String.valueOf(model.getValueAt(i, 4)));
                    String[] time = String.valueOf(model.getValueAt(i, 5)).split("-");
                    cbNam.setSelectedItem(time[2]);
                    cbThang.setSelectedItem(time[1]);
                    cbNgay.setSelectedItem(time[0]);
                    String[] times = String.valueOf(model.getValueAt(i, 6)).split(":");
                    cbGio.setSelectedItem(times[0]);
                    cbPhut.setSelectedItem(times[1]);
                    txtSL.setText(String.valueOf(model.getValueAt(i, 7)));
                }
            }
        });

        ArrayList<String> checkbox = new ArrayList<String>();
        bts[0].addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                int i = tba.getSelectedRow();
                checkbox.clear();
                String ngayThi = cbNam.getSelectedItem().toString() + "-" + cbThang.getSelectedItem().toString() + "-" + cbNgay.getSelectedItem().toString();
                String gioThi = cbGio.getSelectedItem().toString() + ":" + cbPhut.getSelectedItem().toString() + ":00";
                if (i == -1) {
                    new ErrorFrame("Vui lòng chọn hàng cần xóa!");
                } else if(doiNgay(ngayThi, gioThi).compareTo(LocalDateTime.now())<0){
                        new ErrorFrame("Không thể xóa đề đã thi");
                }else  {
                    for (int j = 0; j < tba.getRowCount(); j++) {
                        String checked = tba.getValueAt(j, 0).toString();
                        if (checked.equals("true")) {
                            checkbox.add(String.valueOf(tba.getValueAt(j, 1)));
                        }
                    }
                    ErrorFrame err = new ErrorFrame("Bạn có chắc chắn muốn xóa không?");
                    err.btnOK.addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent arg0) {
                            String ma = String.valueOf(model.getValueAt(i, 1));
                            if (checkbox.isEmpty()) {
                                if (dt.xoa(ma)) {
                                    new ErrorFrame("Xóa thành công!");
                                    check = 1;
                                    mahoantac = ma;
                                } else {
                                    new ErrorFrame("Xóa thất bại!");
                                }
                            } else {
                                for (int j = 0; j < checkbox.size(); j++) {
                                    dt.xoa(checkbox.get(j));
                                }
                                check = 4;
                            }
                            vtData.clear();
                            vtData.addAll(infoData.getThongTin(Integer.parseInt(txtPage.getText())));
                            model.setDataVector(vtData, vtHeader);
                            tba.setModel(model);
                            lbMa.setText("");
                            cbMon.setSelectedItem("");
                            cbKT.setSelectedItem("");
                            txtTime.setText("");
                            cbNam.setSelectedItem("YYYY");
                            cbThang.setSelectedItem("MM");
                            cbNgay.setSelectedItem("DD");
                            cbGio.setSelectedItem("00");
                            cbPhut.setSelectedItem("00");
                            txtSL.setText("");
                            guiBase.setEnabled(bts[3]);
                        }
                    }
                    );
                }
            }
        });

        MonBUS monbus = new MonBUS();
        KyThiBUS ktbus = new KyThiBUS();
        bts[1].addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent arg0) {

                String maKT ;
                String ngayThi;
                String gioThi;
                if (cbMon.getSelectedItem().equals("") || cbKT.getSelectedItem().equals("") || txtTime.getText().equals("") || cbNam.getSelectedItem().equals("YYYY") || cbThang.getSelectedItem().equals("MM") || cbNgay.getSelectedItem().equals("DD") || txtSL.getText().equals("")) {
                    new ErrorFrame("Vui lòng điền đầy đủ thông tin!");
                } else{
                    maKT = ktbus.getList().get(cbKT.getSelectedIndex()).getMaKyThi();
                    ngayThi = cbNam.getSelectedItem().toString() + "-" + cbThang.getSelectedItem().toString() + "-" + cbNgay.getSelectedItem().toString();
                    gioThi = cbGio.getSelectedItem().toString() + ":" + cbPhut.getSelectedItem().toString() + ":00";
                    if (isCheckText(txtSL.getText(), txtTime.getText(), ngayThi, gioThi, maKT)) {
                        ErrorFrame err = new ErrorFrame("Bạn có chắc chắn muốn thêm không?");
                    err.btnOK.addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent arg0) {
                            DoKhoFrame doKho = new DoKhoFrame();
                            doKho.btnOK.addMouseListener(new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e){
                                    String kho = doKho.txtKho.getText();
                                    String de = doKho.txtDe.getText();
                                    String tB = doKho.txtTB.getText();
                                    if(checkDoKho(doKho,Integer.parseInt(txtSL.getText()))){
                                        doKho.f.setVisible(false);
                                        String maMon = monbus.getList().get(cbMon.getSelectedIndex()).getMaMon();
                                        String ngayThi = cbNam.getSelectedItem().toString() + "-" + cbThang.getSelectedItem().toString() + "-" + cbNgay.getSelectedItem().toString();
                                        String gioThi = cbGio.getSelectedItem().toString() + ":" + cbPhut.getSelectedItem().toString() + ":00";
                                        DeThiDTO dtDT = new DeThiDTO();
                                        int tg = Integer.parseInt(txtTime.getText());
                                        int slc = Integer.parseInt(txtSL.getText());
                                        if (createDe(maMon, slc, maKT, tg, ngayThi, gioThi,Integer.parseInt(kho),
                                                Integer.parseInt(tB),Integer.parseInt(de))) {
                                            new ErrorFrame("Thêm Thành Công");
                                            vtData.clear();
                                            vtData.addAll(infoData.getThongTin(Integer.parseInt(txtPage.getText())));
                                            model.setDataVector(vtData, vtHeader);
                                            tba.setModel(model);
                                            mahoantac = dtDT.getMaDe();
                                            check = 2;
                                            guiBase.setEnabled(bts[3]);
                                        } else {
                                            new ErrorFrame("Thêm Thất Bại");
                                        }
                                    }
                                }
                            });

                        }
                    });
                }
                }
            }
        });

        bts[2].addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                int i = tba.getSelectedRow();
                String maKT = ktbus.getList().get(cbKT.getSelectedIndex()).getMaKyThi();
                String ngayThi = cbNam.getSelectedItem().toString() + "-" + cbThang.getSelectedItem().toString() + "-" + cbNgay.getSelectedItem().toString();
                String gioThi = cbGio.getSelectedItem().toString() + ":" + cbPhut.getSelectedItem().toString() + ":00";
                if (i < 0) {
                    new ErrorFrame("Vui lòng chọn hàng cần sửa!");
                } else if (cbMon.getSelectedItem().equals("") || cbKT.getSelectedItem().equals("")
                        || txtTime.getText().equals("") || cbNam.getSelectedItem().equals("YYYY")
                        || cbThang.getSelectedItem().equals("MM") || cbNgay.getSelectedItem().equals("DD")
                        || txtSL.getText().equals(""))
                {
                    new ErrorFrame("Vui lòng điền đầy đủ thông tin!");
                } else if(doiNgay(ngayThi, gioThi).compareTo(LocalDateTime.now())<0){
                    System.out.println("ngu");
                        new ErrorFrame("Không thể sửa đề đã thi");
                }else if (isCheckText(txtSL.getText(), txtTime.getText(), ngayThi, gioThi, maKT)) {
                    ErrorFrame err = new ErrorFrame("Bạn có chắc chắn muốn sửa không?");
                    err.btnOK.addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent arg0) {
                            String maMon = monbus.getList().get(cbMon.getSelectedIndex()).getMaMon();

                            DeThiDTO dtDT = new DeThiDTO();
                            dtDT.setMaMon(maMon);
                            dtDT.setMaKyThi(maKT);
                            dtDT.setThoigianLamBai(Integer.parseInt(txtTime.getText()));
                            dtDT.setNgayThi(ngayThi);
                            dtDT.setGioThi(gioThi);
                            dtDT.setSoLuongCauHoi(Integer.parseInt(txtSL.getText()));
                            dthoantac = dt.getObject(String.valueOf(model.getValueAt(i, 1)));
                            dt.sua(dtDT, String.valueOf(model.getValueAt(i, 1)));
                            new ErrorFrame("Sửa thành công!");
                            mahoantac = String.valueOf(model.getValueAt(i, 1));
                            vtData.clear();
                            vtData.addAll(infoData.getThongTin(Integer.parseInt(txtPage.getText())));
                            model.setDataVector(vtData, vtHeader);
                            tba.setModel(model);
                            lbMa.setText("");
                            cbMon.setSelectedItem("");
                            cbKT.setSelectedItem("");
                            txtTime.setText("");
                            cbNam.setSelectedItem("YYYY");
                            cbThang.setSelectedItem("MM");
                            cbNgay.setSelectedItem("DD");
                            cbGio.setSelectedItem("00");
                            cbPhut.setSelectedItem("00");
                            txtSL.setText("");
                            check = 3;
                            guiBase.setEnabled(bts[3]);
                        }
                    });
                }
            }
        }
        );

        bts[3].addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent arg0) {
                if (check == 1) {
                    dt.khoiphuc(mahoantac);
                } else if (check == 2) {
                    dt.xoavinhvien(mahoantac);
                } else if (check == 3) {
                    dt.sua(dthoantac, mahoantac);
                } else if (check == 4) {
                    for (int i = 0; i < checkbox.size(); i++) {
                        dt.khoiphuc(checkbox.get(i));
                    }
                }

                vtData.clear();
                vtData.addAll(dt.getThongTin(Integer.parseInt(txtPage.getText())));
                model.setDataVector(vtData, vtHeader);
                tba.setModel(model);
                guiBase.isEnabled(bts[3]);
                check = 0;
            }
        }
        );

        bts[4].addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                tba.clearSelection();
                lbMa.setText("");
                cbMon.setSelectedItem("");
                cbKT.setSelectedItem("");
                txtTime.setText("");
                cbNam.setSelectedItem("YYYY");
                cbThang.setSelectedItem("MM");
                cbNgay.setSelectedItem("DD");
                cbGio.setSelectedItem("00");
                cbPhut.setSelectedItem("00");
                txtSL.setText("");
            }
        });

        bts[5].addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                int i = tba.getSelectedRow();
                if (i >= 0) {
                    DeThiBUS busde = new DeThiBUS();
                    DeThiDTO de = busde.getObject(String.valueOf(model.getValueAt(i, 1)));
                    DeThiFrame fDe = new DeThiFrame();
                    fDe.createJFrame(de);
                } else {
                    new ErrorFrame("Chọn Đề Muốn Xem");
                }
            }
        });

        btImportExcel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                dt.importExcel(file.getFileExcelNameOpen(pnPaint));
            }
        });

        btExportExcel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                dt.exportExcel(file.getFileExcelNameSave(pnPaint));
            }
        });
        //Tính số trang
        if (cout % 25 == 0) {
            numPage = cout / 25;
        } else {
            numPage = cout / 25 + 1;
        }
        MyLabel lbPage = new MyLabel("1/" + numPage);
        lbPage.setFont(new Font("Arial", Font.PLAIN, 14));

        btPre.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                if (Integer.parseInt(txtPage.getText()) > 1) {
                    int page = Integer.parseInt(txtPage.getText());
                    page--;
                    vtData.clear();
                    vtData.addAll(infoData.getThongTin(page));
                    model.setDataVector(vtData, vtHeader);
                    tba.setModel(model);
                    txtPage.setText(Integer.toString(page));
                    lbPage.setText(txtPage.getText() + "/" + numPage);
                }
            }
        });

        btNext.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                if (Integer.parseInt(txtPage.getText()) < numPage) {
                    int page = Integer.parseInt(txtPage.getText());
                    page++;
                    vtData.clear();
                    vtData.addAll(infoData.getThongTin(page));
                    model.setDataVector(vtData, vtHeader);
                    tba.setModel(model);
                    txtPage.setText(Integer.toString(page));
                    lbPage.setText(txtPage.getText() + "/" + numPage);
                }
            }
        });
        txtPage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (Integer.parseInt(txtPage.getText()) <= numPage && Integer.parseInt(txtPage.getText()) >= 1) {
                    int page = Integer.parseInt(txtPage.getText());
                    vtData.clear();
                    vtData.addAll(infoData.getThongTin(page));
                    tba.setModel(model);
                    lbPage.setText(txtPage.getText() + "/" + numPage);
                }
            }
        });

        pnPage.add(lbPage);
        tb.add(pnPage);

        MyPanel pnExcel = new MyPanel();
        pnExcel.setBackground(Color.white);
        pnExcel.setLayout(new FlowLayout(4, 20, 3));
        pnExcel.setPreferredSize(new Dimension(new Dimension((int) ((int) widthFrame * 0.25), (int) ((int) heightFrame * 0.05))));

        pnExcel.add(btImportExcel);
        pnExcel.add(btExportExcel);
        tb.add(pnExcel);

        UserFrame.pCenter.add(top, BorderLayout.NORTH);
        UserFrame.pCenter.add(btn, BorderLayout.CENTER);
        UserFrame.pCenter.add(tb, BorderLayout.SOUTH);
        UserFrame.pCenter.validate();
        UserFrame.pCenter.repaint();
    }

    public ArrayList<CauHoiDTO> randDe(String maMon, int soLuongCau,int de,int tb,int kho) {
        GiangVienMonBUS busGVM = new GiangVienMonBUS();
        CauHoiBUS busCauHoi = new CauHoiBUS();
        ArrayList<GiangVienMonDTO> gVMS = new ArrayList<GiangVienMonDTO>();
        ArrayList<CauHoiDTO> cauHoiDes = new ArrayList<CauHoiDTO>();
        gVMS = busGVM.getListChungMon(maMon);

        ArrayList<CauHoiDTO> cauHois = new ArrayList<CauHoiDTO>();
        cauHois = busCauHoi.getListCauHoiMon(gVMS);
        if (cauHois !=null) {
            if(cauHois.size() >= soLuongCau ){
                while(cauHois.size() > 0) {
                    Random rand = new Random();
                    int k = rand.nextInt(cauHois.size());
                    if((cauHois.get(k).getDoKho() == 1) && de>0){
                        de--;
                        cauHoiDes.add(cauHois.get(k));
                        cauHois.remove(k);
                    } else if((cauHois.get(k).getDoKho() == 2) && tb>0){
                        tb--;
                        cauHoiDes.add(cauHois.get(k));
                        cauHois.remove(k);
                    } else if((cauHois.get(k).getDoKho() == 3) && kho>0){
                        kho--;
                        cauHoiDes.add(cauHois.get(k));
                        cauHois.remove(k);
                    } else{
                        cauHois.remove(k);
                    }
                    if(cauHoiDes.size() == soLuongCau){
                        break;
                    }
                }
            } else {
                new ErrorFrame("Số Lượng Câu Hỏi Không Đủ");
                return null;
            }
        } else {
            new ErrorFrame("Số Lượng Câu Hỏi Không Đủ");
            return null;
        }
        if(cauHoiDes.size()<soLuongCau){
           new ErrorFrame("Số Lượng Câu Hỏi Không Đủ");
            return null;
        }
        return cauHoiDes;
    }

    public boolean checkDoKho(DoKhoFrame doKho,int tongSoCau){
        if(!CheckError.isInteger(doKho.txtDe.getText())){
            new ErrorFrame("Số câu dễ phải nguyên");
            doKho.txtDe.requestFocus();
            return false;
        }
        if(!CheckError.isInteger(doKho.txtTB.getText())){
            new ErrorFrame("Số câu trung bình phải nguyên");
            doKho.txtTB.requestFocus();
            return false;
        }
        if(!CheckError.isInteger(doKho.txtKho.getText())){
            new ErrorFrame("Số câu khó phải nguyên");
            doKho.txtKho.requestFocus();
            return false;
        }
        if((Integer.parseInt(doKho.txtDe.getText())+Integer.parseInt(doKho.txtTB.getText())+Integer.parseInt(doKho.txtKho.getText()))!=tongSoCau){
        new ErrorFrame("Số câu cộng lại phải bằng số câu của đề");
        return false;
    }
        return true;
    }

    public boolean createDe(String maMon, int soLuongCau, String maKyThi, int tg, String ngaythi, String gio,int kho,int tb,int De) {
        ArrayList<CauHoiDTO> cauHoiDes = new ArrayList<CauHoiDTO>();
        DeThiBUS busDe = new DeThiBUS();
        DeCauHoiBUS busDCH = new DeCauHoiBUS();
        cauHoiDes = randDe(maMon, soLuongCau,De,tb,kho);
        boolean check = false;

        if (cauHoiDes != null) {
            if(cauHoiDes.size() == soLuongCau ){
                DeThiDTO de = new DeThiDTO(maMon, maKyThi, tg, ngaythi, gio, soLuongCau, De, tb, kho);
                check = busDe.them(de);
                if (check) {
                    for (CauHoiDTO ch : cauHoiDes) {
                        DeCauHoiDTO dch = new DeCauHoiDTO(ch.getMaCauHoi(), de.getMaDe(), 1);
                        check = busDCH.them(dch);
                    }
                    if (!check) {
                        busDe.xoa(de.getMaDe());
                    }
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
        return check;
    }

    public boolean isCheckText(String slc, String tg, String ngay, String gio, String maKyThi) {

        LocalDateTime nowTime = LocalDateTime.now();
        if (!CheckError.isInteger(slc)) {
            new ErrorFrame("Số Lượng câu phải là số nguyên");
            return false;
        }
        if (!CheckError.isInteger(tg)) {
            new ErrorFrame("Thời gian làm bài phải là số nguyên");
            return false;
        }
        if (!checkTime(ngay, gio, maKyThi)) {
            new ErrorFrame("Ngày Thi Phải Thuộc Kỳ Thi");
            return false;
        }
        if(doiNgay(ngay, gio).compareTo(nowTime)<0){
            new ErrorFrame("Giờ Thi Phải Lớn Hơn Hoặc Bằng Giờ Hiện Tại");
            return false;
        }

        return true;
    }



    public boolean checkTime(String ngay, String gio, String maKyThi) {
        KyThiDTO kt = new KyThiDTO();
        KyThiBUS busKT = new KyThiBUS();

        kt = busKT.getObject(maKyThi);

        LocalDateTime ngayDe = doiNgay(ngay, gio);
        LocalDateTime bd = doiNgay(kt.getNgayBatDau(), "00:00:00");
        LocalDateTime ketThuc = doiNgay(kt.getNgayKetThuc(), "23:59:59");

        int dau = ngayDe.compareTo(bd);
        int cuoi = ngayDe.compareTo(ketThuc);

        if (dau < 0 || cuoi > 0) {
            return false;
        }
        return true;
    }
    public LocalDateTime doiNgay(String ngay, String gio) {
        System.out.println(ngay);
        System.out.println(gio);
        String[] date = ngay.split("-");
        String[] time = gio.split(":");
        LocalDateTime deTime = LocalDateTime.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]),
                Integer.parseInt(time[0]), Integer.parseInt(time[1]), Integer.parseInt(time[2]));
        return deTime;
    }
    public boolean checkTime(String maKyThi) {
        KyThiDTO kt = new KyThiDTO();
        KyThiBUS busKT = new KyThiBUS();

        kt = busKT.getObject(maKyThi);

        LocalDateTime ngayDe = LocalDateTime.now();
        LocalDateTime bd = doiNgay(kt.getNgayBatDau(), "00:00:00");
        LocalDateTime ketThuc = doiNgay(kt.getNgayKetThuc(), "23:59:59");

        int dau = ngayDe.compareTo(bd);
        int cuoi = ngayDe.compareTo(ketThuc);
        System.out.println(dau);
        System.out.println(cuoi);
        if (dau < 0 || cuoi > 0) {
            return false;
        }
        return true;
    }
}
