package ru.enovikow.otus.model;

public class Ruble extends Money {
    private NoteValue value;

    public Ruble(NoteValue value) {
        this.value = value;
    }

    @Override
    public NoteValue getNominal() {
        return value;
    }

    @Override
    public String toString() {
        return "Ruble{" +
                "value=" + value +
                '}';
    }
}
