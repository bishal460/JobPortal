package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ViewMyAppliedJobPanel extends JPanel implements ViewJobPanel.ViewPanelInterface.AddviewInteface {
    private static JTable table;
    private static JButton back;
    private ViewMyInterface viewMyInterface;
    private final static String[] headers= {"Job Name", "Location", "Salary", "Experience"};
    DefaultTableModel model;
    public ViewMyAppliedJobPanel(LayoutManager layout, boolean isDoubleBuffered,JobPortalFrame job) {
        super(layout, isDoubleBuffered);
        viewMyInterface = job;
        model = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        model.setColumnIdentifiers(headers);
        back = new JButton();
        back.setText("Back");
        back.setBounds(20,20,80,40);

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
        backListener();
        init();
    }
    public void init(){
        try{
            Connection conn = GUI.DataBaseConnection.getConnection();
            try{
                String sql="SELECT * FROM apply_job";
                PreparedStatement ps=conn.prepareStatement(sql);
                ResultSet rs=ps.executeQuery();
                while(rs.next()){
                    String[] row = {rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)};
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

    private void backListener() {
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                viewMyInterface.backMYListener();
            }
        });
    }

    @Override
    public void onAdd() {
        DefaultTableModel model = model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        model.setColumnIdentifiers(headers);
        table.setModel(model);

        try {
            Connection conn = GUI.DataBaseConnection.getConnection();
            try {
                String sql = "SELECT * FROM apply_job";
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String[] row = {rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)};
                    model.addRow(row);
                }
            } catch (Exception e) {
                System.out.println("Exceptiion");
                e.printStackTrace();
            }
        } catch (NullPointerException e) {

        }
    }

    interface  ViewMyInterface{
       void backMYListener();
    }
}
