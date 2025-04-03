public class JobApplication {
    private Job job;
    private JobSeeker jobSeeker;

    public JobApplication(Job job, JobSeeker jobSeeker) {
        this.job = job;
        this.jobSeeker = jobSeeker;
    }

    public Job getJob() {
        return job;
    }

    public JobSeeker getJobSeeker() {
        return jobSeeker;
    }
}
