/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_Exam;

import BUS_EXAM.*;
import DAO_EXAM.*;
import DTO_EXAM.DeThiDTO;
import DTO_EXAM.KyThiDTO;
import DTO_EXAM.*;
import GUI_Exam.MyButton;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import static java.lang.Thread.sleep;
import java.time.*;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.event.*;
import org.apache.commons.math3.special.Erf;

/**
 *
 * @author AQ
 */
public class ThiFrame {

    private int soCauDung = 0;
    private String dsDungSai = "";
    String svThi = "";

    public ThiFrame() {
    }

    JFrame f = new JFrame();
    JPanel pNorth = new JPanel();
    JPanel pWest = new JPanel();
    JPanel pCenter = new JPanel();
    JPanel pEast = new JPanel();
    JPanel pSouth = new JPanel();
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int widthFrame = (int) ((int) screenSize.getWidth() * 0.9);
    int heightFrame = (int) ((int) screenSize.getHeight() * 0.9);

    int page = 0;
    private int num = 0;
    int timeLeft;

    String[] titleProfile = {"Mã Số: ", "Họ Tên: ", "Ngày Sinh: ", "Giới tính: ", "Lớp: ", "Khoa:"};
    String phut;
    String giay;
    JLabel lbCoutDown = new JLabel();
    CauHoiBUS ch = new CauHoiBUS();
    ArrayList<CauHoiDTO> cauHois;

    JRadioButton[] rbA = new JRadioButton[100];
    JRadioButton[] rbB = new JRadioButton[100];
    JRadioButton[] rbC = new JRadioButton[100];
    JRadioButton[] rbD = new JRadioButton[100];
    boolean checkSubmit = false;
    KyThiBUS busKyThi = new KyThiBUS();
    KyThiDTO kythi;

    public void createJFrame(DeThiDTO de, SinhVienDTO tempUser, JFrame fLichThi) {
        kythi = busKyThi.getKyThi(de.getMaKyThi());
        cauHois = ch.getCauHoiList(de.getMaDe());
        num = de.getSoLuongCauHoi();
        LopBUS busLop = new LopBUS();
        KhoaBUS busKhoa = new KhoaBUS();
        String[] time = de.getGioThi().split(":");
        int[] times = doiGio(Integer.parseInt(time[1]) + de.getThoigianLamBai());
        times[0] += Integer.parseInt(time[0]);

        LocalTime ketthuc = LocalTime.of(times[0], times[1], Integer.parseInt(time[2]));
        LocalTime nowTime = LocalTime.now();
        int t1 = ketthuc.getHour() * 3600 + ketthuc.getMinute() * 60 + ketthuc.getSecond();
        int t2 = nowTime.getHour() * 3600 + nowTime.getMinute() * 60 + nowTime.getSecond();
        tempUser.setMaKhoa(ContainerDAO.daoLop.getMaKhoa(tempUser.maLop));
        String[] testProfile = {tempUser.getMaUser(), tempUser.getHoDem() + " " + tempUser.getTen(), tempUser.getNgaySinh(),
            tempUser.getGioiTinh(), busLop.getTenLop(tempUser.getMaLop()), busKhoa.getTenKhoa(tempUser.getMaKhoa())};

        f.setUndecorated(true);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new BorderLayout());

        f.setPreferredSize(new Dimension(widthFrame, heightFrame));
        f.setBounds(0, 0, widthFrame, heightFrame);
        f.setLocation((int) screenSize.getWidth() / 2 - (int) f.getPreferredSize().getWidth() / 2,
                (int) screenSize.getHeight() / 3 - (int) f.getPreferredSize().getHeight() / 3);

        createNorth(tempUser.getMaUser(), de.getMaDe());
        createWest();

        createCenter(de, testProfile, fLichThi);
        createEast();
        createSouth();
        if (((t1 - t2) / 60) > 9) {
            phut = Integer.toString((t1 - t2) / 60);
        } else {
            phut = "0" + Integer.toString((t1 - t2) / 60);
        }
        if (((t1 - t2) % 60) > 9) {
            giay = Integer.toString((t1 - t2) % 60);
        } else {
            giay = "0" + Integer.toString((t1 - t2) % 60);
        }
        lbCoutDown.setText(phut + " : " + giay);
        if (((t1 - t2) / 60) > 9) {
            phut = Integer.toString((t1 - t2) / 60);
        } else {
            phut = "0" + Integer.toString((t1 - t2) / 60);
        }
        if (((t1 - t2) % 60) > 9) {
            giay = Integer.toString((t1 - t2) % 60 - 1);
        } else {
            giay = "0" + Integer.toString((t1 - t2) % 60 - 1);
        }
        timeLeft = (t1 - t2) * 1000;
        f.setVisible(true);
        coutdown(cauHois, de, fLichThi);

    }

    public int[] doiGio(int time) {
        int[] times = new int[2];
        times[0] = time / 60;
        times[1] = time % 60;
        return times;
    }

    public void createNorth(String maUser, String maDe) {
        pNorth.setLayout(new FlowLayout(2, 5, 3));
        pNorth.setPreferredSize(new Dimension(0, 30));
        pNorth.setBackground(new Color(255, 127, 80));

        MyButton btnMinimize = new MyButton("-");
        MyButton btnThoat = new MyButton("X", MyButton.EXIT);

        btnMinimize.setFont(btnMinimize.getFont().deriveFont(25.0f));

        btnThoat.setPreferredSize(new Dimension(26, 24));
        btnMinimize.setPreferredSize(new Dimension(26, 24));

        btnMinimize.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                f.setState(Frame.ICONIFIED);
            }
        });

        btnThoat.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                ErrorFrame hoi = new ErrorFrame("Bạn Có Chắc Chắn Muốn Thoát Không");
                hoi.btnOK.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        nopBai(maUser, maDe, cauHois);
                        System.exit(0);
                    }
                });
            }

        });

        pNorth.add(btnMinimize);
        pNorth.add(btnThoat);

        f.add(pNorth, BorderLayout.NORTH);
    }

    public void createWest() {
        pWest.setLayout(new BorderLayout());
        JPanel pRightWest = new JPanel();
        JPanel pLeftWest = new JPanel();

        pWest.setBackground(new Color(255, 127, 80));
        pWest.setPreferredSize(new Dimension(3, 0));

        f.add(pWest, BorderLayout.WEST);
    }

    //Hàm gán sự kiện đổi màu nền khi chọn đáp án
    public void setEventSelected(JRadioButton rd, JTextArea txa) {
        rd.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent arg0) {
                if (!rd.isSelected()) {

                    txa.setBackground(Color.white);
                }

            }
        });
    }

    public void createCenter(DeThiDTO de, String[] testProfile, JFrame fLichThi) {

        pCenter.setLayout(new BoxLayout(pCenter, BoxLayout.Y_AXIS));

        JPanel pnTop = new JPanel();
//        pnTop.setBackground(new Color(158, 222, 115));
        pnTop.setBackground(new Color(255, 255, 255));
        pnTop.setLayout(new FlowLayout(0, 0, 0));
        pnTop.setPreferredSize(new Dimension((int) ((int) widthFrame), (int) ((int) heightFrame * 0.2)));

        //Thông tin sinh viên
        JPanel pnBaoProfile = new JPanel();
        pnBaoProfile.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.2), (int) ((int) heightFrame * 0.2)));
        pnBaoProfile.setBackground(Color.white);
        JPanel pnProfile = new JPanel();
        MyBorder bdProfile = new MyBorder(new Color(26, 157, 255), 3, 10, 0);
        pnProfile.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.19), (int) ((int) heightFrame * 0.19)));
        MyBorder bdTopLeft = new MyBorder(new Color(26, 157, 255), 6, 10, 0);
        MyBorder bdTopMid = new MyBorder(new Color(0, 139, 0), 3, 10, 0);
        pnProfile.setBorder(bdTopLeft);
        pnProfile.setBackground(new Color(232, 242, 252));
        pnProfile.setLayout(new FlowLayout(0, 8, 2));
        for (int i = 0; i < titleProfile.length; i++) {
            JLabel lbProfile = new JLabel(titleProfile[i] + testProfile[i]);
            lbProfile.setFont(lbProfile.getFont().deriveFont(15.0f));
            pnProfile.add(lbProfile);

        }
        pnBaoProfile.add(pnProfile);

        //Thông tin kỳ thi
        JPanel pnTopLeft = new JPanel();
        pnTopLeft.setLayout(new FlowLayout(1, 12, 5));
        pnTopLeft.setBackground(new Color(255, 255, 255));
        JPanel pnExam = new JPanel();
        pnExam.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.57), (int) ((int) heightFrame * 0.19)));
        pnExam.setBorder(bdTopMid);
        pnExam.setBackground(new Color(172, 255, 175, 100));
        pnExam.setLayout(new FlowLayout(1, 30, 10));

        JLabel lbExam = new JLabel("KỲ THI: " + kythi.getTenKyThi().toUpperCase(), JLabel.CENTER);
        lbExam.setFont(lbExam.getFont().deriveFont(22.0f));
        lbExam.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.52), (int) ((int) heightFrame * 0.04)));
        lbExam.setForeground(new Color(255, 0, 0));
        pnExam.add(lbExam);

        JLabel lbExam3 = new JLabel("Bắt đầu: " + kythi.getNgayBatDau(), JLabel.CENTER);
        JLabel lbExam3_1 = new JLabel("Kết Thúc: " + kythi.getNgayKetThuc(), JLabel.CENTER);
        lbExam3.setFont(lbExam.getFont().deriveFont(16.0f));
        lbExam3.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.24), (int) ((int) heightFrame * 0.04)));
        lbExam3.setHorizontalAlignment(SwingConstants.RIGHT);
        pnExam.add(lbExam3);

        lbExam3_1.setFont(lbExam.getFont().deriveFont(16.0f));
        lbExam3_1.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.26), (int) ((int) heightFrame * 0.04)));
        pnExam.add(lbExam3_1);
        lbExam3_1.setHorizontalAlignment(SwingConstants.LEFT);

        JLabel lbExam2 = new JLabel("MÔN: " + ContainerDAO.daoMon.getTenMon(de.getMaMon()).toUpperCase());
        lbExam2.setFont(lbExam.getFont().deriveFont(16.0f));
        pnExam.add(lbExam2);
//        }

        //Đếm ngược thời gian
        JPanel pnTime = new JPanel();
        MyBorder bdTopRight = new MyBorder(Color.red, 6, 1, 0);
        pnTime.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.2), (int) ((int) heightFrame * 0.19)));
        pnTime.setBorder(bdTopRight);
        pnTime.setBackground(new Color(255, 230, 255));
        pnTime.setLayout(new FlowLayout(1, 10, 20));

        JLabel lbTime = new JLabel("Thời gian còn lại:");
        lbTime.setFont(lbTime.getFont().deriveFont(20.0f));

        lbCoutDown.setFont(new Font("Arial", Font.BOLD, 40));
        lbCoutDown.setForeground(Color.red);

        pnTime.add(lbTime);
        pnTime.add(lbCoutDown);

        pnTopLeft.add(pnExam);
        pnTopLeft.add(pnTime);

        pnTop.add(pnBaoProfile);
        pnTop.add(pnTopLeft);

        JPanel pnBot = new JPanel();
        pnBot.setLayout(new FlowLayout(0, 0, 0));
        pnBot.setBackground(Color.white);
        pnBot.setPreferredSize(new Dimension((int) ((int) widthFrame), (int) ((int) heightFrame * 0.75)));

        //Danh sách câu hỏi
        JPanel pnMenu = new JPanel();
        JPanel pnBaoMenu = new JPanel();
        pnBaoMenu.setBackground(Color.white);
        pnBaoMenu.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.2), (int) ((int) heightFrame * 0.8)));
        pnMenu.setLayout(new FlowLayout(0, 5, 10));
        pnMenu.setBackground(new Color(232, 242, 252));
        pnMenu.setBorder(bdTopLeft);
        pnMenu.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.19), (int) ((int) heightFrame * 0.735)));

        MyLabel lbMenu = new MyLabel("DANH SÁCH CÂU HỎI");
        lbMenu.setBackground(new Color(232, 242, 252));
        lbMenu.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.16), (int) ((int) heightFrame * 0.05)));
        pnMenu.add(lbMenu);
        pnBaoMenu.add(pnMenu);

        //Nội dung câu hỏi
        final JPanel pnCard = new JPanel();
        CardLayout card = new CardLayout();
        pnCard.setLayout(card);

        JPanel pnQuest = new JPanel();
        pnQuest.setLayout(new FlowLayout(1, 50, 30));
        pnQuest.setBackground(Color.white);
        pnQuest.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.78), (int) ((int) heightFrame * 0.8)));

        ImageIcon icA = new ImageIcon("src/img/A (1).jpg");
        ImageIcon icB = new ImageIcon("src/img/B (1).jpg");
        ImageIcon icC = new ImageIcon("src/img/C (1).jpg");
        ImageIcon icD = new ImageIcon("src/img/D (1).jpg");
        ImageIcon icCH = new ImageIcon("src/img/66afeb2dd3b96d92cb05580f52db7597.gif");
        for (int i = 1; i <= de.getSoLuongCauHoi(); i++) {

            JPanel pnQ = new JPanel();
            pnQ.setLayout(new FlowLayout(0, 50, 30));
            pnQ.setBackground(Color.white);
            pnQ.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.78), (int) ((int) heightFrame * 0.55)));

            ImageIcon icCauHoi = new ImageIcon("src/img/icons8-task-50.png");
            JLabel lbCH = new JLabel(icCauHoi);
            lbCH.setText("CÂU HỎI SỐ " + i + ":");
            lbCH.setForeground(new Color(25, 118, 210));
            lbCH.setFont(new Font("Segoe UI", Font.BOLD, 40));
            lbCH.setFont(lbCH.getFont().deriveFont(20.0f));
            lbCH.setHorizontalAlignment(SwingConstants.LEFT);
            lbCH.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.71), (int) ((int) heightFrame * 0.05)));

            JPanel pnContent = new JPanel();
            pnContent.setLayout(null);
            pnContent.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.71), (int) ((int) heightFrame * 0.15)));
            pnContent.setBackground(new Color(255, 255, 255));

            JTextArea txtQuest = new JTextArea();
            JLabel lbCauHoi = new JLabel(icCH);
            lbCauHoi.setBounds(0, 0, (int) ((int) widthFrame * 0.07), (int) ((int) heightFrame * 0.12));
            lbCauHoi.setOpaque(true);
            lbCauHoi.setBackground(new Color(255, 255, 255));
            lbCauHoi.setBorder(bdProfile);
            pnContent.add(lbCauHoi);
            txtQuest.setText(cauHois.get(i - 1).getNoiDung());
            txtQuest.setFont(new Font("Segoe", Font.BOLD, 30));
            txtQuest.setFont(txtQuest.getFont().deriveFont(15.0f));
            txtQuest.setBounds(100, 0, (int) ((int) widthFrame * 0.64), (int) ((int) heightFrame * 0.12));
            txtQuest.setBackground(new Color(232, 242, 252));
            txtQuest.setEditable(false);
            txtQuest.setLineWrap(true);
            txtQuest.setBorder(bdProfile);
            pnContent.add(txtQuest);

            pnQ.add(lbCH);
            pnQ.add(pnContent);
            int j;
            for (j = 1; j <= 4; j++) {
                JTextArea txtAns = new JTextArea();
                JLabel lbAns = new JLabel();
                MyBorder bdTxtAns;
                Color isColor;
                JPanel pnAns = new JPanel();

                pnAns.setLayout(null);
                pnAns.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.34), (int) ((int) heightFrame * 0.1)));
                pnAns.setBackground(new Color(255, 255, 255));
                lbAns.setBounds(0, 0, (int) ((int) widthFrame * 0.06), (int) ((int) heightFrame * 0.1));

                txtAns.setFont(lbCH.getFont().deriveFont(13.0f));
                txtAns.setBounds(77, 1, (int) ((int) widthFrame * 0.28), (int) ((int) heightFrame * 0.096));
                txtAns.setBackground(new Color(255, 255, 255));
                txtAns.setEditable(false);
                txtAns.setLineWrap(true);
                txtAns.setWrapStyleWord(true);

                if (j == 1) {
                    bdTxtAns = new MyBorder(new Color(67, 209, 255), 3, 10, 0);
                    rbA[i] = new JRadioButton("");

                    isColor = new Color(221, 248, 255);
                    txtAns.setText(cauHois.get(i - 1).getDapanA());
                    lbAns.setIcon(icA);
                    eventAnswer(txtAns, isColor, rbA[i]);
                    setEventSelected(rbA[i], txtAns);
                    eventAnswerLb(lbAns, txtAns, isColor, rbA[i]);
                } else if (j == 2) {
                    bdTxtAns = new MyBorder(new Color(255, 254, 129), 3, 10, 0);
                    rbB[i] = new JRadioButton("");

                    isColor = new Color(249, 254, 201);
                    txtAns.setText(cauHois.get(i - 1).getDapanB());
                    lbAns.setIcon(icB);
                    eventAnswer(txtAns, isColor, rbB[i]);
                    setEventSelected(rbB[i], txtAns);
                    eventAnswerLb(lbAns, txtAns, isColor, rbB[i]);
                } else if (j == 3) {
                    bdTxtAns = new MyBorder(new Color(255, 97, 97), 3, 10, 0);
                    rbC[i] = new JRadioButton("");
                    isColor = new Color(255, 217, 217);
                    txtAns.setText(cauHois.get(i - 1).getDapanC());
                    lbAns.setIcon(icC);
                    eventAnswer(txtAns, isColor, rbC[i]);
                    setEventSelected(rbC[i], txtAns);
                    eventAnswerLb(lbAns, txtAns, isColor, rbC[i]);
                } else {
                    bdTxtAns = new MyBorder(new Color(160, 214, 102), 3, 10, 0);
                    rbD[i] = new JRadioButton("");

                    isColor = new Color(217, 238, 192);
                    txtAns.setText(cauHois.get(i - 1).getDapanD());
                    lbAns.setIcon(icD);
                    eventAnswer(txtAns, isColor, rbD[i]);
                    setEventSelected(rbD[i], txtAns);
                    eventAnswerLb(lbAns, txtAns, isColor, rbD[i]);
                }
                //sự kiên txtAns và lbAns

                //
                txtAns.setBorder(bdTxtAns);
                pnAns.add(lbAns);
                pnAns.add(txtAns);

                pnQ.add(pnAns);
            }

            ButtonGroup bgAns = new ButtonGroup();
            bgAns.add(rbA[i]);
            bgAns.add(rbB[i]);
            bgAns.add(rbC[i]);
            bgAns.add(rbD[i]);
            pnCard.add(Integer.toString(i), pnQ);
        }
        //Phân trang
        JPanel pnPage = new JPanel();
        pnPage.setLayout(new FlowLayout(1, 100, 0));
        pnPage.setBackground(Color.white);
        pnPage.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.71), (int) ((int) heightFrame * 0.1)));
        //số câu
        ArrayList<MyButton> buttons = new ArrayList();

        for (int i = 0; i < num; i++) {
            String name = "Câu " + Integer.toString(i + 1);
            MyButton btMenu = new MyButton(name, 1);
            btMenu.setPreferredSize(new Dimension(50, 25));
            pnMenu.add(btMenu);
            buttons.add(btMenu);
        }
        click(buttons.get(0), buttons);
        //Icon Thanh tao
        ImageIcon icNext2 = new ImageIcon("src/img/icons8-right-button-64.png");
        ImageIcon icNext1 = new ImageIcon("src/img/icons8-next-64.png");
        ImageIcon icPre2 = new ImageIcon("src/img/icons8-prev-64.png");
        ImageIcon icPre1 = new ImageIcon("src/img/icons8-previous-64.png");
        JLabel lbNext = new JLabel(icNext1);
        JLabel lbPre = new JLabel(icPre1);
        lbNext.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                card.next(pnCard);
                if (page == num - 1) {
                    page = 0;
                } else {
                    page++;
                }
                click(buttons.get(page), buttons);
            }

            @Override
            public void mousePressed(MouseEvent arg0) {

            }

            @Override
            public void mouseReleased(MouseEvent arg0) {

            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                lbNext.setIcon(icNext2);
                lbNext.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                lbNext.setIcon(icNext1);
            }
        });
        lbPre.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                card.previous(pnCard);
                if (page == 0) {
                    page = num - 1;
                } else {
                    page--;
                }
                click(buttons.get(page), buttons);
            }

            @Override
            public void mousePressed(MouseEvent arg0) {

            }

            @Override
            public void mouseReleased(MouseEvent arg0) {

            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                lbPre.setIcon(icPre2);
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                lbPre.setIcon(icPre1);
            }
        });
        pnPage.add(lbPre);
        pnPage.add(lbNext);

        int i = 1;
        for (MyButton bt : buttons) {
            String namecard = Integer.toString(i);
            i++;
            bt.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent arg0) {
                    card.show(pnCard, namecard);
                    click(bt, buttons);
                    page = Integer.parseInt(namecard) - 1;
                }
            });
        }

//        //Nộp bài
        JPanel pnSubmit = new JPanel();
        pnSubmit.setBackground(new Color(232, 242, 252));
        pnSubmit.setPreferredSize(new Dimension((int) ((int) widthFrame * 0.16), (int) ((int) heightFrame * 0.05)));
        MyButton btSubmit = new MyButton("Nộp bài", 2);
        btSubmit.setFont(btSubmit.getFont().deriveFont(14.0f));
        btSubmit.setPreferredSize(new Dimension(70, 30));
        pnSubmit.add(btSubmit);
        pnMenu.add(pnSubmit);

        btSubmit.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                ErrorFrame err = new ErrorFrame("Bạn Muốn Nộp Bài?");
                err.btnOK.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent arg0) {
                        nopBai(LoginBUS.isUser.getMaUser(), de.getMaDe(), cauHois);
                        checkSubmit = true;
                        f.setVisible(false);
                        fLichThi.setVisible(true);
                    }
                });
            }
        });

        pnQuest.add(pnCard);
        pnQuest.add(pnPage);
        pnBot.add(pnBaoMenu);
        pnBot.add(pnQuest);

        pCenter.add(pnTop);
        pCenter.add(pnBot);

        f.add(pCenter, BorderLayout.CENTER);
    }

    public void eventAnswer(JTextArea txtAns, Color isColor, JRadioButton rbtn) {

        txtAns.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                rbtn.setSelected(true);
                txtAns.setBackground(isColor);
            }

            @Override
            public void mousePressed(MouseEvent arg0) {

            }

            @Override
            public void mouseReleased(MouseEvent arg0) {

            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                txtAns.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                txtAns.setBackground(isColor);
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                txtAns.setBackground(Color.white);
                if (rbtn.isSelected()) {
                    txtAns.setBackground(isColor);
                }

            }
        });
    }

    public void eventAnswerLb(JLabel lb, JTextArea txtAns, Color isColor, JRadioButton rbtn) {

        lb.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                rbtn.setSelected(true);
                txtAns.setBackground(isColor);
            }

            @Override
            public void mousePressed(MouseEvent arg0) {

            }

            @Override
            public void mouseReleased(MouseEvent arg0) {

            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                txtAns.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                lb.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                txtAns.setBackground(isColor);
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                txtAns.setBackground(Color.white);
                if (rbtn.isSelected()) {
                    txtAns.setBackground(isColor);
                }

            }
        });
    }

    public void click(MyButton btn, ArrayList<MyButton> buttons) {
        for (MyButton button : buttons) {
            button.setBackground(new Color(172, 255, 175));
            button.addMouseListener(new MouseAdapter() {
                public void mouseExited(MouseEvent arg0) {
                    button.setBackground(new Color(172, 255, 175));
                }
            });
        }
        choice(buttons);
        btn.setBackground(new Color(0, 205, 102));
        btn.addMouseListener(new MouseAdapter() {
            public void mouseExited(MouseEvent arg0) {
                btn.setBackground(new Color(0, 205, 102));
            }
        });
    }

    public void choice(ArrayList<MyButton> buttons) {
        int i = 1;
        for (MyButton button : buttons) {
            if (rbA[i].isSelected() || rbB[i].isSelected() || rbC[i].isSelected() || rbD[i].isSelected()) {
                button.setBackground(new Color(255, 106, 106));
                button.addMouseListener(new MouseAdapter() {
                    public void mouseExited(MouseEvent arg0) {
                        button.setBackground(new Color(255, 106, 106));
                    }
                });
            }
            i++;
        }
    }

    public void createEast() {
        pEast.setBackground(new Color(255, 127, 80));
        pEast.setPreferredSize(new Dimension(3, 0));

        f.add(pEast, BorderLayout.EAST);
    }

    public void createSouth() {
        pSouth.setBackground(new Color(255, 127, 80));
        pSouth.setPreferredSize(new Dimension(0, 3));

        f.add(pSouth, BorderLayout.SOUTH);
    }

    public String getResult(ArrayList<CauHoiDTO> cauHois) {
        String Result = "";
        for (int i = 1; i <= num; i++) {
            if (rbA[i].isSelected()) {
                Result += "A";
                if (cauHois.get(i - 1).getDapanDung().equals("A")) {
                    soCauDung++;
                    dsDungSai += "1";
                } else {
                    dsDungSai += "0";
                }
            } else if (rbB[i].isSelected()) {
                Result += "B";
                if (cauHois.get(i - 1).getDapanDung().equals("B")) {
                    soCauDung++;
                    dsDungSai += "1";
                } else {
                    dsDungSai += "0";
                }
            } else if (rbC[i].isSelected()) {
                Result += "C";
                if (cauHois.get(i - 1).getDapanDung().equals("C")) {
                    soCauDung++;
                    dsDungSai += "1";
                } else {
                    dsDungSai += "0";
                }
            } else if (rbD[i].isSelected()) {
                Result += "D";
                if (cauHois.get(i - 1).getDapanDung().equals("D")) {
                    soCauDung++;
                    dsDungSai += "1";
                } else {
                    dsDungSai += "0";
                }
            } else {
                Result += "#";
                dsDungSai += "0";
            }
        }
        return Result;
    }

    public void nopBai(String maSV, String maDe, ArrayList<CauHoiDTO> cauHois) {
        String dsDapAn = getResult(cauHois);
        double diem = 10 * (1.0 * soCauDung / cauHois.size());
        String diemString = Double.toString(diem);
        BaiThiDTO baiThi = new BaiThiDTO(maSV, maDe, dsDapAn, dsDungSai, soCauDung, diemString, 1);
        BaiThiBUS busBT = new BaiThiBUS();
        BaiThiKyThiBUS busBTKt = new BaiThiKyThiBUS();
        if (!(busBT.them(baiThi)) && (busBTKt.add(baiThi, kythi.getMaKyThi()))) {
            new ErrorFrame("Kiểm Tra Kết Nối Để Nộp Bài");
        }
    }

    public void coutdown(ArrayList<CauHoiDTO> cauHois, DeThiDTO de, JFrame fLich) {
        //hàm đếm ngược thời gian
        try {
            ActionListener countDown = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    lbCoutDown.setText(phut + " : " + giay);
                    timeLeft -= 1000;
                    int min = Integer.parseInt(phut), second = Integer.parseInt(giay);
                    if (second == 0) {
                        second = 59;
                    } else {
                        second--;
                    }
                    if (second == 59) {
                        min--;
                    }
                    if (second > 9) {
                        giay = Integer.toString(second);
                    } else {
                        giay = "0" + Integer.toString(second);
                    }
                    if (min < 9) {
                        phut = "0" + Integer.toString(min);
                    } else {
                        phut = Integer.toString(min);
                    }
                    if (timeLeft <= 0) {
                        ((Timer) e.getSource()).stop();
                        fLich.setVisible(true);
                        f.setVisible(false);
                        new ErrorFrame("Hết Giờ Làm Bài");
                        nopBai(LoginBUS.isUser.getMaUser(), de.getMaDe(), cauHois);

                        DeThiBUS busDe = new DeThiBUS();
                        DeThiDTO dePhu = de;
                        dePhu.setTrangThai(0);
                        busDe.sua(dePhu, de.getMaDe());
                    }
                }
            };
            Timer timer = new Timer(1000, countDown);
            timer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
