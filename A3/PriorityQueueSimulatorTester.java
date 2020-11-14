import java.util.PriorityQueue;
import java.util.Random;

public class PriorityQueueSimulatorTester {
    public static void main(String[] args) {
        int maxNumberOfJobs = 10;
        int currentTime = 0;
        Job[] jobsInputArray = new Job[maxNumberOfJobs];
        Random rand = new Random();
        LinearPriorityQueue priorityQueue = new LinearPriorityQueue(); // Creating empty linear priority queue
        HeapPriorityQueue heapPriorityQueue = new HeapPriorityQueue(); // Creating empty heap priority queue
        LinearPriorityQueue.JobNode node;
        HeapPriorityQueue.Entry<Integer, Job> entry;
        int jobLength = rand.nextInt(70) + 1; // jobLength [1, 70]
        int jobPriority = rand.nextInt(40) + 1; // jobPriority [1, 40]
        // Creating and initializing job objects in each index
        for (int i = 0; i < jobsInputArray.length; i++) {
            String jobName = "JOB_" + (i + 1); // e.g name of job: JOB_10
            jobsInputArray[i] = new Job(jobName, jobLength, jobPriority); // create job object
            priorityQueue.insert(jobsInputArray[i].getJobPriority(), jobsInputArray[i]); // insert object in priority queue
            ++currentTime;
            Job heapJob = new Job(jobsInputArray[i]); // Creating copy of job
            heapPriorityQueue.insert(heapJob.getJobPriority(), heapJob); // insert object in priority queue
            jobLength = rand.nextInt(70) + 1;
            jobPriority = rand.nextInt(40) + 1;
        }
        // Simulator begins
        int i = 0;
        while (!priorityQueue.isEmpty() && !heapPriorityQueue.isEmpty()) {
            node = priorityQueue.removeMin();
            entry = heapPriorityQueue.removeMin();
            ++currentTime;
            ++i;
            if (i % 30 == 0) {
                priorityQueue.prioritizeMax();
                heapPriorityQueue.prioritizeMax();
            }
            if (node.getJob().getCurrentJobLength() > 0 && entry.getValue().getCurrentJobLength() > 0) {
                priorityQueue.insert(node.getPriority(), node.getJob());
                heapPriorityQueue.insert(entry.getKey(), entry.getValue());
            }
            if (node.getJob().getCurrentJobLength() == 0 && entry.getValue().getCurrentJobLength() == 0) {
                node.getJob().setEndTime(currentTime);
                entry.getValue().setEndTime(currentTime);
            }
            System.out.println(" --------------------- ");
            System.out.println("| List Priority Queue |");
            System.out.println(" --------------------- ");
            System.out.println("Now executing " + node.getJob().toString());
            System.out.println(" --------------------- ");
            System.out.println("| Heap Priority Queue |");
            System.out.println(" --------------------- ");
            System.out.println("Now executing " + entry.getValue().toString() + "\n");
        }


    }
}
