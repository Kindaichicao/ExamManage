/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_Exam;

import BUS_EXAM.LoginBUS;
import BUS_EXAM.UserBUS;
import DTO_EXAM.UserDTO;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author "ThanhCute"
 */
public class UserFrame {

    private JFrame f = new JFrame();
    private JPanel pNorth = new JPanel();
    private JPanel pWest = new JPanel();
    static JPanel pCenter = new JPanel();
    private JPanel pEast = new JPanel();
    private JPanel pSouth = new JPanel();
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int widthFrame = (int) ((int) screenSize.getWidth() * 0.9);
    private int heightFrame = (int) ((int) screenSize.getHeight() * 0.9);
    private JTextField[] pnText;

    private ArrayList<MyButton> btnMenus = new ArrayList<MyButton>();
    public static ArrayList<String> titleProfile = new ArrayList<String>();

    public void createJFrame(ArrayList<String> testProfile, ArrayList<String> menu, ArrayList<String> quyen) {
        titleProfile.add("Mã Số: ");
        titleProfile.add("Họ Tên: ");
        titleProfile.add("Ngày Sinh: ");
        titleProfile.add("Giới Tính: ");
        titleProfile.add("Khoa: ");
        titleProfile.add("Lớp: ");

        f.setUndecorated(true);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new BorderLayout());

        f.setPreferredSize(new Dimension(widthFrame, heightFrame));
        f.setBounds(0, 0, widthFrame, heightFrame);
        f.setLocation((int) screenSize.getWidth() / 2 - (int) f.getPreferredSize().getWidth() / 2,
                (int) screenSize.getHeight() / 3 - (int) f.getPreferredSize().getHeight() / 3);

        createNorth();
        createWest(testProfile, menu, quyen);
        createCenter(quyen.get(0));
        createEast();
        createSouth();

        f.setVisible(true);
    }

    public void createNorth() {
        pNorth.setLayout(new FlowLayout(2, 5, 3));
        pNorth.setPreferredSize(new Dimension(0, 30));
        pNorth.setBackground(new Color(255, 127, 80));

        MyButton btnMinimize = new MyButton("-");
        MyButton btnDangXuat = new MyButton("Đăng Xuất");
        MyButton btnThoat = new MyButton("X", MyButton.EXIT);

        btnMinimize.setFont(btnMinimize.getFont().deriveFont(25.0f));

        btnDangXuat.setPreferredSize(new Dimension(80, 24));
        btnThoat.setPreferredSize(new Dimension(26, 24));
        btnMinimize.setPreferredSize(new Dimension(26, 24));

        btnMinimize.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                f.setState(Frame.ICONIFIED);
            }
        });
        btnDangXuat.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                ErrorFrame hoi = new ErrorFrame("Bạn Có Muốn Đăng Xuất Không", f, "dangXuat");

            }
        });

        btnThoat.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                ErrorFrame hoi = new ErrorFrame("Bạn Có Chắc Chắn Muốn Thoát Không");
                hoi.btnOK.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        System.exit(0);
                    }
                });

            }

        });

        pNorth.add(btnMinimize);
        pNorth.add(btnDangXuat);
        pNorth.add(btnThoat);

//        createCenter();
        f.add(pNorth, BorderLayout.NORTH);
    }

    public void createWest(ArrayList<String> testProfile, ArrayList<String> menu, ArrayList<String> quyen) {
        AutoMouseClick autoMouseClick = new AutoMouseClick();
        pWest.setLayout(new BorderLayout());
        JPanel pRightWest = new JPanel();
        JPanel pLeftWest = new JPanel();
        JPanel pProfile = new JPanel();
        JPanel pMenu = new JPanel();
        MyBorder bdProfile = new MyBorder();
        MyBorder bdMenu = new MyBorder(new Color(158, 222, 115), 1, 10, 0);

        pLeftWest.setBackground(new Color(255, 127, 80));
        pLeftWest.setPreferredSize(new Dimension(3, 0));
        pLeftWest.setLayout(new BorderLayout());

        pRightWest.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.21), 0));
        pRightWest.setBackground(new Color(158, 222, 115));
        pProfile.setBorder(bdProfile);
        pProfile.setBackground(new Color(255, 255, 255));
        pProfile.setLayout(new FlowLayout(0, 10, 5));

        if (testProfile.size() != 0) {

            pProfile.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.2), (int) ((int) heightFrame * 0.25)));

            for (int i = 0; i < testProfile.size(); i++) {
                JLabel lbProfile = new JLabel(titleProfile.get(i) + testProfile.get(i));
                lbProfile.setOpaque(true);
                lbProfile.setFont(lbProfile.getFont().deriveFont(16.0f));
                pProfile.add(lbProfile);
                lbProfile.setBackground(Color.WHITE);
            }
            pRightWest.add(pProfile, BorderLayout.NORTH);
            pMenu.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.2), (int) ((int) heightFrame * 0.69)));
            pMenu.setBorder(bdProfile);

        } else {
//            pProfile.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.2), (int) ((int) heightFrame * 0.1)));
            pMenu.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.2), (int) ((int) heightFrame * 0.94)));
        }

        pMenu.setBackground(new Color(255, 239, 207));
        pMenu.setLayout(new FlowLayout(1, 100, 5));

        for (int i = 0; i < menu.size() - 1; i++) {
            MyButton btn = new MyButton(menu.get(i), MyButton.MENU);
            btn.setFont(btn.getFont().deriveFont(16.0f));
            btn.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.18), (int) ((int) heightFrame * 0.06)));
            btn.setBorder(bdMenu);
            btnMenus.add(btn);
            UserBUS busUser = new UserBUS();
            UserDTO dtoUser = busUser.getObject(LoginBUS.isUser.getMaUser());
            if (dtoUser.getMaChucVu().equals("CV00")) {
                autoMouseClick.autoMouse(btnMenus.get(i), quyen.get(i), btnMenus, f);
            } else {
                autoMouseClick.autoMouse(btnMenus.get(i), quyen.get(i), btnMenus, quyen.get(i));
            }

            pMenu.add(btn);
        }

        MyButton btn = new MyButton(menu.get(menu.size() - 1), MyButton.MENU);
        btn.setFont(btn.getFont().deriveFont(16.0f));
        btn.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.18), (int) ((int) heightFrame * 0.06)));
        btn.setBorder(bdMenu);
        btnMenus.add(btn);
        //Giao dien nen
//        btnMenus.get(0).setBackground(new Color(0, 205, 102));
        //het
        autoMouseClick.autoMouse(btnMenus.get(menu.size() - 1), "doiMatKhau", btnMenus, f);
        pMenu.add(btn);
        pRightWest.add(pMenu, BorderLayout.SOUTH);

        pWest.add(pLeftWest, BorderLayout.WEST);
        pWest.add(pRightWest, BorderLayout.CENTER);
        f.add(pWest, BorderLayout.WEST);
    }
//Riêng

    public void createCenter(String quyen) {//Tự vẽ nha các cậu
//        AutoGUI.map_gui.get(quyen).apply(f);
        pCenter.removeAll();
        pCenter.validate();
        pCenter.repaint();
        pCenter.setBackground(Color.white);
        JPanel pnBao = new JPanel();
        pnBao.setBackground(Color.white);
        pnBao.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.8), (int) ((int) heightFrame * 0.8)));
        pnBao.setLayout(new FlowLayout(1, 0, 50));
        pnBao.setBackground(Color.white);

        JLabel lbTitle = new JLabel("HỆ THỐNG QUẢN LÝ THI TRẮC NGHIỆM");
        lbTitle.setFont(new Font("Arial", Font.BOLD, 45));
        lbTitle.setForeground(Color.blue);

        JLabel lbImg1 = new JLabel();
        ImageIcon icon1 = new ImageIcon("src/img/cute2.gif");
        lbImg1.setIcon(icon1);

        JLabel lbImg2 = new JLabel();
        //ImageIcon icon2 = new ImageIcon("src/img/cute2.gif");
        lbImg2.setIcon(icon1);

        pnBao.add(lbTitle);
        pnBao.add(lbImg1);
        pnBao.add(lbImg2);
        pCenter.add(pnBao);
        pCenter.validate();
        pCenter.repaint();
        f.add(pCenter, BorderLayout.CENTER);
    }
//Kết Thúc Riêng

    public void createEast() {
        pEast.setBackground(new Color(255, 127, 80));
        pEast.setPreferredSize(new Dimension(3, 0));

        f.add(pEast, BorderLayout.EAST);
    }

    public void createSouth() {
        pSouth.setBackground(new Color(255, 127, 80));
        pSouth.setPreferredSize(new Dimension(0, 3));

        f.add(pSouth, BorderLayout.SOUTH);
    }

    //Quân
    public void createADFrame(ArrayList<String> testProfile, ArrayList<String> menu, ArrayList<String> quyen) {
        titleProfile.clear();
        titleProfile.add("Mã Số: ");
        titleProfile.add("Họ Tên: ");
        titleProfile.add("Ngày Sinh: ");
        titleProfile.add("Giới Tính: ");

        f.setUndecorated(true);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new BorderLayout());

        f.setPreferredSize(new Dimension(widthFrame, heightFrame));
        f.setBounds(0, 0, widthFrame, heightFrame);
        f.setLocation((int) screenSize.getWidth() / 2 - (int) f.getPreferredSize().getWidth() / 2,
                (int) screenSize.getHeight() / 3 - (int) f.getPreferredSize().getHeight() / 3);

        createNorth();
        createWestAD(testProfile, menu, quyen);
        createCenter(quyen.get(0));
        createEast();
        createSouth();

        f.setVisible(true);
    }

    public void createWestAD(ArrayList<String> testProfile, ArrayList<String> menu, ArrayList<String> quyen) {
        AutoMouseClick autoMouseClick = new AutoMouseClick();
        pWest.setLayout(new BorderLayout());
        JPanel pRightWest = new JPanel();
        JPanel pLeftWest = new JPanel();
        JPanel pProfile = new JPanel();
        JPanel pMenu = new JPanel();
        MyBorder bdProfile = new MyBorder();
        MyBorder bdMenu = new MyBorder(new Color(158, 222, 115), 1, 10, 0);

        pLeftWest.setBackground(new Color(255, 127, 80));
        pLeftWest.setPreferredSize(new Dimension(3, 0));
        pLeftWest.setLayout(new BorderLayout());

        pRightWest.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.21), 0));
        pRightWest.setBackground(new Color(158, 222, 115));
        pProfile.setBorder(bdProfile);
        pProfile.setBackground(new Color(255, 255, 255));
        pProfile.setLayout(new FlowLayout(0, 10, 5));

        if (testProfile.size() != 0) {

            pProfile.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.2), (int) ((int) heightFrame * 0.2)));

            for (int i = 0; i < testProfile.size() - 1; i++) {
                JLabel lbProfile = new JLabel(titleProfile.get(i) + testProfile.get(i));
                lbProfile.setOpaque(true);
                lbProfile.setFont(lbProfile.getFont().deriveFont(16.0f));
                pProfile.add(lbProfile);
                lbProfile.setBackground(Color.WHITE);
            }
            pRightWest.add(pProfile, BorderLayout.NORTH);
            pMenu.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.2), (int) ((int) heightFrame * 0.84)));
            pMenu.setBorder(bdProfile);

        } else {
//            pProfile.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.2), (int) ((int) heightFrame * 0.1)));
            pMenu.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.2), (int) ((int) heightFrame * 0.94)));
        }

        pMenu.setBackground(new Color(255, 239, 207));
        pMenu.setLayout(new FlowLayout(1, 100, 5));

        JPanel pnList = new JPanel();
        pnList.setBackground(new Color(255, 239, 207));
        pnList.setLayout(new FlowLayout(1, 100, 5));
        pnList.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.19), (int) ((int) heightFrame * 0.68)));

        for (int i = 0; i < menu.size() - 1; i++) {
            MyButton btn = new MyButton(menu.get(i), MyButton.MENU);
            btn.setFont(btn.getFont().deriveFont(16.0f));
            btn.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.18), (int) ((int) heightFrame * 0.06)));
            btn.setBorder(bdMenu);
            btnMenus.add(btn);
            UserBUS busUser = new UserBUS();
            UserDTO dtoUser = busUser.getObject(LoginBUS.isUser.getMaUser());
            if (dtoUser.getMaChucVu().equals("CV00")) {
                autoMouseClick.autoMouse(btnMenus.get(i), quyen.get(i), btnMenus, f);
            } else {
                autoMouseClick.autoMouse(btnMenus.get(i), quyen.get(i), btnMenus, quyen.get(i));
            }

            pnList.add(btn);
        }

        MyButton btn = new MyButton(menu.get(menu.size() - 1), MyButton.MENU);
        btn.setFont(btn.getFont().deriveFont(16.0f));
        btn.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.18), (int) ((int) heightFrame * 0.06)));
        btn.setBorder(bdMenu);
        btnMenus.add(btn);
        pnList.add(btn);

        autoMouseClick.autoMouse(btnMenus.get(menu.size() - 1), "doiMatKhau", btnMenus, f);
        pMenu.setLayout(new FlowLayout(1, 0, 0));
        pnList.setLayout(new FlowLayout(0, 0, 5));
        pnList.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.2), (int) ((int) heightFrame * 0.068 * menu.size())));
        JScrollPane pane = new JScrollPane(pnList);
        pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        pane.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.195), (int) ((int) heightFrame * 0.72)));
        pMenu.add(pane);
        pRightWest.add(pMenu, BorderLayout.SOUTH);

        pWest.add(pLeftWest, BorderLayout.WEST);
        pWest.add(pRightWest, BorderLayout.CENTER);
        f.add(pWest, BorderLayout.WEST);
    }
}
