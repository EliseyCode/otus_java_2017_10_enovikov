package event;

import atm.ATM;

public class CollectATMBalance extends Event {
    @Override
    public void execute(ATM atm) {
        atm.getBalance();
    }
}
