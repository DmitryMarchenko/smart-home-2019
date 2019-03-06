package ru.sbt.mipt.oop.randomgeneratestrategy;

import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.SensorEventGenerator;
import ru.sbt.mipt.oop.event.SensorEventType;

public class RandomChooseForNextSensorEvent implements SensorEventGenerator {
    @Override
    public SensorEvent getNextSensorEvent() {
        // pretend like we're getting the events from physical world, but here we're going to just generate some random events
        SensorEventType sensorEventType = SensorEventType.values()[(int) (SensorEventType.values().length * Math.random())];
        String objectId = "" + ((int) (10 * Math.random()));
        return new SensorEvent(sensorEventType, objectId);
    }
}
