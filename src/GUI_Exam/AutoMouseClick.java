/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_Exam;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author ASUS
 */
public class AutoMouseClick {

    public void autoMouse(MyButton btn, Object key, ArrayList<MyButton> buttons, Object title) {
        btn.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                for (MyButton button : buttons) {
                    button.setBackground(new Color(158, 222, 115));
                    button.addMouseListener(new MouseAdapter() {
                        public void mouseExited(MouseEvent arg0) {
                            button.setBackground(new Color(158, 222, 115));
                        }
                    });
                }
                btn.setBackground(new Color(0, 205, 102));
                btn.addMouseListener(new MouseAdapter() {
                    public void mouseExited(MouseEvent arg0) {
                        btn.setBackground(new Color(0, 205, 102));
                    }
                });
                AutoGUI auto = new AutoGUI();
                auto.map_gui.get(key).apply(title);
            }
        });
    }
}
