import java.util.*;
import java.util.stream.Collectors;

public class Minesweeper {
    public static final int BEGINNER = 0;
    public static final int INTERMEDIATE = 1;
    public static final int ADVANCED = 2;

    public static List<String> minesCoordinates = new ArrayList<>();
    public static int SIDE;
    public static int MINES;
    public static int MOVES_LEFT;

    public static boolean isValid(int row, int col) {
        return (row >= 0) && (row < SIDE) && (col >= 0) && (col < SIDE);
    }

    public static boolean isMine(int row, int col, char[][] board) {
        return board[row][col] == '*';
    }

    public static int[] makeMove() {
        System.out.println("Enter your move, (row, column) ->");
        Scanner scanner = new Scanner(System.in);
        int row, col;
        List<Integer> input = Arrays.stream(scanner.nextLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        row = input.get(0);
        col = input.get(1);
        return new int[]{row, col};
    }

    public static void printBoard(char[][] board) {
        System.out.println("Current Status of Board:");
        System.out.print("  ");
        for (int i = 0; i < SIDE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIDE; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < SIDE; j++) {
                System.out.print(String.format("%c ", board[i][j]));
            }
            System.out.println();
        }
    }

    public static int countAdjacentMines(int row, int col, char[][] board) {
        int count = 0;
        if (isValid(row - 1, col)) {
            if (isMine(row - 1, col, board)) {
                count++;
            }
        }

        if (isValid(row + 1, col)) {
            if (isMine(row + 1, col, board)) {
                count++;
            }
        }

        if (isValid(row, col + 1)) {
            if (isMine(row, col + 1, board)) {
                count++;
            }
        }

        if (isValid(row, col - 1)) {
            if (isMine(row, col - 1, board)) {
                count++;
            }
        }

        if (isValid(row - 1, col + 1)) {
            if (isMine(row - 1, col + 1, board)) {
                count++;
            }
        }

        if (isValid(row - 1, col - 1)) {
            if (isMine(row - 1, col - 1, board)) {
                count++;
            }
        }

        if (isValid(row + 1, col + 1)) {
            if (isMine(row + 1, col + 1, board)) {
                count++;
            }
        }

        if (isValid(row + 1, col - 1)) {
            if (isMine(row + 1, col - 1, board)) {
                count++;
            }
        }

        return count;
    }

    public static boolean playMinesweeperUtil(char[][] myBoard, char[][] realBoard, int row, int col) {
        if (myBoard[row][col] != '-') {
            return false;
        }

        if (realBoard[row][col] == '*') {
            myBoard[row][col] = '*';
            for (int i = 0; i < MINES; i++) {
                List<String> coordinates = Arrays.stream(minesCoordinates.get(i).split(",")).collect(Collectors.toList());
                int mineRow = Integer.parseInt(coordinates.get(0));
                int mineCol = Integer.parseInt(coordinates.get(1));
                myBoard[mineRow][mineCol] = '*';
            }
            printBoard(myBoard);
            System.out.println("You lost!");
            return true;
        } else {
            int count = countAdjacentMines(row, col, realBoard);
            MOVES_LEFT--;
            String value = String.valueOf(count);
            myBoard[row][col] = value.charAt(0);

            if (count == 0) {
                if (isValid(row - 1, col)) {
                    if (!isMine(row - 1, col, realBoard)) {
                        playMinesweeperUtil(myBoard, realBoard, row - 1, col);
                    }
                }

                if (isValid(row + 1, col)) {
                    if (!isMine(row + 1, col, realBoard)) {
                        playMinesweeperUtil(myBoard, realBoard, row + 1, col);
                    }
                }

                if (isValid(row, col + 1)) {
                    if (!isMine(row, col + 1, realBoard)) {
                        playMinesweeperUtil(myBoard, realBoard, row, col + 1);
                    }
                }

                if (isValid(row, col - 1)) {
                    if (!isMine(row, col - 1, realBoard)) {
                        playMinesweeperUtil(myBoard, realBoard, row, col - 1);
                    }
                }

                if (isValid(row - 1, col + 1)) {
                    if (!isMine(row - 1, col + 1, realBoard)) {
                        playMinesweeperUtil(myBoard, realBoard, row - 1, col + 1);
                    }
                }

                if (isValid(row - 1, col - 1)) {
                    if (!isMine(row - 1, col - 1, realBoard)) {
                        playMinesweeperUtil(myBoard, realBoard, row - 1, col - 1);
                    }
                }

                if (isValid(row + 1, col + 1)) {
                    if (!isMine(row + 1, col + 1, realBoard)) {
                        playMinesweeperUtil(myBoard, realBoard, row + 1, col + 1);
                    }
                }

                if (isValid(row + 1, col - 1)) {
                    if (!isMine(row + 1, col - 1, realBoard)) {
                        playMinesweeperUtil(myBoard, realBoard, row + 1, col - 1);
                    }
                }

            }
            return false;
        }
    }

    public static void placeMines(char[][] realBoard) {
        Random random = new Random();
        for (int i = 0; i < MINES; i++) {

            int row = random.nextInt(SIDE - 1);
            int col = random.nextInt(SIDE - 1);
            while (realBoard[row][col] == '*') {
                row = random.nextInt(SIDE - 1);
                col = random.nextInt(SIDE - 1);
            }
            realBoard[row][col] = '*';
            minesCoordinates.add(row + "," + col);
        }
    }

    public static void initialise(char[][] realBoard, char[][] myBoard) {
        for (int i = 0; i < SIDE; i++) {
            for (int j = 0; j < SIDE; j++) {
                realBoard[i][j] = myBoard[i][j] = '-';
            }
        }
    }

    public static void replaceMine(int row, int col, char[][] board) {
        for (int i = 0; i < SIDE; i++) {
            for (int j = 0; j < SIDE; j++) {
                if (board[i][j] != '*') {
                    board[i][j] = '*';
                    board[row][col] = '-';
                }
            }
        }
    }

    public static void playMinesweeper() {
        boolean gameOver = false;
        char[][] realBoard = new char[SIDE][SIDE];
        char[][] myBoard = new char[SIDE][SIDE];
        MOVES_LEFT = SIDE * SIDE - MINES;
        initialise(realBoard, myBoard);

        placeMines(realBoard);

        int currentMoveIndex = 0;
        while (!gameOver) {
            printBoard(myBoard);
            int[] coordinates = makeMove();
            int row = coordinates[0];
            int col = coordinates[1];
            if (currentMoveIndex == 0) {
                if (isMine(row, col, realBoard)) {
                    replaceMine(row, col, realBoard);
                }
            }
            currentMoveIndex++;

            gameOver = playMinesweeperUtil(myBoard, realBoard, row, col);

            if (!gameOver && MOVES_LEFT == 0) {
                System.out.println("You won!");
                gameOver = true;
            }
        }
    }

    public static void chooseDifficultyLevel() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the Difficulty level");
        System.out.println("Press 0 for BEGINNER (9 * 9 Cells and 10 Mines)");
        System.out.println("Press 1 for INTERMEDIATE (16 * 16 Cells and 40 Mines)");
        System.out.println("Press 2 for ADVANCED (24 * 24 Cells and 99 Mines)");
        int level = Integer.parseInt(scanner.nextLine());
        if (level == BEGINNER) {
            SIDE = 9;
            MINES = 10;
        }

        if (level == INTERMEDIATE) {
            SIDE = 16;
            MINES = 40;
        }

        if (level == ADVANCED) {
            SIDE = 24;
            MINES = 99;
        }
    }

    public static void main(String[] args) {
        chooseDifficultyLevel();
        playMinesweeper();
    }
}
