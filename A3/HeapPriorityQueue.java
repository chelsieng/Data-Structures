
import java.util.ArrayList;
import java.util.Map;


public class HeapPriorityQueue {
    private ArrayList<Map.Entry<Integer, Job>> heap;
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
    private Job parent(int index) {
        return heap.get(parentIndex(index)).getValue();
    }

    // get value of left child
    private Job leftChild(int index) {
        return heap.get(leftChildIndex(index)).getValue();
    }

    // get value of right child
    private Job rightChild(int index) {
        return heap.get(rightChildIndex(index)).getValue();
    }

    // Swap value of two indices
    private void swap(int i, int j) {
        Map.Entry<Integer, Job> temp = heap.get(i);
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

    // Insert method which insert element at the head of list

    // removeMin method which removes the element of highest priority

    // prioritizeMax method which finds the oldest element in the list


}
