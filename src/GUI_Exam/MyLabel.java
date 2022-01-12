/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_Exam;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 *
 * @author ASUS
 */
public class MyLabel extends JLabel {

    public MyLabel() {
        this.setOpaque(true);
        this.setPreferredSize(new Dimension(90, 30));
        this.setHorizontalAlignment(SwingConstants.LEFT);
        this.setFont(this.getFont().deriveFont(15.0f));
        this.setBackground(Color.WHITE);
    }

    public MyLabel(String text) {
        this.setText(text);
        this.setOpaque(true);
        this.setPreferredSize(new Dimension(90, 30));
        this.setHorizontalAlignment(SwingConstants.LEFT);
        this.setFont(this.getFont().deriveFont(15.0f));
        this.setBackground(Color.WHITE);
    }

    @Override
    public void setPreferredSize(Dimension preferredSize) {
        super.setPreferredSize(preferredSize);
    }

}
