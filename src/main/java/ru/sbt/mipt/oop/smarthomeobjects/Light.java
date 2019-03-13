package ru.sbt.mipt.oop.smarthomeobjects;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Actionable;
import ru.sbt.mipt.oop.event.SensorEventType;

public class Light implements Actionable {
    private boolean isOn;
    private final String id;

    public Light(String id, boolean isOn) {
        this.id = id;
        this.isOn = isOn;
    }

    public boolean isOn() {
        return isOn;
    }

    public String getId() {
        return id;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    @Override
    public void execute(Action action) {
        if (!id.equals(action.getObjectId())) {
            return;
        }
        if (action.getEventType() == SensorEventType.LIGHT_ON) {
            this.setOn(true);
            System.out.println("Light " + this.getId() + " was turned on.");
        }
        if (action.getEventType() == SensorEventType.LIGHT_OFF) {
            this.setOn(false);
            System.out.println("Light " + this.getId() + " was turned off.");
        }
    }
}
