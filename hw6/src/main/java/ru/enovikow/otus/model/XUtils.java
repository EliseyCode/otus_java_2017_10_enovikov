package ru.enovikow.otus.model;

public class XUtils {

    private static final int TOTAL_CELL_VALUE = 4;

    public static int[] getTotalNotesCount(int value) {
        int[] values = new int[TOTAL_CELL_VALUE];

        int thousands = value / 1000;
        value -= thousands * 1000;
        int fiveHundreds = value / 500;
        value -= fiveHundreds * 500;
        int oneHundreds = value / 100;
        value -= oneHundreds * 100;
        int fifty = value / 50;
        value -= fifty * 50;

        values[0] = thousands;
        values[1] = fiveHundreds;
        values[2] = oneHundreds;
        values[3] = fifty;
        return values;
    }
}
