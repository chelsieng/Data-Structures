public class Student {
    private long SIDC;
    private String familyName;
    private String firstName;

    // Default constructor
    public Student() {
        SIDC = 0;
        familyName = "";
        firstName = "";
    }

    // Parameterized constructor
    public Student(long key, String firstName, String familyName) {
        this.SIDC = key;
        this.familyName = familyName;
        this.firstName = firstName;
    }

    // Copy constructor
    public Student(Student student) {
        this.SIDC = student.SIDC;
        this.familyName = student.familyName;
        this.firstName = student.firstName;
    }

    // Getters
    public String getFamilyName() {
        return familyName;
    }

    public String getFirstName() {
        return firstName;
    }

    public long getSIDC() {
        return SIDC;
    }

    // Setters
    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSIDC(long ID) {
        this.SIDC = ID;
    }

    // String representation of student
    // E.g: ID: 40072839; Name: John Smith; DOB: 31/12/1995
    public String toString() {
        return "ID: " + SIDC + "\tName: " + firstName + " " + familyName;
    }
}
