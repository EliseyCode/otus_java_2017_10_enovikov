package ru.enovikow.otus.model;

import org.junit.Assert;

import org.junit.BeforeClass;
import org.junit.Test;
import ru.enovikow.otus.ATMImpl;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import static ru.enovikow.otus.model.NoteValue.*;

public class ATMImplTest {

    private static ATMImpl atm = new ATMImpl();

    @BeforeClass
    public static void init() {
        Queue<Money> pack = new LinkedList<>();
        int oneThousand = 50;
        int fiveHundred = 100;
        int oneHundred = 150;
        int fifty = 60;


        while (oneThousand > 0) {
            pack.add(new Ruble(ONE_THOUSAND));
            oneThousand--;
        }

        while (fiveHundred > 0) {
            pack.add(new Ruble(FIVE_HUNDRED));
            fiveHundred--;
        }

        while (oneHundred > 0) {
            pack.add(new Ruble(ONE_HUNDRED));
            oneHundred--;
        }

        while (fifty > 0) {
            pack.add(new Ruble(FIFTY));
            fifty--;
        }
        atm.insertSum(pack);
    }

    @Test
    public void giveOut() throws Exception {
        atm.giveOutSum(2250);

        Assert.assertEquals(48, atm.getOneThousandCellSize());
        Assert.assertEquals(100, atm.getFiveHundredCellSize());
        Assert.assertEquals(148, atm.getOneHundredCellSize());
        Assert.assertEquals(59, atm.getFiftyCellSize());

        atm.giveOutSum(1650);

        Assert.assertEquals(47, atm.getOneThousandCellSize());
        Assert.assertEquals(99, atm.getFiveHundredCellSize());
        Assert.assertEquals(147, atm.getOneHundredCellSize());
        Assert.assertEquals(58, atm.getFiftyCellSize());

        atm.giveOutSum(550);

        Assert.assertEquals(47, atm.getOneThousandCellSize());
        Assert.assertEquals(98, atm.getFiveHundredCellSize());
        Assert.assertEquals(147, atm.getOneHundredCellSize());
        Assert.assertEquals(57, atm.getFiftyCellSize());
    }

    @Test
    public void getIn() throws Exception {
        Queue<Money> insertMoney = new LinkedList<>();
        insertMoney.addAll(Arrays.asList(new Ruble(ONE_THOUSAND), new Ruble(ONE_THOUSAND), new Ruble(FIVE_HUNDRED), new Ruble(ONE_HUNDRED), new Ruble(FIFTY)));
        atm.insertSum(insertMoney);
        Assert.assertEquals(49, atm.getOneThousandCellSize());
        Assert.assertEquals(99, atm.getFiveHundredCellSize());
        Assert.assertEquals(148, atm.getOneHundredCellSize());
        Assert.assertEquals(58, atm.getFiftyCellSize());
    }

    @Test
    public void printSum() throws Exception {
        atm.printSum();
        Assert.assertEquals(50, atm.getOneThousandCellSize());
        Assert.assertEquals(100, atm.getFiveHundredCellSize());
        Assert.assertEquals(150, atm.getOneHundredCellSize());
        Assert.assertEquals(60, atm.getFiftyCellSize());
    }

}