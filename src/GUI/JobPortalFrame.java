package GUI;
import Interface.Adder;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.swing.*;
import java.awt.*;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.ResultSet;

public class JobPortalFrame extends JFrame implements LoginPanel.LoginPanelInterface, SignUpPanel.SignUPinterface, DashboardPanel.DashboardPanelInterface, ViewJobPanel.ViewPanelInterface, AddJobPanel.AddPanelInterface, ViewMyAppliedJobPanel.ViewMyInterface{
    JPanel loginPanel, signuppanel, dashboardpanel, addjobpanel, viewjobpanel, viewmyjobpanel;
    private static String userCode = "";
    public JobPortalFrame(String title) throws HeadlessException {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(640, 480);
        //this.setResizable(false);
        loginPanel = new LoginPanel(null, false, this);
        signuppanel = new SignUpPanel(null, false, this);
        dashboardpanel = new DashboardPanel(null, false, this);
        viewmyjobpanel = new ViewMyAppliedJobPanel(null, false, this);
        addjobpanel = new AddJobPanel(null, false, this);
        viewjobpanel = new ViewJobPanel(null, false, this,viewmyjobpanel);
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
        try{
            Registry reg = LocateRegistry.getRegistry("127.0.0.1",2099);
            Adder ad = (Adder)reg.lookup("MyServer");
            if(ad.loginUser(username,password)){
                userCode = username;
                loginPanel.setVisible(false);
                this.setContentPane(dashboardpanel);
                dashboardpanel.setVisible(true);
                this.setSize(1280, 720);
            }else{
                JOptionPane.showMessageDialog(this,
                        "Wrong password or Username");
                return;
            }
        } catch (AccessException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }


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
        try{
            Registry reg = LocateRegistry.getRegistry("127.0.0.1",2099);
            Adder ad = (Adder)reg.lookup("MyServer");
            if(ad.signUp(username,password,email)){
                signuppanel.setVisible(false);
                JOptionPane.showMessageDialog(this,
                        "Account is created");
                this.setContentPane(loginPanel);
                loginPanel.setVisible(true);
            }
        } catch (AccessException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
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
        this.setContentPane(addjobpanel);
        addjobpanel.setVisible(true);
    }

    @Override
    public void viewJob() {
        dashboardpanel.setVisible(false);
        this.setContentPane(viewjobpanel);
        viewjobpanel.setVisible(true);
    }

    @Override
    public void viewMyJob() {
        dashboardpanel.setVisible(false);
        this.setContentPane(viewmyjobpanel);
        viewmyjobpanel.setVisible(true);
    }

    @Override
    public void logout() {
        dashboardpanel.setVisible(false);
        this.setSize(640, 480);
        this.setContentPane(loginPanel);
        loginPanel.setVisible(true);
    }

    @Override
    public ResultSet viewJobList() {
        try{
            System.out.println("start from ui");
            Registry reg = LocateRegistry.getRegistry("127.0.0.1",2099);
            Adder ad = (Adder)reg.lookup("MyServer");
            return ad.viewJob();
        }catch (AccessException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onApplyForJob(String[] data) {
        try{
            Registry reg = LocateRegistry.getRegistry("127.0.0.1",2099);
            Adder ad = (Adder)reg.lookup("MyServer");
            ad.addMyJob(data);
        }catch (Exception e){

        }
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
    public void onJobAdd(String jobname, String location, String salary, String experience) {
        if (jobname.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please provide job title");
            return;
        }
        if (location.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please provide location");
            return;
        }
        if (salary.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please provide salary");
            return;
        }
        if (experience.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please provide experience");
            return;
        }

        if (!salary.matches("[0-9]+")) {
            JOptionPane.showMessageDialog(this,
                    "Please provide salary in number");
            return;
    }
        if (!experience.matches("[0-9]+")) {
            JOptionPane.showMessageDialog(this,
                    "Please provide experience in number");
            return;
        }

        try{
            Registry reg = LocateRegistry.getRegistry("127.0.0.1",2099);
            Adder ad = (Adder)reg.lookup("MyServer");
            if(ad.addJob(jobname,location,Integer.parseInt(salary),Integer.parseInt(experience),userCode)){
                JOptionPane.showMessageDialog(this,
                        "Job is created");
            }
        } catch (AccessException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onBackAddPanel() {
        addjobpanel.setVisible(false);
        this.setContentPane(dashboardpanel);
        dashboardpanel.setVisible(true);
    }

    @Override
    public void backMYListener() {
        viewmyjobpanel.setVisible(false);
        this.setContentPane(dashboardpanel);
        dashboardpanel.setVisible(true);
    }
}
