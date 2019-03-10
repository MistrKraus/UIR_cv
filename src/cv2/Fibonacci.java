package cv2;

public class Fibonacci {
    public static void main(String[] args) {
        System.out.println("Fibonacci:\n");

        int n = 9;

        System.out.println("Rekurze:");
        System.out.println(rekurze(n));
        System.out.println("Bez rekurze:");
        System.out.println(bezRekurze(n));
    }

    public static int rekurze(int n) {
        if (n <= 1)
            return 0;

        if (n == 2)
            return 1;

        return rekurze(n - 1) + rekurze(n - 2);
    }

    public static int bezRekurze(int n) {
        if (n <= 1)
            return 0;


        if (n == 2) {
            return 1;
        }

        int[] arr = new int[n];

        arr[0] = 0;
        arr[1] = 1;

        for (int i = 2; i < n; i++)
            arr[i] = arr[i - 2] + arr[i - 1];

        return arr[n - 1];
    }
}
