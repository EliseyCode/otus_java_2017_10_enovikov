import atm.ATM;
import atm.ATMDepartment;
import atm.Cell;
import event.CollectATMBalance;
import event.RestoreAllATMEvent;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static ATMDepartment atmDepartment;

    static {
        atmDepartment = ATMDepartment.getATMDepartment();
    }

    public static void main(String[] args) {
        List<Cell> cells = new ArrayList<>();

        cells.add(new Cell(1, 10));
        cells.add(new Cell(5, 10));
        cells.add(new Cell(10, 10));
        cells.add(new Cell(50, 10));
        cells.add(new Cell(100, 10));
        ATM atm = new ATM(cells);
        atmDepartment.register(atm);

        cells.removeAll(cells);

        cells.add(new Cell(1, 30));
        cells.add(new Cell(5, 30));
        cells.add(new Cell(10, 30));
        cells.add(new Cell(50, 30));
        cells.add(new Cell(100, 30));
        ATM atm2 = new ATM(cells);
        atmDepartment.register(atm2);

        System.out.println("First ATM before " + atm.getBalance());
        System.out.println("Second ATM before " + atm2.getBalance());

        atm.withdraw(200);
        atm2.withdraw(1000);
        System.out.println("First ATM after " + atm.getBalance());
        System.out.println("Second ATM after " + atm2.getBalance());

        atmDepartment.notify(new CollectATMBalance());

        atmDepartment.notify(new RestoreAllATMEvent());
        System.out.println("First ATM after restore " + atm.getBalance());
        System.out.println("Second ATM after restore " + atm2.getBalance());
    }
}
