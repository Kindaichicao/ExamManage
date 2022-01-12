/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_Exam;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;
import java.util.function.Function;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

/**
 *
 * @author "ThanhCute"
 */
public class MyTable extends JTable {

    public MyTable() {
        this.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 15));
        this.getTableHeader().setOpaque(false);
        this.getTableHeader().setBackground(new Color(32, 136, 203));
        this.getTableHeader().setForeground(new Color(255, 255, 255));
        this.setRowHeight(25);
        this.setSelectionBackground(new Color(245, 154, 189));
        this.setGridColor(new Color(32, 136, 203));
        this.setModel(dataModel);
        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(JLabel.CENTER);

        this.updateUI();
    }

    public MyTable(TableModel model) {
        super(model);
        this.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 15));
        this.getTableHeader().setOpaque(false);
        this.getTableHeader().setBackground(new Color(32, 136, 203));
        this.getTableHeader().setForeground(new Color(255, 255, 255));
        this.setRowHeight(25);
        this.setSelectionBackground(new Color(245, 154, 189));
        this.setGridColor(new Color(32, 136, 203));
        this.setModel(dataModel);
        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(JLabel.CENTER);
        this.setDefaultRenderer(this.getColumnClass(0), center);
        this.updateUI();
    }

    public void createModelRow(String[] data, DefaultTableModel model) {
        Vector row = new Vector();
        for (int i = 0; i < data.length; i++) {
            row.add(data[i]);
        }
        model.addRow(row);
    }
}
