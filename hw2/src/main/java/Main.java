import utils.Measuring;

import java.util.Arrays;
import java.util.List;

public class Main {
    private static int size;

    static {
        size = 1_000_000;
    }

    public static void main(String[] args) {

        System.out.println("Size of empty String: " + Measuring.getSize(size, () -> new String()));
        System.out.println("Size of Object: " + Measuring.getSize(size, () -> new Object()));
        System.out.println("Size of Object[] array: " + Measuring.getSize(size, () -> new Object[0]));

        System.out.println("Оценка роста размера контейнера от количества элементов в нем : ");
        List<Integer> x = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        x.stream().map(n -> {
            int[] arr = n <= 0 ? new int[1] : new int[n];
            for (int i = 0; i < n; i++) arr[i] = i;
            return arr;
        }).forEach(m -> Measuring.printSize("Array of int[" + m.length + "]: ", () -> m.clone(), size));
    }

}
