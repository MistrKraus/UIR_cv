package cv4;

import java.util.ArrayList;

public class Heuristika {


    public static void main(String[] args) {
        ArrayList<City> cities = createInfrastructure();
        aStar(cities, "G");
    }

    public static ArrayList<City> createInfrastructure() {
        ArrayList<City> cities = new ArrayList<>();

        City city1 = new City("S", 52);
        City city2 = new City("A", 26);
        City city3 = new City("B", 47);
        City city4 = new City("C", 14);
        City city5 = new City("G", Integer.MAX_VALUE);

        cities.add(city1);
        cities.add(city2);
        cities.add(city3);
        cities.add(city4);
        cities.add(city5);

        // S
        city1.addPath(new Path(city1, city2, 36));
        city1.addPath(new Path(city1, city3, 11));

        // A
        city2.addPath(new Path(city2, city1, 36));
        city2.addPath(new Path(city2, city4, 13));
        city2.addPath(new Path(city2, city5, 43));

        // B
        city3.addPath(new Path(city3, city1, 11));
        city3.addPath(new Path(city3, city4, 31));

        // C
        city4.addPath(new Path(city4, city2, 13));
        city4.addPath(new Path(city4, city3, 31));
        city4.addPath(new Path(city4, city5, 19));

        // G
        city5.addPath(new Path(city5, city2, 43));
        city5.addPath(new Path(city5, city4, 19));

        return cities;
    }

    public static boolean aStar(ArrayList<City> cities, String target) {
        City start = cities.get(0);
        ArrayList<City> closed = new ArrayList<>();     // Množina již uzavřených uzlů.
        ArrayList<City> open = new ArrayList<>();       // Množina otevřených uzlů.
        open.add(start);
        City x;
        City y;
        boolean currBetter;
        int currGscore;

        System.out.println("Processing: ");
        while (!open.isEmpty()) {
            x = getOptimalCity(open);

            if (x.cityName.equals(target)) {
                System.out.println("Optimalni cesta:");
                reconstructPath(x);
                return true;
            }

            open.remove(x);
            closed.add(x);
            System.out.println(x.cityName);

            for (Path path : x.paths) {
                y = path.city2;

                if (closed.contains(y))
                    continue;


                currGscore = x.g_score + path.distance;

                if (!open.contains(y)) {
                    open.add(y);
                    currBetter = true;
                } else {
                    currBetter = currGscore < y.g_score;
                }

                if (currBetter) {
                    y.previous = x;
                    y.g_score = currGscore;
                    y.f_score = y.g_score + y.h_score;
                }
            }
        }

        return false;
    }

    public static void reconstructPath(City c) {
        int distance = 0;
        System.out.print(c.cityName + " <- ");
        while (c.previous != null) {
            distance += c.getPathTo(c.previous).distance;
            c = c.previous;
            System.out.print(c.cityName + " <- ");
        }

        System.out.println("Urazena vzdalenost: " + distance);
    }

    public static City getOptimalCity(ArrayList<City> cities) {
        City x = cities.get(0);
        int temp = cities.get(0).f_score;

        for (int i = 1; i < cities.size(); i++) {
            if (temp > cities.get(i).f_score) {
                temp = cities.get(i).f_score;
                x = cities.get(i);
            }
        }

        return x;
    }
}

class City {
    public final String cityName;
    public City previous;
    public int g_score;
    public int h_score;
    public int f_score;
    public ArrayList<Path> paths = new ArrayList<>();

    public City(String cityNum, int h_score) {
        h_score = 0;
        this.cityName = cityNum;
        this.g_score = 0;
        this.h_score = h_score;
        this.f_score = h_score;
    }

    public void addPath(Path path) {
        paths.add(path);
    }

    public Path getPathTo(City target) {
        for (Path p : paths) {
            if (p.city2.cityName.equals(target.cityName))
                return p;
        }

        return null;
    }

}

class Path {
    public final City city1;
    public final City city2;
    public final int distance;

    public Path(City city1, City city2, int distance) {
        this.city1 = city1;
        this.city2 = city2;
        this.distance = distance;
    }
}
