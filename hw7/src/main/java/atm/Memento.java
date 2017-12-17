package atm;

class Memento {
    private final Cell initialState;

    Memento(Cell stateToSave) {
        this.initialState = stateToSave.clone();
    }

    Cell getInitialState() {
        return initialState;
    }
}
