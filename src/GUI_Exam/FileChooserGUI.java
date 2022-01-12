/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_Exam;

import java.awt.Component;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileView;

/**
 *
 * @author Admin Class này để gọi hộp thoại mở file lúc import, export
 */
public class FileChooserGUI {

    //Lấy tên file để lưu
    public String getFileExcelNameSave(Component f) {
        String fileName = "";
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        JFileChooser fileDialog = new JFileChooser();
        int rVal = fileDialog.showSaveDialog(f);
        if (rVal == JFileChooser.APPROVE_OPTION) {
            fileName = fileDialog.getSelectedFile().getPath();
        }
        if (!fileName.endsWith("xlsx") || !fileName.endsWith("xls")) {
            fileName = fileName + ".xlsx";
        };
        return fileName;
    }

    //Lấy tên file để mở
    public String getFileExcelNameOpen(Component f) {
        String fileName = "";
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        JFileChooser fileDialog = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel (*xlsx, *xls)", "xlsx", "xls");
        fileDialog.addChoosableFileFilter(filter);
        int rVal = fileDialog.showOpenDialog(f);
        if (rVal == JFileChooser.APPROVE_OPTION) {
            fileName = fileDialog.getSelectedFile().getPath();
        }
        return fileName;
    }

    public String getFilePDFNameSave(Component f) {
        String fileName = "";
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        JFileChooser fileDialog = new JFileChooser();
        int rVal = fileDialog.showSaveDialog(f);
        if (rVal == JFileChooser.APPROVE_OPTION) {
            fileName = fileDialog.getSelectedFile().getPath();
        }
        if (!fileName.endsWith("pdf")) {
            fileName = fileName + ".pdf";
        };
        return fileName;
    }
}
