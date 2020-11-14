public class LinearPriorityQueue {
    // Job node class to store object and next
    public class JobNode {
        private int priority;
        private Job job = new Job();
        private JobNode next;

        // Default constructor
        public JobNode() {
            priority = 0;
            job = null;
            next = null;
        }

        // Parameterized constructor to initialize value node object
        public JobNode(int priority, Job job, JobNode next) {
            this.priority = priority;
            this.job = job;
            this.next = next;
        }

        // Getter
        public int getPriority() {
            return priority;
        }

        public Job getJob() {
            return job;
        }

        public JobNode getNext() {
            return next;
        }

        // Setter
        public void setPriority(int priority) {
            this.priority = priority;
        }

        public void setJob(Job job) {
            this.job = job;
        }

        public void setNext(JobNode next) {
            this.next = next;
        }
    }

    private JobNode head;
    private int size;
    private long insertTime;
    private int priorityChange;

    // Default constructor
    public LinearPriorityQueue() {
        head = null;
        size = 0;
        insertTime = 0;
        priorityChange = 0;
    }

    // Getter
    public JobNode getJobNode() {
        return head;
    }

    public int size() {
        return size;
    }

    public int getPriorityChange() {
        return priorityChange;
    }

    // Setter
    public void setJobNode(JobNode head) {
        this.head = head;
    }

    // Insert method which insert element at the head of list
    public void insert(int priority, Job job) {
        JobNode newHead = new JobNode();
        newHead.priority = priority; // Assigning priority to new node
        newHead.job = job; // Assigning object to new node
        newHead.next = head; // set next of new node to next
        head = newHead; // set head of list to new node
        job.setEntryTime(++insertTime); // increment entry time
        size++; // increase size

    }

    // removeMin method which removes the element of highest priority
    public JobNode removeMin() {
        if (head == null) {
            throw new IllegalArgumentException("Error: The queue is empty.");
        }
        JobNode current = head; // temp pointer
        int min = current.priority; // min priority
        JobNode toRemove = current; // node to be removed
        JobNode beforeToRemove = current; // node before node to be removed
        while (current.next != null) {
            if (min >= current.next.priority) {
                min = current.next.priority;
                beforeToRemove = current;
                toRemove = current.next;
            }
            current = current.next; // pointer moving forward
        }
        if (toRemove == head) {
            head = head.next;
            toRemove.next = null;
        }
        // Removing element from the list
        beforeToRemove.next = toRemove.next;
        toRemove.next = null;
        size--;
        toRemove.job.setCurrentJobLength(toRemove.job.getCurrentJobLength() - 1); // decrement current length of job by 1
        return toRemove;
    }

    // prioritizeMax method which finds the oldest element in the list
    public void prioritizeMax() {
        if (head == null) {
            return;
        }
        JobNode current = head; // temp pointer to head
        long min = current.job.getEntryTime(); // lowest entry time of job
        JobNode toFind = current; // node to be found
        // Finding oldest node in list
        while (current.next != null) {
            if (min > current.next.job.getEntryTime()) {
                min = current.next.job.getEntryTime();
                toFind = current.next;
            }
            current = current.next; //pointer moving forward
        }
        toFind.setPriority(1); // changes current priority to 1
        toFind.job.setFinalPriority(1); // set final priority of job to 1
        ++priorityChange;
    }

    // Return true if priority queue is empty
    public boolean isEmpty() {
        return size == 0;
    }

    public void print() {
        if (head == null) {
            System.out.println("The priority queue is empty.");
            return;
        }
        JobNode current = head;
        while (current.next != null) {
            System.out.println(current.getJob().toString());
            current = current.next;
        }
        System.out.println(current.getJob().toString());
    }

}
