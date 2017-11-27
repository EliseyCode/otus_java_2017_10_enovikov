package ru.enovikow.otus.model;

public interface ATM {
    void insertSum(Money note) throws Exception;
    void giveOutSum(int value);
    void printSum();
}
