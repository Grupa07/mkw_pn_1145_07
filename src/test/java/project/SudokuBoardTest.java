package project;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SudokuBoardTest {

    @Test
    public void fillBorderTest() {
        SudokuBoard sudokuBoard = new SudokuBoard();
        sudokuBoard.fillBoard();

        int col = (int) (Math.random() * 9);
        int row = (int) (Math.random() * 9);

        int x = col - (col % 3); // coordinates of left top cell of the segment
        int y = row - (row % 3);

        int testVal = sudokuBoard.getBoard(col, row);

        // check if there are empty cells (0 represents empty cell)
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Assertions.assertNotEquals(sudokuBoard.getBoard(i, j), 0);
            }
        }

        // check if values are repeated in the same column
        for (int i = 0; i < 9; i++) {
            if (i != row) Assertions.assertNotEquals(sudokuBoard.getBoard(col, i), testVal);
            else Assertions.assertEquals(sudokuBoard.getBoard(col, i), testVal);
        }

        // check if values are repeated in the same row
        for (int i = 0; i < 9; i++) {
            if (i != col) Assertions.assertNotEquals(sudokuBoard.getBoard(i, row), testVal);
            else Assertions.assertEquals(sudokuBoard.getBoard(i, row), testVal);
        }

        // Check if values are repeated in the segment
        for (int i = x; i < x + 3; i++) {
            for (int j = y; j < y + 3; j++) {
                if (i != col && j != row) {
                    Assertions.assertNotEquals(sudokuBoard.getBoard(i, j), testVal);
                } else if (i == col && j == row) {
                    Assertions.assertEquals(sudokuBoard.getBoard(i, j), testVal);
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

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sudokuBoard1.getBoard(i, j) != sudokuBoard2.getBoard(i, j)) {
                    different = true;
                }
            }
        }

        Assertions.assertEquals(different, true);
    }
}