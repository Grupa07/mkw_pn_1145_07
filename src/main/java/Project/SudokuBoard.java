package Project;

public class SudokuBoard {

    private int[][] board = new int[9][9];

    public int getBoard(int col, int row) {
        return board[col][row];
    }

    public void setBoard(int col, int row, int val) {
        board[col][row] = val;
    }

    private boolean checkIfValid(int col, int row) {

        int curVal = getBoard(col, row);

        // column check
        for (int i = 0; i < col; i++) {
            if (getBoard(i, row) == curVal) {
                return false;
            }
        }

        // row check
        for (int i = 0; i < row; i++) {
            if (getBoard(col, i) == curVal) {
                return false;
            }
        }

        // segment check
        int x = col - (col % 3); // coordinates of left top cell of the segment
        int y = row - (row % 3);

        for (int i = x; i < x + 3; i++) {
            for (int j = y; j < y + 3; j++) {
                if (getBoard(i, j) == curVal && !(i == col && j == row)) {
                    return false;
                }
            }
        }

        return true;
    }

    public void fillBoard() {

        int[] firstVal = new int[81];

        int index = 0;
        while (index < 81) {

            int col = index % 9;
            int row = index / 9;

            boolean leave = false;
            do {
                // jeśli komórka na którą patrzymy równa się zero
                // to znaczy, że nie było wartości początkowe (lub została wyzerowana)
                if (firstVal[index] == 0) {
                    firstVal[index] = (int) (Math.random() * 9 + 1);
                    setBoard(col, row, firstVal[index]);
                } else {
                    // ustawiamy liczbę o jedną większą od poprzedniej próbowanej
                    setBoard(col, row, (getBoard(col, row) % 9 + 1));

                    // jeśli obecnie wybrana wartość równa się pierwszej wylowanej wartości
                    // to sprawdziliśmy wszystkie liczby i żadnej nie można wstawić
                    if (getBoard(col, row) == firstVal[index]) {
                        firstVal[index] = 0;
                        index--;
                        break;
                    }
                }

                // sprawdzamy czy wstawiona liczba jest wstaiona według zasad sudoku
                if (checkIfValid(col, row)) {
                    // jeśli tak wychodzimy z wewnętrznej pętli i patrzymy na kolejną komórkę
                    index++;
                    leave = true;
                }
            } while (!leave);
        }
    }

}