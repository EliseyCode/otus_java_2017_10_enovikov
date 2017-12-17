package event;

import atm.ATM;

@FunctionalInterface
public interface Event {
 void execute(ATM atm);
}
