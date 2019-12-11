package Database_Controller;

import DatabaseInterface.UserInterface;
import Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserController implements UserInterface {
    Connection cn;

    public UserController(Connection cn) {
        this.cn = cn;
    }

    @Override
    public ResultSet loginUser(User u) {
        try{
            String sql="SELECT * FROM USERS WHERE USERNAME=? AND PASSWORD=?";
            PreparedStatement ps=cn.prepareStatement(sql);
            ps.setString(1, u.getUsername());
            ps.setString(2, u.getPassword());
            ResultSet rs=ps.executeQuery();
            return rs;
        }
        catch(Exception e){}

        return null;
    }

    @Override
    public void signupUser(User u) {
        try{
            String sql="INSERT INTO USERS(USERNAME,PASSWORD) VALUES(?,?)";
            PreparedStatement ps=cn.prepareStatement(sql);
            ps.setString(1, u.getUsername());
            ps.setString(2, u.getPassword());
            ps.execute();
        }
        catch(SQLException e){

        }
    }
}
