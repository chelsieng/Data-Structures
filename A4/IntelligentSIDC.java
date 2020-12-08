public class IntelligentSIDC {
    protected ADT adt_type;
    private int SDICThreshold;

    //Default Constructor
    public IntelligentSIDC() {

    }

    //Parameterized constructor
    public IntelligentSIDC(int size) {
        SetSIDCThreshold(size);
        adt_type = new MinHeap(size);
    }

    public void setAdt_type(ADT adt_type) {
        this.adt_type = adt_type;
    }

    // get size of IntelligentSIDC
    public int size() {
        return adt_type.size();
    }

    public void setSize(int size) {
        adt_type.setSize(size);
    }

    // Determines what data types or data structures will be used
    public void SetSIDCThreshold(int size) {
        SDICThreshold = size;
    }

    // randomly generates new non-existing key of 8 digits
    public long generate() {
        return adt_type.generate();
    }

    // return all keys as a sorted sequence
    public long[] allKeys(IntelligentSIDC intelligentSIDC) {
        return adt_type.allKeys(intelligentSIDC);
    }

    // add an entry for the given key and value
    public void add(IntelligentSIDC intelligentSIDC, long key, Student value) {
        if (adt_type.capacityReached()) {
            grow(intelligentSIDC);
        }
        adt_type.add(intelligentSIDC, key, value);
    }

    // change to bigger adt
    private void grow(IntelligentSIDC intelligentSIDC) {
        IntelligentSIDC binarySearchTree = new BinarySearchTree(SDICThreshold);
        Entry entry = adt_type.remove(intelligentSIDC);
        while (entry != null) {
            binarySearchTree.add(binarySearchTree, entry.getKey(), entry.getValue());
            entry = adt_type.remove(intelligentSIDC);
        }
        System.out.println("---IntelligentSIDC Grown: Data transferred---");
        setAdt_type((ADT) binarySearchTree);
    }

    // remove the entry for the given key
    public Entry remove(IntelligentSIDC intelligentSIDC, long key) {
        if (intelligentSIDC.size() == 1001) {
            Entry removed = adt_type.remove(intelligentSIDC, key);
            if (removed == null) {
                return null;
            }
            BinarySearchTree binarySearchTree = new BinarySearchTree();
            if (adt_type.getClass() == binarySearchTree.getClass()) {
                shrink(intelligentSIDC);
            }
            return removed;
        } else {
            return adt_type.remove(intelligentSIDC, key);
        }
    }

    // change to smaller ADT
    private void shrink(IntelligentSIDC intelligentSIDC) {
        IntelligentSIDC heap = new MinHeap(intelligentSIDC.size());
        long[] allKeys = intelligentSIDC.allKeys(intelligentSIDC);
        for (int i = intelligentSIDC.size() - 1; i >= 0; i--) {
            Student student = intelligentSIDC.getValues(intelligentSIDC, allKeys[i]);
            heap.add(heap, allKeys[i], student);
        }
        System.out.println("---IntelligentSIDC Shrinked: Data transferred---");
        setAdt_type((ADT) heap);
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
