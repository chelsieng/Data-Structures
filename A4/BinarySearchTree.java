public class BinarySearchTree {
    public static class Entry {
        private long key; // key is the priority
        private Student value; // value is the job object
        private int height; // height of entry
        private Entry left, right; // child of parent

        // Default constructor
        public Entry() {
            key = 0;
            value = null;
            height = 1;
            left = null;
            right = null;
        }

        // Parameterized Constructor
        public Entry(long key, Student value) {
            this.key = key;
            this.value = value;
            height = 1;
        }

        // Copy constructor
        public Entry(Entry entry) {
            this.key = entry.key;
            this.value = new Student(entry.value);
            this.height = entry.height;
            this.right = entry.right;
            this.left = entry.left;
        }

        // Getter
        public long getKey() {
            return key;
        }

        public Student getValue() {
            return value;
        }

        public int getHeight() {
            return height;
        }

        public Entry getLeft() {
            return left;
        }

        public Entry getRight() {
            return right;
        }

        // Setter
        public void setKey(long key) {
            this.key = key;
        }

        public void setValue(Student value) {
            this.value = value;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public void setLeft(Entry left) {
            this.left = left;
        }

        public void setRight(Entry right) {
            this.right = right;
        }
    }

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

    // return all the keys in a sorted sequence
    public long[] allKeys(BinarySearchTree tree) {
        inOrder(tree.root);
        return keys.getEntries();
    }

    public void inOrder(Entry root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        int location = binarySearch(keys.getEntries(), root.key);
        if (location == -1) {
            keys.add(root.key);
        }
        inOrder(root.right);
    }

    // add an entry for the given key and value
    public void add(BinarySearchTree tree, Long key, Student value) {
        //-------------Insertion part---------------
        // Creating new node
        Entry entry = new Entry(key, value);
        // if tree is empty
        if (tree.root == null) {
            tree.root = entry;
            return;
        }
        Entry current = root; // pointer
        Entry toAdd = null; // pointer to track current pointer
        while (current != null) {
            toAdd = current;
            if (key < current.key) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        if (key < toAdd.key) {
            toAdd.left = entry;
        } else {
            toAdd.right = entry;
        }
        ++size;
    }


    // remove the entry for the given key
    public Entry remove(BinarySearchTree tree, long key) {
        Entry toRemove = tree.root; // start at the root
        Entry parent = tree.root; // pointer to parent of node
        boolean isLeftChild = true;
        //Check if key is present
        while (toRemove.key != key) {
            parent = toRemove;
            if (key < toRemove.key) {
                isLeftChild = true;
                toRemove = toRemove.left; // go left
            } else {
                isLeftChild = false;
                toRemove = toRemove.right; // go right
            }
            // key not found
            if (toRemove == null) {
                System.out.println("Error: Entry was not found!");
                return null;
            }
        }

        // if node does not have children
        if (toRemove.left == null && toRemove.right == null) {
            // if is at root, delete root
            if (toRemove == tree.root) {
                tree.root = null;
            }
            // if is a left child of parent, delete left child
            else if (isLeftChild) {
                parent.left = null;
            } else {
                parent.right = null; // if is a right child of parent, delete right child
            }
        }
        // If has no right child
        else if (toRemove.right == null) {
            if (toRemove == tree.root) {
                tree.root = toRemove.left;
            }// If node to be removed is on the left of parent, move left's node up to parent node
            else if (isLeftChild) {
                parent.left = toRemove.left;
            } else {
                parent.right = toRemove.left; // Vice versa
            }
        } // If has no left child
        else if (toRemove.left == null) {
            if (toRemove == root) {
                root = toRemove.right;
            }// If node to be removed is on the left of parent, move right's node up to parent node
            else if (isLeftChild) {
                parent.left = toRemove.right;
            } else {
                parent.right = toRemove.right; // Vice versa
            }
        } // If node has two children, find replacement of node to be deleted
        else {
            Entry replace = getReplacement(toRemove); // replacement node
            // If is root
            if (toRemove == tree.root) {
                tree.root = replace;
            } else if (isLeftChild) {
                parent.left = replace; // If is left child
            } else {
                parent.right = replace; // If is right child
            }
            replace.left = toRemove.left;
        }
        --size;
        return toRemove; // node removed
    }

    private Entry getReplacement(Entry entry) {
        Entry replaceParent = entry;
        Entry replace = entry;
        Entry toRemove = entry.right;
        // No more left children
        while (toRemove != null) {
            replaceParent = replace;
            replace = toRemove;
            toRemove = toRemove.left;
        }
        // If replacement is not the right child, move replacement into left child of parent
        // and move right child of entry into right child of replacement
        if (replace != entry.right) {
            replaceParent.left = replace.right;
            replace.right = entry.right;
        }
        return replace;
    }

    // return the values of the given key
    public Student getValues(BinarySearchTree tree, long key) {
        Entry toFind = tree.root;
        while (toFind.key != key) {
            if (key < toFind.key) {
                toFind = toFind.left; // go left
            } else {
                toFind = toFind.right; // go right
            }
            if (toFind == null) {
                System.out.println("Error: Student does not exist!");
                return null;
            }
        }
        return toFind.getValue();
    }

    // return the key for the successor of key
    public long nextKey(BinarySearchTree tree, long key) {
        long[] entries = allKeys(tree);
        int location = binarySearch(entries, key);
        int nextIndex = location + 1;
        if (nextIndex == entries.length) {
            System.out.println("Error: Successor of key does not exist!");
            return -1;
        }
        return entries[nextIndex];
    }

    // return the key for the predecessor of key;
    public long prevKey(BinarySearchTree tree, long key) {
        long[] entries = allKeys(tree);
        int location = binarySearch(entries, key);
        int prevIndex = location - 1;
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
        if (from > to) {
            int temp = to;
            to = from;
            from = temp;
        }
        return (to - from) - 1;
    }
}
