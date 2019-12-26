package GUI;

import Model.Job;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ViewJobPanel extends JPanel {
    ViewPanelInterface view;
    private final static String[] headers= {"Job Name", "Location", "Salary", "Experience","Posted By"};
    private static JTable table;
    private static JButton back;
    ViewPanelInterface.AddviewInteface onAdd;
    DefaultTableModel model;
    public ViewJobPanel(LayoutManager layout, boolean isDoubleBuffered, JobPortalFrame view, JPanel viewmyjobpanel) {
        super(layout, isDoubleBuffered);
        this.view = view;
        back = new JButton();
        back.setText("Back");
        back.setBounds(20,20,80,40);
        onAdd = (ViewPanelInterface.AddviewInteface) viewmyjobpanel;
        model = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        model.setColumnIdentifiers(headers);
        table = new JTable();
        table.setModel(model);

        table.setBackground(Color.LIGHT_GRAY);
        table.setForeground(Color.black);
        Font font = new Font("",1,22);
        table.setFont(font);
        table.setRowHeight(30);

        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(200, 100, 880, 200);
        this.add(pane);
        this.add(back);
        init();
        tableListener();
        backListener();
    }

    private void backListener() {
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                view.onBackViewPanel();
            }
        });
    }


    public void addMyjob(String[] data,Connection conn) {
        try{
            String sql="INSERT INTO apply_job(userid,job_title,location,salary,experience) VALUES(?,?,?,?,?)";
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setString(1,data[4]);
            ps.setString(2, data[0]);
            ps.setString(3, data[1]);
            ps.setString(4, data[2]);
            ps.setString(5, data[3]);
            ps.execute();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    private void tableListener() {
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if(view.chooseOption()){
                int i = table.getSelectedRow();
                String jobname = (model.getValueAt(i,0)).toString();
                String Location = (model.getValueAt(i,1)).toString();
                String Salary = (model.getValueAt(i,2)).toString();
                String Experience = (model.getValueAt(i,3)).toString();
                String PostedBy = (model.getValueAt(i,4)).toString();
                String[] data = {jobname,Location,Salary,Experience,PostedBy};
                addMyjob(data,DataBaseConnection.getConnection());
                model.removeRow(i);
                onAdd.onAdd();
                }
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        });
    }

    private void init() {
        try{
            Connection conn = GUI.DataBaseConnection.getConnection();
            try{
                System.out.println("Start to search");
                String sql="SELECT * FROM job";
                PreparedStatement ps=conn.prepareStatement(sql);
                ResultSet rs=ps.executeQuery();
                while(rs.next()){
                    String[] row = {rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)};
                    model.addRow(row);
                }
            }
            catch(Exception e){
                System.out.println("Exceptiion");
                e.printStackTrace();
            }
        }
        catch (NullPointerException e){

        }
    }

    interface ViewPanelInterface{
    ResultSet viewJobList();
    void onApplyForJob(String[] data);
    boolean chooseOption();
    void onBackViewPanel();
        interface AddviewInteface{
            void onAdd();
        }
}
}
