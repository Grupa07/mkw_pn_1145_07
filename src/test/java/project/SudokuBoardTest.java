package project;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SudokuBoardTest {

    @Test
    public void fillBorderTest() {
        SudokuBoard sudokuBoard = new SudokuBoard();
        sudokuBoard.fillBoard();

        // check if there are empty cells (0 represents empty cell)
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                Assertions.assertNotEquals(sudokuBoard.getBoard(i, j), 0);
            }
        }

        // check if values are repeated in the same column
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                for (int k = j + 1; k < 9; k++) {
                    Assertions.assertNotEquals(sudokuBoard.getBoard(i, j), sudokuBoard.getBoard(i, k));
                }
            }
        }
        // check if values are repeated in the same row
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                for (int k = j + 1; k < 9; k++) {
                    Assertions.assertNotEquals(sudokuBoard.getBoard(j, i), sudokuBoard.getBoard(k, i));
                }
            }
        }

        // check if values are repeated in the segment
        for (int k = 0; k < 81; k++) {
            int col = k / 9;
            int row = k % 9;

            int x = col - (col % 3);
            int y = row - (row % 3);

            for (int i = x; i < x + 3; i++) {
                for (int j = y; j < y + 3; j++) {
                    if (i != col && j != row) {
                        Assertions.assertNotEquals(sudokuBoard.getBoard(i, j), sudokuBoard.getBoard(col,row));
                    } else if (i == col && j == row) {
                        Assertions.assertEquals(sudokuBoard.getBoard(i, j), sudokuBoard.getBoard(col,row));
                    }
                }
            }
        }
    }

    @Test
    void CheckIfAlwaysDifferent() {
        SudokuBoard sudokuBoard1 = new SudokuBoard();
        SudokuBoard sudokuBoard2 = new SudokuBoard();

        sudokuBoard1.fillBoard();
        sudokuBoard2.fillBoard();

        boolean different = false;

        for (int i = 0; i < 81; i++) {
            int col = i / 9;
            int row = i % 9;
            if (sudokuBoard1.getBoard(i, i) != sudokuBoard2.getBoard(i, i)) {
                different = true;
                break;
            }
        }

        Assertions.assertTrue(different);
    }
}