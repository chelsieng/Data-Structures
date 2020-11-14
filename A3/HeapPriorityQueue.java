import java.util.ArrayList;

public class HeapPriorityQueue {
    public class Entry<K, V> {
        private K key; // key is the priority
        private V value; // value is the job object

        // Default constructor
        public Entry() {
            key = null;
            value = null;
        }

        // Getter
        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        // Setter
        public void setKey(K key) {
            this.key = key;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

    private ArrayList<Entry<Integer, Job>> heap; // Priority queue implemented from arraylist
    private long insertTime; // time at which an entry was inserted in heap
    private int priorityChange; // number of times at which priority was changed

    // Default constructor
    public HeapPriorityQueue() {
        heap = new ArrayList<>();
        insertTime = 0;
        priorityChange = 0;
    }

    // Getter
    public int getPriorityChange() {
        return priorityChange;
    }

    // get index of parent
    private int parentIndex(int childIndex) {
        return (childIndex - 1) / 2;
    }

    // get index of left child
    private int leftChildIndex(int parentIndex) {
        return 2 * parentIndex + 1;
    }

    // get index of right child
    private int rightChildIndex(int parentIndex) {
        return 2 * parentIndex + 2;
    }

    // returns true if node has a parent node
    private boolean hasParent(int index) {
        return parentIndex(index) >= 0;
    }

    // returns true if node has a left child node
    private boolean hasLeftChild(int index) {
        return leftChildIndex(index) < heap.size();
    }

    // returns true if node has a right child node
    private boolean hasRightChild(int index) {
        return rightChildIndex(index) < heap.size();
    }

    // get value of parent
    private Entry<Integer, Job> parent(int index) {
        return heap.get(parentIndex(index));
    }

    // get value of left child
    private Entry<Integer, Job> leftChild(int index) {
        return heap.get(leftChildIndex(index));
    }

    // get value of right child
    private Entry<Integer, Job> rightChild(int index) {
        return heap.get(rightChildIndex(index));
    }

    // Swap value of two indices
    private void swap(int i, int j) {
        Entry<Integer, Job> temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    // Return true if priority queue is empty
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    // Size of priority queue
    public int size() {
        return heap.size();
    }

    // moves the entry up, if necessary to restore heap property
    public void heapUp(int index) {
        // as long as there is a parent
        while (hasParent(index)) {
            // if parent index has lower priority than index, swap
            if (parent(index).getKey() > heap.get(index).getKey()) {
                swap(parentIndex(index), index);
            } // if parent priority equals index priority, consider FCFS if parent entered queue after index, swap
            else if (parent(index).getKey().equals(heap.get(index).getKey()) && parent(index).getValue().getEntryTime() > heap.get(index).getValue().getEntryTime()) {
                swap(parentIndex(index), index);
            } else {
                break; // no more parent
            }
            index = parentIndex(index); // go up
        }
    }

    // moves the entry down, if necessary to restore heap property
    public void heapDown() {
        int index = 0;
        // as long as there is a left child
        while (hasLeftChild(index)) {
            int smallerChildIndex = leftChildIndex(index); // Let left child index be smaller child index
            // if there is a right child and right child has highest priority than smaller child index, swap
            if (hasRightChild(index) && rightChild(index).getKey() < leftChild(index).getKey()) {
                smallerChildIndex = rightChildIndex(index);
            } // if there is a right child and both have same priority, consider FCFS if right child entered queue before smaller child index swap
            else if (hasRightChild(index) && rightChild(index).getKey().equals(leftChild(index).getKey()) &&
                    rightChild(index).getValue().getEntryTime() < leftChild(index).getValue().getEntryTime()) {
                smallerChildIndex = rightChildIndex(index);
            } // if index have lower priority than smaller child index, swap
            if (heap.get(index).getKey() > heap.get(smallerChildIndex).getKey()) {
                swap(index, smallerChildIndex);
            } // if both have same priority, consider entry time, if index entered after smaller child index, swap
            else if (heap.get(index).getKey().equals(heap.get(smallerChildIndex).getKey()) &&
                    heap.get(index).getValue().getEntryTime() > heap.get(smallerChildIndex).getValue().getEntryTime()) {
                swap(index, smallerChildIndex);
            } else {
                break;
            }
            index = smallerChildIndex; // go down
        }
    }

    // Insert method which insert entry and heap up to restore property of heap
    public void insert(int priority, Job job) {
        Entry<Integer, Job> entry = new Entry<>();
        entry.setKey(priority);
        entry.setValue(job);
        heap.add(entry); // add entry at the end of heap
        job.setEntryTime(++insertTime); // increment entry time by 1
        heapUp(heap.size() - 1); // restore heap property
    }

    // removeMin method which removes the element of highest priority and heap down to restore property of heap
    public Entry<Integer, Job> removeMin() {
        if (heap.size() == 0) {
            throw new IllegalArgumentException("Error: The queue is empty.");
        }
        Entry<Integer, Job> toRemove = heap.get(0); //highest priority at the root
        swap(0, heap.size() - 1); // swap entry at the end with entry on top of heap
        heap.remove(heap.size() - 1); // remove last entry
        heapDown(); // restore heap property
        toRemove.getValue().setCurrentJobLength(toRemove.getValue().getCurrentJobLength() - 1); // decrement job length by 1
        return toRemove;
    }

    // prioritizeMax method which finds the oldest element in the heap
    public void prioritizeMax() {
        if (heap.isEmpty()) {
            return;
        }
        long lowestEntryTime = heap.get(0).getValue().getEntryTime(); // let lowest entry time be at root of heap
        Entry<Integer, Job> prioritize = heap.get(0); // let oldest entry be at the root of heap
        // Find smallest entry time
        for (Entry<Integer, Job> entry : heap) {
            // if entry time is smallest than current entry time
            if (entry.getValue().getEntryTime() < lowestEntryTime) {
                lowestEntryTime = entry.getValue().getEntryTime(); // set new entry time to lowest entry time
                prioritize = entry; // let new entry be the oldest entry
            }
        }
        //changes current priority to 1
        prioritize.setKey(1);
        //set final priority to 1
        prioritize.getValue().setFinalPriority(1);
        ++priorityChange;
        //heap up to restore heap property
        heapUp(heap.indexOf(prioritize));
    }

    // Displaying content of queue
    public void print() {
        if (heap.isEmpty()) {
            System.out.println("The priority queue is empty.");
        }
        for (Entry<Integer, Job> entry : heap) {
            System.out.println(entry.getValue().toString());
        }
    }

}
