package ru.sbt.mipt.oop.adapters;

import com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.event.SensorEventType;

public class CCSensorEventToSensorEventAdapter extends SensorEvent {
    private final CCSensorEvent ccSensorEvent;

    public CCSensorEventToSensorEventAdapter(CCSensorEvent ccSensorEvent) {
        this.ccSensorEvent = ccSensorEvent;
    }

    @Override
    public SensorEventType getType() {
        switch (ccSensorEvent.getEventType()) {
            case "LightIsOn" : return SensorEventType.LIGHT_ON;
            case "LightIsOff" : return SensorEventType.LIGHT_OFF;
            case "DoorIsOpen" : return SensorEventType.DOOR_OPEN;
            case "DoorIsClosed" : return SensorEventType.DOOR_CLOSED;
            default: return null;
        }
    }

    @Override
    public String getObjectId() {
        return ccSensorEvent.getObjectId();
    }
}
