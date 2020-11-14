import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Random;

public class PriorityQueueSimulatorTester {
    public static void main(String[] args) {
        int maxNumberOfJobs = 100;
        long currentTime = 0; // current CPU cycle of the program
        long terminationTime = 0; // current CPU cycle at which job was terminated
        long waitTime = 0; // current CPU cycle at which job waited to be terminated
        long totalWaitTime = 0; // total wait time of all jobs
        long startTime = 0; // actual start time of system
        long endTime = 0; // actual end time of system
        Job[] jobsInputArray = new Job[maxNumberOfJobs]; // array of jobs
        Random rand = new Random(); // generating randomness
        LinearPriorityQueue priorityQueue = new LinearPriorityQueue(); // Creating empty linear priority queue
        HeapPriorityQueue heapPriorityQueue = new HeapPriorityQueue(); // Creating empty heap priority queue
        LinearPriorityQueue.JobNode node; // node of linear priority queue
        HeapPriorityQueue.Entry<Integer, Job> entry; // entry of heap priority queue
        int jobLength = rand.nextInt(70) + 1; // random jobLength - boundaries [1, 70]
        int jobPriority = rand.nextInt(40) + 1; // random jobPriority - boundaries [1, 40]

        // Creating and initializing job objects in each index of array
        for (int i = 0; i < jobsInputArray.length; i++) {
            String jobName = "JOB_" + (i + 1); // e.g name of job: JOB_10
            jobsInputArray[i] = new Job(jobName, jobLength, jobPriority); // create job object
            jobLength = rand.nextInt(70) + 1; // generate random integers 1-70
            jobPriority = rand.nextInt(40) + 1; // generate random integers 1-40
        }

        System.out.println(" ------------------------------ ");
        System.out.println("| Unsorted List Priority Queue |");
        System.out.println(" ------------------------------ ");

        // Insertion of jobs in linear priority queue
        for (Job job : jobsInputArray) {
            Job j = new Job(job); // Creating new copies of job object to avoid conflict for later
            startTime = System.currentTimeMillis(); // start actual time of system
            priorityQueue.insert(j.getJobPriority(), j); // insert job object in priority queue
            ++currentTime; // increment CPU cycle
        }

        // Simulator begins
        while (!priorityQueue.isEmpty()) {
            node = priorityQueue.removeMin(); // executing processes
            ++currentTime; // increment CPU cycle
            System.out.println("List Priority Queue | Now executing " + node.getJob().toString());
            // if job length > 0, insert back in PQ
            if (node.getJob().getCurrentJobLength() > 0) {
                priorityQueue.insert(node.getPriority(), node.getJob());
            }
            // if job length = 0, process terminated
            if (node.getJob().getCurrentJobLength() == 0) {
                ++terminationTime;
                // For every termination of 30 processes, prioritize lowest priority in queue
                if (terminationTime % 30 == 0 && terminationTime != 0) {
                    ++currentTime; // increment CPU cycle
                    priorityQueue.prioritizeMax();
                }
                // Calculating wait time
                waitTime = currentTime - node.getJob().getEntryTime() - node.getJob().getJobLength();
                totalWaitTime = totalWaitTime + waitTime; // adding to total wait time
                node.getJob().setEndTime(currentTime);
                node.getJob().setWaitTime(waitTime);
            }
        }
        endTime = System.currentTimeMillis();

        // Linear priority queue is now empty, all jobs terminated
        // Writing to Performance Result file
        try {
            PrintWriter writer = new PrintWriter(new FileOutputStream("A3/SimulatorPerformanceResults.txt", true));
            writer.println(" ------------------------------ ");
            writer.println("| Unsorted List Priority Queue |");
            writer.println(" ------------------------------ ");
            writer.println("Current system time (cycles):" + currentTime);
            writer.println("Total number of jobs executed: " + maxNumberOfJobs + " jobs");
            writer.println("Average process waiting time: " + (totalWaitTime / maxNumberOfJobs) + " cycles");
            writer.println("Total number of priority changes: " + priorityQueue.getPriorityChange());
            writer.println("Actual system time needed to execute all jobs: " + (endTime - startTime) + " ms");
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to open/write to file. Program terminated.");
            System.exit(-1);
        }

        // Reset system time and CPU cycle to zero
        currentTime = 0;
        terminationTime = 0;
        waitTime = 0;
        totalWaitTime = 0;
        startTime = 0;
        endTime = 0;

        System.out.println(" --------------------- ");
        System.out.println("| Heap Priority Queue |");
        System.out.println(" --------------------- ");

        // Insertion of jobs in heap
        for (Job job : jobsInputArray) {
            startTime = System.currentTimeMillis(); // actual system start time of heap
            heapPriorityQueue.insert(job.getJobPriority(), job); // insert job object in heap priority queue
            ++currentTime;
        }

        // Simulator begins
        while (!heapPriorityQueue.isEmpty()) {
            entry = heapPriorityQueue.removeMin(); // executing jobs
            ++currentTime;
            System.out.println("Now executing " + entry.getValue().toString());
            // if job length > 0, insert back in PQ
            if (entry.getValue().getCurrentJobLength() > 0) {
                heapPriorityQueue.insert(entry.getKey(), entry.getValue());
            } // if job length = 0, process terminated
            if (entry.getValue().getCurrentJobLength() == 0) {
                ++terminationTime;
                // For every termination of 30 processes, prioritize lowest priority in queue
                if (terminationTime % 30 == 0 && terminationTime != 0) {
                    ++currentTime;
                    heapPriorityQueue.prioritizeMax();
                }
                // Calculating wait time
                waitTime = currentTime - entry.getValue().getEntryTime() - entry.getValue().getJobLength();
                totalWaitTime = totalWaitTime + waitTime;
                entry.getValue().setEndTime(currentTime);
                entry.getValue().setWaitTime(waitTime);
            }
        }
        endTime = System.currentTimeMillis();

        // Heap is now empty, all jobs terminated
        // Writing to Performance Result file
        try {
            PrintWriter writer = new PrintWriter(new FileOutputStream("A3/SimulatorPerformanceResults.txt", true));
            writer.println(" --------------------- ");
            writer.println("| Heap Priority Queue |");
            writer.println(" --------------------- ");
            writer.println("Current system time (cycles):" + currentTime);
            writer.println("Total number of jobs executed: " + maxNumberOfJobs + " jobs");
            writer.println("Average process waiting time: " + (totalWaitTime / maxNumberOfJobs) + " cycles");
            writer.println("Total number of priority changes: " + heapPriorityQueue.getPriorityChange());
            writer.println("Actual system time needed to execute all jobs: " + (endTime - startTime) + " ms");
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to open/write to file. Program terminated.");
            System.exit(-1);
        }
    }
}
