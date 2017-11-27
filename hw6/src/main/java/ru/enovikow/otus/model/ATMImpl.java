package ru.enovikow.otus.model;

import java.util.ArrayList;
import java.util.List;

public class ATMImpl implements ATM {

    private List<OneThousand> oneThousandCell;
    private List<FiveHundred> fiveHundredCell;
    private List<OneHundred> oneHundredCell;
    private List<Fifty> fiftyCell;

    {
        oneThousandCell = new ArrayList<>();
        fiveHundredCell = new ArrayList<>();
        oneHundredCell = new ArrayList<>();
        fiftyCell = new ArrayList<>();
    }


    public void insertSum(Money note) throws Exception {
        if (note instanceof OneThousand) {
            oneThousandCell.add((OneThousand) note);
        } else if (note instanceof FiveHundred) {
            fiveHundredCell.add((FiveHundred) note);
        } else if (note instanceof OneHundred) {
            oneHundredCell.add((OneHundred) note);
        } else if (note instanceof Fifty) {
            fiftyCell.add((Fifty) note);
        } else {
            throw new Exception();
        }

    }

    public void giveOutSum(int value) {
//        int[] totalNotes = XUtils.getTotalNotesCount(value);
//        giveOutNotes(totalNotes);
//            int thousands = getOneThousandCellSize();
//            int fiveHundreds = getFiveHundredCellSize();
//            int oneHundreds = getOneHundredCellSize();
//            int fifty = getFiftyCellSize();

            int[] availableNotes = new int[] {getOneThousandCellSize(), getFiveHundredCellSize(), getOneHundredCellSize(), getFiftyCellSize()};
            int[] toGive = XUtils.sumToBeGiven(availableNotes, value);


    }

//    public static int[] getTotalNotesCount(int value) {
//        int[] values = new int[TOTAL_CELL_VALUE];
//
//        int thousands = value / 1000;
//        value -= thousands * 1000;
//        int fiveHundreds = value / 500;
//        value -= fiveHundreds * 500;
//        int oneHundreds = value / 100;
//        value -= oneHundreds * 100;
//        int fifty = value / 50;
//        value -= fifty * 50;
//
//        values[0] = thousands;
//        values[1] = fiveHundreds;
//        values[2] = oneHundreds;
//        values[3] = fifty;
//        return values;
//    }

    private void giveOutNotes(int[] totalNotes) {

    }

    public void printSum() {
        int totalCount = getOneThousandCellSize() * 1000
                + getFiveHundredCellSize() * 500
                + getOneHundredCellSize() * 100
                + getFiftyCellSize() * 50;

        System.out.format("Total sum: %d Thousands %d FiveHundreds %d Hundreds %d Fifty %d", totalCount,
                getOneThousandCellSize(), getFiveHundredCellSize(), getOneHundredCellSize(), getFiftyCellSize());
    }

    private void addNotes(int[] totalNotes) {
        for (int i = 0; i < totalNotes.length; i++) {
            switch (i) {
                case 0: {
                    while (totalNotes[0] > 0) {
                        addOneThousandInCell();
                        totalNotes[0]--;
                    }
                    break;
                }
                case 1: {
                    while (totalNotes[1] > 0) {
                        addFiveHundredInCell();
                        totalNotes[1]--;
                    }
                    break;
                }
                case 2: {
                    while (totalNotes[2] > 0) {
                        addOneHundredInCell();
                        totalNotes[2]--;
                    }
                    break;
                }
                case 3: {
                    while (totalNotes[3] > 0) {
                        addFiftyInCell();
                        totalNotes[3]--;
                    }
                    break;
                }
            }
        }
    }

    void addOneThousandInCell() {
        oneThousandCell.add(new OneThousand());
    }

    void addFiveHundredInCell() {
        fiveHundredCell.add(new FiveHundred());
    }

    void addOneHundredInCell() {
        oneHundredCell.add(new OneHundred());
    }

    void addFiftyInCell() {
        fiftyCell.add(new Fifty());
    }

    int getOneThousandCellSize() {
        return oneThousandCell.size();
    }

    int getFiveHundredCellSize() {
        return fiveHundredCell.size();
    }

    int getOneHundredCellSize() {
        return oneHundredCell.size();
    }

    int getFiftyCellSize() {
        return fiftyCell.size();
    }
}
