package ru.sbt.mipt.oop.generatestrategy;

import ru.sbt.mipt.oop.EventGenerator;
import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.event.SensorEventType;

public class RandomChooseForNextSensorEvent implements EventGenerator {
    @Override
    public SensorEvent getNextEvent() {
        // pretend like we're getting the events from physical world, but here we're going to just generate some random events
        SensorEventType sensorEventType = SensorEventType.values()[(int) (SensorEventType.values().length * Math.random())];
        String objectId = "" + ((int) (10 * Math.random()));
        return new SensorEvent(sensorEventType, objectId);
    }
}
