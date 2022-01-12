/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_Exam;

import BUS_EXAM.BaseBUS;
import BUS_EXAM.CauHoiBUS;
import BUS_EXAM.GiangVienBUS;
import BUS_EXAM.KhoaBUS;
import BUS_EXAM.LopBUS;
import BUS_EXAM.SinhVienBUS;
import DTO_EXAM.GiangVienDTO;
import DTO_EXAM.SinhVienDTO;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class PanelQuanLyGiangVien {

    JPanel pnPaint = new JPanel();
    int numPage = 1;
    int check = 0;
    String mahoantac;
    SinhVienDTO svhoantac;
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
    GiangVienDTO gvhoantac;

    public PanelQuanLyGiangVien(String pnCenter) {
        UserFrame.pCenter.removeAll();
        UserFrame.pCenter.validate();
        UserFrame.pCenter.repaint();
        UserFrame.pCenter.setLayout(new BorderLayout());
        UserFrame.pCenter.setBackground(Color.white);
        BaseGUI guiBase = new BaseGUI();
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

        String[] bt = {"Xóa", "Thêm", "Sửa", "Hoàn tác", "Điền lại"};
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
        GiangVienBUS gv = new GiangVienBUS();
        String[] temp = {"", "Mã giảng viên", "Họ", "Tên", "Ngày Sinh", "Giới Tính", "Khoa"};
        info = temp;
        MyLabel lbMaGV = new MyLabel("Mã giảng viên :");
        lbMaGV.setPreferredSize(new Dimension(120, 30));
        top.add(lbMaGV);

        MyLabel lbMa = new MyLabel("");
        lbMa.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.62), 30));
        top.add(lbMa);

        MyLabel lbHo = new MyLabel(info[2]);
        MyTextField txtHo = new MyTextField();
        top.add(lbHo);
        top.add(txtHo);

        MyLabel lbTen = new MyLabel(info[3]);
        MyTextField txtTen = new MyTextField();
        top.add(lbTen);
        top.add(txtTen);

        MyLabel lbNgaySinh = new MyLabel(info[4]);
        JPanel pnNgaySinh = new JPanel();
        pnNgaySinh.setBackground(Color.white);
        pnNgaySinh.setLayout(new FlowLayout(1, 10, 0));
        pnNgaySinh.setPreferredSize(new Dimension(220, 30));

        String[] nam = new String[70];
        nam[0] = "YYYY";
        for (int i = 69; i >= 1; i--) {
            nam[i] = Integer.toString(i + 1940);
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

        pnNgaySinh.add(cbNam);
        pnNgaySinh.add(cbThang);
        pnNgaySinh.add(cbNgay);
        top.add(lbNgaySinh);
        top.add(pnNgaySinh);

        MyLabel lbGioiTinh = new MyLabel(info[5]);
        String[] arrGioiTinh = {"", "Nam", "Nữ"};
        MyComboBox cbGioiTinh = new MyComboBox(arrGioiTinh);
        cbGioiTinh.setSelectedItem("");
        top.add(lbGioiTinh);
        top.add(cbGioiTinh);

        MyLabel lbKhoa = new MyLabel(info[6]);
        KhoaBUS khoa = new KhoaBUS();
        String[] arrKhoa = new String[khoa.getListKhoa().size()];
        khoa.getListKhoa().toArray(arrKhoa);
        MyComboBox cbKhoa = new MyComboBox(arrKhoa);
        cbKhoa.addItem("");
        cbKhoa.setSelectedItem("");
        top.add(lbKhoa);
        top.add(cbKhoa);

        vtHeader.clear();
        vtData.clear();
        for (int i = 0; i < info.length; i++) {
            vtHeader.add(info[i]);
            cbSearch.addItem(info[i]);
        }
        infoData = new GiangVienBUS();
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
                    txtHo.setText(String.valueOf(model.getValueAt(i, 2)));
                    txtTen.setText(String.valueOf(model.getValueAt(i, 3)));
                    String[] time = String.valueOf(model.getValueAt(i, 4)).split("-");
                    cbNam.setSelectedItem(time[2]);
                    cbThang.setSelectedItem(time[1]);
                    cbNgay.setSelectedItem(time[0]);
                    cbGioiTinh.setSelectedItem(String.valueOf(model.getValueAt(i, 5)));
                    cbKhoa.setSelectedItem(String.valueOf(model.getValueAt(i, 6)));
                }
            }
        });

        ArrayList<String> checkbox = new ArrayList<String>();
        bts[0].addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                int i = tba.getSelectedRow();
                checkbox.clear();
                if (i == -1) {
                    new ErrorFrame("Vui lòng chọn hàng cần xóa!");
                } else {
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
                                if (gv.xoa(ma)) {
                                    new ErrorFrame("Xóa thành công!");
                                    check = 1;
                                    mahoantac = ma;
                                } else {
                                    new ErrorFrame("Xóa thất bại!");
                                }
                            } else {
                                for (int j = 0; j < checkbox.size(); j++) {
                                    gv.xoa(checkbox.get(j));
                                }
                                check = 4;
                            }
                            vtData.clear();
                            vtData.addAll(infoData.getThongTin(Integer.parseInt(txtPage.getText())));
                            model.setDataVector(vtData, vtHeader);
                            tba.setModel(model);
                            lbMa.setText("");
                            txtHo.setText("");
                            txtTen.setText("");
                            cbNam.setSelectedItem("YYYY");
                            cbThang.setSelectedItem("MM");
                            cbNgay.setSelectedItem("DD");
                            cbGioiTinh.setSelectedItem("");
                            cbKhoa.setSelectedItem("");
                            guiBase.setEnabled(bts[3]);
                        }
                    });
                }
            }
        });

        KhoaBUS khoabus = new KhoaBUS();
        bts[1].addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                if (txtHo.getText().equals("") || txtTen.getText().equals("") || cbNam.getSelectedItem().equals("YYYY") || cbThang.getSelectedItem().equals("MM") || cbNgay.getSelectedItem().equals("DD") || cbKhoa.getSelectedItem().equals("") || cbGioiTinh.getSelectedItem().equals("")) {
                    new ErrorFrame("Vui lòng điền đầy đủ thông tin!");
                } else if (!CheckError.isNameStudent(txtHo.getText())) {
                    new ErrorFrame("Họ Không Hợp Lệ");
                } else if (!CheckError.isNameStudent(txtTen.getText())) {
                    new ErrorFrame("Tên Không Hợp Lệ");
                } else {
                    ErrorFrame err = new ErrorFrame("Bạn có chắc chắn muốn thêm không?");
                    err.btnOK.addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent arg0) {
                            String maKhoa = khoabus.getList().get(cbKhoa.getSelectedIndex() + 1).getMaKhoa();
                            String ngaySinh = cbNam.getSelectedItem().toString() + "-" + cbThang.getSelectedItem().toString() + "-" + cbNgay.getSelectedItem().toString();
                            GiangVienDTO dtGV = new GiangVienDTO();
                            dtGV.setMaKhoa(maKhoa);
                            dtGV.setHoDem(txtHo.getText());
                            dtGV.setTen(txtTen.getText());
                            dtGV.setNgaySinh(ngaySinh);
                            dtGV.setGioiTinh(cbGioiTinh.getSelectedItem().toString());
                            gv.them(dtGV);
                            new ErrorFrame("Thêm thành công!");
                            vtData.clear();
                            vtData.addAll(infoData.getThongTin(Integer.parseInt(txtPage.getText())));
                            model.setDataVector(vtData, vtHeader);
                            tba.setModel(model);
                            lbMa.setText("");
                            txtHo.setText("");
                            txtTen.setText("");
                            cbNam.setSelectedItem("YYYY");
                            cbThang.setSelectedItem("MM");
                            cbNgay.setSelectedItem("DD");
                            cbGioiTinh.setSelectedItem("");
                            cbKhoa.setSelectedItem("");
                            mahoantac = dtGV.getMaUser();
                            check = 2;
                            guiBase.setEnabled(bts[3]);
                        }
                    });
                }
            }
        });

        bts[2].addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                int i = tba.getSelectedRow();
                if (i == -1) {
                    new ErrorFrame("Vui lòng chọn hàng cần sửa!");
                } else {
                    if (txtHo.getText().equals("") || txtTen.getText().equals("") || cbNam.getSelectedItem().equals("YYYY") || cbThang.getSelectedItem().equals("MM") || cbNgay.getSelectedItem().equals("DD") || cbKhoa.getSelectedItem().equals("") || cbGioiTinh.getSelectedItem().equals("")) {
                        new ErrorFrame("Vui lòng điền đầy đủ thông tin!");
                    } else if (!CheckError.isNameStudent(txtHo.getText())) {
                        new ErrorFrame("Họ Không Hợp Lệ");
                    } else if (!CheckError.isNameStudent(txtTen.getText())) {
                        new ErrorFrame("Tên Không Hợp Lệ");
                    } else {
                        ErrorFrame err = new ErrorFrame("Bạn có chắc chắn muốn sửa không?");
                        err.btnOK.addMouseListener(new MouseAdapter() {
                            public void mouseClicked(MouseEvent arg0) {
                                String maKhoa = khoabus.getList().get(cbKhoa.getSelectedIndex()).getMaKhoa();
                                String ngaySinh = cbNam.getSelectedItem().toString() + "-" + cbThang.getSelectedItem().toString() + "-" + cbNgay.getSelectedItem();
                                GiangVienDTO dtGV = new GiangVienDTO();
                                dtGV.setMaKhoa(maKhoa);
                                dtGV.setHoDem(txtHo.getText());
                                dtGV.setTen(txtTen.getText());
                                dtGV.setNgaySinh(ngaySinh);
                                dtGV.setGioiTinh(cbGioiTinh.getSelectedItem().toString());
                                gvhoantac = gv.getObject(String.valueOf(model.getValueAt(i, 1)));
                                gv.sua(dtGV, String.valueOf(model.getValueAt(i, 1)));
                                new ErrorFrame("Sửa thành công!");
                                mahoantac = String.valueOf(model.getValueAt(i, 1));
                                vtData.clear();
                                vtData.addAll(infoData.getThongTin(Integer.parseInt(txtPage.getText())));
                                model.setDataVector(vtData, vtHeader);
                                tba.setModel(model);
                                lbMa.setText("");
                                txtHo.setText("");
                                txtTen.setText("");
                                cbNam.setSelectedItem("YYYY");
                                cbThang.setSelectedItem("MM");
                                cbNgay.setSelectedItem("DD");
                                cbGioiTinh.setSelectedItem("");
                                cbKhoa.setSelectedItem("");
                                check = 3;
                                guiBase.setEnabled(bts[3]);
                            }
                        });
                    }
                }
            }
        });

        bts[3].addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                if (check == 1) {
                    gv.khoiphuc(mahoantac);
                } else if (check == 2) {
                    gv.xoavinhvien(mahoantac);
                } else if (check == 3) {
                    gv.sua(gvhoantac, mahoantac);
                } else if (check == 4) {
                    for (int i = 0; i < checkbox.size(); i++) {
                        gv.khoiphuc(checkbox.get(i));
                    }
                }

                vtData.clear();
                vtData.addAll(gv.getThongTin(Integer.parseInt(txtPage.getText())));
                model.setDataVector(vtData, vtHeader);
                tba.setModel(model);
                guiBase.isEnabled(bts[3]);
                check = 0;

            }
        });

        bts[4].addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                tba.clearSelection();
                lbMa.setText("");
                txtHo.setText("");
                txtTen.setText("");
                cbNam.setSelectedItem("YYYY");
                cbThang.setSelectedItem("MM");
                cbNgay.setSelectedItem("DD");
                cbGioiTinh.setSelectedItem("");
                cbKhoa.setSelectedItem("");
            }
        });

        btImportExcel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                gv.importExcel(file.getFileExcelNameOpen(pnPaint));
            }
        });

        btExportExcel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                gv.exportExcel(file.getFileExcelNameSave(pnPaint));
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
}
