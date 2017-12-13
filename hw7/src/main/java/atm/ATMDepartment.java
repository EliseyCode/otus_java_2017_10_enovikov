package atm;

import event.Event;

import java.util.ArrayList;
import java.util.List;

public class ATMDepartment {
    private static ATMDepartment atmDepartment;
    protected final List<Observer> observers = new ArrayList<>();
    private static final String RESTORE_TO_BASE_STATE = "restore";

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

    public void unregister(Observer observer) {
        observers.remove(observer);
    }

    public void notify(Event event) {
        if (RESTORE_TO_BASE_STATE.equals(event.getEvent())) {
            observers.forEach(observer -> observer.notify(event));
        }
    }

    public void collectMoney() {
        observers.forEach(observer -> System.out.println(((ATM) observer).getBalance()));
    }

    private ATMDepartment() {
    }
}
