package ru.sbt.mipt.oop.handlestrategy;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.SensorEventHandler;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.smarthomeobjects.Door;
import ru.sbt.mipt.oop.smarthomeobjects.Room;

import static ru.sbt.mipt.oop.event.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.event.SensorEventType.DOOR_OPEN;

public class DoorEventHandler implements SensorEventHandler {
    private final SmartHome smartHome;

    public DoorEventHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void handle(SensorEvent event) {
        if (!isDoorEvent(event)) {
            return;
        }
        Action action = new Action(event.getObjectId(), event.getType(), false);
        smartHome.execute(action);
    }

    private boolean isDoorEvent(SensorEvent event) {
        return event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED;
    }
}

