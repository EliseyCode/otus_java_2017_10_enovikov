package ru.enovikow.otus;

public class Main {

    public static void main(String[] args) {
        try {
            MainTest.start(TestClass.class);
            MainTest.start("ru.enovikow.otus");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
