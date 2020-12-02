import java.util.Arrays;

public class MinHeap {
    public static class Entry {
        private Long key; // key is the priority
        private Student value; // value is the job object

        // Default constructor
        public Entry() {
            key = null;
            value = null;
        }

        // Parameterized Constructor
        public Entry(long key, Student value) {
            this.key = key;
            this.value = value;
        }

        // Getter
        public Long getKey() {
            return key;
        }

        public Student getValue() {
            return value;
        }

        // Setter
        public void setKey(long key) {
            this.key = key;
        }

        public void setValue(Student value) {
            this.value = value;
        }

    }

    private Entry entry; // key: student ID, value: student object
    private Entry[] heap; // array of entries
    private final int CAPACITY = 1000; // max capacity of heap
    private int size; // size of heap
    private int index; // pointer

    // Default constructor
    public MinHeap() {
        entry = new Entry();
        heap = new Entry[0];
        size = 0;
        index = 0;
    }

    // Parameterized constructor
    public MinHeap(int size) {
        heap = new Entry[CAPACITY];
        this.size = 0;
        index = 0;
    }

    // get size of heap
    public int size() {
        return size;
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
        return leftChildIndex(index) < heap.length;
    }

    // returns true if node has a right child node
    private boolean hasRightChild(int index) {
        return rightChildIndex(index) < heap.length;
    }

    // get value of parent
    private Entry parent(int index) {
        return heap[parentIndex(index)];
    }

    // get value of left child
    private Entry leftChild(int index) {
        return heap[leftChildIndex(index)];
    }

    // get value of right child
    private Entry rightChild(int index) {
        return heap[rightChildIndex(index)];
    }

    // Swap value of two indices
    private void swap(int i, int j) {
        Entry temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    // moves the entry up, if necessary to restore heap property
    private void heapUp(int index) {
        // as long as there is a parent
        while (hasParent(index)) {
            // if parent index has lower key than index, swap
            if (parent(index).getKey() > heap[index].getKey()) {
                swap(parentIndex(index), index);
            } else {
                break; // no more parent
            }
            index = parentIndex(index); // go up
        }
    }

    // moves the entry down, if necessary to restore heap property
    private void heapDown() {
        int index = 0;
        // as long as there is a left child
        while (hasLeftChild(index)) {
            int smallerChildIndex = leftChildIndex(index); // Let left child index be smaller child index
            // if there is a right child and right child has highest key than smaller child index, swap
            if (hasRightChild(index) && rightChild(index).getKey() < leftChild(index).getKey()) {
                smallerChildIndex = rightChildIndex(index);
            }  // if index have lower key than smaller child index, swap
            if (heap[index].getKey() > heap[smallerChildIndex].getKey()) {
                swap(index, smallerChildIndex);
            } else {
                break;
            }
            index = smallerChildIndex; // go down
        }
    }

    // return all the keys in a sequence
    public long[] allKeys(MinHeap minHeap) {
        long[] keys = new long[minHeap.size()];
        mergeSort(minHeap.heap); // sort heap
        for (int i = 0; i < keys.length; i++) {
            keys[i] = minHeap.heap[i].getKey(); // add all keys in array of keys
        }
        minHeap.heapDown(); // restore heap property
        return keys;
    }

    // add an entry for the given key and value
    public void add(MinHeap minHeap, Long key, Student value) {
        // Validate capacity of heap
        if (capacityReached(minHeap)) {
            return;
        }
        Entry entry = new Entry();
        entry.setKey(key);
        entry.setValue(value);
        heap[minHeap.index] = entry; // add entry at the end of heap
        minHeap.heapUp(minHeap.index); // restore heap property
        ++minHeap.index; // move pointer
        ++minHeap.size; // update size of heap

    }

    // remove the entry for the given key
    public Entry remove(MinHeap minHeap, long key) {
        // If removing when list is empty
        if (minHeap.size() == 0) {
            throw new IllegalArgumentException("Cannot remove from an empty list!");
        }
        mergeSort(minHeap.heap); // sort heap
        int location = binarySearch(minHeap.heap, key); // get key
        // If not found
        if (location == -1) {
            System.out.println("Error: Key not found!");
            return null;
        }
        minHeap.swap(0, location); // move key at the top
        Entry removed = minHeap.heap[0];
        minHeap.heap[0] = minHeap.heap[minHeap.heap.length - 1]; // removed and replaced with last element of heap
        minHeap.heapDown(); // restore property
        --minHeap.size; // update size of heap
        --minHeap.index; // update pointer
        return removed;
    }

    // returns true if full capacity reached
    private boolean capacityReached(MinHeap heap) {
        if (heap.size() == CAPACITY) {
            System.out.println("Failed: The list reached its capacity!");
            return true;
        } else if (heap.size() == index) {
            heap.heap = Arrays.copyOf(heap.heap, heap.size() + 1);
        }
        return false;
    }

    // Sorts the array of entries
    private void mergeSort(Entry[] array) {
        if (array == null) {
            return;
        }

        if (array.length > 1) {
            int mid = array.length / 2;

            // Split left part
            Entry[] left = new Entry[mid];
            for (int i = 0; i < mid; i++) {
                left[i] = array[i];
            }

            // Split right part
            Entry[] right = new Entry[array.length - mid];
            for (int i = mid; i < array.length; i++) {
                right[i - mid] = array[i];
            }
            mergeSort(left);
            mergeSort(right);

            int i = 0;
            int j = 0;
            int k = 0;

            // Merge left and right arrays
            while (i < left.length && j < right.length) {
                // Add smaller keys first
                if (left[i].getKey() < right[j].getKey()) {
                    array[k] = left[i];
                    i++;
                } else {
                    array[k] = right[j];
                    j++;
                }
                k++;
            }
            // Add remaining entries
            while (i < left.length) {
                array[k] = left[i];
                i++;
                k++;
            }
            while (j < right.length) {
                array[k] = right[j];
                j++;
                k++;
            }
        }
    }

    // returns index where key was found or returns -1 if not found
    private int binarySearch(Entry[] entries, long key) {
        int left = 0, right = entries.length - 1; //pointer at the beginning and end of list
        while (left <= right) {
            int mid = left + (right - left) / 2; // midpoint of l and r
            // Check if key is present at midpoint
            if (entries[mid].key == key) {
                return mid;
            }
            // If key is greater, ignore left half
            if (entries[mid].key < key) {
                left = mid + 1;
            }
            // If key is smaller, ignore right half
            else {
                right = mid - 1;
            }
        }
        // key not present
        return -1;
    }

    // return the values of the given key
    public Student getValues(MinHeap minHeap, long key) {
        mergeSort(minHeap.heap); // sort list
        int location = binarySearch(minHeap.heap, key); // find key
        // key not found
        if (location == -1) {
            System.out.println("Error: Student does not exist!");
            return null;
        }
        Student toFind = minHeap.heap[location].getValue();
        heapDown(); // restore property
        return toFind;
    }

    // return the key for the successor of key
    public long nextKey(MinHeap minHeap, long key) {
        mergeSort(minHeap.heap);
        int location = binarySearch(minHeap.heap, key);
        int nextIndex = location + 1;
        if (nextIndex == minHeap.size()) {
            System.out.println("Error: Successor of key does not exist!");
            return -1;
        }
        long found = minHeap.heap[nextIndex].key;
        minHeap.heapDown();
        return found;
    }

    // return the key for the predecessor of key;
    public long prevKey(MinHeap minHeap, long key) {
        mergeSort(minHeap.heap);
        int location = binarySearch(minHeap.heap, key);
        int prevIndex = location - 1;
        if (prevIndex < 0) {
            System.out.println("Error: Predecessor of key does not exist!");
            return -1;
        }
        long found = minHeap.heap[prevIndex].key;
        minHeap.heapDown();
        return found;
    }

    // returns the number of keys that are within the specified range of the two keys key1 and key2
    public int rangeKey(long key1, long key2) {
        mergeSort(heap);
        int from = binarySearch(heap, key1);
        int to = binarySearch(heap, key2);
        return (to - from) - 1;
    }

    public void print() {
        for (int i = 0; i < size(); i++) {
            System.out.println("(" + heap[i].key + ", " + heap[i].value.getFirstName() + ")");
        }
    }
}
