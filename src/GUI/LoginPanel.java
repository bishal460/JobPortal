package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel {
    private final JButton loginButton, signupButton;
    private static JTextField username, password;
    public static LoginPanelInterface logI;
    public LoginPanel(LayoutManager layout, boolean isDoubleBuffered, JobPortalFrame jobPortalFrame) {
        super(layout, isDoubleBuffered);
        //this.setSize(1280,720);
        this.logI = (LoginPanelInterface) jobPortalFrame;
        JLabel hello = new JLabel("Login here");
        hello.setBounds(480/2,20,80,30);

        JLabel usernamelabel = new JLabel("Username");
        usernamelabel.setBounds(20,120,80,30);

        username = new JTextField();
        username.setBounds(120,120,120,30);

        JLabel passwordlabel = new JLabel("Password");
        passwordlabel.setBounds(20,220,80,30);

        password = new JPasswordField();
        password.setBounds(120,220,120,30);



        loginButton = new JButton();
        loginButton.setText("Log In");
        loginButton.setBounds(20,350,80,30);


        signupButton = new JButton();
        signupButton.setText("Sign Up");
        signupButton.setBounds(120,350,80,30);


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
