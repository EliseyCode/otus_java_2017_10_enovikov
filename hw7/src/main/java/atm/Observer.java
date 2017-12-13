package atm;

import event.Event;

@FunctionalInterface
public interface Observer {
    void notify(Event event);
}
