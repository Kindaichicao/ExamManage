/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_Exam;

import BUS_EXAM.BaseBUS;
import BUS_EXAM.CauHoiBUS;
import BUS_EXAM.GiangVienBUS;
import BUS_EXAM.GiangVienMonBUS;
import BUS_EXAM.KhoaBUS;
import BUS_EXAM.LoginBUS;
import BUS_EXAM.LopBUS;
import BUS_EXAM.SinhVienBUS;
import DTO_EXAM.CauHoiDTO;
import DTO_EXAM.MonDTO;
import DTO_EXAM.SinhVienDTO;
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
public class PanelQuanLyCauHoi {

    JPanel pnPaint = new JPanel();
    int numPage = 1;
    int check = 0;
    int cout = 0;
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
    CauHoiDTO chhoantac;

    public PanelQuanLyCauHoi(String pnCenter) {
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
        String[] info = null;
        JTextField txtPage = new JTextField("1");

        tba.setAutoCreateRowSorter(true);

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
        CauHoiBUS ch = new CauHoiBUS();
        GiangVienBUS gv = new GiangVienBUS();
        GiangVienMonBUS gvm = new GiangVienMonBUS();
        String maGV = LoginBUS.isUser.getMaUser();
        top.setLayout(new FlowLayout(1, 5, 10));
        top.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.8), (int) ((int) heightFrame * 0.38)));
        btn.setLayout(new FlowLayout(1, 20, 10));
        tb.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.75), (int) ((int) heightFrame * 0.5)));
        pane.setPreferredSize(new Dimension(new Dimension((int) ((int) widthFrame * 0.75), (int) ((int) heightFrame * 0.38))));
        String[] temp = {"","Mã câu hỏi", "Nội dung", "A", "B", "C", "D", "Đáp án", "Độ khó", "Môn"};
        info = temp;
        MyLabel lbMaCH = new MyLabel("Mã câu hỏi :");
        lbMaCH.setPreferredSize(new Dimension(120, 30));
        top.add(lbMaCH);
        MyLabel lbMa = new MyLabel("");
        lbMa.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.65), 30));
        top.add(lbMa);

        MyLabel lbND = new MyLabel(info[2]);
        MyTextField txtND = new MyTextField();
        txtND.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.65), 30));
        top.add(lbND);
        top.add(txtND);

        MyLabel lbA = new MyLabel(info[3]);
        MyTextField txtA = new MyTextField();
        txtA.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.65), 30));
        top.add(lbA);
        top.add(txtA);

        MyLabel lbB = new MyLabel(info[4]);
        MyTextField txtB = new MyTextField();
        txtB.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.65), 30));
        top.add(lbB);
        top.add(txtB);

        MyLabel lbC = new MyLabel(info[5]);
        MyTextField txtC = new MyTextField();
        txtC.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.65), 30));
        top.add(lbC);
        top.add(txtC);

        MyLabel lbD = new MyLabel(info[6]);
        MyTextField txtD = new MyTextField();
        txtD.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.65), 30));
        top.add(lbD);
        top.add(txtD);

        MyLabel lbDA = new MyLabel(info[7]);
        String[] arrDA = {"", "A", "B", "C", "D"};
        MyComboBox cbDA = new MyComboBox(arrDA);
        cbDA.setPreferredSize(new Dimension(90, 30));
        top.add(lbDA);
        top.add(cbDA);

        MyLabel lbDK = new MyLabel(info[8]);
        String[] arrDK = {"", "1", "2", "3"};
        MyComboBox cbDK = new MyComboBox(arrDK);
        cbDK.setPreferredSize(new Dimension(90, 30));
        top.add(lbDK);
        top.add(cbDK);

        MyLabel lbMon = new MyLabel(info[9]);
        MonDTO[] arrMon = new MonDTO[gv.getMonQuanLy(maGV).size()];
        gv.getMonQuanLy(maGV).toArray(arrMon);
        System.out.println(gv.getMonQuanLy(maGV));
        String[] arr = new String[gv.getMonQuanLy(maGV).size() + 1];
        arr[0] = "";
        for (int i = 0; i < gv.getMonQuanLy(maGV).size(); i++) {
            arr[i + 1] = arrMon[i].getTenMon();
        }
        MyComboBox cbMon = new MyComboBox(arr);
        top.add(lbMon);
        top.add(cbMon);

        vtHeader.clear();
        vtData.clear();
        for (int i = 0; i < info.length; i++) {
            vtHeader.add(info[i]);
            cbSearch.addItem(info[i]);
        }
        infoData = new CauHoiBUS();
        String[] arrMa = new String[gv.getMonQuanLy(maGV).size()];
        for (int i = 0; i < gv.getMonQuanLy(maGV).size(); i++) {
            arrMa[i] = arrMon[i].getMaMon();
        }
        vtData.addAll(ch.getThongTin(arrMa, 1));
        model.setDataVector(vtData, vtHeader);
        tba.setModel(model);
        cout = ch.coutThongTin(arrMa);

        tba.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int i = tba.getSelectedRow();
                if (i != -1) {
                    lbMa.setText(String.valueOf(model.getValueAt(i, 1)));
                    txtND.setText(String.valueOf(model.getValueAt(i, 2)));
                    txtA.setText(String.valueOf(model.getValueAt(i, 3)));
                    txtB.setText(String.valueOf(model.getValueAt(i, 4)));
                    txtC.setText(String.valueOf(model.getValueAt(i, 5)));
                    txtD.setText(String.valueOf(model.getValueAt(i, 6)));
                    cbDA.setSelectedItem(String.valueOf(model.getValueAt(i, 7)));
                    cbDK.setSelectedItem(String.valueOf(model.getValueAt(i, 8)));
                    cbMon.setSelectedItem(String.valueOf(model.getValueAt(i, 9)));
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
                    for(int j=0;j<tba.getRowCount();j++) {
                        String checked=tba.getValueAt(j,0).toString();
                        if(checked.equals("true")) {
                            checkbox.add(String.valueOf(tba.getValueAt(j, 1)));
                        }
                    }
                    ErrorFrame err = new ErrorFrame("Bạn có chắc chắn muốn xóa không?");
                    err.btnOK.addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent arg0) {
                            String ma = String.valueOf(model.getValueAt(i, 1));
                            if(checkbox.isEmpty()){
                                if (ch.xoa(ma)) {
                                    new ErrorFrame("Xóa thành công!");
                                    check = 1;
                                    mahoantac = ma;
                                } else {
                                new ErrorFrame("Xóa thất bại!");
                                }
                            }else{
                                for(int j=0; j<checkbox.size();j++)
                                    ch.xoa(checkbox.get(j));
                                check = 4;
                            }
                            vtData.clear();
                            vtData.addAll(ch.getThongTin(arrMa,Integer.parseInt(txtPage.getText())));
                            model.setDataVector(vtData, vtHeader);
                            tba.setModel(model);
                            lbMa.setText("");
                            txtND.setText("");
                            txtA.setText("");
                            txtB.setText("");
                            txtC.setText("");
                            txtD.setText("");
                            cbDA.setSelectedItem("");
                            cbDK.setSelectedItem("");
                            cbMon.setSelectedItem("");
                            guiBase.setEnabled(bts[3]);
                        }
                    });
                }
            }
        });

        bts[1].addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                if (txtND.getText().equals("") || txtA.getText().equals("") || txtB.getText().equals("") || txtC.getText().equals("") || txtD.getText().equals("") || cbDA.getSelectedItem().equals("") || cbDK.getSelectedItem().equals("") || cbMon.getSelectedItem().equals("")) {
                    new ErrorFrame("Vui lòng điền đầy đủ thông tin!");
                } else {
                    ErrorFrame err = new ErrorFrame("Bạn có chắc chắn muốn thêm không?");
                    err.btnOK.addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent arg0) {
                            CauHoiDTO dtCH = new CauHoiDTO();
                            dtCH.setMaPhanCong(gvm.getMa(maGV, arrMon[cbMon.getSelectedIndex() - 1].getMaMon()));
                            dtCH.setNoiDung(txtND.getText());
                            dtCH.setDapanA(txtA.getText());
                            dtCH.setDapanB(txtB.getText());
                            dtCH.setDapanC(txtC.getText());
                            dtCH.setDapanD(txtD.getText());
                            dtCH.setDapanDung(cbDA.getSelectedItem().toString());
                            dtCH.setDoKho(cbDK.getSelectedIndex());
                            ch.them(dtCH);
                            new ErrorFrame("Thêm thành công!");
                            vtData.clear();
                            vtData.addAll(ch.getThongTin(arrMa, 1));
                            model.setDataVector(vtData, vtHeader);
                            tba.setModel(model);
                            lbMa.setText("");
                            txtND.setText("");
                            txtA.setText("");
                            txtB.setText("");
                            txtC.setText("");
                            txtD.setText("");
                            cbDA.setSelectedItem("");
                            cbDK.setSelectedItem("");
                            cbMon.setSelectedItem("");
                            mahoantac = dtCH.getMaCauHoi();
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
                    if (txtND.getText().equals("") || txtA.getText().equals("") || txtB.getText().equals("") || txtC.getText().equals("") || txtD.getText().equals("") || cbDA.getSelectedItem().equals("") || cbDK.getSelectedItem().equals("") || cbMon.getSelectedItem().equals("")) {
                        new ErrorFrame("Vui lòng điền đầy đủ thông tin!");
                    } else {
                        ErrorFrame err = new ErrorFrame("Bạn có chắc chắn muốn sửa không?");
                        err.btnOK.addMouseListener(new MouseAdapter() {
                            public void mouseClicked(MouseEvent arg0) {
                                CauHoiDTO dtCH = new CauHoiDTO();
                                dtCH.setMaPhanCong(gvm.getMa(maGV, arrMon[cbMon.getSelectedIndex() - 1].getMaMon()));
                                dtCH.setNoiDung(txtND.getText());
                                dtCH.setDapanA(txtA.getText());
                                dtCH.setDapanB(txtB.getText());
                                dtCH.setDapanC(txtC.getText());
                                dtCH.setDapanD(txtD.getText());
                                dtCH.setDapanDung(cbDA.getSelectedItem().toString());
                                dtCH.setDoKho(cbDK.getSelectedIndex());
                                ch.them(dtCH);
                                chhoantac = ch.getObject(String.valueOf(model.getValueAt(i, 1)));
                                ch.sua(dtCH, String.valueOf(model.getValueAt(i, 1)));
                                new ErrorFrame("Sửa thành công!");
                                mahoantac = String.valueOf(model.getValueAt(i, 1));
                                vtData.clear();
                                vtData.addAll(ch.getThongTin(arrMa, Integer.parseInt(txtPage.getText())));
                                model.setDataVector(vtData, vtHeader);
                                tba.setModel(model);
                                lbMa.setText("");
                                txtND.setText("");
                                txtA.setText("");
                                txtB.setText("");
                                txtC.setText("");
                                txtD.setText("");
                                cbDA.setSelectedItem("");
                                cbDK.setSelectedItem("");
                                cbMon.setSelectedItem("");
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
                    ch.khoiphuc(mahoantac);
                } else if (check == 2) {
                    ch.xoavinhvien(mahoantac);
                } else if (check == 3) {
                    ch.sua(chhoantac, mahoantac);
                }else if (check == 4) {
                    for(int i=0; i<checkbox.size();i++)
                        ch.khoiphuc(checkbox.get(i));
                }

                vtData.clear();
                vtData.addAll(ch.getThongTin(arrMa, Integer.parseInt(txtPage.getText())));
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
                txtND.setText("");
                txtA.setText("");
                txtB.setText("");
                txtC.setText("");
                txtD.setText("");
                cbDA.setSelectedItem("");
                cbDK.setSelectedItem("");
                cbMon.setSelectedItem("");
            }
        });

        btSearch.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                if (txtSearch.getText().equals("Search ...")) {
                    new ErrorFrame("Bạn chưa nhập từ khóa!");
                } else {
                    Vector vtSearch = new Vector();
                    Vector vtcData = new Vector();
                    DefaultTableModel modelSearch = new DefaultTableModel();
                    int index = cbSearch.getSelectedIndex();
                    for (int ind = 1; ind <= cout; ind++) {
                        vtcData.addAll(ch.getThongTin(arrMa, ind));
                        modelSearch.setDataVector(vtcData, vtHeader);
                    }
                    for (int i = 0; i < modelSearch.getRowCount(); i++) {
                        if (modelSearch.getValueAt(i, index).toString().contains(txtSearch.getText())) {
                            vtSearch.add(modelSearch.getDataVector().get(i));
                        }
                    }
                    tba.setModel(new DefaultTableModel(vtSearch, vtHeader));
                }
            }
        });

        btImportExcel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                ch.importExcel(file.getFileExcelNameOpen(pnPaint));
            }
        });

        btExportExcel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                ch.exportExcel(file.getFileExcelNameSave(pnPaint));
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
                    CauHoiBUS ch = new CauHoiBUS();
                    vtData.addAll(ch.getThongTin(arrMa, page));
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
                    vtData.addAll(ch.getThongTin(arrMa, page));
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
                    vtData.addAll(ch.getThongTin(arrMa, page));
                    tba.setModel(model);
                    lbPage.setText(txtPage.getText() + "/" + numPage);
                }
            }
        });

        //tba.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
        //tba.getColumnModel().getColumn(0).setPreferredWidth(5);

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
