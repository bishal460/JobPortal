package GUI;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.swing.*;
import java.awt.*;

public class JobPortalFrame extends JFrame implements LoginPanel.LoginPanelInterface, SignUpPanel.SignUPinterface, DashboardPanel.DashboardPanelInterface, ViewJobPanel.ViewPanelInterface, AddJobPanel.AddPanelInterface {
    JPanel loginPanel, signuppanel, dashboardpanel, addjobpanel, viewjobpanel, viewmyjobpanel;

    public JobPortalFrame(String title) throws HeadlessException {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1280, 720);
        loginPanel = new LoginPanel(null, false, this);
        signuppanel = new SignUpPanel(null, false, this);
        dashboardpanel = new DashboardPanel(null, false, this);
        this.setContentPane(loginPanel);
    }

    public static void main(String[] args) {
        JFrame job = new JobPortalFrame("Job Portal");
        job.setVisible(true);
    }

    @Override
    public void LoginButtonClicked(String username, String password) {
        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please provide username");
            return;
        }
        if (password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please provide password");
            return;
        }
        this.setSize(1280, 720);
    }

    @Override
    public void SignUpButtonClicked() {
        loginPanel.setVisible(false);
        this.setContentPane(signuppanel);
    }

    @Override
    public void onSignup(String username, String password, String email) {
        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please provide username");
            return;
        }
        if (password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please provide password");
            return;
        }
        if (email.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please provide email");
            return;
        }
        if (!(isValidEmailAddress(email))) {
            JOptionPane.showMessageDialog(this,
                    "Email address is incorrect");
            return;
        }
        System.out.println(username + "\n" + password + "\n" + email);
    }

    public static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

    @Override
    public void AddJob() {
        dashboardpanel.setVisible(false);
    }

    @Override
    public void viewJob() {
        dashboardpanel.setVisible(false);
        viewjobpanel = new ViewJobPanel(null, false, this);
        this.setContentPane(viewjobpanel);
    }

    @Override
    public void viewMyJob() {
        dashboardpanel.setVisible(false);
    }

    @Override
    public void logout() {
        dashboardpanel.setVisible(false);
        this.setSize(640, 480);
        this.setContentPane(loginPanel);
    }

    @Override
    public void onApplyForJob() {

    }

    @Override
    public boolean chooseOption() {
        int i = JOptionPane.showConfirmDialog(this, "Do you want apply for this job", "Choose option", JOptionPane.YES_NO_OPTION);
        if (i == 0)
            return true;
        return  false;
    }

    @Override
    public void onBackViewPanel() {
        viewjobpanel.setVisible(false);
        this.setContentPane(dashboardpanel);
        dashboardpanel.setVisible(true);
    }

    @Override
    public void onJobAdd() {

    }
}
