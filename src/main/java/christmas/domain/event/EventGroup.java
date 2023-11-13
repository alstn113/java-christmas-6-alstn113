package christmas.domain.event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EventGroup {
    List<Event> events;

    public EventGroup(List<Event> events) {
        this.events = new ArrayList<>(events);
    }

    public List<Event> getEvents() {
        return Collections.unmodifiableList(events);
    }
}
