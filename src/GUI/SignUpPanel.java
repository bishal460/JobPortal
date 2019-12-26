package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpPanel extends JPanel {
    private static SignUPinterface sign;
    private JButton signupButton;
    private static JTextField username, password, email;
    public SignUpPanel(LayoutManager layout, boolean isDoubleBuffered, JobPortalFrame jobPortalFrame) {
        super(layout, isDoubleBuffered);
        sign = jobPortalFrame;
        JLabel hello = new JLabel("Create Your Account");
        hello.setBounds(480/2,20,80,30);

        JLabel usernamelabel = new JLabel("Username");
        usernamelabel.setBounds(20,120,80,30);

        username = new JTextField();
        username.setBounds(120,120,120,30);

        JLabel passwordlabel = new JLabel("Password");
        passwordlabel.setBounds(20,220,80,30);

        password = new JPasswordField();
        password.setBounds(120,220,120,30);

        JLabel emaillabel = new JLabel("Email");
        emaillabel.setBounds(20,320,80,30);

        email = new JTextField();
        email.setBounds(120,320,140,30);


        signupButton = new JButton();
        signupButton.setText("Sign UP");
        signupButton.setBounds(20,380,80,30);
        signUpListener();


        this.add(hello);
        this.add(signupButton);
        this.add(usernamelabel);
        this.add(username);
        this.add(passwordlabel);
        this.add(password);
        this.add(emaillabel);
        this.add(email);
    }

    private void signUpListener() {
        this.signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                sign.onSignup(username.getText(),password.getText(),email.getText());
            }
        });
    }

    interface SignUPinterface{
        void onSignup(String username, String password, String email);
    }
}
