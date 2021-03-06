package project;

public class BacktrackingSudokuSolver implements SudokuSolver {

    private SudokuBoard board;

    private boolean fillBoard(int x, int y) {
        if (x == 9) {
            if (y == 8) {
                return true;
            }
            x = 0;
            y++;
        }

        int val = (int) (Math.random() * 9 + 1);
        for (int i = 0; i < 9; i++) {
            if (board.checkIfValid(x, y, val)) {
                board.setBoard(x, y, val);
                if (fillBoard(x + 1, y)) {
                    return true;
                }
            }
            val = (val % 9) + 1;
        }
        board.setBoard(x, y, 0);
        return false;
    }

    @Override
    public void solve(SudokuBoard board) {
        this.board = board;
        fillBoard(0, 0);
    }
}