package ru.enovikow.otus;

import ru.enovikow.otus.utils.XUtils;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println(list);
        List<Integer> newList = XUtils.reverseList(list);
        System.out.println(newList);
    }
}
