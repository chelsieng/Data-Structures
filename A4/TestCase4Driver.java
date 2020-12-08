import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class TestCase4Driver {
    public static void main(String[] args) {
        try {
            IntelligentSIDC intelligentSIDC = new IntelligentSIDC(150000);
            NameGenerator nameGenerator = new NameGenerator();
            // Generating new unique keys
            System.out.println("Test Case 4:");
            System.out.println("1.\tGenerating unique 100,000 keys to CSTA_test_files/CSTA_test_file4.txt:\n");
            System.out.println("1.1\tAdding 150,000 keys with new Student Object");
            long firstKey = 0;
            long secondKey = 0;
            long lastKey = 0;
            long fourthKey = 0;
            for (int i = 0; i < 150000; i++) {
                long key = intelligentSIDC.generate();
                intelligentSIDC.add(intelligentSIDC, key, new Student(key, nameGenerator.generateFirstName(), nameGenerator.generateFamilyName()));
            }
            System.out.println("Results:");
            System.out.println("Size:" + intelligentSIDC.size());

            // Generating all keys in sorted sequence and
            System.out.println("\n2.\tWriting all keys in sorted sequence to CSTA_test_files/SORTED_CSTA_test_file4.txt");
            PrintWriter writer = new PrintWriter(new FileOutputStream("A4/CSTA_test_files/SORTED_CSTA_test_file4.txt", true));
            long[] allKeys = intelligentSIDC.allKeys(intelligentSIDC);
            int count = 1;
            for (long k : allKeys) {
                if (count == 1) {
                    firstKey = k;
                }
                if (count == 2) {
                    secondKey = k;
                }
                if (count == 4) {
                    fourthKey = k;
                }
                if (count == 150000) {
                    lastKey = k;
                }
                writer.println(k);
                ++count;
            }
            writer.close();
            System.out.println("Results: ");
            System.out.println("SORTED_CSTA_test_file4.txt created successfully.");

            // Range in between first and last keys, should print size - 2
            System.out.println("\n3.\tGet number of keys in between first key and last key from sorted file");
            System.out.println("Results: ");
            System.out.println("Number of keys: "+intelligentSIDC.rangeKey(firstKey, lastKey));

            // Range in between first and second keys, 0 key in between
            System.out.println("\n4.\tGet number of keys in between first key and second key from sorted file");
            System.out.println("Results: ");
            System.out.println("Number of keys: "+intelligentSIDC.rangeKey(firstKey, secondKey));

            // Range in between fourth and first keys, should print 2
            System.out.println("\n5.\tGet number of keys in between fourth key and first key from file");
            System.out.println("Results: ");
            System.out.println("Number of keys: "+intelligentSIDC.rangeKey(fourthKey, firstKey));
        } catch (FileNotFoundException e) {
            System.out.println("Unable to open/read/write to file. Program terminated.");
            System.exit(-1);
        }
    }
}
