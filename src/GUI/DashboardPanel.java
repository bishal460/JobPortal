package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.plaf.ColorUIResource;
public class DashboardPanel extends JPanel {
    public static final ColorUIResource GRAY_100 = new ColorUIResource (245, 245, 245);
    private final JButton addButton, viewButton, viewMyJobButton, logout;
    private static DashboardPanelInterface dash;
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
            g.setColor(Color.WHITE);
            g.fillRect(0,0,getWidth(),getHeight());
            g.setColor(new Color(50,52,102));
            g.fillRect(0,0,150,getHeight());

            g.setColor(Color.BLACK);
            g.drawLine(440,80,440,400);
            g.drawLine(440,400,440+500,400);
            g.drawString("1",420,400-30);
            g.drawString("2",420,400-60);
            g.drawString("3",420,400-90);
            g.drawString("4",420,400-120);
            g.drawString("5",420,400-150);
            g.drawString("6",420,400-180);
            g.drawString("7",420,400-210);
            g.drawString("8",420,400-240);
            g.drawString("9",420,400-270);
            g.drawString("10",420,400-300);

            drawJob(g);
    }

    public void drawJob(Graphics g){
//        g.drawString("Programmer",440+30,420);
//        g.drawString("DataScientist",440+130,420);
//        g.setColor(Color.RED);
//        g.fillRect(470,400-60,70,60);

        java.util.List<String> names = new ArrayList<>();
        List<Integer> frequency = new ArrayList<>();
        Connection conn = DataBaseConnection.getConnection();
        try {
            String sql = "SELECT * FROM apply_job";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                for(int i = 0; i <names.size(); i++){
                    if(names.get(i).equals(rs.getString(3))){
                        frequency.set(i,frequency.get(i)+1);
                    }
                }
                if(!names.contains(rs.getString(3))){
                    names.add(rs.getString(3));
                    frequency.add(1);
                }

            }
        } catch (Exception e) {
            System.out.println("Exceptiion");
            e.printStackTrace();
        }
        int job = 30;
        for(int i = 0; i<names.size(); i++){
            g.setColor(Color.MAGENTA);
            g.drawString(names.get(i),440+job,420);
            g.fillRect(440+job,400-frequency.get(i)*30,70,frequency.get(i)*30);
            job += 100;

        }
    }



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

        addButton.setBounds(10,20,100,30);
        addButton.setFont(new Font("Verdana",Font.PLAIN,16));
        addButton.setOpaque(false);
        addButton.setContentAreaFilled(false);
        addButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        addButton.setForeground(Color.WHITE);

        JLabel label1 = new JLabel("DATA VISUALISATION");
        label1.setBounds(1280/2-100,20,300,30);
        label1.setFont(new Font("Verdana",Font.BOLD,20));
        label1.setForeground(new Color(50,52,102));
        label1.setBackground(new Color(50,52,102));
        this.add(label1);


        viewButton.setBounds(10,60,100,30);
        viewButton.setFont(new Font("Verdana",Font.PLAIN,16));
        viewButton.setOpaque(false);
        viewButton.setContentAreaFilled(false);
        viewButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        viewButton.setForeground(Color.WHITE);

        viewMyJobButton.setBounds(10,100,120,30);
        viewMyJobButton.setFont(new Font("Verdana",Font.PLAIN,16));
        viewMyJobButton.setOpaque(false);
        viewMyJobButton.setContentAreaFilled(false);
        viewMyJobButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        viewMyJobButton.setForeground(Color.WHITE);

        logout.setBounds(10,160,100,30);
        logout.setFont(new Font("Verdana",Font.PLAIN,16));
        logout.setOpaque(false);
        logout.setContentAreaFilled(false);
        logout.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        logout.setForeground(Color.WHITE);

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
