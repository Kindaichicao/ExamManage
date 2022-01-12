/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_Exam;

import BUS_EXAM.KyThiBUS;
import BUS_EXAM.PDFBUS;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Admin
 */
public class EventThongKeGUI {
    
    PDFBUS pdf = new PDFBUS();
    
    //Sự kiện hover cho Label
    public void eventHover(Vector<JLabel> listLabel){
        for(JLabel lb : listLabel){
            lb.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseEntered(MouseEvent e){
                    lb.setBackground(new Color(30,144,255));
                    lb.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                }
                
                public void mouseClicked(MouseEvent e){
                    lb.setBackground(new Color(30,144,255));
                    for(JLabel temp : listLabel) if (!temp.equals(lb)){
                        temp.setBackground(new Color(135,206,250));
                        temp.addMouseListener(new MouseAdapter(){
                            @Override
                             public void mouseExited(MouseEvent e){
                                   temp.setBackground(new Color(135,206,250));
                             }
                        });
                    }
                    lb.addMouseListener(new MouseAdapter(){
                        public void mouseExited(MouseEvent e){
                            lb.setBackground(new Color(30,144,255));
                        }
                    });
                }
                
                public void mouseExited(MouseEvent e){
                    lb.setBackground(new Color(135,206,250));
                }
                
            });
            
        }
    }
    
    //Sự kiện chuyển đổi giao diện cho Label
     public void eventPanel(JLabel lb,JPanel coverPanel,JPanel panel,JComboBox cb){
        lb.addMouseListener(new MouseAdapter(){
             public void mouseClicked(MouseEvent e){
                if(cb.getSelectedIndex()>=0){
                    coverPanel.removeAll();
                    coverPanel.repaint();
                    coverPanel.add(panel);
                    coverPanel.repaint();
                    coverPanel.revalidate();
                }
            }
        });
    }   
    
     //Sự kiện click xuất file Excel Kỳ Thi
    public void eventExportExcelKyThi(JLabel lb,JPanel panel){
        lb.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                FileChooserGUI save = new FileChooserGUI();
                String fileName = save.getFileExcelNameSave(panel);
                ThongKeKyThiGUI kythi = new ThongKeKyThiGUI();
                kythi.exportExcelThongKe(fileName);
            }
        });
    }
    
    //Sự kiện click xuất file pdf (báo cáo)
    public void eventExportReportKyThi(JLabel lb,String maKyThi){
        lb.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                FileChooserGUI save = new FileChooserGUI();
                System.out.println(maKyThi+" ngu");
                System.out.println(pdf.exportPDFKyThi(maKyThi,save.getFilePDFNameSave(lb)));
            }
        });
    }
    
    
}
