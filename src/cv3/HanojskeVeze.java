package cv3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

public class HanojskeVeze {

    private static LinkedList<Tower> openBFS = new LinkedList<>();     // FIFO
    private static Stack<Tower> openDFS = new Stack<>();               // LIFO
    private static ArrayList<Tower> closedBFS = new ArrayList<>();
    private static ArrayList<Tower> closedDFS = new ArrayList<>();

    private static Tower SOLUTION;

    public static void main(String[] args) {
        int n = 3;
        int maxDepth = 300;
        SOLUTION = new Tower(n);
        SOLUTION.setSolutionState();

        Tower t = new Tower(n);

        openBFS.add(t.getTowerCopy());
        bfs();
        Tower t1 = closedBFS.get(closedBFS.size() - 1);
        while (t1.getPrevious() != null) {
            t1.printTower();
            t1 = t1.getPrevious();
        }
        t.printTower();

        openDFS.add(t.getTowerCopy());
        dfs(maxDepth);
        t1 = closedDFS.get(closedDFS.size() - 1);
        while (t1.getPrevious() != null) {
            t1.printTower();
            t1 = t1.getPrevious();
        }
        t.printTower();
    }

    public static void bfs() {
//        int count = 0;
        boolean visited = false;
        while (openBFS.size() > 0) {
            Tower t = openBFS.pollFirst();
            closedBFS.add(t);

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (i == j)
                        continue;

                    Tower t1 = (t.getTowerCopy()).move(i, j);
                    if (t1.equals(t))
                        continue;

                    for (Tower tower : closedBFS)
                        if (t1.equals(tower)) {
                            visited = true;
                            break;
                        }

                    if (visited) {
                        visited = false;
                        continue;
                    }

                    t1.setPrevious(t);
                    openBFS.add(t1);

                    if (t1.equals(SOLUTION)) {
                        System.out.println("Vyreseno pomoci BFS");
                        closedBFS.add(t1);
                        return;
                    }
                }
            }
        }
    }

    public static boolean dfs(int maxDepht) {
        boolean visited = false;
        while (!openDFS.empty()) {
            Tower t = openDFS.pop();

            if (t.equals(SOLUTION)) {
                System.out.println("Vyreseno pomoci DFS ");
                closedDFS.add(t);
                return true;
            }

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (j == i)
                        continue;

                    Tower t1 = (t.getTowerCopy()).move(i, j);
                    if (t1.equals(t) || t1.getDepth() > maxDepht)
                        continue;

                    for (Tower tower : closedDFS)
                        if (t1.equals(tower)) {
                            visited = true;
                            break;
                        }

                    if (visited) {
                        visited = false;
                        continue;
                    }

                    t1.setPrevious(t);
                    openDFS.add(t1);
                }
            }

            closedDFS.add(t);
        }
        System.out.println("DFS nevyresilo");

        return false;
    }
}

class Tower {
    private final int n;
    private char[][] state;
    private int depth;
    private Tower previous;

    public Tower(int n, int depth) {
        this(n);
        this.depth = depth;
    }

    public Tower(int n) {
        this.n = n;
        this.state = new char[n][3];
        this.depth = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                if (j == 0) {
                    this.state[i][j] = (char)(65 + i);
                } else {
                    this.state[i][j] = ':';
                }
            }
        }
    }

    public Tower(int n, char[][] state, int depth) {
        this(n, state);
        this.depth = depth;
    }

    public Tower(int n, char[][] state) {
        this.n = n;
        this.state = state;

    }

    public Tower move(int from, int to) {
        if (from < 0 || from >= state[1].length || to < 0 || to >= state[1].length || from == to)
            return this;

        int x = 0;
        while (x < state.length - 1)
            if (state[x][from] == ':')
                x++;
            else
                break;

        char value = state[x][from];
        if (value == ':') {
//            System.err.println("Nelze presunout - Chybi kotouc k presunuti.");
            return this;
        }

        int y = 0;
        while (y < state.length)
            if (state[y][to] == ':')
                y++;
            else
                break;
        y--;

        if (y < state.length - 1) {
            if ((int)value > ((int)state[y+1][to])) {
//                System.err.println("Nelze presunout - Spodni kotouc je mensi nez presouvany.");
                return this;
            }
        }

        state[x][from] = ':';
        state[y][to] = value;

        return this;
    }

    public void printTower() {
        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(state[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void setSolutionState() {
        for (int i = n - 1; i > -1; i--) {
            for (int j = 0; j < 3; j++) {
                if (j == 2) {
                    this.state[i][j] = (char)(65 + i);
                } else {
                    this.state[i][j] = ':';
                }
            }
        }
    }

    public Tower getTowerCopy() {
        char[][] ret = new char[this.n][3];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                ret[i][j] = this.state[i][j];
            }
        }

        return new Tower(this.n, ret, depth + 1);
    }

    public int getDepth() {
        return depth;
    }

    public void setPrevious(Tower t) {
        previous = t;
    }

    public Tower getPrevious() {
        return previous;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tower tower = (Tower) o;

        return Arrays.deepEquals(state, tower.state);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(state);
    }
}