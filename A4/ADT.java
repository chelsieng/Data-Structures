public interface ADT {
    long[] allKeys(IntelligentSIDC intelligentSIDC);

    // add an entry for the given key and value
    void add(IntelligentSIDC intelligentSIDC, Long key, Student value);

    // remove the entry for the given key
    Entry remove(IntelligentSIDC intelligentSIDC, long key);

    // return the values of the given key
    Student getValues(IntelligentSIDC intelligentSIDC, long key);

    // return the key for the successor of key
    long nextKey(IntelligentSIDC intelligentSIDC, long key);

    // return the key for the predecessor of key;
    long prevKey(IntelligentSIDC intelligentSIDC, long key);

    // returns the number of keys that are within the specified range of the two keys key1 and key2
    int rangeKey(long key1, long key2);

    // get size of ADT
    int size();
}
