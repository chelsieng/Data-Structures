import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

public class MagicBoard_Ver4 {
    // Method to detect unsolvable boards configurations
    public static boolean MagicBoard(int x, int y, int[][] board) {
        // Checking every integer in column where zero goal position is located
        for (int i = 0; i < board.length; i++) {
            if (i == x) {
                continue; // skip zero goal position
            }
            int goUp = up(i, y, board); // new north position
            int goDown = down(i, y, board); // new south position
            // if position is valid and is zero goal position
            if (goUp >= 0 && board[goUp][y] == 0) {
                return false; // solvable
            }
            if (goDown < board.length && board[goDown][y] == 0) {
                return false; // solvable
            }
        }

        // Checking every integer in row where zero goal position is located
        for (int j = 0; j < board.length; j++) {
            if (y == j) {
                continue; // skip zero goal position
            }
            int goRight = right(x, j, board); // new east position
            int goLeft = left(x, j, board); // new west position
            // if position is valid and is zero goal position
            if (goRight < board.length && board[x][goRight] == 0) {
                return false; // solvable
            }
            if (goLeft >= 0 && board[x][goLeft] == 0) {
                return false; // solvable
            }
        }
        return true; // unsolvable
    }

    // New position when going north
    static int up(int x, int y, int[][] board) {
        x = x - board[x][y];
        return x;
    }

    // New position when going south
    static int down(int x, int y, int[][] board) {
        x = x + board[x][y];
        return x;
    }

    // New position when going east
    static int right(int x, int y, int[][] board) {
        y = y + board[x][y];
        return y;
    }

    // New position when going west
    static int left(int x, int y, int[][] board) {
        y = y - board[x][y];
        return y;
    }

    public static void main(String[] args) {
        try {
            PrintWriter writer = new PrintWriter(new FileOutputStream("A2/testLog_ver4.txt", true));
            // Welcome Message
            System.out.println(" ------------------------------ ");
            System.out.println("| Welcome to Magic Board Game! |");
            System.out.println(" ------------------------------ ");

            // Prompting user for size of board
            Scanner userIn = new Scanner(System.in);
            System.out.println("Please enter the size of your board: ");
            int size = userIn.nextInt();
            // If size entered by user < 5, keep prompting
            while (size < 5) {
                System.out.println("Too small, try again!");
                size = userIn.nextInt();
            }
            // Creating size x size array
            int[][] board = new int[size][size];
            Random random = new Random();
            int randZero1 = random.nextInt(size);
            int randZero2 = random.nextInt(size);
            int randNum = random.nextInt(size - 1) + 1;

            // Randomly placing numbers in array
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {
                    board[i][j] = randNum;
                    randNum = random.nextInt(size - 1) + 1;
                }
            }
            // Randomly placing 0 value in array
            board[randZero1][randZero2] = 0;

            //Uncomment for custom board (not possible to solve)
//            int[][] board = {{1, 4, 1, 3, 1},
//                    {4, 3, 2, 1, 4},
//                    {3, 2, 3, 1, 4},
//                    {1, 3, 4, 2, 3},
//                    {3, 4, 1, 2, 0}};

            // Uncomment for custom board (possible to solve)
//            int[][] board = {{4, 2, 1, 3, 1},
//                    {2, 3, 2, 1, 4},
//                    {3, 2, 3, 1, 4},
//                    {1, 3, 4, 2, 3},
//                    {3, 3, 1, 2, 0}};

            //Displaying board
            System.out.println("Your board looks like this: ");
            writer.println(" ------------------- ");
            writer.println("| Board size: " + board.length + " x " + board.length + " |");
            writer.println(" ------------------- ");
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {
                    System.out.print(board[i][j] + "\t");
                    writer.print(board[i][j] + "\t");
                }
                System.out.println();
                writer.println();
            }
            int x = 999;
            int y = 999;
            // Finding zero goal position (x,y)
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {
                    if (board[i][j] == 0) {
                        x = i;
                        y = j;
                    }
                }
            }
            writer.println("Zero Goal position: (" + x + ", " + y + ")");
            System.out.println("Zero Goal position: (" + x + ", " + y + ")");
            if (MagicBoard(x, y, board)) {
                System.out.println("It is not possible to solve the game.");
                writer.println("It is not possible to solve the game.\n");
            } else {
                System.out.println("It may be possible to solve the game.");
                writer.println("It may be possible to solve the game.\n");
            }
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to open/write to file. Program terminated.");
            System.exit(-1);
        }
    }
}
