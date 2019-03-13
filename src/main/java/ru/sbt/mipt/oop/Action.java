package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.event.SensorEventType;

public class Action {
    private String objectId;
    private SensorEventType eventType;
    private boolean forAll;

    public String getObjectId() {
        return objectId;
    }

    public SensorEventType getEventType() {
        return eventType;
    }

    public boolean isForAll() {
        return forAll;
    }

    public Action(String objectId, SensorEventType eventType, boolean forAll) {
        this.objectId = objectId;
        this.eventType = eventType;
        this.forAll = forAll;
    }
}
