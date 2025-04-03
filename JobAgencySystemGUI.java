import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JobAgencySystemGUI extends Application {
    private List<Job> jobs = new ArrayList<>();
    private List<JobSeeker> jobSeekers = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        // Create labels and text area for displaying output
        TextArea outputArea = new TextArea();
        outputArea.setEditable(false);
        // Load data from files
        loadJobsFromFile();
        loadJobSeekersFromFile();

        // Create labels and input fields for adding a new job
        Label addJobLabel = new Label("Add Job");
        TextField jobIdField = new TextField();
        jobIdField.setPromptText("Enter Job ID");
        TextField jobTitleField = new TextField();
        jobTitleField.setPromptText("Enter Job Title");
        TextField jobDescriptionField = new TextField();
        jobDescriptionField.setPromptText("Enter Job Description");
        TextField educationCriteriaField = new TextField();
        educationCriteriaField.setPromptText("Enter Educational Criteria");
        TextField skillsField = new TextField();
        skillsField.setPromptText("Enter Required Skills (comma-separated)");
        TextField locationField = new TextField();
        locationField.setPromptText("Enter Job Location");
        TextField salaryField = new TextField();
        salaryField.setPromptText("Enter Salary");
        TextField deadlineField = new TextField();
        deadlineField.setPromptText("Enter Application Deadline (YYYY-MM-DD)");
        CheckBox preferExperienceCheckBox = new CheckBox("Prefer Experienced Job Seekers");
        TextField experienceField = new TextField();
        experienceField.setPromptText("Enter Minimum Required Experience (in years)");
        CheckBox fullTimeCheckBox = new CheckBox("Full-time");

        // Create button to add job
        Button addJobButton = new Button("Add Job");

        // Set event handler for add job button
    addJobButton.setOnAction(event -> {
    // Get input values from the fields
    int jobId = Integer.parseInt(jobIdField.getText());
    String title = jobTitleField.getText();
    String description = jobDescriptionField.getText();
    String educationCriteria = educationCriteriaField.getText();
    String[] skillsArr = skillsField.getText().split(",");
    ArrayList<String> requiredSkills = new ArrayList<>();
    for (String skill : skillsArr) {
        requiredSkills.add(skill.trim());
    }
    String location = locationField.getText();
    double salary = Double.parseDouble(salaryField.getText());
    String deadline = deadlineField.getText();
    boolean preferExperience = preferExperienceCheckBox.isSelected();
    int minExperience = preferExperience ? Integer.parseInt(experienceField.getText()) : 0;
    boolean fullTime = fullTimeCheckBox.isSelected();

    // Create the job object with the input values
    Job job = new Job(jobId, title, description, requiredSkills);
    job.setEducationCriteria(educationCriteria);
    job.setLocation(location);
    job.setSalary(salary);
    job.setApplicationDeadline(deadline);
    job.setPreferExperience(preferExperience);
    job.setMinExperience(minExperience);
    job.setFullTime(fullTime);

    // Add the job to the list
    jobs.add(job);

    // Display success message
    outputArea.appendText("\nJob added successfully.");

    // Save jobs to file
    saveJobsToFile();
});


        // Arrange input fields and button in a grid pane for adding a job
        GridPane addJobGrid = new GridPane();
        addJobGrid.setVgap(10);
        addJobGrid.setHgap(10);
        addJobGrid.setAlignment(Pos.CENTER);
        addJobGrid.addRow(0, addJobLabel);
        addJobGrid.addRow(1, new Label("Job ID:"), jobIdField);
        addJobGrid.addRow(2, new Label("Job Title:"), jobTitleField);
        addJobGrid.addRow(3, new Label("Job Description:"), jobDescriptionField);
        addJobGrid.addRow(4, new Label("Educational Criteria:"), educationCriteriaField);
        addJobGrid.addRow(5, new Label("Required Skills:"), skillsField);
        addJobGrid.addRow(6, new Label("Location:"), locationField);
        addJobGrid.addRow(7, new Label("Salary:"), salaryField);
        addJobGrid.addRow(8, new Label("Application Deadline:"), deadlineField);
        addJobGrid.addRow(9, preferExperienceCheckBox, experienceField);
        addJobGrid.addRow(10, fullTimeCheckBox, addJobButton);

        // Create labels and input fields for adding a new job seeker
        Label addJobSeekerLabel = new Label("Add Job Seeker");
        TextField jobSeekerNameField = new TextField();
        jobSeekerNameField.setPromptText("Enter Name");
        TextField jobSeekerSkillsField = new TextField();
        jobSeekerSkillsField.setPromptText("Enter Skills (comma-separated)");
        TextField jobSeekerEducationField = new TextField();
        jobSeekerEducationField.setPromptText("Enter Education Background");
        TextField jobSeekerExperienceField = new TextField();
        jobSeekerExperienceField.setPromptText("Enter Experience (in years)");
        CheckBox preferFullTimeCheckBox = new CheckBox("Prefer Full-time");
        TextField preferredLocationField = new TextField();
        preferredLocationField.setPromptText("Enter Preferred Location");
        CheckBox willingToRelocateCheckBox = new CheckBox("Willing to Relocate");
        TextField jobSeekerContactNumberField = new TextField();
        jobSeekerContactNumberField.setPromptText("Enter Contact Number");
        TextField jobSeekerContactEmailField = new TextField();
        jobSeekerContactEmailField.setPromptText("Enter Contact Email");

        // Create button to add job seeker
        Button addJobSeekerButton = new Button("Add Job Seeker");

        // Set event handler for add job seeker button
addJobSeekerButton.setOnAction(event -> {
    // Get input values from the fields
    String name = jobSeekerNameField.getText();
    String[] skillsArr = jobSeekerSkillsField.getText().split(",");
    ArrayList<String> skills = new ArrayList<>();
    for (String skill : skillsArr) {
        skills.add(skill.trim());
    }
    String education = jobSeekerEducationField.getText();
    int experience = Integer.parseInt(jobSeekerExperienceField.getText());
    boolean preferFullTime = preferFullTimeCheckBox.isSelected();
    String preferredLocation = preferredLocationField.getText();
    boolean willingToRelocate = willingToRelocateCheckBox.isSelected();
    String contactNumber = jobSeekerContactNumberField.getText();
    String contactEmail = jobSeekerContactEmailField.getText();

    // Create the job seeker object with the input values
    JobSeeker jobSeeker = new JobSeeker(name, skills, education, experience,
            preferFullTime, preferredLocation, willingToRelocate, contactNumber, contactEmail);

    // Add the job seeker to the list
    jobSeekers.add(jobSeeker);

    // Display success message
    outputArea.appendText("\nJob seeker added successfully.");

    // Save job seekers to file
    saveJobSeekersToFile();
});


        // Arrange input fields and button in a grid pane for adding a job seeker
        GridPane addJobSeekerGrid = new GridPane();
        addJobSeekerGrid.setVgap(10);
        addJobSeekerGrid.setHgap(10);
        addJobSeekerGrid.setAlignment(Pos.CENTER);
        addJobSeekerGrid.addRow(0, addJobSeekerLabel);
        addJobSeekerGrid.addRow(1, new Label("Name:"), jobSeekerNameField);
        addJobSeekerGrid.addRow(2, new Label("Skills:"), jobSeekerSkillsField);
        addJobSeekerGrid.addRow(3, new Label("Education Background:"), jobSeekerEducationField);
        addJobSeekerGrid.addRow(4, new Label("Experience (in years):"), jobSeekerExperienceField);
        addJobSeekerGrid.addRow(5, preferFullTimeCheckBox, new Label("Preferred Location:"), preferredLocationField);
        addJobSeekerGrid.addRow(6, willingToRelocateCheckBox, addJobSeekerButton);
        addJobSeekerGrid.addRow(7, new Label("Contact Number:"), jobSeekerContactNumberField);
        addJobSeekerGrid.addRow(8, new Label("Contact Email:"), jobSeekerContactEmailField);

        // Create buttons for different functionalities
        Button viewAllJobsButton = new Button("View All Jobs");
        Button viewAllJobSeekersButton = new Button("View All Job Seekers");
        Button matchJobsWithSeekersButton = new Button("Match Jobs with Job Seekers");
        Button applyForMatchedJobsButton = new Button("Apply for Matched Jobs");
        Button exitButton = new Button("Exit");

        // Set event handlers for buttons
        viewAllJobsButton.setOnAction(event -> {
            displayOutput(outputArea, "All Available Jobs:");
            displayAllJobs(outputArea);
        });

        viewAllJobSeekersButton.setOnAction(event -> {
            displayOutput(outputArea, "All Registered Job Seekers:");
            displayAllJobSeekers(outputArea);
        });

        matchJobsWithSeekersButton.setOnAction(event -> {
            displayOutput(outputArea, "Matching Jobs with Job Seekers:");
            matchJobsWithSeekers(outputArea);
        });

        applyForMatchedJobsButton.setOnAction(event -> {
            displayOutput(outputArea, "Applying for Matched Jobs:");
            applyForMatchedJobs(outputArea);
        });

        exitButton.setOnAction(event -> {
            primaryStage.close();
        });

        // Arrange buttons in a HBox
        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(viewAllJobsButton, viewAllJobSeekersButton,
        matchJobsWithSeekersButton, applyForMatchedJobsButton, exitButton);

        // Create text area for displaying output for applying for matched jobs
        TextArea applyForMatchedJobsOutputArea = new TextArea();
        applyForMatchedJobsOutputArea.setEditable(false);
        applyForMatchedJobsOutputArea.setPrefSize(300, 200);

        // Create text field for input in applying for matched jobs
        TextField applyForMatchedJobsInputField = new TextField();
        applyForMatchedJobsInputField.setPromptText("Enter your input here");

        // Arrange components in a VBox for applying for matched jobs
        VBox applyForMatchedJobsBox = new VBox(10);
        applyForMatchedJobsBox.setAlignment(Pos.CENTER);
        applyForMatchedJobsBox.getChildren().addAll(applyForMatchedJobsOutputArea, applyForMatchedJobsInputField);

        // Arrange input fields and button in a grid pane for applying for matched jobs
        GridPane applyForMatchedJobsGrid = new GridPane();
        applyForMatchedJobsGrid.setVgap(10);
        applyForMatchedJobsGrid.setHgap(10);
        applyForMatchedJobsGrid.setAlignment(Pos.CENTER);
        applyForMatchedJobsGrid.addRow(0, new Label("Apply for Matched Jobs:"), applyForMatchedJobsButton);

        // Arrange components in a VBox
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(10));

        // Add sections to the root
        root.getChildren().addAll(new Label("JOB AGENCY SYSTEM"),
                new HBox(10, addJobGrid, addJobSeekerGrid),
                applyForMatchedJobsBox,
                applyForMatchedJobsGrid,
                outputArea, buttonBox);

        // Create scene and set it on the stage
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Job Agency System");
        primaryStage.show();
    }

    private void displayOutput(TextArea outputArea, String message) {
        outputArea.appendText("\n" + message + "\n");
    }

    private void displayAllJobs(TextArea outputArea) {
        if (jobs.isEmpty()) {
            displayOutput(outputArea, "No jobs are currently available.");
        } else {
            for (Job job : jobs) {
                displayOutput(outputArea, job.toString());
            }
        }
    }

    private void displayAllJobSeekers(TextArea outputArea) {
        if (jobSeekers.isEmpty()) {
            displayOutput(outputArea, "No job seekers are currently registered.");
        } else {
            for (JobSeeker jobSeeker : jobSeekers) {
                displayOutput(outputArea, jobSeeker.toString());
            }
        }
    }

    private void matchJobsWithSeekers(TextArea outputArea) {
    // Clear previous output
    outputArea.clear();

    // Iterate through each job
    for (Job job : jobs) {
        outputArea.appendText("Job Title: " + job.getTitle() + "\n");
        outputArea.appendText("|||||||||| MATCHING JOB SEEKERS ||||||||||\n");

        // Filter job seekers based on matching criteria
        List<JobSeeker> matchedJobSeekers = jobSeekers.stream()
                .filter(jobSeeker -> matchesCriteria(jobSeeker, job))
                .collect(Collectors.toList());

        // Display matched job seekers
        if (matchedJobSeekers.isEmpty()) {
            outputArea.appendText("No matching job seekers found.\n");
        } else {
            for (JobSeeker jobSeeker : matchedJobSeekers) {
                outputArea.appendText("Matched Job Seeker: " + jobSeeker.getName() + "\n");
                outputArea.appendText("Contact Number: " + jobSeeker.getContactNumber() + "\n");
                outputArea.appendText("Contact Email: " + jobSeeker.getContactEmail() + "\n");
            }
        }
        outputArea.appendText("\n");
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


private void applyForMatchedJobs(TextArea outputArea) {
    // Clear previous output
    outputArea.clear();

    // Display applying message
    outputArea.appendText("\nApplying for Matched Jobs:\n");

    // Iterate through each job
    for (Job job : jobs) {
        // Find matched job seekers for the current job
        List<JobSeeker> matchedJobSeekers = getMatchedJobSeekers(job);

        // If there are no matched job seekers, continue to the next job
        if (matchedJobSeekers.isEmpty()) {
            continue;
        }

        // Display job title and matched job seekers
        outputArea.appendText("\nJob Title: " + job.getTitle() + "\n");
        outputArea.appendText("Matched Job Seekers:\n");

        // Display matched job seekers and allow user to select a job seeker to apply
        int index = 1;
        for (JobSeeker jobSeeker : matchedJobSeekers) {
            outputArea.appendText(index + ". " + jobSeeker.getName() + " (Email: " + jobSeeker.getContactEmail() + ")\n");
            index++;
        }

        // Show dialog to select a job seeker to apply
        int selectedIndex = showApplyDialog(matchedJobSeekers.size());
        if (selectedIndex > 0 && selectedIndex <= matchedJobSeekers.size()) {
            JobSeeker selectedJobSeeker = matchedJobSeekers.get(selectedIndex - 1);
            // Simulate application submission
            outputArea.appendText("Application submitted successfully.\n");
            // You can add the logic to save the application details here if needed
        } else if (selectedIndex != 0) {
            // Show error message for invalid selection
            outputArea.appendText("Invalid selection. No application submitted.\n");
        }
    }
}

// Method to show dialog for selecting a job seeker to apply
private int showApplyDialog(int maxIndex) {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Apply for Matched Jobs");
    alert.setHeaderText("Select a Job Seeker to Apply");
    // Create buttons for each job seeker and an option to skip
    ButtonType[] buttons = new ButtonType[maxIndex + 1];
    buttons[0] = new ButtonType("Skip");
    for (int i = 1; i <= maxIndex; i++) {
        buttons[i] = new ButtonType(String.valueOf(i));
    }
    alert.getButtonTypes().setAll(buttons);
    // Show dialog and wait for user response
    alert.showAndWait();
    // Get the index of the selected job seeker
    String result = alert.getResult().getText();
    return result.equals("Skip") ? 0 : Integer.parseInt(result);
}

private List<JobSeeker> getMatchedJobSeekers(Job job) {
    return jobSeekers.stream()
            .filter(jobSeeker -> matchesCriteria(jobSeeker, job))
            .collect(Collectors.toList());
}




private static final String JOBS_FILE = "jobs.txt";
private static final String JOB_SEEKERS_FILE = "job_seekers.txt";


private void loadJobsFromFile() {
    try (BufferedReader reader = new BufferedReader(new FileReader(JOBS_FILE))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length >= 11) { // Check if the line has at least 11 elements
                try {
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
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("Invalid input line: " + line);
                    // Log the exception or display a more meaningful error message
                }
            } else {
                System.out.println("Invalid input line: " + line);
            }
        }
    } catch (IOException e) {
        System.out.println("Error loading jobs from file: " + e.getMessage());
        // Log the exception or display a more meaningful error message
    }
}

private void loadJobSeekersFromFile() {
    try (BufferedReader reader = new BufferedReader(new FileReader(JOB_SEEKERS_FILE))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            try {
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
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Invalid input line: " + line);
                // Log the exception or display a more meaningful error message
            }
        }
    } catch (IOException e) {
        System.out.println("Error loading job seekers from file: " + e.getMessage());
        // Log the exception or display a more meaningful error message
    }
}

    // Define method to save jobs to file
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

// Define method to save job seekers to file
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
        launch(args);
    }

}