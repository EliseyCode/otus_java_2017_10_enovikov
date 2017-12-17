package atm;


import event.Event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ATM implements Observer {
    private Cell currentState;

    private List<Memento> savedStates = new ArrayList<>();

    public ATM(List<Cell> cells) {
        Collections.sort(cells);
        Cell initialState = cells.get(0);
        linkCells(cells);
        savedStates.add(new Memento(initialState));
        currentState = initialState;

    }

    public boolean withdraw(int requested) {
        return currentState.withdraw(requested);
    }

    public int getBalance() {
        return currentState.getBalance();
    }

    private void linkCells(List<Cell> cells) {
        Iterator<Cell> iterator = cells.iterator();
        Cell cellA = iterator.next();
        while (iterator.hasNext()) {
            Cell cellB = iterator.next();
            cellA.setNext(cellB);
            cellA = cellB;
        }
    }

    @Override
    public void notify(Event event) {
        event.execute(this);
    }

    public void restoreATM() {
        currentState = savedStates.get(0).getInitialState();
    }
}
