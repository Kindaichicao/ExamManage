/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_Exam;

import BUS_EXAM.*;
import DTO_EXAM.LopDTO;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ASUS
 */
public class PanelQuanLyLop {

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
    LopDTO lhoantac;

    public PanelQuanLyLop(String pnCenter) {
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
        //reing
        LopBUS l = new LopBUS();
        String[] temp = {"", "Mã lớp", "Tên lớp", "Khoa", "Tên cố vấn"};
        info = temp;
        MyLabel lbMaLop = new MyLabel("Mã lớp :");
        lbMaLop.setPreferredSize(new Dimension(120, 30));
        top.add(lbMaLop);
        MyLabel lbMa = new MyLabel("L001");
        lbMa.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.62), 30));
        top.add(lbMa);

        MyLabel lbTen = new MyLabel(info[2]);
        MyTextField txtTen = new MyTextField();
        top.add(lbTen);
        top.add(txtTen);

        MyLabel lbKhoa = new MyLabel(info[3]);
        KhoaBUS khoabus = new KhoaBUS();
        String[] arrKhoa = new String[khoabus.getListKhoa().size()];
        khoabus.getListKhoa().toArray(arrKhoa);
        MyComboBox cbKhoa = new MyComboBox(arrKhoa);
        cbKhoa.addItem("");
        cbKhoa.setSelectedItem("");
        top.add(lbKhoa);
        top.add(cbKhoa);

        MyLabel lbCV = new MyLabel(info[4]);
        GiangVienBUS gvbus = new GiangVienBUS();
        String[] arrCV = new String[gvbus.getListGiangVien().size()];
        gvbus.getListGiangVien().toArray(arrCV);
        MyComboBox cbCV = new MyComboBox(arrCV);
        cbCV.addItem("");
        cbCV.setSelectedItem("");

        top.add(lbCV);
        top.add(cbCV);

        vtHeader.clear();
        vtData.clear();
        for (int i = 0; i < info.length; i++) {
            vtHeader.add(info[i]);
            cbSearch.addItem(info[i]);
        }
        infoData = new LopBUS();
        vtData.addAll(infoData.getThongTin(1));
        model.setDataVector(vtData, vtHeader);
        tba.setModel(model);
        cout = infoData.coutThongTin();
        tba.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                int i = tba.getSelectedRow();
                if (i != -1) {
                    lbMa.setText(String.valueOf(model.getValueAt(i, 1)));
                    txtTen.setText(String.valueOf(model.getValueAt(i, 2)));
                    cbKhoa.setSelectedItem(String.valueOf(model.getValueAt(i, 3)));
                    cbCV.setSelectedItem(String.valueOf(model.getValueAt(i, 4)));
                }
            }
        });

        ArrayList<String> checkbox = new ArrayList<String>();
        bts[0].addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                int i = tba.getSelectedRow();
                if (i == -1) {
                    new ErrorFrame("Vui lòng chọn hàng cần xóa!");
                } else if (i == 0) {
                    new ErrorFrame("Không thể xóa lớp này!");
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
                                if (l.xoa(ma)) {
                                    new ErrorFrame("Xóa thành công!");
                                    check = 1;
                                    mahoantac = ma;
                                } else {
                                    new ErrorFrame("Xóa thất bại!");
                                }
                            } else {
                                for (int j = 0; j < checkbox.size(); j++) {
                                    l.xoa(checkbox.get(j));
                                }
                                check = 4;
                            }
                            vtData.clear();
                            vtData.addAll(infoData.getThongTin(Integer.parseInt(txtPage.getText())));
                            model.setDataVector(vtData, vtHeader);
                            tba.setModel(model);
                            tba.clearSelection();
                            lbMa.setText("");
                            txtTen.setText("");
                            cbKhoa.setSelectedItem("");
                            cbCV.setSelectedItem("");
                            guiBase.setEnabled(bts[3]);

                        }
                    });
                }
            }
        });

        bts[1].addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                if (txtTen.getText().equals("") || cbKhoa.getSelectedItem().equals("") || cbCV.getSelectedItem().equals("")) {
                    new ErrorFrame("Vui lòng điền đầy đủ thông tin!");
                } else if (!CheckError.isNameSubject(txtTen.getText())) {
                    new ErrorFrame("Tên Lớp Không Hợp Lệ");
                } else {
                    ErrorFrame err = new ErrorFrame("Bạn có chắc chắn muốn thêm không?");
                    err.btnOK.addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent arg0) {
                            String maKhoa = khoabus.getList().get(cbKhoa.getSelectedIndex() + 1).getMaKhoa();
                            String maGV = gvbus.getList().get(cbCV.getSelectedIndex()).getMaUser();
                            LopDTO dtL = new LopDTO();
                            dtL.setTenLop(txtTen.getText());
                            dtL.setMaKhoa(maKhoa);
                            dtL.setMaCoVan(maGV);
                            l.them(dtL);
                            new ErrorFrame("Thêm thành công!");
                            vtData.clear();
                            vtData.addAll(infoData.getThongTin(Integer.parseInt(txtPage.getText())));
                            model.setDataVector(vtData, vtHeader);
                            tba.setModel(model);
                            lbMa.setText("");
                            txtTen.setText("");
                            cbKhoa.setSelectedItem("");
                            cbCV.setSelectedItem("");
                            mahoantac = dtL.getMaLop();
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
                } else if (i == 0) {
                    new ErrorFrame("Không thể sửa lớp này!");
                } else {
                    if (txtTen.getText().equals("") || cbKhoa.getSelectedItem().equals("") || cbCV.getSelectedItem().equals("")) {
                        new ErrorFrame("Vui lòng điền đầy đủ thông tin!");
                    } else if (!CheckError.isNameSubject(txtTen.getText())) {
                        new ErrorFrame("Tên Lớp Không Hợp Lệ");
                    } else {
                        ErrorFrame err = new ErrorFrame("Bạn có chắc chắn muốn sửa không?");
                        err.btnOK.addMouseListener(new MouseAdapter() {
                            public void mouseClicked(MouseEvent arg0) {
                                String maKhoa = khoabus.getList().get(cbKhoa.getSelectedIndex() + 1).getMaKhoa();
                                String maGV = gvbus.getList().get(cbCV.getSelectedIndex()).getMaUser();
                                LopDTO dtL = new LopDTO();
                                dtL.setTenLop(txtTen.getText());
                                dtL.setMaKhoa(maKhoa);
                                dtL.setMaCoVan(maGV);
                                lhoantac = l.getObject(String.valueOf(model.getValueAt(i, 1)));
                                l.sua(dtL, String.valueOf(model.getValueAt(i, 1)));
                                new ErrorFrame("Sửa thành công!");
                                mahoantac = String.valueOf(model.getValueAt(i, 1));
                                vtData.clear();
                                vtData.addAll(infoData.getThongTin(Integer.parseInt(txtPage.getText())));
                                model.setDataVector(vtData, vtHeader);
                                tba.setModel(model);
                                lbMa.setText("");
                                txtTen.setText("");
                                cbKhoa.setSelectedItem("");
                                cbCV.setSelectedItem("");
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
                    l.khoiphuc(mahoantac);
                } else if (check == 2) {
                    l.xoavinhvien(mahoantac);
                } else if (check == 3) {
                    l.sua(lhoantac, mahoantac);
                } else if (check == 4) {
                    for (int i = 0; i < checkbox.size(); i++) {
                        l.khoiphuc(checkbox.get(i));
                    }
                }

                vtData.clear();
                vtData.addAll(l.getThongTin(Integer.parseInt(txtPage.getText())));
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
                txtTen.setText("");
                cbKhoa.setSelectedItem("");
                cbCV.setSelectedItem("");
            }
        });

        btImportExcel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                l.importExcel(file.getFileExcelNameOpen(pnPaint));
            }
        });

        btExportExcel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                l.exportExcel(file.getFileExcelNameSave(pnPaint));
            }
        });

        //het
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
