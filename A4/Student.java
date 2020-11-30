public class Student {
    public static class DOB {
        private int day;
        private int month;
        private int year;

        // Default Constructor
        public DOB() {
            day = 0;
            month = 0;
            year = 0;
        }

        // Parameterized Constructor
        public DOB(int month, int day, int year) {
            if ((validMonth(month) && validDay(day))) {
                this.day = day;
                this.month = month;
                this.year = year;
            } else {
                System.out.println("Error: Invalid date of birth!");
                System.exit(-1);
            }
        }

        // Getters
        public int getDay() {
            return day;
        }

        public int getMonth() {
            return month;
        }

        public int getYear() {
            return year;
        }

        // Setters
        public void setDay(int day) {
            if (validDay(day)) {
                this.day = day;
            } else {
                System.out.println("Error: Invalid day of the month!");
                System.exit(-1);
            }

        }

        public void setMonth(int month) {
            // Checking invalid months
            if (validMonth(month)) {
                this.month = month;
            } else {
                System.out.println("Error: Invalid day of the month!");
                System.exit(-1);
            }
        }

        public void setYear(int year) {
            this.year = year;
        }

        public boolean validMonth(int month) {
            return (month >= 1 && month <= 12);
        }

        public boolean validDay(int day) {
            boolean valid = true;
            // Checking invalid days
            if (day > 31 || day < 1) {
                valid = false;
            }
            // Checking for months which have 30 days
            if (day == 31 && (month == 2 || month == 4 || month == 6 || month == 9 || month == 11)) {
                valid = false;
            }
            // Checking for February
            if (month == 2 && day > 29) {
                valid = false;
            }
            return valid;
        }

        // String representation of DOB
        // E.g: 31/12/1995
        public String toString() {
            if (day < 10 && month < 10) {
                return "0" + day + "/0" + month + "/" + year;
            }
            if (month < 10) {
                return day + "/0" + month + "/" + year;
            }
            if (day < 10) {
                return "0" + day + "/" + month + "/" + year;
            } else {
                return day + "/" + month + "/" + year;
            }
        }
    }

    private long ID;
    private String familyName;
    private String firstName;
    private DOB dateOfBirth;

    // Default constructor
    public Student() {
        ID = 0;
        familyName = "";
        firstName = "";
        dateOfBirth = new DOB();
    }

    // Default constructor
    public Student(String firstName, String familyName, DOB dateOfBirth) {
        ID = 0;
        this.familyName = familyName;
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
    }

    // Getters
    public String getFamilyName() {
        return familyName;
    }

    public String getFirstName() {
        return firstName;
    }

    public DOB getDateOfBirth() {
        return dateOfBirth;
    }

    public long getID() {
        return ID;
    }

    // Setters
    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setDateOfBirth(DOB dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    // String representation of student
    // E.g: ID: 40072839; Name: John Smith; DOB: 31/12/1995
    public String toString() {
        return "ID: " + ID + "\tName: " + firstName + " " + familyName + "\tDOB: " + dateOfBirth.toString();
    }

}
