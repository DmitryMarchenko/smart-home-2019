package ru.sbt.mipt.oop.eventtimelinestrategy;

import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.event.Event;

import java.util.Collection;

public class OrderedGenerateAndHandleEventTimeline implements EventTimeline {
    private final EventGenerator eventGenerator;
    private final Collection<EventHandler> eventHandlers;
    private final TimelineStopper stopper;

    public OrderedGenerateAndHandleEventTimeline(EventGenerator eventGenerator,
                                                 Collection<EventHandler> eventHandlers,
                                                 TimelineStopper stopper) {
        this.eventGenerator = eventGenerator;
        this.eventHandlers = eventHandlers;
        this.stopper = stopper;
    }

    @Override
    public void process() {
        while (!stopper.stop()) {
            Event event = eventGenerator.getNextEvent();
            System.out.println("Got event: " + event);
            for (EventHandler eventHandler: eventHandlers) {
                eventHandler.handle(event);
            }
        }
    }
}
