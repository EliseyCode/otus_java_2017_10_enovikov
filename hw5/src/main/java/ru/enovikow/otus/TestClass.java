package ru.enovikow.otus;

import ru.enovikow.otus.annotations.After;
import ru.enovikow.otus.annotations.Before;
import ru.enovikow.otus.annotations.Test;

public class TestClass {

    @Before()
    public static String beforeString() {
        return "Before";
    }

    @Test()
    public static String testString() {
        return "Test";
    }

    @Test()
    public static String test2String() {
        return "Test";
    }

    @After()
    public static String afterString() {
        return "After";
    }
//    @After()
//    public static String afterStringTwo() {
//        return "After";
//    }

}
