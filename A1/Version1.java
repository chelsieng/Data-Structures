import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Random;

public class Version1 {
    public static void RevealStr(String s, int index) {
        StringBuilder str = new StringBuilder(s);
        if (index == s.length()) {
            try {
                PrintWriter printString = new PrintWriter(new FileOutputStream("A1/out1.txt", true));
                printString.println(str.toString());
                printString.close();
            } catch (FileNotFoundException e) {
                System.out.println("out.txt could not be opened/created.");
                System.exit(-1);
            }
            return;
        }
        if (s.charAt(index) == '*') {
            //replace * by 0 and recurse
            str.setCharAt(index, '0');
            RevealStr(str.toString(), index + 1);

            //replace * by 1 and recurse
            str.setCharAt(index, '1');
            RevealStr(str.toString(), index + 1);
        } else {
            RevealStr(s, index + 1);
        }
    }

    public static void main(String[] args) {
        Random random = new Random();
        int stringSize = 50; //length of string
        int numOfAsterisks = 8; //number of * in string
        StringBuilder stringBuilder = new StringBuilder(stringSize);
        for (int i = 0; i < stringSize; i++) {
            int indexOfBinaryString = random.nextInt(2); //generating 0,1 randomly
            stringBuilder.append(indexOfBinaryString); //appending 0/1 to each index of string
        }
        for (int i = 0; i < numOfAsterisks; i++) {
            int indexOfAsterisks = random.nextInt(stringSize); //generation indices of string randomly
            while (stringBuilder.charAt(indexOfAsterisks) == '*') {
                indexOfAsterisks = random.nextInt(stringSize); //keep generating randomly if char at index already has *
            }
            stringBuilder.setCharAt(indexOfAsterisks, '*');
        }
        String s = stringBuilder.toString(); //string representation of string builder
        try {
            PrintWriter printString = new PrintWriter(new FileOutputStream("A1/out1.txt", false));
            printString.close();
        } catch (FileNotFoundException e) {
            System.exit(-1);
        }
        long startTime = System.currentTimeMillis();
        RevealStr(s, 0);
        long endTime = System.currentTimeMillis();
        long elapsed = endTime - startTime;
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '*') {
                count++;
            }
        }
        try {
            PrintWriter printTime = new PrintWriter(new FileOutputStream("A1/time1.txt", true));
            printTime.println("Number of *: " + count + "    Runtime: " + elapsed);
            printTime.close();
        } catch (FileNotFoundException e) {
            System.exit(-1);
        }

    }
}





