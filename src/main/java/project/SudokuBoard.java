package project;

public class SudokuBoard {

    private final SudokuField[][] board = new SudokuField[9][9];
    private final SudokuSolver solver;

    SudokuBoard(SudokuSolver solver) {
        this.solver = solver;
    }

    public int getBoard(int x, int y) {
        return board[x][y].getFieldValue();
    }

    public void setBoard(int x, int y, int val) {
        board[x][y].setFieldValue(val);
    }

    boolean checkBoard() {
        return true;
    }

    private boolean columnCheck(int x, int y, int val) {
        for (int i = 0; i < x; i++) {
            if (getBoard(i, y) == val) {
                return false;
            }
        }
        return true;
    }

    private boolean rowCheck(int x, int y, int val) {
        for (int i = 0; i < y; i++) {
            if (getBoard(x, i) == val) {
                return false;
            }
        }
        return true;
    }

    private boolean segmentCheck(int x, int y, int val) {
        int segX = x - (x % 3);
        int segY = y - (y % 3);

        for (int i = segX; i < segX + 3; i++) {
            for (int j = segY; j < segY + 3; j++) {
                if (getBoard(i, j) == val) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkIfValid(int x, int y, int val) {
        return (columnCheck(x, y, val) && rowCheck(x, y, val) && segmentCheck(x, y, val));
    }

    public void solveGame() {
        solver.solve(this);
    }
}