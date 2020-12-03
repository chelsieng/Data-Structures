public class BinarySearchTree {
    public static class Entry {
        private Long key; // key is the priority
        private Student value; // value is the job object
        private int height; // height of entry
        private Entry left, right; // child of parent

        // Default constructor
        public Entry() {
            key = null;
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
        public Long getKey() {
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
        public void setKey(Long key) {
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

    private Entry root; // treeTree implemented as array
    private int size; // pointer of toRemove index

    // Default constructor
    public BinarySearchTree() {
        root = null;
        size = 0;
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

    // return all the keys in a sorted sequence
    public long[] allKeys(BinarySearchTree tree) {
        long[] keys = new long[tree.size()];
        return keys;
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
}
