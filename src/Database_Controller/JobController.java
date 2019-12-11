package Database_Controller;

import DatabaseInterface.JobInterface;
import Model.Job;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JobController implements JobInterface {
    Connection conn;

    public JobController(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void addJob(Job j) {
        try{
            String sql="INSERT INTO JOB(job_category, job_vacancy, Employment_type, location, salary, deadline, education_level, experience, posted_by) VALUES(?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setString(1, j.getJob_category());
            ps.setString(2, j.getJob_vacancy());
            ps.setString(3, j.getEmployment_type());
            ps.setString(4, j.getLocation());
            ps.setString(5, j.getSalary());
            ps.setString(6, j.getDeadline());
            ps.setString(7, j.getEducation_level());
            ps.setString(8, j.getExperience());
            ps.setString(9, j.getPosted_by());
            ps.execute();
        }
        catch(SQLException e){

        }
    }

    @Override
    public List<Job> viewJob(Job j) {
        List<Job> list = null;
        try{
            String sql="SELECT * FROM STUDENT";
            PreparedStatement ps=conn.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Job job = new Job();
                job.setJob_category(rs.getString(2));
                job.setJob_vacancy(rs.getString(3));
                job.setEmployment_type(rs.getString(4));
                job.setLocation(rs.getString(5));
                job.setSalary(rs.getString(6));
                job.setDeadline(rs.getString(7));
                job.setEducation_level(rs.getString(8));
                job.setExperience(rs.getString(9));
                job.setPosted_by(rs.getString(10));
                list.add(job);
            }

            return list;
        }
        catch(SQLException e){

        }
        return null;
    }

    @Override
    public boolean updateJob(Job j) {
        try{
            String sql="UPDATE JOB SET job_category=?, job_vacancy=?, Employment_type=?, location=?, salary=?, deadline=?, education_level=?, experience=?, posted_by=?  WHERE id=?";
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setString(1, j.getJob_category());
            ps.setString(2, j.getJob_vacancy());
            ps.setString(3, j.getEmployment_type());
            ps.setString(4, j.getLocation());
            ps.setString(5, j.getSalary());
            ps.setString(6, j.getDeadline());
            ps.setString(7, j.getEducation_level());
            ps.setString(8, j.getExperience());
            ps.setString(9, j.getPosted_by());
            ps.setInt(10,j.getId());
            ps.executeUpdate();
            return true;
        }
        catch(SQLException e){
            return false;
        }
    }

    @Override
    public boolean deleteJob(Job j) {
        try{
            String sql="DELETE JOB SET job_category=?, job_vacancy=?, Employment_type=?, location=?, salary=?, deadline=?, education_level=?, experience=?, posted_by=?  WHERE id=?";
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setString(1, j.getJob_category());
            ps.setString(2, j.getJob_vacancy());
            ps.setString(3, j.getEmployment_type());
            ps.setString(4, j.getLocation());
            ps.setString(5, j.getSalary());
            ps.setString(6, j.getDeadline());
            ps.setString(7, j.getEducation_level());
            ps.setString(8, j.getExperience());
            ps.setString(9, j.getPosted_by());
            ps.setInt(10,j.getId());
            ps.executeUpdate();
            return true;
        }
        catch(SQLException e){
            return false;
        }
    }
}
