package atm;

import event.RestoreAllATMEvent;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class ATMDepartmentTest {

    private ATMDepartment atmDepartment = ATMDepartment.getATMDepartment();
    private List<Cell> cells;
    private ATM atm;
    private ATM atm2;

    @Before
    public void init() {
        cells = new ArrayList<>();

        cells.add(new Cell(1, 10));
        cells.add(new Cell(5, 10));
        cells.add(new Cell(10, 10));
        cells.add(new Cell(50, 10));
        cells.add(new Cell(100, 10));
        atm = new ATM(cells);
        atmDepartment.register(atm);

        cells.removeAll(cells);

        cells.add(new Cell(1, 30));
        cells.add(new Cell(5, 30));
        cells.add(new Cell(10, 30));
        cells.add(new Cell(50, 30));
        cells.add(new Cell(100, 30));
        atm2 = new ATM(cells);
        atmDepartment.register(atm2);
    }

    @Test
    public void registerAndUnregisterCount() {
        assertEquals(2, atmDepartment.observers.size());
        atmDepartment.register(new ATM(cells));
        assertEquals(3, atmDepartment.observers.size());
        atmDepartment.unregister(atm);
        atmDepartment.unregister(atm2);
        assertEquals(1, atmDepartment.observers.size());
    }

    @Test
    public void atmWithdraw() {
        assertEquals(1660, atm.getBalance());
        atm.withdraw(920);
        assertEquals(1660 - 920, atm.getBalance());

    }

    @Test
    public void restoreBaseCondition() {
        assertEquals(1660, atm.getBalance());
        assertEquals(4980, atm2.getBalance());

        atm.withdraw(200);
        atm2.withdraw(300);

        assertEquals(1460, atm.getBalance());
        assertEquals(4680, atm2.getBalance());

        atmDepartment.notify(new RestoreAllATMEvent());

        assertEquals(1660, atm.getBalance());
        assertEquals(4980, atm2.getBalance());
    }
}