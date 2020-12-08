import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class TestCase3Driver {
    public static void main(String[] args) {
        try {
            IntelligentSIDC intelligentSIDC = new IntelligentSIDC(500000);
            NameGenerator nameGenerator = new NameGenerator();
            System.out.println("Test Case 3:");
            System.out.println("From CSTA_test_files/CSTA_test_file2.txt:\n");
            System.out.println("1.\tAdding 500,000 keys with new Student Object");
            System.out.println("Results:");
            Scanner reader = new Scanner(new FileInputStream("A4/CSTA_test_files/CSTA_test_file2.txt"));
            long key = reader.nextLong();
            while (reader.hasNextLong()) {
                intelligentSIDC.add(intelligentSIDC, key, new Student(key, nameGenerator.generateFirstName(), nameGenerator.generateFamilyName()));
                key = reader.nextLong();
            }
            intelligentSIDC.add(intelligentSIDC, key, new Student()); // last one
            System.out.println("Size excluding removing duplicates:" + intelligentSIDC.size());

            // Generating all keys in sorted sequence
            System.out.println("\n2.\tWriting all keys in sorted sequence to CSTA_test_files/SORTED_CSTA_test_file2.txt");
            PrintWriter writer = new PrintWriter(new FileOutputStream("A4/CSTA_test_files/SORTED_CSTA_test_file2.txt", true));
            long[] allKeys = intelligentSIDC.allKeys(intelligentSIDC);
            for (long k : allKeys) {
                writer.println(k);
            }
            writer.close();
            System.out.println("Results: ");
            System.out.println("SORTED_CSTA_test_file2.txt created successfully.");

            // Get next key of existing key from file
            System.out.println("\n3.\tGet next key of existing key from sorted file");
            System.out.println("Results: ");
            System.out.println(intelligentSIDC.nextKey(intelligentSIDC, 339));

            // Get next key of last key, should print error msg, successor does not exist
            System.out.println("\n4.\tGet next key of last key from sorted file");
            System.out.println("Results: ");
            intelligentSIDC.nextKey(intelligentSIDC, 99998814);

            // Get previous key of existing key
            System.out.println("\n5.\tGet previous key of existing key from sorted file");
            System.out.println("Results: ");
            System.out.println(intelligentSIDC.prevKey(intelligentSIDC, 726));

            // Get previous key of first key, should print error msg, predecessor does not exist
            System.out.println("\n6.\tGet previous key of first key from sorted file");
            System.out.println("Results: ");
            intelligentSIDC.prevKey(intelligentSIDC, 339);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to open/read/write to file. Program terminated.");
            System.exit(-1);
        }

    }
}
