package project;

public class SudokuBoard {

    private final int[][] board = new int[9][9];

    public int getBoard(int x, int y) {
        return board[x][y];
    }

    public void setBoard(int x, int y, int val) {
        board[x][y] = val;
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
                if (getBoard(i, j) == val && !(i == x && j == y)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkIfValid(int x, int y, int val) {
        return (columnCheck(x, y, val) && rowCheck(x, y, val) && segmentCheck(x, y, val));
    }



    public void fillBoard() {
        BacktrackingSudokuSolver s = new BacktrackingSudokuSolver();
        s.solve(this,0,0);
    }
}