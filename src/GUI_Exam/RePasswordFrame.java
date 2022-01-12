/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_Exam;

import BUS_EXAM.*;
import DTO_EXAM.NguoiDungDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

/**
 *
 * @author ASUS
 */
public class RePasswordFrame {

    JFrame f = new JFrame();
    static boolean check = false;
    JPanel pNorth = new JPanel();
    JPanel pCenter = new JPanel();
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    MyButton btnOK = new MyButton("Lưu Thay Đổi", MyButton.ERROR);
    MyButton btnDong = new MyButton("Đóng", MyButton.ERROR);
    MyPasswordField txMKM = new MyPasswordField();
    MyPasswordField txNLMKM = new MyPasswordField();
    MyPasswordField txMKC = new MyPasswordField();

    public RePasswordFrame() {
        f.setUndecorated(true);
        f.setPreferredSize(new Dimension((int) ((int) d.getWidth() * 0.5), (int) (0.15 * d.getHeight())));

        f.setLayout(new BorderLayout());
        f.setBounds((int) ((int) d.getWidth() * 0.35), (int) (0.25 * d.getHeight()), (int) ((int) d.getWidth() * 0.3), (int) (0.38 * d.getHeight()));
        createNorth();
        createCenter();
        f.setVisible(true);
    }

    public void createNorth() {

        pNorth.setLayout(new FlowLayout(2, 5, 3));
        pNorth.setPreferredSize(new Dimension(0, 30));
        pNorth.setBackground(new Color(255, 127, 80));

        MyButton btnMinimize = new MyButton("-");
        MyButton btnThoat = new MyButton("X", MyButton.EXIT);

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
        pNorth.add(btnMinimize);
        pNorth.add(btnThoat);
        f.add(pNorth, BorderLayout.NORTH);
    }

    public void createCenter() {
        MyBorder bd = new MyBorder(new Color(255, 127, 80), 2, 0, 0);
        pCenter.setBackground(Color.white);
        pCenter.setBorder(bd);
        pCenter.setLayout(new FlowLayout(1, 0, 10));
        pCenter.setPreferredSize(new Dimension((int) ((int) d.getWidth() * 0.3), 100));
        JLabel lbTitle = new JLabel("ĐỔI MẬT KHẨU");
        lbTitle.setPreferredSize(new Dimension((int) ((int) d.getWidth() * 0.3), (int) (0.05 * d.getHeight())));;
        lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lbTitle.setFont(lbTitle.getFont().deriveFont(18.0f));
        MyLabel lbMKC = new MyLabel("Mật Khẩu Hiện Tại: ");
        MyLabel lbMKM = new MyLabel("Mật Khẩu Mới: ");
        MyLabel lbNLMKM = new MyLabel("Nhập Lại Mật Khẩu Mới: ");

        txMKC.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent arg0) {
                if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
                    txMKM.requestFocus();
                }
            }
        });
        txMKM.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent arg0) {
                if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
                    txNLMKM.requestFocus();
                }
            }
        });
        txNLMKM.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent arg0) {
                if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
                    updatePass();
                }
            }
        }
        );
        lbMKM.setPreferredSize(
                new Dimension((int) ((int) d.getWidth()
                        * 0.12), (int) (0.05 * d.getHeight())));
        lbMKC.setPreferredSize(
                new Dimension((int) ((int) d.getWidth()
                        * 0.12), (int) (0.05 * d.getHeight())));
        lbNLMKM.setPreferredSize(
                new Dimension((int) ((int) d.getWidth()
                        * 0.12), (int) (0.05 * d.getHeight())));

        MyButton btnSubmit = new MyButton("Lưu Thay Đổi", MyButton.SUBMIT);
        btnSubmit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                updatePass();
            }

        });

        btnSubmit.setPreferredSize(
                new Dimension((int) ((int) d.getWidth()
                        * 0.1), (int) (0.05 * d.getHeight())));
        btnSubmit.setFont(btnSubmit.getFont().deriveFont(16.0f));

        pCenter.add(lbTitle);

        pCenter.add(lbMKC);

        pCenter.add(txMKC);

        pCenter.add(lbMKM);

        pCenter.add(txMKM);

        pCenter.add(lbNLMKM);

        pCenter.add(txNLMKM);

        pCenter.add(btnSubmit);

        f.add(pCenter, BorderLayout.CENTER);
    }

    public void updatePass() {
        String before = new String(txMKC.getPassword());
        String after = new String(txMKM.getPassword());
        String reAfter = new String(txNLMKM.getPassword());
        RePasswordBUS re = new RePasswordBUS();

        int check = re.rePass(before, after, reAfter);
        if (before.isEmpty()) {
            new ErrorFrame("Vui lòng nhập mật khẩu");
            txMKC.requestFocus();
        } else if (after.isEmpty()) {
            new ErrorFrame("Vui lòng nhập mật khẩu mới");
            txMKM.requestFocus();
        } else if (reAfter.isEmpty()) {
            new ErrorFrame("Vui lòng nhập lại mật khẩu");
            txNLMKM.requestFocus();
        } else {
            if (check == 0) {
                new ErrorFrame("Mật khẩu không đúng");
            } else if (check == 1) {
                new ErrorFrame("Mật khẩu không khớp");
            } else {
                new ErrorFrame("Đổi Thành Công");
            }
        }
    }
}
