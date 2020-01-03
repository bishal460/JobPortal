package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class SignUpPanel extends JPanel {
    private static SignUPinterface sign;
    private JButton signupButton;
    private static JTextField username, password, email;
    public SignUpPanel(LayoutManager layout, boolean isDoubleBuffered, JobPortalFrame jobPortalFrame) {
        super(layout, isDoubleBuffered);
        sign = jobPortalFrame;
        JLabel hello = new JLabel("Create Your Account");
        hello.setBounds(480/2,20,280,30);
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
        username.setForeground(Color.WHITE);;

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

        JLabel emaillabel = new JLabel("Email");
        emaillabel.setBounds(480/2,260,80,30);
        emaillabel.setFont(new Font("Verdana",Font.PLAIN,15));
        emaillabel.setForeground(Color.WHITE);
        emaillabel.setBackground(Color.WHITE);

        email = new JTextField();
        email.setOpaque(false);
        email.setFont(new Font("Verdana",Font.PLAIN,14));
        email.setForeground(Color.WHITE);
        email.setBounds(480/2,290,140,30);


        signupButton = new JButton();
        signupButton.setText("Sign UP");
        signupButton.setBounds(480/2,350,120,30);
        signupButton.setFont(new Font("Verdana",Font.PLAIN,16));
        signupButton.setOpaque(false);
        signupButton.setContentAreaFilled(false);
        signupButton.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        signupButton.setForeground(Color.WHITE);
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

    interface SignUPinterface{
        void onSignup(String username, String password, String email);
    }
}
