package GUI_Exam;

/**
 *
 * @author chang
 */
import BUS_EXAM.LoginBUS;
import BUS_EXAM.UserBUS;
import DTO_EXAM.UserDTO;
import java.awt.*;
import java.awt.event.FocusListener;
import javax.swing.BorderFactory;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class LogInFrame {

    protected JFrame f;
    protected JLabel lbimg;
    private ArrayList<ArrayList<String>> data;
    private JTextField txtUsername = new JTextField();
    private JPasswordField txtPass = new JPasswordField();

    public LogInFrame() {
        f = null;
    }

    public void createLoginFrame() {
        f = new JFrame("LOG IN");
        JPanel pNorth = new JPanel();

        JLabel lbUser = new JLabel();
        JLabel lbPassword = new JLabel();
        JLabel lbTitle = new JLabel("LOG IN");
        lbimg = new JLabel();

        JButton btnLogin = new JButton("LOG IN");

        JPanel pFrame = new JPanel();

        f.setBounds(0, 0, 1200, 700);
        f.setPreferredSize(new Dimension(1200, 700));

        pFrame.setBounds(430, 200, 340, 300);
        pFrame.setBackground(new Color(0, 0, 0, 100));
        pFrame.setOpaque(true);
        pFrame.setPreferredSize(new Dimension(1200, 700));

        //---------------------------CENTER---------------------------//
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        f.setLocation((int) d.getWidth() / 2 - (int) f.getPreferredSize().getWidth() / 2,
                (int) d.getHeight() / 3 - (int) f.getPreferredSize().getHeight() / 3);
        //------------------------------------------------------------//
        pNorth.setLayout(new FlowLayout(2, 5, 3));
        pNorth.setPreferredSize(new Dimension(0, 30));
        pNorth.setBackground(new Color(255, 127, 80));

        MyButton btnMinimize = new MyButton("-");
        MyButton btnThoat = new MyButton("X", MyButton.EXIT);

        btnThoat.setPreferredSize(new Dimension(26, 24));
        btnMinimize.setPreferredSize(new Dimension(26, 24));

        btnMinimize.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                f.setState(Frame.ICONIFIED);
            }
        });
        btnThoat.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                System.exit(0);
            }

        });
        pNorth.add(btnMinimize);
        pNorth.add(btnThoat);

        ImageIcon img = new ImageIcon("src/img/login.jpg");
        lbimg.setBounds(0, 0, 1200, 700);
        lbimg.setIcon(img);
        lbimg.add(pFrame);

        pNorth.setBounds(0, 0, 1200, 30);
        lbTitle.setBounds(35, 35, 150, 50);
        lbTitle.setFont(new Font("Segoe UI", Font.BOLD, 30));
        lbTitle.setForeground(new Color(255, 127, 80));
        lbTitle.setOpaque(false);

        lbUser.setBounds(35, 110, 30, 30);
        ImageIcon user = new ImageIcon("src/img/user.png");
        lbUser.setIcon(user);
        pFrame.add(lbUser);

        lbPassword.setBounds(35, 160, 30, 30);
        ImageIcon lock = new ImageIcon("src/img/lock.png");
        lbPassword.setIcon(lock);
        pFrame.add(lbPassword);

        txtUsername.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtUsername.setForeground(Color.lightGray);
        txtUsername.setBounds(70, 110, 200, 30);
        txtUsername.setText("username");
        txtUsername.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
        txtUsername.setOpaque(true);

        txtPass.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtPass.setForeground(Color.lightGray);
        txtPass.setEchoChar((char) 0);
        txtPass.setText("password");
        txtPass.setBounds(70, 160, 200, 30);
        txtPass.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
        txtPass.setOpaque(true);

        btnLogin.setBounds(180, 220, 90, 30);
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnLogin.setForeground(Color.black);
        btnLogin.setBackground(new Color(255, 127, 80));

        txtUsername.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent arg0) {
                if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
                    txtPass.requestFocus();
                }
            }
        });
        txtPass.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent arg0) {
                if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
                    isLogIn();
                }
            }
        });

        //------------------------------------------------------------//
        txtUsername.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtUsername.getText().equals("username")) {
                    txtUsername.setText("");
                    txtUsername.setForeground(Color.black);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtUsername.getText().equals("")) {
                    txtUsername.setText("username");
                    txtUsername.setForeground(Color.lightGray);
                }
            }

        });

        //------------------------------------------------------------//
        txtPass.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                String s = new String(txtPass.getPassword());
                if (s.equals("password")) {
                    txtPass.setText("");
                    txtPass.setEchoChar('•');
                    txtPass.setForeground(Color.black);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                char[] input = txtPass.getPassword();
                String s = String.valueOf(input);
                if (s.equals("")) {
                    txtPass.setText("password");
                    txtPass.setEchoChar((char) 0);
                    txtPass.setForeground(Color.lightGray);
                }
            }

        });

        //-------------------------Button Exit-----------------------------//
        //------------------------------------------------------------//
        f.add(pNorth);
        pFrame.add(lbTitle);
        pFrame.add(btnLogin);
        pFrame.add(txtUsername);
        pFrame.add(txtPass);
        pFrame.setLayout(null);

        btnLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                isLogIn();
            }
        });
        f.add(lbimg);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(null);
        f.setUndecorated(true);

    }

    public void isLogIn() {
        LoginBUS login = new LoginBUS();
        String pass = new String(txtPass.getPassword());
        if (pass.equals("password") && txtUsername.getText().equals("username")) {
            new ErrorFrame("Vui Lòng Nhập Tài Khoản");
        } else if (txtUsername.getText().equals("username")) {
            new ErrorFrame("Vui Lòng Nhập Tài Khoản");
        } else if (pass.equals("password")) {
            new ErrorFrame("Vui Lòng Nhập Mật Khẩu");
        } else {
            data = login.isCorrect(txtUsername.getText(), pass);
            if (data.isEmpty()) {
                new ErrorFrame("Tài Khoản Đã Bị Khóa");
            } else if (data.get(1).size() > 1) {
                f.setVisible(false);
                UserFrame userFrame = new UserFrame();
                UserBUS busUser = new UserBUS();
                UserDTO User = busUser.getObject(LoginBUS.isUser.getMaUser());
                if (User.getMaChucVu().equals("CV01")) {
                    userFrame.createADFrame(data.get(0), data.get(1), data.get(2));//LỖI HÀM NÀY
                } else {
                    userFrame.createJFrame(data.get(0), data.get(1), data.get(2));
                }
            } else {
                txtPass.setText("password");
                txtPass.setEchoChar((char) 0);
                txtPass.setForeground(Color.lightGray);
                txtUsername.setText("");
                txtUsername.requestFocus();
                new ErrorFrame("Tài Khoản Hoặc Mật Khẩu Không Chính Xác");
            }
        }
    }

    public void setVisible(boolean b) {
        f.setVisible(b);
    }
}
