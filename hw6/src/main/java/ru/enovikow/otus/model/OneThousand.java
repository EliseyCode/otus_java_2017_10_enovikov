package ru.enovikow.otus.model;

public class OneThousand extends Money {
    @Override
    Money createNote() {
        return new OneThousand();
    }
}
