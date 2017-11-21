package ru.enovikow.otus;

import java.util.ArrayList;
import java.util.List;

public class FillList extends Thread {
    private List<String> list = new ArrayList<>();
    private final static int FILL_ARRAY_VALUE = 500_000;
    private final static int REMOVE_VALUE = 250_000;

    FillList() {
        start();
    }

    @Override
    public void run() {
        boolean isAlive = true;

        while (isAlive) {
            try {

                for (int i = 0; i < FILL_ARRAY_VALUE; i++) {
                    list.add(new String());
                }

                System.out.println("list size after 500k elements was added: " + list.size());

                for (int i = list.size() - 1, j = 0; j < REMOVE_VALUE; i--, j++) {
                    list.remove(i);
                }

                System.out.println("list size after removing: : " + list.size() + "\n");

                Thread.sleep(9000);
            } catch (InterruptedException e) {
                isAlive = false;
                e.printStackTrace();
            }
        }
    }
}