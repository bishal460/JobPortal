package DatabaseInterface;

import Model.User;

import java.sql.ResultSet;

public interface UserInterface {
    ResultSet loginUser(User u);
    void signupUser(User u);
}
