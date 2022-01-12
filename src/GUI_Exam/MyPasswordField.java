/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_Exam;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.JPasswordField;

/**
 *
 * @author ASUS
 */
public class MyPasswordField extends JPasswordField {

    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

    public MyPasswordField() {
        this.setPreferredSize(new Dimension((int) ((int) d.getWidth() * 0.15), (int) (0.04 * d.getHeight())));
        this.setBorder(BorderFactory.createLineBorder(Color.decode("#ACE1AF"), 2));

    }
}
