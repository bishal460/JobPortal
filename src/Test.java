import GUI.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
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

        if(names.isEmpty() && frequency.isEmpty()){
            System.out.println("Empty");
        }else{
            for(String x : names){
                System.out.println(x);
            }
            System.out.println("\n");
            for(int y : frequency){
                System.out.println(y);
            }
        }
    }
}
