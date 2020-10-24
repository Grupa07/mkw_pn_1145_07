package Project;

public class SudokuBoard {

    private int[][] board = new int[9][9];

    public int getBoard(int col, int row) {
        return board[col][row];
    }

    public void setBoard(int col, int row, int val) {
        board[col][row] = val;
    }

    private boolean checkAvailability(int col, int row, int val) {

        // column check
        for (int i = 0; i < 9; i++) {
            if (getBoard(col, i) == val) {
                return false;
            }
        }

        // row check
        for (int i = 0; i < 9; i++) {
            if (getBoard(i, row) == val) {
                return false;
            }
        }

        // segment check
        int x = col - (col % 3); // coordinates of left top cell of the segment
        int y = row - (row % 3);

        for (int i = x; i < x + 3; i++) {
            for (int j = y ; j < y + 3; j++) {
                if (getBoard(i, j) == val) {
                    return false;
                }
            }
        }

        return true;
    }

    public void fillBoard() {

    }
}