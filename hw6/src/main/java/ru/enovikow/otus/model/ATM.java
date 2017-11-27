package ru.enovikow.otus.model;

import java.util.Queue;

public interface ATM {
    void insertSum(Queue<Money> notes) throws Exception;
    void giveOutSum(int value);
    void printSum();
}
