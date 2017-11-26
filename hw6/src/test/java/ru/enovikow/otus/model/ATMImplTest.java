package ru.enovikow.otus.model;

import org.junit.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

public class ATMImplTest {

    static ATMImpl atm = new ATMImpl();

    @BeforeClass
    public static void init() {
        int thousand = 50;
        int fiveHundred = 100;
        int oneHundred = 150;
        int fifty = 60;


        while (thousand > 0) {
            atm.addOneThousandInCell();
            thousand--;
        }

        while (fiveHundred > 0) {
            atm.addFiveHundredInCell();
            fiveHundred--;
        }

        while (oneHundred > 0) {
            atm.addOneHundredInCell();
            oneHundred--;
        }

        while (fifty > 0) {
            atm.addFiftyInCell();
            fifty--;
        }
    }

    @Test
    public void getIn() throws Exception {
        atm.insertSum(2250);
        Assert.assertEquals(52, atm.getOneThousandCellSize());
        Assert.assertEquals(100, atm.getFiveHundredCellSize());
        Assert.assertEquals(152, atm.getOneHundredCellSize());
        Assert.assertEquals(61, atm.getFiftyCellSize());
    }

    @Test
    public void giveOut() throws Exception {
        getIn();

    }

    @Test
    public void printSum() throws Exception {
        atm.printSum();
    }

}