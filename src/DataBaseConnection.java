import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    public static Connection cn;
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/sms", "bishal", "password");
            return cn;
        } catch (Exception e) {
            e.printStackTrace();
    }
        return null;
    }

    public static void main(String[] args) {
        if(DataBaseConnection.getConnection() != null){
            System.out.println("Connected");
        }else{
            System.out.println("Not connected");
        }
    }
    }

