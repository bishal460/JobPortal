package GUI;

import org.json.JSONObject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AddJobPanel extends JPanel {
    AddPanelInterface add;
    DefaultTableModel model;
    private static JButton back, addButton;
    private static JTextField jobname, location, salary, experience;
    private static JLabel title, date;
    String[] header = {"Currency","Rate"};
    private static JTable table;
    public AddJobPanel(LayoutManager layout, boolean isDoubleBuffered, JobPortalFrame add) {
        super(layout, isDoubleBuffered);
        back = new JButton();

        model = new DefaultTableModel();
        model.setColumnIdentifiers(header);

        table = new JTable();
        table.setModel(model);
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(800, 120, 400, 200);
        title = new JLabel();
        title.setBounds(800, 20, 400, 30);
        date = new JLabel();
        date.setBounds(800, 50, 400, 30);
        this.add(pane);
        try {
            addAPIDATA();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.add(title);
        this.add(date);

        back.setText("Back");
        back.setBounds(20,20,80,40);

        addButton = new JButton();
        addButton.setText("Insert Job");
        addButton.setBounds(220, 500, 140, 40);

        JLabel joblabel = new JLabel("Job name");
        joblabel.setBounds(220, 100, 80, 40);

        jobname = new JTextField();
        jobname.setBounds(300, 100, 140, 40);

        JLabel locationLabel = new JLabel("Location");
        locationLabel.setBounds(220, 200, 80, 40);


        location = new JTextField();
        location.setBounds(300, 200, 140, 40);

        JLabel salaryLabel = new JLabel("Salary");
        salaryLabel.setBounds(220, 300, 80, 40);
        salary = new JTextField();
        salary.setBounds(300, 300, 140, 40);

        JLabel experienceLabel = new JLabel("Experience");
        experienceLabel.setBounds(220, 400, 80, 40);
        experience = new JTextField();
        experience.setBounds(300, 400, 140, 40);

        this.add = add;
        this.add(back);
        this.add(addButton);
        this.add(jobname);
        this.add(location);
        this.add(salary);
        this.add(experience);
        this.add(joblabel);
        this.add(locationLabel);
        this.add(salaryLabel);
        this.add(experienceLabel);

        addListerner();
        backListener();
    }

    private void addAPIDATA() throws Exception {
        String url = "https://api.ratesapi.io/api/latest";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // optional default is GET
        con.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        JSONObject myResponse = new JSONObject(response.toString());
        title.setText("PUBLIC API : EUR CURRENCY RATE");
        date.setText("Date : "+myResponse.getString("date"));
        System.out.println(myResponse.getJSONObject("rates").getInt("GBP"));
        model.addRow(new String[]{"GBP",myResponse.getJSONObject("rates").get("GBP").toString()});
        model.addRow(new String[]{"HKD",myResponse.getJSONObject("rates").get("GBP").toString()});
        model.addRow(new String[]{"IDR",myResponse.getJSONObject("rates").get("GBP").toString()});
        model.addRow(new String[]{"ILS",myResponse.getJSONObject("rates").get("GBP").toString()});
        model.addRow(new String[]{"DKK",myResponse.getJSONObject("rates").get("GBP").toString()});
        model.addRow(new String[]{"INR",myResponse.getJSONObject("rates").get("GBP").toString()});
        model.addRow(new String[]{"CHF",myResponse.getJSONObject("rates").get("GBP").toString()});
        model.addRow(new String[]{"MXN",myResponse.getJSONObject("rates").get("GBP").toString()});

    }

    private void backListener() {
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                add.onBackAddPanel();
            }
        });
    }

    private void getfromAPI() throws Exception{

    }

    private void addListerner() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                add.onJobAdd(jobname.getText(), location.getText(), salary.getText(),experience.getText());
            }
        });
    }

    interface AddPanelInterface{
        void onJobAdd(String jobname, String location, String salary, String experience);
        void onBackAddPanel();
    }

}
