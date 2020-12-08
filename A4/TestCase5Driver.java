import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class TestCase5Driver {
    public static void main(String[] args) {
        int size = 20000;
        long firstKey = 0;
        long secondKey = 0;
        long lastKey = 0;
        long fourthKey = 0;
        IntelligentSIDC intelligentSIDC = new IntelligentSIDC(size);
        NameGenerator nameGenerator = new NameGenerator();
        try {
            // Generating new unique keys
            System.out.println("Test Case 5:");
            System.out.println("1.\tGenerating unique " + size + " keys to CSTA_test_files/CSTA_test_file5.txt:\n");
            System.out.println("1.1\tAdding " + size + " keys with new Student Object");
            for (int i = 0; i < size; i++) {
                long key = intelligentSIDC.generate();
                intelligentSIDC.add(intelligentSIDC, key, new Student(key, nameGenerator.generateFirstName(), nameGenerator.generateFamilyName()));
            }
            System.out.println("Results:");
            System.out.println("Size:" + intelligentSIDC.size());

            // Generating all keys in sorted sequence
            System.out.println("\n2.\tWriting all keys in sorted sequence to CSTA_test_files/SORTED_CSTA_test_file5.txt");
            PrintWriter writer = new PrintWriter(new FileOutputStream("A4/CSTA_test_files/SORTED_CSTA_test_file5.txt", true));
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
                if (count == intelligentSIDC.size()) {
                    lastKey = k;
                }
                writer.println(k);
                ++count;
            }
            writer.close();
            System.out.println("Results: ");
            System.out.println("SORTED_CSTA_test_file5.txt created successfully.");

            // Testing all methods from test case 1 - 4
            System.out.println("\n3.\tGet next key of existing key from file");
            System.out.println("Results: ");
            System.out.println("Next key: " + intelligentSIDC.nextKey(intelligentSIDC, firstKey));

            System.out.println("\n4.\tGet next key of non-existing key from file");
            System.out.println("Results: ");
            intelligentSIDC.nextKey(intelligentSIDC, 555);

            System.out.println("\n5.\tGet next key of last key from sorted file");
            System.out.println("Results: ");
            intelligentSIDC.nextKey(intelligentSIDC, lastKey);

            System.out.println("\n7.\tGet previous key of existing key from file");
            System.out.println("Results: ");
            System.out.println("Previous key: " + intelligentSIDC.prevKey(intelligentSIDC, secondKey));

            System.out.println("\n8.\tGet previous key of non-existing key from file");
            System.out.println("Results: ");
            intelligentSIDC.prevKey(intelligentSIDC, 555);

            System.out.println("\n6.\tGet previous key of first key from sorted file");
            System.out.println("Results: ");
            intelligentSIDC.prevKey(intelligentSIDC, firstKey);

            System.out.println("\n9.\tGet number of keys in between first key and last key from sorted file");
            System.out.println("Results: ");
            System.out.println(intelligentSIDC.rangeKey(firstKey, lastKey));

            System.out.println("\n10.\tGet number of keys in between first key and second key from sorted file");
            System.out.println("Results: ");
            System.out.println(intelligentSIDC.rangeKey(firstKey, secondKey));

            System.out.println("\n11.\tGet number of keys in between fourth key and first key from file");
            System.out.println("Results: ");
            System.out.println(intelligentSIDC.rangeKey(fourthKey, firstKey));

            System.out.println("\n12.\tGet value associated with existing key from file");
            System.out.println("Results: ");
            System.out.println(intelligentSIDC.getValues(intelligentSIDC, firstKey).toString());

            System.out.println("\n13.\tGet value associated with non-existing key from file");
            System.out.println("Results: ");
            intelligentSIDC.getValues(intelligentSIDC, 555);

            System.out.println("\n14.\tRemove an existing key from file");
            System.out.println("Results: ");
            intelligentSIDC.remove(intelligentSIDC, lastKey);
            System.out.println("Size:" + intelligentSIDC.size());

            System.out.println("\n15.\tRemove a non-existing key from file");
            System.out.println("Results: ");
            intelligentSIDC.remove(intelligentSIDC, 3831);
            System.out.println("Size: " + intelligentSIDC.size());

            // Removing keys until list is empty
            System.out.println("\n16.\tRemoving keys until list is empty");
            System.out.println("Results: ");
            for (int i = allKeys.length-2; i >= 0; i--) {
                intelligentSIDC.remove(intelligentSIDC, allKeys[i]);
            }
            System.out.println("Size: " + intelligentSIDC.size());


        } catch (FileNotFoundException e) {
            System.out.println("Unable to open/read/write to file. Program terminated.");
            System.exit(-1);
        }
    }
}
