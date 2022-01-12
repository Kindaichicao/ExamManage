/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_Exam;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author "ThanhCute"
 */
public class MainFrame {

    public static LoiKetNoiFrame isKN = new LoiKetNoiFrame();

    public static void main(String[] args) {
        new AutoGUI();
        LogInFrame s = new LogInFrame();
        s.createLoginFrame();
        s.setVisible(true);
    }
}
