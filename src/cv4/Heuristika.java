package cv4;

import java.util.ArrayList;

public class Heuristika {

    public static void main(String[] args) {
        City cityS = createInfrastructure();
        aStar(cityS, "G");
    }

    public static City createInfrastructure() {
        City city1 = new City("S");
        City city2 = new City("A");
        City city3 = new City("B");
        City city4 = new City("C");
        City city5 = new City("G");

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

        return city1;
    }

    public static void aStar(City start, String target) {
        ArrayList<City> closed = new ArrayList<>();     // Množina již uzavřených uzlů.
        ArrayList<City> open = new ArrayList<>();       // Množina otevřených uzlů.
        open.add(start);

//        g_skore[start] := 0                                // Délka aktuální optimální cesty.
//        h_skore[start] := heuristický_odhad_vzdálenosti(start, cíl)
//        f_skore[start] := h_skore[start]                   // Předpokládaná délka cesty mezi startem a cílem jdoucí přes y.

        while (!open.isEmpty()) {

        }
//        while openset is not empty
//        x := otevřený uzel s nejmenší hodnotou f_skore[]
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
//        f_skore[y] := g_skore[y] + h_skore[y]
//        return failure
    }
}

class City {
    public final String cityName;
    public City previous;
    public ArrayList<Path> paths = new ArrayList<>();

    public City(String cityNum) {
        this.cityName = cityNum;
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