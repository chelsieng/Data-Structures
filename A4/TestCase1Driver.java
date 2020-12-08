import java.io.*;
import java.util.Scanner;

public class TestCase1Driver {
    public static void main(String[] args) {
        IntelligentSIDC intelligentSIDC = new IntelligentSIDC(1000000);
        NameGenerator nameGenerator = new NameGenerator();
        try {
            // Adding 1,000,000 keys to ADT, since size > 1000, ADT will grow dynamically
            System.out.println("Test Case 1:");
            System.out.println("From CSTA_test_files/CSTA_test_file3.txt:\n");
            System.out.println("1.\tAdding 1,000,000 keys with new Student Object");
            System.out.println("Results:");
            Scanner reader = new Scanner(new FileInputStream("A4/CSTA_test_files/CSTA_test_file3.txt"));
            long key = reader.nextLong();
            while (reader.hasNextLong()) {
                intelligentSIDC.add(intelligentSIDC, key, new Student(key, nameGenerator.generateFirstName(), nameGenerator.generateFamilyName()));
                key = reader.nextLong();
            }
            intelligentSIDC.add(intelligentSIDC, key, new Student()); // last key added
            System.out.println("Size excluding duplicates:" + intelligentSIDC.size());

            // Removing an existing key, size should change
            System.out.println("\n2.\tRemove an existing key from file");
            System.out.println("Results: ");
            intelligentSIDC.remove(intelligentSIDC, 96822685);
            System.out.println("Size:" + intelligentSIDC.size());

            // Removing an non-existing key, should print error, size does not change
            System.out.println("\n3.\tRemove an non-existing key from file");
            System.out.println("Results: ");
            intelligentSIDC.remove(intelligentSIDC, 3831);
            System.out.println("Size: " + intelligentSIDC.size());

            // Get value of existing key, should get print info about student
            System.out.println("\n4.\tGet Value associated with existing key from file");
            System.out.println("Results: ");
            System.out.println(intelligentSIDC.getValues(intelligentSIDC, 34117816).toString());

            // Get value of existing key, should get print error msg
            System.out.println("\n5.\tGet Value associated with non-existing key from file");
            System.out.println("Results: ");
            intelligentSIDC.getValues(intelligentSIDC, 96822685);
            reader.close();

        } catch (FileNotFoundException e) {
            System.out.println("Unable to open/read/write to file. Program terminated.");
            System.exit(-1);
        }
    }
}
