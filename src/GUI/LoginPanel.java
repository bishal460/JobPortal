package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class LoginPanel extends JPanel {
    private final JButton loginButton, signupButton;
    private static JTextField username, password;
    public static LoginPanelInterface logI;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            Image image = ImageIO.read(new File("apple.jpg"));
            g.drawImage(image,0,0,getWidth(),getHeight(),this);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Could not find Image");
        }
    }

    public LoginPanel(LayoutManager layout, boolean isDoubleBuffered, JobPortalFrame jobPortalFrame) {
        super(layout, isDoubleBuffered);
        //this.setSize(1280,720);
        this.logI = (LoginPanelInterface) jobPortalFrame;
        JLabel hello = new JLabel("Login here");
        hello.setBounds(480/2,20,250,30);
        hello.setFont(new Font("Verdana",Font.BOLD,20));
        hello.setBackground(Color.WHITE);
        hello.setForeground(Color.WHITE);

        JLabel usernamelabel = new JLabel("Username");
        usernamelabel.setBounds(480/2,90,120,30);
        usernamelabel.setFont(new Font("Verdana",Font.PLAIN,15));
        usernamelabel.setForeground(Color.WHITE);
        usernamelabel.setBackground(Color.WHITE);

        username = new JTextField();
        username.setBounds(480/2,120,120,30);
        username.setOpaque(false);
        username.setFont(new Font("Verdana",Font.PLAIN,14));
        username.setForeground(Color.WHITE);
        //usernamelabel.setBackground(Color.WHITE);


        JLabel passwordlabel = new JLabel("Password");
        passwordlabel.setBounds(480/2,190,80,30);
        passwordlabel.setFont(new Font("Verdana",Font.PLAIN,15));
        passwordlabel.setForeground(Color.WHITE);
        passwordlabel.setBackground(Color.WHITE);

        password = new JPasswordField();
        password.setBounds(480/2,220,120,30);
        password.setOpaque(false);
        password.setFont(new Font("Verdana",Font.PLAIN,14));
        password.setForeground(Color.WHITE);



        loginButton = new JButton();
        loginButton.setText("Log In");
        loginButton.setBounds(480/2,280,120,30);
        loginButton.setFont(new Font("Verdana",Font.PLAIN,16));
        loginButton.setOpaque(false);
        loginButton.setContentAreaFilled(false);
        loginButton.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        loginButton.setForeground(Color.WHITE);

        signupButton = new JButton();
        signupButton.setText("Sign Up");
        signupButton.setBounds(480/2,320,120,30);
        signupButton.setFont(new Font("Verdana",Font.PLAIN,16));
        signupButton.setOpaque(false);
        signupButton.setContentAreaFilled(false);
        signupButton.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        signupButton.setForeground(Color.WHITE);

        loginListener();
        signupListener();
        this.add(usernamelabel);
        this.add(loginButton);
        this.add(username);
        this.add(password);
        this.add(passwordlabel);
        this.add(hello);
        this.add(signupButton);
    }

    private void signupListener() {
        this.signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                logI.SignUpButtonClicked();
            }
        });
    }

    private void loginListener() {
        this.loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                logI.LoginButtonClicked(username.getText(), password.getText());
            }
        });
        }

    interface LoginPanelInterface{
        void LoginButtonClicked(String username, String password);
        void SignUpButtonClicked();
    }
}
