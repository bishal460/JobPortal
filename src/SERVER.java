
import Interface.Adder;
import Interface.RMI_REMOTE;
import com.mysql.jdbc.Connection;
import GUI.DataBaseConnection;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

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
            Registry reg = LocateRegistry.createRegistry(2099);
            Adder var1 = new RMI_REMOTE(conn);
            reg.rebind("MyServer", var1);
            System.out.println("Server is ready");
        } catch (Exception var2) {
            System.out.println(var2.getMessage());
        }
    }
}
