import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.io.InputStreamReader;


public class JobAgencySystem {
    private ArrayList<Job> jobs;
    private ArrayList<JobSeeker> jobSeekers;
    private List<JobApplication> jobApplications;
    private static final String JOBS_FILE = "jobs.txt";
    private static final String JOB_SEEKERS_FILE = "job_seekers.txt";

    public JobAgencySystem() {
        jobs = new ArrayList<>();
        jobSeekers = new ArrayList<>();
        jobApplications = new ArrayList<>();
        loadJobsFromFile();
        loadJobSeekersFromFile();
    }

    public void menu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n╔══════════════════════════════════╗");
            System.out.println("║         JOB AGENCY SYSTEM        ║");
            System.out.println("╠══════════════════════════════════╣");
            System.out.println("║ 1. Add Job                       ║");
            System.out.println("║ 2. Add Job Seeker                ║");
            System.out.println("║ 3. View All Jobs                 ║");
            System.out.println("║ 4. View All Job Seekers          ║");
            System.out.println("║ 5. Match Jobs with Job Seekers   ║");
            System.out.println("║ 6. Apply for Matched Jobs        ║");
            System.out.println("║ 7. Exit                          ║");
            System.out.println("╚══════════════════════════════════╝");
            System.out.print("\nEnter your choice: ");

    
            int choice = readIntInput();
    
            switch (choice) {
                case 1:
                    addJob();
                    break;
                case 2:
                    addJobSeeker();
                    break;
                case 3:
                    displayAllJobs();
                    break;
                case 4:
                    displayAllJobSeekers();
                    break;
                case 5:
                    matchJobsWithSeekers();
                    break;
                case 6:
                    applyForMatchedJobs();
                    break;
                case 7:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    
        // After menu loop exits, save data to files
        saveJobsToFile();
        saveJobSeekersToFile();
    }

    private void addJob() {
        System.out.println("\nAdding a New Job:");
        System.out.print("Enter Job ID: ");
        int jobId = readIntInput();
        System.out.print("Enter Job Title: ");
        String title = readStringInput();
        System.out.print("Enter Job Description: ");
        String description = readStringInput();
        
        // Additional fields
        System.out.print("Enter Educational Criteria: ");
        String educationCriteria = readStringInput();
        System.out.print("Enter Required Skills (comma-separated): ");
        String[] skillsArr = readStringInput().split(",");
        ArrayList<String> requiredSkills = new ArrayList<>();
        for (String skill : skillsArr) {
            requiredSkills.add(skill.trim());
        }
        System.out.print("Enter Job Location: ");
        String location = readStringInput();
        System.out.print("Enter Salary: ");
        double salary = readDoubleInput();
        System.out.print("Enter Application Deadline (YYYY-MM-DD): ");
        String deadline = readStringInput();
        System.out.print("Does the Company Prefer Experienced Job Seekers? (yes/no): ");
        String preferExperienceStr = readStringInput();
        boolean preferExperience = preferExperienceStr.equalsIgnoreCase("yes");
        int minExperience = 0;
        if (preferExperience) {
            System.out.print("Enter Minimum Required Experience (in years): ");
            minExperience = readIntInput();
        }
        System.out.print("Is the Job Full-time or Part-time? (full-time/part-time): ");
        String jobTypeStr = readStringInput();
        boolean fullTime = jobTypeStr.equalsIgnoreCase("full-time");
        
        // Create the job object with all the provided information
        Job job = new Job(jobId, title, description, requiredSkills);
        job.setEducationCriteria(educationCriteria);
        job.setLocation(location);
        job.setSalary(salary);
        job.setApplicationDeadline(deadline);
        job.setPreferExperience(preferExperience);
        job.setMinExperience(minExperience);
        job.setFullTime(fullTime);
        
        jobs.add(job);
        System.out.println("Job added successfully.");
    }
    
    

    private void addJobSeeker() {
        System.out.println("\nAdding a New Job Seeker:");
        System.out.print("Enter Job Seeker Name: ");
        String name = readStringInput();
        System.out.print("Enter Skills (comma-separated): ");
        String[] skillsArr = readStringInput().split(",");
        ArrayList<String> skills = new ArrayList<>();
        for (String skill : skillsArr) {
            skills.add(skill.trim());
        }
        System.out.print("Enter Educational Background: ");
        String educationBackground = readStringInput();
        System.out.print("Enter Work Experience (years): ");
        int experience = readIntInput();
        System.out.print("Enter Preferred Job Type (full-time/part-time): ");
        String jobTypeStr = readStringInput();
        boolean preferFullTime = jobTypeStr.equalsIgnoreCase("full-time");
        System.out.print("Enter Preferred Job Location: ");
        String preferredLocation = readStringInput();
        System.out.print("Are you Willing to Relocate? (yes/no): ");
        boolean willingToRelocate = readBooleanInput();
        System.out.print("Enter Contact Number: ");
        String contactNumber = readStringInput();
        System.out.print("Enter Contact Email: ");
        String contactEmail = readStringInput();
        
        jobSeekers.add(new JobSeeker(name, skills, educationBackground, experience, preferFullTime,
                                      preferredLocation, willingToRelocate, contactNumber, contactEmail));
        System.out.println("Job seeker added successfully.");
    }
    

    private void displayAllJobs() {
        System.out.println("\nAll Available Jobs:");
        if (jobs.isEmpty()) {
            System.out.println("No jobs are currently available.");
        } else {
            for (Job job : jobs) {
                System.out.println("───────────────────────────────────────────");
                System.out.println("Job Title: " + job.getTitle());
                System.out.println("Job Description: " + job.getDescription());
                System.out.println("Required Skills: " + String.join(", ", job.getRequiredSkills()));
                System.out.println("Education Criteria: " + job.getEducationCriteria());
                System.out.println("Location: " + job.getLocation());
                System.out.println("Salary: $" + job.getSalary());
                System.out.println("Application Deadline: " + job.getApplicationDeadline());
                System.out.println("Prefer Experienced Job Seekers: " + (job.isPreferExperience() ? "Yes" : "No"));
                if (job.isPreferExperience()) {
                    System.out.println("Minimum Experience Required: " + job.getMinExperience() + " years");
                }
                System.out.println("Job Type: " + (job.isFullTime() ? "Full-time" : "Part-time"));
            }
        }
        System.out.println("───────────────────────────────────────────");
    }
    
    private void displayAllJobSeekers() {
        System.out.println("\nAll Registered Job Seekers:");
        if (jobSeekers.isEmpty()) {
            System.out.println("No job seekers are currently registered.");
        } else {
            for (JobSeeker jobSeeker : jobSeekers) {
                System.out.println("───────────────────────────────────────────");
                System.out.println("Job Seeker Name: " + jobSeeker.getName());
                System.out.println("Skills: " + String.join(", ", jobSeeker.getSkills()));
                System.out.println("Education Background: " + jobSeeker.getEducationBackground());
                System.out.println("Work Experience: " + jobSeeker.getExperience() + " years");
                System.out.println("Preferred Job Type: " + (jobSeeker.prefersFullTime() ? "Full-time" : "Part-time"));
                System.out.println("Preferred Job Location: " + jobSeeker.getPreferredLocation());
                System.out.println("Willing to Relocate: " + (jobSeeker.isWillingToRelocate() ? "Yes" : "No"));
                System.out.println("Contact Number: " + jobSeeker.getContactNumber());
                System.out.println("Contact Email: " + jobSeeker.getContactEmail());
            }
        }
        System.out.println("───────────────────────────────────────────");
    }
    
    
    private void matchJobsWithSeekers() {
        System.out.println("Matching Jobs with Job Seekers:");
        for (Job job : jobs) {
            System.out.println("Job Title: " + job.getTitle());
            System.out.println("|||||||||| MATCHING JOB SEEKER ||||||||||");
            
            List<JobSeeker> matchedJobSeekers = jobSeekers.stream()
                    .filter(jobSeeker -> matchesCriteria(jobSeeker, job))
                    .collect(Collectors.toList());
            
            if (matchedJobSeekers.isEmpty()) {
                System.out.println("No matching job seekers found.");
            } else {
                for (JobSeeker jobSeeker : matchedJobSeekers) {
                    System.out.println("Matched Job Seeker: " + jobSeeker.getName());
                    System.out.println("Contact Number: " + jobSeeker.getContactNumber());
                    System.out.println("Contact Email: " + jobSeeker.getContactEmail());
                }
            }
            System.out.println();
        }
    }
    
    
    private boolean matchesCriteria(JobSeeker jobSeeker, Job job) {
        // Check if job seeker's skills match required skills of the job
        boolean skillsMatch = jobSeeker.getSkills().containsAll(job.getRequiredSkills());
        
        // Check if job seeker's educational background matches job's education criteria
        boolean educationMatch = jobSeeker.getEducationBackground().equalsIgnoreCase(job.getEducationCriteria());
        
        // Check if job seeker's experience meets job's minimum experience requirement
        boolean experienceMatch = jobSeeker.getExperience() >= job.getMinExperience();
        
        // Check if job seeker's preferred job type matches job's full-time/part-time requirement
        boolean jobTypeMatch = jobSeeker.prefersFullTime() == job.isFullTime();
        
        // Check if job seeker's preferred location matches job's location
        boolean locationMatch = jobSeeker.getPreferredLocation().equalsIgnoreCase(job.getLocation());
        
        // Return true if all criteria match, otherwise false
        return skillsMatch && educationMatch && experienceMatch && jobTypeMatch && locationMatch;
    }
    
    
    private void applyForMatchedJobs() {
        System.out.println("\nApplying for Matched Jobs:");
        for (Job job : jobs) {
            List<JobSeeker> matchedJobSeekers = getMatchedJobSeekers(job);
            if (!matchedJobSeekers.isEmpty()) {
                System.out.println("\nJob Title: " + job.getTitle());
                System.out.println("Matched Job Seekers:");
                int index = 1;
                for (JobSeeker jobSeeker : matchedJobSeekers) {
                    System.out.println(index + ". " + jobSeeker.getName() + " (Email: " + jobSeeker.getContactEmail() + ")");
                    index++;
                }
                System.out.print("\nEnter the number corresponding to the job seeker you want to apply to (0 to skip): ");
                int selectedIndex = readIntInput();
                if (selectedIndex > 0 && selectedIndex <= matchedJobSeekers.size()) {
                    JobSeeker selectedJobSeeker = matchedJobSeekers.get(selectedIndex - 1);
                    jobApplications.add(new JobApplication(job, selectedJobSeeker));
                    System.out.println("Application submitted successfully.");
                } else if (selectedIndex != 0) {
                    System.out.println("Invalid selection. No application submitted.");
                }
            }
        }
    }
    

    private List<JobSeeker> getMatchedJobSeekers(Job job) {
        return jobSeekers.stream()
                .filter(jobSeeker -> matchesCriteria(jobSeeker, job))
                .collect(Collectors.toList());
    }
    

    private int readIntInput() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String input = reader.readLine();
            return Integer.parseInt(input);
        } catch (IOException | NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            return -1; // Return a sentinel value to indicate error
        }
    }
    
    
    private String readStringInput() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            return reader.readLine();
        } catch (IOException e) {
            System.out.println("Error reading input.");
            return ""; // Return an empty string as fallback
        }
    }
    
    private double readDoubleInput() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String input = reader.readLine();
            return Double.parseDouble(input);
        } catch (IOException | NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            return readDoubleInput(); // Retry if input is invalid
        }
    }

    private boolean readBooleanInput() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String input = reader.readLine().toLowerCase();
            return input.equals("yes") || input.equals("y");
        } catch (IOException e) {
            System.out.println("Error reading input. Assuming 'no'.");
            return false;
        }
    }

    private void loadJobsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(JOBS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 11) { // Check if the line has at least 11 elements
                    int jobId = Integer.parseInt(parts[0]);
                    String title = parts[1];
                    String description = parts[2];
                    String educationCriteria = parts[3];
                    String[] requiredSkills = parts[4].split(";");
                    ArrayList<String> skillsList = new ArrayList<>();
                    for (String skill : requiredSkills) {
                        skillsList.add(skill.trim());
                    }
                    String location = parts[5];
                    double salary = Double.parseDouble(parts[6]);
                    String applicationDeadline = parts[7];
                    boolean preferExperience = Boolean.parseBoolean(parts[8]);
                    int minExperience = Integer.parseInt(parts[9]);
                    boolean fullTime = Boolean.parseBoolean(parts[10]);
    
                    Job job = new Job(jobId, title, description, skillsList);
                    job.setEducationCriteria(educationCriteria);
                    job.setLocation(location);
                    job.setSalary(salary);
                    job.setApplicationDeadline(applicationDeadline);
                    job.setPreferExperience(preferExperience);
                    job.setMinExperience(minExperience);
                    job.setFullTime(fullTime);
    
                    jobs.add(job);
                } else {
                    System.out.println("Invalid input line: " + line);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error loading jobs from file: " + e.getMessage());
        }
    }
    
    

    private void loadJobSeekersFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(JOB_SEEKERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0];
                String[] skillsArr = parts[1].split(";");
                ArrayList<String> skillsList = new ArrayList<>();
                for (String skill : skillsArr) {
                    skillsList.add(skill.trim());
                }
                String educationBackground = parts[2];
                int experience = Integer.parseInt(parts[3]);
                boolean preferFullTime = parts[4].equalsIgnoreCase("full-time");
                String preferredLocation = parts[5];
                boolean willingToRelocate = Boolean.parseBoolean(parts[6]);
                String contactNumber = parts[7];
                String contactEmail = parts[8];
                
                jobSeekers.add(new JobSeeker(name, skillsList, educationBackground, experience,
                        preferFullTime, preferredLocation, willingToRelocate, contactNumber, contactEmail));
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error loading job seekers from file: " + e.getMessage());
        }
    }

    private void saveJobsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(JOBS_FILE))) {
            for (Job job : jobs) {
                writer.write(String.format("%d,%s,%s,%s,%s,%s,%.2f,%s,%b,%d,%b\n",
                        job.getJobId(),
                        job.getTitle(),
                        job.getDescription(),
                        job.getEducationCriteria(),
                        String.join(";", job.getRequiredSkills()),
                        job.getLocation(),
                        job.getSalary(),
                        job.getApplicationDeadline(),
                        job.isPreferExperience(),
                        job.getMinExperience(),
                        job.isFullTime()
                ));
            }
        } catch (IOException e) {
            System.out.println("Error saving jobs to file: " + e.getMessage());
        }
    }

    private void saveJobSeekersToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(JOB_SEEKERS_FILE))) {
            for (JobSeeker jobSeeker : jobSeekers) {
                writer.write(String.format("%s,%s,%s,%d,%s,%s,%b,%s,%s\n",
                        jobSeeker.getName(),
                        String.join(";", jobSeeker.getSkills()),
                        jobSeeker.getEducationBackground(),
                        jobSeeker.getExperience(),
                        jobSeeker.prefersFullTime() ? "full-time" : "part-time",
                        jobSeeker.getPreferredLocation(),
                        jobSeeker.isWillingToRelocate(),
                        jobSeeker.getContactNumber(),
                        jobSeeker.getContactEmail()
                ));
            }
        } catch (IOException e) {
            System.out.println("Error saving job seekers to file: " + e.getMessage());
        }
    }
    
    

    public static void main(String[] args) {
        JobAgencySystem system = new JobAgencySystem();
        system.menu();
    }
}
