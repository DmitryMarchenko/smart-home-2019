package ru.sbt.mipt.oop.smarthomeobjects;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Actionable;
import ru.sbt.mipt.oop.event.SensorEventType;

public class Door implements Actionable {
    private final String id;
    private boolean isOpen;

    public Door(boolean isOpen, String id) {
        this.isOpen = isOpen;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public boolean isOpen() {
        return isOpen;
    }

    @Override
    public void execute(Action action) {
        if (!action.getObjectId().equals(id) && !action.isForAll()) {
            return;
        }
        if (action.getEventType() == SensorEventType.DOOR_OPEN) {
            this.setOpen(true);
            System.out.println("Door " + this.getId() + " was opened.");
        }
        if (action.getEventType() == SensorEventType.DOOR_CLOSED) {
            this.setOpen(false);
            System.out.println("Door " + this.getId() +  " was closed.");
        }
    }
}
