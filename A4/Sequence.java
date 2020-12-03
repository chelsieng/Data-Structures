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

    private void ensureCapacity(Sequence sequence) {
        if (sequence.index == sequence.entries.length) {
            sequence.entries = Arrays.copyOf(sequence.entries, sequence.size() + 1);
        }
    }
}
