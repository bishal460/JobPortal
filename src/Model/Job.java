package Model;

public class Job {
    private int id;
    private String job_category, job_vacancy, Employment_type, location, salary, deadline, education_level, experience, posted_by;

    public Job(String job_category, String job_vacancy, String employment_type, String location, String salary, String deadline, String education_level, String experience, String posted_by) {
        this.job_category = job_category;
        this.job_vacancy = job_vacancy;
        Employment_type = employment_type;
        this.location = location;
        this.salary = salary;
        this.deadline = deadline;
        this.education_level = education_level;
        this.experience = experience;
        this.posted_by = posted_by;
    }
    public Job(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJob_category() {
        return job_category;
    }

    public void setJob_category(String job_category) {
        this.job_category = job_category;
    }

    public String getJob_vacancy() {
        return job_vacancy;
    }

    public void setJob_vacancy(String job_vacancy) {
        this.job_vacancy = job_vacancy;
    }

    public String getEmployment_type() {
        return Employment_type;
    }

    public void setEmployment_type(String employment_type) {
        Employment_type = employment_type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getEducation_level() {
        return education_level;
    }

    public void setEducation_level(String education_level) {
        this.education_level = education_level;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getPosted_by() {
        return posted_by;
    }

    public void setPosted_by(String posted_by) {
        this.posted_by = posted_by;
    }
}
