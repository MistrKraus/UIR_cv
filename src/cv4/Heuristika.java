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

    public static void aStar(ArrayList<City> cities, String target) {
        City start = cities.get(0);
        ArrayList<City> closed = new ArrayList<>();     // Množina již uzavřených uzlů.
        ArrayList<City> open = new ArrayList<>();       // Množina otevřených uzlů.
        open.add(start);
        City x;
        City y;
        boolean inClosed = false;
        int currGscore;

//        g_skore[start] := 0                                // Délka aktuální optimální cesty.
//        h_skore[start] := heuristický_odhad_vzdálenosti(start, cíl)
//        f_score[start] := h_skore[start]                   // Předpokládaná délka cesty mezi startem a cílem jdoucí přes y.

        while (!open.isEmpty()) {
            x = getOptimalCity(cities);

            if (x.equals(target))
                return; // TODO

            open.remove(x);
            closed.add(x);

            for (Path path : x.paths) {
                y = path.city2;

                for (City c : closed) {
                    if (y.equals(c.cityName)) {
                        inClosed = true;
                        break;
                    }
                }

                if (inClosed)
                    continue;

                //currGscore = x.g_score +
            }
        }
//        while openset is not empty
//        x := otevřený uzel s nejmenší hodnotou f_score[]
//        if x = cíl
//        return rekonstruuj_cestu(přišel_z[cíl])
//        vyjmi x z openset
//        přidej x do closedset
//        for each y in sousední_uzly(x)
//        if y in closedset
//        continue
//                stávající_g_skore := g_skore[x] + d(x, y)
//
//        if y not in openset
//        add y to openset
//        stávající_je_lepší := true
//        elseif stávající_g_skore < g_skore[y]
//        stávající_je_lepší := true
//            else
//        stávající_je_lepší := false
//        if stávající_je_lepší = true
//        přišel_z[y] := x
//        g_skore[y] := stávající_g_skore
//        h_skore[y] := heuristický_odhad_vzdálenosti(y, cíl)
//        f_score[y] := g_skore[y] + h_skore[y]
//        return failure
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

    public City(String cityNum, int h_skore) {
        this.cityName = cityNum;
        this.g_score = Integer.MAX_VALUE;
        this.h_score = h_skore;
        this.f_score = Integer.MAX_VALUE;
    }

    public void addPath(Path path) {
        paths.add(path);
    }

    public void setPrevious(City city) {
        this.previous = city;
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