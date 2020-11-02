package project;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BackTrackingSudokuSolverTest {

    // check if there are empty cells (0 represents empty cell)
    private boolean emptyCheck(SudokuBoard board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board.getBoard(i, j) == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    // check if values are repeated in the same column
    private boolean columnCheck(SudokuBoard board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                for (int k = j + 1; k < 9; k++) {
                    if (board.getBoard(i, j) == board.getBoard(i, k)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // check if values are repeated in the same row
    private boolean rowCheck(SudokuBoard board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                for (int k = j + 1; k < 9; k++) {
                    if (board.getBoard(j, i) == board.getBoard(k, i)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // check if values are repeated in the segment
    private boolean segmentCheck(SudokuBoard board) {
        for (int k = 0; k < 81; k++) {
            int col = k / 9;
            int row = k % 9;

            int x = col - (col % 3);
            int y = row - (row % 3);

            for (int i = x; i < x + 3; i++) {
                for (int j = y; j < y + 3; j++) {
                    if (i != col && j != row) {
                        if (board.getBoard(i, j) == board.getBoard(col, row)) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    @Test
    void solveTest() {
        SudokuBoard board = new SudokuBoard();
        board.solveGame();

        // check if there are empty cells (0 represents empty cell)
        Assertions.assertTrue(emptyCheck(board));

        // check if there are empty cells (0 represents empty cell)
        Assertions.assertTrue(columnCheck(board));

        // check if values are repeated in the same row
        Assertions.assertTrue(rowCheck(board));

        // check if values are repeated in the segment
        Assertions.assertTrue(segmentCheck(board));
    }

    @Test
    void CheckIfAlwaysDifferent() {
        SudokuBoard board1 = new SudokuBoard();
        SudokuBoard board2 = new SudokuBoard();

        board1.solveGame();
        board2.solveGame();

        boolean different = false;

        for (int i = 0; i < 81; i++) {
            int col = i / 9;
            int row = i % 9;
            if (board1.getBoard(col, row) != board2.getBoard(col, row)) {
                different = true;
                break;
            }
        }

        Assertions.assertTrue(different);
    }
}