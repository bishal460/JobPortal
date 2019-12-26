package Database_Controller;

import DatabaseInterface.JobInterface;
import Model.Job;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JobController implements JobInterface {
    Connection conn;

    public JobController(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void addJob(Job j) {
        try{
            String sql="INSERT INTO JOB(job_category,location,salary,experience,posted_by) VALUES(?,?,?,?,?)";
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setString(1, j.getJob_category());
            ps.setString(2, j.getLocation());
            ps.setString(3, j.getSalary());
            ps.setString(4, j.getExperience());
            ps.setString(5, j.getPosted_by());
            ps.execute();
        }
        catch(SQLException e){

        }
    }

    @Override
    public ResultSet viewJob() {
        ResultSet rs = null;
        try{
            System.out.println("Start to search");
            String sql="SELECT * FROM job";
            PreparedStatement ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            return rs;
        }
        catch(Exception e){
            System.out.println("Exceptiion");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addMyjob(String[] data) {
        try{
            String sql="INSERT INTO apply_job(userid,job_title,location,salary,experience) VALUES(?,?,?,?,?)";
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setString(1,data[0]);
            ps.setString(2, data[1]);
            ps.setString(3, data[2]);
            ps.setString(4, data[3]);
            ps.setString(5, data[4]);
            ps.execute();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }


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
