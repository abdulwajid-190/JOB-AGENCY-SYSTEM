import java.util.ArrayList;

public class JobSeeker {
    private String name;
    private ArrayList<String> skills;
    private String educationBackground;
    private int experience;
    private boolean preferFullTime;
    private String preferredLocation;
    private boolean willingToRelocate;
    private String contactNumber;
    private String contactEmail;

    public JobSeeker(String name, ArrayList<String> skills, String educationBackground, int experience, 
                     boolean preferFullTime, String preferredLocation, boolean willingToRelocate,
                     String contactNumber, String contactEmail) {
        this.name = name;
        this.skills = skills;
        this.educationBackground = educationBackground;
        this.experience = experience;
        this.preferFullTime = preferFullTime;
        this.preferredLocation = preferredLocation;
        this.willingToRelocate = willingToRelocate;
        this.contactNumber = contactNumber;
        this.contactEmail = contactEmail;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getSkills() {
        return skills;
    }

    public String getEducationBackground() {
        return educationBackground;
    }

    public int getExperience() {
        return experience;
    }

    public boolean prefersFullTime() {
        return preferFullTime;
    }

    public String getPreferredLocation() {
        return preferredLocation;
    }

    public boolean isWillingToRelocate() {
        return willingToRelocate;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    @Override
    public String toString() {
        return "JobSeeker{" +
                "name='" + name + '\'' +
                ", skills=" + skills +
                ", educationBackground='" + educationBackground + '\'' +
                ", experience=" + experience +
                ", preferFullTime=" + preferFullTime +
                ", preferredLocation='" + preferredLocation + '\'' +
                ", willingToRelocate=" + willingToRelocate +
                ", contactNumber='" + contactNumber + '\'' +
                ", contactEmail='" + contactEmail + '\'' +
                '}';
    }
}
