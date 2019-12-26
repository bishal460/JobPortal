package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.plaf.ColorUIResource;
public class DashboardPanel extends JPanel {
    public static final ColorUIResource GRAY_100 = new ColorUIResource (245, 245, 245);
    private final JButton addButton, viewButton, viewMyJobButton, logout;
    private static DashboardPanelInterface dash;
    public DashboardPanel(LayoutManager layout, boolean isDoubleBuffered, JobPortalFrame jobPortalFrame) {
        super(layout, isDoubleBuffered);
        dash = jobPortalFrame;
        addButton = new JButton();
        viewButton = new JButton();
        viewMyJobButton = new JButton();
        logout = new JButton();
        addButton.setText("Add Job");
        viewMyJobButton.setText("View My Job");
        viewButton.setText("View Job");
        logout.setText("Log out");

        addButton.setBounds(1280/4,720/2-100,100,100);
        viewButton.setBounds(1280/4+250,720/2-100,100,100);
        viewMyJobButton.setBounds(1280/4+450,720/2-100,120,100);
        logout.setBounds(1280/4+650,720/2-100,100,100);


        this.add(addButton);
        this.add(viewButton);
        this.add(viewMyJobButton);
        this.add(logout);


        addButtonListener();
        viewButtonListener();
        viewMyJobButtonListener();
        logoutButtonListener();

    }

    private void addButtonListener() {
        this.addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dash.AddJob();
            }
        });
    }
    private void viewButtonListener(){
        this.viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dash.viewJob();
            }
        });
    }

    private void viewMyJobButtonListener(){
        this.viewMyJobButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dash.viewMyJob();
            }
        });
    }
    private void logoutButtonListener(){
        this.logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dash.logout();
            }
        });
    }

    interface DashboardPanelInterface{
        void AddJob();
        void viewJob();
        void viewMyJob();
        void logout();
    }
}
