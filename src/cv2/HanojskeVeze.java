package cv2;

public class HanojskeVeze {
    public static void main(String[] args) {
        int n = 5;

        char[][] tower = new char[n][3];

        // naplneni matice znaky
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                if (j == 0) {
                    tower[i][j] = (char)(65 + i);
                } else {
                    tower[i][j] = ':';
                }
            }
        }
        printTower(tower);
        solve(n,0,1,2, tower);
    }

    public static void solve(int n, int from, int middle, int to, char[][] tower) {
        if (n == 1) {
            System.out.println("Moving A from |" + (from + 1) + "| to |" + (to + 1) + "|");
            tower = move(from, to, tower);
            printTower(tower);
        } else {
            solve(n - 1, from, to, middle, tower);
            System.out.println("Moving " + (char)(65 + n) + " from |" + (from + 1) + "| to |" + (to + 1) + "|");
            tower = move(from, to, tower);
            printTower(tower);
            solve(n - 1, middle, from, to, tower);
        }
    }

    public static char[][] move(int from, int to, char[][] tower) {
        if (from < 0 || from >= tower[1].length || to < 0 || to >= tower[1].length)
            return tower;

        int x = 0;
        while (x < tower.length - 1)
            if (tower[x][from] == ':')
                x++;
            else
                break;

        char value = tower[x][from];
        if (value == ':')
            return tower;
        tower[x][from] = ':';

        x = 0;
        while (x < tower.length)
            if (tower[x][to] == ':')
                x++;
            else
                break;
        x--;

        tower[x][to] = value;

        return tower;
    }

    public static void printTower(char[][] tower) {
        for (int i = 0; i < tower.length; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(tower[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
