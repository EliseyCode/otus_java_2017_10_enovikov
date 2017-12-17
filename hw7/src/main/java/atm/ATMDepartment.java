package atm;

import event.Event;

import java.util.ArrayList;
import java.util.List;

public class ATMDepartment {
    private static ATMDepartment atmDepartment;
    private final List<Observer> observers = new ArrayList<>();

    public static ATMDepartment getATMDepartment() {
        if (atmDepartment == null) {
            return new ATMDepartment();
        } else {
            return atmDepartment;
        }
    }

    public void register(Observer observer) {
        observers.add(observer);
    }

    void unregister(Observer observer) {
        observers.remove(observer);
    }

    public void notify(Event event) {
        observers.forEach(observer -> observer.notify(event));
    }

    List<Observer> getObservers() {
        return observers;
    }

    private ATMDepartment() {
    }
}
