/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_Exam;

import BUS_EXAM.*;
import DAO_EXAM.*;
import DTO_EXAM.BaiThiDTO;
import DTO_EXAM.CauHoiDTO;
import DTO_EXAM.DeCauHoiDTO;
import DTO_EXAM.DeThiDTO;
import DTO_EXAM.GiangVienMonDTO;
import DTO_EXAM.MonDTO;
import GUI_Exam.MyButton;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;
import javax.swing.event.*;

/**
 *
 * @author AQ
 */
public class DeThiFrame {

    JFrame f = new JFrame();
    JPanel pNorth = new JPanel();
    JPanel pWest = new JPanel();
    JPanel pCenter = new JPanel();
    JPanel pEast = new JPanel();
    JPanel pSouth = new JPanel();
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int widthFrame = (int) ((int) screenSize.getWidth() * 0.5);
    int heightFrame = (int) ((int) screenSize.getHeight() * 0.9);
    String dapAnDungs = "";

    public void createJFrame(DeThiDTO de) {

        f.setUndecorated(true);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new BorderLayout());

        f.setPreferredSize(new Dimension(widthFrame, heightFrame));
        f.setBounds(0, 0, widthFrame, heightFrame);
        f.setLocation((int) screenSize.getWidth() / 2 - (int) f.getPreferredSize().getWidth() / 2,
                (int) screenSize.getHeight() / 3 - (int) f.getPreferredSize().getHeight() / 3);
        createNorth();
        createWest();
        createCenter(de);
        createEast();
        createSouth();

        f.setVisible(true);
    }

    public void createNorth() {
        pNorth.setLayout(new FlowLayout(0, 5, 3));
        pNorth.setPreferredSize(new Dimension(0, 30));
        pNorth.setBackground(new Color(26, 157, 255));

        MyButton btnMinimize = new MyButton("-");
        MyButton btnThoat = new MyButton("X", MyButton.EXIT);
        ImageIcon icKQ = new ImageIcon("src/img/icons8-test-passed-16.png");
        JLabel lbTitle = new JLabel(icKQ, JLabel.LEFT);
        lbTitle.setPreferredSize(new Dimension((int) ((int) widthFrame * 1) - 70, 24));
        btnMinimize.setFont(btnMinimize.getFont().deriveFont(25.0f));
        lbTitle.setText("ĐỀ THI");
        lbTitle.setForeground(Color.white);
        lbTitle.setIconTextGap(10);
        lbTitle.setFont(lbTitle.getFont().deriveFont(16.0f));
        btnThoat.setPreferredSize(new Dimension(26, 24));
        btnMinimize.setPreferredSize(new Dimension(26, 24));

        btnMinimize.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                f.setState(Frame.ICONIFIED);
            }
        });

        btnThoat.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                f.setVisible(false);
            }

        });
        pNorth.add(lbTitle);
        pNorth.add(btnMinimize);
        pNorth.add(btnThoat);

        f.add(pNorth, BorderLayout.NORTH);
    }

    public void createWest() {
        pWest.setLayout(new BorderLayout());
        JPanel pRightWest = new JPanel();
        JPanel pLeftWest = new JPanel();

        pWest.setBackground(new Color(26, 157, 255));
        pWest.setPreferredSize(new Dimension(3, 0));

        f.add(pWest, BorderLayout.WEST);
    }

    public void createCenter(DeThiDTO de) {
        JPanel pnTong = new JPanel();
        JPanel pnDe = new JPanel();
        JScrollPane pane = new JScrollPane(pnDe,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        pnTong.setLayout(new FlowLayout(1, 10, 0));
        pnTong.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.99), (int) ((int) heightFrame * 0.99)));
        pnTong.setBackground(Color.white);
        DeThiBUS busDe = new DeThiBUS();
        MonBUS busMon = new MonBUS();
        KyThiBUS busKyThi = new KyThiBUS();
        JPanel pnTitle = new JPanel();
        pnTitle.setLayout(new FlowLayout(0, 0, 5));
        pnTitle.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.9), (int) ((int) heightFrame * 0.11)));
        MonDTO mon = busMon.getObject(de.getMaMon());
        String tenKyThi = "KỲ THI " + busKyThi.getTenKyThi(de.getMaKyThi());
        JLabel lbKt = new JLabel();

        lbKt.setText(tenKyThi.toUpperCase());
        lbKt.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.9), (int) ((int) heightFrame * 0.02)));
        lbKt.setHorizontalAlignment(SwingConstants.CENTER);
        lbKt.setFont(lbKt.getFont().deriveFont(14.0f));

        JLabel lbMon = new JLabel();
        lbMon.setText("Môn: " + mon.getTenMon() + "   /   Số tín chỉ: " + Integer.toString(mon.getSoTinChi()));
        lbMon.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.9), (int) ((int) heightFrame * 0.02)));
        lbMon.setHorizontalAlignment(SwingConstants.CENTER);
        lbMon.setFont(lbMon.getFont().deriveFont(14.0f));
        String ngay = BaseDAO.toDate((de.getNgayThi()));

        JLabel lbTC = new JLabel("Ngày Thi : " + ngay + " | " + de.getGioThi() + "   /   Số Lương Câu Hỏi:"
                + de.getSoLuongCauHoi());
        lbTC.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.9), (int) ((int) heightFrame * 0.02)));
        lbTC.setFont(lbTC.getFont().deriveFont(14.0f));
        lbTC.setHorizontalAlignment(SwingConstants.CENTER);

        pnDe.setLayout(new FlowLayout(1, 0, 0));
        pnDe.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.9), (int) ((int) heightFrame * 0.16) * de.getSoLuongCauHoi()));
        CauHoiBUS ch = new CauHoiBUS();
        ArrayList<CauHoiDTO> cauHois;
        cauHois = ch.getCauHoiList(de.getMaDe());

        for (int i = 0; i < de.getSoLuongCauHoi(); i++) {
            JPanel pnND = new JPanel();
            pnND.setBackground(Color.white);
            pnND.setLayout(new FlowLayout(0, 20, 5));
            pnND.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.9), (int) ((int) heightFrame * 0.16)));
            JTextArea txaND = new JTextArea("Câu " + (i + 1) + ": " + cauHois.get(i).getNoiDung());
            txaND.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.83), (int) ((int) heightFrame * 0.06)));
            txaND.setFont(txaND.getFont().deriveFont(14.0f));
            txaND.setFont(new Font("Segoe UI", Font.BOLD, 12));
            txaND.setBackground(new Color(255, 255, 255));
            txaND.setEditable(false);
            txaND.setLineWrap(true);
            txaND.setWrapStyleWord(true);

            JTextArea txaA = new JTextArea("A : " + cauHois.get(i).getDapanA());
            txaA.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.41), (int) ((int) heightFrame * 0.04)));
            txaA.setFont(txaA.getFont().deriveFont(12.0f));
            txaA.setBackground(new Color(255, 255, 255));
            txaA.setEditable(false);
            txaA.setLineWrap(true);
            txaA.setWrapStyleWord(true);

            JTextArea txaB = new JTextArea("B : " + cauHois.get(i).getDapanB());
            txaB.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.41), (int) ((int) heightFrame * 0.04)));
            txaB.setFont(txaB.getFont().deriveFont(12.0f));
            txaB.setBackground(new Color(255, 255, 255));
            txaB.setEditable(false);
            txaB.setLineWrap(true);
            txaB.setWrapStyleWord(true);

            JTextArea txaC = new JTextArea("C : " + cauHois.get(i).getDapanC());
            txaC.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.41), (int) ((int) heightFrame * 0.04)));
            txaC.setFont(txaC.getFont().deriveFont(12.0f));
            txaC.setBackground(new Color(255, 255, 255));
            txaC.setEditable(false);
            txaC.setLineWrap(true);
            txaC.setWrapStyleWord(true);

            JTextArea txaD = new JTextArea("D : " + cauHois.get(i).getDapanD());
            txaD.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.41), (int) ((int) heightFrame * 0.04)));
            txaD.setFont(txaD.getFont().deriveFont(12.0f));
            txaD.setBackground(new Color(255, 255, 255));
            txaD.setEditable(false);
            txaD.setLineWrap(true);
            txaD.setWrapStyleWord(true);

            if (cauHois.get(i).getDapanDung().equals("A")) {
                txaA.setForeground(Color.red);
            } else if (cauHois.get(i).getDapanDung().equals("B")) {
                txaB.setForeground(Color.red);
            } else if (cauHois.get(i).getDapanDung().equals("C")) {
                txaC.setForeground(Color.red);
            } else {
                txaD.setForeground(Color.red);
            }

            pnND.add(txaND);
            pnND.add(txaA);
            pnND.add(txaB);
            pnND.add(txaC);
            pnND.add(txaD);
            pnDe.add(pnND);
        }

        pnTitle.add(lbKt);
        pnTitle.add(lbMon);
        pnTitle.add(lbTC);

        pnTong.add(pnTitle);
        pnTong.add(pane);
        pane.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.9), (int) ((int) heightFrame * 0.8)));
        pnTitle.setBackground(new Color(255, 255, 255));
        pCenter.add(pnTong);
        pCenter.setBackground(Color.white);
        f.add(pCenter, BorderLayout.CENTER);
    }

    public void createEast() {
        pEast.setBackground(new Color(26, 157, 255));
        pEast.setPreferredSize(new Dimension(3, 0));

        f.add(pEast, BorderLayout.EAST);
    }

    public void createSouth() {
        pSouth.setBackground(new Color(26, 157, 255));
        pSouth.setPreferredSize(new Dimension(0, 3));

        f.add(pSouth, BorderLayout.SOUTH);
    }

}
