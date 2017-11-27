package ru.enovikow.otus.model;

public class FiveHundred extends Money {
    @Override
    Money createNote() {
        return new FiveHundred();
    }
}
