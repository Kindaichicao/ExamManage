/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_Exam;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JTextField;

/**
 *
 * @author ASUS
 */
public class MyTextField extends JTextField {

    public MyTextField() {
        this.setBorder(BorderFactory.createLineBorder(Color.decode("#ACE1AF"), 3));
        this.setPreferredSize(new Dimension(220, 30));
    }

    public MyTextField(String Text, int a) {
        this.setBorder(BorderFactory.createLineBorder(Color.decode("#33CCFF"), 3));
        this.setPreferredSize(new Dimension(220, 30));
        this.setText(Text);
    }

}
