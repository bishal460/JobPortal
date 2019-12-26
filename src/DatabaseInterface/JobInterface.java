package DatabaseInterface;

import Model.Job;

import java.sql.ResultSet;

public interface JobInterface {

    public void addJob(Job j);
    public ResultSet viewJob();
    public void addMyjob(String[] data);
}
