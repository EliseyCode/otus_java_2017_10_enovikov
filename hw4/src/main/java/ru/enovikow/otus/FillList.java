package ru.enovikow.otus;

import java.util.ArrayList;
import java.util.List;

public class FillList extends Thread {
    List<String> list = new ArrayList<>();
    private final static int ARRAY_VALUE = 500_000;
    private final static int REMOVE_VALUE = 250_000;

    public FillList() {
        start();
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("start");
            for (int i = 0; i < ARRAY_VALUE; i++) {
                list.add(new String());
            }

            System.out.println("500k added " + list.size());

            for (int i = list.size() - 1, j = 0; j < REMOVE_VALUE ; i--, j++) {
                list.remove(i);
            }
            System.out.println("removed list size: " + list.size());
            try {
//                System.out.println(Runtime.getRuntime().freeMemory());
                Thread.sleep(10000);
                System.out.println("After Sleep");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
