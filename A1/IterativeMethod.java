import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Stack;

public class Version2 {
    public static void RevealStr(String s) {
        Stack<String> stringStack = new Stack<String>();
        stringStack.push(s); //put s in stack
        while (!stringStack.empty()) {
            String str = stringStack.peek(); //look at string on top of stack
            StringBuilder str2 = new StringBuilder(str);
            //Finding first occurrence of *
            int index = str2.indexOf("*");
            // if found first occurence of *
            if (index != -1) {
                stringStack.pop(); //remove string from stack
                str2.setCharAt(index, '1'); //replace first occ * by 1
                stringStack.push(str2.toString()); //add to stack
                str2.setCharAt(index, '0'); //replace firs occ * by 0
                stringStack.push(str2.toString()); //add to stack
            } else { //if no '*' found
                try {
                    //write to file
                    PrintWriter printString = new PrintWriter(new FileOutputStream("A1/out2.txt", true));
                    printString.println(str.toString());
                    printString.close();
                } catch (FileNotFoundException e) {
                    System.out.println("out.txt could not be opened/created.");
                    System.exit(-1);
                }
                stringStack.pop(); //remove string from stack
            }
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
        System.out.println(stringBuilder.toString());
        for (int i = 0; i < numOfAsterisks; i++) {
            int indexOfAsterisks = random.nextInt(stringSize);
            while (stringBuilder.charAt(indexOfAsterisks) == '*') {
                indexOfAsterisks = random.nextInt(stringSize); //generation indices of string randomly
            }
            stringBuilder.setCharAt(indexOfAsterisks, '*'); //keep generating randomly if char at index already has *
        }
        String s = stringBuilder.toString(); //string representation of string builder
        System.out.println(s);
        try {
            PrintWriter printString = new PrintWriter(new FileOutputStream("A1/out2.txt", false));
            printString.close();
        } catch (FileNotFoundException e) {
            System.exit(-1);
        }
        long startTime = System.currentTimeMillis();
        RevealStr(s);
        long endTime = System.currentTimeMillis();
        long elapsed = endTime - startTime;
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '*') {
                count++;
            }
        }
        try {
            PrintWriter printTime = new PrintWriter(new FileOutputStream("A1/time2.txt", true));
            printTime.println("Number of *: " + count + "    Runtime: " + elapsed);
            printTime.close();
        } catch (FileNotFoundException e) {
            System.exit(-1);
        }
    }
}
