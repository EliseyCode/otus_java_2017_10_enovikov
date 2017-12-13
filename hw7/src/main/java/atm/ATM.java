package atm;

import event.Event;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ATM implements Observer {
    private final Cell first;
    private Cell current;

    public ATM(List<Cell> cells) {

        Collections.sort(cells);
        first = cells.get(0);
        linkCells(cells);

        current = first.clone();
    }

    public boolean withdraw(int requested) {
        return current.withdraw(requested);
    }

    public int getBalance() {
        Iterator<Cell> iterator = current.iterator();
        int balance = 0;
        while (iterator.hasNext()) {
            balance += iterator.next().getBalance();
        }
        return balance;
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
        if ("restore".equals(event.getEvent())) {
            restoreATM();
        }
    }

    private void restoreATM() {
        current = first.clone();
    }
}
