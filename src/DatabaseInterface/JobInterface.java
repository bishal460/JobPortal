package DatabaseInterface;

import Model.Job;

import java.util.List;

public interface JobInterface {

    public void addJob(Job j);
    public List<Job> viewJob(Job j);
    public boolean updateJob(Job j);
    public boolean deleteJob(Job j);
}
