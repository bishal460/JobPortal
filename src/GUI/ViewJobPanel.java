package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ViewJobPanel extends JPanel {
    ViewPanelInterface view;
    private final static String[] headers= {"Job Name", "Position", "Salary", "Experience"};
    private static JTable table;
    private static JButton back;
    DefaultTableModel model;
    public ViewJobPanel(LayoutManager layout, boolean isDoubleBuffered, JobPortalFrame view) {
        super(layout, isDoubleBuffered);
        this.view = view;
        back = new JButton();
        back.setText("Back");
        back.setBounds(20,20,80,40);
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

    private void tableListener() {
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if(view.chooseOption()){
                int i = table.getSelectedRow();
                String name = (model.getValueAt(i,0)).toString();


//                //to delete the row
//                for(int j = 0; j <= table.getRowCount(); j++){
//                    model.removeRow(j);
//                }

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
        String[] row = {"Programmer","Senior","1000","2"};
        this.model.addRow(row);
    }

    interface ViewPanelInterface{
    void onApplyForJob();
    boolean chooseOption();
    void onBackViewPanel();
}
}
