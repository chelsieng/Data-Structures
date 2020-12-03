public class IntelligentSIDC {
    protected ADT adt_type;
    private int SDICThreshold;

    //Default Constructor
    public IntelligentSIDC() {

    }

    //Parameterized constructor
    public IntelligentSIDC(int size) {
        SetSIDCThreshold(size);
        if (SDICThreshold <= 1000) {
            adt_type = new MinHeap(size);
        } else {
            adt_type = new BinarySearchTree(size);
        }
    }

    // get size of IntelligentSIDC
    public int size() {
        return adt_type.size();
    }

    // Determines what data types or data structures will be used
    public void SetSIDCThreshold(int size) {
        SDICThreshold = size;
    }

    public long[] allKeys(IntelligentSIDC intelligentSIDC) {
        return adt_type.allKeys(intelligentSIDC);
    }

    // add an entry for the given key and value
    public void add(IntelligentSIDC intelligentSIDC, Long key, Student value) {
        adt_type.add(intelligentSIDC, key, value);
    }

    // remove the entry for the given key
    public Entry remove(IntelligentSIDC intelligentSIDC, long key) {
        return adt_type.remove(intelligentSIDC, key);
    }

    // return the values of the given key
    public Student getValues(IntelligentSIDC intelligentSIDC, long key) {
        return adt_type.getValues(intelligentSIDC, key);
    }

    // return the key for the successor of key
    public long nextKey(IntelligentSIDC intelligentSIDC, long key) {
        return adt_type.nextKey(intelligentSIDC, key);
    }

    // return the key for the predecessor of key;
    public long prevKey(IntelligentSIDC intelligentSIDC, long key) {
        return adt_type.prevKey(intelligentSIDC, key);
    }

    // returns the number of keys that are within the specified range of the two keys key1 and key2
    public int rangeKey(long key1, long key2) {
        return adt_type.rangeKey(key1, key2);
    }
}
