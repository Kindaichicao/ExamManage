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
import DTO_EXAM.DeThiDTO;
import DTO_EXAM.MonDTO;
import GUI_Exam.MyButton;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.event.*;

/**
 *
 * @author AQ
 */
public class ChiTietKetQuaFrame {

    JFrame f = new JFrame();
    JPanel pNorth = new JPanel();
    JPanel pWest = new JPanel();
    JPanel pCenter = new JPanel();
    JPanel pEast = new JPanel();
    JPanel pSouth = new JPanel();
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int widthFrame = (int) ((int) screenSize.getWidth() * 0.5);
    int heightFrame = (int) ((int) screenSize.getHeight() * 0.5);
    String dapAnDungs = "";

    public void createJFrame(DeThiDTO de, BaiThiDTO baiThi) {

        f.setUndecorated(true);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new BorderLayout());

        f.setPreferredSize(new Dimension(widthFrame, (int) (de.getSoLuongCauHoi() / 5.0) * 40 + (int) ((int) heightFrame * 0.4 + 15)));
        f.setBounds(0, 0, widthFrame, (int) (de.getSoLuongCauHoi() / 5.0) * 40 + (int) ((int) heightFrame * 0.4 + 15));
        f.setLocation((int) screenSize.getWidth() / 2 - (int) f.getPreferredSize().getWidth() / 3,
                (int) screenSize.getHeight() / 3 - (int) f.getPreferredSize().getHeight() / 3);
        createNorth();
        createWest();
        createCenter(de, baiThi);
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
        lbTitle.setText("KẾT QUẢ");
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

    public void createCenter(DeThiDTO de, BaiThiDTO baiThi) {
        pCenter.setLayout(new FlowLayout(1, 30, 10));
        DeThiBUS busDe = new DeThiBUS();
        MonBUS busMon = new MonBUS();
        KyThiBUS busKyThi = new KyThiBUS();
        JPanel pnTitle = new JPanel();
        pnTitle.setLayout(new FlowLayout(0, 0, 5));
        pnTitle.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.9), (int) ((int) heightFrame * 0.2)));
        MonDTO mon = busMon.getObject(de.getMaMon());
        String tenKyThi = "KỲ THI " + busKyThi.getTenKyThi(de.getMaKyThi());
        JLabel lbKt = new JLabel();

        lbKt.setText(tenKyThi.toUpperCase());
        lbKt.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.9), (int) ((int) heightFrame * 0.05)));
        lbKt.setHorizontalAlignment(SwingConstants.CENTER);
        lbKt.setFont(lbKt.getFont().deriveFont(14.0f));

        JLabel lbMon = new JLabel();
        lbMon.setText("Môn: " + mon.getTenMon() + "   /   Số tín chỉ: " + Integer.toString(mon.getSoTinChi()));
        lbMon.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.9), (int) ((int) heightFrame * 0.05)));
        lbMon.setHorizontalAlignment(SwingConstants.CENTER);
        lbMon.setFont(lbMon.getFont().deriveFont(14.0f));

        JLabel lbTC = new JLabel("Số câu đúng: " + baiThi.getSoCauDung() + "/" + de.getSoLuongCauHoi()
                + "  (" + baiThi.getDiem() + "/10 Điểm)");
        lbTC.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.9), (int) ((int) heightFrame * 0.05)));
        lbTC.setFont(lbTC.getFont().deriveFont(14.0f));
        lbTC.setForeground(Color.red);
        lbTC.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel pnChuThich = new JPanel();
        pnChuThich.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.9), (int) ((int) heightFrame * 0.05)));
        pnChuThich.setLayout(new FlowLayout(0, 10, 0));
        JLabel lbDACX = new JLabel();
        JLabel lbTxDACX = new JLabel(":Đáp án chính xác");
        JLabel lbDung = new JLabel();
        JLabel lbTxDung = new JLabel(":Chọn Đúng");
        JLabel lbSai = new JLabel();
        JLabel lbTxSai = new JLabel(":Chọn Sai");

        lbDACX.setPreferredSize(new Dimension(20, 20));
        lbDACX.setOpaque(true);
        lbDACX.setBackground(new Color(67, 209, 255));

        lbDung.setBackground(new Color(160, 214, 102));
        lbDung.setPreferredSize(new Dimension(20, 20));
        lbDung.setOpaque(true);

        lbSai.setPreferredSize(new Dimension(20, 20));
        lbSai.setOpaque(true);
        lbSai.setBackground(new Color(255, 97, 97));

        pnChuThich.add(lbDACX);
        pnChuThich.add(lbTxDACX);
        pnChuThich.add(lbDung);
        pnChuThich.add(lbTxDung);
        pnChuThich.add(lbSai);
        pnChuThich.add(lbTxSai);

        pnTitle.add(lbKt);
        pnTitle.add(lbMon);
        pnTitle.add(lbTC);
        pnTitle.add(pnChuThich);

        JPanel pnMenu = new JPanel();
        pnMenu.setLayout(new FlowLayout(0, 7, 5));
        pnMenu.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.9), (int) (de.getSoLuongCauHoi() / 5.0) * 40 + 5));

        CauHoiBUS ch = new CauHoiBUS();
        ArrayList<CauHoiDTO> cauHois = ch.getCauHoiList(de.getMaDe());

        for (int i = 1; i <= de.getSoLuongCauHoi(); i++) {
            JPanel pnBao = new JPanel();
            pnBao.setLayout(new FlowLayout(0, 10, 5));
            pnBao.setBackground(new Color(221, 248, 255));
            String name = "Câu " + Integer.toString(i) + ": " + cauHois.get(i - 1).getDapanDung();
            JLabel btMenu = new JLabel(name, JLabel.CENTER);
            btMenu.setPreferredSize(new Dimension(70, 25));
            btMenu.setOpaque(true);
            btMenu.setBackground(new Color(67, 209, 255));
            Character dapAn = baiThi.getDsCauTraLoi().charAt(i - 1);
            JLabel lbChon = new JLabel(dapAn.toString(), JLabel.CENTER);
            lbChon.setPreferredSize(new Dimension(30, 25));
            lbChon.setOpaque(true);
            if (Character.getNumericValue(baiThi.getDsDungSai().charAt(i - 1)) == 1) {
                lbChon.setBackground(new Color(160, 214, 102));
            } else {
                lbChon.setBackground(new Color(255, 97, 97));
            }
            pnBao.add(btMenu);
            pnBao.add(lbChon);
            pnMenu.add(pnBao);
        }

//        pnMenu.setBackground(Color.red);
        pCenter.setBackground(Color.white);
        pnTitle.setBackground(new Color(255, 255, 255));
        pnMenu.setBackground(new Color(255, 217, 217));
        pnChuThich.setBackground(Color.WHITE);
        pCenter.add(pnTitle);
        pCenter.add(pnChuThich);
        pCenter.add(pnMenu);
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
