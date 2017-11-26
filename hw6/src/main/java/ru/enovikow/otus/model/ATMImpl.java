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


    public void insertSum(int value) {

        int[] totalNotes = XUtils.getTotalNotesCount(value);

        addNotes(totalNotes);
    }

    public void giveOutSum(int value) {
        int[] totalNotes = XUtils.getTotalNotesCount(value);
        giveOutNotes(totalNotes);
    }

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
