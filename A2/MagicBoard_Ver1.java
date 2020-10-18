import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class MagicBoard_Ver1 {
    public static boolean MagicBoard(int x, int y, int[][] board, boolean[][] validateBoard) {
        // Check if position is off board
        if (x < 0 || x >= board.length || y < 0 || y >= board.length) {
            return false;
        } else if (board[x][y] == 0) {
            return true; //Reached goal
        } else if (!validateBoard[x][y]) {
            return false; // already visited
        }
        validateBoard[x][y] = false; // visited
        // Go north, south, east or west
        return MagicBoard(up(x, y, board), y, board, validateBoard) || MagicBoard(down(x, y, board), y, board, validateBoard) || MagicBoard(x, right(x, y, board), board, validateBoard) || MagicBoard(x, left(x, y, board), board, validateBoard);
    }

    // Creating same board with boolean values instead
    static boolean[][] validateMagicBoard(int[][] board) {
        boolean[][] validateBoard = new boolean[board.length][board.length];
        for (int i = 0; i < validateBoard.length; i++) {
            for (int j = 0; j < validateBoard.length; j++) {
                validateBoard[i][j] = true;
            }
        }
        return validateBoard;
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
        // Test Log file
        try {
            PrintWriter writer = new PrintWriter(new FileOutputStream("A2/testLog_ver1.txt", true));
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

            //Uncomment for custom board
//        int [][]board= {{1,4,1,3,1},
//                {4,3,2,1,4},
//                {3,2,3,1,4},
//                {1,3,4,2,3},
//                {3,4,1,2,0}};

            //Displaying board
            System.out.println("Your board looks like this: ");
            writer.println(" ------------------- ");
            writer.println("| Board size: "+ board.length + " x " + board.length + " |");
            writer.println(" ------------------- ");
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {
                    System.out.print(board[i][j] + "\t");
                    writer.print(board[i][j] + "\t");
                }
                System.out.println();
                writer.println();
            }

            //Creating same board with boolean values to check if each position was visited
            boolean[][] boolBoard = validateMagicBoard(board);

            boolean illegal = true;
            int x = 999;
            int y = 999;

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
            writer.println("Start position: (" + x + ", " + y + ")");
            if (MagicBoard(x, y, board, boolBoard)) {
                System.out.println("It is possible to solve the game from start position.");
                writer.println("It is possible to solve the game from start position.\n");
            } else {
                System.out.println("It is not possible to solve the game from start position.");
                writer.println("It is not possible to solve the game from start position.\n");
            }
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to open/write to file. Program terminated.");
            System.exit(-1);
        }
    }
}
