/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_Exam;

import BUS_EXAM.BaseBUS;
import BUS_EXAM.CauHoiBUS;
import BUS_EXAM.KhoaBUS;
import BUS_EXAM.KyThiBUS;
import BUS_EXAM.LopBUS;
import BUS_EXAM.SinhVienBUS;
import DTO_EXAM.KyThiDTO;
import DTO_EXAM.SinhVienDTO;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ASUS
 */
public class PanelQuanLyKyThi {

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
    KyThiDTO kthoantac;

    public PanelQuanLyKyThi(String pnCenter) {
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
        KyThiBUS kt = new KyThiBUS();
        top.setLayout(new FlowLayout(0, 30, 20));
        String[] temp = {"", "Mã kỳ thi", "Tên kỳ thi", "Ngày bắt đầu", "Ngày kết thúc"};
        info = temp;
        MyLabel lbMaKT = new MyLabel("Mã kỳ thi :");
        lbMaKT.setPreferredSize(new Dimension(120, 30));
        top.add(lbMaKT);
        MyLabel lbMa = new MyLabel("");
        lbMa.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.65), 30));
        top.add(lbMa);

        MyLabel lbTen = new MyLabel(info[2]);
        MyTextField txtTen = new MyTextField();
        top.add(lbTen);
        top.add(txtTen);

        MyLabel lbNgayBD = new MyLabel(info[3]);
        lbNgayBD.setPreferredSize(new Dimension(100, 30));
        JPanel pnNgayBD = new JPanel();
        pnNgayBD.setBackground(Color.white);
        pnNgayBD.setLayout(new FlowLayout(0, 10, 0));
        pnNgayBD.setPreferredSize(new Dimension(500, 30));

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

        pnNgayBD.add(cbNam);
        pnNgayBD.add(cbThang);
        pnNgayBD.add(cbNgay);
        top.add(lbNgayBD);
        top.add(pnNgayBD);

        MyLabel lbNgayKT = new MyLabel(info[4]);
        lbNgayKT.setPreferredSize(new Dimension(110, 30));
        JPanel pnNgayKT = new JPanel();
        pnNgayKT.setBackground(Color.white);
        pnNgayKT.setLayout(new FlowLayout(0, 10, 0));
        pnNgayKT.setPreferredSize(new Dimension(250, 30));

        MyComboBox cbNam1 = new MyComboBox(nam);
        cbNam1.setPreferredSize(new Dimension(90, 30));
        cbNam1.setFont(new Font("Arial", Font.PLAIN, 12));
        cbNam1.setSelectedItem("YYYY");

        MyComboBox cbThang1 = new MyComboBox(thang);
        cbThang1.setFont(new Font("Arial", Font.PLAIN, 12));
        cbThang1.setPreferredSize(new Dimension(50, 30));
        cbThang1.setSelectedItem("MM");

        MyComboBox cbNgay1 = new MyComboBox();
        cbNgay1.setFont(new Font("Arial", Font.PLAIN, 12));
        cbNgay1.setPreferredSize(new Dimension(50, 30));
        cbNgay1.addItem("DD");
        cbNgay1.setSelectedItem("DD");
        cbThang1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getSource() == cbThang1) {
                    int numDate = 0;
                    if (!cbThang1.getSelectedItem().equals("MM") && !cbNam1.getSelectedItem().equals("YYYY")) {
                        int x = Integer.parseInt(cbThang1.getSelectedItem().toString());
                        if (x == 2) {
                            int y = Integer.parseInt(cbNam1.getSelectedItem().toString());
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
                    cbNgay1.removeAllItems();
                    cbNgay1.addItem("DD");
                    for (int i = 1; i <= numDate; i++) {
                        if (i < 10) {
                            ngay[i] = "0" + Integer.toString(i);
                        } else {
                            ngay[i] = Integer.toString(i);
                        }
                        cbNgay1.addItem(ngay[i]);
                    }
                }
            }
        });
        pnNgayKT.add(cbNam1);
        pnNgayKT.add(cbThang1);
        pnNgayKT.add(cbNgay1);
        top.add(lbNgayKT);
        top.add(pnNgayKT);

        vtHeader.clear();
        vtData.clear();
        for (int i = 0; i < info.length; i++) {
            vtHeader.add(info[i]);
            cbSearch.addItem(info[i]);
        }
        infoData = new KyThiBUS();
        vtData.addAll(infoData.getThongTin(1));
        model.setDataVector(vtData, vtHeader);
        tba.setModel(model);
        tba.removeColumn(tba.getColumnModel().getColumn(0));
        cout = infoData.coutThongTin();

        tba.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int i = tba.getSelectedRow();
                if (i != -1) {
                    lbMa.setText(String.valueOf(model.getValueAt(i, 1)));
                    txtTen.setText(String.valueOf(model.getValueAt(i, 2)));
                    String[] time = String.valueOf(model.getValueAt(i, 3)).split("-");
                    cbNam.setSelectedItem(time[2]);
                    cbThang.setSelectedItem(time[1]);
                    cbNgay.setSelectedItem(time[0]);
                    String[] times = String.valueOf(model.getValueAt(i, 4)).split("-");
                    cbNam1.setSelectedItem(times[2]);
                    cbThang1.setSelectedItem(times[1]);
                    cbNgay1.setSelectedItem(times[0]);
                }
            }
        });

        guiBase.isEnabled(bts[0]);

        bts[1].addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                if (txtTen.getText().equals("") || cbNam.getSelectedItem().equals("YYYY") || cbThang.getSelectedItem().equals("MM") || cbNgay.getSelectedItem().equals("DD") || cbNam1.getSelectedItem().equals("YYYY") || cbThang1.getSelectedItem().equals("MM") || cbNgay1.getSelectedItem().equals("DD")) {
                    new ErrorFrame("Vui lòng điền đầy đủ thông tin!");
                } else {
                    String ngayBD = cbNam.getSelectedItem().toString() + "-" + cbThang.getSelectedItem().toString() + "-" + cbNgay.getSelectedItem().toString();
                    String ngayKT = cbNam1.getSelectedItem().toString() + "-" + cbThang1.getSelectedItem().toString() + "-" + cbNgay1.getSelectedItem().toString();
                    if (ngayBD.compareTo(ngayKT) > 0) {
                        new ErrorFrame("Lỗi: Ngày bắt đầu lớn hơn ngày kết thúc!");
                    } else if (!CheckError.isNameSubject(txtTen.getText())) {
                        new ErrorFrame("Tên Kì Thi Không Hợp Lệ");
                    } else {
                        ErrorFrame err = new ErrorFrame("Bạn có chắc chắn muốn thêm không?");
                        err.btnOK.addMouseListener(new MouseAdapter() {
                            public void mouseClicked(MouseEvent arg0) {
                                KyThiDTO dtKT = new KyThiDTO();
                                dtKT.setTenKyThi(txtTen.getText());
                                dtKT.setNgayBatDau(ngayBD);
                                dtKT.setNgayKetThuc(ngayKT);
                                kt.them(dtKT);
                                new ErrorFrame("Thêm thành công!");
                                vtData.clear();
                                vtData.addAll(infoData.getThongTin(Integer.parseInt(txtPage.getText())));
                                model.setDataVector(vtData, vtHeader);
                                tba.setModel(model);
                                tba.removeColumn(tba.getColumnModel().getColumn(0));
                                lbMa.setText("");
                                txtTen.setText("");
                                cbNam.setSelectedItem("YYYY");
                                cbThang.setSelectedItem("MM");
                                cbNgay.setSelectedItem("DD");
                                cbNam1.setSelectedItem("YYYY");
                                cbThang1.setSelectedItem("MM");
                                cbNgay1.setSelectedItem("DD");
                                mahoantac = dtKT.getMaKyThi();
                                check = 2;
                                guiBase.setEnabled(bts[3]);
                            }
                        });
                    }
                }
            }
        });

        bts[2].addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                int i = tba.getSelectedRow();
                if (i == -1) {
                    new ErrorFrame("Vui lòng chọn hàng cần sửa!");
                } else {
                    if (txtTen.getText().equals("") || cbNam.getSelectedItem().equals("YYYY") || cbThang.getSelectedItem().equals("MM") || cbNgay.getSelectedItem().equals("DD") || cbNam1.getSelectedItem().equals("YYYY") || cbThang1.getSelectedItem().equals("MM") || cbNgay1.getSelectedItem().equals("DD")) {
                        new ErrorFrame("Vui lòng điền đầy đủ thông tin!");
                    } else if (!CheckError.isNameSubject(txtTen.getText())) {
                        new ErrorFrame("Tên Kì Thi Không Hợp Lệ");
                    } else {
                        String ngayBD = cbNam.getSelectedItem().toString() + "-" + cbThang.getSelectedItem().toString() + "-" + cbNgay.getSelectedItem().toString();
                        String ngayKT = cbNam1.getSelectedItem().toString() + "-" + cbThang1.getSelectedItem().toString() + "-" + cbNgay1.getSelectedItem().toString();
                        if (ngayBD.compareTo(ngayKT) > 0) {
                            new ErrorFrame("Lôi: Ngày bắt đầu lơn hơn ngày kết thúc!");
                        } else {
                            ErrorFrame err = new ErrorFrame("Bạn có chắc chắn muốn sửa không?");
                            err.btnOK.addMouseListener(new MouseAdapter() {
                                public void mouseClicked(MouseEvent arg0) {
                                    KyThiDTO dtKT = new KyThiDTO();
                                    dtKT.setTenKyThi(txtTen.getText());
                                    dtKT.setNgayBatDau(ngayBD);
                                    dtKT.setNgayKetThuc(ngayKT);
                                    kthoantac = kt.getObject(String.valueOf(model.getValueAt(i, 0)));
                                    kt.sua(dtKT, String.valueOf(model.getValueAt(i, 0)));
                                    new ErrorFrame("Sửa thành công!");
                                    mahoantac = String.valueOf(model.getValueAt(i, 0));
                                    vtData.clear();
                                    vtData.addAll(infoData.getThongTin(Integer.parseInt(txtPage.getText())));
                                    model.setDataVector(vtData, vtHeader);
                                    tba.setModel(model);
                                    tba.removeColumn(tba.getColumnModel().getColumn(0));
                                    lbMa.setText("");
                                    txtTen.setText("");
                                    cbNam.setSelectedItem("YYYY");
                                    cbThang.setSelectedItem("MM");
                                    cbNgay.setSelectedItem("DD");
                                    cbNam1.setSelectedItem("YYYY");
                                    cbThang1.setSelectedItem("MM");
                                    cbNgay1.setSelectedItem("DD");
                                    check = 3;
                                    guiBase.setEnabled(bts[3]);
                                }
                            });
                        }
                    }
                }
            }
        });

        bts[3].addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                if (check == 2) {
                    kt.xoavinhvien(mahoantac);
                    vtData.clear();
                    vtData.addAll(kt.getThongTin(Integer.parseInt(txtPage.getText())));
                    model.setDataVector(vtData, vtHeader);
                    tba.setModel(model);
                    guiBase.isEnabled(bts[3]);
                    check = 0;
                } else if (check == 3) {
                    kt.sua(kthoantac, mahoantac);
                    vtData.clear();
                    vtData.addAll(kt.getThongTin(Integer.parseInt(txtPage.getText())));
                    model.setDataVector(vtData, vtHeader);
                    tba.setModel(model);
                    guiBase.isEnabled(bts[3]);
                    check = 0;
                }
                tba.removeColumn(tba.getColumnModel().getColumn(0));
            }
        });

        bts[4].addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                tba.clearSelection();
                lbMa.setText("");
                txtTen.setText("");
                cbNam.setSelectedItem("YYYY");
                cbThang.setSelectedItem("MM");
                cbNgay.setSelectedItem("DD");
                cbNam1.setSelectedItem("YYYY");
                cbThang1.setSelectedItem("MM");
                cbNgay1.setSelectedItem("DD");
            }
        });

        btImportExcel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                kt.importExcel(file.getFileExcelNameOpen(pnPaint));
            }
        });

        btExportExcel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                kt.exportExcel(file.getFileExcelNameSave(pnPaint));
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
                    tba.removeColumn(tba.getColumnModel().getColumn(0));
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
                    tba.removeColumn(tba.getColumnModel().getColumn(0));
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
                    tba.removeColumn(tba.getColumnModel().getColumn(0));
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
