/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_Exam;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author ASUS
 */
public class BaseGUI {

    public void isEnabled(MyButton btn) {
        btn.setEnabled(false);
        btn.setBackground(Color.lightGray);
        btn.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {

            }

            @Override
            public void mousePressed(MouseEvent arg0) {

            }

            @Override
            public void mouseReleased(MouseEvent arg0) {

            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                btn.setBackground(Color.lightGray);
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                btn.setBackground(Color.lightGray);
            }
        });
    }

    public void setEnabled(MyButton btn) {
        btn.setEnabled(true);
        btn.setBackground(new Color(172, 255, 175));
        btn.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {

            }

            @Override
            public void mousePressed(MouseEvent arg0) {

            }

            @Override
            public void mouseReleased(MouseEvent arg0) {

            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                btn.setBackground(new Color(0, 205, 102));
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                btn.setBackground(new Color(172, 255, 175));

            }
        });
    }
}
