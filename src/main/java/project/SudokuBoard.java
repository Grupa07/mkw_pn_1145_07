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

    private boolean checkIfValid(int x, int y, int val) {
        return (columnCheck(x, y, val) && rowCheck(x, y, val) && segmentCheck(x, y, val));
    }

    private boolean fillCells(int x, int y) {
        if (x == 9) {
            if (y == 8) {
                return true;
            }
            x = 0;
            y++;
        }

        int val = (int) (Math.random() * 9 + 1);
        for (int i = 0; i < 9; i++) {
            if (checkIfValid(x, y, val)) {
                setBoard(x, y, val);
                if (fillCells(x + 1, y)) {
                    return true;
                }
            }
            val = (val % 9) + 1;
        }
        setBoard(x, y, 0);
        return false;
    }

    public void fillBoard() {
        fillCells(0, 0);
    }
}