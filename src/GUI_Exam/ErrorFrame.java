/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_Exam;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;
import java.util.function.Function;

/**
 *
 * @author "ThanhCute"
 */
public class ErrorFrame {

    JFrame f = new JFrame();
    static boolean check = false;
    private JPanel pNorth = new JPanel();
    private JPanel pCenter = new JPanel();
    private Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    MyButton btnOK = new MyButton("OK", MyButton.ERROR);
    MyButton btnDong = new MyButton("Đóng", MyButton.ERROR);
    MyButton btnThoat = new MyButton("X", MyButton.EXIT);

    public ErrorFrame(String title) {
        check = false;
        f.setUndecorated(true);
        f.setPreferredSize(new Dimension((int) ((int) d.getWidth() * 0.5), (int) (0.2 * d.getHeight())));

        f.setLayout(new BorderLayout());
        f.setBounds((int) ((int) d.getWidth() * 0.385), (int) (0.35 * d.getHeight()), (int) ((int) d.getWidth() * 0.23), (int) (0.2 * d.getHeight()));
        createNorth();
        createCenter(title);

        btnOK.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                check = true;
                f.setVisible(false);

            }
        });
        f.setVisible(true);
    }

    public ErrorFrame(String title, JFrame a, String func) {
        check = false;
        f.setUndecorated(true);
        f.setPreferredSize(new Dimension((int) ((int) d.getWidth() * 0.5), (int) (0.2 * d.getHeight())));

        f.setLayout(new BorderLayout());
        f.setBounds((int) ((int) d.getWidth() * 0.385), (int) (0.35 * d.getHeight()), (int) ((int) d.getWidth() * 0.23), (int) (0.2 * d.getHeight()));
        createNorth();
        createCenter(title);
        btnDong.setText("Hủy");
        btnOK.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                check = true;
                f.setVisible(false);
                AutoGUI auto = new AutoGUI();
                auto.map_gui.get(func).apply(a);
            }
        });

        f.setVisible(true);
    }

    public void createNorth() {

        pNorth.setLayout(new FlowLayout(2, 5, 3));
        pNorth.setPreferredSize(new Dimension(0, 30));
        pNorth.setBackground(new Color(255, 127, 80));

        MyButton btnMinimize = new MyButton("-");

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

    public void createCenter(String title) {
        MyBorder bd = new MyBorder(new Color(255, 127, 80), 3, 0, 0);
        pCenter.setBorder(bd);
        pCenter.setBackground(Color.white);
        pCenter.setLayout(new FlowLayout(1, 0, 3));
        JLabel lbTitle = new JLabel(title);
        lbTitle.setPreferredSize(new Dimension((int) ((int) d.getWidth() * 0.23) - 8, (int) (0.11 * d.getHeight())));
        lbTitle.setBackground(Color.white);
        lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lbTitle.setFont(lbTitle.getFont().deriveFont(14.0f));
        lbTitle.setOpaque(true);

        pCenter.add(lbTitle);
        JPanel pnButton = new JPanel();
        pnButton.setBackground(Color.white);
        pnButton.setLayout(new FlowLayout(1, 10, 0));

        btnOK.setPreferredSize(new Dimension(50, 30));
        btnDong.setPreferredSize(new Dimension(50, 30));
        btnOK.setOpaque(true);

        btnDong.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                check = false;
                f.setVisible(false);

            }
        });

        pnButton.add(btnOK);
        pnButton.add(btnDong);
        pCenter.add(pnButton);
        f.add(pCenter, BorderLayout.CENTER);
    }

    public boolean getCheck() {
        return this.check;
    }

}
