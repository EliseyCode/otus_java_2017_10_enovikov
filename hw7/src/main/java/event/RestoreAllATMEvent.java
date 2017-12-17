package event;

import atm.ATM;

public class RestoreAllATMEvent implements Event {

    @Override
    public void execute(ATM atm) {
        atm.restoreATM();
    }
}
