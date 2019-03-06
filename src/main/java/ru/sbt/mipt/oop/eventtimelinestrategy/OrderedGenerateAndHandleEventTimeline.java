package ru.sbt.mipt.oop.eventtimelinestrategy;

import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.event.SensorEvent;

public class OrderedGenerateAndHandleEventTimeline implements EventTimeline {
    private final SensorEventGenerator eventGenerator;
    private final SensorEventHandler eventHandler;
    private final TimelineStopper stopper;

    public OrderedGenerateAndHandleEventTimeline(SensorEventGenerator eventGenerator,
                                                 SensorEventHandler eventHandler,
                                                 TimelineStopper stopper) {
        this.eventGenerator = eventGenerator;
        this.eventHandler = eventHandler;
        this.stopper = stopper;
    }

    @Override
    public void process() {
        while (!stopper.stop()) {
            SensorEvent event = eventGenerator.getNextSensorEvent();
            eventHandler.handle(event);
        }
    }
}
