package event;

public class RestoreAllATMEvent extends Event {
    @Override
    public String getEvent() {
        return "restore";
    }
}
