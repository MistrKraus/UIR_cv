package cv2;

public class Faktorial {
    public static void main(String[] args) {
        System.out.println("Faktorial:\n");

        long x = 3;

        System.out.println("Rekurze: " + rekurze(x));
        System.out.println("Bez rekurze: " + bezRekurze(x));
    }

    public static long rekurze(long x) {
        if (x > 1) {
            x *= rekurze(x - 1);
        }

        return x;
    }

    public static long bezRekurze(long x) {
        long result = x;
        while (x > 1) {
            result *= --x;
        }

        return result;
    }
}
