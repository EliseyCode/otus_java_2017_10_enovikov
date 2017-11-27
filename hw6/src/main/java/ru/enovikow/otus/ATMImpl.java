package ru.enovikow.otus;

import ru.enovikow.otus.exceptions.NoSuchNoteException;
import ru.enovikow.otus.model.ATM;
import ru.enovikow.otus.model.Money;
import ru.enovikow.otus.model.NoteValue;

import java.util.LinkedList;
import java.util.Queue;

public class ATMImpl implements ATM {

    private Queue<Money> oneThousandCell;
    private Queue<Money> fiveHundredCell;
    private Queue<Money> oneHundredCell;
    private Queue<Money> fiftyCell;

    {
        oneThousandCell = new LinkedList<>();
        fiveHundredCell = new LinkedList<>();
        oneHundredCell = new LinkedList<>();
        fiftyCell = new LinkedList<>();
    }

    public void insertSum(Queue<Money> notes) {
        notes.forEach(this::insertNote);
    }

    private void insertNote(Money note) {
        if (NoteValue.ONE_THOUSAND.equals(note.getNominal())) {
            oneThousandCell.add(note);
        } else if (NoteValue.FIVE_HUNDRED.equals(note.getNominal())) {
            fiveHundredCell.add(note);
        } else if (NoteValue.ONE_HUNDRED.equals(note.getNominal())) {
            oneHundredCell.add(note);
        } else if (NoteValue.FIFTY.equals(note.getNominal())) {
            fiftyCell.add(note);
        } else {
            throw new NoSuchNoteException("Note is illegal " + note.toString());
        }
    }

    public void giveOutSum(int value) {
        int[] availableNotes = new int[]{getOneThousandCellSize(), getFiveHundredCellSize(), getOneHundredCellSize(), getFiftyCellSize()};
        int[] toGiveValue = XUtils.sumToBeGiven(availableNotes, value);

        Queue<Money> total = new LinkedList();

        for (int i = 0; i < availableNotes.length; i++) {
            for (int j = 0; j < toGiveValue[i]; j++) {
                switch (i) {
                    case 0: {
                        total.add(oneThousandCell.poll());
                        break;
                    }
                    case 1: {
                        total.add(fiveHundredCell.poll());
                        break;
                    }
                    case 2: {
                        total.add(oneHundredCell.poll());
                        break;
                    }
                    case 3: {
                        total.add(fiftyCell.poll());
                        break;
                    }
                }
            }
        }
        issueRequestedAmount(total, value);
    }

    private void issueRequestedAmount(Queue<Money> total, int value) {
        System.out.format("Sum to be given: %s \n", value);
        total.forEach(System.out::println);

        System.out.println();
    }

    public void printSum() {
        int totalCount = getOneThousandCellSize() * 1000
                + getFiveHundredCellSize() * 500
                + getOneHundredCellSize() * 100
                + getFiftyCellSize() * 50;

        System.out.format("Total sum: %d Thousands %d FiveHundreds %d Hundreds %d Fifty %d \n", totalCount,
                getOneThousandCellSize(), getFiveHundredCellSize(), getOneHundredCellSize(), getFiftyCellSize());
    }

    public int getOneThousandCellSize() {
        return oneThousandCell.size();
    }

    public int getFiveHundredCellSize() {
        return fiveHundredCell.size();
    }

    public int getOneHundredCellSize() {
        return oneHundredCell.size();
    }

    public int getFiftyCellSize() {
        return fiftyCell.size();
    }
}