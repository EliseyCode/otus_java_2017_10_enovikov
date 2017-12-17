package atm;

import event.Event;
import event.RestoreAllATMEvent;

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

    protected void notify(Event event) {
        observers.forEach(observer -> observer.notify(event));
    }

    List<Observer> getObservers() {
        return observers;
    }

    public int getTotalBalance() {
        return observers.stream().mapToInt(observer -> ((ATM) observer).getBalance()).sum();
    }

    public void restoreAllATM() {
        notify(new RestoreAllATMEvent());
    }

    private ATMDepartment() {
    }
}
