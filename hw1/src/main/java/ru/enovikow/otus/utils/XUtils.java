package ru.enovikow.otus.utils;

import com.google.common.collect.ImmutableList;
import java.util.List;

public class ListReverse {

//    public List<Integer> reverseList() {
//
//    }

    public List<Integer> reverseList(List<Integer> list) {
        return (List) ImmutableList.of(list).reverse();
    }
}
