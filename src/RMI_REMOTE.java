import Database_Controller.UserController;
import Model.User;
import com.mysql.jdbc.Connection;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RMI_REMOTE extends UnicastRemoteObject implements Adder {
    private Connection conn;
    private UserController userController;
    public RMI_REMOTE(Connection conn) throws RemoteException {
        super();
        this.conn = conn;
        userController = new UserController(conn);
    }

    @Override
    public boolean loginUser(String username, String password) throws RemoteException {
        User user = new User(username, password);
        ResultSet rs = userController.loginUser(user);
        try {
            if(rs.next()){
                return true;
            }else{
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    @Override
    public boolean signUp(String username, String password) throws RemoteException {
        User user = new User(username, password);
        try{
            userController.signupUser(user);
        }catch(Exception ex){
            return false;
        }
        return true;
    }
}