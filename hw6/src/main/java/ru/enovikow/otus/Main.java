package ru.enovikow.otus;

import ru.enovikow.otus.model.Money;
import ru.enovikow.otus.model.NoteValue;
import ru.enovikow.otus.model.Ruble;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {
        ATMImpl atm = new ATMImpl();
        atm.printSum();
        Queue<Money> deposit = new LinkedList<>();
        deposit.addAll(Arrays.asList(new Ruble(NoteValue.ONE_THOUSAND), new Ruble(NoteValue.ONE_THOUSAND),
                new Ruble(NoteValue.ONE_THOUSAND), new Ruble(NoteValue.FIVE_HUNDRED), new Ruble(NoteValue.FIVE_HUNDRED),
                new Ruble(NoteValue.ONE_HUNDRED), new Ruble(NoteValue.ONE_HUNDRED), new Ruble(NoteValue.ONE_HUNDRED),
                new Ruble(NoteValue.FIFTY), new Ruble(NoteValue.FIFTY), new Ruble(NoteValue.FIFTY)));
        atm.insertSum(deposit);
        atm.printSum();

        atm.giveOutSum(950);
        atm.printSum();
    }
}
