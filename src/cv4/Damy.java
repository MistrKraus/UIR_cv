package cv4;

public class Damy {

    public static void main(String[] args) {
        int n = 4;

        Chessboard ch = new Chessboard(n, null);

        ch.place4queens(ch, 0);
    }
}

class Chessboard {

    private int queenCount = 0;
    private int[][] matrix;
    private final int n;
    private final Chessboard previous;
    private final static int queen = 1;

    public Chessboard(int n, Chessboard previous) {
        this.n = n;
        this.previous = previous;
        matrix = new int[n][n];
    }

    private void addQueen(int y, int x) {
        for (int i = 0; i < n; i++) {
            // kontrola radku
            if (!isFree(y, i))
                return;

            // kontrola sloupce
            if (!isFree(i, x))
                return;

            // kontrola jedne diagonaly
            if (!isFree(y - i, x - i))
                return;

            if (!isFree(y + i, x + i))
                return;

            // kontrola druhe diagonly
            if (!isFree(y + i, x - i))
                return;

            if (!isFree(y - i, x + i))
                return;
        }

        matrix[y][x] = queen;
        queenCount++;
    }

    /**
     * Umisti 4 kralovny do hraciho pole
     *
     * @param ch hraci pole
     * @param row radek, do ktereho se umisti prvni
     */
    public void place4queens(Chessboard ch, int row) {
        if (ch.queenCount >= 4) {
            Chessboard prev = ch;
            while (prev != null) {
                prev.printChessboard();
                prev = prev.previous;
            }
            System.out.println("-----");
            return;
        }

        if (row == 4)
            return;

        Chessboard ch1 = ch.getCopy();
        for (int i = 0; i < n; i++) {
            ch1.addQueen(row, i);
            if (ch1.queenCount > ch.queenCount) {
                place4queens(ch1, row + 1);
                ch1.spitOnTheFakeQueen(row, i);
            }
        }
    }

    public void spitOnTheFakeQueen(int y, int x) {
        if (y < 0 || y >= n || x < 0 || x >= n || matrix[y][x] == 0)
            return;

        matrix[y][x] = 0;
        queenCount--;
    }
    
    private boolean isFree(int y, int x) {
        if (y < 0 || y >= n || x < 0 || x >= n)
            return true;

        return matrix[y][x] != queen;
    }

    public void printChessboard() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public int getQueenCount() {
        return queenCount;
    }

    public Chessboard getPrevious() {
        return previous;
    }

    private void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public void setQueenCount(int queenCount) {
        this.queenCount = queenCount;
    }

    public Chessboard getCopy() {
        Chessboard copy = new Chessboard(n, this);
        int[][] copyMatrix = new int[n][n];

//        copyMatrix = Arrays.copyOf(matrix, n);

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                copyMatrix[i][j] = matrix[i][j];

        copy.setMatrix(copyMatrix);
        copy.setQueenCount(this.queenCount);

        return copy;
    }
}
