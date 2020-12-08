public class BinarySearchTree extends IntelligentSIDC implements ADT {
    private Entry root; // root of binary search tree
    private int size; // pointer of toRemove index
    private Sequence keys;

    // Default constructor
    public BinarySearchTree() {
        root = null;
        size = 0;
        keys = new Sequence();
    }

    //Parameterized constructor
    public BinarySearchTree(int size) {
        root = null;
        size = 0;
        keys = new Sequence(size);
    }

    // Getter
    public Entry getRoot() {
        return root;
    }

    public int size() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    // returns false as BST is used to add large data
    public boolean capacityReached() {
        return false;
    }

    public Entry remove(IntelligentSIDC intelligentSIDC) {
        return null;
    }

    // Setter
    public void setRoot(Entry root) {
        this.root = root;
    }

    private int binarySearch(long[] entries, long key) {
        int left = 0, right = entries.length - 1; //pointer at the beginning and end of list
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

    public void SetSIDCThreshold(int size) {

    }

    // return all the keys in a sorted sequence
    public long[] allKeys(IntelligentSIDC tree) {
        mergeSort(keys.getEntries());
        return keys.getEntries();
    }

    // Randomly generates new keys
    public long generate() {
        long min = 10000000;
        long max = 99999999;
        long newKey = (long) (Math.random() * (max - min + 1) + min);
        long location = search(newKey);
        while (location != -1) {
            newKey = (long) (Math.random() * (max - min + 1) + min);
            location = search(newKey);
        }
        return newKey;
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

    private long search(long key) {
        Entry toFind = root;
        while (toFind.getKey() != key) {
            if (key < toFind.getKey()) {
                toFind = toFind.getLeft(); // go left
            } else {
                toFind = toFind.getRight(); // go right
            }
            if (toFind == null) {
                return -1; // not found
            }
        }
        return toFind.getKey(); // found
    }

    // add an entry for the given key and value
    public void add(IntelligentSIDC tree, long key, Student value) {
        // Creating new node
        Entry entry = new Entry(key, value);
        // if tree is empty
        if (root == null) {
            root = entry;
            keys.add(key);
            ++size;
            return;
        }
        //Check for duplicates
        long location = search(key);
        if (location != -1) {
//            System.out.println("Duplicates are not allowed!");
            return;
        }
        Entry current = root; // pointer
        Entry parent; // pointer to track current pointer
        while (true) {
            parent = current;
            if (key < current.getKey()) {
                current = current.getLeft();
                if (current == null) {
                    parent.setLeft(entry);
                    keys.add(key);
                    ++size;
                    return;
                }
            } else {
                current = current.getRight();
                if (current == null) {
                    parent.setRight(entry);
                    keys.add(key);
                    ++size;
                    return;
                }
            }
        }
    }


    // remove the entry for the given key
    public Entry remove(IntelligentSIDC tree, long key) {
        Entry toRemove = root; // start at the root
        Entry parent = root; // pointer to parent of node
        boolean isLeftChild = true;
        //Check if key is present
        while (toRemove.getKey() != key) {
            parent = toRemove;
            if (key < toRemove.getKey()) {
                isLeftChild = true;
                toRemove = toRemove.getLeft(); // go left
            } else {
                isLeftChild = false;
                toRemove = toRemove.getRight(); // go right
            }
            // key not found
            if (toRemove == null) {
                System.out.println("Error: Entry was not found!");
                return null;
            }
        }

        // if node does not have children
        if (toRemove.getLeft() == null && toRemove.getRight() == null) {
            // if is at root, delete root
            if (toRemove == root) {
                root = null;
            }
            // if is a left child of parent, delete left child
            else if (isLeftChild) {
                parent.setLeft(null);
            } else {
                parent.setRight(null); // if is a right child of parent, delete right child
            }
        }
        // If has no right child
        else if (toRemove.getRight() == null) {
            if (toRemove == root) {
                root = toRemove.getLeft();
            }// If node to be removed is on the left of parent, move left's node up to parent node
            else if (isLeftChild) {
                parent.setLeft(toRemove.getLeft());
            } else {
                parent.setRight(toRemove.getLeft()); // Vice versa
            }
        } // If has no left child
        else if (toRemove.getLeft() == null) {
            if (toRemove == root) {
                root = toRemove.getRight();
            }// If node to be removed is on the left of parent, move right's node up to parent node
            else if (isLeftChild) {
                parent.setLeft(toRemove.getRight());
            } else {
                parent.setRight(toRemove.getRight()); // Vice versa
            }
        } // If node has two children, find replacement of node to be deleted
        else {
            Entry replace = getReplacement(toRemove); // replacement node
            // If is root
            if (toRemove == root) {
                root = replace;
            } else if (isLeftChild) {
                parent.setLeft(replace); // If is left child
            } else {
                parent.setRight(replace); // If is right child
            }
            replace.setLeft(toRemove.getLeft());
        }
        --size;
        keys.remove(key);
        return toRemove; // node removed
    }

    private Entry getReplacement(Entry entry) {
        Entry replaceParent = entry;
        Entry replace = entry;
        Entry toRemove = entry.getRight();
        // No more left children
        while (toRemove != null) {
            replaceParent = replace;
            replace = toRemove;
            toRemove = toRemove.getLeft();
        }
        // If replacement is not the right child, move replacement into left child of parent
        // and move right child of entry into right child of replacement
        if (replace != entry.getRight()) {
            replaceParent.setLeft(replace.getRight());
            replace.setRight(replace.getRight());
        }
        return replace;
    }

    // return the values of the given key
    public Student getValues(IntelligentSIDC tree, long key) {
        Entry toFind = root;
        while (toFind.getKey() != key) {
            if (key < toFind.getKey()) {
                toFind = toFind.getLeft(); // go left
            } else {
                toFind = toFind.getRight(); // go right
            }
            if (toFind == null) {
                System.out.println("Error: Student does not exist!");
                return null;
            }
        }
        return toFind.getValue();
    }

    // return the key for the successor of key
    public long nextKey(IntelligentSIDC tree, long key) {
        long[] entries = allKeys(tree);
        int location = binarySearch(entries, key);
        if (location == -1) {
            System.out.println("Error: Key does not exist!");
            return -1;
        }
        int nextIndex = location + 1;
        if (nextIndex == entries.length) {
            System.out.println("Error: Successor of key does not exist!");
            return -1;
        }
        return entries[nextIndex];
    }

    // return the key for the predecessor of key;
    public long prevKey(IntelligentSIDC tree, long key) {
        long[] entries = allKeys(tree);
        int location = binarySearch(entries, key);
        int prevIndex = location - 1;
        if (location == -1) {
            System.out.println("Error: Key does not exist!");
            return -1;
        }
        if (prevIndex < 0) {
            System.out.println("Error: Predecessor of key does not exist!");
            return -1;
        }
        return entries[prevIndex];
    }

    // returns the number of keys that are within the specified range of the two keys key1 and key2
    public int rangeKey(long key1, long key2) {
        long[] entries = allKeys(this);
        int from = binarySearch(entries, key1);
        int to = binarySearch(entries, key2);
        if (from == -1 || to == -1) {
            System.out.println("Error: One of the keys does not exist!");
        }
        if (from > to) {
            int temp = to;
            to = from;
            from = temp;
        }
        return (to - from) - 1;
    }
}
