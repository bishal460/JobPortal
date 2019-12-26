
import com.mysql.jdbc.Connection;

import java.rmi.Naming;

public class SERVER {
    public static void main(String[] args) {
        try {
            Connection conn = DataBaseConnection.getConnection();
            if(conn != null){
                System.out.println("Hello from the database"
                );
            }else{
                System.out.println("No connection");
            }
            Adder var1 = new RMI_REMOTE(conn);
            Naming.rebind("//localhost/MyServer", var1);
            System.out.println("Server is ready");
        } catch (Exception var2) {
            System.out.println(var2.getMessage());
        }
    }
}
