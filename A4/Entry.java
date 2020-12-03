public class Entry {
    private long key; // key is the priority
    private Student value; // value is the job object
    private int height; // height of entry
    private Entry left, right; // child of parent

    // Default constructor
    public Entry() {
        key = 0;
        value = null;
        height = 1;
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
