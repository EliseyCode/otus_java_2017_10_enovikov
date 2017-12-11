import atm.ATM;
import atm.Cell;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Cell> cells = new ArrayList<>();

        cells.add(new Cell(1, 10));
        cells.add(new Cell(5, 10));
        cells.add(new Cell(10, 10));
        cells.add(new Cell(50, 10));
        cells.add(new Cell(100, 10));

        ATM atm = new ATM(cells);
        System.out.println("Initial balance: " + atm.getBalance());

        atm.withdraw(1578);
        System.out.println("Final balance: " + atm.getBalance());
    }
}
