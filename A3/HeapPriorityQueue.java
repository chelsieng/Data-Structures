import java.util.ArrayList;

public class HeapPriorityQueue {
    public class Entry<K, V> {
        private K key;
        private V value;

        public Entry() {
            key = null;
            value = null;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

    private ArrayList<Entry<Integer, Job>> heap;
    private long insertTime;

    // Default constructor
    public HeapPriorityQueue() {
        heap = new ArrayList<>();
        insertTime = 0;
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
        while (hasParent(index)) {
            if (parent(index).getKey() > heap.get(index).getKey()) {
                swap(parentIndex(index), index);
            } else if (parent(index).getKey().equals(heap.get(index).getKey()) && parent(index).getValue().getEntryTime() > heap.get(index).getValue().getEntryTime()) {
                swap(parentIndex(index), index);
            } else {
                break;
            }
            index = parentIndex(index);
        }
    }

    // moves the entry down, if necessary to restore heap property
    public void heapDown() {
        int index = 0;
        while (hasLeftChild(index)) {
            int smallerChildIndex = leftChildIndex(index);
            if (hasRightChild(index) && rightChild(index).getKey() < leftChild(index).getKey()) {
                smallerChildIndex = rightChildIndex(index);
            } else if (hasRightChild(index) && rightChild(index).getKey().equals(leftChild(index).getKey()) &&
                    rightChild(index).getValue().getEntryTime() < leftChild(index).getValue().getEntryTime()) {
                smallerChildIndex = rightChildIndex(index);
            }
            if (heap.get(index).getKey() > heap.get(smallerChildIndex).getKey()) {
                swap(index, smallerChildIndex);
            } else if (heap.get(index).getKey().equals(heap.get(smallerChildIndex).getKey()) &&
                    heap.get(index).getValue().getEntryTime() > heap.get(smallerChildIndex).getValue().getEntryTime()) {
                swap(index, smallerChildIndex);
            } else {
                break;
            }
            index = smallerChildIndex;
        }
    }

    // Insert method which insert element at the head of list
    public void insert(int priority, Job job) {
        Entry<Integer, Job> entry = new Entry<>();
        entry.setKey(priority);
        entry.setValue(job);
        heap.add(entry);
        job.setEntryTime(++insertTime);
        heapUp(heap.size() - 1);
    }

    // removeMin method which removes the element of highest priority
    public Entry<Integer, Job> removeMin() {
        if (heap.size() == 0) {
            throw new IllegalArgumentException("Error: The queue is empty.");
        }
        Entry<Integer, Job> toRemove = heap.get(0);
        swap(0, heap.size() - 1);
        heap.remove(heap.size() - 1);
        heapDown();
        toRemove.getValue().setCurrentJobLength(toRemove.getValue().getCurrentJobLength() - 1);
        return toRemove;
    }

    // prioritizeMax method which finds the oldest element in the list
    public void prioritizeMax() {
        long lowestEntryTime = heap.get(0).getValue().getEntryTime();
        Entry<Integer, Job> prioritize = heap.get(0);
        // Find smallest entry time
        for (Entry<Integer, Job> entry : heap) {
            if (entry.getValue().getEntryTime() < lowestEntryTime) {
                lowestEntryTime = entry.getValue().getEntryTime();
                prioritize = entry;
            }
        }
        //changes current priority to 1
        prioritize.setKey(1);
        //set final priority to 1
        prioritize.getValue().setFinalPriority(1);
        //heap up
        heapUp(heap.indexOf(prioritize));
    }

    public void print() {
        if (heap.isEmpty()){
            System.out.println("The priority queue is empty.");
        }
        for (Entry<Integer, Job> entry : heap) {
            System.out.println(entry.getValue().toString());
        }
    }

}
