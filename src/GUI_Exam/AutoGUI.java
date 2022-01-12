/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_Exam;

import BUS_EXAM.*;
import BUS_EXAM.MonSinhVienBUS;
import static DAO_EXAM.BaseDAO.toDate;
import DAO_EXAM.ContainerDAO;
import DTO_EXAM.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author "ThanhCute"
 */
public class AutoGUI {

    JPanel pnPaint = new JPanel();
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int widthFrame = (int) ((int) screenSize.getWidth() * 0.9);
    int heightFrame = (int) ((int) screenSize.getHeight() * 0.9);
    String[] titleProfile = {"Mã Số: ", "Họ Tên: ", "Ngày Sinh: ", "Quê Quán: ", "Khoa: "};
    String[] menu = {"Quản lý Sinh Viên", "Quản Lý Giảng Viên", "Quản Lý Học Phần", "quản Lý Kết Quả", "Thống Kê", "quản Lý Đề Thi"};
    MyCheckBox[] cbQuyen = new MyCheckBox[11];
    MyComboBox[] cbbQuyen = new MyComboBox[11];
    JLabel lbMaQuyen = new JLabel();
    MyTextField txTenQuyen = new MyTextField();
    ArrayList<QuyenDTO> cvs = new ArrayList<QuyenDTO>();
    DefaultTableModel modelPQ = new DefaultTableModel();
    MyTable tbChucVu;
    int numPage = 1;

    String passhoantac;

    MyTable tba = new MyTable();
    public Vector vtHeader= new Vector();
    public Vector vtData= new Vector();
    DefaultTableModel  model = new DefaultTableModel(vtData, vtHeader);
    FileChooserGUI file = new FileChooserGUI();

    //kết thúc

    public static Map<Object, Function<Object, Object>> map_gui = new HashMap<Object, Function<Object, Object>>();

    Function<Object, Object> quanLy = (pnCenter) -> {

        String ma = (String) pnCenter;

        switch(Integer.parseInt(ma.substring(1))){
            case 2:{//Sinh Viên
                new PanelQuanLySinhVien(ma);
                break;
            }
            case 4:{
                new PanelQuanLyGiangVien(ma);
                break;
            }
            case 6:{//Nhân Viên
                new PanelQuanLyNhanVien(ma);
                break;
            }
            case 8:{//Câu hỏi
                new PanelQuanLyCauHoi(ma);
                break;
            }
            case 10:{//Môn học
                new PanelQuanLyMonHoc(ma);
                break;
            }
            case 12:{
                new PanelQuanLyBaiThi(ma);
                break;
            }
            case 14:{//Đề thi
                new PanelQuanLyDeThi(ma);
                break;
            }
            case 16:{//Kỳ Thi
                new PanelQuanLyKyThi(ma);
                break;
            }
            case 22:{//Khoa
                new PanelKhoa(ma);
                break;
            }
            case 18:{//Lớp
                new PanelQuanLyLop(ma);
                break;
            }
            default:{//Tài Khoản
                new PanelQuanLyTaiKhoan(ma);
                break;
            }
        };

        return UserFrame.pCenter;
    };
    Function<Object, Object> xem = (pnCenter) -> {
//        String title = (String) pnCenter;
        UserFrame.pCenter.removeAll();
        UserFrame.pCenter.validate();
        UserFrame.pCenter.repaint();
        UserFrame.pCenter.setLayout(new BorderLayout());
        UserFrame.pCenter.setBackground(Color.white);
        String ma = (String) pnCenter;
        JPanel tb = new JPanel();
        tb.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.75), (int) ((int) heightFrame )));
        tb.setBackground(Color.white);
        JScrollPane pane = new JScrollPane();
        pane.setPreferredSize(new Dimension(new Dimension((int) ((int) widthFrame * 0.75), (int) ((int) heightFrame * 0.7))));

        //Tìm kiếm
        JPanel pnSearch = new JPanel();
        pnSearch.setLayout(new FlowLayout(0, 30, 25));
        pnSearch.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.65), (int) ((int) heightFrame * 0.1)));
        pnSearch.setBackground(Color.white);

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
        pnSearch.add(txtSearch);
        pnSearch.add(cbSearch);
        tb.add(pnSearch);

        MyButton btSearch = new MyButton("Tìm Kiếm", MyButton.ADD);
        btSearch.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.09), (int) ((int) heightFrame * 0.04)));
        btSearch.setFont(btSearch.getFont().deriveFont(15.0f));
        tb.add(btSearch);

        String[] info= null;
        BaseBUS infoData;
        int cout=0;
        Vector vtHeader= new Vector();
        Vector vtData = new Vector();
        MyTable tba = new MyTable();
        DefaultTableModel  model = new DefaultTableModel(vtData, vtHeader);
        tba.setAutoCreateRowSorter(true);
        pane.setViewportView(tba);
        tb.add(pane);

        //Thông tin quản lý của từng đối tượng
        switch(Integer.parseInt(ma.substring(1))){
            case 1:{//Sinh Viên
                String[] temp = {"","Mã sinh viên","Họ", "Tên", "Ngày Sinh", "Giới Tính","Lớp","Khoa"};
                info=temp;
                vtHeader.clear();
                vtData.clear();
                infoData= new SinhVienBUS();
                break;
            }
            case 3:{//Giảng Viên
                String[] temp = {"","Mã giảng viên","Họ", "Tên", "Ngày Sinh", "Giới Tính","Khoa"};
                info=temp;
                infoData= new GiangVienBUS();
                break;
            }
            case 5:{//Nhân Viên
                String[] temp = {"","Mã nhân viên","Họ", "Tên", "Ngày Sinh", "Giới Tính","Khoa"};
                info=temp;
                infoData= new NhanVienBUS();
                break;
            }
            case 7:{//Câu hỏi
                String[] temp = {"","Mã câu hỏi","Nội dung", "A", "B", "C","D","Đáp án","Độ khó","Môn"};
                info=temp;
                infoData= new CauHoiBUS();
                break;
            }
            case 9:{//Môn học
                String[] temp = {"","Mã môn học","Tên môn", "Số tín chỉ"};
                info=temp;
                infoData= new MonBUS();
                break;
            }
            case 11:{//Bài thi
                String[] temp={"","Mã sinh viên", "Mã đề", "Danh sách câu trả lời","Danh sách đúng sai", "Số câu đúng", "Điểm"};
                info=temp;
                infoData= new BaiThiBUS();
                break;
            }
            case 13:{//Đề thi
                String[] temp = {"","Mã đề","Tên môn", "Tên kỳ thi", "Thời gian làm bài", "Ngày Thi", "Giờ Thi","Số lượng câu hỏi"};
                info=temp;
                infoData= new DeThiBUS();
                break;
            }
            case 21:{//Khoa
                String[] temp = {"","Mã khoa","Tên khoa"};
                info=temp;
                infoData= new KhoaBUS();
                break;
            }
            case 15:{//Kỳ Thi
                String[] temp = {"","Mã kỳ thi","Tên kỳ thi", "Ngày bắt đầu", "Ngày kết thúc"};
                info=temp;
                infoData= new KyThiBUS();
                break;
            }
            case 17:{//Lớp
                String[] temp = {"","Mã lớp","Tên lớp", "Khoa", "Tên cố vấn"};
                info=temp;
                infoData= new LopBUS();
                break;
            }
            default:{//Tài khoản
                String[] temp = {"","Mã tài khoản","Mật khẩu", "Tên chức vụ"};
                info=temp;
                infoData= new UserBUS();
                break;
            }
        };
        for (int i = 0; i < info.length; i++){
            vtHeader.add(info[i]);
            cbSearch.addItem(info[i]);
        }
        vtData.addAll(infoData.getThongTin(1));
        model.setDataVector(vtData, vtHeader);
        tba.setModel(model);
        cout=infoData.coutThongTin();
        btSearch.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                if(txtSearch.getText().equals("Search ..."))
                    new ErrorFrame("Bạn chưa nhập từ khóa!");
                else{
                    Vector vtSearch= new Vector();
                    int index = cbSearch.getSelectedIndex();
                    for (int i = 0; i< model.getRowCount(); i++){
                        if (model.getValueAt(i,index).toString().contains(txtSearch.getText())){
                            vtSearch.add(model.getDataVector().get(i));
                        }
                    }
                    tba.setModel(new DefaultTableModel(vtSearch, vtHeader));
                    tba.removeColumn(tba.getColumnModel().getColumn(0));
                }
            }
        });
        tba.removeColumn(tba.getColumnModel().getColumn(0));

        MyPanel pnPage = new MyPanel();
        pnPage.setLayout(new FlowLayout(4,10,3));
        pnPage.setPreferredSize(new Dimension(new Dimension((int) ((int) widthFrame * 0.5), (int) ((int) heightFrame * 0.05))));
        pnPage.setBackground(Color.white);

        MyButton btPre = new MyButton("<<",7);
        btPre.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.03), (int) ((int) heightFrame * 0.04)));
        MyButton btNext = new MyButton(">>",7);
        btNext.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.03), (int) ((int) heightFrame * 0.04)));
        JTextField txtPage = new JTextField("1");
        txtPage.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.03), (int) ((int) heightFrame * 0.04)));
        txtPage.setHorizontalAlignment(JTextField.CENTER);
        pnPage.add(btPre);
        pnPage.add(txtPage);
        pnPage.add(btNext);

        //Tính số trang
        if(cout%25==0)
            numPage= cout/25;
        else numPage= cout/25+1;
        MyLabel lbPage= new MyLabel("1/"+numPage);
        lbPage.setFont(new Font("Arial", Font.PLAIN,14));

        btPre.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                if(Integer.parseInt(txtPage.getText())> 1){
                    int page=Integer.parseInt(txtPage.getText());
                    page--;
                    vtData.clear();
                    vtData.addAll(infoData.getThongTin(page));
                    model.setDataVector(vtData, vtHeader);
                    tba.setModel(model);
                    tba.removeColumn(tba.getColumnModel().getColumn(0));
                    txtPage.setText(Integer.toString(page));
                    lbPage.setText(txtPage.getText()+"/"+numPage);
                }
            }
        });

        btNext.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                if(Integer.parseInt(txtPage.getText())<numPage){
                    int page=Integer.parseInt(txtPage.getText());
                    page++;
                    vtData.clear();
                    vtData.addAll(infoData.getThongTin(page));
                    model.setDataVector(vtData, vtHeader);
                    tba.setModel(model);
                    tba.removeColumn(tba.getColumnModel().getColumn(0));
                    txtPage.setText(Integer.toString(page));
                    lbPage.setText(txtPage.getText()+"/"+numPage);
                }
            }
        });
        txtPage.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(Integer.parseInt(txtPage.getText())<=numPage && Integer.parseInt(txtPage.getText())>= 1){
                    int page=Integer.parseInt(txtPage.getText());
                    tba.setModel(new DefaultTableModel(infoData.getThongTin(page), vtHeader));
                    tba.removeColumn(tba.getColumnModel().getColumn(0));
                    lbPage.setText(txtPage.getText()+"/"+numPage);
                }
            }});
        pnPage.add(lbPage);
        tb.add(pnPage);

        MyPanel pnExcel = new MyPanel();
        pnExcel.setBackground(Color.white);
        pnExcel.setLayout(new FlowLayout(4,20,3));
        pnExcel.setPreferredSize(new Dimension(new Dimension((int) ((int) widthFrame * 0.25), (int) ((int) heightFrame * 0.05))));

        ImageIcon img = new ImageIcon("src/img/excel.png");
        MyButton btExportExcel = new MyButton("Xuất Excel",1);
        btExportExcel.setIcon(img);
        btExportExcel.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.08), (int) ((int) heightFrame * 0.04)));

        pnExcel.add(btExportExcel);
        tb.add(pnExcel);

        btExportExcel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                    infoData.exportExcel(file.getFileExcelNameSave(pnPaint));
            }
        });

        UserFrame.pCenter.add(tb, BorderLayout.CENTER);
        UserFrame.pCenter.validate();
        UserFrame.pCenter.repaint();
        return UserFrame.pCenter;
    };

    //LichThi
    Function<Object, Object> xemLichThi = (pnCenter) -> {
        UserFrame.pCenter.removeAll();
        UserFrame.pCenter.repaint();
        UserFrame.pCenter.validate();
        JFrame f = (JFrame) pnCenter;
        SinhVienDTO tempUser = (SinhVienDTO) LoginBUS.isUser;
        // Lấy danh sách môn thi
        ArrayList<MonDTO> mons = new ArrayList<MonDTO>();
        KyThiBUS busKyThi = new KyThiBUS();
        SinhVienBUS busSV = new SinhVienBUS();

        MonSinhVienBUS busMSV = new MonSinhVienBUS();
        ArrayList<KyThiDTO> kyThis = new ArrayList<>();
        kyThis = busKyThi.getList();
        KyThiDTO kythi = busKyThi.getKyThiHienTai();
        for(KyThiDTO kt:kyThis){
            if(checkTime(kt.getMaKyThi())){
                kythi = kt;
            }
        }
        mons = busMSV.getMonList(tempUser.getMaUser());

        //==========
        JPanel pnTong = new JPanel();
        UserFrame.pCenter.setBackground(Color.white);
        JScrollPane pane = new JScrollPane(pnTong,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        MyBorder bdTong = new MyBorder(new Color(70, 130, 180), 5, 0, 0);

        //Tiêu Đề
        JPanel pnTieuDe = new JPanel();
        JLabel lbTieuDe = new JLabel();

        pnTieuDe.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.77), (int) ((int) heightFrame * 0.1)));
        pnTieuDe.setBackground(new Color(26, 157, 255));
        lbTieuDe.setText(kythi.getTenKyThi().toUpperCase());
        lbTieuDe.setFont(lbTieuDe.getFont().deriveFont(25.0f));
        lbTieuDe.setForeground(Color.white);
        lbTieuDe.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.77), (int) ((int) heightFrame * 0.1)));
        lbTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
        pnTieuDe.add(lbTieuDe);

        pane.setBorder(bdTong);

        pnTong.setLayout(new FlowLayout(0, 50, 30));
        pnTong.setBackground(Color.white);
        pane.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.784), (int) ((int) heightFrame * 0.84)));

        pane.getViewport().setBackground(new Color(255, 255, 255));
        int dong = 1;

        if(mons.size()>0){
            for (MonDTO mon : mons) {
            DeThiBUS busDe = new DeThiBUS();
            DeThiDTO de = busDe.getDeMaMonKyThi(mon.getMaMon(), kythi.getMaKyThi());
            if(de!=null){
                JPanel pnDeThi = new JPanel();
                JPanel pnChan = new JPanel();
                JPanel pnNoiDung = new JPanel();
                String nameBtn = "Vào Thi";

                //Panel Tổng
                pnDeThi.setLayout(new BorderLayout());
                pnDeThi.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.34), (int) ((int) heightFrame * 0.3)));
                pnDeThi.setBackground(new Color(232, 242, 252));
                pnTong.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.77), (int) ((int) heightFrame * 0.3 + 30) * ((int) (dong + 1) / 2) + 30));
                dong++;
                //

                pnDeThi.setLayout(new BorderLayout());
                //Tiêu Đề
                JLabel lbDeThi = new JLabel(mon.getTenMon().toUpperCase());
                lbDeThi.setFont(lbDeThi.getFont().deriveFont(15.0f));
                lbDeThi.setForeground(Color.white);
                lbDeThi.setBackground(new Color(26, 157, 255));
                lbDeThi.setOpaque(true);
                lbDeThi.setHorizontalAlignment(SwingConstants.CENTER);
                lbDeThi.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.34), (int) ((int) heightFrame * 0.05)));
                pnDeThi.add(lbDeThi, BorderLayout.NORTH);
                //Kết Thúc Tiêu Đề

                //Nội Dung Panel
                pnNoiDung.setLayout(new FlowLayout(0, 10, 10));
                pnNoiDung.setPreferredSize(new Dimension(new Dimension((int) ((int) widthFrame * 0.25), 0)));
                pnNoiDung.setBackground(new Color(232, 242, 252));
                ImageIcon icMon = new ImageIcon("src/img/icons8-test-passed-30.png");
                ImageIcon icThoiGian = new ImageIcon("src/img/icons8-alarm-clock-30.png");
                ImageIcon icSoLuongCau = new ImageIcon("src/img/icons8-questionnaire-30.png");
                ImageIcon icSoTinChi = new ImageIcon("src/img/icons8-bookmark-30.png");
                JLabel lbMon = new JLabel(icMon);
                JLabel lbThoiGian = new JLabel(icThoiGian);
                JLabel lbSoLuongCau = new JLabel(icSoLuongCau);
                JLabel lbSoTinChi = new JLabel(icSoTinChi);
                createLabel(lbMon, "Ngày Thi: " + de.getGioThi().split(":")[0] + ":" + de.getGioThi().split(":")[1] + " / " + toDate(de.getNgayThi()));
                lbMon.setForeground(new Color(255, 127, 80));
                lbMon.setFont(lbMon.getFont().deriveFont(18.0f));
                createLabel(lbThoiGian, "Thời gian làm bài: " + Integer.toString(de.getThoigianLamBai()) + " phút");
                createLabel(lbSoLuongCau, "Số lượng câu hỏi: " + Integer.toString(de.getSoLuongCauHoi()) + " câu");
                createLabel(lbSoTinChi, "Số tín chỉ: " + Integer.toString(mon.getSoTinChi()));

                pnNoiDung.add(lbMon);
                pnNoiDung.add(lbThoiGian);
                pnNoiDung.add(lbSoLuongCau);
                pnNoiDung.add(lbSoTinChi);
                pnDeThi.add(pnNoiDung, BorderLayout.WEST);

                JPanel pnBtn = new JPanel();
                pnBtn.setLayout(new FlowLayout(1, 0, (int) ((int) heightFrame * 0.105)));
                MyButton btThi = new MyButton(nameBtn.toUpperCase(), MyButton.SUBMIT);
                btThi.setForeground(Color.white);
                btThi.setFont(new Font("Segoe UI", Font.BOLD, 14));
                btThi.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.08), (int) ((int) heightFrame * 0.05)));
                btThi.setOpaque(true);
                if (de.getTrangThai() == 0 || checkTime(de) > 0) {
                    isEnabled(btThi);
                    btThi.setText("ĐÃ THI");
                } else {
                    btThi.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent arg0) {
                            btnThiMouseClick(de, tempUser, f);

                        }
                    });
                }

                pnBtn.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.1), (int) ((int) heightFrame * 0.3)));
                pnBtn.setBackground(new Color(232, 242, 252));
                pnBtn.add(btThi);
                pnDeThi.add(pnBtn, BorderLayout.CENTER);

                //Kết Thúc Nội Dung
                //Gạch Chân
                pnChan.setBackground(new Color(255, 127, 80));
                pnChan.setPreferredSize(new Dimension(0, 3));
                pnDeThi.add(pnChan, BorderLayout.SOUTH);
                pnTong.add(pnDeThi);
            }
        }

        }else{

        }

        UserFrame.pCenter.add(pnTieuDe);
        UserFrame.pCenter.add(pane);
        UserFrame.pCenter.repaint();
        UserFrame.pCenter.validate();
        return UserFrame.pCenter;
    };

    public void createLabel(JLabel lb, String text) {
        lb.setText(text);
        lb.setOpaque(true);
        lb.setHorizontalAlignment(SwingConstants.LEFT);
        lb.setFont(lb.getFont().deriveFont(14.0f));
        lb.setBackground(new Color(232, 242, 252));
        lb.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.25), (int) ((int) heightFrame * 0.05)));
    }

    public int[] doiGio(int time) {
        int[] times = new int[2];
        times[0] = time / 60;
        times[1] = time % 60;
        return times;
    }

    public int checkTime(DeThiDTO de) {
        String[] date = de.getNgayThi().split("-");
        String[] time = de.getGioThi().split(":");
        LocalDateTime nowTime = LocalDateTime.now();
        LocalDateTime deTime = LocalDateTime.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]),
                Integer.parseInt(time[0]), Integer.parseInt(time[1]), Integer.parseInt(time[2]));

        int[] times = doiGio(Integer.parseInt(time[1]) + de.getThoigianLamBai());
        times[0] += Integer.parseInt(time[0]);
        LocalDateTime ketthuc = LocalDateTime.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]),
                times[0], times[1], Integer.parseInt(time[2]));
        int diff = nowTime.compareTo(deTime);
        if (diff < 0) {
            return diff;
        }
        diff = nowTime.compareTo(ketthuc);
        if (diff > 0) {
            return diff;
        }
        return 0;
    }

    public void btnThiMouseClick(DeThiDTO de, SinhVienDTO tempUser, JFrame f) {
        int diff = checkTime(de);
        if (diff < 0) {
            new ErrorFrame("Chưa Đến Giờ Làm Bài");

        } else if (diff > 0) {
            new ErrorFrame("Hết Giờ Làm Bài");
        } else {
            BaiThiBUS busBT = new BaiThiBUS();
            BaiThiDTO dtoBT = busBT.getObject(LoginBUS.isUser.getMaUser(), de.getMaDe());
            if (dtoBT.getMaSinhVien().equals("")) {
                ThiFrame thiFrame = new ThiFrame();
                thiFrame.createJFrame(de, tempUser, f);
                f.setVisible(false);
            } else {
                new ErrorFrame("Bạn Đã Làm Bài Thi Này");
            }
        }
    }

    public void isEnabled(MyButton btn) {
        btn.setEnabled(false);
        btn.setBackground(Color.lightGray);
        btn.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {

            }

            @Override
            public void mousePressed(MouseEvent arg0) {

            }

            @Override
            public void mouseReleased(MouseEvent arg0) {

            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                btn.setBackground(Color.lightGray);
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                btn.setBackground(Color.lightGray);
            }
        });
    }

    Function<Object, Object> thi = (fLichThi) -> {
        UserFrame.pCenter.removeAll();
        UserFrame.pCenter.repaint();
        UserFrame.pCenter.validate();
        JFrame fLich = (JFrame) fLichThi;
        //Giao Dien
        JPanel pnTong = new JPanel();
        pnTong.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.8), (int) ((int) heightFrame * 0.98)));
        pnTong.setLayout(null);
        JPanel pnBao = new JPanel();
        pnBao.setLayout(null);

        pnBao.setBounds(70, 40, (int) ((int) widthFrame * 0.7), (int) ((int) heightFrame * 0.85));
        ImageIcon icTren = new ImageIcon("src/img/5af9e91a0d463e0f82c62e8510167042.gif");
        ImageIcon icDuoi = new ImageIcon("src/img/66afeb2dd3b96d92cb05580f52db7597 (1).gif");
        ImageIcon icHong1 = new ImageIcon("src/img/hong1.gif");
        ImageIcon icHong2 = new ImageIcon("src/img/hong2.gif");
        ImageIcon icTren2 = new ImageIcon("src/img/an.gif");
        JLabel lbTren = new JLabel(icTren, JLabel.CENTER);
        JLabel lbTren2 = new JLabel(icTren2, JLabel.CENTER);
        MyBorder bdBao = new MyBorder(new Color(255, 254, 129), 10, 50, 0);
        JLabel lbDuoi = new JLabel(icDuoi);
        JLabel lbHong1 = new JLabel(icHong2);
        JLabel lbHong2 = new JLabel(icHong1);
        MyBorder bd = new MyBorder(new Color(0, 0, 0), 2, 200, 30);
        MyBorder bd2 = new MyBorder(new Color(0, 0, 0), 2, 50, 30);
        lbTren.setBounds(560, 100, 300, 240);
        lbTren.setOpaque(true);
        lbTren.setBackground(new Color(255, 254, 129));
        lbTren.setBorder(bd);

        lbTren2.setBounds(110, 130, 300, 240);
        lbTren2.setOpaque(true);
        lbTren2.setBackground(new Color(221, 248, 255));
        lbTren2.setBorder(bd2);

        lbDuoi.setBounds(60, 380, 240, 240);
        lbDuoi.setOpaque(true);
        lbDuoi.setBackground(Color.white);

        lbHong1.setBounds(460, 350, 240, 240);
        lbHong1.setOpaque(true);
        lbHong1.setBackground(Color.white);

        lbHong2.setBounds(700, 350, 240, 240);
        lbHong2.setOpaque(true);
        lbHong2.setBackground(Color.white);

        pnBao.add(lbTren);
        pnBao.add(lbDuoi);
        pnBao.add(lbHong1);
        pnBao.add(lbHong2);
        pnBao.add(lbTren2);
        pnBao.setBorder(bdBao);
        pnBao.setBackground(Color.white);
        pnTong.add(pnBao);
        pnTong.setBackground(new Color(221, 248, 255));
        UserFrame.pCenter.add(pnTong);
        UserFrame.pCenter.setBackground(new Color(221, 248, 255));
        UserFrame.pCenter.repaint();
        UserFrame.pCenter.validate();
        //Het Frame
        //Xu ly
        KyThiBUS busKyThi = new KyThiBUS();
        SinhVienDTO tempUser = (SinhVienDTO) LoginBUS.isUser;
        KyThiDTO kythi = busKyThi.getKyThiHienTai();
        ArrayList<MonDTO> mons = new ArrayList<MonDTO>();
        MonSinhVienBUS busMSV = new MonSinhVienBUS();
        mons = busMSV.getMonList(tempUser.getMaUser());
        boolean check = false;
        for (MonDTO mon : mons) {
            DeThiBUS busDe = new DeThiBUS();
            DeThiDTO de = busDe.getDeMaMonKyThi(mon.getMaMon(), kythi.getMaKyThi());
            if (checkTime(de) == 0) {
                check = true;
                BaiThiBUS busBT = new BaiThiBUS();
                BaiThiDTO dtoBT = busBT.getObject(LoginBUS.isUser.getMaUser(), de.getMaDe());
                System.out.println(dtoBT.toString());
                if (dtoBT.getMaSinhVien().equals("")) {
                    ThiFrame fThi = new ThiFrame();
                    fThi.createJFrame(de, tempUser, fLich);
                    fLich.setVisible(false);
                } else {
                    new ErrorFrame("Hiện Tại Chưa Có Môn Nào Được Thi");

                }
            }
        }
        if (check == false) {
            new ErrorFrame("Hiện Tại Chưa Có Môn Nào Được Thi");
        }

        return UserFrame.pCenter;
    };
    Function<Object, Object> ketQua = isKQ -> {
        UserFrame.pCenter.removeAll();
        UserFrame.pCenter.repaint();
        UserFrame.pCenter.validate();
        SinhVienDTO tempUser = (SinhVienDTO) LoginBUS.isUser;
        // Lấy danh sách đề thi của sv
        ArrayList<BaiThiDTO> baiThis = new ArrayList<BaiThiDTO>();
        BaiThiBUS busBT = new BaiThiBUS();
//        KyThiDTO kythi = ContainerDAO.daoKyThi.getKyThiHienTai();
        baiThis = busBT.getBaiThiList(tempUser.getMaUser());

        //==========
        JPanel pnTong = new JPanel();
        UserFrame.pCenter.setBackground(Color.white);
        JScrollPane pane = new JScrollPane(pnTong,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        MyBorder bdTong = new MyBorder(new Color(70, 130, 180), 5, 0, 0);

        //Tiêu Đề
        JPanel pnTieuDe = new JPanel();
        JLabel lbTieuDe = new JLabel();

        pnTieuDe.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.77), (int) ((int) heightFrame * 0.1)));
        pnTieuDe.setBackground(new Color(26, 157, 255));
        lbTieuDe.setText("KẾT QUẢ THI:");
        lbTieuDe.setFont(lbTieuDe.getFont().deriveFont(25.0f));
        lbTieuDe.setForeground(Color.white);
        lbTieuDe.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.77), (int) ((int) heightFrame * 0.1)));
        lbTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
        pnTieuDe.add(lbTieuDe);

        pane.setBorder(bdTong);

        pnTong.setLayout(new FlowLayout(0, 40, 30));
        pnTong.setBackground(Color.white);
        pane.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.784), (int) ((int) heightFrame * 0.84)));

        pane.getViewport().setBackground(new Color(232, 242, 252));
        int dong = 1;

        for (BaiThiDTO baiThi : baiThis) {
            DeThiBUS busDe = new DeThiBUS();
            MonBUS busMon = new MonBUS();

            DeThiDTO de = busDe.getObject(baiThi.getMaDe());
            String maMon = busDe.getMaMon(de.getMaDe());

            MonDTO mon = new MonDTO();
            mon = busMon.getObject(maMon);
            JPanel pnBaiThi = new JPanel();
            JPanel pnChan = new JPanel();
            JPanel pnNoiDung = new JPanel();
            String nameBtn = "Chi Tiết";

            //Panel Tổng
            pnBaiThi.setLayout(new BorderLayout());
            pnBaiThi.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.22), (int) ((int) heightFrame * 0.2)));
            pnBaiThi.setBackground(new Color(232, 242, 252));
            pnTong.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.77), (int) ((int) heightFrame * 0.2 + 30) * ((int) (dong + 2) / 3) + 30));
            dong++;
            //

            pnBaiThi.setLayout(new BorderLayout());
            //Tiêu Đề
            JLabel lbBaiThi = new JLabel(mon.getTenMon().toUpperCase());
            lbBaiThi.setFont(lbBaiThi.getFont().deriveFont(15.0f));
//                lbBaiThi.setForeground(Color.white);
            lbBaiThi.setBackground(new Color(160, 214, 102));
            lbBaiThi.setOpaque(true);
            lbBaiThi.setHorizontalAlignment(SwingConstants.CENTER);
            lbBaiThi.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.16), (int) ((int) heightFrame * 0.05)));
            pnBaiThi.add(lbBaiThi, BorderLayout.NORTH);
            //Kết Thúc Tiêu Đề

            //Nội Dung Panel
            pnNoiDung.setLayout(new FlowLayout(0, 20, 10));
            pnNoiDung.setPreferredSize(new Dimension(new Dimension((int) ((int) widthFrame * 0.16), 0)));
//            pnNoiDung.setBackground(new Color(232, 242, 252));
            pnNoiDung.setBackground(new Color(217, 238, 207));
            ImageIcon icDiem = new ImageIcon("src/img/score.png");
            ImageIcon icSoCauDung = new ImageIcon("src/img/correction.png");
            JLabel lbSoCauDung = new JLabel(icSoCauDung);
            JLabel lbDiem = new JLabel(icDiem);
            createLabel(lbSoCauDung, "   Kết Quả: " + baiThi.getSoCauDung() + "/" + de.getSoLuongCauHoi());
            lbSoCauDung.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.12), (int) ((int) heightFrame * 0.05)));
            lbSoCauDung.setBackground(new Color(217, 238, 207));
            lbSoCauDung.setForeground(new Color(255, 0, 0));
            createLabel(lbDiem, "      Điểm: " + baiThi.getDiem());
            lbSoCauDung.setFont(lbSoCauDung.getFont().deriveFont(15.0f));
            lbDiem.setFont(lbDiem.getFont().deriveFont(15.0f));
            lbDiem.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.12), (int) ((int) heightFrame * 0.05)));
            pnNoiDung.add(lbSoCauDung);
            pnNoiDung.add(lbDiem);
            lbDiem.setBackground(new Color(217, 238, 207));
            pnBaiThi.add(pnNoiDung, BorderLayout.WEST);

            JPanel pnBtn = new JPanel();
            pnBtn.setLayout(new FlowLayout(1, 0, (int) ((int) heightFrame * 0.05)));
            MyButton btThi = new MyButton(nameBtn.toUpperCase(), MyButton.SUBMIT);
            btThi.setForeground(Color.white);
            btThi.setFont(new Font("Segoe UI", Font.BOLD, 14));
            btThi.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.05), (int) ((int) heightFrame * 0.045)));
            btThi.setOpaque(true);

            btThi.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent arg0) {
                    chiTietBaiThi(de, baiThi);

                }
            });

            pnBtn.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.1), (int) ((int) heightFrame * 0.2)));
            pnBtn.setBackground(new Color(217, 238, 207));
            pnBtn.add(btThi);
            pnBaiThi.add(pnBtn, BorderLayout.CENTER);

            //Kết Thúc Nội Dung
            //Gạch Chân
            pnChan.setBackground(new Color(255, 97, 97));
            pnChan.setPreferredSize(new Dimension(0, 3));
            pnBaiThi.add(pnChan, BorderLayout.SOUTH);
            pnTong.add(pnBaiThi);

        }
        UserFrame.pCenter.add(pnTieuDe);
        UserFrame.pCenter.add(pane);
        UserFrame.pCenter.repaint();
        UserFrame.pCenter.validate();
        return UserFrame.pCenter;
    };

    public void chiTietBaiThi(DeThiDTO de, BaiThiDTO baiThi) {
        ChiTietKetQuaFrame cTKQ = new ChiTietKetQuaFrame();
        cTKQ.createJFrame(de, baiThi);
    }
    //Sinh Vien

    //Phan Quyen
    Function<Object, Object> phanQuyen = isKQ -> {

        UserFrame.pCenter.removeAll();
        UserFrame.pCenter.repaint();
        UserFrame.pCenter.validate();
        JPanel pnBao = new JPanel();
        pnBao.setLayout(new FlowLayout(1, 0, 10));
        pnBao.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.8), (int) ((int) heightFrame * 0.9)));

        JScrollPane pane = new JScrollPane();
        Vector header = new Vector();

        QuyenBUS busCV = new QuyenBUS();

        cvs = busCV.getList();
        cvs.remove(0);
        for (int i = 0; i < cvs.size(); i++) {
            if (cvs.get(i).getTrangThai() == 0) {
                cvs.remove(i);
            }
        }

        header.add("Mã Quyền");
        header.add("Tên Quyền");

        modelPQ = new DefaultTableModel(header, 0);
        tbChucVu = new MyTable(modelPQ);

        for (QuyenDTO cv : cvs) {
            Vector row = new Vector();
            row.add(cv.getMaChucVu());
            row.add(cv.getTenChucVu());
            modelPQ.addRow(row);
        }
        tbChucVu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                clickOnTable(tbChucVu.getSelectedRow());
            }
        });
        JPanel pnSearch = new JPanel();
        pnSearch.setLayout(new FlowLayout(0, 20, 0));
        pnSearch.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.3), (int) ((int) heightFrame * 0.04)));
//        pnSearch.setBackground(Color.white);

        JPanel tb = new JPanel();
        tb.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.75), (int) ((int) heightFrame * 0.05)));
        tb.setLayout(new FlowLayout(1, 0, 0));
        tb.setBackground(Color.white);
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
        MyButton btSearch = new MyButton("Tìm Kiếm", MyButton.ADD);
        btSearch.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.09), (int) ((int) heightFrame * 0.04)));
        btSearch.setFont(btSearch.getFont().deriveFont(15.0f)
        );
        btSearch.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                timKiem(txtSearch.getText());
            }
        });

        pnSearch.add(txtSearch);
        pnSearch.add(btSearch);
        pnSearch.setBackground(Color.white);
        tb.add(pnSearch);

        JPanel pnTacVu = new JPanel();
        pnTacVu.setLayout(new FlowLayout(1, 20, 20));
        pnTacVu.setPreferredSize(new Dimension((new Dimension((int) ((int) widthFrame * 0.09), (int) ((int) heightFrame * 0.25)))));
        MyButton btThem = new MyButton("Thêm", MyButton.ADD);
        btThem.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.08), (int) ((int) heightFrame * 0.05)));
        btThem.setFont(btThem.getFont().deriveFont(15.0f));
        btThem.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                ErrorFrame er = new ErrorFrame("Bạn Muốn Thêm Quyền Này");
                er.btnOK.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent arg0) {
                        btnThemQuyen();
                    }
                });
            }
        });

        MyButton btSua = new MyButton("Sửa", MyButton.ADD);
        btSua.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.08), (int) ((int) heightFrame * 0.05)));
        btSua.setFont(btThem.getFont().deriveFont(15.0f));

        btSua.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                ErrorFrame er = new ErrorFrame("Bạn Muốn Sửa Quyền Này");
                er.btnOK.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent arg0) {
                        btnSuaQuyen(tbChucVu.getSelectedRow());
                    }
                });

            }
        });
        MyButton btXoa = new MyButton("Xóa", MyButton.DELETE);
        btXoa.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.08), (int) ((int) heightFrame * 0.05)));
        btXoa.setFont(btThem.getFont().deriveFont(15.0f));
        btXoa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                ErrorFrame er = new ErrorFrame("Bạn Muốn Xóa Quyền Này");
                er.btnOK.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent arg0) {
                        btnXoaQuyen(tbChucVu.getSelectedRow());

                    }

                });
            }
        });

        pnTacVu.setBackground(Color.white);
        pnTacVu.add(btThem);
        pnTacVu.add(btSua);
        pnTacVu.add(btXoa);
//        tb.add(pnTacVu);
        pane.setViewportView(tbChucVu);
        pane.setPreferredSize(
                new Dimension((int) ((int) widthFrame * 0.45), (int) ((int) heightFrame
                        * 0.2)));
        pane.getViewport()
                .setBackground(new Color(255, 255, 255));
        String[] q1 = {"Sinh Viên", "Giảng Viên", "Nhân Viên", "Câu Hỏi", "Môn Học", "Bài Thi"};
        String[] q2 = {"Đề Thi", "Kỳ Thi", "Lớp", "Tài Khoản", "Khoa"};
        JPanel pnQuyen = new JPanel();
        pnQuyen.setLayout(
                new FlowLayout(1, 10, 0));
        pnQuyen.setPreferredSize(
                new Dimension((int) ((int) widthFrame * 0.75), (int) ((int) heightFrame * 0.65) - 70));
        MyBorder bd = new MyBorder(new Color(26, 157, 255), 2, 0, 0);
        ;
        JPanel pnQ1 = new JPanel();
        pnQ1.setPreferredSize(
                new Dimension((int) ((int) widthFrame * 0.25 - 10), (int) ((int) heightFrame * 0.65) - 70));
        JPanel pnQ2 = new JPanel();
        pnQ2.setPreferredSize(
                new Dimension((int) ((int) widthFrame * 0.25 - 10), (int) ((int) heightFrame * 0.65) - 70));
        JPanel pnQ3 = new JPanel();
        pnQ3.setPreferredSize(
                new Dimension((int) ((int) widthFrame * 0.25 - 10), (int) ((int) heightFrame
                        * 0.65) - 70));
        pnQ1.setBorder(bd);
        pnQ2.setBorder(bd);
        pnQ3.setBorder(bd);
        pnQuyen.add(pnQ1);
        pnQuyen.add(pnQ2);
        pnQuyen.add(pnQ3);
        pnQ1.setBackground(Color.white);
        pnQ2.setBackground(Color.white);
        pnQ3.setBackground(Color.white);
        pnQ1.setLayout(
                new FlowLayout(1, 0, 6));
        pnQ2.setLayout(
                new FlowLayout(1, 0, 6));
        String[] cbChucNang = {"Chỉ Xem", "Quản Lý"};
        for (int i = 0;
                i < 6; i++) {
            cbQuyen[i] = new MyCheckBox(q1[i]);
            cbQuyen[i].setPreferredSize(new Dimension((int) ((int) widthFrame * 0.1), (int) ((int) heightFrame * 0.08)));
            cbQuyen[i].setBackground(Color.white);

            cbbQuyen[i] = new MyComboBox(cbChucNang);
            cbbQuyen[i].setPreferredSize(new Dimension((int) ((int) widthFrame * 0.1), (int) ((int) heightFrame * 0.04)));
            cbbQuyen[i].setBackground(Color.white);
            cbbQuyen[i].setEnabled(false);
            eventCheckBox(cbbQuyen[i], cbQuyen[i]);
            pnQ1.add(cbQuyen[i]);
            pnQ1.add(cbbQuyen[i]);
        }
        for (int i = 6;
                i < 11; i++) {
            cbQuyen[i] = new MyCheckBox(q2[i - 6]);
            cbQuyen[i].setPreferredSize(new Dimension((int) ((int) widthFrame * 0.1), (int) ((int) heightFrame * 0.08)));
            cbQuyen[i].setBackground(Color.white);

            cbbQuyen[i] = new MyComboBox(cbChucNang);
            cbbQuyen[i].setPreferredSize(new Dimension((int) ((int) widthFrame * 0.1), (int) ((int) heightFrame * 0.04)));
            cbbQuyen[i].setBackground(Color.white);
            cbbQuyen[i].setEnabled(false);
            eventCheckBox(cbbQuyen[i], cbQuyen[i]);
            pnQ2.add(cbQuyen[i]);
            pnQ2.add(cbbQuyen[i]);
        }
        pnQ3.setLayout(
                new FlowLayout(1, 5, 30));
        JLabel lbMQ = new JLabel("  Mã Quyền:");
        JLabel lbTQ = new JLabel("  Tên Quyền:");
        lbMQ.setPreferredSize(
                new Dimension((int) ((int) widthFrame * 0.06), (int) ((int) heightFrame
                        * 0.04)));
        lbTQ.setPreferredSize(
                new Dimension((int) ((int) widthFrame * 0.06), (int) ((int) heightFrame * 0.04)));
        MyBorder bdMQ = new MyBorder(Color.black, 1, 0, 0);
        lbMaQuyen.setPreferredSize(
                new Dimension((int) ((int) widthFrame * 0.13), (int) ((int) heightFrame
                        * 0.04)));
        lbMaQuyen.setBorder(bdMQ);
        txTenQuyen.setPreferredSize(
                new Dimension((int) ((int) widthFrame * 0.13), (int) ((int) heightFrame
                        * 0.04)));
        pnQ3.add(lbMQ);
        pnQ3.add(lbMaQuyen);
        pnQ3.add(lbTQ);
        pnQ3.add(txTenQuyen);
        pnQ3.add(pnTacVu);
        JPanel tbd = new JPanel();
        tbd.setPreferredSize(
                new Dimension((int) ((int) widthFrame * 0.75), (int) ((int) heightFrame
                        * 0.05)));
        tbd.setLayout(
                new FlowLayout(2, 20, 0));
        ImageIcon img = new ImageIcon("src/img/excel.png");
        MyButton btImportExcel = new MyButton("Nhập Excel", 1);
        btImportExcel.setIcon(img);
        FileChooserGUI file = new FileChooserGUI();
        QuyenBUS quyenNgu = new QuyenBUS();
        btImportExcel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                quyenNgu.importExcel(file.getFileExcelNameOpen(new JPanel()));
            }
        });

        btImportExcel.setPreferredSize(
                new Dimension((int) ((int) widthFrame * 0.08), (int) ((int) heightFrame * 0.04)));
        MyButton btExportExcel = new MyButton("Xuất Excel", 1);
        btExportExcel.setIcon(img);
        btExportExcel.setPreferredSize(
                new Dimension((int) ((int) widthFrame * 0.08), (int) ((int) heightFrame
                        * 0.04)));
        btExportExcel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                quyenNgu.exportExcel(file.getFileExcelNameSave(new JPanel()));
            }
        });
        tbd.add(btImportExcel);
        tbd.add(btExportExcel);
        pnBao.add(tb);
        pnBao.add(pane);
        pnBao.add(pnQuyen);
        pnBao.add(tbd);
        UserFrame.pCenter.add(pnBao);
        UserFrame.pCenter.repaint();
        UserFrame.pCenter.validate();
        pnQuyen.setBackground(Color.white);
        pnBao.setBackground(Color.white);
        tb.setBackground(Color.white);
        tbd.setBackground(Color.white);
        UserFrame.pCenter.setBackground(Color.white);
        return UserFrame.pCenter;

    };

    public void timKiem(String a) {
        if (a.equals("")) {
            tbChucVu.setModel(modelPQ);
        } else {
            DefaultTableModel model1 = new DefaultTableModel();
            Vector hd = new Vector();
            hd.add("Mã Quyền");
            hd.add("Tên Quyền");
            model1 = new DefaultTableModel(hd, 0);
            for (QuyenDTO q : cvs) {
                if (q.toString().toLowerCase().contains(a.toLowerCase())) {
                    Vector b = new Vector();
                    b.add(q.getMaChucVu());
                    b.add(q.getTenChucVu());
                    model1.addRow(b);
                }
            }
            tbChucVu.setModel(model1);
        }
    }

    public void clickOnTable(int i) {
        ArrayList<Integer> ctq = new ArrayList<>();

        ChiTietQuyenBUS busChiTiet = new ChiTietQuyenBUS();

        if (i >= 0) {
            txTenQuyen.setText(cvs.get(i).getTenChucVu());
            lbMaQuyen.setText(cvs.get(i).getMaChucVu());
            ctq = busChiTiet.getListTrangThai(cvs.get(i).getMaChucVu());
            for (int j = 0; j < 22; j += 2) {
                if ((ctq.get(j) + ctq.get(j + 1)) >= 1) {
                    cbQuyen[j / 2].setSelected(true);
                    cbbQuyen[j / 2].setEnabled(true);
                    if (ctq.get(j) == 1) {
                        cbbQuyen[j / 2].setSelectedIndex(0);
                    } else {
                        cbbQuyen[j / 2].setSelectedIndex(1);
                    }
                } else {
                    cbQuyen[j / 2].setSelected(false);
                    cbbQuyen[j / 2].setEnabled(false);
                    cbbQuyen[j / 2].setSelectedIndex(0);
                }
            }
        }
    }

    public LocalDateTime doiNgay(String ngay, String gio) {
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
        if (dau < 0 || cuoi > 0) {
            return false;
        }
        return true;
    }

    public void btnXoaQuyen(int i) {
        QuyenBUS busQ = new QuyenBUS();
        if (i >= 0) {
            if (busQ.xoa(cvs.get(i).getMaChucVu())) {
                cvs.remove(i);
                modelPQ.removeRow(i);
                new ErrorFrame("Xóa Thành Công");
            } else {
                new ErrorFrame("Xóa Thất Bại");
            }
        } else {
            new ErrorFrame("Vui Lòng Chọn Quyền Muốn Xóa");
        }
    }

    public void btnSuaQuyen(int i) {
        boolean check = false;

        if (i >= 0) {
            if (txTenQuyen.getText().equals("")) {
                new ErrorFrame("Tên Quyền Không Được Trống");
            } else {
                QuyenDTO quyen = new QuyenDTO(lbMaQuyen.getText(), txTenQuyen.getText(), 1);
                QuyenBUS busQ = new QuyenBUS();
                if (busQ.sua(quyen, quyen.getTenChucVu())) {
                    new ErrorFrame("Sửa Thành Công");
                    modelPQ.setValueAt(txTenQuyen.getText(), i, 1);
                } else {
                    new ErrorFrame("Sửa Thất Bại");
                }
                ChiTietQuyenBUS busCT = new ChiTietQuyenBUS();
                ArrayList<ChiTietQuyenDTO> ctqs = new ArrayList<>();
                ArrayList<Integer> tt = new ArrayList<>();
                ctqs = busCT.getListCT(cvs.get(i).getMaChucVu());
                for (int j = 0; j < 11; j++) {
                    if (cbQuyen[j].isSelected()) {
                        if (cbbQuyen[j].getSelectedIndex() == 0) {
                            tt.add(1);
                            tt.add(0);
                        } else {
                            tt.add(0);
                            tt.add(1);
                        }
                    } else {
                        tt.add(0);
                        tt.add(0);
                    }
                }
                for (int j = 0; j < 22; j++) {
                    ctqs.get(j).setTrangThai(tt.get(j));
                    check = busCT.sua(ctqs.get(j), ctqs.get(j).getMaPhanQuyen());
                }

            }
        } else {
            new ErrorFrame("Vui Lòng Chọn Quyền Muốn Sửa");
        }
    }

    public void btnThemQuyen() {
        boolean check = false;
        QuyenDTO quyenMoi = new QuyenDTO();
        if (txTenQuyen.getText().equals("")) {
            new ErrorFrame("Tên Quyền Không Được Trống");
        } else {
            ArrayList<Integer> tt = new ArrayList<>();
            for (int j = 0; j < 11; j++) {
                if (cbQuyen[j].isSelected()) {
                    if (cbbQuyen[j].getSelectedIndex() == 0) {
                        tt.add(1);
                        tt.add(0);
                    } else {
                        tt.add(0);
                        tt.add(1);
                    }
                } else {
                    tt.add(0);
                    tt.add(0);
                }
            }
            QuyenBUS busQuyen = new QuyenBUS();

            quyenMoi.setTenChucVu(txTenQuyen.getText());
            check = busQuyen.them(quyenMoi);
            Vector a = new Vector();
            a.add(quyenMoi.getMaChucVu());
            a.add(quyenMoi.getTenChucVu());
            modelPQ.addRow(a);
            cvs.add(quyenMoi);
            for (int j = 0; j < 22; j++) {
                String maQuyen = "Q";
                if (j < 9) {
                    maQuyen = maQuyen + "0" + Integer.toString(j + 1);
                } else {
                    maQuyen += Integer.toString(j + 1);
                }
                ChiTietQuyenDTO ct = new ChiTietQuyenDTO();
                ct.setMaChucVu(quyenMoi.getMaChucVu());
                ct.setMaQuyen(maQuyen);
                ct.setTrangThai(tt.get(j));
                ChiTietQuyenBUS busCT = new ChiTietQuyenBUS();
                check = busCT.them(ct);
            }
        }
        if (check) {
            new ErrorFrame("Thêm Quyền Thành Công");

        } else {
            new ErrorFrame("Thêm Quyền Thất Bại");

        }
    }
    public void eventCheckBox(MyComboBox cbb, MyCheckBox cb) {
        cb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                if (cb.isSelected()) {
                    cbb.setEnabled(true);
                } else {
                    cbb.setEnabled(false);
                }
            }
        });

    }

    Function<Object, Object> dangXuat = frame -> {
        if (ErrorFrame.check) {
            JFrame new_frame = (JFrame) frame;
            new_frame.setVisible(false);
            LogInFrame s = new LogInFrame();
            s.createLoginFrame();
            s.setVisible(true);
        }
        return pnPaint;
    };

    Function<Object, Object> doiMatKhau = frame -> {
        new RePasswordFrame();
        return true;
    };

    public AutoGUI() {
        map_gui.put("Q02", quanLy);
        map_gui.put("Q04", quanLy);
        map_gui.put("Q06", quanLy);
        map_gui.put("Q08", quanLy);
        map_gui.put("Q10", quanLy);
        map_gui.put("Q12", quanLy);
        map_gui.put("Q14", quanLy);
        map_gui.put("Q16", quanLy);
        map_gui.put("Q18", quanLy);
        map_gui.put("Q20", quanLy);
        map_gui.put("Q22", quanLy);

        map_gui.put("Q01", xem);
        map_gui.put("Q03", xem);
        map_gui.put("Q05", xem);
        map_gui.put("Q07", xem);
        map_gui.put("Q09", xem);
        map_gui.put("Q11", xem);
        map_gui.put("Q13", xem);
        map_gui.put("Q15", xem);
        map_gui.put("Q17", xem);
        map_gui.put("Q19", xem);
        map_gui.put("Q21", xem);
        map_gui.put("Q23", xem);

        map_gui.put("Q24", phanQuyen);
        map_gui.put("Q25", xemLichThi);
        map_gui.put("Q26", thi);
        map_gui.put("Q27", ketQua);

        map_gui.put("doiMatKhau", doiMatKhau);
        map_gui.put("dangXuat", dangXuat);
    }
}
