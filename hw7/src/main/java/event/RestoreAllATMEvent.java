package event;

import atm.ATM;

public class RestoreAllATMEvent extends Event {

    @Override
    public void execute(ATM atm) {
        atm.restoreATM();
    }
}
