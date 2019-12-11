import com.mysql.jdbc.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    public static Connection cn;
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sms", "root", "");
            return cn;
        } catch (ClassNotFoundException | SQLException e) {
        }
        return null;
    }
    }

