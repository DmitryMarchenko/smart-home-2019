package ru.sbt.mipt.oop.eventtimelinestrategy;

import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.event.SensorEvent;

import java.util.Collection;

public class OrderedGenerateAndHandleEventTimeline implements EventTimeline {
    private final SensorEventGenerator eventGenerator;
    private final Collection<SensorEventHandler> eventHandlers;
    private final TimelineStopper stopper;

    public OrderedGenerateAndHandleEventTimeline(SensorEventGenerator eventGenerator,
                                                 Collection<SensorEventHandler> eventHandlers,
                                                 TimelineStopper stopper) {
        this.eventGenerator = eventGenerator;
        this.eventHandlers = eventHandlers;
        this.stopper = stopper;
    }

    @Override
    public void process() {
        while (!stopper.stop()) {
            SensorEvent event = eventGenerator.getNextSensorEvent();
            System.out.println("Got event: " + event);
            for (SensorEventHandler eventHandler: eventHandlers) {
                eventHandler.handle(event);
            }
        }
    }
}
