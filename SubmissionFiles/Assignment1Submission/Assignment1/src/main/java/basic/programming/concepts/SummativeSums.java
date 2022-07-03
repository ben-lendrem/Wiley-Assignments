package basic.programming.concepts;

public class SummativeSums {
    public static void main(String[] args) {
        int[] exampleArr1 = { 1, 90, -33, -55, 67, -16, 28, -55, 15 };
        int[] exampleArr2 = { 999, -60, -77, 14, 160, 301 };
        int[] exampleArr3 = { 10, 20, 30, 40, 50, 60, 70, 80, 90, 100,
                110, 120, 130, 140, 150, 160, 170, 180, 190, 200, -99 };
        System.out.printf("#1 Array sum: %d\n" +
                "#2 Array sum: %d\n" +
                "#3 Array sum: %d",
                sumArray(exampleArr1), sumArray(exampleArr2), sumArray(exampleArr3));
    }

    static int sumArray(int[] arr) {
        int result = 0;
        for (int current : arr) {
            result += current;
        }
        return result;
    }
}
