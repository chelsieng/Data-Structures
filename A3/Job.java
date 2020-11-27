public class Job {
    private String jobName; // name of job
    private int jobLength; // the needed CPU cycles for this job to terminate
    private int currentJobLength; // the remaining length of the job at any given time
    private int jobPriority; // the initial priority of this job
    private int finalPriority; // the final priority of the job at termination time
    private long entryTime; // the time this job entered the priority queue
    private long endTime; // the time this job finally terminated
    private long waitTime; // total amount of wait time a job had to incur from the time it entered until it terminates

    // Default Constructor
    public Job() {
        jobName = "";
        jobLength = 0;
        currentJobLength = 0;
        jobPriority = 0;
        finalPriority = 0;
        entryTime = 0;
        endTime = 0;
        waitTime = 0;
    }

    // Parameterized constructor
    public Job(String jobName, int jobLength, int jobPriority) {
        if (jobLength < 1 || jobLength > 70) {
            System.out.println("Error: invalid length of job! Program will terminate now!");
            System.exit(-1);
        }
        if (jobPriority < 1 || jobPriority > 40) {
            System.out.println("Error: invalid value of priority! Program will terminate now!");
            System.exit(-1);
        }
        this.jobName = jobName;
        this.jobLength = jobLength;
        this.currentJobLength = jobLength;
        this.jobPriority = jobPriority;
        this.finalPriority = jobPriority;
        entryTime = 0;
        endTime = 0;
        waitTime = 0;
    }

    // Copy Constructor
    public Job(Job job) {
        this.jobName = job.jobName;
        this.jobLength = job.jobLength;
        this.currentJobLength = job.currentJobLength;
        this.jobPriority = job.jobPriority;
        this.finalPriority = job.finalPriority;
        this.entryTime = job.entryTime;
        this.endTime = job.endTime;
        this.waitTime = job.waitTime;
    }

    // Getters
    public String getJobName() {
        return jobName;
    }

    public int getJobLength() {
        return jobLength;
    }

    public int getCurrentJobLength() {
        return currentJobLength;
    }

    public int getJobPriority() {
        return jobPriority;
    }

    public int getFinalPriority() {
        return finalPriority;
    }

    public long getEntryTime() {
        return entryTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public long getWaitTime() {
        return waitTime;
    }

    // Setters
    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public void setJobLength(int jobLength) {
        if (jobLength < 1 || jobLength > 70) {
            System.out.println("Error: invalid length of job! Program will terminate now!");
            System.exit(-1);
        }
        this.jobLength = jobLength;
    }

    public void setCurrentJobLength(int currentJobLength) {
        this.currentJobLength = currentJobLength;
    }

    public void setJobPriority(int jobPriority) {
        if (jobPriority < 1 || jobPriority > 40) {
            System.out.println("Error: invalid value of priority! Program will terminate now!");
            System.exit(-1);
        }
        this.jobPriority = jobPriority;
    }

    public void setFinalPriority(int finalPriority) {
        if (jobPriority < 1 || jobPriority > 40) {
            System.out.println("Error: invalid value of priority! Program will terminate now!");
            System.exit(-1);
        }
        this.finalPriority = finalPriority;
    }

    public void setEntryTime(long entryTime) {
        this.entryTime = entryTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public void setWaitTime(long waitTime) {
        this.waitTime = waitTime;
    }

    // String representation of Job
    // For e.g. Job_285. Job length: 42 cycles; Current remaining length: 26 cycles; Initial priority: 22; Current priority: 1
    public String toString() {
        return this.jobName + ". Job length: " + this.jobLength + " cycles; Current remaining length: " + this.currentJobLength + " cycles; Initial priority: " + this.jobPriority + "; Current priority: " + finalPriority;
    }

}
