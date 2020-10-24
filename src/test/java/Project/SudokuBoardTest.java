package Project;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SudokuBoardTest {

    @Test
    public void fillBorderTest() {
        SudokuBoard sudokuBoard = new SudokuBoard();
        sudokuBoard.fillBoard();

        int testVal = sudokuBoard.getBoard(0, 0);

        // check if there are empty cells (0 represents empty cell)
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Assertions.assertNotEquals(sudokuBoard.getBoard(i, j), 0);
            }
        }

        // check if values are repeated in the same column
        for (int i = 1; i < 9; i++) {
            Assertions.assertNotEquals(sudokuBoard.getBoard(i, 0), testVal);
        }

        // check if values are repeated in the same row
        for (int i = 1; i < 9; i++) {
            Assertions.assertNotEquals(sudokuBoard.getBoard(0, i), testVal);
        }

        // check if values are repeated in the segment
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i != 0 && j != 0) {
                    Assertions.assertNotEquals(sudokuBoard.getBoard(i, j), testVal);
                }
            }
        }
    }
}
