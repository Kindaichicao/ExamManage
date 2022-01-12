/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_Exam;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 *
 * @author "ThanhCute"
 */
public class MyButton extends JLabel implements MouseListener {

    public static int ADD = 1;
    public static int SUBMIT = 2;
    public static int DELETE = 3;
    public static int EXIT = 4;
    public static int MENU = 5;
    public static int ERROR = 6;

    private Color hoverInColor = new Color(211, 160, 211);
    private Color hoverOutColor = new Color(230, 230, 250);

    public MyButton() {
        this.setOpaque(true);
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.addMouseListener(this);
        this.setBackground(hoverOutColor);
    }

    public MyButton(int type) {
        this.setOpaque(true);
        this.setHorizontalAlignment(SwingConstants.CENTER);
        switch (type) {
            case 1:
                this.hoverInColor = new Color(0, 205, 102);
                this.hoverOutColor = new Color(172, 255, 175);
                break;
            case 2:
                this.hoverInColor = new Color(255, 106, 106);
                this.hoverOutColor = new Color(255, 127, 80);
                break;
            case 3:
                this.hoverInColor = new Color(255, 48, 48);
                this.hoverOutColor = new Color(255, 106, 106);
                break;
            case 4:
                this.hoverInColor = new Color(255, 48, 48);
                this.hoverOutColor = new Color(230, 230, 250);
                break;
            case 5:
                this.hoverInColor = new Color(0, 205, 102);
                this.hoverOutColor = new Color(158, 222, 115);
                break;
            case 6:
                this.hoverInColor = new Color(255, 255, 102);
                this.hoverOutColor = new Color(255, 127, 80);
                break;
        }
        this.addMouseListener(this);
        this.setBackground(hoverOutColor);
    }

    public MyButton(String text) {
        this.setOpaque(true);
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setText(text);
        this.addMouseListener(this);
        this.setBackground(hoverOutColor);
    }

    public MyButton(String text, int type) {
        this.setOpaque(true);
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setText(text);
        switch (type) {
            case 1:
                this.hoverInColor = new Color(0, 205, 102);
                this.hoverOutColor = new Color(172, 255, 175);
                break;
            case 2:
                this.hoverInColor = new Color(255, 106, 106);
                this.hoverOutColor = new Color(255, 127, 80);
                break;
            case 3:
                this.hoverInColor = new Color(255, 48, 48);
                this.hoverOutColor = new Color(255, 106, 106);
                break;
            case 4:
                this.hoverInColor = new Color(255, 48, 48);
                this.hoverOutColor = new Color(230, 230, 250);
                break;
            case 5:
                this.hoverInColor = new Color(0, 205, 102);
                this.hoverOutColor = new Color(158, 222, 115);
                break;
            case 6:
                this.hoverInColor = new Color(255, 255, 102);
                this.hoverOutColor = new Color(255, 127, 80);
                break;
        }
        this.addMouseListener(this);
        this.setBackground(hoverOutColor);
    }

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
        this.setBackground(this.hoverInColor);
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        this.setBackground(this.hoverOutColor);
    }

}
