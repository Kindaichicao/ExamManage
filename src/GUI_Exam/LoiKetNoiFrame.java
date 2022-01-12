/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_Exam;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.*;

/**
 *
 * @author ASUS
 */
public class LoiKetNoiFrame {

    private JFrame f = new JFrame();
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int widthFrame = (int) ((int) screenSize.getWidth() * 0.4);
    int heightFrame = (int) ((int) screenSize.getHeight() * 0.65);

    public LoiKetNoiFrame() {
        f.setLayout(null);
        f.setPreferredSize(new Dimension(widthFrame, heightFrame));
        f.setBounds(0, 0, widthFrame, heightFrame);
        f.setLocation((int) screenSize.getWidth() / 2 - (int) f.getPreferredSize().getWidth() / 2,
                (int) screenSize.getHeight() / 2 - (int) f.getPreferredSize().getHeight() / 2);
        ImageIcon icGif = new ImageIcon("src/img/b6231877f55f66bc0ddd1cf89c92b56e.gif");
        JLabel lbGif = new JLabel(icGif);
        JLabel lbThongBao = new JLabel("VUI LÒNG KIỂM TRA KẾT NỐI!");
        lbThongBao.setHorizontalAlignment(SwingConstants.CENTER);
        lbThongBao.setFont(lbThongBao.getFont().deriveFont(18.0f));
        lbThongBao.setForeground(Color.red);
        lbThongBao.setOpaque(true);
        lbThongBao.setBackground(Color.white);
        lbGif.setOpaque(true);
        lbGif.setBackground(Color.white);

        lbThongBao.setBounds(0, 0, widthFrame, 50);
        f.add(lbThongBao);
        lbGif.setBounds(0, 37, widthFrame, heightFrame);
        f.add(lbGif);

    }

    public void show() {
        f.setVisible(true);
    }
}
