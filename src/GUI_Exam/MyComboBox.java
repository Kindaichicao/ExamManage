/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_Exam;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import static org.apache.poi.xssf.usermodel.XSSFBuiltinTableStyle.init;

/**
 *
 * @author AQ
 */
public class MyComboBox extends JComboBox {

    public MyComboBox() {
        super();
        this.setBorder(BorderFactory.createLineBorder(Color.decode("#ACE1AF"), 3));
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(220, 30));
    }

    public MyComboBox(String[] string) {
        super(string);
        this.setBorder(BorderFactory.createLineBorder(Color.decode("#ACE1AF"), 3));
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(220, 30));
    }

    public MyComboBox(Vector<String> items) {
        super(items);
        setModel(new DefaultComboBoxModel<String>(items));
        this.setBorder(BorderFactory.createLineBorder(Color.decode("#ACE1AF"), 3));
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(220, 30));
        init();
    }

}
