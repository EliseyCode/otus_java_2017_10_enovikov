package ru.enovikow.otus;

import ru.enovikow.otus.exceptions.NotEnoughMoneyException;

class XUtils {

    private static final int TOTAL_CELL_VALUE = 4;

    public static int[] sumToBeGiven(int[] availableNotes, int value) {
        int[] needValue = new int[TOTAL_CELL_VALUE];

        while (value > 0) {
            if (1000 <= value && availableNotes[0] > 0) {
                value -= 1000;
                needValue[0]++;
                availableNotes[0]--;
            } else if (500 <= value && availableNotes[1] > 0) {
                value -= 500;
                needValue[1]++;
                availableNotes[1]--;
            } else if (100 <= value && availableNotes[2] > 0) {
                value -= 100;
                needValue[2]++;
                availableNotes[2]--;
            } else if (50 <= value && availableNotes[3] > 0) {
                value -= 50;
                needValue[3]++;
                availableNotes[3]--;
            } else {
                throw new NotEnoughMoneyException("Not enough money");
            }
        }
        return needValue;
    }
}
