/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_Exam;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;

/**
 *
 * @author ASUS
 */
public class MyCheckBox extends JCheckBox {

    public MyCheckBox() {
        this.setIcon(new ImageIcon("src/img/uncheck.png"));
        // Set selected icon when checkbox state is selected
        this.setSelectedIcon(new ImageIcon("src/img/checked.png"));
        // Set disabled icon for checkbox
        this.setDisabledIcon(new ImageIcon("src/img/uncheck.png"));
        // Set disabled-selected icon for checkbox
        this.setDisabledSelectedIcon(new ImageIcon(""));
        // Set checkbox icon when checkbox is pressed
        this.setPressedIcon(new ImageIcon("src/img/checked.png"));
        // Set icon when a mouse is over the checkbox
        this.setRolloverIcon(new ImageIcon("src/img/uncheck.png"));
        // Set icon when a mouse is over a selected checkbox
        this.setRolloverSelectedIcon(new ImageIcon("src/img/checked.png"));
    }

    public MyCheckBox(String text) {
        this.setText(text);
        this.setFont(this.getFont().deriveFont(14.0f));
        this.setIcon(new ImageIcon("src/img/uncheck.png"));
        // Set selected icon when checkbox state is selected
        this.setSelectedIcon(new ImageIcon("src/img/checked.png"));
        // Set disabled icon for checkbox
        this.setDisabledIcon(new ImageIcon("src/img/uncheck.png"));
        // Set disabled-selected icon for checkbox
        this.setDisabledSelectedIcon(new ImageIcon(""));
        // Set checkbox icon when checkbox is pressed
        this.setPressedIcon(new ImageIcon("src/img/checked.png"));
        // Set icon when a mouse is over the checkbox
        this.setRolloverIcon(new ImageIcon("src/img/uncheck.png"));
        // Set icon when a mouse is over a selected checkbox
        this.setRolloverSelectedIcon(new ImageIcon("src/img/checked.png"));
    }
}
