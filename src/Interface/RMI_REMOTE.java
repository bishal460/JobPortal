package Interface;

import Database_Controller.JobController;
import Database_Controller.UserController;
import Interface.Adder;
import Model.Job;
import Model.User;
import com.mysql.jdbc.Connection;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RMI_REMOTE extends UnicastRemoteObject implements Adder {
    private Connection conn;
    private UserController userController;
    private JobController jobController;
    public RMI_REMOTE(Connection conn) throws RemoteException {
        super();
        this.conn = conn;
        userController = new UserController(conn);
        jobController = new JobController(conn);
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
    public boolean signUp(String username, String password,String email) throws RemoteException {
        User user = new User(username, password,email);
        try{
            userController.signupUser(user);
        }catch(Exception ex){
            return false;
        }
        return true;
    }

    @Override
    public boolean addJob(String jobname, String location, int salary, int experience, String user) throws RemoteException {
        Job job = new Job();
        job.setJob_category(jobname);
        job.setLocation(location);
        job.setSalary(Integer.toString(salary));
        job.setExperience(Integer.toString(experience));
        job.setPosted_by(user);
        try{
            jobController.addJob(job);
        }catch(Exception ex){
            return false;
        }
        return true;
    }

    @Override
    public ResultSet viewJob() throws RemoteException {
        return jobController.viewJob();
    }

    @Override
    public void addMyJob(String[] data) throws RemoteException {
        jobController.addMyjob(data);
    }


}