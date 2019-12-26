package Interface;

import Model.Job;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.util.List;

public interface Adder extends Remote {
    public boolean loginUser(String username, String password) throws RemoteException;
    public boolean signUp(String username, String password,String email) throws RemoteException;
    public boolean addJob(String jobname, String location,int salary, int experience, String user) throws RemoteException;
    public ResultSet viewJob() throws RemoteException;
    public void addMyJob(String[] data) throws RemoteException;

}
