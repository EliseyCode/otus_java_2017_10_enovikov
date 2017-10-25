package utils;

import java.util.function.Supplier;

public class Measuring {

    public Measuring() {
    }

    public static <T> long getSize(int size, Supplier<T> supplier) {
        long memory_before;
        long memory_after;
        System.gc();
        memory_before = getMemory();

        Object[] array = new Object[size];
        memory_before = getMemory();
        for (int j = 0; j < size; j++) {
            array[j] = supplier.get();
        }

        memory_after = getMemory();
        array = null;
        System.gc();
        return (memory_after - memory_before) / size;
    }

    public static <T> void printSize(String text, Supplier<T> supplier, int size) {
        StringBuilder sb = new StringBuilder().append(text).append(Measuring.getSize(size, supplier));
        System.out.println(sb);
    }

    private static long getMemory() {
        return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }
}
