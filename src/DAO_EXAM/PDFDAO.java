package DAO_EXAM;

import DAO_EXAM.KyThiDAO;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import GUI_Exam.FileChooserGUI;
import java.util.Locale;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.ExporterInput;
import net.sf.jasperreports.export.OutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.view.JasperViewer;

public class PDFDAO extends BaseDAO {

    public boolean exportPDFKyThi(String maKyThi, String fileName) {
        KyThiDAO kythi = new KyThiDAO();
        String reportSrcFile = "fileReport\\file_Compile_KyThi.jrxml";
        JasperReport jasperReport = null;
        try {
            jasperReport = JasperCompileManager.compileReport(reportSrcFile);
        } catch (JRException ex) {
            Logger.getLogger(PDFDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("tenkythi", kythi.getKyThi(maKyThi).getTenKyThi());
        parameters.put("maKyThi", maKyThi);
        JasperPrint print = null;
        try {
            print = JasperFillManager.fillReport(jasperReport, parameters, super.getConnection());
        } catch (JRException ex) {
            Logger.getLogger(PDFDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        // PDF Exportor.
        JRPdfExporter exporter = new JRPdfExporter();

        ExporterInput exporterInput = new SimpleExporterInput(print);
        // ExporterInput
        exporter.setExporterInput(exporterInput);

        // ExporterOutput
        OutputStreamExporterOutput exporterOutput = new SimpleOutputStreamExporterOutput(fileName);

        // Output
        exporter.setExporterOutput(exporterOutput);
        SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
        exporter.setConfiguration(configuration);
        try {
            exporter.exportReport();
        } catch (JRException ex) {
            Logger.getLogger(PDFDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        JasperViewer.viewReport(print);

        return true;
    }

    public static void main(String[] args) {
        PDFDAO pdf = new PDFDAO();
        System.out.println(pdf.exportPDFKyThi("KT01", "check.pdf"));
    }
}
