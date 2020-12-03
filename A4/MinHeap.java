import java.util.Arrays;

public class MinHeap extends IntelligentSIDC implements ADT {
    protected Entry[] heap; // array of entries
    protected final int CAPACITY = 1000; // max capacity of heap
    protected int size; // size of heap
    protected int index; // pointer

    // Default constructor
    public MinHeap() {
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
    protected void heapUp(int index) {
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
    protected void heapDown() {
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
    public long[] allKeys(IntelligentSIDC minHeap) {
        long[] keys = new long[size()];
        mergeSort(heap); // sort heap
        for (int i = 0; i < keys.length; i++) {
            keys[i] = heap[i].getKey(); // add all keys in array of keys
        }
        heapDown(); // restore heap property
        return keys;
    }

    // add an entry for the given key and value
    public void add(IntelligentSIDC minHeap, Long key, Student value) {
        // Validate capacity of heap
        if (capacityReached()) {
            return;
        }
        Entry entry = new Entry();
        entry.setKey(key);
        entry.setValue(value);
        heap[index] = entry; // add entry at the end of heap
        heapUp(index); // restore heap property
        ++index; // move pointer
        ++size; // update size of heap

    }

//    // remove the entry for the given key
    public Entry remove(IntelligentSIDC minHeap, long key) {
        // If removing when list is empty
        if (size() == 0) {
            throw new IllegalArgumentException("Cannot remove from an empty list!");
        }
        mergeSort(heap); // sort heap
        int location = binarySearch(heap, key); // get key
        // If not found
        if (location == -1) {
            System.out.println("Error: Key not found!");
            return null;
        }
        swap(0, location); // move key at the top
        Entry removed = heap[0];
        heap[0] = heap[heap.length - 1]; // removed and replaced with last element of heap
        heapDown(); // restore property
        --size; // update size of heap
        --index; // update pointer
        return removed;
    }

    // returns true if full capacity reached
    private boolean capacityReached() {
        if (size() == CAPACITY) {
            System.out.println("Failed: The list reached its capacity!");
            return true;
        } else if (heap.length == index) {
            heap = Arrays.copyOf(heap, size() + 1);
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
            if (entries[mid].getKey() == key) {
                return mid;
            }
            // If key is greater, ignore left half
            if (entries[mid].getKey() < key) {
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
    public Student getValues(IntelligentSIDC minHeap, long key) {
        mergeSort(heap); // sort list
        int location = binarySearch(heap, key); // find key
        // key not found
        if (location == -1) {
            System.out.println("Error: Student does not exist!");
            return null;
        }
        Student toFind = heap[location].getValue();
        heapDown(); // restore property
        return toFind;
    }

    // return the key for the successor of key
    public long nextKey(IntelligentSIDC minHeap, long key) {
        mergeSort(heap);
        int location = binarySearch(heap, key);
        int nextIndex = location + 1;
        if (nextIndex == size()) {
            System.out.println("Error: Successor of key does not exist!");
            return -1;
        }
        long found = heap[nextIndex].getKey();
        heapDown();
        return found;
    }

    // return the key for the predecessor of key;
    public long prevKey(IntelligentSIDC minHeap, long key) {
        mergeSort(heap);
        int location = binarySearch(heap, key);
        int prevIndex = location - 1;
        if (prevIndex < 0) {
            System.out.println("Error: Predecessor of key does not exist!");
            return -1;
        }
        long found = heap[prevIndex].getKey();
        heapDown();
        return found;
    }

    // returns the number of keys that are within the specified range of the two keys key1 and key2
    public int rangeKey(long key1, long key2) {
        mergeSort(heap);
        int from = binarySearch(heap, key1);
        int to = binarySearch(heap, key2);
        if (from > to) {
            int temp = to;
            to = from;
            from = temp;
        }
        return (to - from) - 1;
    }

    public void print() {
        for (int i = 0; i < size(); i++) {
            System.out.println("(" + heap[i].getKey() + ", " + heap[i].getValue().getFirstName() + ")");
        }
    }

//    public static void main(String[] args) {
//        Student s1 = new Student("Chelsie", "Ng", new Student.DOB(12, 18, 1999));
//        Student s2 = new Student("Nigel", "Yong", new Student.DOB(12, 11, 1999));
//        Student s3 = new Student("Stella", "Lo", new Student.DOB(1, 30, 1972));
//        Student s4 = new Student("Kelvin", "Lo", new Student.DOB(10, 14, 1995));
//        Student s5 = new Student("Chelsie", "Ng", new Student.DOB(12, 18, 1999));
//        Student s6 = new Student("Nigel", "Yong", new Student.DOB(12, 11, 1999));
//        Student s7 = new Student("Stella", "Lo", new Student.DOB(1, 30, 1972));
//        Student s8 = new Student("Kelvin", "Lo", new Student.DOB(10, 14, 1995));
//        Student s9 = new Student("Chelsie", "Ng", new Student.DOB(12, 18, 1999));
//        Student s10 = new Student("Nigel", "Yong", new Student.DOB(12, 11, 1999));
//        long key1 = 40071692;
//        long key2 = 40071693;
//        long key3 = 40071694;
//        long key4 = 40071690;
//        long key5 = 40071691;
//        long key6 = 40071699;
//        long key7 = 40071698;
//        long key8 = 40071697;
//        long key9 = 40071696;
//        long key10 = 40071695;
//        MinHeap MinHeap = new MinHeap(10);
//        MinHeap.add(MinHeap, key1, s1);
//        MinHeap.add(MinHeap, key2, s2);
//        MinHeap.add(MinHeap, key3, s3);
//        MinHeap.add(MinHeap, key4, s4);
//        MinHeap.add(MinHeap, key5, s5);
//        MinHeap.add(MinHeap, key6, s6);
//        MinHeap.add(MinHeap, key7, s7);
//        MinHeap.add(MinHeap, key8, s8);
//        MinHeap.add(MinHeap, key9, s9);
//        MinHeap.add(MinHeap, key10, s10);
//        MinHeap.print();
//        MinHeap.remove(MinHeap, 40071692);
//        System.out.println("----------------------");
//        MinHeap.print();
//        long k = 02;
//        MinHeap.add(MinHeap, k, new Student());
//        System.out.println("----------------------");
//        MinHeap.print();
//        long k2 = 9;
//        long k3 = 10;
//        MinHeap.add(MinHeap, k2, new Student());
//        MinHeap.add(MinHeap, k3, new Student());
//        System.out.println("----------------------");
//        MinHeap.print();
//        MinHeap.add(MinHeap, key1, new Student());
//        Random random = new Random();
//        int num = random.nextInt(10000000) + 10000000;
//        for (int i = 0; i < 999; i++) {
//            long key = num;
//            MinHeap.add(MinHeap, key, new Student());
//            num = random.nextInt(10000000) + 10000000;
//        }
//        MinHeap.add(MinHeap, key1, new Student());
//    }

}
