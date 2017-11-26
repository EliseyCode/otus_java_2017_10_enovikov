package ru.enovikow.otus;

import ru.enovikow.otus.annotations.After;
import ru.enovikow.otus.annotations.Before;
import ru.enovikow.otus.annotations.Test;

public class TestClass {

    @Before(testResult = "Befor")
    public static String beforeString() {
        return "Before";
    }

    @Test(testResult = "Test")
    public static String testString() {
        return "Test";
    }

    @After(testResult = "After")
    public static String afterString() {
        return "After";
    }
}
