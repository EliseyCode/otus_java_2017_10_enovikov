package ru.enovikow.otus.model;

public class OneHundred extends Money {

    @Override
    Money createNote() {
        return new OneHundred();
    }
}
