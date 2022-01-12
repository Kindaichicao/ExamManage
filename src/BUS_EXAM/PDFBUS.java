/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS_EXAM;
import DAO_EXAM.PDFDAO;
import java.awt.print.PrinterJob;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;

/**
 *
 * @author Admin
 */
public class PDFBUS {
    PDFDAO pdfDAO = new PDFDAO();
    
    public boolean exportPDFKyThi(String maKyThi,String fileName){
        return pdfDAO.exportPDFKyThi(maKyThi, fileName);
    }
    
    public boolean printPDFKyThi(){
        InputStream fileInput = null;
        try {
            fileInput = new BufferedInputStream(new FileInputStream("C:\\Users\\Admin\\Desktop\\sndns.pdf"));
            Doc myDoc = new SimpleDoc(fileInput,DocFlavor.INPUT_STREAM.AUTOSENSE, null);
            PrinterJob printerJob = PrinterJob.getPrinterJob();
            PrintService printService;
            if(printerJob.printDialog()){
                printService = printerJob.getPrintService();
                DocPrintJob printJob = printService.createPrintJob();
                try {
                    PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
                    printJob.print(myDoc,aset);
                } catch (PrintException ex) {
                    System.err.println("Err in 50");
                    return false;
                }
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Err in 54");
            return false;
        }
        return true;
    }
}
