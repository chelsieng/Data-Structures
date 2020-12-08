import java.util.Arrays;

public class Sequence {
    private long[] entries;
    private int index; // pointer
    private int size; // size of sequence

    public Sequence() {
        entries = new long[0];
        index = 0;
        size = 0;
    }

    public Sequence(int size) {
        entries = new long[size];
        index = 0;
        size = 0;
    }

    public long[] getEntries() {
        return entries;
    }

    public int size() {
        return size;
    }

    // add an entry for the given key and value
    public void add(long key) {
        // Validate capacity of heap
        ensureCapacity(this);
        entries[index] = key;
        ++index;
        ++size;
    }

    // remove the entry for the given key
    public long remove(long key) {
        // If removing when list is empty
        if (size() == 0) {
            throw new IllegalArgumentException("Cannot remove from an empty list!");
        }
        mergeSort(entries); // sort heap
        int location = binarySearch(entries, key); // get key
        // If not found
        if (location == -1) {
            System.out.println("Error: Key not found!");
            return -1;
        }
        long removed = entries[location];
        entries[location] = entries[size-1];
        --size; // update size of heap
        --index; // update pointer
        return removed;
    }

    // Sorts the array of entries
    private void mergeSort(long[] array) {
        if (array == null) {
            return;
        }

        if (array.length > 1) {
            int mid = array.length / 2;

            // Split left part
            long[] left = new long[mid];
            for (int i = 0; i < mid; i++) {
                left[i] = array[i];
            }

            // Split right part
            long[] right = new long[array.length - mid];
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
                if (left[i] < right[j]) {
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
    private int binarySearch(long[] entries, long key) {
        int left = 0, right = this.size - 1; //pointer at the beginning and end of list
        while (left <= right) {
            int mid = left + (right - left) / 2; // midpoint of l and r
            // Check if key is present at midpoint
            if (entries[mid] == key) {
                return mid;
            }
            // If key is greater, ignore left half
            if (entries[mid] < key) {
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

    private void ensureCapacity(Sequence sequence) {
        if (sequence.index == sequence.entries.length) {
            sequence.entries = Arrays.copyOf(sequence.entries, sequence.size() + 1);
        }
    }
}
