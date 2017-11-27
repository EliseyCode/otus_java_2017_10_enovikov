package ru.enovikow.otus.model;

public class Fifty extends Money {
    @Override
    Money createNote() {
        return new Fifty();
    }
}
