import java.util.Random;
import java.util.Scanner;

public class MagicBoard_Ver1 {
    public static boolean MagicBoard(int x, int y, int[][] board) {
        return true;
    }

    public static void main(String[] args) {
        // Welcome Message
        System.out.println(" ------------------------------ ");
        System.out.println("| Welcome to Magic Board Game! |");
        System.out.println(" ------------------------------ ");

        // Prompting user for size of board
        Scanner userIn = new Scanner(System.in);
//        System.out.println("Please enter the size of your board: ");
//        int size = userIn.nextInt();
//        // If size entered by user < 5, keep prompting
//        while (size < 5){
//            System.out.println("Too small, try again!");
//            size = userIn.nextInt();
//        }
        // Creating size x size array
//        int [][] board = new int[size][size];
        int[][] board = new int[5][5];
        Random random = new Random();
//        int randZero1 = randNum.nextInt(size);
//        int randZero2 = randNum.nextInt(size);
//        int randNum = random.nextInt(size-1) + 1;
        int randZero1 = random.nextInt(5);
        int randZero2 = random.nextInt(5);
        int randNum = random.nextInt(4) + 1;


        // Randomly placing numbers in array
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = randNum;
//                randNum = random.nextInt(size-1)+1;
                randNum = random.nextInt(4) + 1;
            }
        }
        // Randomly placing 0 value in array
        board[randZero1][randZero2] = 0;

        //Displaying board
        System.out.println("Your board looks like this: ");
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                System.out.print(board[i][j] + "\t");
            }
            System.out.println();
        }
        boolean illegal = true;
        // Keep prompting user to choose start position if position is illegal
        while (illegal) {
            // Prompting user to select start position
            System.out.println("\nChoose your legal start position (x,y): ");
            System.out.println("\t1. (0, 0)");
            System.out.println("\t2. (0, " + (board.length - 1) + ")");
            System.out.println("\t3. (" + (board.length - 1) + ", 0)");
            System.out.println("\t4. (" + (board.length - 1) + ", " + (board.length - 1) + ")");
            System.out.println(">>");
            int startAt = userIn.nextInt();
            // Keeping prompting user to select 1-4
            while (startAt <= 0 || startAt > 4) {
                System.out.println("\nChoose your legal start position (x,y): ");
                System.out.println("\t1. (0, 0)");
                System.out.println("\t2. (0, " + (board.length - 1) + ")");
                System.out.println("\t3. (" + (board.length - 1) + ", 0)");
                System.out.println("\t4. (" + (board.length - 1) + ", " + (board.length - 1) + ")");
                System.out.println(">>");
                startAt = userIn.nextInt();
            }
            int x = 999;
            int y = 999;
            if (startAt == 1) {
                x = 0;
                y = 0;
            } else if (startAt == 2) {
                x = 0;
                y = board.length - 1;
            } else if (startAt == 3) {
                x = board.length - 1;
                y = 0;
            } else if (startAt == 4) {
                x = board.length - 1;
                y = board.length - 1;
            }
            // Illegal start position at zero
            if (board[x][y] == 0) {
                System.out.println("Illegal start! Zero is the goal, try again!");
                illegal = true;
            } else {
                illegal = false; //Legal start position
            }
        }
    }
}
