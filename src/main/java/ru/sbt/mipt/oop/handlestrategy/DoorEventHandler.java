package ru.sbt.mipt.oop.handlestrategy;

import ru.sbt.mipt.oop.EventHandler;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.event.Event;
import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.event.SensorEventType;
import ru.sbt.mipt.oop.smarthomeobjects.Door;

public class DoorEventHandler implements EventHandler {
    private final SmartHome smartHome;

    public DoorEventHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void handle(Event event) {
        if (!(event instanceof SensorEvent)) {
            return;
        }
        SensorEvent sensorEvent = (SensorEvent) event;
        smartHome.execute(obj -> {
            if (!(obj instanceof Door)) {
                return;
            }
            Door door = (Door) obj;
            if (sensorEvent.getObjectId().equals(door.getId())) {
                if (sensorEvent.getType() == SensorEventType.DOOR_OPEN) {
                    door.setOpen(true);
                    System.out.println("Door " + door.getId() + " was opened.");
                }
                if (sensorEvent.getType() == SensorEventType.DOOR_CLOSED) {
                    door.setOpen(false);
                    System.out.println("Door " + door.getId() +  " was closed.");
                }
            }
        });
    }
}

