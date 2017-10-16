package ru.enovikow.otus.utils;

import com.google.common.collect.ImmutableList;

import java.util.List;

public class XUtils {
    public static List<Integer> reverseList(List<Integer> list) {
        return ImmutableList.copyOf(list).reverse();
    }
}
