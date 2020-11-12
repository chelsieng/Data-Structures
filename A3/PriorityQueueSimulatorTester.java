import java.util.Random;

public class PriorityQueueSimulatorTester {
    public static void main(String[] args) {
        int maxNumberOfJobs = 10;
        Job[] jobsInputArray = new Job[maxNumberOfJobs];
        Random rand = new Random();
        int jobLength = rand.nextInt(70) + 1; // jobLength [1, 70]
        int jobPriority = rand.nextInt(40) + 1; // jobPriority [1, 40]
        // Creating and initializing job objects in each index
        for (int i = 0; i < jobsInputArray.length; i++) {
            String jobName = "JOB_" + (i + 1);
            jobsInputArray[i] = new Job(jobName, jobLength, jobPriority);
            System.out.println(jobsInputArray[i].toString());
            jobLength = rand.nextInt(70) + 1;
            jobPriority = rand.nextInt(40) + 1;
        }


    }
}
