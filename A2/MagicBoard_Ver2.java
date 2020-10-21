import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.*;

public class MagicBoard_Ver2 {
    // Iterative method
    public static boolean MagicBoard(int x, int y, int[][] board) {
        Pair<Integer, Integer> position = new Pair<>(x, y);
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        // Adding position in queue
        queue.add(position);
        // Until queue is empty
        while (!queue.isEmpty()) {
            Pair<Integer, Integer> p = queue.peek(); // Get the head of the queue
            queue.remove(); // Dequeue

            // Calculating new legal positions
            int goUp = up(p.first, p.second, board); // Go north
            int goDown = down(p.first, p.second, board); // Go south
            int goRight = right(p.first, p.second, board); // Go east
            int goLeft = left(p.first, p.second, board); // Go west

            // Goal is reached
            if (board[p.first][p.second] == 0) {
                return true;
            }

            // If new position is not visited and off board add to queue
            if (board[p.first][p.second] != board.length && goUp >= 0) {
                queue.add(new Pair<>(goUp, p.second));
            }
            // If new position is not visited and off board add to queue
            if (board[p.first][p.second] != board.length && goDown < board.length) {
                queue.add(new Pair<>(goDown, p.second));
            }
            // If new position is not visited and off board add to queue
            if (board[p.first][p.second] != board.length && goRight < board.length) {
                queue.add(new Pair<>(p.first, goRight));
            }
            // If new position is not visited and off board add to queue
            if (board[p.first][p.second] != board.length && goLeft >= 0) {
                queue.add(new Pair<>(p.first, goLeft));
            }
            // Mark as visited
            board[p.first][p.second] = board.length;
        }
        return false; // Goal not reached
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

    // Pair Class to store (x,y) position of board
    static class Pair<U, V> {
        public U first; // x position
        public V second; // y position

        private Pair(U first, V second) {
            this.first = first;
            this.second = second;
        }
    }

    public static void main(String[] args) {
        try {
            PrintWriter writer = new PrintWriter(new FileOutputStream("A2/testLog_ver2.txt", true));
            // Welcome Message
            System.out.println(" ------------------------------ ");
            System.out.println("| Welcome to Magic Board Game! |");
            System.out.println(" ------------------------------ ");

//            // Prompting user for size of board
            Scanner userIn = new Scanner(System.in);
//            System.out.println("Please enter the size of your board: ");
//            int size = userIn.nextInt();
//            // If size entered by user < 5, keep prompting
//            while (size < 5) {
//                System.out.println("Too small, try again!");
//                size = userIn.nextInt();
//            }
//            // Creating size x size array
//            int[][] board = new int[size][size];
//            Random random = new Random();
//            int randZero1 = random.nextInt(size);
//            int randZero2 = random.nextInt(size);
//            int randNum = random.nextInt(size - 1) + 1;
//
//            // Randomly placing numbers in array
//            for (int i = 0; i < board.length; i++) {
//                for (int j = 0; j < board.length; j++) {
//                    board[i][j] = randNum;
//                    randNum = random.nextInt(size - 1) + 1;
//                }
//            }
//        // Randomly placing 0 value in array
//            board[randZero1][randZero2] = 0;

            //Uncomment for custom board (not possible to solve)
//        int [][]board= {{1,4,1,3,1},
//                {4,3,2,1,4},
//                {3,2,3,1,4},
//                {1,3,4,2,3},
//                {3,4,1,2,0}};

            // Uncomment for custom board (possible to solve)
        int[][] board = {{4, 2, 1, 3, 1},
                {2, 3, 2, 1, 4},
                {3, 2, 3, 1, 4},
                {1, 3, 4, 2, 3},
                {3, 3, 1, 2, 0}};

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
            if (MagicBoard(x, y, board)) {
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
