import java.util.ArrayList;

public class Job {
    private int jobId;
    private String title;
    private String description;
    private ArrayList<String> requiredSkills;
    private String educationCriteria;
    private String location;
    private double salary;
    private String applicationDeadline;
    private boolean preferExperience;
    private int minExperience;
    private boolean fullTime;

    public Job(int jobId, String title, String description, ArrayList<String> requiredSkills) {
        this.jobId = jobId;
        this.title = title;
        this.description = description;
        this.requiredSkills = requiredSkills;
    }

    public int getJobId() {
        return jobId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<String> getRequiredSkills() {
        return requiredSkills;
    }

    public String getEducationCriteria() {
        return educationCriteria;
    }

    public void setEducationCriteria(String educationCriteria) {
        this.educationCriteria = educationCriteria;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getApplicationDeadline() {
        return applicationDeadline;
    }

    public void setApplicationDeadline(String applicationDeadline) {
        this.applicationDeadline = applicationDeadline;
    }

    public boolean isPreferExperience() {
        return preferExperience;
    }

    public void setPreferExperience(boolean preferExperience) {
        this.preferExperience = preferExperience;
    }

    public int getMinExperience() {
        return minExperience;
    }

    public void setMinExperience(int minExperience) {
        this.minExperience = minExperience;
    }

    public boolean isFullTime() {
        return fullTime;
    }

    public void setFullTime(boolean fullTime) {
        this.fullTime = fullTime;
    }

    @Override
    public String toString() {
        return "Job{" +
                "jobId=" + jobId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", requiredSkills=" + requiredSkills +
                ", educationCriteria='" + educationCriteria + '\'' +
                ", location='" + location + '\'' +
                ", salary=" + salary +
                ", applicationDeadline='" + applicationDeadline + '\'' +
                ", preferExperience=" + preferExperience +
                ", minExperience=" + minExperience +
                ", fullTime=" + fullTime +
                '}';
    }
}
